-- ToyProject 용 계정 생성(system으로 실행)
create user toy identified by java1234;

grant connect, resource, dba to toy;

--ToyProject > ddl.sql

-- 회원
create table tblUser (
    id varchar2(50) not null,
    pw varchar2(50) not null,
    name varchar2(50) not null,
    email varchar2(100) not null,
    lv char(1) not null,
    pic varchar2(100) default 'pic.png' not null,
    intro varchar2(500) not null,
    ing char(1) default 'y' not null,
    constraint tblUser_pk primary key(id)
);

-- 게시판
create table tblBoard (
    seq number not null,
    subject varchar2(300) not null,
    content varchar2(4000) not null,
    regdate date default sysdate not null,
    readcount number default 0 not null,
    id varchar2(50) not null,
    CONSTRAINT tblboard_pk primary key(seq),
    CONSTRAINT tblboard_fk foreign key(id) references tblUser(id)
);

create sequence seqBoard;

-- 게시물 작성자 이름 뷰
create or replace view vwBoard
as
select 
    seq, subject, id, readcount,
    case
        when to_char(sysdate, 'yyyy-mm-dd') = to_char(regdate, 'yyyy-mm-dd' ) 
            then to_char(regdate, 'hh24:mi:ss')
        else
            to_char(regdate, 'yyyy-mm-dd')
    end as regdate,
    (select name from tblUser where id = tblBoard.id) as name,
    case
        when ((sysdate - regdate) / 24 / 60) < 30 then 1
        else 0
    end as isnew,
    (sysdate - regdate) as reg
from tblBoard order by seq desc;

