package dev.mvc.book;

import org.springframework.web.multipart.MultipartFile;

public class Member_BookVO {
   /** book ��ȣ */
   private  int      bno;
   /** �Խù� ���� */
   private  String   title="";
   /** �ŷ����� */
   private  String   deal_code="";
   /** ��ǰ���� */
   private  String   product_code="";
   /** �� ī�װ� */
   private  String   category="";
   /** ���� */
   private  String   region="";
   /** �ŷ���� */
   private  String   deal_way="";
   /** �ŷ����� */
   private  String   deal_state="";
   /** �������� */
   private  String   purc_date="";
   /** �Ǹż��� */
   private  int      quantity; 
   /** ������� */
   private  int      hprice;
   /** å���� */
   private  String   btitle="";
   /** å���ǻ� */
   private  String   publisher="";
   /** å���� */
   private  String   bwriter="";
   /** �Խù� ���� */
   private  String   content="";
   /** ��ȸ�� */
   private  int      cnt;
   /** ���̵� */
   private  String   userid="";
   /** ������� */
   private  String   wdate="";
   /** ����� �̹��� */
   private  String   thumb="";
   /** ���ε� ���� */
   private  String   file1="";
   /** ���ε� ���� */
   private  String   file2="";
   /** ���ε� ���� */
   private  String   file3=""; 
   /** ���ε� ���� */
   private  String   file4="";
   /** ���ε� ���� */
   private  String   file5="";
   /** ���ε� �� ���� ũ��*/
   private  long     size1;
   /** ���ε� �� ���� ũ��*/
   private  long     size2;
   /** ���ε� �� ���� ũ��*/
   private  long     size3;
   /** ���ε� �� ���� ũ��*/
   private  long     size4;
   /** ���ε� �� ���� ũ��*/
   private  long     size5;
   
   /** �г��� */
   private String nickname;
   /** ��й�ȣ */
   private String passwd;
   /** ��ȭ��ȣ */
   private String tel;
   /** �̸��� */
   private String email;
   
   
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
   
   
   
   public Member_BookVO() {
      // TODO Auto-generated constructor stub
   }
   
   

   

   

   public Member_BookVO(int bno, String title, String deal_code, String product_code, String category, String region,
         String deal_way, String deal_state, String purc_date, int quantity, int hprice, String btitle,
         String publisher, String bwriter, String content, int cnt, String userid, String wdate, String thumb,
         String file1, String file2, String file3, String file4, String file5, long size1, long size2, long size3,
         long size4, long size5, String nickname, String passwd, String tel, String email, MultipartFile file1mf,
         MultipartFile file2mf, MultipartFile file3mf, MultipartFile file4mf, MultipartFile file5mf, String size1Label,
         String size2Label, String size3Label, String size4Label, String size5Label) {
      this.bno = bno;
      this.title = title;
      this.deal_code = deal_code;
      this.product_code = product_code;
      this.category = category;
      this.region = region;
      this.deal_way = deal_way;
      this.deal_state = deal_state;
      this.purc_date = purc_date;
      this.quantity = quantity;
      this.hprice = hprice;
      this.btitle = btitle;
      this.publisher = publisher;
      this.bwriter = bwriter;
      this.content = content;
      this.cnt = cnt;
      this.userid = userid;
      this.wdate = wdate;
      this.thumb = thumb;
      this.file1 = file1;
      this.file2 = file2;
      this.file3 = file3;
      this.file4 = file4;
      this.file5 = file5;
      this.size1 = size1;
      this.size2 = size2;
      this.size3 = size3;
      this.size4 = size4;
      this.size5 = size5;
      this.nickname = nickname;
      this.passwd = passwd;
      this.tel = tel;
      this.email = email;
      file1MF = file1mf;
      file2MF = file2mf;
      file3MF = file3mf;
      file4MF = file4mf;
      file5MF = file5mf;
      this.size1Label = size1Label;
      this.size2Label = size2Label;
      this.size3Label = size3Label;
      this.size4Label = size4Label;
      this.size5Label = size5Label;
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




   public String getSize2Label() {
      return size2Label;
   }




   public void setSize2Label(String size2Label) {
      this.size2Label = size2Label;
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




   public int getBno() {
      return bno;
   }

   public void setBno(int bno) {
      this.bno = bno;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
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

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getRegion() {
      return region;
   }

   public void setRegion(String region) {
      this.region = region;
   }

   public String getDeal_way() {
      return deal_way;
   }

   public void setDeal_way(String deal_way) {
      this.deal_way = deal_way;
   }

   public String getDeal_state() {
      return deal_state;
   }

   public void setDeal_state(String deal_state) {
      this.deal_state = deal_state;
   }

   public String getPurc_date() {
      return purc_date;
   }

   public void setPurc_date(String purc_date) {
      this.purc_date = purc_date;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public int getHprice() {
      return hprice;
   }

   public void setHprice(int hprice) {
      this.hprice = hprice;
   }

   public String getBtitle() {
      return btitle;
   }

   public void setBtitle(String btitle) {
      this.btitle = btitle;
   }

   public String getPublisher() {
      return publisher;
   }

   public void setPublisher(String publisher) {
      this.publisher = publisher;
   }

   public String getBwriter() {
      return bwriter;
   }

   public void setBwriter(String bwriter) {
      this.bwriter = bwriter;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public int getCnt() {
      return cnt;
   }

   public void setCnt(int cnt) {
      this.cnt = cnt;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
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

   public String getFile3() {
      return file3;
   }

   public void setFile3(String file3) {
      this.file3 = file3;
   }

   public String getFile4() {
      return file4;
   }

   public void setFile4(String file4) {
      this.file4 = file4;
   }

   public String getFile5() {
      return file5;
   }

   public void setFile5(String file5) {
      this.file5 = file5;
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
   
   
}


