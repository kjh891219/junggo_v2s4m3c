/**********************************/
/* Table Name: ����/���/�Ǳ� */
/**********************************/
drop table music
delete from music
select * from music
CREATE TABLE music(
   ctno                               NUMBER(6)                                  NOT NULL,
    nickname                         VARCHAR2(20)                              NOT NULL,
    passwd                             VARCHAR2(100)                              NOT NULL,
    category                           CHAR(20)                                    NOT NULL,
    deal_way                          VARCHAR2(20)                              NOT NULL,
    deal_code                         VARCHAR2(20)                              NOT NULL,
    product_code                    VARCHAR2(20)                              NOT NULL,
    h_price                              NUMBER(15)         DEFAULT 0         NOT NULL,
    region                             VARCHAR2(20)                              NOT NULL,
    tel                                  VARCHAR2(14)                              NOT NULL ,
    email                              VARCHAR2(100)                             NOT NULL ,
    quantity                          NUMBER(6)          DEFAULT 0           NOT NULL ,
    title                                VARCHAR2(200)                             NOT NULL,
    content                           VARCHAR2(4000)                           NOT NULL,
    thumb                            VARCHAR2(100)                                   NULL ,
    file1                               VARCHAR2(50)                                     NULL,
    size1                               NUMBER(9)          DEFAULT 0                NULL,
    file2                              VARCHAR2(50)                                     NULL,
    size2                               NUMBER(9)          DEFAULT 0                NULL,
    file3                               VARCHAR2(50)                                     NULL,
    size3                               NUMBER(9)          DEFAULT 0                NULL,
    file4                               VARCHAR2(50)                                     NULL,
    size4                               NUMBER(9)          DEFAULT 0                NULL,
    file5                               VARCHAR2(50)                                     NULL,
    size5                               NUMBER(9)          DEFAULT 0                NULL,
    purc_date                         VARCHAR2(20)                             NOT NULL ,
    userid                             VARCHAR2(20)                              NOT NULL,
    wdate                             DATE                  DEFAULT sysdate   NOT NULL,
    ct_cnt                              NUMBER(6)          DEFAULT 0           NOT NULL,
    PRIMARY KEY(ctno),
  FOREIGN KEY (userid) REFERENCES member (userid)
);

alter table music rename column h_price to hprice;
alter table music rename column ct_cnt to cnt;


COMMENT ON TABLE music is '����/���';
COMMENT ON COLUMN music.ctno is '�۹�ȣ';
COMMENT ON COLUMN music.title is '����';
COMMENT ON COLUMN music.deal_code is '�ŷ�����';
COMMENT ON COLUMN music.product_code is '��ǰ����';
COMMENT ON COLUMN music.category is '��ǰ����';
COMMENT ON COLUMN music.region is '����';
COMMENT ON COLUMN music.deal_way is '�ŷ����';
COMMENT ON COLUMN music.purc_date is '������';
COMMENT ON COLUMN music.quantity is '����';
COMMENT ON COLUMN music.hprice is '�������';
COMMENT ON COLUMN music.content is '����';
COMMENT ON COLUMN music.cnt is '��ȸ��';
COMMENT ON COLUMN music.nickname  is '�г���';
COMMENT ON COLUMN music.userid is '���̵�';
COMMENT ON COLUMN music.passwd is '��й�ȣ';
COMMENT ON COLUMN music.tel is '��ȭ��ȣ';
COMMENT ON COLUMN music.email is '�����ּ�';
COMMENT ON COLUMN music.wdate is '�������';
COMMENT ON COLUMN music.deal_status is '�ŷ�����';

/** Insert sample data*/
truncate table music 
INSERT INTO music (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, thumb, file1, size1, file2, size2, file3, size3, file4, size4, file5, size5 )
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM music), '�ǾƳ� �Ǹ��մϴ�','�˴ϴ�', '����ǰ'
, '�ǾƳ�', '��⵵', '�ù�', '2015-01-01', 1
, 1000000, '1�� �̸� ����� ���� �ֻ� �ǾƳ��Դϴ�', 0, '�����Ѱŷ���', 'sol1'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0,'',0,'',0,'',0,'',0);

