-- 1. meeting table
CREATE TABLE IF NOT EXISTS meeting (
    meeting_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    meeting_name VARCHAR(100) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    location VARCHAR(255),
    description VARCHAR(255),
    status VARCHAR(50) DEFAULT 'active',
    host_id INT NOT NULL,
    CONSTRAINT meeting__status_constraint CHECK (status IN ('active', 'finished'))
);

-- 2. Attendee table
CREATE TABLE IF NOT EXISTS attendee (
    attendee_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL
);

-- 3. Poll table
CREATE TABLE IF NOT EXISTS poll (
    poll_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(100) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    total_votes INT NOT NULL DEFAULT 0,
    meeting_id INT NOT NULL
    CONSTRAINT poll__status_constraint CHECK (status IN ('created', 'active', 'finished'))
);

-- 4. Poll Option table
CREATE TABLE IF NOT EXISTS poll_option (
    poll_option_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    text VARCHAR(100) NOT NULL,
    num_of_votes INT DEFAULT 0,
    poll_id INT NOT NULL
);

-- 5. Poll Vote table
CREATE TABLE IF NOT EXISTS poll_vote (
     poll_vote_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     vote_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     attendee_id INT NOT NULL,
     poll_option_id INT NOT NULL
);

-- 6. meeting Attendance table
CREATE TABLE IF NOT EXISTS meeting_attendance (
     meeting_id INT NOT NULL,
     attendee_id INT NOT NULL
);

-- 7. meeting Speech Requests table
CREATE TABLE IF NOT EXISTS meeting_speech (
     id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     meeting_id INT NOT NULL,
     attendee_id INT NOT NULL,
     request_time TIMESTAMP NOT NULL
);

-- Foreign Key Constraints

-- meeting
ALTER TABLE meeting
ADD CONSTRAINT fk_meeting_host
FOREIGN KEY (host_id) REFERENCES attendee(attendee_id);

-- poll
ALTER TABLE poll
ADD CONSTRAINT fk_poll_meeting
FOREIGN KEY (meeting_id) REFERENCES meeting(meeting_id);

-- poll_option
ALTER TABLE poll_option
ADD CONSTRAINT fk_poll_option_poll
FOREIGN KEY (poll_id) REFERENCES poll(poll_id);

-- poll_vote
ALTER TABLE poll_vote
ADD CONSTRAINT fk_vote_attendee
FOREIGN KEY (attendee_id) REFERENCES attendee(attendee_id),
ADD CONSTRAINT fk_vote_option
FOREIGN KEY (poll_option_id) REFERENCES poll_option(poll_option_id);

-- meeting_attendance
ALTER TABLE meeting_attendance
ADD CONSTRAINT fk_attendance_attendee
FOREIGN KEY (attendee_id) REFERENCES attendee(attendee_id),
ADD CONSTRAINT fk_attendance_meeting
FOREIGN KEY (meeting_id) REFERENCES meeting(meeting_id);

-- meeting_speech
ALTER TABLE meeting_speech
ADD CONSTRAINT fk_speech_attendee
FOREIGN KEY (attendee_id) REFERENCES attendee(attendee_id),
ADD CONSTRAINT fk_speech_meeting
FOREIGN KEY (meeting_id) REFERENCES meeting(meeting_id);
