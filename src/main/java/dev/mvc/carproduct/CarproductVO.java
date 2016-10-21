package dev.mvc.carproduct;

import org.springframework.web.multipart.MultipartFile;

public class CarproductVO {

  /** �߰�����ȣ */
  private int     p_no;
  /** �г��� */
  private String nickname;
  /** ��й�ȣ */
  private String passwd;
  /** ���̵� */
  private String userid;
  /** �ڵ���ī�װ��ڵ� */
  private String category;
  /** �ŷ���� */
  private String deal_way;
  /** �ŷ������ڵ� */
  private String deal_code;
  /** ��ǰ���� */
  private String product_code;
  /** ������� */
  private int     h_price;
  /** �ŷ����� */
  private String region;
  /** ��ȭ��ȣ */
  private String tel;
  /** �̸��� */
  private String email;
  /** ���� */
  private int quantity;
  /** ���� */
  private String title;
  /** ���� */
  private String content;
  /** Preview ���� �̹��� 200 X 150, �ڵ� ������*/
  private String file1;
  /** ���ε� ���� */
  private String file2;
  /** ���ε�� ���� ũ�� */
  private long size2;
  /** ���Խñ� */
  private String purc_date;
  /** �۵���� */
  private String wdate;
  /** ��ȸ�� */
  private int     p_cnt;
  
  /** Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü */  
  private MultipartFile file2MF;
  /** size2�� �ĸ� ���� ��¿� ���� */
  private String size2Label;
  public int getP_no() {
    return p_no;
  }
  public void setP_no(int p_no) {
    this.p_no = p_no;
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
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public String getFile2() {
    return file2;
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
  public int getP_cnt() {
    return p_cnt;
  }
  public void setP_cnt(int p_cnt) {
    this.p_cnt = p_cnt;
  }
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
  
  
}
