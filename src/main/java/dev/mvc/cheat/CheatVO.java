package dev.mvc.cheat;
import org.springframework.web.multipart.MultipartFile;

public class CheatVO {
  /** �Խñ۹�ȣ*/
  private int ctno;
  /** ���� */
  private String title;
  /** �Ű�����*/
  private String gubun;
  /** ���� */
  private String region;
  /** �߻��� */
  private String occurday;
  /** ���رݾ�*/
  private int buyprice;
  /** ������ǰ ����� ���̵�*/
  private String cheatid;
  /** ������ǰ ����� ��ȭ��ȣ*/  
  private String cheattel;
  /** ������ǰ ����� ����*/
  private String cheatemail;
  /** ��ȸ��*/
  private int cnt;
  /** ����*/
  private String content;
  /** ����� ����*/
  private String email;
  /** ����� ��ȭ��ȣ*/
  private String tel;
  /** ����� ���̵�*/
  private String userid;
  /** ����� �г���*/
  private String nickname;
  /** ��й�ȣ*/
  private String passwd;
  /** �ۼ�����*/
  private String wdate;
  /** ÷����������*/
  /** ����� �̹���*/
  private String file1 = "";
  /** ���ε� ���� */
  private String file2 = "";
  /** ���ε�� ���� ũ�� */
  private long size2 = 0;
  
  /** Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  ���� �÷��� �������� ����. */  
  private MultipartFile file2MF;
  
  /** size2�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size2Label; 
  
  public String getFile1() {
    if (file1 == null){
      file1 = "";
    }
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }

  public long getSize2() {
    return size2;
  }

  public void setSize2(long size2) {
    this.size2 = size2;
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

  public void setFile2(String file2) {
    this.file2 = file2;
  }

  public String getFile2() {
  if (file2 == null){
   file2 = "";
  }
  return file2;
  }
  
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public String getWdate() {
    return wdate;
  }
  public void setWdate(String wdate) {
    this.wdate = wdate;
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
  public int getCtno() {
    return ctno;
  }
  public void setCtno(int ctno) {
    this.ctno = ctno;
  }
  public String getGubun() {
    return gubun;
  }
  public void setGubun(String gubun) {
    this.gubun = gubun;
  }
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getOccurday() {
    return occurday;
  }
  public void setOccurday(String occurday) {
    this.occurday = occurday;
  }
  public int getBuyprice() {
    return buyprice;
  }
  public void setBuyprice(int buyprice) {
    this.buyprice = buyprice;
  }
  public String getCheatid() {
    return cheatid;
  }
  public void setCheatid(String cheatid) {
    this.cheatid = cheatid;
  }
  public String getCheattel() {
    return cheattel;
  }
  public void setCheattel(String cheattel) {
    this.cheattel = cheattel;
  }
  public String getCheatemail() {
    return cheatemail;
  }
  public void setCheatemail(String cheatemail) {
    this.cheatemail = cheatemail;
  }
  
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  
  public CheatVO(){}
  
}
