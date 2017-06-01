INSERT INTO user(dtype, id,last_name, name,password,role,user_name,jmbg,address,date_of_birth,gender,picture_path,title) VALUES ('student', '1', 'peric', 'pera', '123', 'da', 'perica', 'da', 'da', '2008-10-03 22:59:52', 'male', 'da', null);
INSERT INTO user(dtype, id,last_name, name,password,role,user_name,jmbg,address,date_of_birth,gender,picture_path,title) VALUES ('professor', '2', 'jovo', 'jovic', '123', 'da', 'jovica', 'da', 'da', '2008-10-03 22:59:52', 'male', 'da', 'da');
INSERT INTO transaction (id, bank_account,price, purpose, recipient, student_id) VALUES ('1', '2222222', '2000', 'dada', 'dede', '1');
INSERT INTO document (id, name, path,student_id) VALUES ('1', 'indeks', 'aha', '1');
INSERT INTO exam (id,pass,points,student_id) VALUES ('1', true, '90', '1');
INSERT INTO subject (id,name,semester) VALUES ('1', 'Engleski', '1');
INSERT INTO obligation(id,date_of_obligation,oblitagion_type,points,subject_id,exam_id) VALUES ('1', '2008-10-03 22:59:52', 'kolokvijum', '30', '1','1');
INSERT INTO professor_role(id,role,professor_id,subject_id) VALUES ('1', 'rola', '1', '1');
INSERT INTO user_subjects (students_id,subjects_id) VALUES ('1', '1');
