package dev.mvc.qna;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.qna.QnADAOInter;
import dev.mvc.qna.QnAVO;
import dev.mvc.tmember.MemberDAOInter;
import dev.mvc.tmember.MemberVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;
 
@Controller
public class QnACont {
  @Autowired
  @Qualifier("dev.mvc.qna.QnADAO")
  private QnADAOInter qnaDAO;
  
  @Autowired
  @Qualifier("dev.mvc.tmember.MemberDAO")
  private MemberDAOInter memberDAO;
  
  
  /**
   * ��ü ����� ����մϴ�.
   * @return
   */

  @RequestMapping(value = "/qna/list.do", 
                             method = RequestMethod.GET)
  public ModelAndView list(QnAVO qnavo, SearchDTO searchDTO,
        HttpServletRequest request) {
     
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/list"); // /webapp/code/list.jsp

    int totalRecord = 0;
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    
    
    int recordPerPage = 10;
    int beginOfPage = (searchDTO.getNowPage() -1) * 10;
    
    int startNum = beginOfPage + 1 ;
    int endNum = beginOfPage + recordPerPage;
      hashMap.put("startNum", startNum);
      hashMap.put("endNum", endNum);
      
    List<QnAVO> list = qnaDAO.list(hashMap);
    Iterator<QnAVO> iter = list.iterator();
    while (iter.hasNext() == true) {
       QnAVO vo = iter.next();
       int rownum = (qnavo.getRownum());
       qnavo.setRownum(rownum + 1);
       vo.setRownum(qnavo.getRownum());
       vo.setTitle(Tool.textLength(vo.getTitle(), 10));
       vo.setQdate(vo.getQdate());
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    totalRecord = qnaDAO.count(hashMap);
    mav.addObject("totalRecord", qnaDAO.count(hashMap));
    
    String paging = new Paging().paging5(
          totalRecord,
          searchDTO.getNowPage(),
          recordPerPage,
          searchDTO.getCol(),
          searchDTO.getWord());
    
    mav.addObject("paging", paging);
    
    
    
    return mav;
  }

  /**
   * ���̵� ��� ����մϴ�
   * @return
   */
/*
  @RequestMapping(value = "/qna/idlist.do", 
        method = RequestMethod.GET)
  public ModelAndView idlist(String userid) {
   
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/qna/idlist"); // /webapp/code/list.jsp
      userid = "chanmi";
      List<QnAVO> idlist = qnaDAO.idlist(userid);
      mav.addObject("idlist", qnaDAO.idlist(userid));
      
      return mav;
  }
*/  

  public QnACont(){
    System.out.println("--> QnACont created.");
  }
  
  @RequestMapping(value = "/qna/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView(); 
    mav.setViewName("/qna/create"); // /webapp/code/create.jsp
    
    MemberVO memberVO = memberDAO.read_userid("chanmi");
    System.out.println(memberVO.getEmail());
    System.out.println(memberVO.getNickname());
    System.out.println(memberVO.getPwd());
    mav.addObject("memberVO", memberVO);
    
    return mav;
  }
  
  @RequestMapping(value = "/qna/create.do", method = RequestMethod.POST)
  public ModelAndView create(QnAVO qnaVO,
        HttpServletRequest request, 
        HttpSession session) {
    System.out.println("--> create() POST called.");
    System.out.println("-->"+qnaVO.getContent());
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String file3 = "";
    long size1 = 0;
    long size2 = 0;
    long size3 = 0;
    String upDir = Tool.getRealPath(request, "/qna/storage");
    MultipartFile file1MF = qnaVO.getFile1MF();
    MultipartFile file2MF = qnaVO.getFile2MF();
    MultipartFile file3MF = qnaVO.getFile3MF();
    System.out.println("file2MF:"+file2MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size1 = file1MF.getSize();
    size2 = file2MF.getSize();
    size3 = file3MF.getSize();
    
    if (size1 > 0) {
      file1 = Upload.saveFileSpring(file1MF, upDir);
      // -------------------------------------------------------------------
    }
    if (size2 > 0) {
    	file2 = Upload.saveFileSpring(file2MF, upDir);
    	// -------------------------------------------------------------------
    }
    if (size3 > 0) {
    	file3 = Upload.saveFileSpring(file3MF, upDir);
    	// -------------------------------------------------------------------
    }
    qnaVO.setFile1(file1); // ���� �̹���
    qnaVO.setFile2(file2); // ���� �̹���
    qnaVO.setFile3(file3); // ���� �̹���
    qnaVO.setSize1(size1); // ���� �̹���
    qnaVO.setSize2(size2); // ���� �̹���
    qnaVO.setSize3(size3); // ���� �̹���
    // -------------------------------------------------------------------
      
    qnaVO.setUserid("chanmi"); // ȸ�� ������ ����
  /*    Integer itg = (Integer)(session.getAttribute("mno"));
    qnaVO.setMno(itg.intValue());*/
    
 
    if (qnaDAO.create(qnaVO) == 1) { 
      msgs.add("��ϵǾ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./create.jsp'\">��ӵ��</button>");
    } else {
      msgs.add("����� �����߽��ϴ�.");
      msgs.add("�ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
     
   @RequestMapping(value = "/qna/reply.do", 
           method = RequestMethod.GET)
   public ModelAndView reply(QnAVO qnavo) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/qna/reply"); // /webapp/qna/reply.jsp
   MemberVO memberVO = memberDAO.read_userid("chanmi");
   System.out.println(memberVO.getEmail());
   System.out.println(memberVO.getNickname());
   System.out.println(memberVO.getPwd());
   mav.addObject("memberVO", memberVO);
   mav.addObject("qnaVO", qnavo);
   
   return mav;
   }
   
   @RequestMapping(value = "/qna/reply.do", method = RequestMethod.POST)
   public ModelAndView reply(QnAVO qnaVO, HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/qna/message");
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // ���� ����
   // -------------------------------------------------------------------
   String file1 = "";
   String file2 = "";
   String file3 = "";
   long size1 = 0;
   long size2 = 0;
   long size3 = 0;
   String upDir = Tool.getRealPath(request, "/qna/storage");
   MultipartFile file1MF = qnaVO.getFile1MF();
   MultipartFile file2MF = qnaVO.getFile2MF();
   MultipartFile file3MF = qnaVO.getFile3MF();
   System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size1 = file1MF.getSize();
   size2 = file2MF.getSize();
   size3 = file3MF.getSize();
   
   if (size1 > 0) {
     file1 = Upload.saveFileSpring(file1MF, upDir);
     // -------------------------------------------------------------------
   }
   if (size2 > 0) {
     file2 = Upload.saveFileSpring(file2MF, upDir);
     // -------------------------------------------------------------------
   }
   if (size3 > 0) {
     file3 = Upload.saveFileSpring(file3MF, upDir);
     // -------------------------------------------------------------------
   }
   qnaVO.setFile1(file1); // ���� �̹���
   qnaVO.setFile2(file2); // ���� �̹���
   qnaVO.setFile3(file3); // ���� �̹���
   qnaVO.setSize1(size1); // ���� �̹���
   qnaVO.setSize2(size2); // ���� �̹���
   qnaVO.setSize3(size3); // ���� �̹���
   // -------------------------------------------------------------------
   
   qnaVO.setUserid("chanmi"); // ȸ�� ������ ����
   // -------------------------------------------------------------------
   
   // ---------- �亯 ���� �ڵ� ���� ---------- 
   QnAVO parentVO = qnaDAO.read(qnaVO.getQnano()); // �θ�� ���� ����
   
   qnaVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ
   qnaVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
   
   
   System.out.println("�׽�Ʈ�׽�Ʈ" + parentVO.getGrpno());
   
   
   qnaDAO.updateAnsnum(qnaVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
   
   qnaVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
   qnaVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
   // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   if (qnaDAO.reply(qnaVO) == 1) {
   msgs.add("���� ����߽��ϴ�.");
   links
   .add("<button type='button' onclick=\"location.href='./create.do'>��� ���</button>");
   } else {
   msgs.add("�� ��Ͽ� �����߽��ϴ�.");
   msgs.add("�ٽ� �õ����ּ���.");
   links
   .add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
   }
   
   links
   .add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
   links
   .add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);
   
   return mav;
   }
  
  /**
   * ���� ��ȸ�մϴ�
   * @param mno
   * @return
   */
  @RequestMapping(value = "/qna/read.do", method = RequestMethod.GET)
  public ModelAndView read(int qnano, 
                                SearchDTO searchDTO,
                                      HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/read");
    QnAVO qnaVO = qnaDAO.read(qnano);
    qnaDAO.increaseCnt(qnaVO.getQnano());
    mav.addObject("qnaVO", qnaVO); 
    
    String content = qnaVO.getContent();
    content = Tool.convertChar(content);
    qnaVO.setContent(content);
    mav.addObject("qnaVO",qnaVO);

    // �Խ��ǿ� ���� ���� �ľ�
    mav.addObject("searchDTO", searchDTO);
 
    return mav;
  }
  
  
  /**
   * ������
   * @param qnano
   * @return
   */
  @RequestMapping(value = "/qna/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int qnano) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/delete"); // /webapp/member/delete.jsp
    mav.addObject("qnano", qnano);
    
    return mav;
  }
  
  /**
   * ���� ó��
   * @param qnaVO
   * @return
   */
  @RequestMapping(value = "/qna/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(QnAVO qnaVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (qnaDAO.delete(qnaVO.getQnano()) == 1) {
       mav.setViewName("redirect:/qna/list.do");//Ȯ���� ���
 
    } else {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do\">���</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  /**
   * ������
   * @param qnano
   * @return
   */
  @RequestMapping(value="/qna/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int qnano){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/update"); 
    mav.addObject("qnaVO", qnaDAO.read(qnano)); 
 
    return mav;
 
  }
   
  /**
   * �۰� ������ ���� ó��
   * 
   * @param qnaVO
   * @return
   */
  @RequestMapping(value = "/qna/update.do", method = RequestMethod.POST)
  public ModelAndView update2(QnAVO qnaVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String file3 = "";
    long size1 = 0;
    long size2 = 0;
    long size3 = 0;
    
    String upDir = Tool.getRealPath(request, "/qna/storage");
    MultipartFile file1MF = qnaVO.getFile1MF();
    MultipartFile file2MF = qnaVO.getFile2MF();
    MultipartFile file3MF = qnaVO.getFile3MF();
    System.out.println("file2MF:"+file2MF);
    QnAVO oldVO = qnaDAO.read(qnaVO.getQnano());
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size1 = file1MF.getSize();
    size2 = file2MF.getSize();
    size3 = file3MF.getSize();
    if (size1 > 0) {
      file1 = Upload.saveFileSpring(file1MF, upDir);
      qnaVO.setFile1(file1); // ���ο� ���۵� ���ϸ� ����
      qnaVO.setSize1(file1MF.getSize()); //���ο� ũ�� ���� 
    }else{
       file1 = oldVO.getFile1(); // ���� ���ε带 ���� �ʴ� ���
    }
    if (size2 > 0) {
    	file2 = Upload.saveFileSpring(file2MF, upDir);
    	qnaVO.setFile2(file2); // ���ο� ���۵� ���ϸ� ����
    	qnaVO.setSize2(file2MF.getSize()); //���ο� ũ�� ���� 
    }else{
    	file2 = oldVO.getFile2(); // ���� ���ε带 ���� �ʴ� ���
    }
    if (size3 > 0) {
    	file3 = Upload.saveFileSpring(file3MF, upDir);
    	qnaVO.setFile3(file3); // ���ο� ���۵� ���ϸ� ����
    	qnaVO.setSize3(file3MF.getSize()); //���ο� ũ�� ���� 
    }else{
    	file3 = oldVO.getFile3(); // ���� ���ε带 ���� �ʴ� ���
    }
    
       qnaVO.setFile1(file1); // ���� �̹���
       qnaVO.setFile2(file2); // ���� �̹���
       qnaVO.setFile3(file3); // ���� �̹���
       qnaVO.setSize1(size1); // ���� �̹��� ������
       qnaVO.setSize2(size2); // ���� �̹��� ������
       qnaVO.setSize3(size3); // ���� �̹��� ������
    // -------------------------------------------------------------------
       
       qnaVO.setUserid("chanmi"); // ȸ�� ������ ����
       
    // -------------------------------------------------------------------
       
    if (qnaDAO.update(qnaVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/qna/read.do?qnano=" + qnaVO.getQnano()); // Ȯ���� ���
    } else {
      msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
      links
          .add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links
          .add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
}
 