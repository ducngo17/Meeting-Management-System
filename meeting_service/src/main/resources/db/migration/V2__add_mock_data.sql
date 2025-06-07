INSERT INTO attendee (name) VALUES
('Alice Johnson'),
('Bob Smith'),
('Carol Lee'),
('David Kim'),
('Emma Davis'),
('Frank Moore'),
('Grace Hill'),
('Henry Turner'),
('Ivy Scott'),
('Jack Lewis');

INSERT INTO meeting (meeting_name, start_time, end_time, location, description, host_id) VALUES
('Tech Innovations 2025', '2025-06-01 09:00', '2025-06-01 17:00', 'Hall A', 'Tech trends.', 1),
('Green Future Forum', '2025-06-15 10:00', '2025-06-15 16:00', 'Hall B', 'Sustainability in tech.', 2),
('AI and Society', '2025-07-10 09:00', '2025-07-10 17:00', 'Hall C', 'Impacts of AI.', 3),
('Startup Connect', '2025-08-01 10:00', '2025-08-01 16:00', 'Main Auditorium', 'Startup networking.', 4),
('Developer Fest', '2025-09-01 09:00', '2025-09-01 17:00', 'Room 101', 'Developer tools.', 5);

INSERT INTO poll (title, start_time, end_time, status, total_votes, meeting_id) VALUES
('Priority Feature', '2025-06-01 10:00', '2025-06-01 12:00', 'active', 2, 1),
('Investment Strategy', '2025-06-15 11:00', '2025-06-15 13:00', 'finished', 2, 2),
('AI Policy Direction', '2025-07-10 13:00', '2025-07-10 14:00', 'active', 0, 3),
('Pitch Format', '2025-08-01 11:00', '2025-08-01 12:00', 'active', 0,4),
('Favorite Framework', '2025-09-01 13:00', '2025-09-01 14:00', 'active', 0,5),
('Analytics Tools', '2025-10-01 14:00', '2025-10-01 15:00', 'active', 0,1),
('Best Cloud Provider', '2025-11-01 12:00', '2025-11-01 13:00', 'finished', 0,1),
('Threat Focus', '2025-12-01 10:30', '2025-12-01 11:30', 'active', 0,2);

INSERT INTO poll_option (text, poll_id, num_of_votes) VALUES
('AI Features', 1, 1),
('Cloud Scalability', 1, 0),
('Data Privacy', 1, 1),
('User Interface', 1, 0),
('Green Investments', 2, 2),
('R&D Spending', 2, 0),
('Carbon Neutrality Goals', 2, 0),
('Ethical AI', 3, 0),
('Open AI Policy', 3, 0),
('AI for Good', 3, 0),
('Regulatory Compliance', 3, 0),
('Pitch Decks', 4, 0),
('Live Demos', 4, 0),
('Startup Videos', 4, 0),
('React', 5, 0),
('Vue', 5, 0),
('Svelte', 5, 0),
('Angular', 5, 0),
('Power BI', 6, 0),
('Tableau', 6, 0),
('Looker', 6, 0),
('AWS', 7, 0),
('Azure', 7, 0),
('Google Cloud', 7, 0),
('IBM Cloud', 7, 0),
('Malware', 8, 0),
('Phishing', 8, 0),
('Ransomware', 8, 0),
('Insider Threats', 8, 0);

INSERT INTO poll_vote (attendee_id, poll_option_id) VALUES
(3, 3),
(4, 5),
(5, 5),
(10, 1);

INSERT INTO meeting_attendance (meeting_id, attendee_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 10),
(2, 4),
(2, 5),
(3, 6),
(4, 7),
(4, 8),
(5, 9),
(5, 10);

INSERT INTO meeting_speech (meeting_id, attendee_id, request_time)
VALUES
(1, 3, '2025-06-01 10:15'),
(2, 5, '2025-06-15 11:45');
