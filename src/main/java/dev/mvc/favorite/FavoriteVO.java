package dev.mvc.favorite;

public class FavoriteVO {
  
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
  
  
  /** ���ø���Ʈ ��ȣ*/
  private int     f_no;
  /** �г��� */
  private String nickname;
  /** url �ּ� */
  private String url;
  /** �̸� */
  private String name;
  /** ���̵� */
  private String userid;
  /** ���� */
  private String title;
  /** ��� ���� */
  private int hprice;
  
  /** Preview ���� �̹��� 200 X 150, �ڵ� ������*/
  private String thumb;

  public int getF_no() {
    return f_no;
  }

  public void setF_no(int f_no) {
    this.f_no = f_no;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getThumb() {
    return (thumb != null)?thumb:"";
  }

  public void setThumb(String thumb) {
    this.thumb = thumb;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int gethprice() {
    return hprice;
  }

  public void sethprice(int hprice) {
    this.hprice = hprice;
  }
  
}
