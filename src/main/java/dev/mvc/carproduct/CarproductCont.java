package dev.mvc.carproduct;
 
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
public class CarproductCont {
  @Autowired
  @Qualifier("dev.mvc.carproduct.CarproductDAO")
  private CarproductDAOInter carproductDAO;
  
  public CarproductCont(){
    System.out.println("--> CarproductCont created.");
  }
  
  /**
   * ���ڵ� ���
   * @param
   * @return
   */
 @RequestMapping(value = "/carproduct/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException { 
  // System.out.println("--> create() GET called.");
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/carproduct/create"); // /webapp/carproduct/create.jsp
  
  
  response.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  if (session.getAttribute("userid") == null ){
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
     + "location.href = '../member/login.do';"
     + "</script>"); 
    session.setAttribute("url", "carproduct/list.do");//
   
    
    
  } else {
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>" 
        + "location.href = './create.jsp';"
        + "</script>");
    
  }
  
  
  
  String userid = session.getAttribute("userid").toString();
  MemberVO memberVO = carproductDAO.test(userid);
  
  mav.addObject("memberVO", memberVO);
  mav.addObject("userid", userid);
  System.out.println(memberVO);

  return mav;
}
 
 

/* @RequestMapping(value = "/carproduct/favorite_create.do", 
     method = RequestMethod.GET)
 public void favorite_create(CarproductVO carproductVO, 
     HttpServletRequest request, 
     HttpSession session, HttpServletResponse response) throws IOException {
     response.setCharacterEncoding("UTF-8");
     PrintWriter writer = response.getWriter();
     writer.println
       ("<script>alert('�̾߾߾߾߾߾�');" 
         + "location.href = './read.do';"
         + "</script>"
       );
 }*/
 
 /**
  * ��� ó��
  * @param carproductVO
  * @param request
  * @param session
  * @return
 * @throws IOException 
  */
 @RequestMapping(value = "/carproduct/create.do", 
                            method = RequestMethod.POST)
 public ModelAndView create(CarproductVO carproductVO, 
                                          HttpServletRequest request, 
                                          HttpSession session) {
   // System.out.println("--> create() POST called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/carproduct/message");
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // ���� ����
   // -------------------------------------------------------------------
   String thumb = "";
   String file1 = "";
   long size1 = 0;
   String upDir = Tool.getRealPath(request, "/carproduct/storage");
   MultipartFile file1MF = carproductVO.getFile1MF();
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
   carproductVO.setThumb(thumb); // Thumb �̹���
   carproductVO.setFile1(file1); // ���� �̹���
   carproductVO.setSize1(size1); // ���� �̹���
   // -------------------------------------------------------------------
   
   
   // -------------------------------------------------------------------
   // ���� ����2
   // -------------------------------------------------------------------
   String file2 = "";
   long size2 = 0;
   MultipartFile file2MF = carproductVO.getFile2MF();
   System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size2 = file2MF.getSize();
   if (size2 > 0) {
     file2 = Upload.saveFileSpring(file2MF, upDir);
   }
   carproductVO.setFile2(file2); // ���� �̹���
   carproductVO.setSize2(size2); // ���� �̹���
   // -------------------------------------------------------------------
   
   // -------------------------------------------------------------------
   // ���� ���� 3
   // -------------------------------------------------------------------
   String file3 = "";
   long size3 = 0;
   MultipartFile file3MF = carproductVO.getFile3MF();
   // System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size3 = file3MF.getSize();
   if (size3 > 0) {
     file3 = Upload.saveFileSpring(file3MF, upDir);
   }
   carproductVO.setFile3(file3); // ���� �̹���
   carproductVO.setSize3(size3); // ���� �̹���
   // -------------------------------------------------------------------

   // -------------------------------------------------------------------
   // ���� ���� 4
   // -------------------------------------------------------------------
   String file4 = "";
   long size4 = 0;
   MultipartFile file4MF = carproductVO.getFile4MF();

   size4 = file4MF.getSize();
   if (size4 > 0) {
     file4 = Upload.saveFileSpring(file4MF, upDir);
   }
   carproductVO.setFile4(file4); // ���� �̹���
   carproductVO.setSize4(size4); // ���� �̹���
   // -------------------------------------------------------------------
   
   
   // -------------------------------------------------------------------
   // ���� ���� 5
   // -------------------------------------------------------------------
   String file5 = "";
   long size5 = 0;
   MultipartFile file5MF = carproductVO.getFile5MF();

   size5 = file5MF.getSize();
   if (size5 > 0) {
     file5 = Upload.saveFileSpring(file5MF, upDir);
   }
   carproductVO.setFile5(file5); // ���� �̹���
   carproductVO.setSize5(size5); // ���� �̹���
   // -------------------------------------------------------------------
   
   
   //usedcarVO.setU_no(4); // ȸ�� ������ ����
   // Integer itg = (Integer)(session.getAttribute("mno"));
   // blogVO.setMno(itg.intValue());
    
   if (carproductDAO.create(carproductVO) == 1) {
     msgs.add("���� ����߽��ϴ�.");
     links.add("<button type='button' onclick=\"location.href='./create.do?p_no="
             + carproductVO.getP_no() + "'\">��� ���</button>");
   } else {
     msgs.add("�� ��Ͽ� �����߽��ϴ�.");
     msgs.add("�ٽ� �õ����ּ���.");
     links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
   }

   links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
   links.add("<button type='button' onclick=\"location.href='./list.do?p_no="
           + carproductVO.getP_no() + "'\">���</button>");
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);

   return mav;
 }


 
