package dev.mvc.camera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import dev.mvc.tmember.MemberVO;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

import org.springframework.beans.factory.annotation.Qualifier;

@Controller
public class CameraCont {
  @Autowired
  @Qualifier("dev.mvc.camera.CameraDAO")
  private CameraDAOInter cameraDAO;
  

  public CameraCont() {
    System.out.println("--> CameraCont created."); 
  }
  
  
  /**
   * ��ü ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/camera/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/list"); // /webapp/member/list.jsp
    
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    
    
    int recordPerPage = 10; // �������� ����� ���ڵ� ����
    // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 1���� ����
    int beginOfPage = (searchDTO.getNowPage() - 1) * 10; 
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // ���� rownum, 1
    int endNum = beginOfPage + recordPerPage; // ���� rownum, 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    
    
    
    System.out.println(searchDTO.getCol());
    System.out.println(searchDTO.getWord());
    
    
    int totalRecord = 0;
    List<CameraVO> list = cameraDAO.list3(hashMap);
    Iterator<CameraVO> iter = list.iterator();
    while (iter.hasNext() == true) { // ���� ��� �˻�
      CameraVO vo = iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2())); // MB ���� ��ȯ
      vo.setSize4Label(Tool.unit(vo.getSize4()));
      
    }
    
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    
    totalRecord = cameraDAO.count(hashMap);
    mav.addObject("totalRecord", cameraDAO.count(hashMap));
    
    String paging = new Paging().paging5(
        totalRecord, 
        searchDTO.getNowPage(), 
        recordPerPage, 
        searchDTO.getCol(), 
        searchDTO.getWord());
    mav.addObject("paging", paging);
    
    System.out.println(paging);
    
    
    return mav;
  }

  @RequestMapping(value = "/camera/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/create"); // /webapp/member/create.jsp
  
    String userid = session.getAttribute("userid").toString();
    MemberVO memberVO = cameraDAO.test(userid);
    
    mav.addObject("memberVO", memberVO);
    mav.addObject("userid", userid);
    System.out.println(memberVO);
    return mav;
  }

  @RequestMapping(value = "/camera/create.do", method = RequestMethod.POST)
  public ModelAndView create(CameraVO cameraVO, HttpServletRequest request) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/message"); // webapp/member/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    
 // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String upDir = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file2MF = cameraVO.getFile2MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      cameraVO.setFile2(file2); // ���۵� ���ϸ� ����
      cameraVO.setSize2(file2MF.getSize());

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
    cameraVO.setFile1(file1); // Thumb �̹���
    cameraVO.setFile2(file2); // ���� �̹���
    // -------------------------------------------------------------------
    
 
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
    
    
    
 // -------------------------------------------------------------------
    // ���� ����2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
    String upDir2 = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file4MF = cameraVO.getFile4MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir2);
      cameraVO.setFile4(file4); // ���۵� ���ϸ� ����
      cameraVO.setSize4(file4MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) {
        file3 = Tool.preview(upDir2, file4, 120, 80);
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile3(file3); // Thumb �̹���
    cameraVO.setFile4(file4); // ���� �̹���
    // -------------------------------------------------------------------
    
    
 // -------------------------------------------------------------------
    // ���� ����3
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
    String upDir3 = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file6MF = cameraVO.getFile6MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir3);
      cameraVO.setFile6(file6); // ���۵� ���ϸ� ����
      cameraVO.setSize6(file6MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) {
        file5 = Tool.preview(upDir3, file6, 120, 80);
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile5(file5); // Thumb �̹���
    cameraVO.setFile6(file6); // ���� �̹���
    // -------------------------------------------------------------------
    
 
    
    
    // -------------------------------------------------------------------
    // ���� ����4
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
    String upDir4 = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file8MF = cameraVO.getFile8MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir4);
      cameraVO.setFile8(file8); // ���۵� ���ϸ� ����
      cameraVO.setSize8(file8MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) {
        file7 = Tool.preview(upDir4, file8, 120, 80);
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile7(file7); // Thumb �̹���
    cameraVO.setFile8(file8); // ���� �̹���
    // -------------------------------------------------------------------
    
    
    
 // -------------------------------------------------------------------
    // ���� ����5
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
    String upDir5 = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file10MF = cameraVO.getFile10MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir5);
      cameraVO.setFile10(file10); // ���۵� ���ϸ� ����
      cameraVO.setSize10(file10MF.getSize());

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) {
        file9 = Tool.preview(upDir5, file10, 120, 80);
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile9(file9); // Thumb �̹���
    cameraVO.setFile10(file10); // ���� �̹���
    // -------------------------------------------------------------------
    
    
    
    
    
    
    
    
    if (cameraDAO.create(cameraVO) == 1) {
      msgs.add("��� ó�� �Ϸ��߽��ϴ�.");
      msgs.add("�����մϴ�.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    } else {
      msgs.add("��Ͽ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
  @RequestMapping(value = "/camera/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno, SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    cameraDAO.increaseCnt(ctno); //��ȸ�� ����
    mav.setViewName("/camera/read");
    CameraVO cameraVO = cameraDAO.read(ctno);
    cameraVO.setSize2Label(Tool.unit(cameraVO.getSize2()));
    cameraVO.setSize4Label(Tool.unit(cameraVO.getSize4()));
    mav.addObject("cameraVO", cameraVO);
    mav.addObject("searchDTO", searchDTO);
    mav.addObject("root", request.getContextPath());
    return mav;
  }
  
  /**
   * ������
   * @param ctno
   * @return
   */
  @RequestMapping(value="/camera/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int ctno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/update"); 
    mav.addObject("cameraVO", cameraDAO.read(ctno)); 
    return mav;
 
  }
  
  
  

  
  
  
  
  
  
  
  
  /**
   * �۰� ������ ���� ó��
   * 
   * @param cameraVO
   * @return
   */
  @RequestMapping(value = "/camera/update.do", method = RequestMethod.POST)
  public ModelAndView update(CameraVO cameraVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
 // -------------------------------------------------------------------
    // ���� ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";

    String upDir = Tool.getRealPath(request, "/camera/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = cameraVO.getFile2MF();
    CameraVO oldVO = cameraDAO.read(cameraVO.getCtno());

    if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
      cameraVO.setFile2(file2); // ���ο� ���ϸ� ����
      cameraVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����

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
    cameraVO.setFile1(file1); 
    cameraVO.setFile2(file2);
    
    
    
    
    
    
 // -------------------------------------------------------------------
    // ���� ���� ����2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";

    String upDir2 = Tool.getRealPath(request, "/camera/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file4MF = cameraVO.getFile4MF();
    CameraVO oldVO2 = cameraDAO.read(cameraVO.getCtno());

    if (file4MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir2, oldVO2.getFile4()); // ���� ��ϵ� ���� ����
      file4 = Upload.saveFileSpring(file4MF, upDir2); // ���ο� ���� ����
      cameraVO.setFile4(file4); // ���ο� ���ϸ� ����
      cameraVO.setSize4(file4MF.getSize()); // ���ο� ũ�� ����

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) { // �̹������� �˻�
        Tool.deleteFile(upDir2, oldVO2.getFile3()); // ���� ����
        file3 = Tool.preview(upDir2, file4, 120, 80); // thumb �̹��� ����
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------

    } else {
      file3 = oldVO2.getFile3(); // ���� ���ε带���� �ʴ� ���
      file4 = oldVO2.getFile4();
    }
    cameraVO.setFile3(file3); 
    cameraVO.setFile4(file4);
    
    
    
    
    
    
 
    if (cameraDAO.update(cameraVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/camera/read.do?ctno=" + cameraVO.getCtno());
    } else {
      msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
      links
          .add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links
          .add("<button type='button' onclick=\"location.href='./list.do'>���</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
  /**
   * ������
   * @param ctno
   * @return
   */
  @RequestMapping(value = "/camera/delete.do", 
                              method = RequestMethod.GET)
  public ModelAndView delete(int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/delete"); // /webapp/blog/delete.jsp
    mav.addObject("ctno", ctno);
    return mav;
  }
  
  /**
   * ���� ó��
   * @param blogVO
   * @return
   */
  @RequestMapping(value = "/camera/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(CameraVO cameraVO) {
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (cameraDAO.delete(cameraVO.getCtno()) == 1) {
      
      mav.setViewName("redirect:/camera/list.do");//Ȯ���� ���

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
