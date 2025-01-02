- **题目 1：** 写一条 SQL 查询，输出所有被阻止的用户的用户名（即被其他用户 block 的用户），并且这些用户创建账号的时间是在 2024 年。
    
    **涉及表：** `Users`，`Blocks`
```SQL
SELECT DISTINCT u.username
FROM Blocks b
LEFT Join Users u ON b.blocked_id = u.user_id
WHERE u.created_at BETWEEN '2024-01-01' AND '2024-12-31'
```
    
- **题目 2：** 写一条 SQL 查询，输出每个广告的点击次数（`click`），并显示广告的内容。查询结果按点击次数降序排列，若点击次数相同则按广告内容的字母顺序升序排列。
    
    **涉及表：** `Advertisements`，`AdInteractions`
    
```SQL
SELECT COUNT(ai.ad_id), ad.content
FROM Advertisements ad 
LEFT JOIN AdInteractions ai ON ad.ad_id = ai.ad_id
WHERE ai.interaction_type = 'click'
GROUP by ad.ad_id
ORDER BY COUNT(ai.ad_id) DESC, ad.content
```

- **题目 3：** 写一条 SQL 查询，输出所有用户发送的消息数量，以及每个用户的用户名。查询结果可以按任意顺序显示。
    
    **涉及表：** `Users`，`Messages`
```SQL
SELECT u.username,COUNT(m.sender_id)
FROM Users u
LEFT JOIN Messages m ON u.user_id = m.sender_id
GROUP BY u.user_id,u.username
```
    
- **题目 4：** 写一条 SQL 查询，输出参加了所有事件的用户的用户名。换句话说，查询所有在系统中注册的事件（`Events`）中每一场都参加的用户。
    
    **涉及表：** `Users`，`Events`，`EventRSVPs`
```SQL
SELECT t1 AS name
FROM (
        SELECT u.user_id,u.username AS name,COUNT(DISTINCT er.user_id) AS c
        FROM Users u 
        LEFT JOIN EventRSVPs er ON u.user_id = er.user_id
        GROUP BY u.user_id,u.username
    )  AS t1

WHERE t1.c = 
(
    SELECT COUNT(DISTINCT rsvp_id)
    FROM EventRSVPs
)
```