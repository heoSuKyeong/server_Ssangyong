--ToyProject > dml.sql

--회원
insert into tblUser(id, pw, name, email, lv, pic, intro, ing) values ('hahaha', '1111', '하하하', 'ha@gmail.com', '1', default, '안녕하세요. 하하하입니다.', default);

select * from tblUser;

update tblUser set pw='1111' where id = 'susukkang';
update tblUser set lv = '2' where id = 'admin';

commit;

-- 게시판
insert into tblBoard (seq, subject, content, regdate, readcount, id) values (seqBoard.nextVal, '게시판입니다.', '내용입니다.', default, default, 'susukkang');

delete from tblBoard where seq = 2;

select * from vwBoard;

update tblBoard set regdate = regdate -1 where seq <=5;

-- 게시판 - 검색
select * from vwBoard where subject like '%게시판%';

select * from vwBoard;
commit;

-- 게시판 - 페이징 >  rownum 활용
select * from (select a.*, rownum as rnum from vwBoard a) where rnum between 1 and 10;

select count(*) from tblBoard;


-- 댓글
select * from tblComment;

commit;






