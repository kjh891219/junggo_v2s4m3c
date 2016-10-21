package dev.mvc.cheat;

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

import dev.mvc.cheat.CheatVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class CheatCont {
  @Autowired
  @Qualifier("dev.mvc.cheat.CheatDAO")
  private CheatDAOInter cheatDAO;

  @Autowired
  @Qualifier("dev.mvc.cheat.CReplyDAO")
  private CReplyDAOInter cReplyDAO;

  public CheatCont() {
    System.out.println("--> CheatCont created.");
  }

  /**
   * 
   * @param opentype
   *          : U(����), R(���)
   * @return
   */
  @RequestMapping(value = "/cheat/create.do", method = RequestMethod.GET)
  public ModelAndView create(int ctno, String opentype) {
    System.out.println("--> create() GET called");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/create"); // /webapp/cheat/create.jsp
    if (opentype.equals("U")) {
      System.out.println("--> create() GET called [U].");
      mav.addObject("cheatVO", cheatDAO.read(ctno));
    }

    mav.addObject("opentype", opentype);
    return mav;
  }

  @RequestMapping(value = "/cheat/create.do", method = RequestMethod.POST)
  public ModelAndView create(CheatVO cheatVO, HttpServletRequest request, HttpSession session, String opentype) {
    System.out.println("--> create() POST called.");

    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp

    /* ���� ��� Log */
    if (opentype.equals("U")) {
      System.out.println("--> create() POST ���� ���.");
    } else if (opentype.equals("R")) {
      System.out.println("--> create() POST ��� ���.");
    }

    /* ������ ��� ��й�ȣ üũ */
    if (opentype.equals("U")) {
      System.out.println("--> create() POST ���� ���.");

      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("ctno", cheatVO.getCtno());
      hashMap.put("passwd", cheatVO.getPasswd());

      if (cheatDAO.passwordCheck(hashMap) == 0) {
        mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
        msgs.add("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
        links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
        links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
        mav.addObject("msgs", msgs);
        mav.addObject("links", links);

      }
    }

    // -------------------------------------------------------------------
    // ���� ���� ����
    // -------------------------------------------------------------------
    
    String file1 = "";
    String file2 = "";

    String upDir = Tool.getRealPath(request, "/cheat/storage");
    MultipartFile file2MF = cheatVO.getFile2MF();
    
    if (opentype.equals("U")) {
      
      CheatVO oldVO = cheatDAO.read(cheatVO.getCtno());

      if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
        file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
        cheatVO.setFile2(file2); // ���ο� ���ϸ� ����
        cheatVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����

        // -------------------------------------------------------------------
        // Thumb ���� ����
        // -------------------------------------------------------------------
        if (Tool.isImage(file2)) { // �̹������� �˻�
          Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
          file1 = Tool.preview(upDir, file2, 120, 80); // thumb �̹��� ����
        } else {
          file1 = "";
        }
        // -------------------------------------------------------------------

      } else {
        file1 = oldVO.getFile1(); // ���� ���ε带���� �ʴ� ���
        file2 = oldVO.getFile2();
      }
      cheatVO.setFile1(file1);
      cheatVO.setFile2(file2);
    } else {
      if (file2MF.getSize() > 0) {
        file2 = Upload.saveFileSpring(file2MF, upDir);
        cheatVO.setFile2(file2); // ���۵� ���ϸ� ����
        cheatVO.setSize2(file2MF.getSize());

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
      cheatVO.setFile1(file1); // Thumb �̹���
      cheatVO.setFile2(file2); // ���� �̹���
    }
    
    int result = 0;
    if (opentype.equals("U")) {
      result = cheatDAO.update(cheatVO);
    } else {
      result = cheatDAO.create(cheatVO);
    }

    if (result == 1) {
      msgs.add("������ �Ϸ��߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    } else {
      mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
      msgs.add("������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /**
   * 1�� ��ȸ
   * 
   * @param ctno
   * @return
   */
  @RequestMapping(value = "/cheat/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno) {
    ModelAndView mav = new ModelAndView();
    cheatDAO.setCnt(ctno); // ��ȸ�� ����

    mav.setViewName("/cheat/read");
    mav.addObject("cheatVO", cheatDAO.read(ctno));

    return mav;
  }

  /**
   * ����Ʈ ����� �˻�+����¡+�亯�� �����Ͽ� ����մϴ�.
   * 
   * @param searchDTO
   *          �˻� ����
   * @return ����� �Խ��� ���
   */
  @RequestMapping(value = "/cheat/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/list");
    int totalRecord = 0;

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());

    int recordPerPage = 10; // �������� ����� ���ڵ� ����
    // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 1���� ����
    int beginOfPage = (searchDTO.getNowPage() - 1) * recordPerPage;
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // ���� rownum, 1
    int endNum = beginOfPage + recordPerPage; // ���� rownum, 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);

    List<CheatVO> list = cheatDAO.list(hashMap); // �˻�
    Iterator<CheatVO> iter = list.iterator();
    while (iter.hasNext() == true) { // ���� ��� �˻�
      CheatVO vo = iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 20));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());

    totalRecord = cheatDAO.count(hashMap);
    mav.addObject("totalRecord", cheatDAO.count(hashMap)); // �˻��� ���ڵ� ����

    String paging = new Paging().paging5(totalRecord, searchDTO.getNowPage(), recordPerPage, searchDTO.getCol(),
        searchDTO.getWord());
    mav.addObject("paging", paging);
    return mav;
  }

  /**
   * �۰� ������ ���� ó��
   * 
   * @param cheatVO
   * @return
   */
  @RequestMapping(value = "/cheat/update1.do", method = RequestMethod.POST)
  public ModelAndView update(CheatVO cheatVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("ctno", cheatVO.getCtno());
    hashMap.put("passwd", cheatVO.getPasswd());

    if (cheatDAO.passwordCheck(hashMap) == 0) {
      mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
      msgs.add("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      return mav;
    }
    // -------------------------------------------------------------------
    // ���� ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";

    String upDir = Tool.getRealPath(request, "/cheat/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = cheatVO.getFile2MF();
    CheatVO oldVO = cheatDAO.read(cheatVO.getCtno());

    if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
      cheatVO.setFile2(file2); // ���ο� ���ϸ� ����
      cheatVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) { // �̹������� �˻�
        Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
        file1 = Tool.preview(upDir, file2, 120, 80); // thumb �̹��� ����
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------

    } else {
      file1 = oldVO.getFile1(); // ���� ���ε带���� �ʴ� ���
      file2 = oldVO.getFile2();
    }
    cheatVO.setFile1(file1);
    cheatVO.setFile2(file2);
    // -------------------------------------------------------------------

    if (cheatDAO.update(cheatVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/cheat/read.do?ctno=" + cheatVO.getCtno());
    } else {
      mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
      msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
    return mav;
  }

  /**
   * ������
   * 
   * @param ctno
   * @return
   */
  @RequestMapping(value = "/cheat/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/delete"); // /webapp/cheat/delete.jsp
    mav.addObject("ctno", ctno);

    return mav;
  }

  /**
   * ���� ó��
   * 
   * @param cheatVO
   * @return
   */
  @RequestMapping(value = "/cheat/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int ctno, String passwd) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("ctno", ctno);
    hashMap.put("passwd", passwd);
    if (cheatDAO.passwordCheck(hashMap) == 0) {
      msgs.add("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      return mav;
    }

    if (cheatDAO.delete(ctno) == 1) {
      mav.setViewName("redirect:/cheat/list.do");
    } else {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /**
   * ��� ���
   * 
   * @param cReplyVO
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value = "/cheat/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(CReplyVO cReplyVO, HttpServletRequest request, HttpSession session) {
    System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp

    // ---------- �亯 ���� �ڵ� ���� ----------
    System.out.println("cReplyVO:" + cReplyVO.getCtno());
    CReplyVO parentVO = cReplyDAO.read(cReplyVO.getCtno()); // �θ�� ���� ����
    if (parentVO == null) {
      cReplyVO.setGrpno(1); // �׷� ��ȣ
      cReplyVO.setIndent(1);
      cReplyVO.setAnsnum(1); // �亯 ����
    } else {
      cReplyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ
      cReplyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����

      cReplyDAO.updateAnsnum(cReplyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.

      cReplyVO.setIndent(parentVO.getIndent() + 1); // �亯 ���� ����
      cReplyVO.setAnsnum(parentVO.getAnsnum() + 1); // �θ� �ٷ� �Ʒ� ���
    }
    // ---------- �亯 ���� �ڵ� ���� ----------

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (cReplyDAO.reply(cReplyVO) == 1) {
      mav.setViewName("redirect:/cheat/read.do?ctno=" + cReplyVO.getCtno());
    } else {
      msgs.add("��� ����� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

}
