CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20),
  storagefoldername VARCHAR
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(50),
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

INSERT INTO USERS (username,salt,password,firstname,lastname,storagefoldername) VALUES
('thanhnhan3395','uCv7NPENHAnRoGXX/zrg2g==','hehYFfMnU2ywA7xNibCAQw==','Nhan','Thanh Tran','thanhnhan3395'),
('loganfox4273','KGkGXL+GseEyjrw98dMjMA==','sk5gXLBP39jaquU0POA6Mg==','Logan','Fox','loganfox4273'),
('geraldaustin111','wHJ1sSBxGiz9JnMXnBmOAg==','3vQ3YKj1p0h+ywPOfQv0xw==','Gerald','Austin','geraldaustin111'),
('marcianichols4792','Kuvrpw05GlT67cmkVKNmjg==','G/XMZiczSJos0ivGNLbMzQ==','Marcia','Nichols','marcianichols4792');

INSERT INTO NOTES (notetitle,notedescription,userid) VALUES
('Example Note for Nhan','Hello Nhan, thank you for visiting this page. This is a short example of a note that you can store.',1),
('Example Note for Logan','Hello Logan, thank you for visiting this page. This is a short example of a note that you can store.',2),
('Example Note for Gerald','Hello Gerald, thank you for visiting this page. This is a short example of a note that you can store.',3),
('Example Note for Marcia','Hello Marcia, thank you for visiting this page. This is a short example of a note that you can store.',4);

INSERT INTO CREDENTIALS (url,username,key,password,userid) VALUES
('https://brse-sharing.nhanthanhtran.com/','nhanthanhtran030395@gmail.com','Snt4vrMionVN9jnxjlarHw==','vr53olHCL/Nv9c8IgqOuvYan2mQCQW4wk7+JyX6mHzE=',1),
('https://randomuser.me/','logan.fox@example.com','sL/Nc71kdYxLzi5B4gXsnQ==','6PVo/g8XY/47oxdCdLWANQ==',2),
('https://randomuser.me/','gerald.austin@example.com','h0gFGPZJ/WckzPSIG+aXoA==','qlGwiL/dwWXcsesoZ+RaBA==',3),
('https://randomuser.me/','marcia.nichols@example.com','syEQvvZpDGb5GuELLztNrA==','FYBUy1fzR1/ehPfu2G8/bg==',4);
