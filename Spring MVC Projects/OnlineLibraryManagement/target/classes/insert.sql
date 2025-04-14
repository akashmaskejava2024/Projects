INSERT INTO librarymanagment.Librarian (FirstName, LastName, ContactNumber, Email, Username, Password)
VALUES ('riya', 'Verma', '9988776655', 'riya.verma@example.com', 'root', 'root');

INSERT INTO librarymanagment.Student (FirstName, LastName, Email, className, Department, ContactNumber, Faculty)
VALUES ('Anjali', 'Patel', 'anjali.patel@example.com', '12th', 'Computer Science', '9123456789', 'Science');

INSERT INTO librarymanagment.Teacher (FirstName, LastName, Email, ContactNumber, Faculty)
VALUES ('Ravi', 'Sharma', 'ravi.sharma@example.com', '9876543210', 'Science');

INSERT INTO librarymanagment.Book (Title, Author, Language, ISBN, Publisher, PublisherCity, PublicationDate, Status, AvailableQuantity, TotalQuantity)
VALUES ('Java Programming', 'James Gosling', 'English', '978-0-123456-47-2', 'Pearson', 'New York', '2020-01-15', 'Available', 10, 10);
