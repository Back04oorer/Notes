## User
- <mark style="background: #FFC0CB;">user_id</mark>
- username unique
- phone_number
- full_name
- email
- passwd
- role
## Scroll
- <mark style="background: #FFC0CB;">scroll_id</mark>
- name <mark style="background: #FFFF00;">Unique</mark>
- user_id
- blob
- date
## Access
- <mark style="background: #FFC0CB;">access_id</mark>
- scroll_id 
- user_id
- date
- access_type

```SQL
SELECT s.scroll_id, COUNT(access_type)
FROM Scroll s 
LEFT JOIN Access ON s.scroll_id = a.sroll_id
GROUP BY s.scroll_id
WHERE a.access_type = 'D'
```

```SQL
INSERT INTO Scrolls (scroll_id, name, user_id, blobs, date) VALUES
('s001', 'Scroll of Wisdom', 'u001', hex('This is a sample scroll data.'), '2023-01-15'),
('s002', 'Scroll of Secrets', 'u002', hex('This is another scroll content.'), '2023-02-20'),
('s003', 'Scroll of Power', 'u003', hex('Scroll of Power contains ancient wisdom.'), '2023-03-10'),
('s004', 'Ancient Scroll', 'u004', hex('Ancient scroll with mysterious text.'), '2023-04-05'),
('s005', 'Scroll of Knowledge', 'u005', hex('The scroll of knowledge is vast and deep.'), '2023-05-25'),
('s006', 'Mystic Scroll', 'u002', hex('Mystic scroll holds secret power.'), '2023-06-15'),
('s007', 'Scroll of Destiny', 'u001', hex('The scroll of destiny reveals your future.'), '2023-07-30');
```
## Function

### User

#### Int `IfUserExists`
#### `GetPassWd`
return hashed passwd if userid exists
#### `UpdateUser`
update attributes of a user
#### `AddUser`

#### `DeleteUser`

#### `GetSingleUser`

#### `ViewAllUser`
except passwd
### Scroll
#### `AddScroll`

#### `DeleteScroll`

#### `SearchScroll`
```SQL
SELECT *
FROM your_table
WHERE 
    (condition1 IS NULL OR column1 = condition1) AND
    (condition2 IS NULL OR column2 = condition2) AND
    (condition3 IS NULL OR column3 = condition3);
```

#### `UpdateScroll`
- name
- blob

### Summary




```java
/**  
 * Initialize DB with a schema for the project ** @return an integer value to check if the program runs successfully.  
 *         0 for success, 1 for fail */public Integer DBInitial() {  
    Connection conn = null;  
    PreparedStatement stmt = null;  
    int return_code = 1;  
  
    try {  
        conn = DBConnect(dbString);  
        if (conn == null) {  
            throw new Exception("Connection cannot be null");  
        }  
  
        // Create Users Table  
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +  
                "user_id VARCHAR(64) PRIMARY KEY, " +  
                "username  VARCHAR(64) UNIQUE NOT NULL, " +  
                "phone_number VARCHAR(32) NOT NULL, " +  
                "full_name VARCHAR(64) NOT NULL, " +  
                "email VARCHAR(64) NOT NULL, " +  
                "passwd VARCHAR(256) NOT NULL, " +  
                "role VARCHAR(16) NOT NULL" +  
                ")";  
  
        stmt = conn.prepareStatement(sql);  
        stmt.executeUpdate();  
        System.out.println("User table created");  
  
        // Create Scrolls table  
        sql = "CREATE TABLE IF NOT EXISTS Scrolls(" +  
                "scroll_id VARCHAR(64) PRIMARY KEY," +  
                "name VARCHAR(64) UNIQUE NOT NULL," +  
                "user_id VARCHAR(64) REFERENCES Users(user_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL," +  
                "blobs BLOB NOT NULL," +  
                "date DATE NOT NULL)";  
        stmt = conn.prepareStatement(sql);  
        stmt.executeUpdate();  
  
        System.out.println("Scrolls table created");  
  
        // Create Access table  
        sql = "CREATE TABLE IF NOT EXISTS Access(" +  
                "access_id VARCHAR(64) PRIMARY KEY," +  
                "scroll_id VARCHAR(64) REFERENCES Scrolls(scroll_id)  ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,"  
                +  
                "user_id VARCHAR(64) REFERENCES Users(user_id)  ON DELETE CASCADE ON UPDATE CASCADE NOT NULL," +  
                "date DATE NOT NULL," +  
                "access_type CHAR(1) CHECK(access_type in ('U','D')))";  
        stmt = conn.prepareStatement(sql);  
        stmt.executeUpdate();  
        System.out.println("Access table created");  
        conn.commit();  
        return_code = 0;  
  
    } catch (Exception e) {  
  
        e.printStackTrace();  
  
    } finally {  
        if (stmt != null) {  
            try {  
                stmt.close();  
  
            } catch (Exception e) {  
                e.printStackTrace();  
                return_code = 1;  
            }  
        }  
        if (conn != null) {  
            try {  
                conn.close();  
  
            } catch (Exception e) {  
                e.printStackTrace();  
                return_code = 1;  
            }  
        }  
  
    }  
    return return_code;  
}
```