package dev.mvc.book;
 
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

import dev.mvc.tmember.MemberDAOInter;
import dev.mvc.tmember.MemberVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;
 
@Controller
public class BookCont {
  @Autowired
  @Qualifier("dev.mvc.book.BookDAO")
  private BookDAOInter bookDAO;

  @Autowired
  @Qualifier("dev.mvc.tmember.MemberDAO")
  private MemberDAOInter memberDAO;
  

  public BookCont(){
    System.out.println("--> BookCont created.");
  }
  
  /**
   * ��ü ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/book/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO,
          HttpServletRequest request) {
	  ModelAndView mav = new ModelAndView();
	  mav.setViewName("/book/list"); // /webapp/member/list.jsp
	  
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
	    
	  List<BookVO> list = bookDAO.list(hashMap);
	  Iterator<BookVO> iter = list.iterator();
	  while (iter.hasNext() == true) {
		  BookVO vo = iter.next();
		  vo.setTitle(Tool.textLength(vo.getTitle(), 10));
		  vo.setWdate(vo.getWdate().substring(0, 10));
	  }
	  
	  mav.addObject("list", list);
	  mav.addObject("root", request.getContextPath());
	  
	  totalRecord = bookDAO.count(hashMap);
	  mav.addObject("totalRecord", bookDAO.count(hashMap));
	  
			  
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
   * �Խù� ���
   * @return 
   */
  @RequestMapping(value = "/book/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView(); 
    mav.setViewName("/book/create"); // /webapp/code/create.jsp
    
 
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString();
    String tel = session.getAttribute("tel").toString();
    String email = session.getAttribute("email").toString();
    
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    mav.addObject("tel", tel);
    mav.addObject("email", email);
    
    return mav;
  }
  
  @RequestMapping(value = "/book/create.do", method = RequestMethod.POST)
  public ModelAndView create(BookVO bookVO,
        HttpServletRequest request, 
        HttpSession session) {
    System.out.println("--> create() POST called.");
    System.out.println("-->"+bookVO.getContent());
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/book/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String thumb = "";
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
    long size1 = 0;
    long size2 = 0;
    long size3 = 0;
    long size4 = 0;
    long size5 = 0;
    
    String upDir = Tool.getRealPath(request, "/book/storage");
    MultipartFile file1MF = bookVO.getFile1MF();
    MultipartFile file2MF = bookVO.getFile2MF();
    MultipartFile file3MF = bookVO.getFile3MF();
    MultipartFile file4MF = bookVO.getFile4MF();
    MultipartFile file5MF = bookVO.getFile5MF();
    
    System.out.println("file1MF:"+file1MF);
    System.out.println("file2MF:"+file2MF);
    System.out.println("file3MF:"+file3MF);
    System.out.println("file4MF:"+file4MF);
    System.out.println("file5MF:"+file5MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size1 = file1MF.getSize();
    size2 = file2MF.getSize();
    size3 = file3MF.getSize();
    size4 = file4MF.getSize();
    size5 = file5MF.getSize();
    
    if (file1MF.getSize() > 0) {
      file1 = Upload.saveFileSpring(file1MF, upDir);
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file1)) {
        thumb = Tool.preview(upDir, file1, 120, 80);
      } else {
        thumb = "";
      }
      
      // -------------------------------------------------------------------
    }
    if (file2MF.getSize() > 0) {
       file2 = Upload.saveFileSpring(file2MF, upDir);
    }
    if (file3MF.getSize() > 0) {
       file3 = Upload.saveFileSpring(file3MF, upDir);
    }
    if (file4MF.getSize() > 0) {
       file4 = Upload.saveFileSpring(file4MF, upDir);
    }
    if (file5MF.getSize() > 0) {
       file5 = Upload.saveFileSpring(file5MF, upDir);
    }
    
    bookVO.setThumb(thumb); // Thumb �̹���
    bookVO.setFile1(file1); // ���� �̹���
    bookVO.setFile2(file2); // ���ε� �̹���
    bookVO.setFile3(file3); // ���ε� �̹���
    bookVO.setFile4(file4); // ���ε� �̹���
    bookVO.setFile5(file5); // ���ε� �̹���
    bookVO.setSize1(size1); // ���� �̹���
    bookVO.setSize2(size2); // ���� �̹���
    bookVO.setSize2(size3); // ���� �̹���
    bookVO.setSize2(size4); // ���� �̹���
    bookVO.setSize2(size5); // ���� �̹���
    // -------------------------------------------------------------------
      
    
  /*    Integer itg = (Integer)(session.getAttribute("mno"));
    bookVO.setMno(itg.intValue());*/
    
 
    if (bookDAO.create(bookVO) == 1) { 
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
  
  

  
  /**
   * �Խù� ��ȸ
   * @param bno
   * @return
   */
  @RequestMapping(value = "/book/read.do", method = RequestMethod.GET)
  public ModelAndView read(int bno, 
          SearchDTO searchDTO, 
          HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/book/read");
    

    BookVO bookVO = bookDAO.read(bno);
    bookDAO.increaseCnt(bookVO.getBno());

    String content = bookVO.getContent();
    content = Tool.convertChar(content);
    bookVO.setContent(content);
    
    mav.addObject("searchDTO",searchDTO);
    
    mav.addObject("root", request.getContextPath());
    
   
    mav.addObject("bookVO", bookVO);
    
    return mav;
  }
  

  /**
   * ������
   * @param bno
   * @return
   */
  @RequestMapping(value="/book/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int bno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/book/update"); 
    mav.addObject("bookVO", bookDAO.read(bno)); 
    return mav;
 
  }
  
