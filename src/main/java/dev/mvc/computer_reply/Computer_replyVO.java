package dev.mvc.computer_reply;

public class Computer_replyVO {
/*
 * CREATE TABLE nreply(
    rno                    NUMBER(6)   NOT NULL,  
    rcomment           VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    ctno                   NUMBER(6)    NULL ,
    userid                 VARCHAR2(20)     NULL ,
    nickname                VARCHAR2(30)     NOT NULL,
    wdate                 DATE     DEFAULT sysdate     NOT NULL,
    grpno                 NUMBER(7)        NOT NULL,
    indent                NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member (userid),
    FOREIGN KEY (ctno) REFERENCES computer (ctno)
);
 */
  
  private int rno;
  private String rcomment;
  private int ctno;
  private String userid;
  private String nickname;
  private String wdate;
  private int grpno;
  private int indent;
  private int ansnum;
  
  
  public int getRno() {
    return rno;
  }
  public void setRno(int rno) {
    this.rno = rno;
  }
  public String getRcomment() {
    return rcomment;
  }
  public void setRcomment(String rcomment) {
    this.rcomment = rcomment;
  }
  public int getCtno() {
    return ctno;
  }
  public void setCtno(int ctno) {
    this.ctno = ctno;
  }
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getWdate() {
    return wdate;
  }
  public void setWdate(String wdate) {
    this.wdate = wdate;
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

  
  
  
}