/**
 * �Ѱ��� ���ڵ� ��ȸ
 * @param p_no
 * @return
 */
@RequestMapping(value = "/carproduct/read.do", method = RequestMethod.GET)
public ModelAndView read(int p_no, SearchDTO searchDTO) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/carproduct/read");
  
  if(p_no>carproductDAO.maxp_no()){
    p_no = carproductDAO.maxp_no();
  }
  
  if(p_no<carproductDAO.minp_no()){
    p_no = carproductDAO.minp_no();
  }
  
  carproductDAO.increaseCnt(p_no);
  mav.addObject("carproductVO", carproductDAO.read(p_no));
  mav.addObject("searchDTO", searchDTO);
  return mav;
}

    /**
     * ������
     * @param p_no
     * @return
     */
    @RequestMapping(value = "/carproduct/delete.do", method = RequestMethod.GET)
    public ModelAndView delete(int p_no) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/carproduct/delete"); // /webapp/member/delete.jsp
      mav.addObject("p_no", p_no);
      
      return mav;
    }

 /**
 * ���� ó��
 * @param blogVO
 * @return
 */
@RequestMapping(value = "/carproduct/delete.do", 
                           method = RequestMethod.POST)
public ModelAndView delete(CarproductVO carproductVO) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/carproduct/message");

  ArrayList<String> msgs = new ArrayList<String>();
  ArrayList<String> links = new ArrayList<String>();
  
  if (carproductDAO.delete(carproductVO.getP_no()) == 1) {
    mav.setViewName("redirect:/carproduct/list.do?p_no=" + carproductVO.getP_no());//Ȯ���� ���

  } else {
    msgs.add("�� ������ �����߽��ϴ�.");
    links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
    links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do?p_no="+carproductVO.getP_no()+"'\">���</button>");
  }
  
  mav.addObject("msgs", msgs);
  mav.addObject("links", links);
  
  return mav;
}
   

/**
 * ������
 * @param p_no
 * @return
 */
@RequestMapping(value="/carproduct/update.do", 
                           method=RequestMethod.GET)
public ModelAndView update(int p_no){  
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/carproduct/update");
  CarproductVO carproductVO = carproductDAO.read(p_no);
  mav.addObject("carproductVO", carproductVO);
 

  return mav;

}
 
/**
 * �۰� ������ ���� ó��
 * @param carproductVO
 * @return
 */
@RequestMapping(value = "/carproduct/update.do", 
                           method = RequestMethod.POST)
