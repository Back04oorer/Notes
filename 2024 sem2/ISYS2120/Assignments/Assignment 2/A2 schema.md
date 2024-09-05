## Submission
date NOT NULL
time NOT NULL

## Student
studentkey PK
surname
given name
year-of-entry
major(s) MULTI VALUE
official email
address

## Staff
staffed PK
surname 
given name
title
department
official email
contact phone number


## Offering

staff coordinator
staffs 

## Assessment
offering
name
weight
kind
instruction document
allow multiple submissions -> how to calculate the overall score 


## Subject
name
credit-hours
major(s)
whether or not students are allowed to take it more than once


## Department





### 1. **Student（学生）**

- **Attributes（属性）：**
    - `studentkey` (PK) — 学生唯一标识符（例如JS2X57）
    - `surname` — 姓氏
    - `given_name` — 名字
    - `year_of_entry` — 入学年份
    - `major` — 主修专业
    - `email` — 官方电子邮件
    - `address` — 地址

### 2. **Staff（教职工）**

- **Attributes（属性）：**
    - `staff_id` (PK) — 教职工唯一标识符
    - `surname` — 姓氏
    - `given_name` — 名字
    - `title` — 职称（例如教授、讲师等）
    - `department_id` — 所属系 FK
    - `email` — 官方电子邮件
    - `phone_number` — 联系电话

### 3. **Department（系）**

- **Attributes（属性）：**
    - `department_id` (PK) — 系的唯一标识符
    - `name` — 系的名称

### 4. **Subject（科目）**

- **Attributes（属性）：**
    - `subject_id` (PK) — 科目的唯一标识符
    - `name` — 科目名称
    - `credit_hours` — 学分
    - `major` — 该科目所属的主修专业（如果有）
    - `can_be_repeated` — 是否允许重复修读

### 5. **Offering（开设）** Relationship

- **Attributes（属性）：**
    - `term` — 学期（例如2024年第一学期）
    - `year` — 开设年份
    - `subject_id` (FK) — 关联的科目
    - `coordinator` — 课程协调员（教职工的ID）
    - `department_id` FK -> department
### 6. **Assessment Task（评估任务）**

- **Attributes（属性）：**
    - `assessment_id` (PK) — 评估任务的唯一标识符
    - `name` — 任务名称（例如“作业1”）
    - `weight` — 任务在总成绩中的权重
    - `type` — 任务类型（例如Essay, Quiz, ProblemSet, FinalExam）
    - `instruction` — 任务说明文档
    - `latest_date` — 最迟提交日期
- **Subclasses（子类）：**
    - **Repeatable Task（可重复的任务）**
        - `policy_for_multiple_submissions` — 多次提交的评分策略
    - **Non-Repeatable Task（不可重复的任务）**
        - `exemption_request` — 免除申请文本
        - `exemption_request_date` — 免除申请日期
        - `exemption_approved` — 免除申请是否批准

### 7. **Submission（提交）**

- **Attributes（属性）：**
    - `submission_id` (PK) — 提交的唯一标识符
    - `date` — `NOT NULL`
    - `time` - `NOT NULL`
    - `mark` — 分数 
    - `scan` — ??????
    - `assessment_id` (FK) — 关联的评估任务
    - `studentkey` (FK) — 关联的学生
    - `staff_id` (FK) — 评分的教职工

### 8. **Major（专业）**（如果需要单独建模）

- **Attributes（属性）：**
    - `major_id` (PK) — 专业的唯一标识符
    - `name` — 专业名称
    - `department_id` (FK) — 关联的系

### 9. **Exemption Request（免除申请）**

- **Attributes（属性）：**
    - `request_id` (PK) — 申请的唯一标识符
    - `request_text` — 申请文本
    - `request_date` — 申请日期
    - `approved` — 是否批准
    - `assessment_id` (FK) — 关联的评估任务
    - `studentkey` (FK) — 关联的学生