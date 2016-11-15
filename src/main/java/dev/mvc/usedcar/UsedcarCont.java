package dev.mvc.usedcar;
 
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

import dev.mvc.carproduct.CarproductVO;
import dev.mvc.tmember.MemberVO;
import dev.mvc.usedcar.UsedcarVO;
import web.tool.Paging;
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
  
  /**
   * ���ڵ� ���
   * @param
   * @return
   */
 @RequestMapping(value = "/usedcar/create.do", 
      method = RequestMethod.GET)
public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException { 
// System.out.println("--> create() GET called.");
ModelAndView mav = new ModelAndView();
mav.setViewName("/usedcar/create"); // /webapp/blog/create.jsp

response.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
if (session.getAttribute("userid") == null ){
  PrintWriter writer = response.getWriter();
  writer.println
  ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
   + "location.href = '../member/login.do';"
   + "</script>"); 
  session.setAttribute("url", "usedcar/list.do");//
 
  
  
} else {
  PrintWriter writer = response.getWriter();
  writer.println
  ("<script>" 
      + "location.href = './create.jsp';"
      + "</script>");
  
}



String userid = session.getAttribute("userid").toString();
MemberVO memberVO = usedcarDAO.test(userid);

mav.addObject("memberVO", memberVO);
mav.addObject("userid", userid);
System.out.println(memberVO);

return mav;
}

  
  
 /**
  * �Ѱ��� ���ڵ� ��ȸ
  * @param u_no
  * @return
  */
  @RequestMapping(value = "/usedcar/read.do", method = RequestMethod.GET)
  public ModelAndView read(int u_no, SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/read");
    
    if(u_no>usedcarDAO.maxu_no()){
      u_no = usedcarDAO.maxu_no();
    }
    
    if(u_no<usedcarDAO.minu_no()){
      u_no = usedcarDAO.minu_no();
    }
    

    usedcarDAO.increaseCnt(u_no);
    mav.addObject("usedcarVO", usedcarDAO.read(u_no));
    mav.addObject("searchDTO", searchDTO);
    
    return mav;
  }
 
