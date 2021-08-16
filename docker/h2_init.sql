create user if not exists moan password 'abcstrng!5' admin;
CREATE TABLE patient (id integer auto_increment, created_dt_tm TIMESTAMP default CURRENT_TIMESTAMP, updated_dt_tm TIMESTAMP default CURRENT_TIMESTAMP, family_name varchar(255) NOT NULL, name varchar(255) NOT NULL, gender ENUM('male', 'female') default 'male', date_of_birth DATE default NULL, PRIMARY KEY (id));
INSERT INTO patient VALUES 
(1, '2021-08-05 10:00:00', '2021-08-05 10:00:00', 'Yonson', 'Yon', 'male', '1986-01-27'),
(2, '2021-08-05 10:30:00', '2021-08-05 10:30:00', 'Lubson', 'Lub', 'male', '1980-10-15'),
(3, '2021-08-05 11:00:00', '2021-08-05 11:00:00', 'Izumson', 'Izum', 'male', '1987-05-05'),
(4, '2021-08-05 11:30:00', '2021-08-05 11:30:00', 'Kaisen', 'Kai', 'male', '1982-07-17'),
(5, '2021-08-05 12:00:00', '2021-08-05 12:00:00', 'Tedson', 'Ted', 'male', '1987-08-18'),
(6, '2021-08-05 12:30:00', '2021-08-05 12:30:00', 'Yonson', 'El', 'male', '2017-10-07'),
(7, '2021-08-05 13:00:00', '2021-08-05 13:00:00', 'Kaisen', 'Zed', 'male', '2021-05-12');
CREATE TABLE attendance (id integer auto_increment, created_dt_tm TIMESTAMP default CURRENT_TIMESTAMP, updated_dt_tm TIMESTAMP default CURRENT_TIMESTAMP, reason CLOB NOT NULL, diagnosis CLOB default NULL, PRIMARY KEY (id));
INSERT INTO attendance VALUES 
(1, '2021-08-06 11:00:00', '2021-08-06 11:00:00', 'Dental service', 'Caries'),
(2, '2021-08-06 11:30:00', '2021-08-06 11:30:00', 'Dental service', 'Caries'),
(3, '2021-08-06 10:00:00', '2021-08-06 10:00:00', 'Routine psychologist consultation', '-'),
(4, '2021-08-06 11:00:00', '2021-08-06 11:00:00', 'Eye pain', 'Conjunctivitis'),
(5, '2021-08-06 11:30:00', '2021-08-06 11:30:00', 'Backache', 'Neuralgia'),
(6, '2021-08-07 10:00:00', '2021-08-07 10:00:00', 'Torticollis', 'Congenital torticollis'),
(7, '2021-08-07 10:00:00', '2021-08-07 10:00:00', 'Routine check', 'Patronage'),
(8, '2021-08-07 10:30:00', '2021-08-07 10:30:00', 'Back massage', 'Neuralgia');
CREATE TABLE patients_attendances (patient_id integer NOT NULL, attendance_id integer NOT NULL, PRIMARY KEY (patient_id, attendance_id));
INSERT INTO patients_attendances VALUES 
(1, 1), (6, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(1, 6), (6, 6),
(4, 7), (7, 7),
(5, 8);
CREATE TABLE attendances_services (attendance_id integer NOT NULL, service_id integer NOT NULL, count int(11) NOT NULL default 1, PRIMARY KEY (attendance_id, service_id));
INSERT INTO attendances_services VALUES 
(1, 23, 1),
(1, 24, 1),
(1, 25, 1),
(2, 23, 1),
(3,  3, 1),
(4, 21, 1),
(5,  8, 1),
(6, 30, 1),
(7, 15, 1),
(8, 29, 1);
CREATE TABLE service (id integer auto_increment, created_dt_tm TIMESTAMP default CURRENT_TIMESTAMP, updated_dt_tm TIMESTAMP default CURRENT_TIMESTAMP, parent_id integer default NULL, name varchar(255) NOT NULL, description varchar(600) DEFAULT NULL, PRIMARY KEY (id));
INSERT INTO service VALUES 
(  1, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Psychology', 'is an integration of science, theory, and clinical knowledge for the purpose of understanding, preventing, and relieving psychologically-based distress or dysfunction and to promote subjective well-being and personal development.[1][2] Central to its practice are psychological assessment, clinical formulation, and psychotherapy, although clinical psychologists also engage in research, teaching, consultation, forensic testimony, and program development and administration.[3] In many countries, clinical psychology is a regulated mental health profession.'),
(  2, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 1,    'Psychiatrist, consultation', 'is a doctor who treats mental illness. He works with patients who have disorders of the emotional-volitional system (for example, schizophrenia), intellectual development (mental retardation), etc.'),
(  3, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 1,    'Psychologist, consultation', 'The psychologist works with mentally healthy people who need counseling or therapy in order to eliminate anxiety, neurotic experiences and other problems. Psychologists use in their work such techniques as NLP, transactional analysis, psychosynthesis, gestalt therapy, etc.'),
(  4, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Psychotherapy', 'Psychotherapy (also psychological therapy or talking therapy) is the use of psychological methods, particularly when based on regular personal interaction with adults, to help a person change behavior and overcome problems in desired ways. Psychotherapy aims to improve an individual''s well-being and mental health, to resolve or mitigate troublesome behaviors, beliefs, compulsions, thoughts, or emotions, and to improve relationships and social skills.'),
(  5, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 4,    'Psychotherapist, consultation', 'A psychotherapist is a specialist with a higher medical or psychological education with additional qualifications in the field of psychotherapy. He uses psychotherapeutic methods in his work: conversation, psychological games, coding, etc. In addition, a psychotherapist may resort to medication.'),
(  6, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 5,    'Psychoanalyst, consultation', 'is a psychotherapist who works with clients using psychoanalysis methods: through the interpretation of dreams, penetration into the subconscious, etc.'),
(  7, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Neurology', 'Neurology (from Greek: νεῦρον (neûron), "string, nerve" and the suffix -logia, "study of") is a branch of medicine dealing with disorders of the nervous system. Neurology deals with the diagnosis and treatment of all categories of conditions and disease involving the central and peripheral nervous systems (and their subdivisions, the autonomic and somatic nervous systems), including their coverings, blood vessels, and all effector tissue, such as muscle. Neurological practice relies heavily on the field of neuroscience, the scientific study of the nervous system.'),
(  8, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 7,    'Neurologist, consultation', ''),
(  9, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Gynecology', ''),
( 10, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 9,    'Gynecologist, consultation', ''),
( 11, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 9,    'Midwifery', 'Midwifery is the health science and health profession that deals with pregnancy, childbirth, and the postpartum period (including care of the newborn), in addition to the sexual and reproductive health of women throughout their lives.'),
( 12, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 11,   'Midwifery, consultation', ''),
( 13, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Therapy', ''),
( 14, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 13,   'Family therapist, consultation', 'a specialist in the field of therapy; a doctor specializing in the detection, treatment, prevention of internal diseases.'),
( 15, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 43,   'Pediatrician, consultation', ''),
( 16, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Gastroenterology', ''),
( 17, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 16,   'Gastroenterologist, consultation', ''),
( 18, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Otolaryngology', ''),
( 19, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 18,   'Otolaryngology, consultation', ''),
( 20, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Ophthalmology', ''),
( 21, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 20,   'Ophthalmologist, consultation', ''),
( 22, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Dentistry', ''),
( 23, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 22,   'Dentist, consultation', ''),
( 24, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 22,   'Caries treatment', ''),
( 25, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 22,   'Anesthesia', ''),
( 26, '2021-01-01 10:00:00', '2021-01-01 10:00:00', NULL, 'Massage', ''),
( 27, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 26,   'Masseur, consultation', ''),
( 28, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 26,   'Neck massage', ''),
( 29, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 26,   'Back massage, along the ridge', ''),
( 30, '2021-01-01 10:00:00', '2021-01-01 10:00:00', 26,   'Children''s massage of torticollis', '');