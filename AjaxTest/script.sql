
-- 고양이 좌표
create table tblCat (
    seq number primary key,
    catid varchar2(50) not null,        --<img >
    x number not null,                  --top
    y number not null                   --left
);

insert into tblCat values (1, 'cat1', 0, 0);
insert into tblCat values (2, 'cat2', 0, 0);
insert into tblCat values (3, 'cat3', 0, 0);
insert into tblCat values (4, 'cat4', 0, 0);
insert into tblCat values (5, 'cat5', 0, 0);

commit;

select * from tblCat;

--ex08. Address
select * from tblAddress;

delete from tblAddress where seq=5;