/**
   * ��� ó��
   * @param usedcarVO
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
    String thumb = "";
    String file1 = "";
    long size1 = 0;
    String upDir = Tool.getRealPath(request, "/usedcar/storage");
    MultipartFile file1MF = usedcarVO.getFile1MF();
    System.out.println("file1MF:"+file1MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size1 = file1MF.getSize();
    if (size1 > 0) {
      file1 = Upload.saveFileSpring(file1MF, upDir);
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file1)) {
        thumb = Tool.preview(upDir, file1, 140, 180);
      } else {
        thumb = "";
      }
      
      // -------------------------------------------------------------------
    }
    usedcarVO.setThumb(thumb); // Thumb �̹���
    usedcarVO.setFile1(file1); // ���� �̹���
    usedcarVO.setSize1(size1); // ���� �̹���
    // -------------------------------------------------------------------
    
    
    // -------------------------------------------------------------------
    // ���� ����2
    // -------------------------------------------------------------------
    String file2 = "";
    long size2 = 0;
    MultipartFile file2MF = usedcarVO.getFile2MF();
    System.out.println("file2MF:"+file2MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size2 = file2MF.getSize();
    if (size2 > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
    }
    usedcarVO.setFile2(file2); // ���� �̹���
    usedcarVO.setSize2(size2); // ���� �̹���
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // ���� ���� 3
    // -------------------------------------------------------------------
    String file3 = "";
    long size3 = 0;
    MultipartFile file3MF = usedcarVO.getFile3MF();
    // System.out.println("file2MF:"+file2MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size3 = file3MF.getSize();
    if (size3 > 0) {
      file3 = Upload.saveFileSpring(file3MF, upDir);
    }
    usedcarVO.setFile3(file3); // ���� �̹���
    usedcarVO.setSize3(size3); // ���� �̹���
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // ���� ���� 4
    // -------------------------------------------------------------------
    String file4 = "";
    long size4 = 0;
    MultipartFile file4MF = usedcarVO.getFile4MF();

    size4 = file4MF.getSize();
    if (size4 > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir);
    }
    usedcarVO.setFile4(file4); // ���� �̹���
    usedcarVO.setSize4(size4); // ���� �̹���
    // -------------------------------------------------------------------
    
    
    // -------------------------------------------------------------------
    // ���� ���� 5
    // -------------------------------------------------------------------
    String file5 = "";
    long size5 = 0;
    MultipartFile file5MF = usedcarVO.getFile5MF();

    size5 = file5MF.getSize();
    if (size5 > 0) {
      file5 = Upload.saveFileSpring(file5MF, upDir);
    }
    usedcarVO.setFile5(file5); // ���� �̹���
    usedcarVO.setSize5(size5); // ���� �̹���
    // -------------------------------------------------------------------
    
    
    //usedcarVO.setU_no(4); // ȸ�� ������ ����
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
     
    if (usedcarDAO.create(usedcarVO) == 1) {
      msgs.add("���� ����߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./create.do?u_no="
              + usedcarVO.getU_no() + "'\">��� ���</button>");
    } else {
      msgs.add("�� ��Ͽ� �����߽��ϴ�.");
      msgs.add("�ٽ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
    }

    links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do?u_no="
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
   * @param usedcarVO
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
    String upDir = Tool.getRealPath(request, "/usedcar/storage");
    UsedcarVO oldVO = usedcarDAO.read(usedcarVO.getU_no());

    // -------------------------------------------------------------------
    // 1��° ����
    // -------------------------------------------------------------------  
    String thumb = "";
    String file1 = "";
    long size1 = 0;
    // <input type="file" name='file1MF' id='file1MF' size='40' >
    MultipartFile file1MF = usedcarVO.getFile1MF();
    size1 = file1MF.getSize();
    if (size1 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ��ϵ� ���� ����
      file1 = Upload.saveFileSpring(file1MF, upDir); // ���ο� ���� ����

      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file1)) { // �̹������� �˻�
        Tool.deleteFile(upDir, oldVO.getThumb()); // ���� ����
        thumb = Tool.preview(upDir, file1, 120, 80); // thumb �̹��� ����
      } else {
        thumb = "";
      }
      // -------------------------------------------------------------------

    } else {
      thumb = oldVO.getThumb();
      file1 = oldVO.getFile1(); // ���� ���ε带���� �ʴ� ���
      size1 = oldVO.getSize1();
    }
    usedcarVO.setThumb(thumb);
    // System.out.println("--> thumb: " + thumb);  
    usedcarVO.setFile1(file1); 
    usedcarVO.setSize1(size1);
    // -------------------------------------------------------------------
        
    // -------------------------------------------------------------------
    // 2��° ����
    // -------------------------------------------------------------------  
    String file2 = "";
    long size2 = 0;
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = usedcarVO.getFile2MF();
    size2 = file2MF.getSize();
    if (size2 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����

    } else {
      file2 = oldVO.getFile2(); // ���� ���ε带���� �ʴ� ���
      size2 = oldVO.getSize2();
    }
    usedcarVO.setFile2(file2); 
    usedcarVO.setSize2(size2);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 3��° ����
    // -------------------------------------------------------------------  
    String file3 = "";
    long size3 = 0;
    // <input type="file" name='file3MF' id='file3MF' size='40' >
    MultipartFile file3MF = usedcarVO.getFile3MF();
    size3 = file3MF.getSize();
    if (size3 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile3()); // ���� ��ϵ� ���� ����
      file3 = Upload.saveFileSpring(file3MF, upDir); // ���ο� ���� ����

    } else {
      file3 = oldVO.getFile3(); // ���� ���ε带���� �ʴ� ���
      size3 = oldVO.getSize3();
    }
    usedcarVO.setFile3(file3); 
    usedcarVO.setSize3(size3);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 4��° ����
    // -------------------------------------------------------------------  
    String file4 = "";
    long size4 = 0;
    // <input type="file" name='file4MF' id='file4MF' size='40' >
    MultipartFile file4MF = usedcarVO.getFile4MF();
    size4 = file4MF.getSize();
    if (size4 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile4()); // ���� ��ϵ� ���� ����
      file4 = Upload.saveFileSpring(file4MF, upDir); // ���ο� ���� ����

    } else {
      file4 = oldVO.getFile4(); // ���� ���ε带���� �ʴ� ���
      size4 = oldVO.getSize4();
    }
    usedcarVO.setFile4(file4); 
    usedcarVO.setSize4(size4);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 5��° ����
    // -------------------------------------------------------------------  
    String file5 = "";
    long size5 = 0;
    // <input type="file" name='file5MF' id='file5MF' size='40' >
    MultipartFile file5MF = usedcarVO.getFile5MF();
    size5 = file5MF.getSize();
    if (size5 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile5()); // ���� ��ϵ� ���� ����
      file5 = Upload.saveFileSpring(file5MF, upDir); // ���ο� ���� ����

    } else {
      file5 = oldVO.getFile5(); // ���� ���ε带���� �ʴ� ���
      size5 = oldVO.getSize5();
    }
    usedcarVO.setFile5(file5); 
    usedcarVO.setSize5(size5);
    // -------------------------------------------------------------------


    if (usedcarDAO.update(usedcarVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/usedcar/read.do?u_no=" + usedcarVO.getU_no()
          + "&u_no=" + usedcarVO.getU_no()); // Ȯ���� ���
    } else {
      msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?u_no="
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
   * @param usedcarVO
   * @return
   */
  @RequestMapping(value = "/usedcar/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(HttpSession session, HttpServletResponse response,
                                         HttpServletRequest request, UsedcarVO usedcarVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (usedcarDAO.delete(usedcarVO.getU_no()) == 1) {
      mav.setViewName("redirect:/usedcar/list.do?u_no=" + usedcarVO.getU_no());//Ȯ���� ���
 
    } else {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?u_no="+usedcarVO.getU_no()+"'\">���</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  /**
   * u_no ���� �Խ��� ����� �˻�+����¡�Ͽ� ����մϴ�.
   * 
   * @param u_no
   *          ��ü ��Ͽ��� ������ �Խ��� ��ȣ
   * @param searchDTO �˻� ����         
   * @return ����� �Խ��� ���
   */
  @RequestMapping(value = "/usedcar/list.do", 
                             method = RequestMethod.GET)
  public ModelAndView list(
                                        SearchDTO searchDTO,
                                        HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/list");
    int totalRecord = 0;

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

    List<UsedcarVO> list = usedcarDAO.list(hashMap); // �˻�
    Iterator<UsedcarVO> iter = list.iterator();
    while (iter.hasNext() == true) { // ���� ��� �˻�
      UsedcarVO vo = iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2()));
    }
    mav.addObject("list", list);

    
    totalRecord = usedcarDAO.count(hashMap);
    mav.addObject("totalRecord", usedcarDAO.count(hashMap)); // �˻��� ���ڵ� ����
 
    String paging = new Paging().paging5(
                                           totalRecord, 
                                           searchDTO.getNowPage(), 
                                           recordPerPage, 
                                           searchDTO.getCol(), 
                                           searchDTO.getWord());
    mav.addObject("paging", paging);
    return mav;
  }

  /**
   * �ֱ� ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/usedcar/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/usedcar/list2"); // /webapp/member/list.jsp
     
     
     List<UsedcarVO> list = usedcarDAO.newlist();
     Iterator<UsedcarVO> iter = list.iterator(); // ��ü�� ���������� �����ϴ� ���
     while(iter.hasNext() == true){  // ���� ��� �˻�
       UsedcarVO vo = iter.next();  // ��� ����
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // ���ڿ� 10�� 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // �� �� ��
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     
     return mav;
  }


  
}