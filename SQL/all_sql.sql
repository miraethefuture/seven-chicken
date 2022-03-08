-- 관리자 테이블 생성
create table admintable (
    admin_id varchar2(20),
    admin_pwd varchar2(20) NOT NULL,
    CONSTRAINT PK_ADMINID PRIMARY KEY (admin_id)
);


-- 관리자 추가
insert into admintable values('kim1234', '12341234');
insert into admintable values('park4567', '45674567');


create table login_info (
    login_no number primary key,
    login_id varchar2(20)
    login_date varchar2(20) not null,
);


-- 로그인 시퀀스 생성
create sequence logininfo_seq
start with 1
increment by 1;

-- (임의의 데이터 입력)
insert into login_info values(logininfo_seq.nextval, null, sysdate || ' ' || TO_CHAR(SYSDATE, 'HH24:MI:SS'));


-- 멤버 테이블 생성
CREATE TABLE membertable (
    mem_no NUMBER,
    mem_name VARCHAR2(20),
    mem_id VARCHAR2(20) ,
    mem_pwd VARCHAR2(20) NOT NULL,
    mem_point NUMBER,
    mem_addr VARCHAR2(30),
    mem_phone VARCHAR2(20),
    CONSTRAINT PK_MEMID PRIMARY KEY (mem_id)

    );


-- 멤버 시퀀스 생성
create sequence member_seq
  start with 1
  increment by 1;


-- 멤버 추가
insert into membertable values(member_seq.nextval, '박명수', 'great_park', '1234a', 300, '서울시 마포구', '010-1111-2222');
insert into membertable values(member_seq.nextval, '유재석', 'dduck77', '1234b', 400, '서울시 강남구', '010-3333-4444');
insert into membertable values(member_seq.nextval, '정준하', 'junha0000', '1234c', 700, '부산시 동래구', '010-5555-6666');
insert into membertable values(member_seq.nextval, '하동훈', 'hahaha', '1234d', 400, '인천시 남동구', '010-7777-8888');
insert into membertable values(member_seq.nextval, '장도연', 'jang11', '1234e', 250, '서울시 강서구', '010-9999-0101');
insert into membertable values(member_seq.nextval, '박지윤', 'jiyoon3', '1234f', 550, '경기도 이천시', '010-0102-0203');
insert into membertable values(member_seq.nextval, '전현무', 'lucifer', '1234g', 650, '경기도 광주시', '010-0405-0607');
insert into membertable values(member_seq.nextval, '박나래', 'naraebar', '1234h', 330, '서울시 마포구', '010-0605-0101');
insert into membertable values(member_seq.nextval, '이광수', 'girin33', '1234i', 100, '서울시 마포구', '010-1122-2233');
insert into membertable values(member_seq.nextval, '지석진', 'zzzzz', '1234j', 50, '경기도 평택시', '010-4455-6677');


-- 메뉴테이블 생성
create table menutable(
   Menu_name varchar2(30) primary key,
   Menu_price number(10) not null,
   Menu_count number(10) not null
);


-- 메뉴 추가
insert into menutable values('세븐치즈트러플', 19000, 0);
insert into menutable values('세븐후라이드', 17500, 0);
insert into menutable values('세븐허니오리지날', 16000, 0);
insert into menutable values('세븐허니콤보', 20000, 0);
insert into menutable values('세븐오리지날', 16000, 0);
insert into menutable values('세븐오리지날반반' , 17000, 0);
insert into menutable values('세븐레드오리지날', 17000, 0);
insert into menutable values('세븐레드콤보' , 20000, 0);
insert into menutable values('세븐레허반반', 22000, 0);
insert into menutable values('세븐살살치킨', 17000, 0);
insert into menutable values('세븐신화오리지날', 18000, 0);
insert into menutable values('세븐시그니처세트', 27500, 0);

insert into menutable values('세븐치즈볼', 5500, 0);
insert into menutable values('세븐치킨버거', 4900, 0);
insert into menutable values('세븐치킨카츠', 6000, 0);
insert into menutable values('세븐칠리포테이토', 4000, 0);
insert into menutable values('세븐칩카사바', 1500, 0);
insert into menutable values('세븐꽈배기', 3500, 0);
insert into menutable values('치킨무', 500, 0);
insert into menutable values('세븐웨지감자', 3500, 0);
insert into menutable values('적피클', 1000, 0);
insert into menutable values('세븐샐러드', 5000, 0);

insert into menutable values('하바네로마요소스', 1000, 0);
insert into menutable values('허니갈릭소스', 1000, 0);
insert into menutable values('레드소스', 1000, 0);
insert into menutable values('스위트칠리소스', 1000, 0);
insert into menutable values('타르타르소스', 1000, 0);

insert into menutable values('코카콜라', 2000, 0);
insert into menutable values('스프라이트', 2000, 0);
insert into menutable values('환타', 2000, 0);
insert into menutable values('한라산맥주', 11000, 0);
insert into menutable values('허니스파클링', 3000, 0);
insert into menutable values('생맥주', 7000, 0);


-- 주문 테이블 생성
create table ordertable(
        order_num varchar2(10) primary key,
        order_date varchar2(20) not null,
        order_total number(8,0) not null,
        paid varchar2(20) not null
            constraint paid_check check(paid in('결제완료','결제실패','대기중'))
            );


--데이터 크기 변경        alter table ordertable modify(paid varchar2(20));


-- 주문 시퀀스 생성
create sequence order_seq
start with 1
increment by 1;


-- 임의의 값 입력
insert into ordertable values(order_seq.nextval, sysdate, 47500, '대기중');

insert into ordertable values(order_seq.nextval, sysdate, 16000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 22000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 18000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 36000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 22500, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 19000, '결제완료');
insert into ordertable values(order_seq.nextval, sysdate, 22000, '결제완료');