  /**
   * �۰� ������ ���� ó��
   * 
   * @param bookVO
   * @return
   */
  @RequestMapping(value = "/book/update.do", method = RequestMethod.POST)
  public ModelAndView update(BookVO bookVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String thumb = "";
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
    long size1 = 0;
    long size2 = 0;
    long size3 = 0;
    long size4 = 0;
    long size5 = 0;
    
    String upDir = Tool.getRealPath(request, "/book/storage");
    MultipartFile file1MF = bookVO.getFile1MF();
    MultipartFile file2MF = bookVO.getFile2MF();
    MultipartFile file3MF = bookVO.getFile3MF();
    MultipartFile file4MF = bookVO.getFile4MF();
    MultipartFile file5MF = bookVO.getFile5MF();
    
    BookVO oldVO = bookDAO.read(bookVO.getBno());
    
    System.out.println("file1MF:"+file1MF);
    System.out.println("file2MF:"+file2MF);
    System.out.println("file3MF:"+file3MF);
    System.out.println("file4MF:"+file4MF);
    System.out.println("file5MF:"+file5MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size1 = file1MF.getSize();
    size2 = file2MF.getSize();
    size3 = file3MF.getSize();
    size4 = file4MF.getSize();
    size5 = file5MF.getSize();
    
    if (file1MF.getSize() > 0) {
      Tool.deleteFile(upDir, oldVO.getFile1()); 
      file1 = Upload.saveFileSpring(file1MF, upDir);
      bookVO.setFile1(file1);
      bookVO.setSize1(size1);
      
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file1)) {
    	  Tool.deleteFile(upDir, oldVO.getThumb());
        thumb = Tool.preview(upDir, file1, 120, 80);
      } else {
        thumb = "";
      }
      
      // -------------------------------------------------------------------
    } else{
      thumb 	   = oldVO.getThumb();
    	file1 		= oldVO.getFile1();
    }
    
    if (file2MF.getSize() > 0) {
    	Tool.deleteFile(upDir, oldVO.getFile2()); 
    	file2 = Upload.saveFileSpring(file2MF, upDir);
    	bookVO.setFile1(file2);
    	bookVO.setSize1(size2);
    	
    } else{
    	file2 		= oldVO.getFile2();
    }
    
    if (file3MF.getSize() > 0) {
    	Tool.deleteFile(upDir, oldVO.getFile3()); 
    	file3 = Upload.saveFileSpring(file3MF, upDir);
    	bookVO.setFile1(file3);
    	bookVO.setSize1(size3);
    	
    } else{
    	file3 		= oldVO.getFile3();
    }
    
    if (file4MF.getSize() > 0) {
    	Tool.deleteFile(upDir, oldVO.getFile4()); 
    	file4 = Upload.saveFileSpring(file4MF, upDir);
    	bookVO.setFile1(file4);
    	bookVO.setSize1(size4);
    	
    } else{
    	file4 		= oldVO.getFile4();
    }
    if (file5MF.getSize() > 0) {
    	Tool.deleteFile(upDir, oldVO.getFile5()); 
    	file5 = Upload.saveFileSpring(file5MF, upDir);
    	bookVO.setFile1(file5);
    	bookVO.setSize1(size5);
    	
    } else{
    	file5 		= oldVO.getFile5();
    }
    
    bookVO.setThumb(thumb); // Thumb �̹���
    bookVO.setFile1(file1); // ���� �̹���
    bookVO.setFile2(file2); // ���ε� �̹���
    bookVO.setFile3(file3); // ���ε� �̹���
    bookVO.setFile4(file4); // ���ε� �̹���
    bookVO.setFile5(file5); // ���ε� �̹���
    bookVO.setSize1(size1); // ���� �̹���
    bookVO.setSize2(size2); // ���� �̹���
    bookVO.setSize2(size3); // ���� �̹���
    bookVO.setSize2(size4); // ���� �̹���
    bookVO.setSize2(size5); // ���� �̹���
    // -------------------------------------------------------------------
      
    if (bookDAO.update(bookVO) == 1) {
        // ������ ��ȸ�� �ڵ� �̵�
        mav.setViewName("redirect:/book/read.do?bno=" + bookVO.getBno()); // Ȯ���� ���
      } else {
        msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
        links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
        links.add("<button type='button' onclick=\"location.href='./list.do'>���</button>");
        mav.addObject("msgs", msgs);
        mav.addObject("links", links);
      }
    
    return mav;
  }
  
  /**
   * ������
   * @param bno
   * @return
   */
  @RequestMapping(value = "/book/delete.do", 
                              method = RequestMethod.GET)
  public ModelAndView delete(int bno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/book/delete"); // /webapp/book/delete.jsp
    mav.addObject("bno", bno);
    return mav;
  }
  
  /**
   * ���� ó�� 
   * @param bookVO
   * @return
   */
  @RequestMapping(value = "/book/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(BookVO bookVO) {
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (bookDAO.delete(bookVO.getBno()) == 1) {
      
      mav.setViewName("redirect:/book/list.do");//Ȯ���� ���

    } else {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'>���</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
}
 