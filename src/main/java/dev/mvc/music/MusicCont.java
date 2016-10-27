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
    
    String thumb = "";
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
     
    String upDir = Tool.getRealPath(request, "/music/storage");
    MultipartFile file1MF = musicVO.getFile1MF();
    MultipartFile file2MF = musicVO.getFile2MF();
    MultipartFile file3MF = musicVO.getFile3MF();
    MultipartFile file4MF = musicVO.getFile4MF();
    MultipartFile file5MF = musicVO.getFile5MF();
    
    if (opentype.equals("U")) {
      
      MusicVO oldVO = musicDAO.read(musicVO.getCtno());
      /*���� ���� 1*/ 
      if (file1MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ��ϵ� ���� ����
        file1 = Upload.saveFileSpring(file1MF, upDir); // ���ο� ���� ����
        musicVO.setFile1(file1); // ���ο� ���ϸ� ����
        musicVO.setSize1(file1MF.getSize()); // ���ο� ũ�� ����

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
      musicVO.setThumb(thumb);
      musicVO.setFile1(file1);
      
      /*���� ���� file1MF*/ 
      if (file1MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ��ϵ� ���� ����
        file1 = Upload.saveFileSpring(file1MF, upDir); // ���ο� ���� ����
        musicVO.setFile1(file1); // ���ο� ���ϸ� ����
        musicVO.setSize1(file1MF.getSize()); // ���ο� ũ�� ����

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
      musicVO.setThumb(thumb);
      musicVO.setFile1(file1);
      
      /*���� ���� file2MF */  
      if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile2());  // ���� ��ϵ� ���� ����
        file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
        musicVO.setFile2(file2);  // ���ο� ���ϸ� ����
        musicVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����
      
      } else {
        file2 = oldVO.getFile2();
      }
     
      musicVO.setFile2(file2);
      
      /*���� ���� file3MF */  
      if (file3MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile3());  // ���� ��ϵ� ���� ����
        file3 = Upload.saveFileSpring(file3MF, upDir); // ���ο� ���� ����
        musicVO.setFile3(file3);  // ���ο� ���ϸ� ����
        musicVO.setSize3(file3MF.getSize()); // ���ο� ũ�� ����
      
      } else {
        file3 = oldVO.getFile3();
      }
     
      musicVO.setFile3(file3);
      
      /*���� ���� file4MF */  
      if (file4MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile4());  // ���� ��ϵ� ���� ����
        file4 = Upload.saveFileSpring(file4MF, upDir); // ���ο� ���� ����
        musicVO.setFile4(file4);  // ���ο� ���ϸ� ����
        musicVO.setSize4(file4MF.getSize()); // ���ο� ũ�� ����
      
      } else {
        file4 = oldVO.getFile4();
      }
     
      musicVO.setFile4(file4);
      
      
      /*���� ���� file5MF */  
      if (file5MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
        Tool.deleteFile(upDir, oldVO.getFile5());  // ���� ��ϵ� ���� ����
        file5 = Upload.saveFileSpring(file5MF, upDir); // ���ο� ���� ����
        musicVO.setFile5(file5);  // ���ο� ���ϸ� ����
        musicVO.setSize5(file5MF.getSize()); // ���ο� ũ�� ����
      
      } else {
        file5 = oldVO.getFile5();
      }
     
      musicVO.setFile5(file5);
    } else {
      /*���� ���� file1MF */  
      if (file1MF.getSize() > 0) {
        file1 = Upload.saveFileSpring(file1MF, upDir);
        musicVO.setFile1(file1); // ���۵� ���ϸ� ����
        musicVO.setSize1(file1MF.getSize());

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
      
      musicVO.setThumb(thumb); // Thumb �̹���
      musicVO.setFile1(file1); // ���� �̹���
      
      /*���� ���� file2MF */  
      if (file2MF.getSize() > 0) {
        file2 = Upload.saveFileSpring(file2MF, upDir);
        musicVO.setFile2(file2); // ���۵� ���ϸ� ����
        musicVO.setSize2(file2MF.getSize());
      }
 
      musicVO.setFile2(file2); // ���� �̹���
      
      /*���� ���� file3MF */  
      if (file3MF.getSize() > 0) {
        file3 = Upload.saveFileSpring(file3MF, upDir);
        musicVO.setFile3(file3); // ���۵� ���ϸ� ����
        musicVO.setSize3(file3MF.getSize());
      }
 
      musicVO.setFile3(file3); // ���� �̹���

      /*���� ���� file4MF */  
      if (file4MF.getSize() > 0) {
        file4 = Upload.saveFileSpring(file4MF, upDir);
        musicVO.setFile4(file4); // ���۵� ���ϸ� ����
        musicVO.setSize4(file4MF.getSize());
      }
 
      musicVO.setFile4(file4); // ���� �̹���

      /*���� ���� file5MF */  
      if (file5MF.getSize() > 0) {
        file5 = Upload.saveFileSpring(file5MF, upDir);
        musicVO.setFile5(file5); // ���۵� ���ϸ� ����
        musicVO.setSize5(file5MF.getSize());
      }
 
      musicVO.setFile5(file5); // ���� �̹���

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
