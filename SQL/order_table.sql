create table ordertable(
        order_num varchar2(10) primary key,
        order_date varchar2(20) not null,
        order_total number(8,0) not null,
        paid varchar2(20) not null
            constraint paid_check check(paid in('결제완료','결제실패','대기중'))
            );

--데이터 크기 변경        alter table ordertable modify(paid varchar2(20));

create sequence order_seq
start with 1
increment by 1;

insert into ordertable values(order_seq.nextval, sysdate, 47500, '대기중');

insert into ordertable values(order_seq.nextval, sysdate, 16000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 22000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 18000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 36000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 22500, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 19000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 22000, '결제완료');
