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
    seq, subject, id, readcount, content,
    case
        when to_char(sysdate, 'yyyy-mm-dd') = to_char(regdate, 'yyyy-mm-dd' ) 
            then to_char(regdate, 'hh24:mi:ss')
        else
            to_char(regdate, 'yyyy-mm-dd')
    end as regdate,
    (select name from tblUser where id = tblBoard.id) as name,
    case
        when (sysdate - regdate) <1 then 1
        else 0
    end as isnew,
    (select count(*) from tblComment where bseq=tblBoard.seq) as ccnt    
from tblBoard order by seq desc;


-- 댓글
create table tblComment (
    seq number not null,
    content varchar2(1000) not null,
    regdate date default sysdate not null,
    id varchar2(50) not null,                   -- ID
    bseq number not null,                       -- 부모 글번호
    
    constraint tblcomment_pk primary key(seq),
    CONSTRAINT tblboard_fk_id foreign key(id) references tblUser(id),
    CONSTRAINT tblboard_fk_bseq foreign key(bseq) references tblBoard(seq)
);

create sequence seqComment;
commit;



-- 마커 저장 테이블
create table tblMarker (
    seq number primary key,     -- PK
    lat number not null,        -- 위도
    lng number not null         -- 경도
);

create sequence seqMarker;

commit;


-- 장소 테이블
create table tblPlace (
    seq number primary key,     -- PK
    lat number not null,        -- 위도
    lng number not null,         -- 경도
    name varchar2(100) not null,        -- 장소명
    category varchar2(100) default 'default' not null       -- 장소분류
);

create sequence seqPlace;



