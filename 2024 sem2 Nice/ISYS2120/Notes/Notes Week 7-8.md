# Week 7 

## 1. Data Security Goals ❗️
##### Confidentiality Properties
User <mark style="background: #FF5582A6;">should not</mark> be able to see things which they are not supposed to see.
##### Integrity Properties
User <mark style="background: #FF5582A6;">should not</mark> be able to modify things which they are not supposed to modify.
- this property prevents the wrong people modifying the contents of the database.
- <mark style="background: #FF5582A6;">Integrity constraints</mark> which restrict what the content might be can also indirectly assist to achieve integrity security property, by preventing some modifications.
##### Availability Properties
User should be able to see and modify things which they are allowed to see and modify.
## 2. Policy and Mechanism
##### Definition
- A <mark style="background: #FF5582A6;">security policy</mark> indicates who should be allowed to do which actions, and who should not be allowed *(What need to be considered)*
- A security mechanism is how the system controls who is allowed to do which actions
	- Controlled by DBA 
- We expect that the security mechanism will **enforce** the security policy in place
##### Data Minimalism
*Only store the data that is necessary.*\
- The best protection against unauthorized access to data in your database is to consider very carefully what you store in the first place
- A database should only store information that is <mark style="background: #FF5582A6;">absolutely necessary</mark> for the operation of your application

##### Data Privacy
- Some information is specifically protected and requires specific standards and auditing procedures(审计程序)

## 3. Database Access Control

##### Access control (for authorization)
- A mechanism provided by dbms for controlling which users perform various operations on various parts of the database.
- It is called 'discretionary'(自主的) access control, because the decision about what is permitted or not, is set by appropriate people as the system operates, and can be adjusted as the situation changes.
- In SQL, based on the concept of access rights or privileges for tables, and commands for giving users privilege ( and revoking privileges).

##### GRANT command
```postgresql
GRANT privilegelist ON tablelist TO userlist [WITH GRANT OPTION]
```
- The following are among the <mark style="background: #FF5582A6;">privileges</mark> which can be mentioned in <mark style="background: #FF5582A6;">privilege list.</mark>
	- `SELECT`: Allows to read all columns of any of tables in *tablelist* 
	- `INSERT`: Allows to insert tuples in any of the listed tables
	- `DELETE`: Allows to delete tuples from any of the listed tables
	- `UPDATE`: allows to modify the values in the any tuples of the tabels
	- `REFERENCE`: Can define foreign keys
- If someone with the necessary permissions executes the `GRANT` command, the specified privileges will be granted to users, allowing them to access certain tables.
	- Privileges can also be granted to access a view
	- Privileges can also be granted to roles
	- Privileges may given with `WITH GRANT OPTION`or just the privilege itself can be given
- If a user has a privilege with the `GRANT OPTION`, they can themselves pass privilege on to other users by executing a `GRANT` command
	- The owner has `GRANT OPTION` privileges
	- Anyone who gets privileges form a command `WITH GRANT OPTION` has the capacity to pass those privileges on to others(including right to give them `WITH GRANT OPTION`)
- Only owner of a table (or superuser) can execute `ALTER` and `DROP`

##### Column-specific access control
- It is common for security policy to want different access rights on different columns in a single table
- Most dbms support variation of command: `GRANT privilege(column) ON table`
	- Eg `GRANT SELECT(sid,name,phone) ON Studnet TO Fre`
	- Eg `GRANT UPDATE(phone) ON Student TO Jane`
		- `GRANT INSERT` on specific columns, means that in the newly added row, any other columns will get the <mark style="background: #FF5582A6;">default</mark> value for that column

##### Revoke of Privileges
```PostgreSQl
REVOKE <privilege_list>
ON table
FROM <user_list>
```

- When a privilege if revoked from X, it is also revoked from all users who got it <mark style="background: #FF5582A6;">solely</mark> from X.
- But if a user has this privilege via several routes, it is still there until all granters have revoked it

##### Authorization Mode REFERENCES
- Foreign key constraint could be exploited to
	- Prevent some kind of modification: eg can prevent deletion of rows in some other table
	- reveal information(泄露信息) : successful insertion into `DontDismissMe` reveals that a row with particular value exists in `Student`
	- Example: `INSERT INTO DonDismissMe VALUES (111111)`
- Existence of References privilege allows to limit these power to appropriate users
	- Most users will not have `REFERENCE` privilege and so cannot use theses tricks

##### Role-based Authorization
- In SQL, privileges are assigned to roles
	- Roles can then be granted to users and to other roles.
	- Reflects how real organizations work
	- Much more flexible and less error-prone(容易出错的), especially on large schemas
	- <mark style="background: #FF5582A6;">use role-based authorization whenever possible</mark>
```PostgreSQL
CREATE ROLE manager
GRANT select,insert ON student TO manager
GRANT manager TO shari
GRANT manager TO keiko
REVOKE manager FROM shari
```
##### Schema privilege in PostgreSQL
- As well as granting access on a table etc, you need to also grant **USAGE** access to the schema that contains the table eg `GRANT USAGE ON unidb to Joe`
- When opening the connection, the user needs to be sure to connect to the database in which the table exists

## 4. User Identity And authentication
##### User Identity(authentication)
- <mark style="background: #FF5582A6;">Every</mark> request to dbms comes from some identified user
	- They connected to the dbms
	- Identity was determined then
	- Every command they issue comes on a connection and carries the connection id
##### DBMS accounts
- In many systems, the dbms has its own set of user identity/passwords
	- DBMS passwords need to be carefully protected
		- Consider where passwords are stored, how they are handed out to users, etc
