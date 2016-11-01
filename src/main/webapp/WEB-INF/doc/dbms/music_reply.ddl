drop table music_reply;
delete from MUSIC_REPLY
select * from MUSIC_REPLY
CREATE TABLE MUSIC_REPLY(
    rno                               NUMBER(6)                                         NOT NULL    PRIMARY KEY,
    nickname                       VARCHAR2(20)                                     NOT NULL,
    rcomment                      VARCHAR2(1000)                                  NOT NULL,
    rdate                             DATE                     DEFAULT SYSDATE    NOT NULL,
    grpno                            NUMBER(7)                                         NOT NULL,
    indent                            NUMBER(2)            DEFAULT 0              NOT NULL,
    ansnum                          NUMBER(5)            DEFAULT 0              NOT NULL
    
);



alter table MUSIC_REPLY add(userid varchar2(20));
alter table MUSIC_REPLY
add constraint FK_MUSIC_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member(userid) on delete cascade;

alter table MUSIC_REPLY add(ctno number(6));
alter table MUSIC_REPLY
add constraint FK_MUSIC_REPLY FOREIGN KEY(ctno)
REFERENCES music(ctno) on delete cascade;

insert into MUSIC_REPLY(rno, nickname, passwd, rcomment, grpno, indent, ansnum, ctno, userid)
values ((select NVL(MAX(rno),0)+1 as rno from music_reply),
'°ü¸®ÀÚ', '1234', 'sdfdfdf', (select NVL(MAX(grpno),0)+1 as grpno from music_reply),
0, 0, 1, 'master');


select * from music_reply where ctno=3;

select rno, nickname, rcomment, grpno, indent, ansnum, ctno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, ctno, userid from MUSIC_REPLY ORDER BY grpno DESC, ansnum ASC)

  select rno, nickname, rcomment, grpno, indent, ansnum, rdate, ctno, userid, rownum as r
  from (select rno, nickname, rcomment, grpno, indent, ansnum, rdate, ctno, userid from MUSIC_REPLY order by grpno desc, ansnum asc)
    WHERE ctno=2
