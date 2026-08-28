-- Syntax fk: constraint + ten rang buoc + foreign key (cot hien tai) references + teen bang lien quan(cot lien quan) 
-- Unique và primary key khác nhau như nào? là unique thì có thể có giá trị null còn primary key thì phải có giá trị.
-- Default: khi không truyền giá trị vào cột đó thì sẽ lấy giá trị ở default làm giá trị
-- NOT NULL: cột đó phải có giá trị
-- Check: kiểm tra giá trị có hợp lệ không
DROP DATABASE IF EXISTS rw_102;

CREATE DATABASE rw_102;

USE rw_102;

-- Table 1:Department  
-- DepartmentID:  định danh của phòng ban (auto increment) 
-- DepartmentName: tên đầy đủ của phòng ban (VD: sale, marketing, …) 

CREATE TABLE department ( 
	department_id 		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    department_name    VARCHAR(50) NOT NULL UNIQUE
);
            
-- Table 2: Position  
-- PositionID:  định danh của chức vụ (auto increment) 
-- PositionName: tên chức vụ (Dev, Test, Scrum Master, PM) 

CREATE TABLE position (
	position_id 		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    position_name 		ENUM('DEV','TEST','SCRUM_MASTER','PM')
);

-- Table 3: Account  
-- AccountID:  định danh của User (auto increment) 
-- Email:  Địa chỉ email
-- Username:  tên đăng nhập
-- FullName:  tên đầy đủ
-- DepartmentID: phòng ban của user trong hệ thống 
-- PositionID: chức vụ của User 
-- CreateDate: ngày tạo tài khoản 

CREATE TABLE account (
	account_id 			INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email				VARCHAR(255) UNIQUE NOT NULL,
    user_name			VARCHAR(50) UNIQUE NOT NULL,
    full_name			VARCHAR(255) DEFAULT 'NO NAME',
    department_id		INT UNSIGNED,
    position_id			INT UNSIGNED,
    create_date			DATETIME DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_acc_dep FOREIGN KEY (department_id) REFERENCES department(department_id),
    CONSTRAINT fk_acc_pos FOREIGN KEY (position_id) REFERENCES `position`(position_id),
    CONSTRAINT chk_username Check(length(user_name) > 8)
);

-- Table 4: Group  
-- GroupID:  định danh của nhóm (auto increment) 
-- GroupName:  tên nhóm 
-- CreatorID: id của người tạo group 
-- CreateDate: ngày tạo group 

CREATE TABLE `group` (
    group_id           INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    group_name         VARCHAR(100) NOT NULL,
    creator_id         INT UNSIGNED NOT NULL,
    create_date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_group_creator FOREIGN KEY (creator_id) REFERENCES account(account_id)
);

-- Table 5: GroupAccount  
-- GroupID:  định danh của nhóm 
-- AccountID:  định danh của User 
-- JoinDate: Ngày user tham gia vào nhóm 

CREATE TABLE group_account (
	group_id			INT UNSIGNED NOT NULL,
    account_id         	INT UNSIGNED NOT NULL,
	join_date          	DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (group_id, account_id),
    CONSTRAINT fk_group_account_group FOREIGN KEY (group_id)  REFERENCES `group`(group_id),
	CONSTRAINT fk_group_account_account FOREIGN KEY (account_id) REFERENCES account(account_id)
);

-- Table 6: TypeQuestion  
-- TypeID:  định danh của loại câu hỏi (auto increment) 
-- TypeName:  tên của loại câu hỏi (Essay, Multiple-Choice) 

CREATE TABLE type_question (
	type_id				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    type_name			ENUM('ESSAY','MULTIPLE_CHOICE')
);

-- Table 7: CategoryQuestion  
-- CategoryID:  định danh của chủ đề câu hỏi (auto increment) 
-- CategoryName:  tên của chủ đề câu hỏi (Java, .NET, SQL, Postman, Ruby, …) 

CREATE TABLE category_question (
	category_id        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	category_name      VARCHAR(50) NOT NULL UNIQUE
);

-- Table 8: Question  
-- QuestionID:  định danh của câu hỏi (auto increment) 
-- Content:  nội dung của câu hỏi 
-- CategoryID:  định danh của chủ đề câu hỏi 
-- TypeID:  định danh của loại câu hỏi 
-- CreatorID: id của người tạo câu hỏi 
-- CreateDate: ngày tạo câu hỏi 

