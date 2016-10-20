package dev.mvc.usedcar;
 
import java.util.ArrayList;
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

import dev.mvc.usedcar.UsedcarVO;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

 
@Controller
public class UsedcarCont {
  @Autowired
  @Qualifier("dev.mvc.usedcar.UsedcarDAO")
  private UsedcarDAOInter usedcarDAO;
  
  public UsedcarCont(){
    System.out.println("--> UsedcarCont created.");
  }
  
 @RequestMapping(value = "/usedcar/create.do", 
      method = RequestMethod.GET)
public ModelAndView create() {
// System.out.println("--> create() GET called.");
ModelAndView mav = new ModelAndView();
mav.setViewName("/usedcar/create"); // /webapp/blog/create.jsp
return mav;
}
  
  /**
   * ��ü ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/usedcar/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/list"); //  /webapp/member/list.jsp
    mav.addObject("list", usedcarDAO.list());
 
    return mav;
  }
  
  @RequestMapping(value = "/usedcar/read.do", method = RequestMethod.GET)
  public ModelAndView read(int u_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/read");
    mav.addObject("usedcarVO", usedcarDAO.read(u_no));
 
    return mav;
  }
 
/**
   * ��� ó��
   * @param blogVO
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value = "/usedcar/create.do", 
                             method = RequestMethod.POST)
  public ModelAndView create(UsedcarVO usedcarVO, 
                                           HttpServletRequest request, 
                                           HttpSession session) {
    // System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String upDir = Tool.getRealPath(request, "/usedcar/storage");
    MultipartFile file2MF = usedcarVO.getFile2MF();
    System.out.println("file2MF:"+file2MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      usedcarVO.setFile2(file2); // ���۵� ���ϸ� ����
      usedcarVO.setSize2(file2MF.getSize());

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
    usedcarVO.setFile1(file1); // Thumb �̹���
    usedcarVO.setFile2(file2); // ���� �̹���
    // -------------------------------------------------------------------
    
    //usedcarVO.setU_no(4); // ȸ�� ������ ����
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
     
    if (usedcarDAO.create(usedcarVO) == 1) {
      msgs.add("���� ����߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./create.do?blogcategoryno="
              + usedcarVO.getU_no() + "'\">��� ���</button>");
    } else {
      msgs.add("�� ��Ͽ� �����߽��ϴ�.");
      msgs.add("�ٽ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do?blogcategoryno="
            + usedcarVO.getU_no() + "'\">���</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  
  /**
   * ������
   * @param blogno
   * @return
   */
  @RequestMapping(value="/usedcar/update.do", 
                             method=RequestMethod.GET)
  public ModelAndView update(int u_no){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/update");
    UsedcarVO usedcarVO = usedcarDAO.read(u_no);
    mav.addObject("usedcarVO", usedcarVO);
   

    return mav;

  }
   
  /**
   * �۰� ������ ���� ó��
   * @param blogVO
   * @return
   */
  @RequestMapping(value = "/usedcar/update.do", 
                             method = RequestMethod.POST)
  public ModelAndView update(UsedcarVO usedcarVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    // -------------------------------------------------------------------
    // ���� ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
 
    String upDir = Tool.getRealPath(request, "/blog/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = usedcarVO.getFile2MF();
    UsedcarVO oldVO = usedcarDAO.read(usedcarVO.getU_no());
 
    if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
      usedcarVO.setFile2(file2); // ���ο� ���ϸ� ����
      usedcarVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����
 
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
    usedcarVO.setFile1(file1); 
    usedcarVO.setFile2(file2);
    // -------------------------------------------------------------------
 
    if (usedcarDAO.update(usedcarVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/usedcar/read.do?u_no=" + usedcarVO.getU_no()
          + "&u_no=" + usedcarVO.getU_no()); // Ȯ���� ���
    } else {
      msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?blogcategoryno="
             + usedcarVO.getU_no() + "'\">���</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
  /**
   * ������
   * @param u_no
   * @return
   */
  @RequestMapping(value = "/usedcar/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int u_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/delete"); // /webapp/member/delete.jsp
    mav.addObject("u_no", u_no);
    
    return mav;
  }
  
   /**
   * ���� ó��
   * @param blogVO
   * @return
   */
  @RequestMapping(value = "/usedcar/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(UsedcarVO usedcarVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (usedcarDAO.delete(usedcarVO.getU_no()) == 1) {
      mav.setViewName("redirect:/usedcar/list.do?u_no=" + usedcarVO.getU_no());//Ȯ���� ���
 
    } else {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?u_no="+usedcarVO.getU_no()+"'\">���</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
   /**
   * ���� ��ȸ�մϴ�
   * @param u_no
   * @return
   */
/*  @RequestMapping(value = "/usedcar/read.do", 
                             method = RequestMethod.GET)
  public ModelAndView read(int u_no, SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/read");
    UsedcarVO usedcarVO = usedcarDAO.read(u_no);
    
   // String content = usedcarVO.getContent();
    //content = Tool.convertChar(content); // Ư�� ���� ó��
    //usedcarVO.setContent(content);
    
    mav.addObject("usedcarVO", usedcarVO);
    
    return mav;
  }*/
  
}