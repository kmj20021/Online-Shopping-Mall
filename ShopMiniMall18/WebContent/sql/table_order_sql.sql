 --주문 테이블
     create table orderInfo
  (  num NUMBER(6) PRIMARY KEY, --주문번호 시퀀스 사용
     userid VARCHAR2(10),	--주문하는 고객 이름
     gCode varchar2(20) not null,	-- 주문 상품 번호
     gName varchar2(50) not null,	--상품 이름
     gPrice NUMBER(6) not null,
     gSize CHAR(1) not null,
     gColor VARCHAR2(10) not null,
     gAmount NUMBER(2) not null,
     gImage varchar2(20) not null,
     orderName VARCHAR2(10) NOT NULL, --배송자 이름 
     post VARCHAR2(5) NOT NULL,
     addr1 VARCHAR2(500) NOT NULL,
     addr2 VARCHAR2(500) NOT NULL,
     phone VARCHAR2(11) NOT NULL,
     payMethod VARCHAR2(30) NOT NULL,
     orderDay DATE  DEFAULT SYSDATE
  );   
  alter table orderInfo	
  add CONSTRAINT orderInfo_userid_fk FOREIGN KEY(userid)	--참조키
   REFERENCES member(userid) ON DELETE CASCADE;
   
   
  alter table orderInfo
  add CONSTRAINT orderInfo_gCode_fk FOREIGN KEY(gCode)	--참조키
   REFERENCES goods(gCode) ON DELETE CASCADE; 
   
   create sequence orderInfo_seq;   