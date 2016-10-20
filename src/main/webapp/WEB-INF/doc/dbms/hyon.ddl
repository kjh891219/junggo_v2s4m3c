/*************************************
 *  Table Name: ȸ�� 
*/
drop table member_test;

CREATE TABLE member_test(
    userid                            VARCHAR2(20)     NOT NULL PRIMARY KEY,
    mno                               NUMBER(6)        NOT NULL UNIQUE,
    pwd                               VARCHAR2(30)     NOT NULL,
    name                              VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL UNIQUE,
    tel                               VARCHAR2(14)     NOT NULL,
    zipcode                           VARCHAR2(5)          NULL,
    address1                          VARCHAR2(80)         NULL,
    address2                          VARCHAR2(50)         NULL,
    email                             VARCHAR2(100)    NOT NULL UNIQUE,
    mdate                             DATE             NOT NULL,
    auth                              VARCHAR2(20)     DEFAULT 'N'   NOT NULL,
    confirm                           CHAR(1)    DEFAULT 'N'     NOT NULL, -- �̸��� ��ũ Ŭ�� ����, Y:Ŭ��, N:Ŭ������
    act                               CHAR(1)    NOT NULL,  -- M: ������, Y: �α��� ����, N: �α��� �Ұ�, H: ���� ���
    droupout                          VARCHAR2(1)    DEFAULT 'N'     NOT NULL
);


INSERT INTO member_test(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('master', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member_test), '1234', '������', '������', 'abc@mail.com', '000-0000-0000', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N')






/**********************************/
/* Table Name: ī�޶� */
/**********************************/
drop table camera;

CREATE TABLE CAMERA(
		ctno                          		NUMBER(6)		 NOT NULL		 PRIMARY KEY,
		category                      		VARCHAR2(20)		 NOT NULL,
		nickname                      		VARCHAR2(20)		 NOT NULL,
		passwd                        		VARCHAR2(10)		 NOT NULL,
		deal_way                      		VARCHAR2(20)		 NOT NULL,
		deal_code                     		VARCHAR2(20)		 NOT NULL,
		product_code                  		VARCHAR2(20)		 NOT NULL,
		hprice                        		NUMBER(15)		 DEFAULT 0		 NOT NULL,
		region                        		VARCHAR2(20)		 DEFAULT ''		 NOT NULL,
		tel                           		VARCHAR2(14)		 DEFAULT ''		 NOT NULL,
		email                         		VARCHAR2(100)		 DEFAULT ''		 NOT NULL,
		quantity                      		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		title                         		VARCHAR2(200)		 DEFAULT ''		 NOT NULL,
		content                       		VARCHAR2(4000)		 NOT NULL,
		purc_date                     		VARCHAR2(20)		 DEFAULT ''		 NOT NULL,
		wdate                         		DATE		 DEFAULT sysdate		 NOT NULL,
		cnt                           		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		file1                             VARCHAR2(100)        NULL ,
    file2                             VARCHAR2(50)         NULL ,
    size2                             NUMBER(9)        DEFAULT 0       NULL,
    file3                             VARCHAR2(100)        NULL ,
    file4                             VARCHAR2(50)         NULL ,
    size4                             NUMBER(9)        DEFAULT 0       NULL
);

alter table CAMERA add (userid varchar2(20) );
ALTER TABLE CAMERA 
ADD CONSTRAINT FK_CAMERA FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE CAMERA is 'ī�޶�';
COMMENT ON COLUMN CAMERA.ctno is '�۹�ȣ';
COMMENT ON COLUMN CAMERA.category is 'ī�װ�';
COMMENT ON COLUMN CAMERA.nickname  is '�г���';
COMMENT ON COLUMN CAMERA.passwd is '��й�ȣ';
COMMENT ON COLUMN CAMERA.deal_way is '�ŷ����';
COMMENT ON COLUMN CAMERA.deal_code is '�ŷ�����';
COMMENT ON COLUMN CAMERA.product_code is '��ǰ����';
COMMENT ON COLUMN CAMERA.hprice is '�������';
COMMENT ON COLUMN CAMERA.region is '����';
COMMENT ON COLUMN CAMERA.tel is '��ȭ��ȣ';
COMMENT ON COLUMN CAMERA.email is '�̸���';
COMMENT ON COLUMN CAMERA.quantity is '����';
COMMENT ON COLUMN CAMERA.title is '����';
COMMENT ON COLUMN CAMERA.content is '����';
COMMENT ON COLUMN CAMERA.purc_date is '������';
COMMENT ON COLUMN CAMERA.wdate is '�������';
COMMENT ON COLUMN CAMERA.cnt is '��ȸ��';

drop table camera;
/**
 * ī�޶� ���̺� ����Ʈ
 */
SELECT ctno, deal_code, title, hprice, deal_way, region, nickname 
FROM camera
ORDER BY ctno DESC

select * from camera order by ctno desc;
/**
 * 
 * ī�޶� ���̺� �Է�(��� ���̺��� nickname�� userid�� �����´�)
 */


insert into CAMERA(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM CAMERA),
'�Ϲݵ�ī', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '���', '010-2222-3333', 'kkk@kkk.com', 1, 'DSLR�Ⱦƿ�', 'DSLR�ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'));

