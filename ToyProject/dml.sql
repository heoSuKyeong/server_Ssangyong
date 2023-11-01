--ToyProject > dml.sql

--회원
insert into tblUser(id, pw, name, email, lv, pic, intro, ing) values ('hahaha', '1111', '하하하', 'ha@gmail.com', '1', default, '안녕하세요. 하하하입니다.', default);
insert into tblUser(id, pw, name, email, lv, pic, intro, ing) values ('susukkang', '1111', '수수깡', 'susukkang@gmail.com', '1', default, '안녕하세요. 수수깡데스입니다.', default);

select * from tblUser;

update tblUser set ing = 'y' where id = 'susu';

commit;

-- 게시판
insert into tblBoard (seq, subject, content, regdate, readcount, id) values (seqBoard.nextVal, '게시판입니다.', '내용입니다.', default, default, 'susukkang');

select 
    seq, subject, id, readcount, regdate ,
    (select name from tblUser where id = tblBoard.id) as name    
from tblBoard order by seq desc;

delete from tblBoard where seq = 2;


update tblBoard set regdate = regdate -1 where seq <=6;


commit;