### Structure&Instruction

In this Sprint, the tests primarily used JUnit to test some of the functionalities of `SQLiteJDBC.java`. The main goal was to test whether `SQLiteJDBC.java` can provide a stable connection to the database and whether it can offer effective interfaces for implementing specific functionalities. Ideally, developers implementing specific features should be able to perform necessary operations without directly connecting to the database.

In this project, we use JaCoCo to measure test coverage. The specific workflow is as follows:
- Write unit tests.
- Run `gradle test` to execute the tests.
- Run `gradle jacocoTestReport` to generate the test coverage report.
- Review the generated report to ensure that most of the code logic is covered. If certain parts are not covered, write additional tests to improve the coverage.

Since the logic concerning the `User` functionality has not been fully defined, only partial content was tested in this sprint. This section explains the rationale behind the design of each JUnit test, the types of tests (Normal, Edge cases), the inputs, and the expected outputs.

#### JUnit Test Cases

##### testDBConnect1

- **TestType**: Normal
- **Description**: Test to check if a valid database connection can be established and if the connection settings are correctly applied.
- **Input**: Database file `"src/test/resources/databases/db_1.db"`
- **Expected Output**:
    - The connection object should not be null.
    - Auto-commit should be disabled.
- **Result**: Pass

##### testDBConnect2

- **TestType**: Edge
- **Description**: Test to check if a non-existent database file is handled correctly.
- **Input**: Database file `"src/test/resources/databases/notexist.db"`
- **Expected Output**: Connection object should be null.
- **Result**: Pass

##### testAddUser1

- **TestType**: Normal
- **Description**: Test to verify that a user can be successfully added to the database, and that the user information is correctly stored and retrievable.
- **Input**:
    - Database file `"src/test/resources/databases/db_1.db"`
    - User information: `user_id = "testAddUser1"`, `username = "Junk Dog"`, `phone_number = "18258803672"`, `full_name = "Mingyuan Ba"`, `email = "2450707828@qq.com"`, `passwd = "111111"`, `role = "user"`
- **Expected Output**: User should be successfully inserted into the database and retrieved with correct details.
- **Result**: Pass

##### testAddUser2

- **TestType**: Edge (Primary Key Constraint)
- **Description**: Test to check if the database correctly enforces the Primary Key (PK) constraint when trying to insert a user with an existing user_id.
- **Input**: User with `user_id = "u002"` (existing ID).
- **Expected Output**: Status code `2` indicating a PK constraint violation.
- **Result**: Pass

##### testAddUser3

- **TestType**: Edge (Unique Constraint)
- **Description**: Test to check if the database correctly enforces a unique constraint when adding a user with the same `user_id` but different username.
- **Input**: User with `user_id = "u002"`, `username = "Alex"` (existing ID with a different username).
- **Expected Output**: Status code `2` indicating a unique constraint violation.
- **Result**: Pass

##### testAddUser4

- **TestType**: Edge (Bad Connection)
- **Description**: Test to check if the database handles a bad or invalid connection string properly.
- **Input**: Invalid database file `"src/test/resources/databases/invalid_db_string.db"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass

##### testIfUserExists1

- **TestType**: Normal
- **Description**: Test to verify that the database correctly checks for the existence of an existing user in the database.
- **Input**: `user_id = "u001"`
- **Expected Output**: Status code `0` indicating the user exists.
- **Result**: Pass

##### testIfUserExists2

- **TestType**: Edge
- **Description**: Test to verify that the database correctly handles a query for a non-existent user.
- **Input**: `user_id = "nobody"`
- **Expected Output**: Status code `2` indicating the user does not exist.
- **Result**: Pass

##### testIfUserExists3

- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the database handles the scenario where a connection to a non-existent database is attempted when querying for a user.
- **Input**: Database file `"src/test/resources/databases/nothingHere.db"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass

##### testIfUsernameExist1

- **TestType**: Normal
- **Description**: Test to verify if a valid username exists in the database.
- **Input**: Username `"alex_jones"`
- **Expected Output**: Status code `0` indicating the username exists.
- **Result**: Pass

##### testIfUsernameExist2

