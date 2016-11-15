package dev.mvc.art;

import org.springframework.web.multipart.MultipartFile;

/*
 * CREATE TABLE ART(
ano               NUMBER(6)                           NOT NULL  PRIMARY KEY, -- ��ȣ
category          VARCHAR2(20)                        NOT NULL, -- ī�װ� 
userid            VARCHAR2(20)                        NOT NULL, -- ���̵�
nickname          VARCHAR2(20)                        NOT NULL, -- �г���
passwd            VARCHAR2(10)                        NOT NULL, -- ��й�ȣ
deal_way          VARCHAR2(20)                        NOT NULL, -- �ŷ����
deal_code         VARCHAR2(20)                        NOT NULL, -- �ŷ�����
product_code      VARCHAR2(20)                        NOT NULL, -- ��ǰ����
hprice            NUMBER(15)        DEFAULT 0         NOT NULL, -- �������
region            VARCHAR2(20)      DEFAULT ''        NOT NULL, -- ����
tel               VARCHAR2(14)      DEFAULT ''        NOT NULL, -- ��ȭ ��ȣ
email             VARCHAR2(100)     DEFAULT ''        NOT NULL, -- �̸���
quantity          NUMBER(6)         DEFAULT 0         NOT NULL, -- ����
title             VARCHAR2(200)     DEFAULT ''        NOT NULL, -- ����
content           VARCHAR2(4000)                      NOT NULL, -- ����
cnt               NUMBER(6)         DEFAULT 0         NOT NULL, -- ��ȸ��
purc_date         VARCHAR2(20)      DEFAULT ''        NOT NULL, -- ������
wdate             DATE              DEFAULT sysdate   NOT NULL, -- ��� ����
thumb             VARCHAR2(100)                      NULL ,
file1             VARCHAR2(50)                       NULL ,
file2             VARCHAR2(50)                       NULL,
file3             VARCHAR2(50)                       NULL,
file4             VARCHAR2(50)                       NULL,
file5             VARCHAR2(50)                       NULL,
size1             NUMBER(9)         DEFAULT 0        NULL,
size2             NUMBER(9)         DEFAULT 0        NULL,
size3             NUMBER(9)         DEFAULT 0        NULL,
size4             NUMBER(9)         DEFAULT 0        NULL,
size5             NUMBER(9)         DEFAULT 0        NULL,
  FOREIGN KEY (userid) REFERENCES member(userid)
);
 */
public class ArtVO {
//---------------------------------------
  /** ���������� �� ��ȣ **/
  private int my_no;
  /** ���������� �� �� **/
  private int my_cnt;
  public int getMy_no() {
    return my_no;
  }
  public void setMy_no(int my_no) {
    this.my_no = my_no;
  }
  public int getMy_cnt() {
    return my_cnt;
  }

  public void setMy_cnt(int my_cnt) {
    this.my_cnt = my_cnt;
  }
//---------------------------------------
  /**�۹�ȣ*/
  private int ano;
  /**ī�װ�*/
  private String category;
  /**���̵�*/
  private String userid;
  /**�г���*/
  private String nickname;
  /**��й�ȣ*/
  private String passwd;
  /**�ŷ����*/
  private String deal_way;
  /**�ŷ�����*/
  private String deal_code;
  /**��ǰ����*/
  private String product_code;
  /**�������*/
  private int hprice;
  /**����*/
  private String region;
  /**��ȭ��ȣ*/
  private String tel;
  /**�̸���*/
  private String email;
  /**��ȸ��*/
  private int cnt;
  /**����*/
  private int quantity;
  /**����*/
  private String title;
  /**�󼼳���*/
  private String content;
  /**���Խñ�*/
  private String purc_date;
  /**�۵����*/
  private String wdate;
  
  /** Preview ���� �̹��� 200 X 150, �ڵ� ������ */
  private String thumb = "";
  /** ���ε� ���� */
  private String file1 = "";
  /** ���ε�� ���� ũ�� */
  private long size1 = 0;
  
  
  /** ���ε� ����2 */
  private String file2 = "";
  /** ���ε�� ���� ũ��2 */
  private long size2 = 0;
  
  
  /** ���ε� ����3 */
  private String file3 = "";
  /** ���ε�� ���� ũ��3 */
  private long size3 = 0;
  
  
  /** ���ε� ����4 */
  private String file4 = "";
  /** ���ε�� ���� ũ��4 */
  private long size4 = 0;
  
  
  
  /** ���ε� ����5 */
  private String file5 = "";
  /** ���ε�� ���� ũ��5 */
  private long size5 = 0;
  
  
  