insert into CAMERA(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM CAMERA),
'DSLR', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '�λ�', '010-2222-3333', 'kkk@kkk.com', 1, 'DSLR�Ⱦƿ�', 'DSLR�ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'));

insert into CAMERA(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM CAMERA),
'�ʸ�ī�޶�', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '��õ', '010-2222-3333', 'kkk@kkk.com', 1, 'DSLR�Ⱦƿ�', 'DSLR�ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'));


insert into CAMERA(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid, file1, file2, size2) 
   values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM CAMERA),
'�ʸ�ī�޶�', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '��õ', '010-2222-3333', 'kkk@kkk.com', 1, 'DSLR�Ⱦƿ�', 'DSLR�ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'), 'ddd.jpg', 'ddd.jpg', 10);



select * from camera;



/**
 * ī�޶� ���̺� ����ȸ
 */
select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from camera
where ctno = 1

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from camera
where category = 'DSLR'
/**
 * 
 * ��ȸ�� ����
 */
update camera 
set cnt = cnt + 1
where ctno = 1

/**����
 * 
 */
update camera
set category='�Ϲݵ�ī', nickname='������', passwd='1234', deal_way='�ù�', deal_code='�˴ϴ�', product_code='�Ż�ǰ', hprice='500000', region='��õ', tel='010-5555-6666', email='kkk@kkk.com', quantity='5', title='�Ϲݵ�ī�Ⱦƿ�', content='��������������', purc_date='2016��1��' 
where ctno = 1;

/*   ����      */
delete camera
where ctno = 1;


/* �˻� */

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt, file1, file2, size2, file3, file4, size4
from camera
where title like '%��ī%'
order by ctno desc;

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt, file1, file2, size2, file3, file4, size4
from camera
where content like '%dfdf%'
order by ctno desc;




SELECT COUNT(*) as cnt
FROM camera
where title like '%��ī%'

-- step 1
 SELECT ctno, deal_code, title, hprice, deal_way, region, nickname, wdate,cnt, file1, file2, size2, rownum as r  
   FROM camera
   ORDER BY ctno DESC
   
-- step 2
SELECT ctno, deal_code, title, hprice, deal_way, region, nickname, wdate,cnt, file1, file2, size2, rownum as r
from (
SELECT ctno, deal_code, title, hprice, deal_way, region, nickname, wdate,cnt, file1, file2, size2  
   FROM camera
   ORDER BY ctno DESC
)

