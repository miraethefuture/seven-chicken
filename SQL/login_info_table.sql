create table login_info (
    login_no number primary key,
    login_id varchar2(20)
    login_date varchar2(20) not null,
);

create sequence logininfo_seq
start with 1
increment by 1;

insert into login_info values(logininfo_seq.nextval, null, sysdate || ' ' || TO_CHAR(SYSDATE, 'HH24:MI:SS'));
