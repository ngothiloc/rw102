DROP DATABASE IF EXISTS rw_102;
CREATE DATABASE rw_102;

-- ki tu: varchar(50)
-- number: so nguyen:int 
		-- so thuc: float, double
		-- soDienThoai: string (chuoi so)
		-- ngaythang: yyyy-MM-dd(2026-08-12) - date
					-- 20:42:45				 - time
					-- 2026-08-12 20:42:45	 - datetime
-- enum: ki tu: tu cau hinh cac options


USE rw_102;

-- Table 1:Department  
-- DepartmentID:  định danh của phòng ban (auto increment) 
-- DepartmentName: tên đầy đủ của phòng ban (VD: sale, marketing, …) 

DROP TABLE IF EXISTS department;
CREATE TABLE department (
	department_id 		INT AUTO_INCREMENT UNIQUE,
    department_name 	VARCHAR(50)
);

-- Table 2: Position  
-- PositionID:  định danh của chức vụ (auto increment) 
-- PositionName: tên chức vụ (Dev, Test, Scrum Master, PM) 

CREATE TABLE position (
	position_id 		INT AUTO_INCREMENT UNIQUE,
    position_name 		VARCHAR(30)
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
	account_id 			INT AUTO_INCREMENT UNIQUE,
    email				VARCHAR(255),
    user_name			VARCHAR(50),
    full_name			VARCHAR(255),
    department_id		INT,
    position_id			INT,
    create_date			DATETIME
);

-- Table 4: Group  
-- GroupID:  định danh của nhóm (auto increment) 
-- GroupName:  tên nhóm 
-- CreatorID: id của người tạo group 
-- CreateDate: ngày tạo group 

CREATE TABLE groupp (
	group_id 			INT AUTO_INCREMENT UNIQUE,
    group_name 			VARCHAR(50),
    create_id			INT,
    create_date			DATETIME
    
);

-- Table 5: GroupAccount  
-- GroupID:  định danh của nhóm 
-- AccountID:  định danh của User 
-- JoinDate: Ngày user tham gia vào nhóm 

CREATE TABLE group_account (
	group_id			INT AUTO_INCREMENT UNIQUE,
    account_id			INT,
    join_date			DATETIME
);

-- Table 6: TypeQuestion  
-- TypeID:  định danh của loại câu hỏi (auto increment) 
-- TypeName:  tên của loại câu hỏi (Essay, Multiple-Choice) 

CREATE TABLE type_question (
	type_id				INT AUTO_INCREMENT UNIQUE,
    type_name			Enum('Essay','Multiple-Choice')
);

-- Table 7: CategoryQuestion  
-- CategoryID:  định danh của chủ đề câu hỏi (auto increment) 
-- CategoryName:  tên của chủ đề câu hỏi (Java, .NET, SQL, Postman, Ruby, …) 

CREATE TABLE category_question (
	category_id			INT AUTO_INCREMENT UNIQUE,
    category_name		VARCHAR(50)
);

-- Table 8: Question  
-- QuestionID:  định danh của câu hỏi (auto increment) 
-- Content:  nội dung của câu hỏi 
-- CategoryID:  định danh của chủ đề câu hỏi 
-- TypeID:  định danh của loại câu hỏi 
-- CreatorID: id của người tạo câu hỏi 
-- CreateDate: ngày tạo câu hỏi 

CREATE TABLE question (
	question_id			INT AUTO_INCREMENT UNIQUE,
    content				TEXT,
    category_id			INT,
    creator_id			INT,
    create_date			DATETIME
);

-- Table 9: Answer  
-- AnswerID:  định danh của câu trả lời (auto increment) 
-- Content:  nội dung của câu trả lời 
-- QuestionID:  định danh của câu hỏi  
-- isCorrect: câu trả lời này đúng hay sai 

CREATE TABLE answer (
	answer_id			INT AUTO_INCREMENT UNIQUE,
    content				text,
    question_id			int,
    isCorrect			ENUM('Đúng','Sai')
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
	exam_id				INT auto_increment unique,
    code				INT,
    title				TEXT,
    category_id			INT,
    category_date		DATETIME
);

-- Table 11: ExamQuestion  
-- ExamID:  định danh của đề thi 
-- QuestionID:  định danh của câu hỏi  

CREATE TABLE exam_question (
	exam_id				INT AUTO_INCREMENT UNIQUE,
    exam_id				INT,
    question_id			INT
);



CREATE TABLE user (
	id					INT,
    full_name			VARCHAR(255),
    username			VARCHAR(50),
    birth_of_date		DATE,
    gender				ENUM('MALE','FEMALE')
);

select * from user;

insert into department(department_id, department_name)
	values		(1, 'Sale'),
				(2, 'Marketing'),
                (3, 'Development');
                
insert into user(id, full_name, username, birth_of_date, gender)
	values (1, 'Ngô Lộc', 'loc123', '2004-10-31', 'Male')