  /** Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  ���� �÷��� �������� ����.
   */  
  private MultipartFile file1MF;

  /** size2�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size1Label; 
  
  
  /** Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü2,
  ���� �÷��� �������� ����.
   */  
  private MultipartFile file2MF;

  /** size4�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size2Label; 
  
  
  /** Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü3,
  ���� �÷��� �������� ����.
   */  
  private MultipartFile file3MF;

  /** size6�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size3Label; 
  
  /** Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü4,
  ���� �÷��� �������� ����.
   */  
  private MultipartFile file4MF;

  /** size8�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size4Label; 
  
  /** Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü5,
  ���� �÷��� �������� ����.
   */  
  private MultipartFile file5MF;

  /** size10�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size5Label;

 

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
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

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getDeal_way() {
    return deal_way;
  }

  public void setDeal_way(String deal_way) {
    this.deal_way = deal_way;
  }

  public String getDeal_code() {
    return deal_code;
  }

  public void setDeal_code(String deal_code) {
    this.deal_code = deal_code;
  }

  public String getProduct_code() {
    return product_code;
  }

  public void setProduct_code(String product_code) {
    this.product_code = product_code;
  }

  public int getHprice() {
    return hprice;
  }

  public void setHprice(int hprice) {
    this.hprice = hprice;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
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

  public String getPurc_date() {
    return purc_date;
  }

  public void setPurc_date(String purc_date) {
    this.purc_date = purc_date;
  }

  public String getWdate() {
    return wdate;
  }

  public void setWdate(String wdate) {
    this.wdate = wdate;
  }

  public String getThumb() {
    return thumb;
  }

  public void setThumb(String thumb) {
    this.thumb = thumb;
  }

  public String getFile1() {
    return (file1 != null)?file1:"";
  }

  public void setFile1(String file1) {
    this.file1 = file1;
  }

  public long getSize1() {
    return size1;
  }

  public void setSize1(long size1) {
    this.size1 = size1;
  }

  public String getFile2() {
    return (file2 != null)?file2:"";
  }

  public void setFile2(String file2) {
    this.file2 = file2;
  }

  public long getSize2() {
    return size2;
  }

  public void setSize2(long size2) {
    this.size2 = size2;
  }

  public String getFile3() {
    return (file3 != null)?file3:"";
  }

  public void setFile3(String file3) {
    this.file3 = file3;
  }

  public long getSize3() {
    return size3;
  }

  public void setSize3(long size3) {
    this.size3 = size3;
  }

  public String getFile4() {
    return (file4 != null)?file4:"";
  }

  public void setFile4(String file4) {
    this.file4 = file4;
  }

  public long getSize4() {
    return size4;
  }

  public void setSize4(long size4) {
    this.size4 = size4;
  }

  public String getFile5() {
    return (file5 != null)?file5:"";
  }

  public void setFile5(String file5) {
    this.file5 = file5;
  }

  public long getSize5() {
    return size5;
  }

  public void setSize5(long size5) {
    this.size5 = size5;
  }
//////////////////////////////
  public MultipartFile getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  
  public String getSize1Label() {
    return size1Label;
  }

  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }
////////////////////////////////
  public MultipartFile getFile2MF() {
    return file2MF;
  }

  public void setFile2MF(MultipartFile file2mf) {
    file2MF = file2mf;
  }

  public String getSize2Label() {
    return size2Label;
  }

  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
  }
//////////////////////////////////////////////////////
  public MultipartFile getFile3MF() {
    return file3MF;
  }
  
  public void setFile3MF(MultipartFile file3mf) {
    file3MF = file3mf;
  }
  
  public String getSize3Label() {
    return size3Label;
  }

  public void setSize3Label(String size3Label) {
    this.size3Label = size3Label;
  }
 ///////////////////////////////////////////////
  public MultipartFile getFile4MF() {
    return file4MF;
  }

  public void setFile4MF(MultipartFile file4mf) {
    file4MF = file4mf;
  }

  public String getSize4Label() {
    return size4Label;
  }

  public void setSize4Label(String size4Label) {
    this.size4Label = size4Label;
  }
////////////////////////////////////////////
  public MultipartFile getFile5MF() {
    return file5MF;
  }

  public void setFile5MF(MultipartFile file5mf) {
    file5MF = file5mf;
  }

  public String getSize5Label() {
    return size5Label;
  }

  public void setSize5Label(String size5Label) {
    this.size5Label = size5Label;
  } 
}
