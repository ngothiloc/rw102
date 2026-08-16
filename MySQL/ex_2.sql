-- Syntax fk: constraint + ten rang buoc + foreign key (cot hien tai) references + teen bang lien quan(cot lien quan) 
-- Unique và primary key khác nhau như nào? là unique thì có thể có giá trị null còn primary key thì phải có giá trị.
-- Default: khi không truyền giá trị vào cột đó thì sẽ lấy giá trị ở default làm giá trị
-- NOT NULL: cột đó phải có giá trị
-- Check: kiểm tra giá trị có hợp lệ không

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
    CONSTRAINT fk_question_category FOREIGN KEY (category_id) REFERENCES type_question(type_id),
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
    category_date		DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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

-- ===== INSERT IN ======

-- Table 1:Department  
INSERT INTO department (department_name)
VALUES
    ('Sale'),
    ('Marketing'),
    ('Development'),
    ('Human Resources'),
    ('Accounting');
    
-- Table 2: Position  
INSERT INTO `position` (position_name)
	VALUES 	('DEV'),
			('TEST'),
            ('SCRUM_MASTER'),
            ('PM');
		
-- Table 3: Account  
INSERT INTO account
    (email, user_name, full_name, department_id, position_id)
VALUES
    ('loc@gmail.com', 'ngotienloc01', 'Ngo Tien Loc', 3, 1),
    ('nam@gmail.com', 'nguyenvannam01', 'Nguyen Van Nam', 3, 2),
    ('lan@gmail.com', 'tranthilan01', 'Tran Thi Lan', 2, 3),
    ('hung@gmail.com', 'lequanghung01', 'Le Quang Hung', 1, 4),
    ('mai@gmail.com', 'phamthimai01', 'Pham Thi Mai', 4, 1);

-- Table 4: Group  
INSERT INTO `group`
    (group_name, creator_id)
VALUES
    ('Java Fresher', 1),
    ('SQL Training', 2),
    ('Testing Team', 3),
    ('Project Management', 4),
    ('Backend Developer', 5);

-- Table 5: Group  
INSERT INTO group_account
    (group_id, account_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5);
    
-- Table 6: Group  
INSERT INTO type_question (type_name)
VALUES
    ('ESSAY'),
    ('MULTIPLE_CHOICE');

-- Table 7: Group  
INSERT INTO category_question (category_name)
VALUES
    ('Java'),
    ('.NET'),
    ('SQL'),
    ('Postman'),
	('Ruby');

-- Table 8: Group  
INSERT INTO question
    (content, category_id, type_id, creator_id)
VALUES
    ('What is OOP in Java?', 1, 1, 1),
    ('Which keyword is used for inheritance in Java?', 1, 2, 2),
    ('What is a primary key in SQL?', 3, 1, 3),
    ('Which HTTP method is commonly used to create data?', 4, 2, 4),
    ('What is a class in Ruby?', 5, 1, 5);

-- Table 9: Group  
INSERT INTO answer
    (content, question_id, isCorrect)
VALUES
    ('OOP is Object-Oriented Programming.', 1, TRUE),
    ('extends', 2, TRUE),
    ('A primary key uniquely identifies each record in a table.', 3, TRUE),
    ('POST', 4, TRUE),
    ('A class is a blueprint for creating objects.', 5, TRUE);

-- Table 10: exam  
INSERT INTO exam
   (code, title, category_id, duration, creator_id)
VALUES
    ('JAVA01', 'Java Basic Exam', 1, 60, 1),
    ('NET01', '.NET Basic Exam', 2, 45, 2),
    ('SQL01', 'SQL Basic Exam', 3, 60, 3),
	('POSTMAN01', 'Postman API Exam', 4, 30, 4),
    ('RUBY01', 'Ruby Basic Exam', 5, 45, 5);

-- Table 11: exam_question  
INSERT INTO exam_question
    (exam_id, question_id)
VALUES
    (1, 1),
    (1, 2),
    (3, 3),
    (4, 4),
    (5, 5);