- **题目 1：** 写一条 SQL 查询，输出所有用户的用户名以及他们分享过的帖子数量。查询结果按帖子数量降序排列，若帖子数量相同则按用户名升序排列。
    
    **涉及表：** `Users`，`Shares`
    
- **题目 2：** 写一条 SQL 查询，输出所有在 2024 年发生的事件名称和组织这些事件的用户的用户名，查询结果按事件日期升序排列。
    
    **涉及表：** `Events`，`Users`
    
- **题目 3：** 写一条 SQL 查询，输出用户名和他们的好友数量（即被接受的好友请求数量）。查询结果按好友数量降序排列，若好友数量相同则按用户名升序排列。
    
    **涉及表：** `Users`，`Friendships`
    
- **题目 4：** 对于所有阻止了比他们接受的好友还要多的用户，列出以下内容（按以下列顺序）：
	- 该用户的用户名
	- 该用户阻止的用户数量
	- 该用户接受的好友数量

结果按用户阻止的用户数量降序排列，如果阻止的用户数量相同，则按接受的好友数量降序排列。

```SQL
SELECT t1.username,t2.count,t1.count-t2.count
FROM 
    (
        SELECT u.user_id AS user_id,u.username AS username,COUNT(friend_id) AS count
        FROM Users u 
        LEFT JOIN Friendships f ON u.user_id = f.user_id
        GROUP BY u.user_id
    ) AS t1
    LEFT JOIN 
    (
        SELECT u.user_id AS user_id,u.username AS username,COUNT(b.blocker_id) AS count
        FROM Users u 
        LEFT JOIN Blocks b ON u.user_id = b.blocker_id
        GROUP BY u.user_id
    ) AS t2 ON t1.user_id = t2.user_id
WHERE t2.count > (t1.count-t2.count)
ORDER BY t2.count DESC,(t1.count-t2.count) DESC
```