INSERT INTO music (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, thumb, file1, size1, file2, size2, file3, size3, file4, size4, file5, size5 )
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM music), 'MIKA ���� ���ſ��ؿ�','��ϴ�', '�߰�ǰ'
, 'MIKA ����', '��⵵', '���ŷ�', '2015-01-01', 1
, 1000000, '���� �ֻ� MIKA ���� ���ؿ�', 0, '�����Ѱŷ���', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0,'',0,'',0,'',0,'',0);

INSERT INTO music (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, thumb, file1, size1, file2, size2, file3, size3, file4, size4, file5, size5 )
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM music), '�ǾƳ� �Ǹ��մϴ�2','�˴ϴ�', '�߰�ǰ'
, 'MIKA ����', '���', '���ŷ�', '2011-01-01', 1
, 1000000, '5�� �̸� ����� �߰� �ǾƳ�', 0, '�����Ѱŷ���', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0,'',0,'',0,'',0,'',0);

/** update music table */
select * from music
UPDATE music set title = 'MIKA ���� �ŷ���', deal_code = '�ù�', product_code ='����ǰ'
        , category = 'MIKA ����', region ='��⵵', deal_way='���ŷ�', purc_date='2016-03-03', quantity=1
        , hprice=800000, content='�̻�� CD', cnt = 0, nickname='�մ���', userid ='chanmi'
        , passwd='1234', tel='02-0930-0909', email='test1@google.com', deal_status='Y'
        , thumb = '', file1='test.txt', size1=0, file2 ='test.txt', size2 = 0, file3 ='test.txt', size3 = 0
        , file4 ='test.txt', size4 = 0, file5 ='test.txt', size5 = 0
WHERE ctno = 3 and passwd = '4546'

/** ���� */
DELETE music WHERE ctno = 9 and passwd = '4546'
 

/**********************************/
/* Table Name: ����/������ �Խñ� ��� */
/**********************************/
drop table mreply
CREATE TABLE mreply(
    rno                    NUMBER(6)   NOT NULL,  
    rcomment           VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    ctno                   NUMBER(6)    NULL ,
    userid                 VARCHAR2(20)     NULL ,
    nickname                VARCHAR2(30)     NOT NULL,
    passwd               VARCHAR2(10)     NOT NULL,
    wdate                 DATE     DEFAULT sysdate     NOT NULL,
    grpno                 NUMBER(7)        NOT NULL,
    indent                NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member (userid),
    FOREIGN KEY (ctno) REFERENCES music (ctno)
);

COMMENT ON TABLE mreply is '����/���� ���';
COMMENT ON COLUMN mreply.rno is '��۹�ȣ';
COMMENT ON COLUMN mreply.rcomment is '����';
COMMENT ON COLUMN mreply.ctno is '�۹�ȣ';
COMMENT ON COLUMN mreply.userid is '���̵�';
COMMENT ON COLUMN mreply.nickname is '����� �г���';
COMMENT ON COLUMN mreply.passwd is '��й�ȣ';
COMMENT ON COLUMN mreply.wdate is '�������';

INSERT INTO  mreply (rno, rcomment, ctno, userid, nickname, passwd, wdate, grpno, indent, ansnum)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM mreply), '���� �ŷ� �ϼ���', 1, 'master', '���ſ��ϴ»��', '1234', sysdate, 1, 1, 1);
INSERT INTO  mreply (rno, rcomment, ctno, userid, nickname, passwd, wdate, grpno, indent, ansnum)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM mreply), '���� �ŷ� �ϼ���', 1, 'master', '���ſ��ϴ»��', '1234', sysdate, 1, 1, 1);
INSERT INTO  mreply (rno, rcomment, ctno, userid, nickname, passwd, wdate, grpno, indent, ansnum)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM mreply), '���� �ŷ� �ϼ���', 1, 'master', '���ſ��ϴ»��', '1234', sysdate, 1, 1, 1);


/** ���� */
DELETE mreply WHERE rno = 1 and passwd = '5678'

select * from music
