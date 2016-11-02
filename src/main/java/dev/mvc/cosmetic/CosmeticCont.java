package dev.mvc.cosmetic;

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

import dev.mvc.tmember.MemberVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class CosmeticCont {
  @Autowired
  @Qualifier("dev.mvc.cosmetic.CosmeticDAO")
  private CosmeticDAOInter cosmeticDAO;
  
  public CosmeticCont(){
    System.out.println("--> CosmeticCont created.");
  }
  
  @RequestMapping(value = "/cosmetic/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
    System.out.println("--> create() GET called."); 
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic/create"); // /webapp/cosmetic/create.jsp

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url",  "cosmetic/list.do");//
      
    }else{
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>"
          + "location.href = './create.jsp'; "
          + "</script>");
      session.setAttribute("url", "cosmetic/list.do"); 
    }
    
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString();
    MemberVO memberVO = cosmeticDAO.test(userid);
    
    mav.addObject("memberVO", memberVO);
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    System.out.println(memberVO);
    
    
    return mav;
  }
  
  @RequestMapping(value = "/cosmetic/create.do", 
      method = RequestMethod.POST)
  public ModelAndView create(CosmeticVO cosmeticVO,
                                        HttpServletRequest request, 
                                        HttpSession session) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic/message"); // /webapp/cosmetic/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
   
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
    String file6 = "";
    String file7 = "";
    String file8 = "";
    String file9 = "";
    String file10 = "";
    
    String upDir = Tool.getRealPath(request, "/cosmetic/storage");
    MultipartFile file2MF = cosmeticVO.getFile2MF();
    MultipartFile file4MF = cosmeticVO.getFile4MF();
    MultipartFile file6MF = cosmeticVO.getFile6MF();
    MultipartFile file8MF = cosmeticVO.getFile8MF();
    MultipartFile file10MF = cosmeticVO.getFile10MF();
    
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      cosmeticVO.setFile2(file2); // ���۵� ���ϸ� ����
      cosmeticVO.setSize2(file2MF.getSize());
 
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
    cosmeticVO.setFile1(file1); // Thumb �̹���
    cosmeticVO.setFile2(file2); // ���� �̹���
    // -------------------------------------------------------------------
    
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir);
      cosmeticVO.setFile4(file4); // ���۵� ���ϸ� ����
      cosmeticVO.setSize4(file4MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) {
        file3 = Tool.preview(upDir, file4, 120, 80);
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
    }
    cosmeticVO.setFile3(file3); // Thumb �̹���
    cosmeticVO.setFile4(file4); // ���� �̹���
    // -------------------------------------------------------------------
    
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir);
      cosmeticVO.setFile6(file6); // ���۵� ���ϸ� ����
      cosmeticVO.setSize6(file6MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) {
        file5 = Tool.preview(upDir, file6, 120, 80);
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------
    }
    cosmeticVO.setFile5(file5); // Thumb �̹���
    cosmeticVO.setFile6(file6); // ���� �̹���
    // -------------------------------------------------------------------
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir);
      cosmeticVO.setFile8(file8); // ���۵� ���ϸ� ����
      cosmeticVO.setSize8(file8MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) {
        file7 = Tool.preview(upDir, file8, 120, 80);
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------
    }
    cosmeticVO.setFile7(file7); // Thumb �̹���
    cosmeticVO.setFile8(file8); // ���� �̹���
    // -------------------------------------------------------------------
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir);
      cosmeticVO.setFile10(file10); // ���۵� ���ϸ� ����
      cosmeticVO.setSize10(file10MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) {
        file9 = Tool.preview(upDir, file10, 120, 80);
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
    }
    cosmeticVO.setFile9(file9); // Thumb �̹���
    cosmeticVO.setFile10(file10); // ���� �̹���
    // -------------------------------------------------------------------
 
    // cosmeticVO.setCno(2); // ȸ�� ������ ����
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
    
    if (cosmeticDAO.create(cosmeticVO) == 1) {
      msgs.add("��ϵǾ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    } else {
      msgs.add("��Ͽ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
    }
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
 /* *//**
   * ��ü ����� ����մϴ�.
   * 
   * @return
   */
    @RequestMapping(value = "/cosmetic/list.do", 
        method = RequestMethod.GET)
  public ModelAndView list3(SearchDTO searchDTO,
                               HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/cosmetic/list");
  
  int totalRecord = 0;
  
  // HashMap hashMap = new HashMap();
  HashMap<String, Object> hashMap = new HashMap<String, Object>();
  hashMap.put("col", searchDTO.getCol());
  hashMap.put("word", searchDTO.getWord());
  
  int recordPerPage = 5; // �������� ����� ���ڵ� ����
  // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 1���� ����
  int beginOfPage = (searchDTO.getNowPage() - 1) * 5; 
  // 1 page: 0
  // 2 page: 10
  // 3 page: 20
  int startNum = beginOfPage + 1; // ���� rownum, 1
  int endNum = beginOfPage + recordPerPage; // ���� rownum, 10
  hashMap.put("startNum", startNum);
  hashMap.put("endNum", endNum);
  
  List<CosmeticVO> list = cosmeticDAO.list3(hashMap); // �˻�
  Iterator<CosmeticVO> iter = list.iterator();
  while (iter.hasNext() == true) { // ���� ��� �˻�
    CosmeticVO vo = iter.next(); // ��� ����
  vo.setTitle(Tool.textLength(vo.getTitle(), 10));
  vo.setWdate(vo.getWdate().substring(0, 10));
  // vo.setFile1(Tool.textLength(10, vo.getFile1()));
  // vo.setFile2(Tool.textLength(10, vo.getFile2()));
  vo.setSize2Label(Tool.unit(vo.getSize2()));
  }
  mav.addObject("list", list);
  mav.addObject("root", request.getContextPath());
  
  totalRecord = cosmeticDAO.count(hashMap);
  mav.addObject("totalRecord", cosmeticDAO.count(hashMap)); // �˻��� ���ڵ� ����
  
  String paging = new Paging().paging5(
                      totalRecord, 
                      searchDTO.getNowPage(), 
                      recordPerPage, 
                      searchDTO.getCol(), 
                      searchDTO.getWord());
  mav.addObject("paging", paging);
  return mav;
  } 
  
 /* *//**
   * 
   * @param blogcategoryno
   *          ��ü ��Ͽ��� ������ �Խ��� ��ȣ
   * @param searchDTO �˻� ����         
   * @return ����� �Խ��� ���
   *//*
  @RequestMapping(value = "/cosmetic/list.do", method = RequestMethod.GET)
  public ModelAndView list2( 
                                        SearchDTO searchDTO,
                                        HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic/list");
 
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    List<CosmeticVO> list = cosmeticDAO.list2(hashMap); // �˻�
    Iterator<CosmeticVO> iter = list.iterator();
    while (iter.hasNext() == true) { // ���� ��� �˻�
      CosmeticVO vo = iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2())); // MB ���� ��ȯ
    }
    mav.addObject("list", list);
 
    mav.addObject("root", request.getContextPath());
    mav.addObject("totalRecord", cosmeticDAO.count(hashMap)); // �˻��� ���ڵ� ����
 
    return mav;
  }  */
  
  
    /**
     * ���� ��ȸ�մϴ�
     * @param cno
     * @return
     */
    @RequestMapping(value = "/cosmetic/read.do", method = RequestMethod.GET)
    public ModelAndView read(int cno,
          SearchDTO searchDTO, 
        HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/cosmetic/read");
      mav.addObject("CosmeticVO", cosmeticDAO.read(cno));
      
      
      cosmeticDAO.increaseCnt(cno);
      CosmeticVO cosmeticVO = cosmeticDAO.read(cno);
     
      cosmeticVO.setSize2Label(Tool.unit(cosmeticVO.getSize2()));
      cosmeticVO.setSize4Label(Tool.unit(cosmeticVO.getSize4()));
      cosmeticVO.setSize6Label(Tool.unit(cosmeticVO.getSize6()));
      cosmeticVO.setSize8Label(Tool.unit(cosmeticVO.getSize8()));
      cosmeticVO.setSize10Label(Tool.unit(cosmeticVO.getSize10()));
      
      mav.addObject("cosmeticVO", cosmeticVO);
      mav.addObject("searchDTO", searchDTO);
      
      String content = cosmeticVO.getContent();
      content = Tool.convertChar(content);
      cosmeticVO.setContent(content);
      
      
      //�Խ��ǿ� ���� ���� �ľ�
  //    BlogcategoryVO blogcategoryVO =  blogcategoryDAO.read(blogVO.getBlogcategoryno());
  //    mav.addObject("blogcategoryVO", blogcategoryVO);
      mav.addObject("root", request.getContextPath());
      return mav;
    }
  
  /**
   * ������
   * @param cno
   * @return
   */
  @RequestMapping(value="/cosmetic/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int cno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic/update"); 
    mav.addObject("cosmeticVO", cosmeticDAO.read(cno)); 
 
    return mav;
 
  }
   
  /**
   * �۰� ������ ���� ó��
   * 
   * @param cosmeticVO
   * @return
   */
  @RequestMapping(value = "/cosmetic/update.do", method = RequestMethod.POST)
  public ModelAndView update(CosmeticVO cosmeticVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    // -------------------------------------------------------------------
    // ���� ����1
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
 
    String upDir = Tool.getRealPath(request, "/cosmetic/storage");
    MultipartFile file2MF = cosmeticVO.getFile2MF();
    CosmeticVO oldVO = cosmeticDAO.read(cosmeticVO.getCno());
 
    if (file2MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); //���� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); //���ο� ���� ����
      cosmeticVO.setFile2(file2); // ���ο� ���ϸ� ����
      cosmeticVO.setSize2(file2MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) { // �̹������� �˻�
        Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
        file1 = Tool.preview(upDir, file2, 120, 80); //thumb �̹��� ����
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //���Ͼ��ε带 ������� �ʴ� ���
      file1 = oldVO.getFile1(); //���� ���ϵ��� ���
      file2 = oldVO.getFile2();
    }
    cosmeticVO.setFile1(file1);
    cosmeticVO.setFile2(file2);
    // -------------------------------------------------------------------
 
    // -------------------------------------------------------------------
    // ���� ����2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
 
    String upDir2 = Tool.getRealPath(request, "/cosmetic/storage");
    MultipartFile file4MF = cosmeticVO.getFile4MF();
    CosmeticVO oldVO2 = cosmeticDAO.read(cosmeticVO.getCno());
 
    if (file4MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir2, oldVO2.getFile4()); //���� ���� ����
      file4 = Upload.saveFileSpring(file4MF, upDir2); //���ο� ���� ����
      cosmeticVO.setFile4(file4); // ���ο� ���ϸ� ����
      cosmeticVO.setSize4(file4MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) { // �̹������� �˻�
        Tool.deleteFile(upDir2, oldVO2.getFile3()); // ���� ����
        file3 = Tool.preview(upDir2, file4, 120, 80); //thumb �̹��� ����
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //���Ͼ��ε带 ������� �ʴ� ���
      file3 = oldVO2.getFile3(); //���� ���ϵ��� ���
      file4 = oldVO2.getFile4();
    }
    cosmeticVO.setFile3(file3);
    cosmeticVO.setFile4(file4);
    // -------------------------------------------------------------------
    
    
    // -------------------------------------------------------------------
    // ���� ����3
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
 
    String upDir3 = Tool.getRealPath(request, "/cosmetic/storage");
    MultipartFile file6MF = cosmeticVO.getFile6MF();
    CosmeticVO oldVO3 = cosmeticDAO.read(cosmeticVO.getCno());
 
    if (file6MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir3, oldVO3.getFile6()); //���� ���� ����
      file6 = Upload.saveFileSpring(file6MF, upDir3); //���ο� ���� ����
      cosmeticVO.setFile6(file6); // ���ο� ���ϸ� ����
      cosmeticVO.setSize6(file6MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) { // �̹������� �˻�
        Tool.deleteFile(upDir3, oldVO3.getFile5()); // ���� ����
        file5 = Tool.preview(upDir3, file6, 120, 80); //thumb �̹��� ����
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //���Ͼ��ε带 ������� �ʴ� ���
      file5 = oldVO3.getFile5(); //���� ���ϵ��� ���
      file6 = oldVO3.getFile6();
    }
    cosmeticVO.setFile5(file5);
    cosmeticVO.setFile6(file6);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // ���� ����4
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
 
    String upDir4 = Tool.getRealPath(request, "/cosmetic/storage");
    MultipartFile file8MF = cosmeticVO.getFile8MF();
    CosmeticVO oldVO4 = cosmeticDAO.read(cosmeticVO.getCno());
 
    if (file8MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir4, oldVO4.getFile8()); //���� ���� ����
      file8 = Upload.saveFileSpring(file8MF, upDir4); //���ο� ���� ����
      cosmeticVO.setFile8(file8); // ���ο� ���ϸ� ����
      cosmeticVO.setSize8(file8MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) { // �̹������� �˻�
        Tool.deleteFile(upDir4, oldVO4.getFile7()); // ���� ����
        file7 = Tool.preview(upDir4, file8, 120, 80); //thumb �̹��� ����
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //���Ͼ��ε带 ������� �ʴ� ���
      file7 = oldVO4.getFile7(); //���� ���ϵ��� ���
      file8 = oldVO4.getFile8();
    }
    cosmeticVO.setFile7(file7);
    cosmeticVO.setFile8(file8);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // ���� ����5
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
 
    String upDir5 = Tool.getRealPath(request, "/cosmetic/storage");
    MultipartFile file10MF = cosmeticVO.getFile10MF();
    CosmeticVO oldVO5 = cosmeticDAO.read(cosmeticVO.getCno());
 
    if (file10MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir5, oldVO5.getFile10()); //���� ���� ����
      file10 = Upload.saveFileSpring(file10MF, upDir5); //���ο� ���� ����
      cosmeticVO.setFile10(file10); // ���ο� ���ϸ� ����
      cosmeticVO.setSize10(file10MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb ���� ����
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) { // �̹������� �˻�
        Tool.deleteFile(upDir5, oldVO5.getFile9()); // ���� ����
        file9 = Tool.preview(upDir5, file10, 120, 80); //thumb �̹��� ����
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //���Ͼ��ε带 ������� �ʴ� ���
      file9 = oldVO5.getFile9(); //���� ���ϵ��� ���
      file10 = oldVO5.getFile10();
    }
    cosmeticVO.setFile9(file9);
    cosmeticVO.setFile10(file10);
    // -------------------------------------------------------------------
    
    
    if (cosmeticDAO.update(cosmeticVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/cosmetic/read.do?cno=" + cosmeticVO.getCno() ); // Ȯ���� ���
    } else {
      msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
  /**
   * ������
   * @param cno
   * @return
   */
  @RequestMapping(value = "/cosmetic/delete.do", 
                            method = RequestMethod.GET)
  public ModelAndView delete(int cno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic/delete"); // /webapp/cosmetic/delete.jsp
    mav.addObject("cno", cno);
   
    return mav;
  }
  
  /**
   * ���� ó��
   * @param cosmeticVO
   * @return
   */
  @RequestMapping(value = "/cosmetic/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(CosmeticVO cosmeticVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (cosmeticDAO.delete(cosmeticVO.getCno()) == 1) {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./list.do?cno="+cosmeticVO.getCno()+"'\">���</button>");
      
    } else {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./index.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?cno="+cosmeticVO.getCno()+"'\">���</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
}
