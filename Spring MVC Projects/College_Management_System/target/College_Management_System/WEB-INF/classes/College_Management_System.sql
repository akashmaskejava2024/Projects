CREATE DATABASE IF NOT EXISTS CollegeManagementSystem;
USE CollegeManagementSystem;

-- Professor Table
CREATE TABLE IF NOT EXISTS professor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    department VARCHAR(100) NOT NULL,
    active TINYINT DEFAULT 1
);

-- Student Table
CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    roll_number VARCHAR(50) UNIQUE NOT NULL,
    semester INT NOT NULL,
    professor_id INT,
    active TINYINT DEFAULT 1,
    FOREIGN KEY (professor_id) REFERENCES professor(id) ON DELETE SET NULL
);

-- Parent Table
CREATE TABLE IF NOT EXISTS parent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    active TINYINT DEFAULT 1,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
);

-- Course Table
CREATE TABLE IF NOT EXISTS course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    semester INT NOT NULL,
    active TINYINT DEFAULT 1
);

-- Exam Score Table
CREATE TABLE IF NOT EXISTS exam_score (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    semester INT NOT NULL,
    marks INT CHECK (marks BETWEEN 0 AND 100),
    active TINYINT DEFAULT 1,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);

-- Attendance Table
CREATE TABLE IF NOT EXISTS attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    date DATE NOT NULL,
    status ENUM('Present', 'Absent') NOT NULL,
    active TINYINT DEFAULT 1,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
);

-- Activity Table
CREATE TABLE IF NOT EXISTS activity ( 
    id INT AUTO_INCREMENT PRIMARY KEY, 
    student_id INT NOT NULL, 
    type ENUM('Sports', 'Cultural', 'Other') NOT NULL, 
    subtype VARCHAR(100) NOT NULL, 
    competition_level ENUM('College', 'State', 'National') NOT NULL, 
    `rank` INT NOT NULL,
    achievement VARCHAR(255), 
    image VARCHAR(255),
    active TINYINT DEFAULT 1,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE 
);

-- Student-Course Enrollment Table
CREATE TABLE IF NOT EXISTS student_course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    semester INT NOT NULL,
    professor_id INT NOT NULL,
    active TINYINT DEFAULT 1,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    FOREIGN KEY (professor_id) REFERENCES professor(id) ON DELETE CASCADE
);

-- Admin Table
CREATE TABLE IF NOT EXISTS admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    active TINYINT DEFAULT 1
);

-- Student Academic Info Table
CREATE TABLE IF NOT EXISTS student_academic_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT UNIQUE NOT NULL,
    academic_year INT NOT NULL,
    calendar_year VARCHAR(7) NOT NULL,
    active TINYINT DEFAULT 1,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
);

-- Subject Table
CREATE TABLE IF NOT EXISTS subject (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course_id INT NOT NULL,
    semester INT NOT NULL,
    active TINYINT DEFAULT 1,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);

-- Student Exam Score Table
CREATE TABLE IF NOT EXISTS student_exam_score (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    internal_marks_obtained INT CHECK (internal_marks_obtained >= 0),  
    internal_marks_total INT CHECK (internal_marks_total > 0),  
    external_marks_obtained INT CHECK (external_marks_obtained >= 0),  
    external_marks_total INT CHECK (external_marks_total > 0),  
    total_marks_obtained INT GENERATED ALWAYS AS (internal_marks_obtained + external_marks_obtained) STORED,  
    total_marks_max INT GENERATED ALWAYS AS (internal_marks_total + external_marks_total) STORED,
    active TINYINT DEFAULT 1,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject(id) ON DELETE CASCADE
);

-- Batch Table
CREATE TABLE IF NOT EXISTS batch (
    id INT PRIMARY KEY AUTO_INCREMENT,
    professor_id INT,
    course_id INT,
    semester INT,
    active TINYINT DEFAULT 1,
    FOREIGN KEY (professor_id) REFERENCES professor(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);