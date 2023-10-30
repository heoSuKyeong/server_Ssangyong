--ToyProject > dml.sql

--회원
insert into tblUser(id, pw, name, email, lv, pic, intro, ing) values ('hong', '1111', '홍길동', 'hong@gmail.com', '1', default, '안녕하세요. 홍길동입니다.', default);

select * from tblUser;



commit;