CREATE TABLE question (
	question_id			INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content				TEXT NOT NULL,
    category_id			INT UNSIGNED NOT NULL,
    type_id				INT UNSIGNED NOT NULL,
    creator_id			INT UNSIGNED NOT NULL,
    create_date			DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_question_category FOREIGN KEY (category_id) REFERENCES category_question(category_id),
    CONSTRAINT fk_question_type FOREIGN KEY (type_id) REFERENCES type_question(type_id),
    CONSTRAINT fk_question_creator FOREIGN KEY (creator_id) REFERENCES account(account_id)
);

-- Table 9: Answer  
-- AnswerID:  định danh của câu trả lời (auto increment) 
-- Content:  nội dung của câu trả lời 
-- QuestionID:  định danh của câu hỏi  
-- isCorrect: câu trả lời này đúng hay sai 

CREATE TABLE answer (
	answer_id			INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content				TEXT NOT NULL,
    question_id			INT UNSIGNED NOT NULL,
    isCorrect			BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT fk_answer_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);

-- Table 10: Exam  
-- ExamID:  định danh của đề thi (auto increment) 
-- Code: mã đề thi 
-- Title: tiêu đề của đề thi 
-- CategoryID:  định danh của chủ đề thi 
-- Duration: thời gian thi 
-- CreatorID: id của người tạo đề thi 
-- CreateDate: ngày tạo đề thi 

CREATE TABLE exam (
	exam_id				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    code				VARCHAR(20) NOT NULL UNIQUE,
    title				TEXT NOT NULL,
    category_id			INT UNSIGNED NOT NULL,
    duration			SMALLINT UNSIGNED NOT NULL,
    creator_id			INT UNSIGNED NOT NULL,
    create_date		DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_exam_category FOREIGN KEY (category_id) REFERENCES category_question(category_id),
    CONSTRAINT fk_exam_creator FOREIGN KEY (creator_id) REFERENCES account(account_id)
);

-- Table 11: ExamQuestion  
-- ExamID:  định danh của đề thi 
-- QuestionID:  định danh của câu hỏi  

CREATE TABLE exam_question (
	exam_id            INT UNSIGNED NOT NULL,
	question_id        INT UNSIGNED NOT NULL,
    PRIMARY KEY (exam_id, question_id),
    CONSTRAINT fk_exam_question_exam FOREIGN KEY (exam_id) REFERENCES exam(exam_id),
    CONSTRAINT fk_exam_question_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);


-- ============================ INSERT INTO ==================================


-- Table 1: Department
INSERT INTO department (department_name)
VALUES
    ('Marketing'),
    ('Sale'),
    ('Bảo vệ'),
    ('Nhân sự'),
    ('Kỹ thuật');

-- Table 2: Position
INSERT INTO `position` (position_name)
VALUES
    ('DEV'),
    ('TEST'),
    ('SCRUM_MASTER'),
    ('PM'),
    ('DEV');

-- Table 3: Account
INSERT INTO account
    (email, user_name, full_name, department_id, position_id, create_date)
VALUES
    ('loc@gmail1.com', 'ngotienloc01', 'Ngo Tien Loc1', 3, 1, '2020-08-20 08:00:00'),
    ('loc@gmail2.com', 'ngotienloc02', 'Ngo Tien Loc2', 3, 2, '2026-08-20 08:00:00'),
    ('loc@gmail3.com', 'ngotienloc03', 'Ngo Tien Loc3', 2, 1, '2026-08-20 08:00:00'),
    ('loc@gmail4.com', 'ngotienloc04', 'Ngo Tien Loc4', 2, 3, '2026-08-20 08:00:00'),
    ('loc@gmail5.com', 'ngotienloc05', 'Ngo Tien Loc5', 4, 1, '2026-08-20 08:00:00'),
    ('loc@gmail6.com', 'ngotienloc06', 'Ngo Tien Loc6', 3, 1, '2026-08-20 08:00:00'),
    ('loc@gmail7.com', 'ngotienloc07', 'Ngo Tien Loc7', 3, 2, '2026-08-20 08:00:00');

-- Table 4: Group
INSERT INTO `group`
    (group_name, creator_id, create_date)
VALUES
    ('Group 1', 1, '2020-08-20 08:00:00'),
    ('Group 2', 2, '2024-08-20 08:00:00'),
    ('Group 3', 3, '2026-08-20 08:00:00'),
    ('Group 4', 4, '2026-08-20 08:00:00'),
    ('Group 5', 5, '2026-08-20 08:00:00'),
    ('Group 6', 1, '2026-08-20 08:00:00');

