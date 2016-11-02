
CREATE TABLE product_reply(
rno      NUMBER(10) NOT NULL, --��۹�ȣ  
rcomment     VARCHAR(1000) NOT NULL, -- �۳���
pno         NUMBER(6) NOT NULL, -- �۹�ȣ (�ܷ�Ű)
userid       VARCHAR(20) NOT NULL, --�������̵�
nickname    VARCHAR(20) NOT NULL, -- �г���
--passwd    VARCHAR(10) NOT NULL, -- ��й�ȣ
wdate     DATE DEFAULT SYSDATE NOT NULL,
grpno      NUMBER(7) NOT NULL,  --�׷� ��ȣ
indent     NUMBER(3) DEFAULT 0 NOT NULL, -- �亯����
ansnum   NUMBER(5) DEFAULT 0 NOT NULL, -- �亯����
PRIMARY KEY(rno),
FOREIGN KEY(userid) REFERENCES member(userid) on delete cascade,
FOREIGN KEY(pno) REFERENCES product(pno) on delete cascade
);

alter table product_reply drop(passwd);
--�Է�
INSERT INTO product_reply(rno, rcomment,  pno, userid, nickname,  wdate , grpno, indent, ansnum)
VALUES ((select NVL(MAX(rno),0)+1 as rno from product_reply),
           '���ƿ�', 1, (select userid from member where userid = 'master'), 'nickname',  sysdate, 1, 1, 1);


/**
 * ��� ��ȸ
 */            
SELECT pr.rno, pr.nickname, pr.rcomment, pr.userid
from product_reply pr, product p
where pr.rno = p.pno;

drop table product_reply;

select rno, nickname, rcomment, grpno, indent, ansnum, pno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, pno, userid from product_reply ORDER BY grpno DESC, ansnum ASC)


-------------------------------------------------------------
drop table cloth_reply;

CREATE TABLE cloth_reply(
rno      NUMBER(10) NOT NULL, --��۹�ȣ  
rcomment     VARCHAR(1000) NOT NULL, -- �۳���
clothno         NUMBER(6) NOT NULL, -- �۹�ȣ (�ܷ�Ű)
userid       VARCHAR(20) NOT NULL, --�������̵�
nickname    VARCHAR(20) NOT NULL, -- �г���
--passwd    VARCHAR(10) NOT NULL, -- ��й�ȣ
wdate     DATE DEFAULT SYSDATE NOT NULL,
grpno      NUMBER(7) NOT NULL,  --�׷� ��ȣ
indent     NUMBER(3) DEFAULT 0 NOT NULL, -- �亯����
ansnum   NUMBER(5) DEFAULT 0 NOT NULL, -- �亯����
PRIMARY KEY(rno),
FOREIGN KEY(userid) REFERENCES member(userid) on delete cascade,
FOREIGN KEY(clothno) REFERENCES cloth(clothno) on delete cascade
);

alter table cloth_reply drop(passwd);

--�Է�
INSERT INTO cloth_reply(rno, rcomment,  clothno, userid, nickname,  wdate , grpno, indent, ansnum)
VALUES ((select NVL(MAX(rno),0)+1 as rno from cloth_reply),
           '���ƿ�', 1, (select userid from member where userid = 'master'), 'nickname',  sysdate, 1, 1, 1);



/**
 * ��� ��ȸ
 */            
SELECT cr.rno, cr.nickname, cr.rcomment, cr.userid
from cloth_reply cr, cloth c
where cr.rno = c.clothno;

drop table cloth_reply;

select rno, nickname, rcomment, grpno, indent, ansnum, clothno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, clothno, userid from cloth_reply ORDER BY grpno DESC, ansnum ASC)