- Some dbms may use Single Sign On(SSO), to avoid separate identity management
##### Content-based access control
- Policy often calls for access to be limited to certain rows only, in a table
- The decision about whether a row should be accessed by someone, may depend on the contents of the some fields in the row.
- SQL DBMS allows one to achieve this by defining a VIEW with only the relevant data, and then **GRANT** access to that VIEW
## 5. Views
##### Relational Views
- A view is a virtual relation, but we store a *definition*, rather than set of tuples
- Syntax
```PostgreSQ
CREATE VIEW NAME AS <query expression>
```
##### View and Security
- User not granted access to base tables. Instead they are granted access to the view of the database appropriate to their needs.
- Views can be used to present necessary information, while hiding details in underlying relations
- Creator of view has a privilege on the view if he has the privilege on all underling tables
	- Granting privilege on a view does not imply changing any privileges
	- If creator of base tables revokes `SELECT` right from view creator, view is no longer useful
##### Updating Views
can they be updated? Sometimes, yes

Example VIEW:
```PostgreSQL
CREATE VIEW CsReg (StudId, CrsCode, Semester) AS
SELECT T.StudId, T.CrsCode, T.Semester
FROM Transcript T
WHERE T.CrsCode LIKE 'CS%' AND T.Semester = 'S2024'
```
- Issue 1
```PostgreSQL
INSERT INTO CsReg (StudId, CrsCode, Semester) 
VALUES (1111, ‘CSE305’, ‘S2024’)
```
The attributes of underlying table that have not been projected out will be <mark style="background: #FF5582A6;">Nulls</mark>
or <mark style="background: #FF5582A6;">default</mark>

- Issue 2
```PostgreSQL
INSERT INTO CsReg (StudId, CrsCode, Semester) 
VALUES (1111, ‘ECO105’, ‘S2020’)
```
When inserting data into a view, using `WITH CHECK OPTION` ensures that the new records meet the view's defining conditions; otherwise, the insertion is rejected.
- Issue 3
```PostgreSQL
CREATE VIEW ProfDept (PrName, DeName) AS
SELECT P.Name, D.Name
FROM Professor P, Department D
WHERE P.DeptId = D.DeptId;
```
when a view is based on multiple tables, the update operation on the view may not uniquely determine how to modify the base tables, which makes updating the view complex or infeasible. In such cases, modifying the view may not be mapped to a unique update operation on the base tables.


## 6. Integrity and Constraints
##### Integrity Constraint
- Condition that must be true for every instance of a database
	- A *legal* instance of a relation is one that satisfies all specified ICs
	- DBMS should never allow illegal instance
- IC are specified in the database schema
	- The database designer is responsible to ensure that the integrity constraints are not contradicting each other
- Ic are checked when the database is modified
- Possible reactions if an IC is violated
	- Undoing of the modification
	- Execution of 'maintenance' operations to make db legal again
##### Domain Constraints
- Fields must be of right data domain
	- `DEFAULT`
	- `NOT NULL`
##### User-Defined Domains
- PostgreSQL approach: New domain can be created from existing data domains
```PostgreSQL
CREATE DOMAIN <domain-name> <sql-data-type>
```
- Example:
```PostgreSQL
create domain Dollars numeric(12,2)
create domain Pounds numeric(12,2)
```
- Domains can be further restricted with **check** clause
```PostgreSQL
create domain Grade char check(value in (‘F’,’P’,’C’,’D’,’H’))
```

##### PK constraints
...

##### Foreign Keys & Referential Integrity
...
##### ALTER TABLE statement
IC can be added, modified(only domain constraints), 
##### Assertion
tmd,这东西不被大部分DBMS支持,这玩意考了我吃
##### Transaction
- A sequence of database actions, that collectively accomplish one real-world change.
- all-or-nothing impact
- See Week 11
##### Deferring Constraint Checking
- Any dbms know constraint domain, key, foreign-key ,check /assertion may be declared:
	- NOT DEFERRABLE
		- The default. It means that every time a db modification occurs, the constraint is  checked immediately afterwards
	- DEFERRABLE 
		- Will wait until a transaction (with several operations) is complete before checking the constraint.




---
# Week 8
## 1. Database-backed applications
### Data-intensive System
##### Definition
3 types of functionality (often placed in separate layers of code):

![w8_1](Notes/2024%20sem2%20Nice/ISYS2120/Notes/Graphs/w8_1.png)
The system architecture determines whether these 3 components reside on a single computing system or whether they are distributed across several tiers.

##### Presentation layer
- Web browser is used as GUI for end-users
- App for mobile devices

##### SQL in application Code
- SQL commands can be called from within a host language (python, java) program
- Two main integration approaches
	- Statement-level interface (SLI)
		- Embed(嵌入) SQL in the host language
		- Application program is a mixture of host language statements and SQL statements and directives
		- A special compiler must deal with both aspects
	- Call-level interface (CLI)
		- Create special API to call SQL commands
		- SQL statements are passed as arguments to host language procedures/APIs
		- Standard programming language compiler, and program is combined with a library that supports the API
##### Call -level interfaces and Database APIs
- Program can invoke methods/procedures in a library with database calls(APIs)
	- Pass SQL strings from language, present result sets in language-friendly way
	- Supposedly DBMS-neutral
		- a "driver" executes the calls and translates them into DBMS-specific code
		- database can be across a network
- Several Variants
	- SQL/CLI
	- ODBC
	- JDBC (for java)
	- PDO
##### privileges of codes
- Many databases are accessed indirectly
- The program can do lots of checking of whether access is appropriate, before sending SQL to dbms
- The program may run with its own appropriate level of privilege, rather than from the end-user who is the source of request. 程序代表用户进行数据库操作，使用的是程序或开发者所赋予的权限，而不是用户的权限。
- Often , the program has quite a lot of privilege, but this is risky if there are mistakes in the code, or if an attacker can obtain