-- Table 5: GroupAccount
INSERT INTO group_account
    (group_id, account_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5);

-- Table 6: TypeQuestion
INSERT INTO type_question (type_name)
VALUES
    ('ESSAY'),
    ('MULTIPLE_CHOICE'),
    ('ESSAY'),
    ('MULTIPLE_CHOICE'),
    ('ESSAY'),
    ('MULTIPLE_CHOICE'),
    ('ESSAY'),
    ('MULTIPLE_CHOICE'),
    ('ESSAY'),
    ('MULTIPLE_CHOICE');

-- Table 7: CategoryQuestion
INSERT INTO category_question (category_name)
VALUES
    ('SQL01'),
    ('SQL02'),
    ('SQL03'),
    ('SQL04'),
    ('SQL05');


-- Table 8: Question
INSERT INTO question
    (content, category_id, type_id, creator_id, create_date)
VALUES
    ('Câu hỏi 1', 1, 1, 1, '2019-08-20 08:00:00'),
    ('Câu hỏi 2', 2, 2, 2, '2019-08-20 08:00:00'),
    ('Câu hỏi 3', 3, 1, 3, '2026-08-20 08:00:00'),
    ('Câu hỏi 4', 4, 2, 4, '2026-08-20 08:00:00'),
    ('Câu hỏi 5', 5, 1, 5, '2026-08-20 08:00:00'),
    ('Câu hỏi 6', 1, 1, 1, '2026-08-20 08:00:00'),
    ('Câu hỏi 7', 1, 2, 2, '2026-08-20 08:00:00'),
    ('Câu hỏi 8', 2, 1, 3, '2026-08-20 08:00:00'),
    ('Câu hỏi 9', 3, 2, 4, '2026-08-20 08:00:00');
-- Table 9: Answer
INSERT INTO answer
    (content, question_id, isCorrect)
VALUES
    ('Trả lời 1', 1, TRUE),
    ('Trả lời 2', 1, FALSE),
    ('Trả lời 3', 1, FALSE),
    ('Trả lời 4', 1, FALSE),
    ('Trả lời 5', 2, TRUE),
    ('Trả lời 6', 3, TRUE),
    ('Trả lời 7', 4, TRUE),
    ('Trả lời 8', 5, TRUE),
    ('Trả lời 9', 6, TRUE),
    ('Trả lời 10', 7, TRUE);

-- Table 10: Exam
INSERT INTO exam
    (code, title, category_id, duration, creator_id, create_date)
VALUES
    ('EXAM01', 'Đề thi 1', 1, 60, 1, '2019-08-20 08:00:00'),
    ('EXAM02', 'Đề thi 2', 2, 45, 2, '2019-08-20 08:00:00'),
    ('EXAM03', 'Đề thi 3', 3, 60, 3, '2026-08-20 08:00:00'),
    ('EXAM04', 'Đề thi 4', 4, 30, 4, '2026-08-20 08:00:00'),
    ('EXAM05', 'Đề thi 5', 5, 45, 5, '2026-08-20 08:00:00');