----------------------------------------------------------------------
CREATE TABLE cosmetic_reply(
rno      NUMBER(10) NOT NULL, --��۹�ȣ  
rcomment     VARCHAR(1000) NOT NULL, -- �۳���
cno         NUMBER(6) NOT NULL, -- �۹�ȣ (�ܷ�Ű)
userid       VARCHAR(20) NOT NULL, --�������̵�
nickname    VARCHAR(20) NOT NULL, -- �г���
--passwd    VARCHAR(10) NOT NULL, -- ��й�ȣ
wdate     DATE DEFAULT SYSDATE NOT NULL,
grpno      NUMBER(7) NOT NULL,  --�׷� ��ȣ
indent     NUMBER(3) DEFAULT 0 NOT NULL, -- �亯����
ansnum   NUMBER(5) DEFAULT 0 NOT NULL, -- �亯����
PRIMARY KEY(rno),
FOREIGN KEY(userid) REFERENCES member(USERID) on delete cascade,
FOREIGN KEY(cno) REFERENCES cosmetic(cno) on delete cascade
);

alter table cosmetic_reply drop(passwd);
--�Է�
INSERT INTO cosmetic_reply(rno, rcomment,  cno, userid, nickname,  wdate , grpno, indent, ansnum)
VALUES ((select NVL(MAX(cno),0)+1 as cno from cosmetic_reply),
           '���ƿ�', 1, (select userid from member where userid = 'master'), 'nickname', sysdate, 1, 1, 1);


/**
 * ��� ��ȸ
 */            
SELECT cor.rno, cor.nickname, cor.rcomment, cor.userid
from cosmetic_reply cor, cosmetic cos
where cor.rno = cos.cno;

drop table cosmetic_reply;

select rno, nickname, rcomment, grpno, indent, ansnum, cno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, cno, userid from cosmetic_reply ORDER BY grpno DESC, ansnum ASC)



---------------------------------------------------------------------------------------------

CREATE TABLE sports_reply(
rno      NUMBER(10) NOT NULL, --��۹�ȣ  
rcomment     VARCHAR(1000) NOT NULL, -- �۳���
sno         NUMBER(6) NOT NULL, -- �۹�ȣ (�ܷ�Ű)
userid       VARCHAR(20) NOT NULL, --�������̵�
nickname    VARCHAR(20) NOT NULL, -- �г���
--passwd    VARCHAR(10) NOT NULL, -- ��й�ȣ
wdate     DATE DEFAULT SYSDATE NOT NULL,
grpno      NUMBER(7) NOT NULL,  --�׷� ��ȣ
indent     NUMBER(3) DEFAULT 0 NOT NULL, -- �亯����
ansnum   NUMBER(5) DEFAULT 0 NOT NULL, -- �亯����
PRIMARY KEY(rno),
FOREIGN KEY(userid) REFERENCES member(USERID) on delete cascade,
FOREIGN KEY(sno) REFERENCES sports(sno) on delete cascade
);

alter table sports_reply drop(passwd);
--�Է�
INSERT INTO sports_reply(rno, rcomment,  sno, userid, nickname,  wdate , grpno, indent, ansnum)
VALUES ((select NVL(MAX(sno),0)+1 as sno from sports_reply),
           '���ƿ�', 1, (select userid from member where userid = 'master'), 'nickname',  sysdate, 1, 1, 1);


/**
 * ��� ��ȸ
 */            
SELECT sr.rno, sr.nickname, sr.rcomment, sr.userid
from sports_reply sr, sports s
where sr.rno = s.sno;

drop table sports_reply;

select rno, nickname, rcomment, grpno, indent, ansnum, sno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, sno, userid from sports_reply ORDER BY grpno DESC, ansnum ASC)


/*
 * ����
 */

delete from sports_reply where rno in (  
  select a.rno from sports_reply a, (select sno, grpno, indent, rno, ansnum from sports_reply where rno = 5) b
  where a.sno = b.sno and a.grpno = b.grpno and a.indent >= b.indent and  a.rno >= b.rno and a.ansnum >= b.ansnum)