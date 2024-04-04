'''
db
database file, containing all the logic to interface with the sql database
'''

from sqlalchemy import create_engine
from sqlalchemy.orm import Session
from models import *
from sqlalchemy import Table
from pathlib import Path
from sqlalchemy import or_

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

# 添加RoomInfo记录到数据库
def insert_room(room_id: int, user_a: str, user_b: str):
    with Session(engine) as session:
        room_info = RoomInfo(room_id=room_id, user_a=user_a, user_b=user_b)
        session.add(room_info)
        try:
            session.commit()
            print(f"Room {room_id} created with users {user_a} and {user_b}.")
        except Exception as e:
            session.rollback()  # 如果发生错误，则回滚
            print(f"Failed to insert room info: {e}")
        finally:
            session.close()  # 确保session被正确关闭


def find_room_id_by_users(user_a: str, user_b: str) -> int:
    with Session(engine) as session:
        # 使用or_来构建逻辑或条件
        room_info = session.query(RoomInfo).filter(
            or_(
                (RoomInfo.user_a == user_a) & (RoomInfo.user_b == user_b),
                (RoomInfo.user_a == user_b) & (RoomInfo.user_b == user_a)
            )
        ).first()

        if room_info is not None:
            return room_info.room_id
        
        return None

def find_free_room_id():
    with Session(engine) as session:
        # 获取所有现有的room_id，按升序排序
        existing_ids = session.query(RoomInfo.room_id).order_by(RoomInfo.room_id).all()
        existing_ids = [id[0] for id in existing_ids]  # 将结果转换为单个数字列表

        # 寻找第一个空闲的ID
        free_id = 1
        while free_id in existing_ids:
            free_id += 1

        return free_id


def insert_message(room_id: int, sender: str, content: str):
    with Session(engine) as session:
        # 创建一个Message实例
        message = Message(room_id=room_id, sender=sender, content=content)
        
        # 添加这个实例到session
        session.add(message)
        
        # 提交session到数据库
        try:
            session.commit()
            print(f"Message added: {sender}: {content} in room {room_id}")
        except Exception as e:
            # 如果出现错误，回滚更改
            session.rollback()
            print(f"Failed to insert message: {e}")
        finally:
            # 关闭session
            session.close()

def get_all_messages():
    with Session(engine) as session:
        # 查询messages表中的所有记录
        messages = session.query(Message).all()

        # 用于存储所有消息信息的列表
        all_messages = []

        # 遍历每条消息记录，并收集其详细信息
        for message in messages:
            message_info = (message.id, message.room_id, message.sender, message.content)
            all_messages.append(message_info)

            # 如果需要在控制台打印每条消息的详细信息
            print(f"ID: {message.id}, Room ID: {message.room_id}, Sender: {message.sender}, Content: {message.content}")

        # 返回所有消息的详细信息列表
        return all_messages

def get_messages_by_room_id(room_id: int) -> list:
    with Session(engine) as session:
        # 查询指定room_id的所有消息
        messages = session.query(Message.sender, Message.content).filter(Message.room_id == room_id).all()

        # messages 已经是一个包含了很多元组的列表，每个元组包含(sender, content)
        return messages


#################################################################################
# 下面是支持函数
#################################################################################


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

def get_all_room_info():
    with Session(engine) as session:
        # 查询RoomInfo表中的所有记录
        rooms = session.query(RoomInfo).all()

        # 如果需要，您可以在这里打印每个房间的信息，或者返回这些信息
        for room in rooms:
            print(f"Room ID: {room.room_id}, User A: {room.user_a}, User B: {room.user_b}")

        # 或者，如果您想返回这些记录以供进一步处理，可以这样做：
        return rooms

def drop_room_info_table():
    with engine.begin() as connection:
        # 直接删除RoomInfo表
        RoomInfo.__table__.drop(bind=engine, checkfirst=True)
        print("RoomInfo table has been dropped.")