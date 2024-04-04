
# Friend list
## Table for friend list

## Friend request




## Real friend list
`jinja`


# Chat history
## Table for Room ids

| Field      | Type    | Description                      |
| ---------- | ------- | -------------------------------- |
| room id    | Integer | 消息的唯一标识符，主键。                     |
| username 1 | String  | 发送者的用户名，外键，引用`User`表的`username`。 |
| username 2 | String  | 接收者的用户名，外键，引用`User`表的`username`。 |

## Table for Messages

| Field           | Type    | Description                      |
| --------------- | ------- | -------------------------------- |
| id              | Integer | 消息的唯一标识符，主键。                     |
| sender_username | String  | 发送者的用户名，外键，引用`User`表的`username`。 |
| room id         | String  | 接收者的用户名，外键，引用`User`表的`username`。 |
| content         | String  | 消息的文本内容。                         |
