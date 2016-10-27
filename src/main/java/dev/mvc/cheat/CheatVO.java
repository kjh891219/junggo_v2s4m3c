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
  private String thumb = "";
  /** ���ε� ���� */
  private String file1 = "";
  /** ���ε�� ���� ũ�� */
  private long size1 = 0;
  /** ���ε� ���� */
  private String file2 = "";
  /** ���ε�� ���� ũ�� */
  private long size2 = 0;
  /** ���ε� ���� */
  private String file3 = "";
  /** ���ε�� ���� ũ�� */
  private long size3 = 0;
  /** ���ε� ���� */
  private String file4 = "";
  /** ���ε�� ���� ũ�� */
  private long size4 = 0;
  /** ���ε� ���� */
  private String file5 = "";
  /** ���ε�� ���� ũ�� */
  private long size5 = 0;
  
  /** Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  ���� �÷��� �������� ����. */  
  private MultipartFile file1MF;
  private MultipartFile file2MF;
  private MultipartFile file3MF;
  private MultipartFile file4MF;
  private MultipartFile file5MF;
  
  /** size2�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size1Label; 
  private String size2Label; 
  private String size3Label; 
  private String size4Label; 
  private String size5Label; 
  
  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  
  public long getSize1() {
    return size1;
  }
  public void setSize1(long size1) {
    this.size1 = size1;
  }
  
  public long getSize2() {
    return size2;
  }

  public void setSize2(long size2) {
    this.size2 = size2;
  }
  
  public long getSize3() {
    return size3;
  }
  public void setSize3(long size3) {
    this.size3 = size3;
  }
 
  public long getSize4() {
    return size4;
  }
  public void setSize4(long size4) {
    this.size4 = size4;
  }
  
  public long getSize5() {
    return size5;
  }
  public void setSize5(long size5) {
    this.size5 = size5;
  }
  
  public String getFile1() {
    if (file1 == null){
      file1 = "";
    }
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
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

  public String getFile3() {
    if (file3 == null){
      file3 = "";
    }
    return file3;
  }
  public void setFile3(String file3) {
    this.file3 = file3;
  }
 
  public String getFile4() {
    if (file4 == null){
      file4 = "";
    }
    return file4;
  }
  public void setFile4(String file4) {
    this.file4 = file4;
  }
  
  public String getFile5() {
    if (file5 == null){
      file5 = "";
    }
    return file5;
  }
  public void setFile5(String file5) {
    this.file5 = file5;
  }
 
 
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public MultipartFile getFile2MF() {
    return file2MF;
  }
  public void setFile2MF(MultipartFile file2mf) {
    file2MF = file2mf;
  }
  public MultipartFile getFile3MF() {
    return file3MF;
  }
  public void setFile3MF(MultipartFile file3mf) {
    file3MF = file3mf;
  }
  public MultipartFile getFile4MF() {
    return file4MF;
  }
  public void setFile4MF(MultipartFile file4mf) {
    file4MF = file4mf;
  }
  public MultipartFile getFile5MF() {
    return file5MF;
  }
  public void setFile5MF(MultipartFile file5mf) {
    file5MF = file5mf;
  }
  public String getSize1Label() {
    return size1Label;
  }
  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }
  public String getSize3Label() {
    return size3Label;
  }
  public void setSize3Label(String size3Label) {
    this.size3Label = size3Label;
  }
  public String getSize4Label() {
    return size4Label;
  }
  public void setSize4Label(String size4Label) {
    this.size4Label = size4Label;
  }
  public String getSize5Label() {
    return size5Label;
  }
  public void setSize5Label(String size5Label) {
    this.size5Label = size5Label;
  }
  public String getSize2Label() {
    return size2Label;
  }

  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
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
