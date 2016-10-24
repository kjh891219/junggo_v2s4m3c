package dev.mvc.music;

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

import dev.mvc.music.MusicVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class MusicCont {
  @Autowired
  @Qualifier("dev.mvc.music.MusicDAO")
  private MusicDAOInter musicDAO;

  /*@Autowired
  @Qualifier("dev.mvc.music.CReplyDAO")
  private CReplyDAOInter cReplyDAO;*/

  public MusicCont() {
    System.out.println("--> MusicCont created.");
  }

  /**
   * 
   * @param opentype
   *          : U(����), R(���)
   * @return
   */
  @RequestMapping(value = "/music/create.do", method = RequestMethod.GET)
  public ModelAndView create(int ctno, String opentype) {
    System.out.println("--> create() GET called");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/music/create"); // /webapp/music/create.jsp
    if (opentype.equals("U")) {
      System.out.println("--> create() GET called [U].");
      mav.addObject("musicVO", musicDAO.read(ctno));
    }

    mav.addObject("opentype", opentype);
    return mav;
  }

  @RequestMapping(value = "/music/create.do", method = RequestMethod.POST)
  public ModelAndView create(MusicVO musicVO, HttpServletRequest request, HttpSession session, String opentype) {
    System.out.println("--> create() POST called.");

    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    mav.setViewName("/music/message"); // /webapp/music/message.jsp

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
      hashMap.put("ctno", musicVO.getCtno());
      hashMap.put("passwd", musicVO.getPasswd());

      if (musicDAO.passwordCheck(hashMap) == 0) {
        mav.setViewName("/music/message"); // /webapp/music/message.jsp
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

    String upDir = Tool.getRealPath(request, "/music/storage");
    MultipartFile file2MF = musicVO.getFile2MF();
    
    if (opentype.equals("U")) {
      
      MusicVO oldVO = musicDAO.read(musicVO.getCtno());

      if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
        file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
        musicVO.setFile2(file2); // ���ο� ���ϸ� ����
        musicVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����

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
      musicVO.setFile1(file1);
      musicVO.setFile2(file2);
    } else {
      if (file2MF.getSize() > 0) {
        file2 = Upload.saveFileSpring(file2MF, upDir);
        musicVO.setFile2(file2); // ���۵� ���ϸ� ����
        musicVO.setSize2(file2MF.getSize());

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
      musicVO.setFile1(file1); // Thumb �̹���
      musicVO.setFile2(file2); // ���� �̹���
    }
    
    int result = 0;
    if (opentype.equals("U")) {
      result = musicDAO.update(musicVO);
    } else {
      result = musicDAO.create(musicVO);
    }

    if (result == 1) {
      msgs.add("������ �Ϸ��߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    } else {
      mav.setViewName("/music/message"); // /webapp/music/message.jsp
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
  @RequestMapping(value = "/music/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno) {
    ModelAndView mav = new ModelAndView();
    musicDAO.setCnt(ctno); // ��ȸ�� ����

    mav.setViewName("/music/read");
    mav.addObject("musicVO", musicDAO.read(ctno));

    return mav;
  }

  /**
   * ����Ʈ ����� �˻�+����¡+�亯�� �����Ͽ� ����մϴ�.
   * 
   * @param searchDTO
   *          �˻� ����
   * @return ����� �Խ��� ���
   */
  @RequestMapping(value = "/music/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/music/list");
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

    List<MusicVO> list = musicDAO.list(hashMap); // �˻�
    Iterator<MusicVO> iter = list.iterator();
    while (iter.hasNext() == true) { // ���� ��� �˻�
      MusicVO vo = iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 20));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());

    totalRecord = musicDAO.count(hashMap);
    mav.addObject("totalRecord", musicDAO.count(hashMap)); // �˻��� ���ڵ� ����

    String paging = new Paging().paging5(totalRecord, searchDTO.getNowPage(), recordPerPage, searchDTO.getCol(),
        searchDTO.getWord());
    mav.addObject("paging", paging);
    return mav;
  }

  /**
   * �۰� ������ ���� ó��
   * 
   * @param musicVO
   * @return
   */
  @RequestMapping(value = "/music/update1.do", method = RequestMethod.POST)
  public ModelAndView update(MusicVO musicVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("ctno", musicVO.getCtno());
    hashMap.put("passwd", musicVO.getPasswd());

    if (musicDAO.passwordCheck(hashMap) == 0) {
      mav.setViewName("/music/message"); // /webapp/music/message.jsp
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

    String upDir = Tool.getRealPath(request, "/music/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = musicVO.getFile2MF();
    MusicVO oldVO = musicDAO.read(musicVO.getCtno());

    if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
      musicVO.setFile2(file2); // ���ο� ���ϸ� ����
      musicVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����
 
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
    musicVO.setFile1(file1);
    musicVO.setFile2(file2);
    // -------------------------------------------------------------------

    if (musicDAO.update(musicVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/music/read.do?ctno=" + musicVO.getCtno());
    } else {
      mav.setViewName("/music/message"); // /webapp/music/message.jsp
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
  @RequestMapping(value = "/music/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/music/delete"); // /webapp/music/delete.jsp
    mav.addObject("ctno", ctno);

    return mav;
  }

  /**
   * ���� ó��
   * 
   * @param musicVO
   * @return
   */
  @RequestMapping(value = "/music/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int ctno, String passwd) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/music/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("ctno", ctno);
    hashMap.put("passwd", passwd);
    if (musicDAO.passwordCheck(hashMap) == 0) {
      msgs.add("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      return mav;
    }

    if (musicDAO.delete(ctno) == 1) {
      mav.setViewName("redirect:/music/list.do");
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