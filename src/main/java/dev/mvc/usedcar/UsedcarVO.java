package dev.mvc.usedcar;

import java.util.Date;

import oracle.sql.DATE;

public class UsedcarVO {
  

  private int     u_no;
  /** �߰�����ȣ */
  private String nickname;
  /** �г��� */
  private String passwd;
  /** ��й�ȣ */
  private String userid;
  /** ���̵� */
  private String category;
  /** �ڵ���ī�װ��ڵ� */
  private String deal_way;
  /** �ŷ���� */
  private String deal_code;
  /** �ŷ������ڵ� */
  private String product_code;
  /** ��ǰ���� */
  private int     h_price;
  /** ������� */
  private String region;
  /** �ŷ����� */
  private String tel;
  /** ��ȭ��ȣ */
  private String email;
  /** �̸��� */
  private int quantity;
  /** ���� */
  private String title;
  /** ���� */
  private String content;
  /** ���� */
  private String purc_date;
  /** ���Խñ� */
  private DATE wdate;
  /** �۵���� */
  private int     u_cnt;
  /** ��ȸ�� */
  
  public int getU_no() {
    return u_no;
  }
  public void setU_no(int u_no) {
    this.u_no = u_no;
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
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
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
  public int getH_price() {
    return h_price;
  }
  public void setH_price(int h_price) {
    this.h_price = h_price;
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
  public DATE getWdate() {
    return wdate;
  }
  public void setWdate(DATE wdate) {
    this.wdate = wdate;
  }
  public int getU_cnt() {
    return u_cnt;
  }
  public void setU_cnt(int u_cnt) {
    this.u_cnt = u_cnt;
  }
  


  
}