-- step 3
SELECT ctno, deal_code, title, hprice, deal_way, region, nickname, wdate,cnt, file1, file2, size2, r
from (
SELECT ctno, deal_code, title, hprice, deal_way, region, nickname, wdate,cnt, file1, file2, size2, rownum as r
from (
SELECT ctno, deal_code, title, hprice, deal_way, region, nickname, wdate,cnt, file1, file2, size2  
   FROM camera
   ORDER BY ctno DESC
  )
)
where r >=1 and r<=2



select * from camera;
/**********************************/
/* Table Name: ��Ȱ��ǰ */
/**********************************/
drop table Living

CREATE TABLE Living(
    ctno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    category                          VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(10)     NOT NULL,
    deal_way                          VARCHAR2(20)     NOT NULL,
    deal_code                         VARCHAR2(20)     NOT NULL,
    product_code                      VARCHAR2(20)     NOT NULL,
    hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
    region                            VARCHAR2(20)     DEFAULT ''    NOT NULL,
    tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
    email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
    quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
    title                             VARCHAR2(200)    DEFAULT ''    NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    purc_date                         VARCHAR2(20)     DEFAULT ''    NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL
);

alter table Living add (userid varchar2(20) );
ALTER TABLE Living 
ADD CONSTRAINT FK_Living FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE Living is '��Ȱ��ǰ';
COMMENT ON COLUMN Living.ctno is '�۹�ȣ';
COMMENT ON COLUMN Living.category is 'ī�װ�';
COMMENT ON COLUMN Living.nickname  is '�г���';
COMMENT ON COLUMN Living.passwd is '��й�ȣ';
COMMENT ON COLUMN Living.deal_way is '�ŷ����';
COMMENT ON COLUMN Living.deal_code is '�ŷ�����';
COMMENT ON COLUMN Living.product_code is '��ǰ����';
COMMENT ON COLUMN Living.hprice is '�������';
COMMENT ON COLUMN Living.region is '����';
COMMENT ON COLUMN Living.tel is '��ȭ��ȣ';
COMMENT ON COLUMN Living.email is '�̸���';
COMMENT ON COLUMN Living.quantity is '����';
COMMENT ON COLUMN Living.title is '����';
COMMENT ON COLUMN Living.content is '����';
COMMENT ON COLUMN Living.purc_date is '������';
COMMENT ON COLUMN Living.wdate is '�������';
COMMENT ON COLUMN Living.cnt is '��ȸ��';

/**
 * 
 * ��Ȱ��ǰ ���̺� �Է�(��� ���̺��� nickname�� userid�� �����´�)
 */


insert into Living(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM Living),
'��������', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '���', '010-2222-3333', 'kkk@kkk.com', 1, '��� �Ⱦƿ�', '��� �ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'));

insert into Living(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM Living),
'�繫��ǰ', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '�λ�', '010-2222-3333', 'kkk@kkk.com', 1, '����� �Ⱦƿ�', '����� �ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'));

insert into Living(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM Living),
'��������', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '�λ�', '010-2222-3333', 'kkk@kkk.com', 1, 'TV �Ⱦƿ�', 'TV �ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'));




select count(ctno) from living
where title like '%TV%'

select count(ctno) from camera
where title like '%TV%'



/**
 * ��Ȱ ���̺� ����ȸ
 */
select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from living
where ctno = 1

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from living
where category = '��������'


/**********************************/
/* Table Name: ������ */
/**********************************/
drop table SPORTS

CREATE TABLE SPORTS(
    ctno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    category                          VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(10)     NOT NULL,
    deal_way                          VARCHAR2(20)     NOT NULL,
    deal_code                         VARCHAR2(20)     NOT NULL,
    product_code                      VARCHAR2(20)     NOT NULL,
    hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
    region                            VARCHAR2(20)     DEFAULT ''    NOT NULL,
    tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
    email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
    quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
    title                             VARCHAR2(200)    DEFAULT ''    NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    purc_date                         VARCHAR2(20)     DEFAULT ''    NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL
);

