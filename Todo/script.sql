
-- 할일 테이블
drop table tblTodo;
drop sequence seqTodo;

create table tblTodo (
    seq number primary key,                 --번호(PK)
    todo varchar2(1000) not null,           --할일
    state char(1) default 'n' not null,     --미완료(n), 완료(y)
    regdate date default sysdate not null
);

create sequence seqTodo;


insert into tblTodo(seq, todo, state, regdate) values (seqTodo.nextVal, '첫번째 할일', default, default);

select * from tblTodo order by seq desc;

select * from tblTodo order by state asc, seq desc;


update tblTodo set state = 'y' where seq = 6;

delete from tblTodo where seq = 1;

commit;