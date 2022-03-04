create table ordertable(
        order_num varchar2(10) primary key,
        order_date varchar2(20) not null,
        order_total number(8,0) not null,
        paid varchar2(6) not null
            constraint paid_check check(paid in('결제완료','결제실패'))
            );      
            
--데이터 크기 변경        alter table ordertable modify(paid varchar2(20));

insert into ordertable values('No.1', '20220310', 16000, '결제완료');
insert into ordertable values('No.2', '20220310', 22000, '결제완료');
insert into ordertable values('No.3', '20220310', 18000, '결제완료');
insert into ordertable values('No.4', '20220310', 36000, '결제완료');
insert into ordertable values('No.5', '20220310', 22500, '결제완료');
insert into ordertable values('No.6', '20220311', 19000, '결제완료');
insert into ordertable values('No.7', '20220311', 22000, '결제완료');
            

