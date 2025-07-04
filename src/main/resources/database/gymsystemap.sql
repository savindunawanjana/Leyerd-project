
DROP DATABASE IF EXISTS gymsystemap;
CREATE DATABASE gymsystemap;
USE gymsystemap;

CREATE TABLE system_user
(
    user_id       VARCHAR(20) PRIMARY KEY,
    user_password VARCHAR(100) NOT NULL,
    user_role     VARCHAR(20),
    user_name     VARCHAR(50),
    phone_number  VARCHAR(15)
);

-- Table: coaches
CREATE TABLE coaches
(

    coach_id         VARCHAR(6) PRIMARY KEY,
    coach_name       VARCHAR(50) NOT NULL,
    coach_number     VARCHAR(15),
    system_user_Roll VARCHAR(20) NOT NULL,
    add_date DATE ,
    email            VARCHAR(100)

);

-- Table: worker
CREATE TABLE worker
(
    worker_id        VARCHAR(6) PRIMARY KEY,
    worker_name      VARCHAR(50) NOT NULL,
    worker_number    VARCHAR(15),
    system_user_Roll VARCHAR(20) NOT NULL,
    add_date DATE ,
    email            VARCHAR(100)

);


CREATE TABLE customer(
                         customer_id       VARCHAR(6) PRIMARY KEY,
                         customer_name     VARCHAR(50) NOT NULL,
                         customer_address  VARCHAR(100),
                         contact_number    VARCHAR(20),
                         customer_birthday DATE,
                         customer_weight   INT,
                         customer_email    VARCHAR(50),
                         register_fees     DECIMAL(10, 2)
);

CREATE TABLE additional_leave_coach
(
    leave_id       INT AUTO_INCREMENT PRIMARY KEY,
    coach_id       VARCHAR(6)  NOT NULL,
    system_user_id VARCHAR(20) NOT NULL,
    leave_date     DATE        NOT NULL,
    FOREIGN KEY (coach_id) REFERENCES coaches (coach_id) ON DELETE CASCADE,
    FOREIGN KEY (system_user_id) REFERENCES system_user (user_id) ON DELETE CASCADE
);


CREATE TABLE additional_leave_worker
(
    leave_id         INT AUTO_INCREMENT PRIMARY KEY,
    worker_id        VARCHAR(6)  NOT NULL,
    system_user_Roll VARCHAR(20) NOT NULL,
    leave_date       DATE        NOT NULL,
    FOREIGN KEY (worker_id) REFERENCES worker (worker_id) ON DELETE CASCADE
    -- FOREIGN KEY (system_user_id) REFERENCES system_user(user_id) ON DELETE CASCADE
);

-- Table: delete_coach
CREATE TABLE delete_coach
(
    delete_id        INT AUTO_INCREMENT PRIMARY KEY,
    coach_id         VARCHAR(6),
    coach_name       VARCHAR(50) NOT NULL,
    delete_date      DATE        NOT NULL,
    system_user_Roll VARCHAR(20),
    reason_to_delete TEXT,
    FOREIGN KEY (coach_id) REFERENCES coaches (coach_id) ON DELETE SET NULL
    -- FOREIGN KEY (system_user_id) REFERENCES system_user(user_id) ON DELETE SET NULL
);

-- Table: delete_worker
CREATE TABLE delete_worker
(
    delete_id        INT AUTO_INCREMENT PRIMARY KEY,
    worker_id        VARCHAR(6),
    worker_name      VARCHAR(50) NOT NULL,
    delete_date      DATE        NOT NULL,
    system_user_Roll VARCHAR(20),
    reason_to_delete TEXT,
    FOREIGN KEY (worker_id) REFERENCES worker (worker_id) ON DELETE SET NULL
    -- FOREIGN KEY (system_user_id) REFERENCES system_user(user_id) ON DELETE SET NULL
);

