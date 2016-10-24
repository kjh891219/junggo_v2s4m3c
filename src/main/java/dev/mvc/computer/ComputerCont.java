package dev.mvc.computer;

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

import dev.mvc.computer.ComputerVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class ComputerCont {
  @Autowired
  @Qualifier("dev.mvc.computer.ComputerDAO")
  private ComputerDAOInter computerDAO;

  /*@Autowired
  @Qualifier("dev.mvc.computer.CReplyDAO")
  private CReplyDAOInter cReplyDAO;*/

  public ComputerCont() {
    System.out.println("--> ComputerCont created.");
  }

  /**
   * 
   * @param opentype
   *          : U(����), R(���)
   * @return
   */
  @RequestMapping(value = "/computer/create.do", method = RequestMethod.GET)
  public ModelAndView create(int ctno, String opentype) {
    System.out.println("--> create() GET called");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/create"); // /webapp/computer/create.jsp
    if (opentype.equals("U")) {
      System.out.println("--> create() GET called [U].");
      mav.addObject("computerVO", computerDAO.read(ctno));
    }

    mav.addObject("opentype", opentype);
    return mav;
  }

  @RequestMapping(value = "/computer/create.do", method = RequestMethod.POST)
  public ModelAndView create(ComputerVO computerVO, HttpServletRequest request, HttpSession session, String opentype) {
    System.out.println("--> create() POST called.");

    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    mav.setViewName("/computer/message"); // /webapp/computer/message.jsp

    /* ������ ��� ��й�ȣ üũ */
    if (opentype.equals("U")) {
      System.out.println("--> create() POST ���� ���.");

      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("ctno", computerVO.getCtno());
      hashMap.put("passwd", computerVO.getPasswd());

      if (computerDAO.passwordCheck(hashMap) == 0) {
        mav.setViewName("/computer/message"); // /webapp/computer/message.jsp
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

    String upDir = Tool.getRealPath(request, "/computer/storage");
    MultipartFile file2MF = computerVO.getFile2MF();
    
    if (opentype.equals("U")) {
      System.out.println("--> create() POST �������� ���� ���.");
      ComputerVO oldVO = computerDAO.read(computerVO.getCtno());

      if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
        file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
        computerVO.setFile2(file2); // ���ο� ���ϸ� ����
        computerVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����

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
      computerVO.setFile1(file1);
      computerVO.setFile2(file2);
    } else {
      System.out.println("--> create() POST �������� ��� ���.");
      if (file2MF.getSize() > 0) {
        file2 = Upload.saveFileSpring(file2MF, upDir);
        computerVO.setFile2(file2); // ���۵� ���ϸ� ����
        computerVO.setSize2(file2MF.getSize());

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
      computerVO.setFile1(file1); // Thumb �̹���
      computerVO.setFile2(file2); // ���� �̹���
    }
    
    int result = 0;
    if (opentype.equals("U")) {
      result = computerDAO.update(computerVO);
    } else {
      result = computerDAO.create(computerVO);
    }

    if (result == 1) {
      msgs.add("������ �Ϸ��߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    } else {
      mav.setViewName("/computer/message"); // /webapp/computer/message.jsp
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
  @RequestMapping(value = "/computer/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno) {
    ModelAndView mav = new ModelAndView();
    computerDAO.setCnt(ctno); // ��ȸ�� ����

    mav.setViewName("/computer/read");
    mav.addObject("computerVO", computerDAO.read(ctno));

    return mav;
  }

  /**
   * ����Ʈ ����� �˻�+����¡+�亯�� �����Ͽ� ����մϴ�.
   * 
   * @param searchDTO
   *          �˻� ����
   * @return ����� �Խ��� ���
   */
  @RequestMapping(value = "/computer/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/list");
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

    List<ComputerVO> list = computerDAO.list(hashMap); // �˻�
    Iterator<ComputerVO> iter = list.iterator();
    while (iter.hasNext() == true) { // ���� ��� �˻�
      ComputerVO vo = iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 20));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());

    totalRecord = computerDAO.count(hashMap);
    mav.addObject("totalRecord", computerDAO.count(hashMap)); // �˻��� ���ڵ� ����

    String paging = new Paging().paging5(totalRecord, searchDTO.getNowPage(), recordPerPage, searchDTO.getCol(),
        searchDTO.getWord());
    mav.addObject("paging", paging);
    return mav;
  }

  /**
   * �۰� ������ ���� ó��
   * 
   * @param computerVO
   * @return
   */
  @RequestMapping(value = "/computer/update1.do", method = RequestMethod.POST)
  public ModelAndView update(ComputerVO computerVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("ctno", computerVO.getCtno());
    hashMap.put("passwd", computerVO.getPasswd());

    if (computerDAO.passwordCheck(hashMap) == 0) {
      mav.setViewName("/computer/message"); // /webapp/computer/message.jsp
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

    String upDir = Tool.getRealPath(request, "/computer/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = computerVO.getFile2MF();
    ComputerVO oldVO = computerDAO.read(computerVO.getCtno());

    if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
      computerVO.setFile2(file2); // ���ο� ���ϸ� ����
      computerVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����
 
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
    computerVO.setFile1(file1);
    computerVO.setFile2(file2);
    // -------------------------------------------------------------------

    if (computerDAO.update(computerVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/computer/read.do?ctno=" + computerVO.getCtno());
    } else {
      mav.setViewName("/computer/message"); // /webapp/computer/message.jsp
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
  @RequestMapping(value = "/computer/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/delete"); // /webapp/computer/delete.jsp
    mav.addObject("ctno", ctno);

    return mav;
  }

  /**
   * ���� ó��
   * 
   * @param computerVO
   * @return
   */
  @RequestMapping(value = "/computer/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int ctno, String passwd) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("ctno", ctno);
    hashMap.put("passwd", passwd);
    if (computerDAO.passwordCheck(hashMap) == 0) {
      msgs.add("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      return mav;
    }

    if (computerDAO.delete(ctno) == 1) {
      mav.setViewName("redirect:/computer/list.do");
    } else {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

 

}
