package dev.mvc.game_reply;

public class Game_ReplyVO {
 /**
  * CREATE TABLE GAME_reply(
    gno                               NUMBER(6)    NULL  ,
    grno                              NUMBER(6)    NOT NULL ,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(20)     NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    cnt                               NUMBER(6)        DEFAULT 0     NOT NULL,
    indent                            NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum                            NUMBER(5)        DEFAULT 0       NOT NULL,
    userid                            VARCHAR2(20)     NULL ,
    FOREIGN KEY (gno) REFERENCES game (gno),
    FOREIGN KEY (userid) REFERENCES member_test (userid)
);
  */
  private int rno;
  private String nickname;
  private String rcomment;
  private String rdate;
  private int grpno;
  private int indent;
  private int ansnum;
  private int gno;
  private String userid;
  
  
  public int getRno() {
  return rno;
}
public void setRno(int rno) {
  this.rno = rno;
}
public String getNickname() {
  return nickname;
}
public void setNickname(String nickname) {
  this.nickname = nickname;
}
public String getRcomment() {
  return rcomment;
}
public void setRcomment(String rcomment) {
  this.rcomment = rcomment;
}
public String getRdate() {
  return rdate;
}
public void setRdate(String rdate) {
  this.rdate = rdate;
}
public int getGrpno() {
  return grpno;
}
public void setGrpno(int grpno) {
  this.grpno = grpno;
}
public int getIndent() {
  return indent;
}
public void setIndent(int indent) {
  this.indent = indent;
}
public int getAnsnum() {
  return ansnum;
}
public void setAnsnum(int ansnum) {
  this.ansnum = ansnum;
}
public int getGno() {
  return gno;
}
public void setGno(int gno) {
  this.gno = gno;
}
public String getUserid() {
  return userid;
}
public void setUserid(String userid) {
  this.userid = userid;
}

 

     
}
