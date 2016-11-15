package dev.mvc.favorite;

public class FavoriteVO {
  
//---------------------------------------
  /** 마이페이지 글 번호 **/
  private int my_no;
  /** 마이페이지 글 수 **/
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
  
  
  /** 위시리스트 번호*/
  private int     f_no;
  /** 닉네임 */
  private String nickname;
  /** url 주소 */
  private String url;
  /** 이름 */
  private String name;
  /** 아이디 */
  private String userid;
  /** 제목 */
  private String title;
  /** 희망 가격 */
  private int hprice;
  
  /** Preview 소형 이미지 200 X 150, 자동 생성됨*/
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
