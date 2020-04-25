create table student(stu_id number primary key,stu_name varchar2(25) not null,dept varchar2(10) not null,yer number not null,phn_no number not null);

create table book(b_id number primary key,b_name varchar2(40) not null,author varchar(25) not null,sec varchar(20) not null,copies_a number not null);

create table lb(stud_id number,book_id number,out_d date,r_date date);

create table login(user_id number primary key,pass varchar(25) not null,name varchar2(25) not null);

alter table lb add foreign key(stud_id) references student(stu_id);

alter table lb add foreign key(book_id) references book(b_id);

desc student;

desc book;

desc lb;

desc login;

insert into student values(101,'san','cse',2,6382878078);
insert into student values(102,'vel','cse',2,6382878078);
insert into student values(103,'reg','it',2,6382878078);
insert into student values(104,'sam','cse',2,6382878078);
insert into student values(105,'pra','civil',2,6382878078);
insert into student values(106,'skg','eee',2,6382878078);
insert into student values(107,'selvm','eee',1,6382878078);
insert into student values(108,'sanjy','cse',4,6382878078);
insert into student values(109,'praveen','it',3,6382878078);
insert into student values(110,'sri','cse',1,6382878078);
insert into student values(111,'praba','civil',2,6382878078);
insert into student values(112,'san','eee',4,6382878078);


select * from student;



insert into book values(1,'Easy way to learn python','Eric Matthe','cse',5);
insert into book values(2,'opertaing system','Silberschatz','cse',5);
insert into book values(3,'The complete references Java','Marima c brown','cse',7);
insert into book values(4,'The Anscii C','Brain Kernighan','cse',2);
insert into book values(5,'Computer Organization','safwat G','it',4);
insert into book values(6,'Principle of complier design','Alfred Aho','it',3);
insert into book values(7,'Database System Concepts','Avi Silberschatz','it',4);
insert into book values(8,'Civil Engineering','J.K.Gupta','civil',1);
insert into book values(9,'Engineering Hydrology','K.Subramani','civil',3);
insert into book values(10,'Estimating and costing','B.N.Dutta','civil',5);
insert into book values(11,'Engeering Electromagnetics','John Buck','eee',2);
insert into book values(12,'Objective Electrical Technolgy','Eric Matthe','eee',1);

select * from book;

select * from book where  b_id = 1;
select * from book where  sec = 'cse';
select * from book where  b_name like '%The%';
select * from book where  author like '%K%';

insert into lb(stud_id,book_id) values(106,12);
insert into lb(stud_id,book_id) values(106,11);
insert into lb(stud_id,book_id,out_d) values(102,2,sysdate);

  
select sysdate from dual;


select * from login;

select * from login where user_id = 1001 and pass = '12345';

insert into login values(1001,'12345','san');
insert into login values(1002,'67890','vel');
