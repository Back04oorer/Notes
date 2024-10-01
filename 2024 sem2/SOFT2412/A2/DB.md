## User
- <mark style="background: #FFC0CB;">user_id</mark>
- username
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





# Question