alter table SPORTS add (userid varchar2(20) );
ALTER TABLE SPORTS 
ADD CONSTRAINT FK_SPORTS FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE SPORTS is '��Ȱ��ǰ';
COMMENT ON COLUMN SPORTS.ctno is '�۹�ȣ';
COMMENT ON COLUMN SPORTS.category is 'ī�װ�';
COMMENT ON COLUMN SPORTS.nickname  is '�г���';
COMMENT ON COLUMN SPORTS.passwd is '��й�ȣ';
COMMENT ON COLUMN SPORTS.deal_way is '�ŷ����';
COMMENT ON COLUMN SPORTS.deal_code is '�ŷ�����';
COMMENT ON COLUMN SPORTS.product_code is '��ǰ����';
COMMENT ON COLUMN SPORTS.hprice is '�������';
COMMENT ON COLUMN SPORTS.region is '����';
COMMENT ON COLUMN SPORTS.tel is '��ȭ��ȣ';
COMMENT ON COLUMN SPORTS.email is '�̸���';
COMMENT ON COLUMN SPORTS.quantity is '����';
COMMENT ON COLUMN SPORTS.title is '����';
COMMENT ON COLUMN SPORTS.content is '����';
COMMENT ON COLUMN SPORTS.purc_date is '������';
COMMENT ON COLUMN SPORTS.wdate is '�������';
COMMENT ON COLUMN SPORTS.cnt is '��ȸ��';

/**
 * 
 * ��Ȱ��ǰ ���̺� �Է�(��� ���̺��� nickname�� userid�� �����´�)
 */


insert into SPORTS(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM SPORTS),
'�౸/�߱�/��', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '���', '010-2222-3333', 'kkk@kkk.com', 1, '�߱��� �Ⱦƿ�', '�߱��� �ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'));

insert into SPORTS(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM SPORTS),
'����ǰ', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '�λ�', '010-2222-3333', 'kkk@kkk.com', 1, '��꺹 �Ⱦƿ�', '��꺹 �ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'));

insert into SPORTS(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM SPORTS),
'��Ÿ������', (select nickname from member_test where userid='master'), '1234', '���ŷ�', '�˴ϴ�', '�߰�ǰ', 100000, '�λ�', '010-2222-3333', 'kkk@kkk.com', 1, '��Ʈ���϶��� �Ⱦƿ�', '��Ʈ���϶��� �ΰ��Ⱦƿ�', '2016��10��', (select userid from member_test where userid='master'));

/**
 * ������ ���̺� ����ȸ
 */
select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from sports
where ctno = 1

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from sports
where category = '����ǰ'

/**********************************/
/* Table Name: ī�޶� ��� */
/**********************************/
drop table CAMERA_REPLY;

CREATE TABLE CAMERA_REPLY(
    rno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    nickname                         VARCHAR2(20) NOT NULL,
    passwd                           VARCHAR2(20) NOT NULL,
    rcomment                         VARCHAR2(1000) NOT NULL
);

alter table CAMERA_REPLY add(ctno number(6));
alter table CAMERA_REPLY
add constraint FK_CAMERA_REPLY FOREIGN KEY(ctno)
REFERENCES CAMERA(ctno);

alter table CAMERA_REPLY add(userid varchar2(20));
alter table CAMERA_REPLY
add constraint FK_CAMERA_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE CAMERA_REPLY is 'ī�޶� ���';
COMMENT ON COLUMN CAMERA_REPLY.rno is '��۹�ȣ';
COMMENT ON COLUMN CAMERA_REPLY.nickname is '�г���';
COMMENT ON COLUMN CAMERA_REPLY.passwd  is '��й�ȣ';
COMMENT ON COLUMN CAMERA_REPLY.rcomment is '��۳���';
COMMENT ON COLUMN CAMERA_REPLY.ctno is '�۹�ȣ(�ܷ�Ű)';

/**
 * 
 * ī�޶� ���(��� ���̺��� nickname�� userid�� �����´�)
 */


insert into CAMERA_REPLY(rno ,nickname, passwd, rcomment, ctno, userid) 
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM CAMERA_REPLY),
(select nickname from member_test where userid='master'), '1234', '���;��', 1, (select userid from member_test where userid='master'));

