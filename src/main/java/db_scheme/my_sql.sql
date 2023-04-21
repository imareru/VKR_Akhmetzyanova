CREATE TABLE classes (
	class_id SERIAL PRIMARY KEY,
	class_number integer NOT NULL
	);

CREATE TABLE students (
	student_id SERIAL PRIMARY KEY, 
	s_surname VARCHAR(30) NOT NULL, 
	s_name VARCHAR(20) NOT NULL,
	s_patronymic VARCHAR(30),
	s_login VARCHAR(30) NOT NULL,
	s_password VARCHAR(30) NOT NULL,
	s_birth_date DATE, 
	class_id INTEGER,
	FOREIGN KEY (class_id) REFERENCES classes (class_id)
	);
	
CREATE TABLE teachers (
	teacher_id SERIAL PRIMARY KEY, 
	t_surname VARCHAR(30) NOT NULL,
	t_name VARCHAR(20) NOT NULL,
	t_patronymic VARCHAR(30),
	t_login VARCHAR(30) NOT NULL,
	t_password VARCHAR(30) NOT NULL,
	t_birth_date DATE
);
	
CREATE TABLE answers (
	answer_id SERIAL PRIMARY KEY,
	answer_text VARCHAR(50),
	bool_val BOOLEAN
	);
	
CREATE TABLE subjects (
	subject_id SERIAL PRIMARY KEY,
	sub_name VARCHAR(15) NOT NULL,
	teacher_id INTEGER,
	FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);

CREATE TABLE tests (
	test_id SERIAL PRIMARY KEY,
	test_name VARCHAR(50) NOT NULL,
	pass_time TIME,
	if_access BOOLEAN NOT NULL,
	close_date DATE NOT NULL,
	subject_id INTEGER,
	FOREIGN KEY (subject_id) REFERENCES subjects (subject_id)
);

CREATE TABLE questions (
	question_id SERIAL PRIMARY KEY,
	question_text TEXT NOT NULL,
	image BYTEA,
	test_id INTEGER,
	answer_id INTEGER,
	FOREIGN KEY (test_id) REFERENCES tests (test_id),
	FOREIGN KEY (answer_id) REFERENCES answers (answer_id)
);

CREATE TABLE results (
	result_id SERIAL PRIMARY KEY,
	student_id INTEGER,
	test_id INTEGER,
	passed_time TIME,
	pass_date DATE,
	score INTEGER,
	FOREIGN KEY (student_id) REFERENCES students (student_id),
	FOREIGN KEY (test_id) REFERENCES tests (test_id)
);
	
