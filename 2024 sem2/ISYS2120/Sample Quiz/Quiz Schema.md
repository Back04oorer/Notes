```SQL
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    profile_picture VARCHAR(255),
    bio TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Friendships Table
f

-- 3. Follows Table
CREATE TABLE Follows (
    follower_id INT,
    followed_id INT,
    PRIMARY KEY (follower_id, followed_id),
    FOREIGN KEY (follower_id) REFERENCES Users(user_id),
    FOREIGN KEY (followed_id) REFERENCES Users(user_id)
);

-- 4. Blocks Table
CREATE TABLE Blocks (
    blocker_id INT,
    blocked_id INT,
    PRIMARY KEY (blocker_id, blocked_id),
    FOREIGN KEY (blocker_id) REFERENCES Users(user_id),
    FOREIGN KEY (blocked_id) REFERENCES Users(user_id)
);

-- 5. Posts Table
CREATE TABLE Posts (
    post_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    content TEXT,
    media_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 6. Comments Table
CREATE TABLE Comments (
    comment_id SERIAL PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    content TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 7. Likes Table
CREATE TABLE Likes (
    like_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    post_id INT,
    comment_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (comment_id) REFERENCES Comments(comment_id)
);


-- 8. Shares Table
CREATE TABLE Shares (
    share_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (post_id) REFERENCES Posts(post_id)
);

-- 9. Groups Table
CREATE TABLE Groups (
    group_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    privacy VARCHAR(10) NOT NULL, -- ENUM('public', 'private') NOT NULL,
    group_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 10. GroupMemberships Table
CREATE TABLE GroupMemberships (
    membership_id SERIAL PRIMARY KEY,
    group_id INT NOT NULL,
    user_id INT NOT NULL,
    role VARCHAR(10) NOT NULL, -- ENUM('member', 'admin', 'moderator') NOT NULL,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (group_id) REFERENCES Groups(group_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 11. Events Table
CREATE TABLE Events (
    event_id SERIAL PRIMARY KEY,
    group_id INT,
    organizer_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    event_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (group_id) REFERENCES Groups(group_id),
    FOREIGN KEY (organizer_id) REFERENCES Users(user_id)
);

-- 12. EventRSVPs Table
CREATE TABLE EventRSVPs (
    rsvp_id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    user_id INT NOT NULL,
    status VARCHAR(20) NOT NULL, -- ENUM('attending', 'maybe', 'not attending') NOT NULL,
    responded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES Events(event_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 13. Messages Table
CREATE TABLE Messages (
    message_id SERIAL PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    content TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES Users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES Users(user_id)
);

-- 14. Advertisements Table
CREATE TABLE Advertisements (
    ad_id SERIAL PRIMARY KEY,
    advertiser_id INT NOT NULL,
    content TEXT,
    target_criteria JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (advertiser_id) REFERENCES Users(user_id)
);

-- 15. AdInteractions Table
CREATE TABLE AdInteractions (
    interaction_id SERIAL PRIMARY KEY,
    ad_id INT NOT NULL,
    user_id INT NOT NULL,
    interaction_type VARCHAR(10) NOT NULL, -- ENUM('view', 'click') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ad_id) REFERENCES Advertisements(ad_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 16. Notifications Table
CREATE TABLE Notifications (
    notification_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    content TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 17. NotificationPreferences Table
CREATE TABLE NotificationPreferences (
    preference_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    notification_type VARCHAR(50) NOT NULL,
    is_enabled BOOLEAN DEFAULT TRUE,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 18. Tags Table
CREATE TABLE Tags (
    tag_id SERIAL PRIMARY KEY,
    content_id INT NOT NULL,
    tagged_item_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    -- Assuming Tags can refer to multiple types of content, constraints or additional logic would be needed to enforce references.
);

-- 19. Mentions Table
CREATE TABLE Mentions (
    mention_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    content_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
    -- Assuming Mentions can refer to multiple types of content, constraints or additional logic would be needed to enforce references.
);

```