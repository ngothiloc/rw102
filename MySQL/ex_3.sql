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
    ('loc@gmail5.com', 'ngotienloc05', 'Ngo Tien Loc5', 4, 1, '2026-08-20 08:00:00');

-- Table 4: Group
INSERT INTO `group`
    (group_name, creator_id, create_date)
VALUES
    ('Group 1', 1, '2020-08-20 08:00:00'),
    ('Group 2', 2, '2024-08-20 08:00:00'),
    ('Group 3', 3, '2026-08-20 08:00:00'),
    ('Group 4', 4, '2026-08-20 08:00:00'),
    ('Group 5', 5, '2026-08-20 08:00:00');

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
    ('Câu hỏi 5', 5, 1, 5, '2026-08-20 08:00:00');

-- Table 9: Answer
INSERT INTO answer
    (content, question_id, isCorrect)
VALUES
    ('Trả lời 1', 1, TRUE),
    ('Trả lời 2', 1, FALSE),
    ('Trả lời 3', 1, FALSE),
    ('Trả lời 4', 1, FALSE),
    ('Trả lời 5', 2, TRUE);

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
    (4, 5);
    
-- ============================ Search ==================================
    
-- Question 2: Lấy ra tất cả các phòng ban
SELECT *
FROM department;


-- Question 3: Lấy ra id của phòng ban "Sale"
SELECT department_id
FROM department
WHERE department_name = 'Sale';


-- Question 4: Lấy ra thông tin account có full name dài nhất
SELECT *
FROM account
ORDER BY CHAR_LENGTH(full_name) DESC
LIMIT 1;


-- Question 5: Lấy ra thông tin account có full name dài nhất và thuộc phòng ban có id = 3
SELECT *
FROM account
WHERE department_id = 3
ORDER BY CHAR_LENGTH(full_name) DESC
LIMIT 1;


-- Question 6: Lấy ra tên group đã tham gia trước ngày 20/12/2019
SELECT DISTINCT g.group_name
FROM `group` g
JOIN group_account ga
    ON g.group_id = ga.group_id
WHERE ga.join_date < '2019-12-20';


-- Question 7: Lấy ra ID của question có >= 4 câu trả lời
SELECT question_id
FROM answer
GROUP BY question_id
HAVING COUNT(answer_id) >= 4;


-- Question 8: Lấy ra các mã đề thi có thời gian thi >= 60 phút và được tạo trước ngày 20/12/2019
SELECT code
FROM exam
WHERE duration >= 60
  AND create_date < '2019-12-20';


-- Question 9: Lấy ra 5 group được tạo gần đây nhất
SELECT *
FROM `group`
ORDER BY create_date DESC
LIMIT 5;


-- Question 10: Đếm số nhân viên thuộc department có id = 2
SELECT COUNT(account_id) AS so_luong_nhan_vien
FROM account
WHERE department_id = 2;


-- Question 11: Lấy ra nhân viên có tên bắt đầu bằng chữ "D" và kết thúc bằng chữ "o"
SELECT *
FROM account
WHERE full_name LIKE 'D%o';


-- Question 12: Xóa tất cả các exam được tạo trước ngày 20/12/2019
DELETE FROM exam_question
WHERE exam_id IN (
    SELECT exam_id
    FROM exam
    WHERE create_date < '2019-12-20'
);

DELETE FROM exam
WHERE create_date < '2019-12-20';


-- Question 13: Xóa tất cả các question có nội dung bắt đầu bằng từ "câu hỏi"
DELETE FROM answer
WHERE question_id IN (
    SELECT question_id
    FROM question
    WHERE content LIKE 'Câu hỏi%'
);

DELETE FROM exam_question
WHERE question_id IN (
    SELECT question_id
    FROM question
    WHERE content LIKE 'Câu hỏi%'
);

DELETE FROM question
WHERE content LIKE 'Câu hỏi%';


-- Question 14: Update account có id = 5
UPDATE account
SET full_name = 'Nguyễn Bá Lộc',
    email = 'loc.nguyenba@vti.com.vn'
WHERE account_id = 5;


-- Question 15: Update account có id = 5 sẽ thuộc group có id = 4
UPDATE group_account
SET group_id = 4
WHERE account_id = 5;

12:22:47	DELETE FROM exam WHERE create_date < '2019-12-20'	Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column.  To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.	0.00015 sec
