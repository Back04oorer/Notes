### ACID
- **Atomic:** State shows either all the effects of txn, or none of them
- **Consistent:** Txn moves from a state where integrity holds, to another where integrity holds.
- **Isolated:** Effect of txns is the same as txns running one after another (ie looks like batch mode)
- **Durable:** Once a txn has committed, its effects remain in the database
### Definition
- A transaction is a collection of one or more operation on one or more database, which reflects a single real-word transition.
	- In the real world, this happened (completely) or it didn’t happen at all (<mark style="background: #FF5582A6;">Atomicity</mark>)
	- Once it has happened, it isn’t forgotten (<mark style="background: #FF5582A6;">Durability</mark>)
- Commerce examples
	- Transfer money between accounts
	- Purchase a group of products
### Coding a transaction
- Typically a computer-based system doing OLTP has a collection of application programs
- Each program is written in a high-level language, which calls DBMS to perform individual SQL statements
	- Either through embedded SQL converted by preprocessor
	- Or through Call Level Interface where application constructs appropriate string and passes it to DBMS
### Why write programs?
- An individual SQL statement can’t do enough
	- It can’t update multiple tables
	- It can’t perform complicated logic (conditionals, looping, etc)
### COMMIT
- As application program is executing, it is “in a transaction”
- Program can execute COMMIT
	- SQL command to finish the transaction successfully
	- The next SQL statement will automatically start a new transaction
### Atomicity
- Two possible outcomes for a transaction
	- It commits: all the changes are made
	- It aborts: no changes are made
- That is, transaction’s activities are <mark style="background: #FF5582A6;">all</mark> or <mark style="background: #FF5582A6;">nothing</mark>
	- Furthermore, once an outcome has been reached, it doesn’t change
### ROLLBACK
- If the application code gets to a place where it can’t complete the transaction successfully, it can execute ROLLBACK
- This causes the system to <mark style="background: #FF5582A6;">“abort”</mark> the transaction
	- The database returns to the state without any of the previous changes made by activity of the transaction
### API for Transactions
- A transaction ends by:
	- COMMIT requests to commit current transaction.
	- ROLLBACK causes current transaction to abort - always satisfied.
- The commit command is a request
	- The system might commit the transaction, or it might abort it for one of several reasons.
### Reasons for Rollback
- User changes their mind (“ctl-C”/cancel)
- Explicit in program, when application code finds a problem
- System-initiated abort
### Autocommit
- Application programmer can set the connection to autocommit
	- every single SQL statement is a transaction in itself
	- no need for explicit commit/rollback code in application
	- but lose the capacity to group several table activities into a single real-world transition
### Python
```python
import pg8000

def bookFlight(flight_num, flight_date, seat_no):
    try:
        # Connect To DB
        conn = pg8000.connect(database="postgres", user="X", password="secret")

        # Execute
        curs = conn.cursor()
        stmt = """SELECT occupied FROM Flight
                  WHERE flightNum=%s AND flightDate=%s AND seat=%s"""
        curs.execute(stmt, (flight_num, flight_date, seat_no))
        result = curs.fetchone()

        if result is None or result[0] == False:
            update_stmt = """UPDATE Flight SET occupied=TRUE
                             WHERE flightNum=%s AND flightDate=%s AND seat=%s"""
            curs.execute(update_stmt, (flight_num, flight_date, seat_no))
        
        # commit
        conn.commit()

    except Exception as e:
	    # roll back
        print(f"An error occurred: {e}")
        conn.rollback()

    finally:
        curs.close()
        conn.close()

# invoke
bookFlight('123', '2023-12-25', '12C')
```
### Integrity
- A real world state is reflected by collections of values in the tables of the DBMS
- But not every collection of values in a table makes sense in the real world
- The state of the tables is restricted by <mark style="background: #FF5582A6;">integrity constraints</mark>
- So the DBMS will enforce them
	- Especially: primary key (some column’s values are non null, and different in every row)
	- And referential integrity: value of foreign key column is actually found in another “referenced” table
