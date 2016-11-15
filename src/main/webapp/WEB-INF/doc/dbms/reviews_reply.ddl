drop table reviews_reply;
delete from REVIEWS_REPLY
select * from REVIEWS_REPLY
CREATE TABLE REVIEWS_REPLY(
    rno                               NUMBER(6)                                         NOT NULL    PRIMARY KEY,
    nickname                       VARCHAR2(20)                                     NOT NULL,
    rcomment                      VARCHAR2(1000)                                  NOT NULL,
    rdate                             DATE                     DEFAULT SYSDATE    NOT NULL,
    grpno                            NUMBER(7)                                         NOT NULL,
    indent                            NUMBER(2)            DEFAULT 0              NOT NULL,
    ansnum                          NUMBER(5)            DEFAULT 0              NOT NULL
);



alter table REVIEWS_REPLY add(userid varchar2(20));
alter table REVIEWS_REPLY
add constraint FK_REVIEWS_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member(userid) on delete cascade;

alter table REVIEWS_REPLY add(r_no number(6));
alter table REVIEWS_REPLY
add constraint FK_REVIEWS_REPLY FOREIGN KEY(r_no)
REFERENCES reviews(r_no) on delete cascade;

insert into REVIEWS_REPLY(rno, nickname, passwd, rcomment, grpno, indent, ansnum, u_no, userid)
values ((select NVL(MAX(rno),0)+1 as rno from reviews_reply),
'°ü¸®ÀÚ', '1234', 'sdfdfdf', (select NVL(MAX(grpno),0)+1 as grpno from reviews_reply),
0, 0, 1, 'master');


select * from reviews_reply where r_no=3;

select rno, nickname, rcomment, grpno, indent, ansnum, r_no, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, r_no, userid from REVIEWS_REPLY ORDER BY grpno DESC, ansnum ASC)

