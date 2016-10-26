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
    
    mav.addObject("cReplylist", cReplyDAO.listReply(ctno));
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
    
    String thumb = "";
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
     
    String upDir = Tool.getRealPath(request, "/cheat/storage");
    MultipartFile file1MF = cheatVO.getFile1MF();
    MultipartFile file2MF = cheatVO.getFile2MF();
    MultipartFile file3MF = cheatVO.getFile3MF();
    MultipartFile file4MF = cheatVO.getFile4MF();
    MultipartFile file5MF = cheatVO.getFile5MF();
    
    if (opentype.equals("U")) {
      
      CheatVO oldVO = cheatDAO.read(cheatVO.getCtno());
      /*���� ���� 1*/ 
      if (file1MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ��ϵ� ���� ����
        file1 = Upload.saveFileSpring(file1MF, upDir); // ���ο� ���� ����
        cheatVO.setFile1(file1); // ���ο� ���ϸ� ����
        cheatVO.setSize1(file1MF.getSize()); // ���ο� ũ�� ����

        // -------------------------------------------------------------------
        // Thumb ���� ����
        // -------------------------------------------------------------------
        if (Tool.isImage(file1)) { // �̹������� �˻�
          Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
          thumb = Tool.preview(upDir, file1, 120, 80); // thumb �̹��� ����
        } else {
          thumb = "";
        }
        // -------------------------------------------------------------------

      } else {
        thumb = oldVO.getThumb(); // ���� ���ε带���� �ʴ� ���
        file1 = oldVO.getFile1();
      }
      cheatVO.setThumb(thumb);
      cheatVO.setFile1(file1);
      
      /*���� ���� file1MF*/ 
      if (file1MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ��ϵ� ���� ����
        file1 = Upload.saveFileSpring(file1MF, upDir); // ���ο� ���� ����
        cheatVO.setFile1(file1); // ���ο� ���ϸ� ����
        cheatVO.setSize1(file1MF.getSize()); // ���ο� ũ�� ����

        // -------------------------------------------------------------------
        // Thumb ���� ����
        // -------------------------------------------------------------------
        if (Tool.isImage(file1)) { // �̹������� �˻�
          Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
          thumb = Tool.preview(upDir, file1, 120, 80); // thumb �̹��� ����
        } else {
          thumb = "";
        }
        // -------------------------------------------------------------------

      } else {
        thumb = oldVO.getThumb(); // ���� ���ε带���� �ʴ� ���
        file1 = oldVO.getFile1();
      }
      cheatVO.setThumb(thumb);
      cheatVO.setFile1(file1);
      
      /*���� ���� file2MF */  
      if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile2());  // ���� ��ϵ� ���� ����
        file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
        cheatVO.setFile2(file2);  // ���ο� ���ϸ� ����
        cheatVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����
      
      } else {
        file2 = oldVO.getFile2();
      }
     
      cheatVO.setFile2(file2);
      
      /*���� ���� file3MF */  
      if (file3MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile3());  // ���� ��ϵ� ���� ����
        file3 = Upload.saveFileSpring(file3MF, upDir); // ���ο� ���� ����
        cheatVO.setFile3(file3);  // ���ο� ���ϸ� ����
        cheatVO.setSize3(file3MF.getSize()); // ���ο� ũ�� ����
      
      } else {
        file3 = oldVO.getFile3();
      }
     
      cheatVO.setFile3(file3);
      
      /*���� ���� file4MF */  
      if (file4MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile4());  // ���� ��ϵ� ���� ����
        file4 = Upload.saveFileSpring(file4MF, upDir); // ���ο� ���� ����
        cheatVO.setFile4(file4);  // ���ο� ���ϸ� ����
        cheatVO.setSize4(file4MF.getSize()); // ���ο� ũ�� ����
      
      } else {
        file4 = oldVO.getFile4();
      }
     
      cheatVO.setFile4(file4);
      
      
      /*���� ���� file5MF */  
      if (file5MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile5());  // ���� ��ϵ� ���� ����
        file5 = Upload.saveFileSpring(file5MF, upDir); // ���ο� ���� ����
        cheatVO.setFile5(file5);  // ���ο� ���ϸ� ����
        cheatVO.setSize5(file5MF.getSize()); // ���ο� ũ�� ����
      
      } else {
        file5 = oldVO.getFile5();
      }
     
      cheatVO.setFile5(file5);
    } else {
      /*���� ���� file1MF */  
      if (file1MF.getSize() > 0) {
        file1 = Upload.saveFileSpring(file1MF, upDir);
        cheatVO.setFile1(file1); // ���۵� ���ϸ� ����
        cheatVO.setSize1(file1MF.getSize());

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
      
      cheatVO.setThumb(thumb); // Thumb �̹���
      cheatVO.setFile1(file1); // ���� �̹���
      
      /*���� ���� file2MF */  
      if (file2MF.getSize() > 0) {
        file2 = Upload.saveFileSpring(file2MF, upDir);
        cheatVO.setFile2(file2); // ���۵� ���ϸ� ����
        cheatVO.setSize2(file2MF.getSize());
      }
 
      cheatVO.setFile2(file2); // ���� �̹���
      
      /*���� ���� file3MF */  
      if (file3MF.getSize() > 0) {
        file3 = Upload.saveFileSpring(file3MF, upDir);
        cheatVO.setFile3(file3); // ���۵� ���ϸ� ����
        cheatVO.setSize3(file3MF.getSize());
      }
 
      cheatVO.setFile3(file3); // ���� �̹���

      /*���� ���� file4MF */  
      if (file4MF.getSize() > 0) {
        file4 = Upload.saveFileSpring(file4MF, upDir);
        cheatVO.setFile4(file4); // ���۵� ���ϸ� ����
        cheatVO.setSize4(file4MF.getSize());
      }
 
      cheatVO.setFile4(file4); // ���� �̹���

      /*���� ���� file5MF */  
      if (file5MF.getSize() > 0) {
        file5 = Upload.saveFileSpring(file5MF, upDir);
        cheatVO.setFile5(file5); // ���۵� ���ϸ� ����
        cheatVO.setSize5(file5MF.getSize());
      }
 
      cheatVO.setFile5(file5); // ���� �̹���

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
    
    CReplyVO parentVO = cReplyDAO.read(cReplyVO.getRno()); // �θ�� ���� ����
    
    if (parentVO == null) {
    
      int maxgrp = cReplyDAO.getMaxgrpno(cReplyVO.getCtno()) + 1;
      cReplyVO.setGrpno(maxgrp); // �׷� ��ȣ
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

  /**
   * ��� ���� ó��
   * 
   * @param rno
   * @return
   */
  @RequestMapping(value = "/cheat/delete_reply.do", method = RequestMethod.GET)
  public ModelAndView delete_reply(int rno, int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/message");
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    if (cReplyDAO.delete_reply(rno) == 1) {
      mav.setViewName("redirect:/cheat/read.do?ctno=" + ctno);
      
    } else {
      msgs.add("��� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
}