-- Table: edit_oto_generate_message
CREATE TABLE edit_oto_generate_message
(
    message_id     INT AUTO_INCREMENT PRIMARY KEY,
    system_user_id VARCHAR(20) NOT NULL,
    edit_date      DATE        NOT NULL,
    FOREIGN KEY (system_user_id) REFERENCES system_user (user_id) ON DELETE CASCADE
);

-- Table: monthly_report
CREATE TABLE monthly_report
(
    report_id   INT AUTO_INCREMENT PRIMARY KEY,
    report_date DATE NOT NULL
);

-- Table: coman_password
CREATE TABLE coman_password
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

-- Table: customer_payment
CREATE TABLE customer_payment
(
    payment_id            INT AUTO_INCREMENT PRIMARY KEY,
    customer_id           VARCHAR(6)     NOT NULL,
    expire_date           DATE           NOT NULL,
    valid_number_of_month INT,
    system_user_roll      VARCHAR(20),
    payment_date          DATE           NOT NULL,
    payment_amount        DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

-- Table: employee_payments
CREATE TABLE employee_payments
(
    payment_id       INT AUTO_INCREMENT PRIMARY KEY,
    system_user_roll VARCHAR(20) NOT NULL,
    employee_role    VARCHAR(20),
    employee_id      VARCHAR(10),
    payment_date     DATE        NOT NULL,
    salary           DECIMAL(10, 2)
    -- FOREIGN KEY (system_user_id) REFERENCES system_user(user_id) ON DELETE CASCADE
);

CREATE TABLE manage_exercises (
                                  id INT PRIMARY KEY,
                                  name VARCHAR(100) NOT NULL,
                                  category VARCHAR(50) NOT NULL,
                                  added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  system_user_id VARCHAR(20),
                                  FOREIGN KEY (system_user_id) REFERENCES system_user(user_id) ON DELETE CASCADE
);


-- Table: message_schedule_to_customer
CREATE TABLE message_schedule_to_customer
(
    schedule_id    INT AUTO_INCREMENT PRIMARY KEY,
    system_user_id VARCHAR(20) NOT NULL,
    customer_id    VARCHAR(6)  NOT NULL,
    send_date      DATE        NOT NULL,
    FOREIGN KEY (system_user_id) REFERENCES system_user (user_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

-- Table: system_user_additional_leave
CREATE TABLE system_user_additional_leave
(
    leave_id   INT AUTO_INCREMENT PRIMARY KEY,
    user_id    VARCHAR(20) NOT NULL,
    leave_date DATE        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES system_user (user_id) ON DELETE CASCADE
);

-- Table: system_user_delete
CREATE TABLE system_user_delete
(
    delete_id        INT AUTO_INCREMENT PRIMARY KEY,
    user_id          VARCHAR(20) NOT NULL,
    delete_date      DATE        NOT NULL,
    reason_to_delete VARCHAR(200),
    FOREIGN KEY (user_id) REFERENCES system_user (user_id) ON DELETE CASCADE
);

CREATE TABLE employees_table (
                                 id VARCHAR(20) PRIMARY KEY,
                                 name VARCHAR(100),
                                 number VARCHAR(15),
                                 role VARCHAR(50),
                                 email VARCHAR(100)
);

CREATE TABLE employee_attendance
(
    attendance_id   INT AUTO_INCREMENT PRIMARY KEY,
    employee_id     VARCHAR(20) NOT NULL,
    job_role        VARCHAR(50) NOT NULL,
    attendance_date DATE        NOT NULL,
    status          ENUM ('Present', 'Absent', 'Late', 'Half Day') DEFAULT 'Present',
    description     TEXT,
    UNIQUE (employee_id, attendance_date),
        FOREIGN KEY (employee_id) REFERENCES employees_table (id) ON DELETE CASCADE

);

CREATE TABLE suppliers (
                           supplier_id VARCHAR(10) PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           phone VARCHAR(15),
                           email VARCHAR(100),
                           address TEXT
);

-- Table for storing item information, linked to suppliers
CREATE TABLE items (
                       item_id VARCHAR(30) PRIMARY KEY,
                       name VARCHAR(30) NOT NULL,
                       qty INT,
                       unit_price DECIMAL(10, 2),
                       supplier_id VARCHAR(10)
                      -- FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

-- Table for storing order header, linked to customers
CREATE TABLE orders (
                        order_id VARCHAR(30) PRIMARY KEY,
                        customer_id VARCHAR(30) NOT NULL,
                        order_date DATE,
                        FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Table for storing order line details, linking orders and items
CREATE TABLE order_details (
                               order_id VARCHAR(30),
                               item_id VARCHAR(30),
                               qty INT,
                               total_price DECIMAL(10, 2),
                               PRIMARY KEY (order_id, item_id),
                               FOREIGN KEY (order_id) REFERENCES orders(order_id),
                               FOREIGN KEY (item_id) REFERENCES items(item_id)
);


CREATE  TABLE lable(

    lblid INT AUTO_INCREMENT PRIMARY KEY,
    massege VARCHAR(1000)
);
INSERT INTO lable (massege)
VALUES ('this is your birthaday month !');





-- Insert into system_user
INSERT INTO system_user (user_id, user_password, user_role, user_name, phone_number)
VALUES ('U001', '1', 'admin', 'John Doe', '1234567890');

-- Insert into coaches
INSERT INTO coaches (coach_id, coach_name, coach_number, system_user_Roll, email)
VALUES ('C001', 'Coach Max', '9876543210', 'admin', 'coachmax@example.com');

-- Insert into worker
INSERT INTO worker (worker_id, worker_name, worker_number, system_user_roll, email)
VALUES ('W001', 'Worker Jane', '5556667777', 'admin', 'workerjane@example.com');

-- Insert into customer
INSERT INTO customer (customer_id, customer_name, customer_address, contact_number, customer_birthday, customer_weight,
                      customer_email,register_fees)
VALUES ('C002', 'Alice Smith', '123 Main St', '7778889999', '2025-05-04', 67,'savindunawanjana08@gmail.com',100.00);

-- Insert into additional_leave_coach
INSERT INTO additional_leave_coach (coach_id, system_user_id, leave_date)
VALUES ('C001', 'U001', '2025-05-01');

-- Insert into additional_leave_worker
INSERT INTO additional_leave_worker (worker_id, system_user_Roll, leave_date)
VALUES ('W001', 'U001', '2025-05-02');

-- Insert into delete_coach
INSERT INTO delete_coach (coach_id, coach_name, delete_date, system_user_Roll, reason_to_delete)
VALUES ('C001', 'Coach Max', '2025-05-03', 'U001', 'Retired');

-- Insert into delete_worker
INSERT INTO delete_worker (worker_id, worker_name, delete_date, system_user_Roll, reason_to_delete)
VALUES ('W001', 'Worker Jane', '2025-05-04', 'U001', 'Resigned');

-- Insert into edit_oto_generate_message
INSERT INTO edit_oto_generate_message (system_user_id, edit_date)
VALUES ('U001', '2025-05-05');

-- Insert into monthly_report
INSERT INTO monthly_report (report_date)
VALUES ('2025-05-01');

-- Insert into coman_password
INSERT INTO coman_password (password)
VALUES ('1');

-- Insert into customer_payment
INSERT INTO customer_payment (customer_id, expire_date, valid_number_of_month, system_user_roll, payment_date,
                              payment_amount)
VALUES ('C002', '2025-08-01', 3, 'admin', '2025-05-01', 150.00);

-- Insert into employee_payments
INSERT INTO employee_payments (system_user_roll, employee_role, employee_id, payment_date, salary)
VALUES ('U001', 'coach', 'C001', '2025-05-10', 1200.00);

-- Insert into manage_exercises
-- Insert into message_schedule_to_customer
INSERT INTO message_schedule_to_customer (system_user_id, customer_id, send_date)
VALUES ('U001', 'C002', '2025-05-15');

-- Insert into system_user_additional_leave
INSERT INTO system_user_additional_leave (user_id, leave_date)
VALUES ('U001', '2025-05-11');

-- Insert into system_user_delete
INSERT INTO system_user_delete (user_id, delete_date, reason_to_delete)
VALUES ('U001', '2025-05-12', 'Account closure');

INSERT INTO customer_payment (customer_id, expire_date, valid_number_of_month, system_user_roll, payment_date,
                              payment_amount)
VALUES ('C002', '2025-08-01', 3, 'U001', '2025-05-01', 150.00);

INSERT INTO suppliers (supplier_id, name, phone, email, address) VALUES
                                                                     ('S001', 'Fresh Produce Ltd', '0777123456', 'contact@freshproduce.com', '45 Market Rd'),
                                                                     ('S002', 'Bakery World', '0777567890', 'sales@bakeryworld.com', '78 Bread Street'),
                                                                     ('S003', 'Dairy Direct', '0788123456', 'info@dairydirect.com', '22 Milk Avenue');

INSERT INTO items (item_id, name, qty, unit_price, supplier_id) VALUES
                                                                    ('I001', 'Apples', 100, 50.00, 'S001'),
                                                                    ('I002', 'Bananas', 120, 40.00, 'S001'),
                                                                    ('I003', 'Bread', 80, 150.00, 'S002'),
                                                                    ('I004', 'Milk 1L', 60, 120.00, 'S003'),
                                                                    ('I005', 'Cheese 500g', 40, 500.00, 'S003');


INSERT INTO orders (order_id, customer_id, order_date) VALUES
                                                           ('O1001', 'C002', '2025-05-21'),
                                                           ('O1002', 'C002', '2025-05-22');


INSERT INTO order_details (order_id, item_id, qty, total_price) VALUES
-- Order 1
('O1001', 'I001', 5, 250.00),
('O1001', 'I003', 2, 300.00),
-- Order 2
('O1002', 'I004', 3, 360.00),
('O1002', 'I005', 1, 500.00);




SELECT COUNT(coach_id)
FROM coaches;

SELECT *
FROM coaches;

SELECT worker_id        AS id,
       worker_name      AS name,
       worker_number    AS number,
       system_user_Roll AS role,
       email
FROM worker

UNION ALL

SELECT coach_id         AS id,
       coach_name       AS name,
       coach_number     AS number,
       system_user_Roll AS role,
       email
FROM coaches;

--
INSERT INTO employees_table (id, name, number, role, email)
SELECT w.worker_id, w.worker_name, w.worker_number, w.system_user_Roll, w.email
FROM worker w
WHERE NOT EXISTS (
    SELECT 1 FROM employees_table e WHERE e.id = w.worker_id
)

UNION ALL

SELECT c.coach_id, c.coach_name, c.coach_number, c.system_user_Roll, c.email
FROM coaches c
WHERE NOT EXISTS (
    SELECT 1 FROM employees_table e WHERE e.id = c.coach_id
);

SELECT ALL * FROM  employees_table;
SELECT ALL * FROM manage_exercises;

    INSERT INTO manage_exercises (id, name, category, system_user_id) VALUES
                                                                      (1, 'Bench Press', 'Strength', 'U001'),
                                                                      (2, 'Deadlift', 'Strength', 'U001'),
                                                                      (3, 'Treadmill Run', 'Cardio', 'U001'),
                                                                      (4, 'Yoga Stretch', 'Flexibility', 'U001'),
                                                                      (5, 'Push Ups', 'Strength', 'U001'),
                                                                      (6, 'Burpees', 'Cardio', 'U001'),
                                                                      (7, 'Squat', 'Strength', 'U001'),
                                                                      (8, 'Leg Press', 'Strength', 'U001'),
                                                                      (9, 'Cycling', 'Cardio', 'U001'),
                                                                      (10, 'Jump Rope', 'Cardio', 'U001'),
                                                                      (11, 'Plank', 'Core', 'U001'),
                                                                      (12, 'Side Lunges', 'Flexibility', 'U001'),
                                                                      (13, 'Hamstring Stretch', 'Flexibility', 'U001');

SHOW TABLES IN gymsystemap;
#
# SELECT
#     -- Customers
#     (SELECT COUNT(*) FROM customer) AS total_customers,
#
#     -- Customer Payments
#     (SELECT COUNT(*) FROM customer_payment WHERE MONTH(payment_date) = 5 AND YEAR(payment_date) = 2025) AS total_customer_payments,
#     (SELECT IFNULL(SUM(payment_amount), 0) FROM customer_payment WHERE MONTH(payment_date) = 5 AND YEAR(payment_date) = 2025) AS total_customer_payment_amount,
#
#     -- New Coaches
#     (SELECT COUNT(*) FROM coaches WHERE MONTH(CURRENT_DATE()) = 5 AND YEAR(CURRENT_DATE()) = 2025) AS new_coaches, -- No join_date column exists
#
#     -- Deleted Coaches
#     (SELECT COUNT(*) FROM delete_coach WHERE MONTH(delete_date) = 5 AND YEAR(delete_date) = 2025) AS deleted_coaches,
#
#     -- New Workers
#     (SELECT COUNT(*) FROM worker WHERE MONTH(CURRENT_DATE()) = 5 AND YEAR(CURRENT_DATE()) = 2025) AS new_workers, -- No join_date column exists
#
#     -- Deleted Workers
#     (SELECT COUNT(*) FROM delete_worker WHERE MONTH(delete_date) = 5 AND YEAR(delete_date) = 2025) AS deleted_workers,
#
#     -- Employee Payments
#     (SELECT COUNT(*) FROM employee_payments WHERE MONTH(payment_date) = 5 AND YEAR(payment_date) = 2025) AS total_employee_payments,
#     (SELECT IFNULL(SUM(salary), 0) FROM employee_payments WHERE MONTH(payment_date) = 5 AND YEAR(payment_date) = 2025) AS total_employee_payment_amount,
#
#     -- Additional Leaves (Coach)
#     (SELECT COUNT(*) FROM additional_leave_coach WHERE MONTH(leave_date) = 5 AND YEAR(leave_date) = 2025) AS coach_leaves,
#
#     -- Additional Leaves (Worker)
#     (SELECT COUNT(*) FROM additional_leave_worker WHERE MONTH(leave_date) = 5 AND YEAR(leave_date) = 2025) AS worker_leaves,
#
#     -- System User Leaves
#     (SELECT COUNT(*) FROM system_user_additional_leave WHERE MONTH(leave_date) = 5 AND YEAR(leave_date) = 2025) AS system_user_leaves,
#
#     -- Deleted System Users
#     (SELECT COUNT(*) FROM system_user_delete WHERE MONTH(delete_date) = 5 AND YEAR(delete_date) = 2025) AS deleted_system_users,
#
#     -- Exercises Added
#     (SELECT COUNT(*) FROM manage_exercises WHERE MONTH(added_date) = 5 AND YEAR(added_date) = 2025) AS exercises_added,
#
#     -- Messages Sent
#     (SELECT COUNT(*) FROM message_schedule_to_customer WHERE MONTH(send_date) = 5 AND YEAR(send_date) = 2025) AS messages_sent,
#
#     -- Orders Placed
#     (SELECT COUNT(*) FROM orders WHERE MONTH(order_date) = 5 AND YEAR(order_date) = 2025) AS total_orders,
#
#     -- Items Sold (total quantity)
#     (SELECT IFNULL(SUM(od.qty), 0)
#      FROM order_details od
#               JOIN orders o ON od.order_id = o.order_id
#      WHERE MONTH(o.order_date) = 5 AND YEAR(o.order_date) = 2025) AS items_sold,
#
#     -- Total Sales Revenue
#     (SELECT IFNULL(SUM(od.total_price), 0)
#      FROM order_details od
#               JOIN orders o ON od.order_id = o.order_id
#      WHERE MONTH(o.order_date) = 5 AND YEAR(o.order_date) = 2025) AS total_sales;
-- Total payments from customers this month




SELECT 'Total Customer Payments' AS category,
       CONCAT('Rs. ', IFNULL(SUM(cp.payment_amount), 0), ' (', COUNT(*), ' payments)') AS value
FROM customer_payment cp
WHERE MONTH(cp.payment_date) = MONTH(CURDATE()) AND YEAR(cp.payment_date) = YEAR(CURDATE())

UNION ALL

-- New coaches added this month (only if added_date column exists)
SELECT 'New Coaches Added',
       COUNT(*)
FROM coaches
WHERE MONTH(add_date) = MONTH(CURDATE()) AND YEAR(add_date) = YEAR(CURDATE())

UNION ALL

-- Deleted coaches this month
SELECT 'Deleted Coaches',
       COUNT(*)
FROM delete_coach
WHERE MONTH(delete_date) = MONTH(CURDATE()) AND YEAR(delete_date) = YEAR(CURDATE())

UNION ALL

-- New workers added this month (only if added_date column exists)
SELECT 'New Workers Added',
       COUNT(*)
FROM worker
WHERE MONTH(add_date) = MONTH(CURDATE()) AND YEAR(add_date) = YEAR(CURDATE())

UNION ALL

-- Deleted workers this month
SELECT 'Deleted Workers',
       COUNT(*)
FROM delete_worker
WHERE MONTH(delete_date) = MONTH(CURDATE()) AND YEAR(delete_date) = YEAR(CURDATE())

UNION ALL

-- Employee payments this month
SELECT 'Employee Payments',
       CONCAT('Rs. ', IFNULL(SUM(salary), 0), ' from ', COUNT(*), ' payments')
FROM employee_payments
WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())

UNION ALL

-- Exercises added this month
SELECT 'Exercises Added',
       COUNT(*)
FROM manage_exercises
WHERE MONTH(added_date) = MONTH(CURDATE()) AND YEAR(added_date) = YEAR(CURDATE())

UNION ALL

-- Orders placed this month
SELECT 'Orders Placed',
       COUNT(*)
FROM orders
WHERE MONTH(order_date) = MONTH(CURDATE()) AND YEAR(order_date) = YEAR(CURDATE());

CREATE TABLE monthly_summary (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 category_key VARCHAR(100) NOT NULL,      -- e.g., totalCustomerPayments
                                 category_label VARCHAR(100) NOT NULL,    -- e.g., "Total Customer Payments"
                                 value VARCHAR(255) NOT NULL,
                                 summary_month TINYINT,
                                 summary_year YEAR,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

SELECT ALL * FROM monthly_summary;
INSERT INTO monthly_summary (category_key, category_label, `value`, summary_month, summary_year)
SELECT category_key, category_label, `value`, summary_month, summary_year
FROM (
         SELECT 'totalCustomerPayments' AS category_key, 'Total Customer Payments' AS category_label,
                CONCAT('Rs. ', IFNULL(SUM(cp.payment_amount), 0), ' (', COUNT(*), ' payments)') AS `value`,
                MONTH(CURDATE()) AS summary_month, YEAR(CURDATE()) AS summary_year
         FROM customer_payment cp
         WHERE MONTH(cp.payment_date) = MONTH(CURDATE()) AND YEAR(cp.payment_date) = YEAR(CURDATE())

         UNION ALL

         SELECT 'newCoachesAdded', 'New Coaches Added',
                COUNT(*),
                MONTH(CURDATE()), YEAR(CURDATE())
         FROM coaches
         WHERE MONTH(add_date) = MONTH(CURDATE()) AND YEAR(add_date) = YEAR(CURDATE())

         UNION ALL

         SELECT 'deletedCoaches', 'Deleted Coaches',
                COUNT(*),
                MONTH(CURDATE()), YEAR(CURDATE())
         FROM delete_coach
         WHERE MONTH(delete_date) = MONTH(CURDATE()) AND YEAR(delete_date) = YEAR(CURDATE())

         UNION ALL

         SELECT 'newWorkersAdded', 'New Workers Added',
                COUNT(*),
                MONTH(CURDATE()), YEAR(CURDATE())
         FROM worker
         WHERE MONTH(add_date) = MONTH(CURDATE()) AND YEAR(add_date) = YEAR(CURDATE())

         UNION ALL

         SELECT 'deletedWorkers', 'Deleted Workers',
                COUNT(*),
                MONTH(CURDATE()), YEAR(CURDATE())
         FROM delete_worker
         WHERE MONTH(delete_date) = MONTH(CURDATE()) AND YEAR(delete_date) = YEAR(CURDATE())

         UNION ALL

         SELECT 'employeePayments', 'Employee Payments',
                CONCAT('Rs. ', IFNULL(SUM(salary), 0), ' from ', COUNT(*), ' payments'),
                MONTH(CURDATE()), YEAR(CURDATE())
         FROM employee_payments
         WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())

         UNION ALL

         SELECT 'exercisesAdded', 'Exercises Added',
                COUNT(*),
                MONTH(CURDATE()), YEAR(CURDATE())
         FROM manage_exercises
         WHERE MONTH(added_date) = MONTH(CURDATE()) AND YEAR(added_date) = YEAR(CURDATE())

         UNION ALL

         SELECT 'ordersPlaced', 'Orders Placed',
                COUNT(*),
                MONTH(CURDATE()), YEAR(CURDATE())
         FROM orders
         WHERE MONTH(order_date) = MONTH(CURDATE()) AND YEAR(order_date) = YEAR(CURDATE())
     ) AS data
WHERE NOT EXISTS (
    SELECT 1 FROM monthly_summary ms
    WHERE ms.category_key = data.category_key
      AND ms.summary_month = data.summary_month
      AND ms.summary_year = data.summary_year
);

-- ////////////////////////////////////////////////////////////////////////////////////////////////
SELECT COALESCE(SUM(payment_amount), 0) AS total_customer_payments
FROM customer_payment
WHERE EXTRACT(MONTH FROM payment_date) = EXTRACT(MONTH FROM CURRENT_DATE)
  AND EXTRACT(YEAR FROM payment_date) = EXTRACT(YEAR FROM CURRENT_DATE);

SELECT COUNT(*) AS new_coaches
FROM coaches
WHERE EXTRACT(MONTH FROM add_date) = EXTRACT(MONTH FROM CURRENT_DATE)
  AND EXTRACT(YEAR FROM add_date) = EXTRACT(YEAR FROM CURRENT_DATE);

SELECT COUNT(*) AS deleted_coaches
FROM delete_coach
WHERE delete_date IS NOT NULL
  AND EXTRACT(MONTH FROM delete_date) = EXTRACT(MONTH FROM CURRENT_DATE)
  AND EXTRACT(YEAR FROM delete_date) = EXTRACT(YEAR FROM CURRENT_DATE);

SELECT COUNT(*) AS new_workers
FROM worker
WHERE EXTRACT(MONTH FROM add_date) = EXTRACT(MONTH FROM CURRENT_DATE)
  AND EXTRACT(YEAR FROM add_date) = EXTRACT(YEAR FROM CURRENT_DATE);


SELECT COUNT(*) AS deleted_workers
FROM delete_worker
WHERE delete_date IS NOT NULL
  AND EXTRACT(MONTH FROM delete_date) = EXTRACT(MONTH FROM CURRENT_DATE)
  AND EXTRACT(YEAR FROM delete_date) = EXTRACT(YEAR FROM CURRENT_DATE);

SELECT COUNT(*) AS exercises_added
FROM manage_exercises
WHERE EXTRACT(MONTH FROM added_date) = EXTRACT(MONTH FROM CURRENT_DATE)
  AND EXTRACT(YEAR FROM added_date) = EXTRACT(YEAR FROM CURRENT_DATE);


SELECT COUNT(*) AS orders_placed
FROM orders
WHERE EXTRACT(MONTH FROM order_date) = EXTRACT(MONTH FROM CURRENT_DATE)
  AND EXTRACT(YEAR FROM order_date) = EXTRACT(YEAR FROM CURRENT_DATE);
