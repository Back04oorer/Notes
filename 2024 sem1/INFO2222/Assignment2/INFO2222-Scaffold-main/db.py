'''
db
database file, containing all the logic to interface with the sql database
'''

from sqlalchemy import create_engine
from sqlalchemy.orm import Session
from models import *

from pathlib import Path

# creates the database directory
Path("database") \
    .mkdir(exist_ok=True)

# "database/main.db" specifies the database file
# change it if you wish
# turn echo = True to display the sql output
engine = create_engine("sqlite:///database/main.db", echo=False)

# initializes the database
Base.metadata.create_all(engine)

# inserts a user to the database
def insert_user(username: str, password: str):
    with Session(engine) as session:
        user = User(username=username, password=password)
        session.add(user)
        session.commit()

# gets a user from the database
def get_user(username: str):
    with Session(engine) as session:
        return session.get(User, username)

# gets all users from the database
def get_all_users():
    with Session(engine) as session:
        # Query all users
        users = session.query(User).all()
        # Extract and return user details
        return [(user.username, user.password) for user in users]

# deletes a user from the database by username
def delete_user(username: str):
    with Session(engine) as session:
        # Query the user by username
        user = session.query(User).filter_by(username=username).first()
        if user:
            # If user exists, delete it
            session.delete(user)
            session.commit()
            return True  # Indicate the user was found and deleted
        else:
            return False  # Indicate no user was found with that username