- **TestType**: Edge
- **Description**: Test to verify how the system handles checking for a non-existent username.
- **Input**: Username `"ImNotHere"`
- **Expected Output**: Status code `2` indicating the username does not exist.
- **Result**: Pass

##### testIfUsernameExist3

- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles checking a username when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/NotHere.db"`, Username `"alex_jones"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass

##### testGetUserId1

- **TestType**: Normal
- **Description**: Test to verify that the correct user ID is returned when querying by a valid username.
- **Input**: Username `"emily_white"`
- **Expected Output**: User ID `"u004"`
- **Result**: Pass

##### testGetUserId2

- **TestType**: Edge
- **Description**: Test to check how the system handles querying a user ID for a non-existent username.
- **Input**: Username `"OMG"`
- **Expected Output**: Result should be null indicating no user found.
- **Result**: Pass

##### testGetUserId3

- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles querying a user ID when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/TOOBAD.db"`, Username `"emily_white"`
- **Expected Output**: Result should be null indicating a failure to retrieve data.
- **Result**: Pass

##### testGetPassWd1

- **TestType**: Normal
- **Description**: Test to verify the correct password is returned when querying by a valid user ID.
- **Input**: User ID `"u001"`
- **Expected Output**: Password `"password123"`
- **Result**: Pass

##### testGetPassWd2

- **TestType**: Edge
- **Description**: Test to check how the system handles querying the password for a non-existent user ID.
- **Input**: User ID `"FaNna"`
- **Expected Output**: Result should be null indicating no password found.
- **Result**: Pass

##### testGetPassWd3

- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles querying a password when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/SYF.db"`, User ID `"u001"`
- **Expected Output**: Result should be null indicating a failure to retrieve data.
- **Result**: Pass

##### testIfAccessExist1

- **TestType**: Normal
- **Description**: Test to verify if a valid access record exists in the database.
- **Input**: Access ID `"a001"`
- **Expected Output**: Status code `0` indicating the access record exists.
- **Result**: Pass

##### testIfAccessExist2

- **TestType**: Edge
- **Description**: Test to verify how the system handles checking for a non-existent access record.
- **Input**: Access ID `"a010"`
- **Expected Output**: Status code `2` indicating the access record does not exist.
- **Result**: Pass

##### testIfAccessExist3

- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles checking an access record when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/so_sad.db"`, Access ID `"a010"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass

##### testUpdateScroll1

- **TestType**: Normal
- **Description**: Test to verify that a scroll record can be updated successfully with a valid input.
- **Input**:
    - Scroll ID `"001"`
    - Scroll name `"CaiJiaWu"`
    - Scroll description `"Scroll of Wisdom"`
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-01"`
- **Expected Output**: Status code `0` indicating successful update.
- **Result**: Pass

##### testUpdateScroll2

- **TestType**: Edge
- **Description**: Test to verify how the system handles updating a scroll record with invalid data.
- **Input**:
    - Scroll ID `"002"`
    - Scroll name `"Monkeyyyyyyy"`
    - Scroll description `"NAHHH"`
    - Blob data representing a `.mp4` file.
    - Scroll date `"2024-06-03"`
- **Expected Output**: Status code `2` indicating an error with the update.
- **Result**: Pass

##### testUpdateScroll3

- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles updating a scroll record when the database connection is invalid.
- **Input**:
    - Invalid database `"src/test/resources/databases/badbad.db"`
    - Scroll ID `"003"`
    - Scroll name `"Monkeyyyyyyy"`
    - Scroll description `"Scroll of Power"`
    - Blob data representing a `.mp4` file.
    - Scroll date `"2024-06-03"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass

##### testUpdateScroll4

- **TestType**: Edge
- **Description**: Test to verify how the system handles updating a scroll record with specific data.
- **Input**:
    - Scroll ID `"004"`
    - Scroll name `"Mystic Scroll"`
    - Scroll description `"Ancient Scroll"`
    - Blob data representing a `.mp4` file.
    - Scroll date `"2024-06-03"`
- **Expected Output**: Status code `2` indicating an error with the update.
- **Result**: Pass
##### testUpdateAddScroll1

- **TestType**: Normal
- **Description**: Test to verify that a scroll record can be added successfully with valid inputs.
- **Input**:
    - Scroll ID `"010"`
    - Scroll name `"Scroll of Rubin"`
    - User ID `"u004"`
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `0` indicating successful addition.
- **Result**: Pass

##### testUpdateAddScroll2

- **TestType**: Edge
- **Description**: Test to verify how the system handles adding a scroll record with an invalid user ID.
- **Input**:
    - Scroll ID `"010"`
    - Scroll name `"Scroll of Rubin"`
    - User ID `"bibabu"` (invalid)
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `2` indicating a failure due to invalid user ID.
- **Result**: Pass

##### testUpdateAddScroll3

- **TestType**: Edge
- **Description**: Test to verify how the system handles adding a scroll record with an invalid scroll ID.
- **Input**:
    - Scroll ID `"011"`
    - Scroll name `"Scroll of Power"`
    - User ID `"u011"` (invalid)
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `2` indicating failure due to invalid scroll ID.
- **Result**: Pass

##### testUpdateAddScroll4

- **TestType**: Edge
- **Description**: Test to verify how the system handles adding a scroll with a duplicate scroll ID.
- **Input**:
    - Scroll ID `"001"` (duplicate)
    - Scroll name `"Scroll of Death"`
    - User ID `"u011"`
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `2` indicating failure due to a duplicate scroll ID.
- **Result**: Pass

##### testUpdateAddScroll5

- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles adding a scroll record when the database connection is invalid.
- **Input**:
    - Invalid database `"src/test/resources/databases/badbad.db"`
    - Scroll ID `"001"`
    - Scroll name `"Scroll of Death"`
    - User ID `"u011"`
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `1` indicating failure to connect to the database.
- **Result**: Pass

##### testGetSingleUser1

- **TestType**: Normal
- **Description**: Test to verify that a single user's information can be retrieved from the database.
- **Input**: User ID `"u002"`
- **Expected Output**: User object with correct details for `user_id = "u002"`, `username = "jane_smith"`, `phone_number = "0987654321"`, etc.
- **Result**: Pass

##### testGetSingleUser2

- **TestType**: Edge
- **Description**: Test to check how the system handles querying for a non-existent user.
- **Input**: User ID `"DoesNotExist"`
- **Expected Output**: Result should be null indicating no user found.
- **Result**: Pass

##### testGetSingleUser3

- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles querying a user when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/Bad.db"`, User ID `"DoesNotExist"`
- **Expected Output**: Result should be null indicating failure to retrieve data.
- **Result**: Pass

##### testInsertAccess1

- **TestType**: Normal
- **Description**: Test to verify that an access record can be inserted successfully with valid inputs.
- **Input**:
    - Access ID `"a006"`
    - Scroll ID `"006"`
    - User ID `"u002"`
    - Access date `"2024-06-10"`
    - Access type `"U"`
- **Expected Output**: Status code `0` indicating successful insertion.
- **Result**: Pass

##### testInsertAccess2

- **TestType**: Edge
- **Description**: Test to verify how the system handles inserting an access record with invalid access type.
- **Input**:
    - Access ID `"a006"`
    - Scroll ID `"006"`
    - User ID `"u002"`
    - Access date `"2024-06-10"`
    - Access type `"XX"` (invalid)
- **Expected Output**: Status code `1` indicating failure due to invalid access type.
- **Result**: Pass

##### testInsertAccess3

- **TestType**: Edge
- **Description**: Test to verify how the system handles inserting an access record with a duplicate access ID.
- **Input**:
    - Access ID `"a003"` (duplicate)
    - Scroll ID `"006"`
    - User ID `"u002"`
    - Access date `"2024-06-10"`
    - Access type `"U"`
- **Expected Output**: Status code `2` indicating failure due to duplicate access ID.
- **Result**: Pass

##### testInsertAccess4

- **TestType**: Edge
- **Description**: Disabled test to verify insertion with specific inputs.
- **Input**: Access ID `"a003"`, Scroll ID `"1"`, User ID `"u002"`, Access date `"2024-06-10"`, Access type `"U"`
- **Expected Output**: Status code `1` indicating an error with the insertion.
- **Result**: Fail