- Some constraints are not declared
	- They are business rules that are supposed to hold
### Dynamic Integrity Constraints
- Some constraints restrict allowable state transitions
	- A transaction might transform the database from one consistent state to another, but the transition might not be permissible
	- **Example**: Students can only progress from Junior via Intermediate to the Senior year, but can never be degraded.
- Dynamic constraints cannot be checked by examining the database state
### Transaction Consistency
- A transaction is <mark style="background: #FF5582A6;">consistent</mark> if, assuming the database is in a consistent state initially, when the transaction completes:
	- All static integrity constraints are satisfied (but constraints might be violated in intermediate states)
		- Can be checked by examining snapshot of database
	- New state satisfies specifications of transaction
		- Cannot be checked from database snapshot
	- No dynamic constraints have been violated
		- Cannot be checked from database snapshot
- This is an obligation on the programmer
	- Usually the organization has a testing/checking and sign-off mechanism before an application program is allowed to get installed in the production system
### Integrity Constraints in Transactions
- When do we check integrity constraints:
- Integrity constraints may be declared:
	- NOT DEFERRABLE
		- The default. It means that every time a database modification occurs, the constraint is checked immediately afterwards.
	- DEFERRABLE
		- Gives the option to wait until a transaction is complete before checking the constraint.
```Python
BEGIN TRANSACTION; 
	SET CONSTRAINTS UnitOfStudy_FK DEFERRED; 
	INSERT INTO UnitOfStudy VALUES(‘info1000’,’Intro to…’,42,6);
	INSERT INTO Lecturer VALUES(42,’Steve McQueen’, …); 
COMMIT;
```
### System obligations
- Provided the application programs have been written properly,
- Then the DBMS is supposed to make sure that the state of the data in the DBMS reflects the real world accurately, as affected by all the committed transactions
### Local to global reasoning
- Organization checks each application program as a separate task
	- Each application program running on its own moves from state where integrity constraints are valid to another state where they are valid
- System makes sure there are no nasty interactions
- So the final state of the data will satisfy all the integrity constraints
### Threats to data integrity
- Need for application rollback
- System crash
- Concurrent Activity
- The system has mechanisms to handle these
### Application rollback
- A transaction may have made changes to the data before discovering that these aren’t appropriate
	- the data is in state where integrity constraints are false
	- Application executes ROLLBACK
- System must somehow return to earlier state
	- Where integrity constraints hold
- So the aborted transaction has no effect at all
### System crash
- At time of crash, an application program may be part-way through (and the data may not meet integrity constraints)
- Also, buffering can cause problems
	- Note that system crash loses all buffered data, restart has only disk state
	- Effects of a committed txn may be only in buffer, not yet recorded in disk state
	- Lack of coordination between flushes of different buffered pages, so even if current state satisfies constraints, the disk state may not
### Concurrency
- When operations of concurrent <mark style="background: #FF5582A6;">threads are interleaved</mark>, the effect on shared state can be unexpected
- Well known issue in operating systems, thread programming
### Famous anomalies
- Dirty data
	- One task T reads data written by T’ while T’ is running, then T’ aborts (so its data was not appropriate)
- Lost update
	- Two tasks T and T’ both modify the same data
	- T and T’ both commit 
	- Final state shows effects of only T, but not of T’
- Inconsistent read
	- One task T sees some but not all changes made by T’
	- The values observed may not satisfy integrity constraints
	- This was not considered by the programmer, so code moves into absurd path
### Problems with serializability
- The performance reduction from serializable isolation is high
	- Transactions are often blocked because they want to read data that another txn has changed
- For many applications, the accuracy of the data they read is not crucial
	- overbooking a plane is ok in practice
	- your banking decisions would not be very different if you saw yesterday’s balance instead of the most up-to-date
### A and D matter!
- Even when isolation isn’t needed, no one is willing to give up atomicity and durability
	- These deal with modifications a txn makes
	- Writing is less frequent than reading, so log entries, and locks for writes (eg insert, update), are considered worth the effort