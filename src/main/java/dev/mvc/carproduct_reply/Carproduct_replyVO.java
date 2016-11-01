package dev.mvc.carproduct_reply;

public class Carproduct_replyVO {
  
  /*
   * rno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    nickname                         VARCHAR2(20) NOT NULL,
    passwd                           VARCHAR2(20) NOT NULL,
    rcomment                         VARCHAR2(1000) NOT NULL,
    rdate                             DATE DEFAULT SYSDATE         NOT NULL,
    grpno                            NUMBER(7)        NOT NULL,
    indent                            NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum                          NUMBER(5)        DEFAULT 0       NOT NULL
    p_no
    userid
   * 
   */
  
  
  private int rno;
  private String nickname;
  private String rcomment;
  private String rdate;
  private int grpno;
  private int indent;
  private int ansnum;
  private int p_no;
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
  public int getP_no() {
    return p_no;
  }
  public void setP_no(int p_no) {
    this.p_no = p_no;
  }
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }

}
