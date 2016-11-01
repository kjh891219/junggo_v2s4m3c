package dev.mvc.game;

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

import web.tool.Paging;
import dev.mvc.game.GameDAOInter;
import dev.mvc.game.GameVO;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class GameCont {
  @Autowired
  @Qualifier("dev.mvc.game.GameDAO")
  private GameDAOInter gameDAO;

  public GameCont() {
    System.out.println("--> GameCont created.");
  }

  @RequestMapping(value = "/game/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game/create"); // /webapp/member/create.jsp
    return mav;
  }

  @RequestMapping(value = "/game/create.do", method = RequestMethod.POST)
  public ModelAndView create(GameVO gameVO, HttpServletRequest request, HttpSession session) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game/message"); // webapp/member/message.jsp

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
    String upDir = Tool.getRealPath(request, "/game/storage");
    MultipartFile file2MF = gameVO.getFile2MF();
    MultipartFile file4MF = gameVO.getFile4MF();
    MultipartFile file6MF = gameVO.getFile6MF();
    MultipartFile file8MF = gameVO.getFile8MF();
    MultipartFile file10MF = gameVO.getFile10MF();

    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size2 = file2MF.getSize();
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      gameVO.setFile2(file2); // ���۵� ���ϸ� ����
      gameVO.setSize2(file2MF.getSize());

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
    gameVO.setFile1(file1); // Thumb �̹���
    gameVO.setFile2(file2); // ���� �̹���
    gameVO.setSize2(size2); // ���� �̹���
   
    
    // -------------------------------------------------------------------
    size4 = file4MF.getSize();
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir);
      gameVO.setFile4(file4); // ���۵� ���ϸ� ����
      gameVO.setSize4(file4MF.getSize());

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
    gameVO.setFile3(file3); // Thumb �̹���
    gameVO.setFile4(file4); // ���� �̹���
    gameVO.setSize2(size4); // ���� �̹���

    
    
    // -------------------------------------------------------------------
    size6 = file6MF.getSize();
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir);
      gameVO.setFile6(file6); // ���۵� ���ϸ� ����
      gameVO.setSize6(file6MF.getSize());

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
    gameVO.setFile5(file5); // Thumb �̹���
    gameVO.setFile6(file6); // ���� �̹���
    gameVO.setSize2(size6); // ���� �̹���

    
    
    
    // -------------------------------------------------------------------
    size8 = file8MF.getSize();
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir);
      gameVO.setFile8(file8); // ���۵� ���ϸ� ����
      gameVO.setSize8(file8MF.getSize());

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
    gameVO.setFile7(file7); // Thumb �̹���
    gameVO.setFile8(file8); // ���� �̹���
    gameVO.setSize2(size8); // ���� �̹���

    // -------------------------------------------------------------------
    
    
    size10 = file10MF.getSize();   
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir);
      gameVO.setFile10(file10); // ���۵� ���ϸ� ����
      gameVO.setSize10(file10MF.getSize());

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
    gameVO.setFile9(file9); // Thumb �̹���
    gameVO.setFile10(file10); // ���� �̹���
    gameVO.setSize2(size10); // ���� �̹���

    // -------------------------------------------------------------------
 
  
    
    if (gameDAO.create(gameVO) == 1) {
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

  /**
   * ��ü ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/game/list.do", method = RequestMethod.GET)
  public ModelAndView list2(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game/list");// /webapp/game/list.jsp

    
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
    List<GameVO> list = gameDAO.list2(hashMap);
    Iterator<GameVO> iter = list.iterator();
    while(iter.hasNext() == true){  // ���� ��� �˻�
      GameVO vo = iter.next();  // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10)); // ���ڿ� 10�� �и�
      vo.setWdate(vo.getWdate().substring(0, 10));  // �����
      //vo.setFile1(Tool.textLength(vo.getFile1(), 10));
      //vo.setFile2(Tool.textLength(vo.getFile2(), 10));
      vo.setSize2Label(Tool.unit(vo.getSize2()));
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    
    totalRecord = gameDAO.count(hashMap);
    mav.addObject("totalRecord", gameDAO.count(hashMap));
    
    
    String paging = new Paging().paging5( totalRecord,             
                                          searchDTO.getNowPage(), 
                                          recordPerPage, 
                                          searchDTO.getCol(), 
                                          searchDTO.getWord());
    mav.addObject("paging", paging);
    return mav;
  }  
 



  @RequestMapping(value = "/game/read.do", method = RequestMethod.GET)
  public ModelAndView read(int gno, SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    gameDAO.increaseCnt(gno);// ��ȸ�� ����
    mav.setViewName("/game/read");
    GameVO gameVO = gameDAO.read(gno);
    gameVO.setSize2Label(Tool.unit(gameVO.getSize2()));
    gameVO.setSize4Label(Tool.unit(gameVO.getSize4()));
    gameVO.setSize6Label(Tool.unit(gameVO.getSize6()));
    gameVO.setSize8Label(Tool.unit(gameVO.getSize8()));
    gameVO.setSize10Label(Tool.unit(gameVO.getSize10()));
    
    mav.addObject("gameVO", gameVO);
    mav.addObject("searchDTO", searchDTO);
    mav.addObject("gameVO", gameDAO.read(gno));
    
    return mav;
  }

  /**
   * ���� �� ���
   * 
   * @return
   */
  @RequestMapping(value = "/game/update.do", method = RequestMethod.GET)
  public ModelAndView update(int gno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game/update"); // /webapp/blogcategory/update.jsp

    GameVO vo = gameDAO.read(gno);
    mav.addObject("vo", vo);

    return mav;
  }

  @RequestMapping(value = "/game/update.do", method = RequestMethod.POST)
  public ModelAndView update(GameVO gameVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();


    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    long size2 = 0;
    String upDir = Tool.getRealPath(request, "/game/storage");
    MultipartFile file2MF = gameVO.getFile2MF();
    GameVO oldVO = gameDAO.read(gameVO.getGno());
         
   
    size2 = file2MF.getSize();
    if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir);// ���ο� ���� ����
      gameVO.setFile2(file2); // ���۵� ���ϸ� ����
      gameVO.setSize2(file2MF.getSize());

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
    gameVO.setFile1(file1); // Thumb �̹���
    gameVO.setFile2(file2); // ���� �̹���
    gameVO.setSize2(size2); // ���� �̹���
    
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
    long size4 = 0;
    MultipartFile file4MF = gameVO.getFile4MF();
    GameVO oldVO2 = gameDAO.read(gameVO.getGno());
         
    size4 = file4MF.getSize();
    if (file4MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO2.getFile4()); // ���� ��ϵ� ���� ����
      file4 = Upload.saveFileSpring(file4MF, upDir);// ���ο� ���� ����
      gameVO.setFile4(file4); // ���۵� ���ϸ� ����
      gameVO.setSize4(file4MF.getSize());

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
    gameVO.setFile3(file3); // Thumb �̹���
    gameVO.setFile4(file4); // ���� �̹���
    gameVO.setSize4(size4); // ���� �̹���
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
    long size6 = 0;
    MultipartFile file6MF = gameVO.getFile6MF();
    GameVO oldVO3 = gameDAO.read(gameVO.getGno());
         
    
    size6 = file6MF.getSize();
    if (file6MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO3.getFile6()); // ���� ��ϵ� ���� ����
      file6 = Upload.saveFileSpring(file6MF, upDir);// ���ο� ���� ����
      gameVO.setFile6(file6); // ���۵� ���ϸ� ����
      gameVO.setSize6(file6MF.getSize());

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
    gameVO.setFile5(file5); // Thumb �̹���
    gameVO.setFile6(file6); // ���� �̹���
    gameVO.setSize6(size6); // ���� �̹���
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
    long size8 = 0;
    MultipartFile file8MF = gameVO.getFile8MF();
    GameVO oldVO4 = gameDAO.read(gameVO.getGno());
         
    
    size8 = file8MF.getSize();
    if (file8MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile8()); // ���� ��ϵ� ���� ����
      file8 = Upload.saveFileSpring(file8MF, upDir);// ���ο� ���� ����
      gameVO.setFile8(file8); // ���۵� ���ϸ� ����
      gameVO.setSize8(file8MF.getSize());

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
    gameVO.setFile7(file7); // Thumb �̹���
    gameVO.setFile8(file8); // ���� �̹���
    gameVO.setSize8(size8); // ���� �̹���
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
    long size10 = 0;
    MultipartFile file10MF = gameVO.getFile10MF();
    GameVO oldVO5 = gameDAO.read(gameVO.getGno());
      
    
    size10 = file10MF.getSize();
    if (file10MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO5.getFile10()); // ���� ��ϵ� ���� ����
      file10 = Upload.saveFileSpring(file10MF, upDir);// ���ο� ���� ����
      gameVO.setFile10(file10); // ���۵� ���ϸ� ����
      gameVO.setSize10(file10MF.getSize());

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
    gameVO.setFile9(file9); // Thumb �̹���
    gameVO.setFile10(file10); // ���� �̹���
    gameVO.setSize10(size10); // ���� �̹���
    // -------------------------------------------------------------------


    
    if (gameDAO.update(gameVO) == 1) {
      msgs.add("��ǰ ���� �Ϸ��߽��ϴ�.");
      mav.setViewName("redirect:/game/read.do?gno=" + gameVO.getGno());

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
   * 
   * @param blogno
   * @return
   */
  @RequestMapping(value = "/game/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int gno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game/delete"); // /webapp/member/delete.jsp
    mav.addObject("gno", gno);

    return mav;
  }

  /**
   * ���ڵ� 1���� �����մϴ�.
   * 
   * @param codeno
   * @return
   */
  @RequestMapping(value = "/game/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(GameVO gameVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (gameDAO.delete(gameVO.getGno()) == 1) {
      mav.setViewName("redirect:/game/list.do");
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

}
