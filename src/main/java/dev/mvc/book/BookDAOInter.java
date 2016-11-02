package dev.mvc.book;

import java.util.HashMap;
import java.util.List;

public interface BookDAOInter {
   int String = 0;

/**
    * ���� �� ���
    * @param bookvo
    * @return
    */
   public int create(BookVO vo);
   
   /**
    * ���� �� ��ü ���
    * @return
    */
   public List<BookVO> list(HashMap hashmap);
   
   /**
    * �˻��� ���ڵ� ��
    * @param hashmap
    * @return
    */
   public int count(HashMap hashmap);

   /**
    * ���� ī�װ��� ���
    * @param categoryno
    * @return
    */
   public BookVO list(int categoryno);
   
   /**
    * ���� �Խù� ��ȸ
    * @param bookVO
    * @return
    */
   public BookVO read(int bno);
   
   
   /**
    * ���� �Խù� ����
    * @param bno
    * @return
    */
   public int update(BookVO bookVO);
   
   /**
    * ���� �Խù� ����
    * @param bno
    * @return
    */
   public int delete(int bno);
   
   /**
    * ��ȸ�� ����
    * @param bno
    * @return
    */
   public int increaseCnt(int bno);

   
 
}