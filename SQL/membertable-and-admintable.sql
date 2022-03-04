
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


-- 관리자 테이블
create table admintable (
    admin_id varchar2(20),
    admin_pwd varchar2(20) NOT NULL,
    CONSTRAINT PK_ADMINID PRIMARY KEY (admin_id)
);


-- 관리자 추가
insert into admintable values('kim1234', '12341234');
insert into admintable values('park4567', '45674567');
