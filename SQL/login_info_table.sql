create table login_info (
    login_no number primary key,
    login_id varchar2(20)
);

create sequence logininfo_seq
start with 1
increment by 1;

insert into login_info values(logininfo_seq.nextval, null);