public ModelAndView update(CarproductVO carproductVO, HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();

  ArrayList<String> msgs = new ArrayList<String>();
  ArrayList<String> links = new ArrayList<String>();

  // -------------------------------------------------------------------
  // ���� ���� ����
  // -------------------------------------------------------------------
  String upDir = Tool.getRealPath(request, "/carproduct/storage");
  CarproductVO oldVO = carproductDAO.read(carproductVO.getP_no());

  // -------------------------------------------------------------------
  // 1��° ����
  // -------------------------------------------------------------------  
  String thumb = "";
  String file1 = "";
  long size1 = 0;
  // <input type="file" name='file1MF' id='file1MF' size='40' >
  MultipartFile file1MF = carproductVO.getFile1MF();
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
  carproductVO.setThumb(thumb);
  // System.out.println("--> thumb: " + thumb);  
  carproductVO.setFile1(file1); 
  carproductVO.setSize1(size1);
  // -------------------------------------------------------------------
      
  // -------------------------------------------------------------------
  // 2��° ����
  // -------------------------------------------------------------------  
  String file2 = "";
  long size2 = 0;
  // <input type="file" name='file2MF' id='file2MF' size='40' >
  MultipartFile file2MF = carproductVO.getFile2MF();
  size2 = file2MF.getSize();
  if (size2 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
    Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
    file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����

  } else {
    file2 = oldVO.getFile2(); // ���� ���ε带���� �ʴ� ���
    size2 = oldVO.getSize2();
  }
  carproductVO.setFile2(file2); 
  carproductVO.setSize2(size2);
  // -------------------------------------------------------------------
  
  // -------------------------------------------------------------------
  // 3��° ����
  // -------------------------------------------------------------------  
  String file3 = "";
  long size3 = 0;
  // <input type="file" name='file3MF' id='file3MF' size='40' >
  MultipartFile file3MF = carproductVO.getFile3MF();
  size3 = file3MF.getSize();
  if (size3 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
    Tool.deleteFile(upDir, oldVO.getFile3()); // ���� ��ϵ� ���� ����
    file3 = Upload.saveFileSpring(file3MF, upDir); // ���ο� ���� ����

  } else {
    file3 = oldVO.getFile3(); // ���� ���ε带���� �ʴ� ���
    size3 = oldVO.getSize3();
  }
  carproductVO.setFile3(file3); 
  carproductVO.setSize3(size3);
  // -------------------------------------------------------------------
  
  // -------------------------------------------------------------------
  // 4��° ����
  // -------------------------------------------------------------------  
  String file4 = "";
  long size4 = 0;
  // <input type="file" name='file4MF' id='file4MF' size='40' >
  MultipartFile file4MF = carproductVO.getFile4MF();
  size4 = file4MF.getSize();
  if (size4 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
    Tool.deleteFile(upDir, oldVO.getFile4()); // ���� ��ϵ� ���� ����
    file4 = Upload.saveFileSpring(file4MF, upDir); // ���ο� ���� ����

  } else {
    file4 = oldVO.getFile4(); // ���� ���ε带���� �ʴ� ���
    size4 = oldVO.getSize4();
  }
  carproductVO.setFile4(file4); 
  carproductVO.setSize4(size4);
  // -------------------------------------------------------------------
  
  // -------------------------------------------------------------------
  // 5��° ����
  // -------------------------------------------------------------------  
  String file5 = "";
  long size5 = 0;
  // <input type="file" name='file5MF' id='file5MF' size='40' >
  MultipartFile file5MF = carproductVO.getFile5MF();
  size5 = file5MF.getSize();
  if (size5 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
    Tool.deleteFile(upDir, oldVO.getFile5()); // ���� ��ϵ� ���� ����
    file5 = Upload.saveFileSpring(file5MF, upDir); // ���ο� ���� ����

  } else {
    file5 = oldVO.getFile5(); // ���� ���ε带���� �ʴ� ���
    size5 = oldVO.getSize5();
  }
  carproductVO.setFile5(file5); 
  carproductVO.setSize5(size5);
  // -------------------------------------------------------------------


  

  if (carproductDAO.update(carproductVO) == 1) {
    // ������ ��ȸ�� �ڵ� �̵�
    mav.setViewName("redirect:/carproduct/read.do?p_no=" + carproductVO.getP_no()
        + "&p_no=" + carproductVO.getP_no()); // Ȯ���� ���
  } else {
    msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
    links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do?p_no="
           + carproductVO.getP_no() + "'\">���</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
  }

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
@RequestMapping(value = "/carproduct/list.do", 
                           method = RequestMethod.GET)
public ModelAndView list(
                                      SearchDTO searchDTO,
                                      HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/carproduct/list");
  int totalRecord = 0;

  // HashMap hashMap = new HashMap();
  HashMap<String, Object> hashMap = new HashMap<String, Object>();
  hashMap.put("col", searchDTO.getCol());
  hashMap.put("word", searchDTO.getWord());
  
  int recordPerPage = 9; // �������� ����� ���ڵ� ����
  // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 1���� ����
  int beginOfPage = (searchDTO.getNowPage() - 1) * 9; 
  // 1 page: 0
  // 2 page: 10
  // 3 page: 20
  int startNum = beginOfPage + 1; // ���� rownum, 1
  int endNum = beginOfPage + recordPerPage; // ���� rownum, 10
  hashMap.put("startNum", startNum);
  hashMap.put("endNum", endNum);

  List<CarproductVO> list = carproductDAO.list(hashMap); // �˻�
  Iterator<CarproductVO> iter = list.iterator();
  while (iter.hasNext() == true) { // ���� ��� �˻�
    CarproductVO vo = iter.next(); // ��� ����
    vo.setTitle(Tool.textLength(vo.getTitle(), 10));
    vo.setWdate(vo.getWdate().substring(0, 10));
    // vo.setFile1(Tool.textLength(10, vo.getFile1()));
    // vo.setFile2(Tool.textLength(10, vo.getFile2()));
    vo.setSize2Label(Tool.unit(vo.getSize2()));
  }
  mav.addObject("list", list);
  
  totalRecord = carproductDAO.count(hashMap);
  mav.addObject("totalRecord", carproductDAO.count(hashMap)); // �˻��� ���ڵ� ����

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
@RequestMapping(value = "/carproduct/list2.do", method = RequestMethod.GET)
public ModelAndView newlist() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/carproduct/list2"); // /webapp/member/list.jsp
   
   
   List<CarproductVO> list = carproductDAO.newlist();
   Iterator<CarproductVO> iter = list.iterator(); // ��ü�� ���������� �����ϴ� ���
   while(iter.hasNext() == true){  // ���� ��� �˻�
     CarproductVO vo = iter.next();  // ��� ����
     vo.setTitle(Tool.textLength(vo.getTitle(),10));   // ���ڿ� 10�� 
     //vo.setWdate(vo.getWdate().substring(0, 10));      // �� �� ��
     // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
     //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
     
   }
   mav.addObject("list2", list);
   
   
   return mav;
}



  
}