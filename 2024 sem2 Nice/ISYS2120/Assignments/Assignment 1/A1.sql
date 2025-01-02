-- 创建 Customers 表
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,   -- 主键，不允许 NULL
    CustomerName VARCHAR(255) NOT NULL
);

-- 创建 Orders 表，CustomerID 列为外键，并允许 NULL 值
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,  -- 主键，不允许 NULL
    OrderDate DATE,
    CustomerID INT,  -- 外键，允许 NULL 值
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)  -- 定义外键约束
);


-- 向 Customers 表插入数据
INSERT INTO Customers (CustomerID, CustomerName)
VALUES (1, 'John Doe'),
       (2, 'Jane Smith');

-- 向 Orders 表插入数据

-- 订单关联到 CustomerID 1
INSERT INTO Orders (OrderID, OrderDate, CustomerID)
VALUES (101, '2024-08-17', 1);

-- 订单关联到 CustomerID 2
INSERT INTO Orders (OrderID, OrderDate, CustomerID)
VALUES (102, '2024-08-18', 2);

-- 插入一个没有关联客户的订单（CustomerID 为 NULL）
INSERT INTO Orders (OrderID, OrderDate, CustomerID)
VALUES (103, '2024-08-19', NULL);