drop table camera_reply
/**
 * ī�޶� ��� ��ȸ
 */
select cr.rno, cr.nickname, cr.rcomment
from camera_reply cr, camera c
where cr.ctno = c.ctno;


/**********************************/
/* Table Name: ��Ȱ��ǰ ��� */
/**********************************/
drop table LIVING_REPLY

CREATE TABLE LIVING_REPLY(
    rno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    nickname                         VARCHAR2(20) NOT NULL,
    passwd                           VARCHAR2(20) NOT NULL,
    rcomment                         VARCHAR2(1000) NOT NULL
);

alter table LIVING_REPLY add(ctno number(6));
alter table LIVING_REPLY
add constraint FK_LIVING_REPLY FOREIGN KEY(ctno)
REFERENCES LIVING(ctno);

alter table LIVING_REPLY add(userid varchar2(20));
alter table LIVING_REPLY
add constraint FK_LIVING_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE CAMERA_REPLY is '��Ȱ��ǰ ���';
COMMENT ON COLUMN CAMERA_REPLY.rno is '��۹�ȣ';
COMMENT ON COLUMN CAMERA_REPLY.nickname is '�г���';
COMMENT ON COLUMN CAMERA_REPLY.passwd  is '��й�ȣ';
COMMENT ON COLUMN CAMERA_REPLY.rcomment is '��۳���';
COMMENT ON COLUMN CAMERA_REPLY.ctno is '�۹�ȣ(�ܷ�Ű)';


/**
 * 
 *  ��Ȱ��ǰ ���(��� ���̺��� nickname�� userid�� �����´�)
 */


insert into LIVING_REPLY(rno ,nickname, passwd, rcomment, ctno, userid) 
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM LIVING_REPLY),
(select nickname from member_test where userid='master'), '1234', '���;��', 1, (select userid from member_test where userid='master'));


/**
 * ��Ȱ��ǰ ��� ��ȸ
 */
select lr.rno, lr.nickname, lr.rcomment
from LIVING_REPLY lr, camera c
where lr.ctno = c.ctno;




/**********************************/
/* Table Name: ������ ��� */
/**********************************/
drop table SPORTS_REPLY

CREATE TABLE SPORTS_REPLY(
    rno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    nickname                         VARCHAR2(20) NOT NULL,
    passwd                           VARCHAR2(20) NOT NULL,
    rcomment                         VARCHAR2(1000) NOT NULL
);

alter table SPORTS_REPLY add(ctno number(6));
alter table SPORTS_REPLY
add constraint FK_SPORTS_REPLY FOREIGN KEY(ctno)
REFERENCES SPORTS(ctno);

alter table SPORTS_REPLY add(userid varchar2(20));
alter table SPORTS_REPLY
add constraint FK_SPORTS_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE CAMERA_REPLY is '������ ���';
COMMENT ON COLUMN CAMERA_REPLY.rno is '��۹�ȣ';
COMMENT ON COLUMN CAMERA_REPLY.nickname is '�г���';
COMMENT ON COLUMN CAMERA_REPLY.passwd  is '��й�ȣ';
COMMENT ON COLUMN CAMERA_REPLY.rcomment is '��۳���';
COMMENT ON COLUMN CAMERA_REPLY.ctno is '�۹�ȣ(�ܷ�Ű)';

/**
 * 
 *  ������ ���(��� ���̺��� nickname�� userid�� �����´�)
 */


insert into SPORTS_REPLY(rno ,nickname, passwd, rcomment, ctno, userid) 
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM SPORTS_REPLY),
(select nickname from member_test where userid='master'), '1234', '���;��', 1, (select userid from member_test where userid='master'));


/**
 * ��Ȱ��ǰ ��� ��ȸ
 */
select sr.rno, sr.nickname, sr.rcomment
from SPORTS_REPLY sr, camera c
where sr.ctno = c.ctno;

