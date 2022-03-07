create table login_info (
    login_no number primary key,
    login_id varchar2(20)
);

insert into login_info values(logininfo_seq.nextval, null);
