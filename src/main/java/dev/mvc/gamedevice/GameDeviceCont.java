package dev.mvc.gamedevice;

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
public class GameDeviceCont {

  @Autowired
  @Qualifier("dev.mvc.gamedevice.GameDeviceDAO")
  private GameDeviceDAOInter gamedeviceDAO;

  public GameDeviceCont() {
    System.out.println("--> GameDeviceCont created");
  }

  @RequestMapping(value = "/gamedevice/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/create");
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "gamedevice/list.do");//
     
      
      
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

  @RequestMapping(value = "/gamedevice/create.do", method = RequestMethod.POST)
  public ModelAndView create(GameDeviceVO gamedeviceVO, HttpServletRequest request,  HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/message");

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
    String upDir = Tool.getRealPath(request, "/gamedevice/storage");
    MultipartFile file2MF = gamedeviceVO.getFile2MF();
    MultipartFile file4MF = gamedeviceVO.getFile4MF();
    MultipartFile file6MF = gamedeviceVO.getFile6MF();
    MultipartFile file8MF = gamedeviceVO.getFile8MF();
    MultipartFile file10MF = gamedeviceVO.getFile10MF();

    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size2 = file2MF.getSize();
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      gamedeviceVO.setFile2(file2); // ���۵� ���ϸ� ����
      gamedeviceVO.setSize2(file2MF.getSize());

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
    gamedeviceVO.setFile1(file1); // Thumb �̹���
    gamedeviceVO.setFile2(file2); // ���� �̹���
    gamedeviceVO.setSize2(size2); // ���� �̹���
   
    
    // -------------------------------------------------------------------
    size4 = file4MF.getSize();
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir);
      gamedeviceVO.setFile4(file4); // ���۵� ���ϸ� ����
      gamedeviceVO.setSize4(file4MF.getSize());

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
    gamedeviceVO.setFile3(file3); // Thumb �̹���
    gamedeviceVO.setFile4(file4); // ���� �̹���
    gamedeviceVO.setSize4(size4); // ���� �̹���
    
    
    // -------------------------------------------------------------------
    size6 = file6MF.getSize();
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir);
      gamedeviceVO.setFile6(file6); // ���۵� ���ϸ� ����
      gamedeviceVO.setSize6(file6MF.getSize());

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
    gamedeviceVO.setFile5(file5); // Thumb �̹���
    gamedeviceVO.setFile6(file6); // ���� �̹���
    gamedeviceVO.setSize6(size6); // ���� �̹���
    
    
    
    // -------------------------------------------------------------------
    size8 = file8MF.getSize();
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir);
      gamedeviceVO.setFile8(file8); // ���۵� ���ϸ� ����
      gamedeviceVO.setSize8(file8MF.getSize());

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
    gamedeviceVO.setFile7(file7); // Thumb �̹���
    gamedeviceVO.setFile8(file8); // ���� �̹���
    gamedeviceVO.setSize8(size8); // ���� �̹���
    // -------------------------------------------------------------------
    
    
    size10 = file10MF.getSize();
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir);
      gamedeviceVO.setFile10(file10); // ���۵� ���ϸ� ����
      gamedeviceVO.setSize10(file10MF.getSize());

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
    gamedeviceVO.setFile9(file9); // Thumb �̹���
    gamedeviceVO.setFile10(file10); // ���� �̹���
    gamedeviceVO.setSize10(size10); // ���� �̹���
    // -------------------------------------------------------------------
 
  
    
    if (gamedeviceDAO.create(gamedeviceVO) == 1) {
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

  @RequestMapping(value = "/gamedevice/list.do", method = RequestMethod.GET)
  public ModelAndView list2(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/list");// /webapp/member/list.jsp
    
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
    List<GameDeviceVO> list = gamedeviceDAO.list2(hashMap);
    Iterator<GameDeviceVO> iter = list.iterator();
    while(iter.hasNext() == true){  // ���� ��� �˻�
      GameDeviceVO vo = iter.next();  // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10)); // ���ڿ� 10�� �и�
      vo.setWdate(vo.getWdate().substring(0, 10));  // �����
      //vo.setFile1(Tool.textLength(vo.getFile1(), 10));
      //vo.setFile2(Tool.textLength(vo.getFile2(), 10));
      vo.setSize2Label(Tool.unit(vo.getSize2()));
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    totalRecord = gamedeviceDAO.count(hashMap);
    
    mav.addObject("totalRecord", gamedeviceDAO.count(hashMap));
    
    
    String paging = new Paging().paging5( totalRecord,             
        searchDTO.getNowPage(), 
        recordPerPage, 
        searchDTO.getCol(), 
        searchDTO.getWord());
        mav.addObject("paging", paging);
    return mav;
  }

  @RequestMapping(value = "/gamedevice/read.do", method = RequestMethod.GET)
  public ModelAndView read(int gdno) {
    ModelAndView mav = new ModelAndView();
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
   
    mav.setViewName("/gamedevice/read");
    mav.addObject("gamedeviceVO", gamedeviceDAO.read(gdno));
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    return mav;
  }
  
  @RequestMapping(value = "/gamedevice/update.do", method = RequestMethod.GET)
  public ModelAndView update(int gdno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/update"); // /webapp/blogcategory/update.jsp

    GameDeviceVO gamedeviceVO = gamedeviceDAO.read(gdno);
    mav.addObject("gamedeviceVO", gamedeviceVO);

    return mav;
  }
  
  @RequestMapping(value = "/gamedevice/update.do", method = RequestMethod.POST)
  public ModelAndView update(GameDeviceVO gamedeviceVO, HttpServletRequest request) {
   
    ModelAndView mav = new ModelAndView();
 
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
    String upDir = Tool.getRealPath(request, "/gamedevice/storage");
    MultipartFile file2MF = gamedeviceVO.getFile2MF();
    GameDeviceVO oldVO = gamedeviceDAO.read(gamedeviceVO.getGdno()); 
     
    
    size2 = file2MF.getSize();
    if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir);// ���ο� ���� ����
      gamedeviceVO.setSize2(file2MF.getSize());
      gamedeviceVO.setFile2(file2); // ���۵� ���ϸ� ����
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
    gamedeviceVO.setFile1(file1); // Thumb �̹���
    gamedeviceVO.setFile2(file2); // ���� �̹���
    gamedeviceVO.setSize2(size2); // ���� �̹���
    
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
    MultipartFile file4MF = gamedeviceVO.getFile4MF();
    GameDeviceVO oldVO2 = gamedeviceDAO.read(gamedeviceVO.getGdno()); 
     
    
    size4 = file4MF.getSize();
    if (file4MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO2.getFile4()); // ���� ��ϵ� ���� ����
      file4 = Upload.saveFileSpring(file4MF, upDir);// ���ο� ���� ����
      gamedeviceVO.setFile4(file4); // ���۵� ���ϸ� ����
      gamedeviceVO.setSize4(file4MF.getSize());

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
    gamedeviceVO.setFile3(file3); // Thumb �̹���
    gamedeviceVO.setFile4(file4); // ���� �̹���
    gamedeviceVO.setSize4(size4); // ���� �̹���
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
    MultipartFile file6MF = gamedeviceVO.getFile6MF();
    GameDeviceVO oldVO3 = gamedeviceDAO.read(gamedeviceVO.getGdno()); 
      
    
    size6 = file6MF.getSize();
    if (file6MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO3.getFile6()); // ���� ��ϵ� ���� ����
      file6 = Upload.saveFileSpring(file6MF, upDir);// ���ο� ���� ����
      gamedeviceVO.setFile6(file6); // ���۵� ���ϸ� ����
      gamedeviceVO.setSize6(file6MF.getSize());

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
    gamedeviceVO.setFile5(file5); // Thumb �̹���
    gamedeviceVO.setFile6(file6); // ���� �̹���
    gamedeviceVO.setSize6(size6); // ���� �̹���
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
    MultipartFile file8MF = gamedeviceVO.getFile8MF();
    GameDeviceVO oldVO4 = gamedeviceDAO.read(gamedeviceVO.getGdno()); 
         
    
    size8 = file8MF.getSize();
    if (file8MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO4.getFile8()); // ���� ��ϵ� ���� ����
      file8 = Upload.saveFileSpring(file8MF, upDir);// ���ο� ���� ����
      gamedeviceVO.setFile8(file8); // ���۵� ���ϸ� ����
      gamedeviceVO.setSize8(file8MF.getSize());

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
    gamedeviceVO.setFile7(file7); // Thumb �̹���
    gamedeviceVO.setFile8(file8); // ���� �̹���
    gamedeviceVO.setSize8(size8); // ���� �̹���
     // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
    MultipartFile file10MF = gamedeviceVO.getFile10MF();
    GameDeviceVO oldVO5 = gamedeviceDAO.read(gamedeviceVO.getGdno()); 
         
    
    size10 = file10MF.getSize();
    if (file10MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO5.getFile10()); // ���� ��ϵ� ���� ����
      file10 = Upload.saveFileSpring(file10MF, upDir);// ���ο� ���� ����
      gamedeviceVO.setFile10(file10); // ���۵� ���ϸ� ����
      gamedeviceVO.setSize10(file10MF.getSize());

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
    gamedeviceVO.setFile9(file9); // Thumb �̹���
    gamedeviceVO.setFile10(file10); // ���� �̹���
    gamedeviceVO.setSize10(size10); // ���� �̹���
    // -------------------------------------------------------------------


    
    if (gamedeviceDAO.update(gamedeviceVO) == 1) {
       mav.setViewName("redirect:/gamedevice/read.do?gdno=" + gamedeviceVO.getGdno());
       links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

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
  @RequestMapping(value = "/gamedevice/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int gdno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/delete"); // /webapp/member/delete.jsp
    mav.addObject("gdno", gdno);
 
    
    return mav;
  }
  
  /**
   * ���ڵ� 1���� �����մϴ�.
   * 
   * @param codeno
   * @return
   */
  @RequestMapping(value = "/gamedevice/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(GameDeviceVO gamedeviceVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (gamedeviceDAO.delete(gamedeviceVO.getGdno()) == 1) {
      mav.setViewName("redirect:/gamedevice/list.do");
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
  @RequestMapping(value = "/gamedevice/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/gamedevice/list2"); // /webapp/member/list.jsp
     
     
     List<GameDeviceVO> list = gamedeviceDAO.newlist();
     Iterator<GameDeviceVO> iter = list.iterator(); // ��ü�� ���������� �����ϴ� ���
     while(iter.hasNext() == true){  // ���� ��� �˻�
       GameDeviceVO vo = iter.next();  // ��� ����
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // ���ڿ� 10�� 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // �� �� ��
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     
     return mav;
  }


}
