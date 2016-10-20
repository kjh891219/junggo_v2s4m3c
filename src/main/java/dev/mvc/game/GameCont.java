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
    String upDir = Tool.getRealPath(request, "/game/storage");
    MultipartFile file2MF = gameVO.getFile2MF();

    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
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
  public ModelAndView read(int gno) {
    ModelAndView mav = new ModelAndView();
    gameDAO.increaseCnt(gno);// ��ȸ�� ����
    mav.setViewName("/game/read");
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
    String upDir = Tool.getRealPath(request, "/game/storage");
    MultipartFile file2MF = gameVO.getFile2MF();
    GameVO oldVO = gameDAO.read(gameVO.getGno());
         
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
    }
    gameVO.setFile1(file1); // Thumb �̹���
    gameVO.setFile2(file2); // ���� �̹���
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
