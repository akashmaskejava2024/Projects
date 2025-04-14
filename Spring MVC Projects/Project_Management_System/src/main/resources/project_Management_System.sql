
-- Create a new database
CREATE DATABASE ProjectManagement;
USE ProjectManagement;

-- Create Admin Table
CREATE TABLE Admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create Professor Table
CREATE TABLE Professor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create Student Table
CREATE TABLE Student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    professorId BIGINT NOT NULL,
    FOREIGN KEY (professorId) REFERENCES Professor(id) ON DELETE CASCADE
);

-- Create Project Table
CREATE TABLE Project (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    studentId BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (studentId) REFERENCES Student(id) ON DELETE CASCADE
);

-- Create ProjectStatus Table
CREATE TABLE ProjectStatus (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    projectId BIGINT NOT NULL,
    studentId BIGINT NOT NULL,
    phase ENUM('Requirement Gathering', 'Design', 'Development', 'Testing', 'Deployment') NOT NULL,
    uiImage VARCHAR(500), -- Store file path or URL
    updateDescription TEXT,
    feedback TEXT,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (projectId) REFERENCES Project(id) ON DELETE CASCADE,
    FOREIGN KEY (studentId) REFERENCES Student(id) ON DELETE CASCADE
);