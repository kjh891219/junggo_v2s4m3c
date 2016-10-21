package dev.mvc.qna;
 
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.qna.QnADAOInter;
import dev.mvc.qna.QnAVO;
import web.tool.Tool;
import web.tool.Upload;
 
@Controller
public class QnACont {
  @Autowired
  @Qualifier("dev.mvc.qna.QnADAO")
  private QnADAOInter qnaDAO;
  
  public QnACont(){
    System.out.println("--> QnACont created.");
  }
  
  @RequestMapping(value = "/qna/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/create"); // /webapp/code/create.jsp
 
    return mav;
  }
  @RequestMapping(value = "/qna/create.do", method = RequestMethod.POST)
  public ModelAndView create(QnAVO qnaVO) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (qnaDAO.create(qnaVO) == 1) { 
      msgs.add("��ϵǾ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./create.jsp'\">��ӵ��</button>");
    } else {
      msgs.add("����� �����߽��ϴ�.");
      msgs.add("�ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  /**
   * ��ü ����� ����մϴ�.
   * @return
   */
  @RequestMapping(value = "/qna/list.do", 
                             method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/list"); // /webapp/code/list.jsp
    mav.addObject("list", qnaDAO.list());
 
    return mav;
  }
  
  @RequestMapping(value = "qna/read.do", method = RequestMethod.GET)
  public ModelAndView read(int qnano){
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/blog/read");
     QnAVO qnaVO = qnaDAO.read(qnano);
     mav.addObject("qnaVO", qnaVO);
     
     String content = qnaVO.getContent();
     content = Tool.convertChar(content);
     qnaVO.setContent(content);
     mav.addObject("qnaVO", qnaVO);
     
     
   return mav;
     
  }
  
  @RequestMapping(value="/qna/update.do", 
        method=RequestMethod.GET)
    public ModelAndView update(int qnano){  
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/qna/update"); 
      mav.addObject("qnaVO", qnaDAO.read(qnano)); 
   
      return mav;
   
    }
  
/*  @RequestMapping(value="/qna/update.do", 
        method=RequestMethod.POST)
    public ModelAndView update2(QnAVO qnaVO, HttpServletRequest request){
     ModelAndView mav = new ModelAndView();
     
     ArrayList<String> msgs = new ArrayList<String>();
     ArrayList<String> links = new ArrayList<String>();
     
     // -------------------------------------------------------------------
     // ���� ����
     // -------------------------------------------------------------------
     String file1 = "";
     String file2 = ""; 
     String upDir = Tool.getRealPath(request, "/blog/storage");
     MultipartFile file2MF = qnaVO.getFile2MF();
     QnAVO oldVO = qnaDAO.read(qnaVO.getQnano()); 
  
     if (file2MF.getSize() > 0) { //���ο� ������ �����ϴ��� Ȯ�� 
       Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
       file2 = Upload.saveFileSpring(file2MF, upDir); // ���ο� ���� ����
       qnaVO.setFile2(file2); // ���ο� ���۵� ���ϸ� ����
       qnaVO.setSize2(file2MF.getSize()); //���ο� ũ�� ���� 
  
       // -------------------------------------------------------------------
       // Thumb ���� ����
       // -------------------------------------------------------------------
       if (Tool.isImage(file2)) {
         Tool.deleteFile(upDir, oldVO.getQfile()); // ���� ����
         file1 = Tool.preview(upDir, file2, 120, 80);
       } else {
         file1 = "";
       }
       // -------------------------------------------------------------------
  
     } else {
       file1 = oldVO.getQfile(); // ���� ���ε带 ���� �ʴ� ���
       file2 = oldVO.getFile2();
     }
     qnaVO.setQfile(file1);
     qnaVO.setFile2(file2);
     // -------------------------------------------------------------------
  
     if (qnaDAO.update(qnaVO) == 1) {
       // ������ ��ȸ�� �ڵ� �̵�
       mav.setViewName("redirect:/blog/read.do?blogno=" + qnaVO.getQnano()
           + "&blogcategoryno=" + qnaVO.getBlogcategoryno()); // Ȯ���� ���
     } else {
       msgs.add("�Խ��� ������ ���� �ϼ̽��ϴ�.");
       links
           .add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
       links
           .add("<button type='button' onclick=\"location.href='./list.do?blogcategoryno="
               + blogVO.getBlogcategoryno() + "'\">���</button>");
       mav.addObject("msgs", msgs);
       mav.addObject("links", links);
     }
     
     
     return mav;
  }
  */
}
 