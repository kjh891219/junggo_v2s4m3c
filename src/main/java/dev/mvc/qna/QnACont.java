package dev.mvc.qna;
 
import java.util.ArrayList;
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

import dev.mvc.qna.QnADAOInter;
import dev.mvc.qna.QnAVO;
import web.tool.SearchDTO;
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
  public ModelAndView create(QnAVO qnaVO,
        HttpServletRequest request, 
        HttpSession session) {
    System.out.println("--> create() POST called.");
    System.out.println("-->"+qnaVO.getContent());
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    long size2 = 0;
    String upDir = Tool.getRealPath(request, "/qna/storage");
    MultipartFile file2MF = qnaVO.getFile2MF();
    System.out.println("file2MF:"+file2MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size2 = file2MF.getSize();
    if (size2 > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) {
        file1 = Tool.preview(upDir, file2, 120, 80);
      } else {
        file1 = "";
      }
      
      // -------------------------------------------------------------------
    }
    qnaVO.setFile1(file1); // Thumb 이미지
    qnaVO.setFile2(file2); // 원본 이미지
    qnaVO.setSize2(size2); // 원본 이미지
    // -------------------------------------------------------------------
      
    qnaVO.setUserid("chanmi"); // 회원 연동시 변경
  /*    Integer itg = (Integer)(session.getAttribute("mno"));
    blogVO.setMno(itg.intValue());*/
    
 
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
    List<QnAVO> list = qnaDAO.list();
    
    mav.addObject("list", qnaDAO.list());
    
    return mav;
  }

  /**
   * 아이디별 목록 출력합니다
   * @return
   */
  @RequestMapping(value = "/qna/idlist.do", 
        method = RequestMethod.GET)
  public ModelAndView idlist(String userid) {
   
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/qna/idlist"); // /webapp/code/list.jsp
      userid = "chanmi";
      List<QnAVO> idlist = qnaDAO.idlist(userid);
      mav.addObject("idlist", qnaDAO.idlist(userid));
      
      return mav;
  }
  
  /**
   * 글을 조회합니다
   * @param mno
   * @return
   */
  @RequestMapping(value = "/qna/read.do", method = RequestMethod.GET)
  public ModelAndView read(int qnano, 
                                SearchDTO searchDTO,
                                      HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/read");
    QnAVO qnaVO = qnaDAO.read(qnano);
    mav.addObject("qnaVO", qnaVO); 
    
    String content = qnaVO.getContent();
    content = Tool.convertChar(content);
    qnaVO.setContent(content);
    mav.addObject("qnaVO",qnaVO);

    // 게시판에 대한 정보 파악
    mav.addObject("searchDTO", searchDTO);
 
    return mav;
  }
  
  
  /**
   * 삭제폼
   * @param blogno
   * @return
   */
  @RequestMapping(value = "/qna/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int qnano) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/delete"); // /webapp/member/delete.jsp
    mav.addObject("qnano", qnano);
    
    return mav;
  }
  
  /**
   * 삭제 처리
   * @param qnaVO
   * @return
   */
  @RequestMapping(value = "/qna/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(QnAVO qnaVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (qnaDAO.delete(qnaVO.getQnano()) == 1) {
      qnaDAO.delete(qnaVO.getCategoryno());
 
    } else {
      msgs.add("글 삭제에 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do\">목록</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  /**
   * 수정폼
   * @param qnano
   * @return
   */
  @RequestMapping(value="/qna/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int qnano){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/update"); 
    mav.addObject("qnaVO", qnaDAO.read(qnano)); 
 
    return mav;
 
  }
   
  /**
   * 글과 파일을 수정 처리
   * 
   * @param qnaVO
   * @return
   */
  @RequestMapping(value = "/qna/update.do", method = RequestMethod.POST)
  public ModelAndView update2(QnAVO qnaVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    long size2 = 0;
    
    String upDir = Tool.getRealPath(request, "/qna/storage");
    MultipartFile file2MF = qnaVO.getFile2MF();
    System.out.println("file2MF:"+file2MF);
    QnAVO oldVO = qnaDAO.read(qnaVO.getQnano());
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size2 = file2MF.getSize();
    if (size2 > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      qnaVO.setFile2(file2); // 새로운 전송된 파일명 저장
      qnaVO.setSize2(file2MF.getSize()); //새로운 크기 저장 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) {
        file1 = Tool.preview(upDir, file2, 120, 80);
      } else {
        file1 = "";
      }
      
      // -------------------------------------------------------------------
    }else{
       file1 = oldVO.getFile1(); // 파일 업로드를 하지 않는 경우
       file2 = oldVO.getFile2();
    }
    
       qnaVO.setFile1(file1); // Thumb 이미지
       qnaVO.setFile2(file2); // 원본 이미지
       qnaVO.setSize2(size2); // 원본 이미지
    // -------------------------------------------------------------------
       
       qnaVO.setUserid("chanmi"); // 회원 연동시 변경
       
    // -------------------------------------------------------------------
       
    if (qnaDAO.update(qnaVO) == 1) {
      // 수정후 조회로 자동 이동
      mav.setViewName("redirect:/qna/read.do?qnano=" + qnaVO.getQnano()); // 확장자 명시
    } else {
      msgs.add("게시판 수정에 실패 하셨습니다.");
      links
          .add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      links
          .add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
}
 