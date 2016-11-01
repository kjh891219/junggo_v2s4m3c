drop table carproduct_reply;
delete from CARPRODUCT_REPLY
select * from CARPRODUCT_REPLY
CREATE TABLE CARPRODUCT_REPLY(
    rno                               NUMBER(6)                                         NOT NULL    PRIMARY KEY,
    nickname                       VARCHAR2(20)                                     NOT NULL,
    rcomment                      VARCHAR2(1000)                                  NOT NULL,
    rdate                             DATE                     DEFAULT SYSDATE    NOT NULL,
    grpno                            NUMBER(7)                                         NOT NULL,
    indent                            NUMBER(2)            DEFAULT 0              NOT NULL,
    ansnum                          NUMBER(5)            DEFAULT 0              NOT NULL
    
);



alter table CARPRODUCT_REPLY add(userid varchar2(20));
alter table CARPRODUCT_REPLY
add constraint FK_CARPRODUCT_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member(userid) on delete cascade;

alter table CARPRODUCT_REPLY add(p_no number(6));
alter table CARPRODUCT_REPLY
add constraint FK_CARPRODUCT_REPLY FOREIGN KEY(p_no)
REFERENCES carproduct(p_no) on delete cascade;

insert into CARPRODUCT_REPLY(rno, nickname, passwd, rcomment, grpno, indent, ansnum, p_no, userid)
values ((select NVL(MAX(rno),0)+1 as rno from carproduct_reply),
'°ü¸®ÀÚ', '1234', 'sdfdfdf', (select NVL(MAX(grpno),0)+1 as grpno from carproduct_reply),
0, 0, 1, 'master');


select * from carproduct_reply where p_no=3;

select rno, nickname, rcomment, grpno, indent, ansnum, p_no, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, p_no, userid from CARPRODUCT_REPLY ORDER BY grpno DESC, ansnum ASC)


 

 
 

 



