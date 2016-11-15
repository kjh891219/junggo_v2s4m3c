package dev.mvc.art;

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
public class ArtCont {
  @Autowired
  @Qualifier("dev.mvc.art.ArtDAO")
  private ArtDAOInter artDAO;
  

  public ArtCont() {
    System.out.println("--> ArtCont created."); 
  }
  
  /**
   * ���ڵ� ���
   * @param
   * @return
   */
 @RequestMapping(value = "/art/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException { 
  // System.out.println("--> create() GET called.");
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/art/create"); // /webapp/carproduct/create.jsp
  
  
  response.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  if (session.getAttribute("userid") == null ){
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
     + "location.href = '../login.do';"
     + "</script>"); 
    session.setAttribute("url", "art/list.do");//
   
    
    
  } else {
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>" 
        + "location.href = './create.jsp';"
        + "</script>");
    
  }
  
  String userid = session.getAttribute("userid").toString();
  String nickname = session.getAttribute("nickname").toString();
  
  mav.addObject("userid", userid);

  return mav;
}
 
 
 /**
  * ��� ó��
  * @param carproductVO
  * @param request
  * @param session
  * @return
  */
 @RequestMapping(value = "/art/create.do", 
                            method = RequestMethod.POST)
 public ModelAndView create(ArtVO artVO, 
                                          HttpServletRequest request, 
                                          HttpSession session) {
   // System.out.println("--> create() POST called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/art/message");
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // ���� ����
   // -------------------------------------------------------------------
   String thumb = "";
   String file1 = "";
   long size1 = 0;
   String upDir = Tool.getRealPath(request, "/art/storage");
   MultipartFile file1MF = artVO.getFile1MF();
   System.out.println("file1MF:"+file1MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size1 = file1MF.getSize();
   if (size1 > 0) {
     file1 = Upload.saveFileSpring(file1MF, upDir);
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
   artVO.setThumb(thumb); // Thumb �̹���
   artVO.setFile1(file1); // ���� �̹���
   artVO.setSize1(size1); // ���� �̹���
   // -------------------------------------------------------------------
   
   
   // -------------------------------------------------------------------
   // ���� ����2
   // -------------------------------------------------------------------
   String file2 = "";
   long size2 = 0;
   MultipartFile file2MF = artVO.getFile2MF();
   System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size2 = file2MF.getSize();
   if (size2 > 0) {
     file2 = Upload.saveFileSpring(file2MF, upDir);
   }
   artVO.setFile2(file2); // ���� �̹���
   artVO.setSize2(size2); // ���� �̹���
   // -------------------------------------------------------------------
   
   // -------------------------------------------------------------------
   // ���� ���� 3
   // -------------------------------------------------------------------
   String file3 = "";
   long size3 = 0;
   MultipartFile file3MF = artVO.getFile3MF();
   // System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size3 = file3MF.getSize();
   if (size3 > 0) {
     file3 = Upload.saveFileSpring(file3MF, upDir);
   }
   artVO.setFile3(file3); // ���� �̹���
   artVO.setSize3(size3); // ���� �̹���
   // -------------------------------------------------------------------

   // -------------------------------------------------------------------
   // ���� ���� 4
   // -------------------------------------------------------------------
   String file4 = "";
   long size4 = 0;
   MultipartFile file4MF = artVO.getFile4MF();

   size4 = file4MF.getSize();
   if (size4 > 0) {
     file4 = Upload.saveFileSpring(file4MF, upDir);
   }
   artVO.setFile4(file4); // ���� �̹���
   artVO.setSize4(size4); // ���� �̹���
   // -------------------------------------------------------------------
   
   
   // -------------------------------------------------------------------
   // ���� ���� 5
   // -------------------------------------------------------------------
   String file5 = "";
   long size5 = 0;
   MultipartFile file5MF = artVO.getFile5MF();

   size5 = file5MF.getSize();
   if (size5 > 0) {
     file5 = Upload.saveFileSpring(file5MF, upDir);
   }
   artVO.setFile5(file5); // ���� �̹���
   artVO.setSize5(size5); // ���� �̹���
   // -------------------------------------------------------------------
   
   
   //usedcarVO.setU_no(4); // ȸ�� ������ ����
   // Integer itg = (Integer)(session.getAttribute("mno"));
   // blogVO.setMno(itg.intValue());
    
   if (artDAO.create(artVO) == 1) {
     msgs.add("���� ����߽��ϴ�.");
     links.add("<button type='button' onclick=\"location.href='./create.do?ano="
             + artVO.getAno() + "'\">��� ���</button>");
   } else {
     msgs.add("�� ��Ͽ� �����߽��ϴ�.");
     msgs.add("�ٽ� �õ����ּ���.");
     links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
   }

   links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
   links.add("<button type='button' onclick=\"location.href='./list.do?ano="
           + artVO.getAno() + "'\">���</button>");
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);

   return mav;
 }
  
  /**
   * ��ü ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/art/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/art/list_grid3"); // /webapp/member/list.jsp
    
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
    List<ArtVO> list = artDAO.list3(hashMap);
    Iterator<ArtVO> iter = list.iterator();
    while (iter.hasNext() == true) { // ���� ��� �˻�
      ArtVO vo = iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2())); // MB ���� ��ȯ
      vo.setSize4Label(Tool.unit(vo.getSize4()));
      
    }
    
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    
    totalRecord = artDAO.count(hashMap);
    mav.addObject("totalRecord", artDAO.count(hashMap));
    
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
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * @param ano
   * @return
   */
  @RequestMapping(value = "/art/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ano, SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/art/read");
    artDAO.increaseCnt(ano);
    mav.addObject("artVO", artDAO.read(ano));
    mav.addObject("searchDTO", searchDTO);
    return mav;
  }
  
  /**
   * ������
   * @param ano
   * @return
   */
  @RequestMapping(value = "/art/delete.do", 
                              method = RequestMethod.GET)
  public ModelAndView delete(int ano) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/art/delete"); // /webapp/blog/delete.jsp
    mav.addObject("ano", ano);
    return mav;
  }
  
  /**
   * ���� ó��
   * @param blogVO
   * @return
   */
  @RequestMapping(value = "/art/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(ArtVO artVO, SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (artDAO.delete(artVO.getAno()) == 1) {
      
      mav.setViewName("redirect:/art/list.do");//Ȯ���� ���

    } else {
      msgs.add("�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'>���</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  /**
   * ������
   * @param ano
   * @return
   */
  @RequestMapping(value="/art/update.do", 
                             method=RequestMethod.GET)
  public ModelAndView update(int ano){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/art/update");
    ArtVO artVO = artDAO.read(ano);
    mav.addObject("artVO", artVO);
   

    return mav;

  }
   
  /**
   * �۰� ������ ���� ó��
   * @param carproductVO
   * @return
   */
  @RequestMapping(value = "/art/update.do", 
                             method = RequestMethod.POST)
  public ModelAndView update(ArtVO artVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    // -------------------------------------------------------------------
    // ���� ���� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/art/storage");
    ArtVO oldVO = artDAO.read(artVO.getAno());

    // -------------------------------------------------------------------
    // 1��° ����
    // -------------------------------------------------------------------  
    String thumb = "";
    String file1 = "";
    long size1 = 0;
    // <input type="file" name='file1MF' id='file1MF' size='40' >
    MultipartFile file1MF = artVO.getFile1MF();
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
    artVO.setThumb(thumb);
    // System.out.println("--> thumb: " + thumb);  
    artVO.setFile1(file1); 
    artVO.setSize1(size1);
    // -------------------------------------------------------------------
        
    // -------------------------------------------------------------------
    // 2��° ����
    // -------------------------------------------------------------------  
    String file2 = "";
    long size2 = 0;
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = artVO.getFile2MF();
    size2 = file2MF.getSize();
    if (size2 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
      file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����

    } else {
      file2 = oldVO.getFile2(); // ���� ���ε带���� �ʴ� ���
      size2 = oldVO.getSize2();
    }
    artVO.setFile2(file2); 
    artVO.setSize2(size2);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 3��° ����
    // -------------------------------------------------------------------  
    String file3 = "";
    long size3 = 0;
    // <input type="file" name='file3MF' id='file3MF' size='40' >
    MultipartFile file3MF = artVO.getFile3MF();
    size3 = file3MF.getSize();
    if (size3 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      System.out.println("3��° ������ �Դ�");
      Tool.deleteFile(upDir, oldVO.getFile3()); // ���� ��ϵ� ���� ����
      file3 = Upload.saveFileSpring(file3MF, upDir); // ���ο� ���� ����

    } else {
      file3 = oldVO.getFile3(); // ���� ���ε带���� �ʴ� ���
      size3 = oldVO.getSize3();
    }
    artVO.setFile3(file3); 
    artVO.setSize3(size3);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 4��° ����
    // -------------------------------------------------------------------  
    String file4 = "";
    long size4 = 0;
    // <input type="file" name='file4MF' id='file4MF' size='40' >
    MultipartFile file4MF = artVO.getFile4MF();
    size4 = file4MF.getSize();
    if (size4 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile4()); // ���� ��ϵ� ���� ����
      file4 = Upload.saveFileSpring(file4MF, upDir); // ���ο� ���� ����

    } else {
      file4 = oldVO.getFile4(); // ���� ���ε带���� �ʴ� ���
      size4 = oldVO.getSize4();
    }
    artVO.setFile4(file4); 
    artVO.setSize4(size4);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 5��° ����
    // -------------------------------------------------------------------  
    String file5 = "";
    long size5 = 0;
    // <input type="file" name='file5MF' id='file5MF' size='40' >
    MultipartFile file5MF = artVO.getFile5MF();
    size5 = file5MF.getSize();
    if (size5 > 0) { // ���ο� ������ �����ϴ��� Ȯ��
      Tool.deleteFile(upDir, oldVO.getFile5()); // ���� ��ϵ� ���� ����
      file5 = Upload.saveFileSpring(file5MF, upDir); // ���ο� ���� ����

    } else {
      file5 = oldVO.getFile5(); // ���� ���ε带���� �ʴ� ���
      size5 = oldVO.getSize5();
    }
    artVO.setFile5(file5); 
    artVO.setSize5(size5);
    // -------------------------------------------------------------------


    

    if (artDAO.update(artVO) == 1) {
      // ������ ��ȸ�� �ڵ� �̵�
      mav.setViewName("redirect:/art/read.do?ano=" + artVO.getAno()
          + "&ano=" + artVO.getAno()); // Ȯ���� ���
    } else {
      msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?ano="
             + artVO.getAno() + "'\">���</button>");
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
  @RequestMapping(value = "/art/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/art/list2"); // /webapp/member/list.jsp
     
     
     List<ArtVO> list = artDAO.newlist();
     Iterator<ArtVO> iter = list.iterator(); // ��ü�� ���������� �����ϴ� ���
     while(iter.hasNext() == true){  // ���� ��� �˻�
       ArtVO vo = iter.next();  // ��� ����
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // ���ڿ� 10�� 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // �� �� ��
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     return mav;
  }


}
