
CREATE TABLE product_reply(
rno      NUMBER(10) NOT NULL, --댓글번호  
rcomment     VARCHAR(1000) NOT NULL, -- 글내용
pno         NUMBER(6) NOT NULL, -- 글번호 (외래키)
userid       VARCHAR(20) NOT NULL, --유저아이디
nickname    VARCHAR(20) NOT NULL, -- 닉네임
--passwd    VARCHAR(10) NOT NULL, -- 비밀번호
wdate     DATE DEFAULT SYSDATE NOT NULL,
grpno      NUMBER(7) NOT NULL,  --그룹 번호
indent     NUMBER(3) DEFAULT 0 NOT NULL, -- 답변차수
ansnum   NUMBER(5) DEFAULT 0 NOT NULL, -- 답변순서
PRIMARY KEY(rno),
FOREIGN KEY(userid) REFERENCES member(userid) on delete cascade,
FOREIGN KEY(pno) REFERENCES product(pno) on delete cascade
);

alter table product_reply drop(passwd);
--입력
INSERT INTO product_reply(rno, rcomment,  pno, userid, nickname,  wdate , grpno, indent, ansnum)
VALUES ((select NVL(MAX(rno),0)+1 as rno from product_reply),
           '좋아요', 1, (select userid from member where userid = 'master'), 'nickname',  sysdate, 1, 1, 1);


/**
 * 댓글 조회
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
rno      NUMBER(10) NOT NULL, --댓글번호  
rcomment     VARCHAR(1000) NOT NULL, -- 글내용
clothno         NUMBER(6) NOT NULL, -- 글번호 (외래키)
userid       VARCHAR(20) NOT NULL, --유저아이디
nickname    VARCHAR(20) NOT NULL, -- 닉네임
--passwd    VARCHAR(10) NOT NULL, -- 비밀번호
wdate     DATE DEFAULT SYSDATE NOT NULL,
grpno      NUMBER(7) NOT NULL,  --그룹 번호
indent     NUMBER(3) DEFAULT 0 NOT NULL, -- 답변차수
ansnum   NUMBER(5) DEFAULT 0 NOT NULL, -- 답변순서
PRIMARY KEY(rno),
FOREIGN KEY(userid) REFERENCES member(userid) on delete cascade,
FOREIGN KEY(clothno) REFERENCES cloth(clothno) on delete cascade
);

alter table cloth_reply drop(passwd);

--입력
INSERT INTO cloth_reply(rno, rcomment,  clothno, userid, nickname,  wdate , grpno, indent, ansnum)
VALUES ((select NVL(MAX(rno),0)+1 as rno from cloth_reply),
           '좋아요', 1, (select userid from member where userid = 'master'), 'nickname',  sysdate, 1, 1, 1);



/**
 * 댓글 조회
 */            
SELECT cr.rno, cr.nickname, cr.rcomment, cr.userid
from cloth_reply cr, cloth c
where cr.rno = c.clothno;

drop table cloth_reply;

select rno, nickname, rcomment, grpno, indent, ansnum, clothno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, clothno, userid from cloth_reply ORDER BY grpno DESC, ansnum ASC)



----------------------------------------------------------------------
CREATE TABLE cosmetic_reply(
rno      NUMBER(10) NOT NULL, --댓글번호  
rcomment     VARCHAR(1000) NOT NULL, -- 글내용
cno         NUMBER(6) NOT NULL, -- 글번호 (외래키)
userid       VARCHAR(20) NOT NULL, --유저아이디
nickname    VARCHAR(20) NOT NULL, -- 닉네임
--passwd    VARCHAR(10) NOT NULL, -- 비밀번호
wdate     DATE DEFAULT SYSDATE NOT NULL,
grpno      NUMBER(7) NOT NULL,  --그룹 번호
indent     NUMBER(3) DEFAULT 0 NOT NULL, -- 답변차수
ansnum   NUMBER(5) DEFAULT 0 NOT NULL, -- 답변순서
PRIMARY KEY(rno),
FOREIGN KEY(userid) REFERENCES member(USERID) on delete cascade,
FOREIGN KEY(cno) REFERENCES cosmetic(cno) on delete cascade
);

alter table cosmetic_reply drop(passwd);
--입력
INSERT INTO cosmetic_reply(rno, rcomment,  cno, userid, nickname,  wdate , grpno, indent, ansnum)
VALUES ((select NVL(MAX(cno),0)+1 as cno from cosmetic_reply),
           '좋아요', 1, (select userid from member where userid = 'master'), 'nickname', sysdate, 1, 1, 1);


/**
 * 댓글 조회
 */            
SELECT cor.rno, cor.nickname, cor.rcomment, cor.userid
from cosmetic_reply cor, cosmetic cos
where cor.rno = cos.cno;

drop table cosmetic_reply;

select rno, nickname, rcomment, grpno, indent, ansnum, cno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, cno, userid from cosmetic_reply ORDER BY grpno DESC, ansnum ASC)



---------------------------------------------------------------------------------------------

CREATE TABLE sports_reply(
rno      NUMBER(10) NOT NULL, --댓글번호  
rcomment     VARCHAR(1000) NOT NULL, -- 글내용
sno         NUMBER(6) NOT NULL, -- 글번호 (외래키)
userid       VARCHAR(20) NOT NULL, --유저아이디
nickname    VARCHAR(20) NOT NULL, -- 닉네임
--passwd    VARCHAR(10) NOT NULL, -- 비밀번호
wdate     DATE DEFAULT SYSDATE NOT NULL,
grpno      NUMBER(7) NOT NULL,  --그룹 번호
indent     NUMBER(3) DEFAULT 0 NOT NULL, -- 답변차수
ansnum   NUMBER(5) DEFAULT 0 NOT NULL, -- 답변순서
PRIMARY KEY(rno),
FOREIGN KEY(userid) REFERENCES member(USERID) on delete cascade,
FOREIGN KEY(sno) REFERENCES sports(sno) on delete cascade
);

alter table sports_reply drop(passwd);
--입력
INSERT INTO sports_reply(rno, rcomment,  sno, userid, nickname,  wdate , grpno, indent, ansnum)
VALUES ((select NVL(MAX(sno),0)+1 as sno from sports_reply),
           '좋아요', 1, (select userid from member where userid = 'master'), 'nickname',  sysdate, 1, 1, 1);


/**
 * 댓글 조회
 */            
SELECT sr.rno, sr.nickname, sr.rcomment, sr.userid
from sports_reply sr, sports s
where sr.rno = s.sno;

drop table sports_reply;

select rno, nickname, rcomment, grpno, indent, ansnum, sno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, sno, userid from sports_reply ORDER BY grpno DESC, ansnum ASC)


/*
 * 삭제
 */

delete from sports_reply where rno in (  
  select a.rno from sports_reply a, (select sno, grpno, indent, rno, ansnum from sports_reply where rno = 5) b
  where a.sno = b.sno and a.grpno = b.grpno and a.indent >= b.indent and  a.rno >= b.rno and a.ansnum >= b.ansnum)