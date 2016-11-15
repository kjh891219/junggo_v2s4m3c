package dev.mvc.product;

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
public class ProductCont {
  @Autowired
  @Qualifier("dev.mvc.product.ProductDAO")
  private ProductDAOInter productDAO;
  
  public ProductCont(){
    System.out.println("--> ProductCont created.");
  }
  
  @RequestMapping(value = "/product/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
    System.out.println("--> create() GET called."); 
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/product/create"); // /webapp/product/create.jsp

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url",  "product/list.do");//
      
    }else{
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>"
          + "location.href = './create.jsp'; "
          + "</script>");
      session.setAttribute("url", "product/list.do"); 
    }
    
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString();
    MemberVO memberVO = productDAO.test(userid);
    
    mav.addObject("memberVO", memberVO);
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    System.out.println(memberVO);
    
    
    return mav;
  }
  
  @RequestMapping(value = "/product/create.do", 
      method = RequestMethod.POST)
  public ModelAndView create(ProductVO productVO,
                                        HttpServletRequest request, 
                                        HttpSession session) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/product/message"); // /webapp/product/message.jsp

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
    
    String upDir = Tool.getRealPath(request, "/product/storage");
    MultipartFile file2MF = productVO.getFile2MF();
    MultipartFile file4MF = productVO.getFile4MF();
    MultipartFile file6MF = productVO.getFile6MF();
    MultipartFile file8MF = productVO.getFile8MF();
    MultipartFile file10MF = productVO.getFile10MF();
    
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      productVO.setFile2(file2); // ���۵� ���ϸ� ����
      productVO.setSize2(file2MF.getSize());
 
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
    productVO.setFile1(file1); // Thumb �̹���
    productVO.setFile2(file2); // ���� �̹���
    // -------------------------------------------------------------------
    
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir);
      productVO.setFile4(file4); // ���۵� ���ϸ� ����
      productVO.setSize4(file4MF.getSize());
 
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
    productVO.setFile3(file3); // Thumb �̹���
    productVO.setFile4(file4); // ���� �̹���
    // -------------------------------------------------------------------
    
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir);
      productVO.setFile6(file6); // ���۵� ���ϸ� ����
      productVO.setSize6(file6MF.getSize());
 
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
    productVO.setFile5(file5); // Thumb �̹���
    productVO.setFile6(file6); // ���� �̹���
    // -------------------------------------------------------------------
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir);
      productVO.setFile8(file8); // ���۵� ���ϸ� ����
      productVO.setSize8(file8MF.getSize());
 
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
    productVO.setFile7(file7); // Thumb �̹���
    productVO.setFile8(file8); // ���� �̹���
    // -------------------------------------------------------------------
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir);
      productVO.setFile10(file10); // ���۵� ���ϸ� ����
      productVO.setSize10(file10MF.getSize());
 
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
    productVO.setFile9(file9); // Thumb �̹���
    productVO.setFile10(file10); // ���� �̹���
    // -------------------------------------------------------------------
 
    // productVO.setpno(2); // ȸ�� ������ ����
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
    
    if (productDAO.create(productVO) == 1) {
      msgs.add("��ϵǾ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
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
    @RequestMapping(value = "/product/list.do", 
        method = RequestMethod.GET)
  public ModelAndView list3(SearchDTO searchDTO,
                               HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/product/list");
  
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
  
  List<ProductVO> list = productDAO.list3(hashMap); // �˻�
  Iterator<ProductVO> iter = list.iterator();
  while (iter.hasNext() == true) { // ���� ��� �˻�
    ProductVO vo = iter.next(); // ��� ����
  vo.setTitle(Tool.textLength(vo.getTitle(), 10));
  vo.setWdate(vo.getWdate().substring(0, 10));
  // vo.setFile1(Tool.textLength(10, vo.getFile1()));
  // vo.setFile2(Tool.textLength(10, vo.getFile2()));
  vo.setSize2Label(Tool.unit(vo.getSize2()));
  }
  mav.addObject("list", list);
  mav.addObject("root", request.getContextPath());
  
  totalRecord = productDAO.count(hashMap);
  mav.addObject("totalRecord", productDAO.count(hashMap)); // �˻��� ���ڵ� ����
  
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
  @RequestMapping(value = "/product/list.do", method = RequestMethod.GET)
  public ModelAndView list2( 
                                        SearchDTO searchDTO,
                                        HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/product/list");
 
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    List<productVO> list = productDAO.list2(hashMap); // �˻�
    Iterator<productVO> iter = list.iterator();
    while (iter.hasNext() == true) { // ���� ��� �˻�
      productVO vo = iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2())); // MB ���� ��ȯ
    }
    mav.addObject("list", list);
 
    mav.addObject("root", request.getContextPath());
    mav.addObject("totalRecord", productDAO.count(hashMap)); // �˻��� ���ڵ� ����
 
    return mav;
  }  */
  
  
    /**
     * ���� ��ȸ�մϴ�
     * @param pno
     * @return
     */
    @RequestMapping(value = "/product/read.do", method = RequestMethod.GET)
    public ModelAndView read(int pno,
          SearchDTO searchDTO, 
        HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/product/read");
      mav.addObject("productVO", productDAO.read(pno));
      
      
      productDAO.increaseCnt(pno);
      ProductVO productVO = productDAO.read(pno);
     
      productVO.setSize2Label(Tool.unit(productVO.getSize2()));
      productVO.setSize4Label(Tool.unit(productVO.getSize4()));
      productVO.setSize6Label(Tool.unit(productVO.getSize6()));
      productVO.setSize8Label(Tool.unit(productVO.getSize8()));
      productVO.setSize10Label(Tool.unit(productVO.getSize10()));
      
      mav.addObject("productVO", productVO);
      mav.addObject("searchDTO", searchDTO);
      

      
      
      //�Խ��ǿ� ���� ���� �ľ�
  //    BlogcategoryVO blogcategoryVO =  blogcategoryDAO.read(blogVO.getBlogcategoryno());
  //    mav.addObject("blogcategoryVO", blogcategoryVO);
      mav.addObject("root", request.getContextPath());
      return mav;
    }
  
  /**
   * ������
   * @param pno
   * @return
   */
  @RequestMapping(value="/product/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int pno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/product/update"); 
    mav.addObject("productVO", productDAO.read(pno)); 
 
    return mav;
 
  }
   
  /**
   * �۰� ������ ���� ó��
   * 
   * @param productVO
   * @return
   */
  @RequestMapping(value = "/product/update.do", method = RequestMethod.POST)
  public ModelAndView update(ProductVO productVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    // -------------------------------------------------------------------
    // ���� ����1
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
 
    String upDir = Tool.getRealPath(request, "/product/storage");
    MultipartFile file2MF = productVO.getFile2MF();
    ProductVO oldVO = productDAO.read(productVO.getPno());
 
    if (file2MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); //���� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); //���ο� ���� ����
      productVO.setFile2(file2); // ���ο� ���ϸ� ����
      productVO.setSize2(file2MF.getSize());
 
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
    productVO.setFile1(file1);
    productVO.setFile2(file2);
    // -------------------------------------------------------------------
 
    // -------------------------------------------------------------------
    // ���� ����2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
 
    String upDir2 = Tool.getRealPath(request, "/product/storage");
    MultipartFile file4MF = productVO.getFile4MF();
    ProductVO oldVO2 = productDAO.read(productVO.getPno());
 
    if (file4MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir2, oldVO2.getFile4()); //���� ���� ����
      file4 = Upload.saveFileSpring(file4MF, upDir2); //���ο� ���� ����
      productVO.setFile4(file4); // ���ο� ���ϸ� ����
      productVO.setSize4(file4MF.getSize());
 
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
    productVO.setFile3(file3);
    productVO.setFile4(file4);
    // -------------------------------------------------------------------
    
    
    // -------------------------------------------------------------------
    // ���� ����3
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
 
    String upDir3 = Tool.getRealPath(request, "/product/storage");
    MultipartFile file6MF = productVO.getFile6MF();
    ProductVO oldVO3 = productDAO.read(productVO.getPno());
 
    if (file6MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir3, oldVO3.getFile6()); //���� ���� ����
      file6 = Upload.saveFileSpring(file6MF, upDir3); //���ο� ���� ����
      productVO.setFile6(file6); // ���ο� ���ϸ� ����
      productVO.setSize6(file6MF.getSize());
 
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
    productVO.setFile5(file5);
    productVO.setFile6(file6);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // ���� ����4
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
 
    String upDir4 = Tool.getRealPath(request, "/product/storage");
    MultipartFile file8MF = productVO.getFile8MF();
    ProductVO oldVO4 = productDAO.read(productVO.getPno());
 
    if (file8MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir4, oldVO4.getFile8()); //���� ���� ����
      file8 = Upload.saveFileSpring(file8MF, upDir4); //���ο� ���� ����
      productVO.setFile8(file8); // ���ο� ���ϸ� ����
      productVO.setSize8(file8MF.getSize());
 
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
    productVO.setFile7(file7);
    productVO.setFile8(file8);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // ���� ����5
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
 
    String upDir5 = Tool.getRealPath(request, "/product/storage");
    MultipartFile file10MF = productVO.getFile10MF();
    ProductVO oldVO5 = productDAO.read(productVO.getPno());
 
    if (file10MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir5, oldVO5.getFile10()); //���� ���� ����
      file10 = Upload.saveFileSpring(file10MF, upDir5); //���ο� ���� ����
      productVO.setFile10(file10); // ���ο� ���ϸ� ����
      productVO.setSize10(file10MF.getSize());
 
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
    productVO.setFile9(file9);
    productVO.setFile10(file10);
    // -------------------------------------------------------------------
    
    
    if (productDAO.update(productVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/product/read.do?pno=" + productVO.getPno() ); // Ȯ���� ���
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
   * @param pno
   * @return
   */
  @RequestMapping(value = "/product/delete.do", 
                            method = RequestMethod.GET)
  public ModelAndView delete(int pno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/product/delete"); // /webapp/product/delete.jsp
    mav.addObject("pno", pno);
   
    return mav;
  }
  
  /**
   * ���� ó��
   * @param productVO
   * @return
   */
  @RequestMapping(value = "/product/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(ProductVO productVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/product/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (productDAO.delete(productVO.getPno()) == 1) {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./list.do?pno="+productVO.getPno()+"'\">���</button>");
      
    } else {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?pno="+productVO.getPno()+"'\">���</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  /**
   * �ֱ� ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/product/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/product/list2"); // /webapp/member/list.jsp
     
     
     List<ProductVO> list = productDAO.newlist();
     Iterator<ProductVO> iter = list.iterator(); // ��ü�� ���������� �����ϴ� ���
     while(iter.hasNext() == true){  // ���� ��� �˻�
       ProductVO vo = iter.next();  // ��� ����
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // ���ڿ� 10�� 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // �� �� ��
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     
     return mav;
  }

}
