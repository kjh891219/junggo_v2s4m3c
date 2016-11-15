package dev.mvc.computer;

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

import dev.mvc.camera.CameraVO;
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
   * @throws IOException 
   */
  @RequestMapping(value = "/computer/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpServletResponse response, HttpSession session) throws IOException {
    System.out.println("--> create() GET called");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/create"); // /webapp/computer/create.jsp
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "computer/list.do");//
     
      
      
    } else {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>" 
          + "location.href = './create.jsp';"
          + "</script>");
      
    }
    
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString(); 
    String email = session.getAttribute("email").toString();
    String nickname = session.getAttribute("nickname").toString();
    
    
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    mav.addObject("nickname", nickname);
    mav.addObject("email", email);
    
    return mav;
  }
  
  /**
   * ��ϰ� ������ �� ȭ�鿡�� ó���Ѵ�.
   * @param computerVO
   * @param request
   * @param session
   * @param opentype : U(�������), R(��ϸ��)
   * @return
   */
  @RequestMapping(value = "/computer/create.do", method = RequestMethod.POST)
  public ModelAndView create(ComputerVO computerVO, HttpServletRequest request, HttpSession session, String opentype) {
    System.out.println("--> create() POST called.");

    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    mav.setViewName("/computer/message"); // /webapp/computer/message.jsp


    // -------------------------------------------------------------------
    // ���� ���� ����
    // -------------------------------------------------------------------
    
    String thumb = "";
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
     
    String upDir = Tool.getRealPath(request, "/computer/storage");
    MultipartFile file1MF = computerVO.getFile1MF();
    MultipartFile file2MF = computerVO.getFile2MF();
    MultipartFile file3MF = computerVO.getFile3MF();
    MultipartFile file4MF = computerVO.getFile4MF();
    MultipartFile file5MF = computerVO.getFile5MF();
    
      /*���� ���� file1MF */  
      if (file1MF.getSize() > 0) {
        file1 = Upload.saveFileSpring(file1MF, upDir);
        computerVO.setFile1(file1); // ���۵� ���ϸ� ����
        computerVO.setSize1(file1MF.getSize());

        // -------------------------------------------------------------------
        // Thumb ���� ����
        // -------------------------------------------------------------------
        if (Tool.isImage(file1)) {
          thumb = Tool.preview(upDir, file1, 250, 250);
        } else {
          thumb = "";
        }
        // -------------------------------------------------------------------
      }
      
      computerVO.setThumb(thumb); // Thumb �̹���
      computerVO.setFile1(file1); // ���� �̹���
      
      /*���� ���� file2MF */  
      if (file2MF.getSize() > 0) {
        file2 = Upload.saveFileSpring(file2MF, upDir);
        computerVO.setFile2(file2); // ���۵� ���ϸ� ����
        computerVO.setSize2(file2MF.getSize());
      }
 
      computerVO.setFile2(file2); // ���� �̹���
      
      /*���� ���� file3MF */  
      if (file3MF.getSize() > 0) {
        file3 = Upload.saveFileSpring(file3MF, upDir);
        computerVO.setFile3(file3); // ���۵� ���ϸ� ����
        computerVO.setSize3(file3MF.getSize());
      }
 
      computerVO.setFile3(file3); // ���� �̹���

      /*���� ���� file4MF */  
      if (file4MF.getSize() > 0) {
        file4 = Upload.saveFileSpring(file4MF, upDir);
        computerVO.setFile4(file4); // ���۵� ���ϸ� ����
        computerVO.setSize4(file4MF.getSize());
      }
 
      computerVO.setFile4(file4); // ���� �̹���

      /*���� ���� file5MF */  
      if (file5MF.getSize() > 0) {
        file5 = Upload.saveFileSpring(file5MF, upDir);
        computerVO.setFile5(file5); // ���۵� ���ϸ� ����
        computerVO.setSize5(file5MF.getSize());
      }
 
      computerVO.setFile5(file5); // ���� �̹���


    if (computerDAO.create(computerVO) == 1) {
      msgs.add("��� ó�� �Ϸ��߽��ϴ�.");
      msgs.add("�����մϴ�.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
    } else {
      msgs.add("��Ͽ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
    }
    
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

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

 
  @RequestMapping(value="/computer/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int ctno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/update"); 
    mav.addObject("computerVO", computerDAO.read(ctno)); 
    return mav;
 
  }
  
  @RequestMapping(value = "/computer/update.do", method = RequestMethod.POST)
  public ModelAndView update(ComputerVO computerVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String thumb = "";
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
     
    String upDir = Tool.getRealPath(request, "/computer/storage");
    MultipartFile file1MF = computerVO.getFile1MF();
    MultipartFile file2MF = computerVO.getFile2MF();
    MultipartFile file3MF = computerVO.getFile3MF();
    MultipartFile file4MF = computerVO.getFile4MF();
    MultipartFile file5MF = computerVO.getFile5MF();
    
    
    ComputerVO oldVO = computerDAO.read(computerVO.getCtno());
    
    
    /*���� ���� file1MF*/ 
    if (file1MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ��ϵ� ���� ����
      file1 = Upload.saveFileSpring(file1MF, upDir); // ���ο� ���� ����
      computerVO.setFile1(file1); // ���ο� ���ϸ� ����
      computerVO.setSize1(file1MF.getSize()); // ���ο� ũ�� ����

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file1)) { // �̹������� �˻�
        Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
        thumb = Tool.preview(upDir, file1, 250, 250); // thumb �̹��� ����
      } else {
        thumb = "";
      }
      // -------------------------------------------------------------------

    } else {
      thumb = oldVO.getThumb(); // ���� ���ε带���� �ʴ� ���
      file1 = oldVO.getFile1();
    }
    computerVO.setThumb(thumb);
    computerVO.setFile1(file1);
    
    /*���� ���� file2MF */  
    if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2());  // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
      computerVO.setFile2(file2);  // ���ο� ���ϸ� ����
      computerVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����
    
    } else {
      file2 = oldVO.getFile2();
    }
   
    computerVO.setFile2(file2);
    
    /*���� ���� file3MF */  
    if (file3MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile3());  // ���� ��ϵ� ���� ����
      file3 = Upload.saveFileSpring(file3MF, upDir); // ���ο� ���� ����
      computerVO.setFile3(file3);  // ���ο� ���ϸ� ����
      computerVO.setSize3(file3MF.getSize()); // ���ο� ũ�� ����
    
    } else {
      file3 = oldVO.getFile3();
    }
   
    computerVO.setFile3(file3);
    
    /*���� ���� file4MF */  
    if (file4MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile4());  // ���� ��ϵ� ���� ����
      file4 = Upload.saveFileSpring(file4MF, upDir); // ���ο� ���� ����
      computerVO.setFile4(file4);  // ���ο� ���ϸ� ����
      computerVO.setSize4(file4MF.getSize()); // ���ο� ũ�� ����
    
    } else {
      file4 = oldVO.getFile4();
    }
   
    computerVO.setFile4(file4);
    
    
    /*���� ���� file5MF */  
    if (file5MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile5());  // ���� ��ϵ� ���� ����
      file5 = Upload.saveFileSpring(file5MF, upDir); // ���ο� ���� ����
      computerVO.setFile5(file5);  // ���ο� ���ϸ� ����
      computerVO.setSize5(file5MF.getSize()); // ���ο� ũ�� ����
    
    } else {
      file5 = oldVO.getFile5();
    }
   
    computerVO.setFile5(file5);
 
    
    if (computerDAO.update(computerVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/computer/read.do?ctno=" + computerVO.getCtno());
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
   * �ֱ� ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/computer/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/computer/list2"); // /webapp/member/list.jsp
     
     
     List<ComputerVO> list = computerDAO.newlist();
     Iterator<ComputerVO> iter = list.iterator(); // ��ü�� ���������� �����ϴ� ���
     while(iter.hasNext() == true){  // ���� ��� �˻�
       ComputerVO vo = iter.next();  // ��� ����
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // ���ڿ� 10�� 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // �� �� ��
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     
     return mav;
  }

}
