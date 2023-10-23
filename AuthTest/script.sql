-- AuthTest > script.sql

drop table tblBonus;
drop table tblUser:

select * from tblUser;

create table tblUser (
    id varchar2(30) primary key,    -- 아이디(PK)
    pw varchar2(30) not null,       -- 암호
    name varchar2(30) not null,      -- 이름
    lv number(1) not null           -- 등급(1-일반, 2-관리자)
);

insert into tblUser values ('hong', '1111', '홍길동', 1);
insert into tblUser values ('test', '1111', '테스트', 1);
insert into tblUser values ('admin', '1111', '관리자', 2);

select * from tblUser where id = 'hong' and pw = '1111';

commit;