-- Table 11: ExamQuestion
INSERT INTO exam_question
    (exam_id, question_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (2, 1),
    (3, 1),
    (4, 1);

-- ============================ STORE PROCEDURE ==================================
DELIMITER $$

-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó
CREATE PROCEDURE sp_get_account_by_department(IN p_department_name VARCHAR(50))
BEGIN
    SELECT
        acc.account_id,
        acc.email,
        acc.user_name,
        acc.full_name,
        dep.department_name
    FROM account acc
    JOIN department dep ON acc.department_id = dep.department_id
    WHERE dep.department_name = p_department_name;
END$$


-- Question 2: Tạo store để in ra số lượng account trong mỗi group
CREATE PROCEDURE sp_count_account_in_group()
BEGIN
    SELECT
        g.group_id,
        g.group_name,
        COUNT(ga.account_id) AS so_luong_account
    FROM `group` g
    LEFT JOIN group_account ga ON g.group_id = ga.group_id
    GROUP BY
        g.group_id,
        g.group_name;
END$$


-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại
CREATE PROCEDURE sp_count_question_by_type_in_current_month()
BEGIN
    SELECT
        tq.type_id,
        tq.type_name,
        COUNT(q.question_id) AS so_luong_question
    FROM type_question tq
    LEFT JOIN question q ON tq.type_id = q.type_id
        AND MONTH(q.create_date) = MONTH(CURRENT_DATE())
        AND YEAR(q.create_date) = YEAR(CURRENT_DATE())
    GROUP BY
        tq.type_id,
        tq.type_name;
END$$


-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất
CREATE PROCEDURE sp_get_type_id_many_question(OUT p_type_id INT)
BEGIN
    SELECT type_id
    INTO p_type_id
    FROM question
    GROUP BY type_id
    ORDER BY COUNT(question_id) DESC
    LIMIT 1;
END$$


-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question
CREATE PROCEDURE sp_get_type_name_many_question()
BEGIN
    CALL sp_get_type_id_many_question(@type_id);

    SELECT
        type_id,
        type_name
    FROM type_question
    WHERE type_id = @type_id;
END$$


-- Question 6: Nhập vào 1 chuỗi, trả về group name hoặc username có chứa chuỗi đó
CREATE PROCEDURE sp_search_group_or_user(IN p_keyword VARCHAR(100))
BEGIN
    SELECT
        'GROUP' AS loai_du_lieu,
        group_id AS id,
        group_name AS ten
    FROM `group`
    WHERE group_name LIKE CONCAT('%', p_keyword, '%')

    UNION

    SELECT
        'USER' AS loai_du_lieu,
        account_id AS id,
        user_name AS ten
    FROM account
    WHERE user_name LIKE CONCAT('%', p_keyword, '%');
END$$


-- Question 7: Nhập fullName, email và tự động tạo username, position, department
CREATE PROCEDURE sp_create_account(
    IN p_full_name VARCHAR(255),
    IN p_email VARCHAR(255)
)
BEGIN
    DECLARE v_user_name VARCHAR(50);
    DECLARE v_position_id INT;
    DECLARE v_department_id INT;

    SET v_user_name = SUBSTRING_INDEX(p_email, '@', 1);

    SELECT position_id
    INTO v_position_id
    FROM `position`
    WHERE position_name = 'DEV'
    LIMIT 1;

    SELECT department_id
    INTO v_department_id
    FROM department
    WHERE department_name = 'Phòng chờ'
    LIMIT 1;

    IF v_department_id IS NULL THEN
        INSERT INTO department(department_name)
        VALUES ('Phòng chờ');

        SET v_department_id = LAST_INSERT_ID();
    END IF;

    INSERT INTO account(
        email,
        user_name,
        full_name,
        department_id,
        position_id
    )
    VALUES (
        p_email,
        v_user_name,
        p_full_name,
        v_department_id,
        v_position_id
    );

    SELECT 'Tạo account thành công' AS thong_bao;
END$$


-- Question 8: Nhập Essay hoặc Multiple-Choice để tìm question có content dài nhất
CREATE PROCEDURE sp_get_longest_question_by_type(IN p_type_name VARCHAR(50))
BEGIN
    SELECT
        q.question_id,
        q.content,
        tq.type_name,
        CHAR_LENGTH(q.content) AS do_dai_content
    FROM question q
    JOIN type_question tq ON q.type_id = tq.type_id
    WHERE tq.type_name = p_type_name
    ORDER BY CHAR_LENGTH(q.content) DESC
    LIMIT 1;
END$$


-- Question 9: Viết store cho phép người dùng xóa exam dựa vào ID
CREATE PROCEDURE sp_delete_exam_by_id(IN p_exam_id INT)
BEGIN
    DELETE FROM exam_question
    WHERE exam_id = p_exam_id;

    DELETE FROM exam
    WHERE exam_id = p_exam_id;
END$$


-- Question 10: Tìm exam được tạo từ 3 năm trước và xóa đi
CREATE PROCEDURE sp_delete_exam_before_3_years()
BEGIN
    DECLARE v_exam_id INT;
    DECLARE v_done INT DEFAULT 0;
    DECLARE v_exam_question_removed INT DEFAULT 0;
    DECLARE v_exam_removed INT DEFAULT 0;

    DECLARE cur_exam CURSOR FOR
        SELECT exam_id
        FROM exam
        WHERE create_date < DATE_SUB(CURRENT_DATE(), INTERVAL 3 YEAR);

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = 1;

    SELECT COUNT(*)
    INTO v_exam_question_removed
    FROM exam_question
    WHERE exam_id IN (
        SELECT exam_id
        FROM exam
        WHERE create_date < DATE_SUB(CURRENT_DATE(), INTERVAL 3 YEAR)
    );

    SELECT COUNT(*)
    INTO v_exam_removed
    FROM exam
    WHERE create_date < DATE_SUB(CURRENT_DATE(), INTERVAL 3 YEAR);

    OPEN cur_exam;

    read_loop: LOOP
        FETCH cur_exam INTO v_exam_id;

        IF v_done = 1 THEN
            LEAVE read_loop;
        END IF;

        CALL sp_delete_exam_by_id(v_exam_id);
    END LOOP;

    CLOSE cur_exam;

    SELECT
        v_exam_question_removed AS so_record_exam_question_da_xoa,
        v_exam_removed AS so_record_exam_da_xoa;
END$$


-- Question 11: Xóa phòng ban theo tên, account thuộc phòng ban đó chuyển về phòng ban chờ việc
CREATE PROCEDURE sp_delete_department_by_name(IN p_department_name VARCHAR(50))
BEGIN
    DECLARE v_department_id INT;
    DECLARE v_waiting_department_id INT;

    SELECT department_id
    INTO v_department_id
    FROM department
    WHERE department_name = p_department_name
    LIMIT 1;

    SELECT department_id
    INTO v_waiting_department_id
    FROM department
    WHERE department_name = 'Chờ việc'
    LIMIT 1;

    IF v_waiting_department_id IS NULL THEN
        INSERT INTO department(department_name)
        VALUES ('Chờ việc');

        SET v_waiting_department_id = LAST_INSERT_ID();
    END IF;

    UPDATE account
    SET department_id = v_waiting_department_id
    WHERE department_id = v_department_id;

    DELETE FROM department
    WHERE department_id = v_department_id;

    SELECT 'Xóa phòng ban thành công' AS thong_bao;
END$$


-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
CREATE PROCEDURE sp_count_question_by_month_in_current_year()
BEGIN
    SELECT
        MONTH(create_date) AS thang,
        COUNT(question_id) AS so_luong_question
    FROM question
    WHERE YEAR(create_date) = YEAR(CURRENT_DATE())
    GROUP BY MONTH(create_date)
    ORDER BY thang;
END$$


-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất
CREATE PROCEDURE sp_count_question_in_6_recent_months()
BEGIN
    SELECT
        DATE_FORMAT(m.thang, '%Y-%m') AS thang,
        CASE
            WHEN COUNT(q.question_id) = 0 THEN 'không có câu hỏi nào trong tháng'
            ELSE COUNT(q.question_id)
        END AS ket_qua
    FROM (
        SELECT DATE_FORMAT(CURRENT_DATE(), '%Y-%m-01') AS thang
        UNION
        SELECT DATE_FORMAT(DATE_SUB(CURRENT_DATE(), INTERVAL 1 MONTH), '%Y-%m-01')
        UNION
        SELECT DATE_FORMAT(DATE_SUB(CURRENT_DATE(), INTERVAL 2 MONTH), '%Y-%m-01')
        UNION
        SELECT DATE_FORMAT(DATE_SUB(CURRENT_DATE(), INTERVAL 3 MONTH), '%Y-%m-01')
        UNION
        SELECT DATE_FORMAT(DATE_SUB(CURRENT_DATE(), INTERVAL 4 MONTH), '%Y-%m-01')
        UNION
        SELECT DATE_FORMAT(DATE_SUB(CURRENT_DATE(), INTERVAL 5 MONTH), '%Y-%m-01')
    ) m
    LEFT JOIN question q ON DATE_FORMAT(q.create_date, '%Y-%m') = DATE_FORMAT(m.thang, '%Y-%m')
    GROUP BY m.thang
    ORDER BY m.thang;
END$$

DELIMITER ;


-- ============================ test ==================================
-- CALL sp_get_account_by_department('Sale');
-- CALL sp_count_account_in_group();
-- CALL sp_count_question_by_type_in_current_month();

-- CALL sp_get_type_id_many_question(@type_id);
-- SELECT @type_id AS type_id_nhieu_question_nhat;

-- CALL sp_get_type_name_many_question();
-- CALL sp_search_group_or_user('loc');
-- CALL sp_create_account('Nguyen Van A', 'nguyenvana@gmail.com');
-- CALL sp_get_longest_question_by_type('ESSAY');
-- CALL sp_delete_exam_by_id(1);
-- CALL sp_delete_exam_before_3_years();
-- CALL sp_delete_department_by_name('Sale');
-- CALL sp_count_question_by_month_in_current_year();
-- CALL sp_count_question_in_6_recent_months();
