package dev.mvc.message; 
/*<style type='text/css'>  
#paging {text-align: center; margin-top: 5px; font-size: 1em;}  
#paging A:link {text-decoration:none; color:black; font-size: 1em;}  
#paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}  
#paging A:visited {text-decoration:none;color:black; font-size: 1em;}  
.span_box_1{    text-align: center;    
font-size: 1em;   
border: 1px;   
border-style: solid;    
border-color: #cccccc;   
padding:1px 6px 1px 6px; ��, ������, �Ʒ�, ����  
margin:1px 2px 1px 2px; ��, ������, �Ʒ�, ����  } 
.span_box_2{    text-align: center;  
background-color: #668db4;   
color: #FFFFFF;    
font-size: 1em;   
border: 1px;  
border-style: solid;   
border-color: #cccccc;  
padding:1px 6px 1px 6px; ��, ������, �Ʒ�, ����  
margin:1px 2px 1px 2px; ��, ������, �Ʒ�, ����  }
</style>

<DIV id='paging'>
<span class='span_box_2'>1</span>
<span class='span_box_1'><A href='./list.do?col=&word=&nowPage=2'>2</A></span></DIV>*/

public class MessagePaging { 
 
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param totalRecord ��ü ���ڵ�� 
   * @param nowPage     ���� ������
   * @param recordPerPage �������� ���ڵ� ��
   * @param col �˻� �÷�  
   * @param word �˻���
   * @param flag ����, ���� �޽��� ���� -> recv, send
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging5(int totalRecord, int nowPage, int recordPerPage, String col, String word, String flag){ 
    int pagePerBlock = 10; // ���� ������ �� 
    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * pagePerBlock);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * pagePerBlock; // 10�� ���� �������� �̵� 
    if (nowGrp >= 2){ 
     // str.append("<span class='span_box_1'><A href='./list.do?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
      str.append("<span class='span_box_1'><A href='./list.do?flag="+flag+"&col="+col+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); 
      }else{ 
        str.append("<span class='span_box_1'><A href='./list.do?flag="+flag+"&col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * pagePerBlock)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list.do?flag="+flag+"&col="+col+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  } 
 
 
} 