package dev.mvc.qna;

import org.springframework.web.multipart.MultipartFile;

public class QnAVO {
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
  
  /** ���ǰԽ��� ��ȣ */
   private  int      qnano;
   /** ���ǰԽ��� ���� */
   private  String   title="";
   /** ���ǰԽ��� ���� */
   private  String   content="";
   /** ����� */
   private  String   qdate="";
   /** ���ε� ���� */
   private  String   file1="";
   /** ���ε� ���� */
   private  String   file2="";
   /** ���ε� ���� */
   private  String   file3="";
   /** ���ε�� ���� ũ�� */
   private  long     size1= 0;
   /** ���ε�� ���� ũ�� */
   private  long     size2= 0;
   /** ���ε�� ���� ũ�� */
   private  long     size3= 0;
   /** ī�װ� */
   private  int      categoryno;
   /** ȸ�� ���̵� */
   private  String   userid="";
   /** ȸ�� ���� */
   private  String	  nickname="";
   /** ȸ�� ��ȭ */
   private  String	  tel="";
   /** ȸ�� �̸��� */
   private  String	  email="";
   /** ȸ�� ��й�ȣ */
   private  String	  passwd="";
   /** ��ȸ�� */
   private  int	  cnt;
   /** �亯 ���� */
   private  int	  replycnt;
   /** �׷��ȣ */
   private  int	  grpno;
   /** �亯���� */
   private  int	  indent;
   /** �亯 ���� */
   private  int	  ansnum;
   /** �Խù� ���� */
   private int rownum = 0; 
   
   
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

   public QnAVO() {
   }



public QnAVO(int qnano, String title, String content, String qdate, String file1, String file2, String file3,
		long size1, long size2, long size3, int categoryno, String userid, String nickname, String tel, String email,
		String passwd, int cnt, int replycnt, int grpno, int indent, int ansnum, MultipartFile file1mf,
		MultipartFile file2mf, MultipartFile file3mf, MultipartFile file4mf, MultipartFile file5mf, String size1Label,
		String size2Label, String size3Label, String size4Label, String size5Label) {
	this.qnano = qnano;
	this.title = title;
	this.content = content;
	this.qdate = qdate;
	this.file1 = file1;
	this.file2 = file2;
	this.file3 = file3;
	this.size1 = size1;
	this.size2 = size2;
	this.size3 = size3;
	this.categoryno = categoryno;
	this.userid = userid;
	this.nickname = nickname;
	this.tel = tel;
	this.email = email;
	this.passwd = passwd;
	this.cnt = cnt;
	this.replycnt = replycnt;
	this.grpno = grpno;
	this.indent = indent;
	this.ansnum = ansnum;
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



public int getRownum() {
   return rownum;
}



public void setRownum(int rownum) {
   this.rownum = rownum;
}



public int getQnano() {
      return qnano;
   }

   public void setQnano(int qnano) {
      this.qnano = qnano;
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

   public String getQdate() {
      return qdate;
   }

   public void setQdate(String qdate) {
      this.qdate = qdate;
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

   public String getFile2() {
      if (file2 == null){
         file2 = "";
       }
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

   public int getCategoryno() {
      return categoryno;
   }

   public void setCategoryno(int categoryno) {
      this.categoryno = categoryno;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
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



public String getFile3() {
    if (file3 == null){
        file3 = "";
      }
	return file3;
}



public void setFile3(String file3) {
	this.file3 = file3;
}



public long getSize1() {
	return size1;
}



public void setSize1(long size1) {
	this.size1 = size1;
}



public long getSize3() {
	return size3;
}



public void setSize3(long size3) {
	this.size3 = size3;
}



public String getNickname() {
	return nickname;
}



public void setNickname(String nickname) {
	this.nickname = nickname;
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



public String getPasswd() {
	return passwd;
}



public void setPasswd(String passwd) {
	this.passwd = passwd;
}



public int getCnt() {
	return cnt;
}



public void setCnt(int cnt) {
	this.cnt = cnt;
}



public int getReplycnt() {
	return replycnt;
}



public void setReplycnt(int replycnt) {
	this.replycnt = replycnt;
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



public MultipartFile getFile1MF() {
	return file1MF;
}



public void setFile1MF(MultipartFile file1mf) {
	file1MF = file1mf;
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
   
   
   

}