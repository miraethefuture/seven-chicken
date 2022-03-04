-- 관리자 테이블
create table admintable (
    admin_id varchar2(20),
    admin_pwd varchar2(20) NOT NULL,
    CONSTRAINT PK_ADMINID PRIMARY KEY (admin_id)
);


-- 관리자 추가
insert into admintable values('kim1234', '12341234');
insert into admintable values('park4567', '45674567');
