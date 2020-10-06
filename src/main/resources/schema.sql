CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR(1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileid INT PRIMARY KEY auto_increment,
    filename VARCHAR,
    contenttype VARCHAR,
    filesize VARCHAR,
    userid INT,
--    filedata BLOB,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialid INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    userid INT,
    foreign key (userid) references USERS(userid)
);

INSERT INTO USERS (username,salt,password,firstname,lastname) VALUES
('thanhnhan3395','uCv7NPENHAnRoGXX/zrg2g==','hehYFfMnU2ywA7xNibCAQw==','Nhan','Thanh Tran');

INSERT INTO NOTES (notetitle,notedescription,userid) VALUES
('Example Note Title','Example Note Description',1),
('Meeting at 10AM','Attend the internal meeting at 10AM. This is an online meeting',1);

INSERT INTO CREDENTIALS (url,username,key,password,userid) VALUES
('https://brse-sharing.nhanthanhtran.com/','nhanthanhtran030395@gmail.com','Snt4vrMionVN9jnxjlarHw==','vr53olHCL/Nv9c8IgqOuvYan2mQCQW4wk7+JyX6mHzE=',1);

INSERT INTO FILES (filename,contenttype,filesize,userid) VALUES
('example.txt','text','1kb',1)