package dev.mvc.message;

/*
CREATE TABLE message(
    msg_no                            NUMBER(6)         NOT NULL   PRIMARY KEY, -- ��ȣ
    sendid                            VARCHAR2(20)      NOT NULL,               -- ���� ���
    receiveid                         VARCHAR2(20)      NOT NULL,               -- �޴� ���
    title                             VARCHAR2(200)     NOT NULL,               -- ����
    content                           VARCHAR2(4000)    NOT NULL,               -- ����
    msg_date                          DATE              NOT NULL,               -- ���� �ð�
    read_ck                           CHAR(1)           DEFAULT 'N' NOT NULL,   -- ���� ����
    visible_recv                      CHAR(1)           DEFAULT 'Y' NOT NULL,   -- ���� �޽��� ǥ��
    visible_send                      CHAR(1)           DEFAULT 'Y' NOT NULL,   -- ���� �޽��� ǥ��
  FOREIGN KEY (receiveid) REFERENCES member (userid),
  FOREIGN KEY (sendid)    REFERENCES member (userid) 
);

 * 
*/

public class MessageVO {
  private int msg_no;
  private String sendid;
  private String receiveid;
  private String title;
  private String content;
  private String msg_date;
  private String read_ck;
  private String visible_recv;
  private String visible_send;
  
  public int getMsg_no() {
    return msg_no;
  }
  public void setMsg_no(int msg_no) {
    this.msg_no = msg_no;
  }
  public String getSendid() {
    return sendid;
  }
  public void setSendid(String sendid) {
    this.sendid = sendid;
  }
  public String getReceiveid() {
    return receiveid;
  }
  public void setReceiveid(String receiveid) {
    this.receiveid = receiveid;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getMsg_date() {
    return msg_date;
  }
  public void setMsg_date(String msg_date) {
    this.msg_date = msg_date;
  }

  public String getRead_ck() {
    return read_ck;
  }
  public void setRead_ck(String read_ck) {
    this.read_ck = read_ck;
  }
  public String getVisible_recv() {
    return visible_recv;
  }
  public void setVisible_recv(String visible_recv) {
    this.visible_recv = visible_recv;
  }
  public String getVisible_send() {
    return visible_send;
  }
  public void setVisible_send(String visible_send) {
    this.visible_send = visible_send;
  }
}
