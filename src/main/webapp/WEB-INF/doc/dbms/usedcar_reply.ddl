drop table usedcar_reply;
delete from USEDCAR_REPLY
select * from USEDCAR_REPLY
CREATE TABLE USEDCAR_REPLY(
    rno                               NUMBER(6)                                         NOT NULL    PRIMARY KEY,
    nickname                       VARCHAR2(20)                                     NOT NULL,
    rcomment                      VARCHAR2(1000)                                  NOT NULL,
    rdate                             DATE                     DEFAULT SYSDATE    NOT NULL,
    grpno                            NUMBER(7)                                         NOT NULL,
    indent                            NUMBER(2)            DEFAULT 0              NOT NULL,
    ansnum                          NUMBER(5)            DEFAULT 0              NOT NULL
    
);



alter table USEDCAR_REPLY add(userid varchar2(20));
alter table USEDCAR_REPLY
add constraint FK_USEDCAR_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member(userid) on delete cascade;

alter table USEDCAR_REPLY add(u_no number(6));
alter table USEDCAR_REPLY
add constraint FK_USEDCAR_REPLY FOREIGN KEY(u_no)
REFERENCES usedcar(u_no) on delete cascade;

insert into USEDCAR_REPLY(rno, nickname, passwd, rcomment, grpno, indent, ansnum, u_no, userid)
values ((select NVL(MAX(rno),0)+1 as rno from usedcar_reply),
'°ü¸®ÀÚ', '1234', 'sdfdfdf', (select NVL(MAX(grpno),0)+1 as grpno from usedcar_reply),
0, 0, 1, 'master');


select * from usedcar_reply where u_no=3;

select rno, nickname, rcomment, grpno, indent, ansnum, u_no, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, u_no, userid from USEDCAR_REPLY ORDER BY grpno DESC, ansnum ASC)


 

 
 

 



