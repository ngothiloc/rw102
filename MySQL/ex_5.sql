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

-- ============================ VIEW ==================================
-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
CREATE VIEW view_account_sale AS
SELECT
    acc.account_id,
    acc.email,
    acc.user_name,
    acc.full_name,
    dep.department_name
FROM account acc
JOIN department dep ON acc.department_id = dep.department_id
WHERE dep.department_name = 'Sale';

SELECT *
FROM view_account_sale;

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
CREATE VIEW view_account_join_many_group AS
SELECT
    acc.account_id,
    acc.email,
    acc.user_name,
    acc.full_name,
    COUNT(ga.group_id) AS so_luong_group
FROM account acc
JOIN group_account ga ON acc.account_id = ga.account_id
GROUP BY
    acc.account_id,
    acc.email,
    acc.user_name,
    acc.full_name
HAVING COUNT(ga.group_id) = (
    SELECT MAX(so_luong_group)
    FROM (
        SELECT COUNT(group_id) AS so_luong_group
        FROM group_account
        GROUP BY account_id
    ) temp
);

SELECT *
FROM view_account_join_many_group;

-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ được coi là quá dài) và xóa nó đi
CREATE VIEW view_question_content_long AS
SELECT
    question_id,
    content,
    category_id,
    type_id,
    creator_id,
    create_date
FROM (
    SELECT
        question_id,
        content,
        category_id,
        type_id,
        creator_id,
        create_date,
        REGEXP_REPLACE(TRIM(content), '[[:space:]]+', ' ') AS content_da_xoa_khoang_trang_thua
    FROM question
) q
WHERE
    CHAR_LENGTH(content_da_xoa_khoang_trang_thua)
    - CHAR_LENGTH(REPLACE(content_da_xoa_khoang_trang_thua, ' ', ''))
    + 1 > 300;

SELECT *
FROM view_question_content_long;

DELETE eq
FROM exam_question eq
JOIN view_question_content_long vq ON eq.question_id = vq.question_id;

DELETE ans
FROM answer ans
JOIN view_question_content_long vq ON ans.question_id = vq.question_id;

DELETE q
FROM question q
JOIN view_question_content_long vq ON q.question_id = vq.question_id;

-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
CREATE VIEW view_department_many_account AS
SELECT
    dep.department_id,
    dep.department_name,
    COUNT(acc.account_id) AS so_luong_nhan_vien
FROM department dep
JOIN account acc ON dep.department_id = acc.department_id
GROUP BY
    dep.department_id,
    dep.department_name
HAVING COUNT(acc.account_id) = (
    SELECT MAX(so_luong_nhan_vien)
    FROM (
        SELECT COUNT(account_id) AS so_luong_nhan_vien
        FROM account
        GROUP BY department_id
    ) temp
);

SELECT *
FROM view_department_many_account;

-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo
CREATE VIEW view_question_created_by_nguyen AS
SELECT
    q.question_id,
    q.content,
    q.category_id,
    q.type_id,
    q.creator_id,
    acc.full_name,
    q.create_date
FROM question q
JOIN account acc ON q.creator_id = acc.account_id
WHERE acc.full_name LIKE 'Nguyen%'
   OR acc.full_name LIKE 'Nguyễn%';

SELECT *
FROM view_question_created_by_nguyen;
