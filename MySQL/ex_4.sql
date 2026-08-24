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

-- ============================ JOIN ==================================
-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ 
SELECT 
	acc.account_id, 
    acc.email, 
    acc.user_name, 
    acc.full_name, 
    dep.department_name 
FROM ACCOUNT acc 
JOIN department dep ON acc.department_id = dep.department_id ;

-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010  
SELECT * FROM account WHERE create_date > '2010-12-20';

-- Question 3: Viết lệnh để lấy ra tất cả các developer  
SELECT 
	acc.account_id, 
    acc.email, 
    acc.user_name, 
    acc.full_name, 
     pos.position_name
FROM ACCOUNT acc 
JOIN position pos ON acc.position_id = pos.position_id 
WHERE pos.position_name = 'DEV';

-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên 
SELECT dep.department_id, dep.department_name, count(1) AS soluongnv
FROM department dep 
JOIN account acc ON dep.department_id = acc.department_id
GROUP BY dep.department_id, dep.department_name 
HAVING count(1) > 3;

-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất 
SELECT ques.content, count(1) FROM exam_question exq JOIN question ques ON exq.question_id = ques.question_id
GROUP by ques.content
HAVING count(1) =  (SELECT count(1) AS sluongcauhoi
					FROM exam_question
					GROUP BY question_id
                    ORDER BY count(1) DESC
					LIMIT 1);

-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question 
SELECT 
	caq.category_name,
    count(1) AS so_luong_question
FROM question ques
RIGHT JOIN category_question caq ON ques.category_id = caq.category_id
GROUP BY caq.category_name;

-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam 
SELECT ques.content, count(1) AS sluongsd
FROM exam_question exq
LEFT JOIN question ques ON ques.question_id = exq.question_id
GROUP BY ques.question_id;

-- Question 8: Lấy ra Question có nhiều câu trả lời nhất 
SELECT question_id, count(1) AS so_luong_answer
FROM answer
GROUP BY question_id
HAVING count(1) = ( SELECT count(1) AS so_luong_answer
					FROM answer
					GROUP BY question_id
                     ORDER BY COUNT(1) DESC
					LIMIT 1);
-- Question 9: Thống kê số lượng account trong mỗi group  
SELECT group_id, count(1) AS so_luong_account
FROM group_account
GROUP BY group_id;

-- Question 10: Tìm chức vụ có ít người nhất  
SELECT  pos.position_name, count(1) AS so_luong_account
FROM account acc
JOIN position pos ON acc.position_id = pos.position_id
GROUP BY acc.position_id
HAVING count(1) = ( SELECT count(1)
					FROM account
					GROUP BY position_id
					ORDER BY count(1) ASC
					LIMIT 1);

-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM   
SELECT 
	dep.department_name,
    pos.position_name,
    COUNT(1) AS so_luong
FROM account acc
JOIN department dep ON  acc.department_id = dep.department_id
JOIN position pos ON acc.position_id = pos.position_id
GROUP BY dep.department_name, pos.position_name;

-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: thông tin cơ bản của question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, … 
SELECT 
    ques.question_id,
    ques.content,
    ques.category_id,
    ques.create_date,
    typ.type_name,
    acc.full_name AS nguoi_tao,
    ans.content AS cau_tra_loi,
    ans.isCorrect
FROM question ques
JOIN type_question typ
    ON ques.type_id = typ.type_id
JOIN account acc
    ON ques.creator_id = acc.account_id
LEFT JOIN answer ans
    ON ques.question_id = ans.question_id;
    
-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm 

-- Question 15: Lấy ra group không có account nào 
SELECT g.group_name
FROM `group` g
LEFT JOIN group_account ga
    ON g.group_id = ga.group_id
WHERE ga.account_id IS NULL;
-- Question 16: Lấy ra question không có answer nào 
SELECT q.*
FROM question q
LEFT JOIN answer a
    ON q.question_id = a.question_id
WHERE a.answer_id IS NULL;

-- ============================ UNION ==================================

-- Question 17:  
-- a) Lấy các account thuộc nhóm thứ 1 
SELECT acc.*
FROM account acc
JOIN group_account ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 1
-- c) Ghép 2 kết quả từ câu a) và câu b) sao cho không có record nào trùng nhau 
UNION
-- b) Lấy các account thuộc nhóm thứ 2 
SELECT acc.*
FROM account acc
JOIN group_account ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 2;

-- Question 18:  
-- a) Lấy các group có lớn hơn 5 thành viên 
SELECT group_id, COUNT(1) AS so_luong_thanh_vien
FROM group_account
GROUP BY group_id
HAVING COUNT(1) > 5
-- c) Ghép 2 kết quả từ câu a) và câu b) 
UNION
-- b) Lấy các group có nhỏ hơn 7 thành viên 
SELECT group_id, COUNT(1) AS so_luong_thanh_vien
FROM group_account
GROUP BY group_id
HAVING COUNT(1) < 7;



