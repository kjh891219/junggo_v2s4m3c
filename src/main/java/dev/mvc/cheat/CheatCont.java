package dev.mvc.cheat;

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

import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class CheatCont {
  @Autowired
  @Qualifier("dev.mvc.cheat.CheatDAO")
  private CheatDAOInter cheatDAO;
  
  public CheatCont(){
    System.out.println("--> CheatCont created.");
  }
  
  @RequestMapping(value = "/cheat/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called."); 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/create"); // /webapp/cheat/create.jsp

    return mav;
  }
  
  @RequestMapping(value = "/cheat/create.do",  method = RequestMethod.POST)
  public ModelAndView create(CheatVO cheatVO, HttpServletRequest request, HttpSession session) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp

    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String upDir = Tool.getRealPath(request, "/cheat/storage");
    System.out.println(upDir);
    MultipartFile file2MF = cheatVO.getFile2MF();

    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      cheatVO.setFile2(file2); // ���۵� ���ϸ� ����
      cheatVO.setSize2(file2MF.getSize());

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
    cheatVO.setFile1(file1); // Thumb �̹���
    cheatVO.setFile2(file2); // ���� �̹���
    // -------------------------------------------------------------------
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (cheatDAO.create(cheatVO) == 1) {
      msgs.add("������ǰ �Ű� ��ϵǾ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    } else {
      msgs.add("������ǰ �Ű� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
 
  /**
   * 1�� ��ȸ
   * @param ctno
   * @param opentype : U(����), R(�󼼺���) 
   * @return
   */
  @RequestMapping(value = "/cheat/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno, String opentype) {
    ModelAndView mav = new ModelAndView();
    if(opentype.equals("R")){
      cheatDAO.setCnt(ctno); //��ȸ�� ����
    }
    
    mav.setViewName("/cheat/read");
    mav.addObject("cheatVO", cheatDAO.read(ctno));
    mav.addObject("opentype", opentype);
    return mav;
  }
  
  /**
  * ����Ʈ ����� �˻�+����¡+�亯��
  * �����Ͽ� ����մϴ�.
  * @param searchDTO �˻� ����         
  * @return ����� �Խ��� ���
  */
 @RequestMapping(value = "/cheat/list.do", 
                            method = RequestMethod.GET)
 public ModelAndView list(SearchDTO searchDTO,
                                       HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/cheat/list");
   int totalRecord = 0;
   
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("col", searchDTO.getCol());
   hashMap.put("word", searchDTO.getWord());
   System.out.println("PAGE:" + searchDTO.getNowPage() );
    
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

   List<CheatVO> list = cheatDAO.list(hashMap); // �˻�
   Iterator<CheatVO> iter = list.iterator();
   while (iter.hasNext() == true) { // ���� ��� �˻�
     CheatVO vo = iter.next(); // ��� ����
     vo.setTitle(Tool.textLength(vo.getTitle(), 20));
     vo.setWdate(vo.getWdate().substring(0, 10));
   }
   mav.addObject("list", list);
   mav.addObject("root", request.getContextPath());
   
   totalRecord = cheatDAO.count(hashMap);
   mav.addObject("totalRecord", cheatDAO.count(hashMap)); // �˻��� ���ڵ� ����
   
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
  * �۰� ������ ���� ó��
  * 
  * @param cheatVO
  * @return
  */
 @RequestMapping(value = "/cheat/update.do", method = RequestMethod.POST)
 public ModelAndView update(CheatVO cheatVO, HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();
   
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("ctno", cheatVO.getCtno());
   hashMap.put("passwd", cheatVO.getPasswd());
   
   if (cheatDAO.passwordCheck(hashMap) == 0)
   {
     mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
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

   String upDir = Tool.getRealPath(request, "/cheat/storage");
   // <input type="file" name='file2MF' id='file2MF' size='40' >
   MultipartFile file2MF = cheatVO.getFile2MF();
   CheatVO oldVO = cheatDAO.read(cheatVO.getCtno());

   if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
     Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
     file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
     cheatVO.setFile2(file2); // ���ο� ���ϸ� ����
     cheatVO.setSize2(file2MF.getSize()); // ���ο� ũ�� ����

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
   cheatVO.setFile1(file1);
   cheatVO.setFile2(file2);
   // -------------------------------------------------------------------

   if (cheatDAO.update(cheatVO) == 1) {
     // ������ ��ȸ�� �ڵ� �̵�
     mav.setViewName(
         "redirect:/cheat/read.do?ctno=" + cheatVO.getCtno());
   } else {
     mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
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
 @RequestMapping(value = "/cheat/delete.do", method = RequestMethod.GET)
 public ModelAndView delete(int ctno) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/cheat/delete"); // /webapp/cheat/delete.jsp
   mav.addObject("ctno", ctno);
   
   return mav;
 }

 /**
  * ���� ó��
  * 
  * @param cheatVO
  * @return
  */
 @RequestMapping(value = "/cheat/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(int ctno, String passwd) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/cheat/message");

   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("ctno", ctno);
   hashMap.put("passwd", passwd);
   if (cheatDAO.passwordCheck(hashMap) == 0)
   {
     msgs.add("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
     links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
     links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
     mav.addObject("msgs", msgs);
     mav.addObject("links", links);
     return mav;
   }
   
   if (cheatDAO.delete(ctno) == 1) {
     mav.setViewName("redirect:/cheat/list.do");
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
