
##### q1
Write a SQL query whose output gives a list showing the username of all users who were created in 2024 that belong to the group with name "ISYS2120 Student (2024)"

```SQL
SELECT DISTINCT u.username
FROM Users u 
LEFT JOIN GroupMemberships gm ON u.user_id=gm.user_id
LEFT JOIN Groups g ON g.group_id = gm.group_id 
WHERE u.created_at BETWEEN '2024-01-01' AND '2024-12-31'
    AND
    g.name = 'ISYS2120 Student (2024)'
```
##### q2
Write a SQL query whose output will give the number of followers of each user, together with the user's username, in any order.

```SQL
SELECT u.username, COUNT(f.followed_id)
FROM Users u 
LEFT JOIN Follows f ON u.user_id = f.followed_id
GROUP BY u.user_id, u.username
```
##### q3
Write a SQL query that outputs the names of groups, each name together with the number of members in that group, for those groups where the number of members is at least 10. Order in decreasing order of number of members, breaking ties alphabetically by name of the group.

```SQL
SELECT g.name,COUNT(gm.user_id)
FROM Groups g
LEFT JOIN GroupMemberships gm USING(group_id)
GROUP BY g.group_id,g.name
HAVING COUNT(gm.user_id) >= 10
ORDER BY COUNT(gm.user_id) DESC, g.name
```
##### q4
Write a SQL query whose output will give the set of username of all pairs of users who are mutual followers. List each pair only once, with the user which comes first alphabetically in the first column. (i.e. if Alice and Bob are mutual followers, the output should contain "Alice, Bob" and not "Bob, Alice")
Order the output by the first username of the pair in alphabetical order, breaking ties by the second username of the pair in alphabetical order

Alice and Bob are mutual followers, means that Alice follows Bob and also Bob follows Alice

```SQL
SELECT u.username,u2.username
FROM Users u

LEFT JOIN Follows f1 ON u.user_id = f1.followed_id
LEFT JOIN Follows f2 ON f1.follower_id = f2.followed_id
LEFT JOIN Users u2 ON f2.followed_id = u2.user_id
WHERE u.user_id = f2.follower_id
    AND 
    u.username < u2.username
ORDER BY u.username,u2.username
```


##### q4 harder
```SQL
SELECT t2.CommentID,t2.content,t2.COUNT2
FROM
    (
    SELECT p.post_id AS postID1,COUNT(p.user_id) AS COUNT1
    FROM Posts p 
    LEFT JOIN Likes l ON l.post_id = p.post_id
    GROUP BY p.post_id
    ) AS t1
    LEFT JOIN 
    (
        SELECT p.post_id AS postID2, c.comment_id AS CommentID,c.content AS content,COUNT(l.like_id) AS COUNT2
        FROM Posts p 
        LEFT JOIN Comments c ON p.post_id = c.post_id
        LEFT JOIN Likes l ON l.comment_id = c.comment_id
        GROUP BY p.post_id,c.comment_id
    ) AS t2
    ON t1.postID1 = t2.postID2
    WHERE COUNT1 < COUNT2

```