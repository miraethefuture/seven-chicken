--메뉴테이블 생성
create table menutable(
   Menu_name varchar2(30) primary key,
   Menu_price number(10) not null,
   Menu_count number(10) not null
);

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
