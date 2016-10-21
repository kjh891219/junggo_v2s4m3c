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
      msgs.add("등록되었습니다.");
      links.add("<button type='button' onclick=\"location.href='./create.jsp'\">계속등록</button>");
    } else {
      msgs.add("등록을 실패했습니다.");
      msgs.add("다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  /**
   * 전체 목록을 출력합니다.
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
     // 파일 전송
     // -------------------------------------------------------------------
     String file1 = "";
     String file2 = ""; 
     String upDir = Tool.getRealPath(request, "/blog/storage");
     MultipartFile file2MF = qnaVO.getFile2MF();
     QnAVO oldVO = qnaDAO.read(qnaVO.getQnano()); 
  
     if (file2MF.getSize() > 0) { //새로운 파일을 전송하는지 확인 
       Tool.deleteFile(upDir, oldVO.getFile2()); // 기존 등록된 파일 삭제
       file2 = Upload.saveFileSpring(file2MF, upDir); // 새로운 파일 저장
       qnaVO.setFile2(file2); // 새로운 전송된 파일명 저장
       qnaVO.setSize2(file2MF.getSize()); //새로운 크기 저장 
  
       // -------------------------------------------------------------------
       // Thumb 파일 생성
       // -------------------------------------------------------------------
       if (Tool.isImage(file2)) {
         Tool.deleteFile(upDir, oldVO.getQfile()); // 파일 삭제
         file1 = Tool.preview(upDir, file2, 120, 80);
       } else {
         file1 = "";
       }
       // -------------------------------------------------------------------
  
     } else {
       file1 = oldVO.getQfile(); // 파일 업로드를 하지 않는 경우
       file2 = oldVO.getFile2();
     }
     qnaVO.setQfile(file1);
     qnaVO.setFile2(file2);
     // -------------------------------------------------------------------
  
     if (qnaDAO.update(qnaVO) == 1) {
       // 수정후 조회로 자동 이동
       mav.setViewName("redirect:/blog/read.do?blogno=" + qnaVO.getQnano()
           + "&blogcategoryno=" + qnaVO.getBlogcategoryno()); // 확장자 명시
     } else {
       msgs.add("게시판 수정에 실패 하셨습니다.");
       links
           .add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
       links
           .add("<button type='button' onclick=\"location.href='./list.do?blogcategoryno="
               + blogVO.getBlogcategoryno() + "'\">목록</button>");
       mav.addObject("msgs", msgs);
       mav.addObject("links", links);
     }
     
     
     return mav;
  }
  */
}
 