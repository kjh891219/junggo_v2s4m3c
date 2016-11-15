package dev.mvc.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class MobileCont {

  @Autowired
  @Qualifier("dev.mvc.mobile.MobileDAO")
  private MobileDAOInter mobileDAO;
  
  @RequestMapping(value = "/mobile/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/create");
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "mobile/list.do");//
     
      
      
    } else {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>" 
          + "location.href = './create.jsp';"
          + "</script>");
      
    }
    
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString();
    String nickname = session.getAttribute("nickname").toString();
    String email = session.getAttribute("email").toString();
    String tel = session.getAttribute("tel").toString();
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    mav.addObject("nickname", nickname);
    mav.addObject("email", email);
    mav.addObject("tel", tel);
    
    
    return mav;
  }
  
  @RequestMapping(value = "/mobile/create.do", method = RequestMethod.POST)
  public ModelAndView create(MobileVO mobileVO, HttpServletRequest request,  HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

 // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
    String file6 = "";
    String file7 = "";
    String file8 = "";
    String file9 = "";
    String file10 = "";
    long size2 = 0;
    long size4 = 0;
    long size6 = 0;
    long size8 = 0;
    long size10 = 0;
    String upDir = Tool.getRealPath(request, "/mobile/storage");
    MultipartFile file2MF = mobileVO.getFile2MF();
    MultipartFile file4MF = mobileVO.getFile4MF();
    MultipartFile file6MF = mobileVO.getFile6MF();
    MultipartFile file8MF = mobileVO.getFile8MF();
    MultipartFile file10MF = mobileVO.getFile10MF();
    size2 = file2MF.getSize();

    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      mobileVO.setFile2(file2); // ���۵� ���ϸ� ����
      mobileVO.setSize2(file2MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) {
        file1 = Tool.preview(upDir, file2, 120, 80);
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------
    }
    mobileVO.setFile1(file1); // Thumb �̹���
    mobileVO.setFile2(file2); // ���� �̹���
    mobileVO.setSize2(size2); // ���� �̹���
   
    size4 = file4MF.getSize();

    // -------------------------------------------------------------------
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir);
      mobileVO.setFile4(file4); // ���۵� ���ϸ� ����
      mobileVO.setSize4(file4MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file3)) {
        file3 = Tool.preview(upDir, file4, 120, 80);
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
    }
    mobileVO.setFile3(file3); // Thumb �̹���
    mobileVO.setFile4(file4); // ���� �̹���
    mobileVO.setSize4(size4); // ���� �̹���
    
    
    size6 = file6MF.getSize();

    // -------------------------------------------------------------------
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir);
      mobileVO.setFile6(file6); // ���۵� ���ϸ� ����
      mobileVO.setSize6(file6MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) {
        file5 = Tool.preview(upDir, file6, 120, 80);
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------
    }
    mobileVO.setFile5(file5); // Thumb �̹���
    mobileVO.setFile6(file6); // ���� �̹���
    mobileVO.setSize6(size6); // ���� �̹���
    
    
    
    size8 = file8MF.getSize();

    // -------------------------------------------------------------------
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir);
      mobileVO.setFile8(file8); // ���۵� ���ϸ� ����
      mobileVO.setSize8(file8MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) {
        file7 = Tool.preview(upDir, file8, 120, 80);
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------
    }
    mobileVO.setFile7(file7); // Thumb �̹���
    mobileVO.setFile8(file8); // ���� �̹���
    mobileVO.setSize8(size8); // ���� �̹���
    
    // -------------------------------------------------------------------
    
    
    size10 = file10MF.getSize();
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir);
      mobileVO.setFile10(file10); // ���۵� ���ϸ� ����
      mobileVO.setSize10(file10MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) {
        file9 = Tool.preview(upDir, file10, 120, 80);
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
    }
    mobileVO.setFile9(file9); // Thumb �̹���
    mobileVO.setFile10(file10); // ���� �̹���
    mobileVO.setSize10(size10); // ���� �̹���
    
    // -------------------------------------------------------------------
 
  
    
    
    if (mobileDAO.create(mobileVO) == 1) {
      msgs.add("��ǰ ��� �Ϸ��߽��ϴ�.");
      msgs.add("������ּż� �����մϴ�.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    } else {
      msgs.add("��ǰ ��Ͽ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  

  @RequestMapping(value = "/mobile/list.do", method = RequestMethod.GET)
  public ModelAndView list2(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/list");// /webapp/member/list.jsp
   
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    int recordPerPage = 10; // �������� ����� ���ڵ� ����
    // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 0���� ����
    int beginOfPage = (searchDTO.getNowPage() - 1) * 10; // nowPage�� 1���� ����
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // ���� rownum 1 
    int endNum = beginOfPage + recordPerPage; // ���� rownum 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    int totalRecord = 0;
    List<MobileVO> list = mobileDAO.list2(hashMap);
    Iterator<MobileVO> iter = list.iterator();
    while(iter.hasNext() == true){  // ���� ��� �˻�
      MobileVO vo = iter.next();  // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10)); // ���ڿ� 10�� �и�
      vo.setWdate(vo.getWdate().substring(0, 10));  // �����
      //vo.setFile1(Tool.textLength(vo.getFile1(), 10));
      //vo.setFile2(Tool.textLength(vo.getFile2(), 10));
      vo.setSize2Label(Tool.unit(vo.getSize2()));
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    totalRecord = mobileDAO.count(hashMap);
    
    mav.addObject("totalRecord", mobileDAO.count(hashMap));
    
    
    String paging = new Paging().paging5( totalRecord,             
        searchDTO.getNowPage(), 
        recordPerPage, 
        searchDTO.getCol(), 
        searchDTO.getWord());
        mav.addObject("paging", paging);
    
    return mav;
  }

  @RequestMapping(value = "/mobile/read.do", method = RequestMethod.GET)
  public ModelAndView read(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/read");
    mav.addObject("mobileVO", mobileDAO.read(mno));
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
   
    mav.setViewName("/mobile/read");
    mav.addObject("mobileVO", mobileDAO.read(mno));
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    return mav;
  }

  @RequestMapping(value = "/mobile/update.do", method = RequestMethod.GET)
  public ModelAndView update(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/update"); // /webapp/blogcategory/update.jsp

    List<MobileVO> mobile_list = mobileDAO.list();
    mav.addObject("mobile_list", mobile_list);

    MobileVO mobileVO = mobileDAO.read(mno);
    mav.addObject("mobileVO", mobileVO);
    
    return mav;
  }
  
  @RequestMapping(value = "/mobile/update.do", method = RequestMethod.POST)
  public ModelAndView update(MobileVO mobileVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    long size2 = 0;
    long size4 = 0;
    long size6 = 0;
    long size8 = 0;
    long size10 = 0;
    String upDir = Tool.getRealPath(request, "/mobile/storage");
    MultipartFile file2MF = mobileVO.getFile2MF();
    MobileVO oldVO = mobileDAO.read(mobileVO.getMno()); 
         
    
    size2 = file2MF.getSize();
    if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir);// ���ο� ���� ����
      mobileVO.setSize2(file2MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) { // �̹������� �˻�
        Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
        file1 = Tool.preview(upDir, file2, 120, 80); // thumb �̹��� ����
      } else {
        file1 = "";
      }
    } else {
      file1 = oldVO.getFile1(); // ���� ���ε带���� �ʴ� ���
      file2 = oldVO.getFile2();
      size2 = oldVO.getSize2();
    }
    mobileVO.setFile1(file1); // Thumb �̹���
    mobileVO.setFile2(file2); // ���� �̹���
    mobileVO.setSize2(size2); // ���� �̹���
     
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
    MultipartFile file4MF = mobileVO.getFile4MF();
    MobileVO oldVO2 = mobileDAO.read(mobileVO.getMno()); 
         
    size4 = file4MF.getSize();

    if (file4MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO2.getFile4()); // ���� ��ϵ� ���� ����
      file4 = Upload.saveFileSpring(file4MF, upDir);// ���ο� ���� ����
      mobileVO.setFile4(file4); // ���۵� ���ϸ� ����
      mobileVO.setSize4(file4MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) { // �̹������� �˻�
        Tool.deleteFile(upDir, oldVO.getFile3()); // ���� ����
        file3 = Tool.preview(upDir, file4, 120, 80); // thumb �̹��� ����
      } else {
        file3 = "";
      }
    } else {
      file3 = oldVO.getFile3(); // ���� ���ε带���� �ʴ� ���
      file4 = oldVO.getFile4();
      size4 = oldVO.getSize4();

    }
    mobileVO.setFile3(file3); // Thumb �̹���
    mobileVO.setFile4(file4); // ���� �̹���
    mobileVO.setSize4(size4); // ���� �̹���
    
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
    MultipartFile file6MF = mobileVO.getFile6MF();
    MobileVO oldVO3 = mobileDAO.read(mobileVO.getMno()); 
         
    size6 = file6MF.getSize();

    if (file6MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO3.getFile6()); // ���� ��ϵ� ���� ����
      file6 = Upload.saveFileSpring(file6MF, upDir);// ���ο� ���� ����
      mobileVO.setFile6(file6); // ���۵� ���ϸ� ����
      mobileVO.setSize6(file6MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) { // �̹������� �˻�
        Tool.deleteFile(upDir, oldVO.getFile5()); // ���� ����
        file5 = Tool.preview(upDir, file6, 120, 80); // thumb �̹��� ����
      } else {
        file5 = "";
      }
    } else {
      file5 = oldVO.getFile5(); // ���� ���ε带���� �ʴ� ���
      file6 = oldVO.getFile6();
      size6 = oldVO.getSize6();

    }
    mobileVO.setFile5(file5); // Thumb �̹���
    mobileVO.setFile6(file6); // ���� �̹���
    mobileVO.setSize6(size6); // ���� �̹���
    
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
    MultipartFile file8MF = mobileVO.getFile8MF();
    MobileVO oldVO4 = mobileDAO.read(mobileVO.getMno()); 
         
    size8 = file8MF.getSize();

    if (file8MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO4.getFile8()); // ���� ��ϵ� ���� ����
      file8 = Upload.saveFileSpring(file8MF, upDir);// ���ο� ���� ����
      mobileVO.setFile8(file8); // ���۵� ���ϸ� ����
      mobileVO.setSize8(file8MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) { // �̹������� �˻�
        Tool.deleteFile(upDir, oldVO.getFile7()); // ���� ����
        file7 = Tool.preview(upDir, file8, 120, 80); // thumb �̹��� ����
      } else {
        file7 = "";
      }
    } else {
      file7 = oldVO.getFile7(); // ���� ���ε带���� �ʴ� ���
      file8 = oldVO.getFile8();
      size8 = oldVO.getSize8();

    }
    mobileVO.setFile7(file7); // Thumb �̹���
    mobileVO.setFile8(file8); // ���� �̹���
    mobileVO.setSize8(size8); // ���� �̹���
    
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
    MultipartFile file10MF = mobileVO.getFile10MF();
    MobileVO oldVO5 = mobileDAO.read(mobileVO.getMno()); 
         
    size10 = file10MF.getSize();
    if (file10MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO5.getFile10()); // ���� ��ϵ� ���� ����
      file10 = Upload.saveFileSpring(file10MF, upDir);// ���ο� ���� ����
      mobileVO.setFile10(file10); // ���۵� ���ϸ� ����
      mobileVO.setSize10(file10MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) { // �̹������� �˻�
        Tool.deleteFile(upDir, oldVO.getFile9()); // ���� ����
        file9 = Tool.preview(upDir, file10, 120, 80); // thumb �̹��� ����
      } else {
        file9 = "";
      }
    } else {
      file9 = oldVO.getFile9(); // ���� ���ε带���� �ʴ� ���
      file10 = oldVO.getFile10();
      size10 = oldVO.getSize10();

    }
    mobileVO.setFile9(file9); // Thumb �̹���
    mobileVO.setFile10(file10); // ���� �̹���
    mobileVO.setSize10(size10); // ���� �̹���
 
    // -------------------------------------------------------------------


    
    if (mobileDAO.update(mobileVO) == 1) {
       mav.setViewName("redirect:/mobile/list.do");
       } else {
      msgs.add("��ǰ ���� ���濡 �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
    return mav;
  }
  /**
   * ������
   * @param blogno
   * @return
   */
  @RequestMapping(value = "/mobile/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/delete"); // /webapp/member/delete.jsp
    mav.addObject("mno", mno);
 
    
    return mav;
  }
  
  /**
   * ���ڵ� 1���� �����մϴ�.
   * 
   * @param codeno
   * @return
   */
  @RequestMapping(value = "/mobile/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(MobileVO mobileVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
System.out.println("delete");
    if (mobileDAO.delete(mobileVO.getMno()) == 1) {
      mav.setViewName("redirect:/mobile/list.do");
    } else {
      msgs.add("������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

      mav.addObject("msgs", msgs);
      mav.addObject("links", links);

    }

    return mav;
  }
  
  
  /**
   * �ֱ� ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/mobile/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/mobile/list2"); // /webapp/member/list.jsp
     
     
     List<MobileVO> list = mobileDAO.newlist();
     Iterator<MobileVO> iter = list.iterator(); // ��ü�� ���������� �����ϴ� ���
     while(iter.hasNext() == true){  // ���� ��� �˻�
       MobileVO vo = iter.next();  // ��� ����
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // ���ڿ� 10�� 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // �� �� ��
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     
     return mav;
  }

  
}
