ALTER TABLE attendee
ADD COLUMN email VARCHAR(100) UNIQUE ,
ADD COLUMN password VARCHAR(100);

UPDATE attendee SET email = 'alice@example.com', password = 'password123' WHERE name = 'Alice Johnson';
UPDATE attendee SET email = 'bob@example.com', password = 'password123' WHERE name = 'Bob Smith';
UPDATE attendee SET email = 'carol@example.com', password = 'password123' WHERE name = 'Carol Lee';
UPDATE attendee SET email = 'david@example.com', password = 'password123' WHERE name = 'David Kim';
UPDATE attendee SET email = 'emma@example.com', password = 'password123' WHERE name = 'Emma Davis';
UPDATE attendee SET email = 'frank@example.com', password = 'password123' WHERE name = 'Frank Moore';
UPDATE attendee SET email = 'grace@example.com', password = 'password123' WHERE name = 'Grace Hill';
UPDATE attendee SET email = 'henry@example.com', password = 'password123' WHERE name = 'Henry Turner';
UPDATE attendee SET email = 'ivy@example.com', password = 'password123' WHERE name = 'Ivy Scott';
UPDATE attendee SET email = 'jack@example.com', password = 'password123' WHERE name = 'Jack Lewis';

ALTER TABLE attendee
ALTER COLUMN email SET NOT NULL,
ALTER COLUMN password SET NOT NULL;