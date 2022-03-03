--메뉴 테이블 생성
create table menu(
	Menu_name varchar2(30) primary key,
	Menu_count number(10) not null,
    Menu_price number(10) not null
);


create table menutable(
	Menu_name varchar2(30) primary key,
	Menu_count number(10) not null,
    Menu_price number(10) not null
);

--메뉴 추가
insert into menutable values('세븐치즈트러플', 0, 19000);
insert into menutable values('세븐후라이드', 0,  17500);
insert into menutable values('세븐허니오리지날', 0,  16000);
insert into menutable values('세븐허니콤보', 0, 20000);
insert into menutable values('세븐오리지날', 0,  16000);
insert into menutable values('세븐오리지날반반' , 0, 17000);
insert into menutable values('세븐레드오리지날', 0,  18000);
insert into menutable values('세븐레드콤보' , 0,  20000);
insert into menutable values('세븐레허반반',  0,  22000);
insert into menutable values('세븐살살치킨',  0,  17000);
insert into menutable values('세븐신화오리지날',  0,  18000);
insert into menutable values('세븐시그니처세트', 0,  30500);

insert into menutable values('세븐치즈볼', 0,  5500);
insert into menutable values('세븐치킨버거', 0,  4900);
insert into menutable values('세븐치킨카츠', 0,  6000);
insert into menutable values('세븐칠리포테이토', 0, 4000);
insert into menutable values('세븐칩카사바', 0,  1500);
insert into menutable values('세븐꽈배기', 0, 3500);
insert into menutable values('치킨무', 0, 500);
insert into menutable values('세븐웨지감자', 0, 3500);
insert into menutable values('적피클', 0, 1000);
insert into menutable values('세븐샐러드', 0, 5000);

insert into menutable values('하바네로마요소스', 0, 1000);
insert into menutable values('허니갈릭소스', 0,  1000);
insert into menutable values('레드소스', 0, 1000);
insert into menutable values('스위트칠리소스', 0, 1000);
insert into menutable values('타르타르 소스', 0, 1000);

insert into menutable values('코카콜라', 0, 2000);
insert into menutable values('스프라이트', 0, 2000);
insert into menutable values('환타', 0, 2000);
insert into menutable values('한라산맥주', 0, 4000);
insert into menutable values('허니스파클링', 0,1000);
insert into menutable values('생맥주', 0, 6000);


