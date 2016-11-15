package dev.mvc.computer;

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

import dev.mvc.camera.CameraVO;
import dev.mvc.computer.ComputerVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class ComputerCont {
  @Autowired
  @Qualifier("dev.mvc.computer.ComputerDAO")
  private ComputerDAOInter computerDAO;

  /*@Autowired
  @Qualifier("dev.mvc.computer.CReplyDAO")
  private CReplyDAOInter cReplyDAO;*/

  public ComputerCont() {
    System.out.println("--> ComputerCont created.");
  }

  /**
   * 
   * @param opentype
   *          : U(수정), R(등록)
   * @return
   * @throws IOException 
   */
  @RequestMapping(value = "/computer/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpServletResponse response, HttpSession session) throws IOException {
    System.out.println("--> create() GET called");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/create"); // /webapp/computer/create.jsp
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "computer/list.do");//
     
      
      
    } else {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>" 
          + "location.href = './create.jsp';"
          + "</script>");
      
    }
    
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString(); 
    String email = session.getAttribute("email").toString();
    String nickname = session.getAttribute("nickname").toString();
    
    
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    mav.addObject("nickname", nickname);
    mav.addObject("email", email);
    
    return mav;
  }
  
  /**
   * 등록과 수정을 한 화면에서 처리한다.
   * @param computerVO
   * @param request
   * @param session
   * @param opentype : U(수정모드), R(등록모드)
   * @return
   */
  @RequestMapping(value = "/computer/create.do", method = RequestMethod.POST)
  public ModelAndView create(ComputerVO computerVO, HttpServletRequest request, HttpSession session, String opentype) {
    System.out.println("--> create() POST called.");

    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    mav.setViewName("/computer/message"); // /webapp/computer/message.jsp


    // -------------------------------------------------------------------
    // 파일 전송 관련
    // -------------------------------------------------------------------
    
    String thumb = "";
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
     
    String upDir = Tool.getRealPath(request, "/computer/storage");
    MultipartFile file1MF = computerVO.getFile1MF();
    MultipartFile file2MF = computerVO.getFile2MF();
    MultipartFile file3MF = computerVO.getFile3MF();
    MultipartFile file4MF = computerVO.getFile4MF();
    MultipartFile file5MF = computerVO.getFile5MF();
    
      /*파일 저장 file1MF */  
      if (file1MF.getSize() > 0) {
        file1 = Upload.saveFileSpring(file1MF, upDir);
        computerVO.setFile1(file1); // 전송된 파일명 저장
        computerVO.setSize1(file1MF.getSize());

        // -------------------------------------------------------------------
        // Thumb 파일 생성
        // -------------------------------------------------------------------
        if (Tool.isImage(file1)) {
          thumb = Tool.preview(upDir, file1, 250, 250);
        } else {
          thumb = "";
        }
        // -------------------------------------------------------------------
      }
      
      computerVO.setThumb(thumb); // Thumb 이미지
      computerVO.setFile1(file1); // 원본 이미지
      
      /*파일 저장 file2MF */  
      if (file2MF.getSize() > 0) {
        file2 = Upload.saveFileSpring(file2MF, upDir);
        computerVO.setFile2(file2); // 전송된 파일명 저장
        computerVO.setSize2(file2MF.getSize());
      }
 
      computerVO.setFile2(file2); // 원본 이미지
      
      /*파일 저장 file3MF */  
      if (file3MF.getSize() > 0) {
        file3 = Upload.saveFileSpring(file3MF, upDir);
        computerVO.setFile3(file3); // 전송된 파일명 저장
        computerVO.setSize3(file3MF.getSize());
      }
 
      computerVO.setFile3(file3); // 원본 이미지

      /*파일 저장 file4MF */  
      if (file4MF.getSize() > 0) {
        file4 = Upload.saveFileSpring(file4MF, upDir);
        computerVO.setFile4(file4); // 전송된 파일명 저장
        computerVO.setSize4(file4MF.getSize());
      }
 
      computerVO.setFile4(file4); // 원본 이미지

      /*파일 저장 file5MF */  
      if (file5MF.getSize() > 0) {
        file5 = Upload.saveFileSpring(file5MF, upDir);
        computerVO.setFile5(file5); // 전송된 파일명 저장
        computerVO.setSize5(file5MF.getSize());
      }
 
      computerVO.setFile5(file5); // 원본 이미지


    if (computerDAO.create(computerVO) == 1) {
      msgs.add("등록 처리 완료했습니다.");
      msgs.add("감사합니다.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">로그인</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    } else {
      msgs.add("등록에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    }
    
    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /**
   * 1건 조회
   * 
   * @param ctno
   * @return
   */
  @RequestMapping(value = "/computer/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno) {
    ModelAndView mav = new ModelAndView();
    computerDAO.setCnt(ctno); // 조회수 증가

    mav.setViewName("/computer/read");
    mav.addObject("computerVO", computerDAO.read(ctno));

    return mav;
  }

  /**
   * 리스트 목록을 검색+페이징+답변을 적용하여 출력합니다.
   * 
   * @param searchDTO
   *          검색 정보
   * @return 추출된 게시판 목록
   */
  @RequestMapping(value = "/computer/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/list");
    int totalRecord = 0;

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());

    int recordPerPage = 10; // 페이지당 출력할 레코드 갯수
    // 페이지에서 출력할 시작 레코드 번호 계산, nowPage는 1부터 시작
    int beginOfPage = (searchDTO.getNowPage() - 1) * recordPerPage;
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // 시작 rownum, 1
    int endNum = beginOfPage + recordPerPage; // 종료 rownum, 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);

    List<ComputerVO> list = computerDAO.list(hashMap); // 검색
    Iterator<ComputerVO> iter = list.iterator();
    while (iter.hasNext() == true) { // 다음 요소 검사
      ComputerVO vo = iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 20));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());

    totalRecord = computerDAO.count(hashMap);
    mav.addObject("totalRecord", computerDAO.count(hashMap)); // 검색된 레코드 갯수

    String paging = new Paging().paging5(totalRecord, searchDTO.getNowPage(), recordPerPage, searchDTO.getCol(),
        searchDTO.getWord());
    mav.addObject("paging", paging);
    return mav;
  }

 

  /**
   * 삭제폼
   * 
   * @param ctno
   * @return
   */
  @RequestMapping(value = "/computer/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/delete"); // /webapp/computer/delete.jsp
    mav.addObject("ctno", ctno);

    return mav;
  }

  /**
   * 삭제 처리
   * 
   * @param computerVO
   * @return
   */
  @RequestMapping(value = "/computer/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int ctno, String passwd) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    


    if (computerDAO.delete(ctno) == 1) {
      mav.setViewName("redirect:/computer/list.do");
    } else {
      msgs.add("글 삭제에 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

 
  @RequestMapping(value="/computer/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int ctno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer/update"); 
    mav.addObject("computerVO", computerDAO.read(ctno)); 
    return mav;
 
  }
  
  @RequestMapping(value = "/computer/update.do", method = RequestMethod.POST)
  public ModelAndView update(ComputerVO computerVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String thumb = "";
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
     
    String upDir = Tool.getRealPath(request, "/computer/storage");
    MultipartFile file1MF = computerVO.getFile1MF();
    MultipartFile file2MF = computerVO.getFile2MF();
    MultipartFile file3MF = computerVO.getFile3MF();
    MultipartFile file4MF = computerVO.getFile4MF();
    MultipartFile file5MF = computerVO.getFile5MF();
    
    
    ComputerVO oldVO = computerDAO.read(computerVO.getCtno());
    
    
    /*파일 저장 file1MF*/ 
    if (file1MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile1()); // 기존 등록된 파일 삭제
      file1 = Upload.saveFileSpring(file1MF, upDir); // 새로운 파일 저장
      computerVO.setFile1(file1); // 새로운 파일명 저장
      computerVO.setSize1(file1MF.getSize()); // 새로운 크기 저장

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file1)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
        thumb = Tool.preview(upDir, file1, 250, 250); // thumb 이미지 생성
      } else {
        thumb = "";
      }
      // -------------------------------------------------------------------

    } else {
      thumb = oldVO.getThumb(); // 파일 업로드를하지 않는 경우
      file1 = oldVO.getFile1();
    }
    computerVO.setThumb(thumb);
    computerVO.setFile1(file1);
    
    /*파일 저장 file2MF */  
    if (file2MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile2());  // 기존 등록된 파일 삭제
      file2 = Upload.saveFileSpring(file2MF, upDir); // 새로운 파일 저장
      computerVO.setFile2(file2);  // 새로운 파일명 저장
      computerVO.setSize2(file2MF.getSize()); // 새로운 크기 저장
    
    } else {
      file2 = oldVO.getFile2();
    }
   
    computerVO.setFile2(file2);
    
    /*파일 저장 file3MF */  
    if (file3MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile3());  // 기존 등록된 파일 삭제
      file3 = Upload.saveFileSpring(file3MF, upDir); // 새로운 파일 저장
      computerVO.setFile3(file3);  // 새로운 파일명 저장
      computerVO.setSize3(file3MF.getSize()); // 새로운 크기 저장
    
    } else {
      file3 = oldVO.getFile3();
    }
   
    computerVO.setFile3(file3);
    
    /*파일 저장 file4MF */  
    if (file4MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile4());  // 기존 등록된 파일 삭제
      file4 = Upload.saveFileSpring(file4MF, upDir); // 새로운 파일 저장
      computerVO.setFile4(file4);  // 새로운 파일명 저장
      computerVO.setSize4(file4MF.getSize()); // 새로운 크기 저장
    
    } else {
      file4 = oldVO.getFile4();
    }
   
    computerVO.setFile4(file4);
    
    
    /*파일 저장 file5MF */  
    if (file5MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile5());  // 기존 등록된 파일 삭제
      file5 = Upload.saveFileSpring(file5MF, upDir); // 새로운 파일 저장
      computerVO.setFile5(file5);  // 새로운 파일명 저장
      computerVO.setSize5(file5MF.getSize()); // 새로운 크기 저장
    
    } else {
      file5 = oldVO.getFile5();
    }
   
    computerVO.setFile5(file5);
 
    
    if (computerDAO.update(computerVO) == 1) {
      // 수정후 조회로 자동 이동
      mav.setViewName("redirect:/computer/read.do?ctno=" + computerVO.getCtno());
    } else {
      msgs.add("게시판 수정에 실패 하셨습니다.");
      links
          .add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      links
          .add("<button type='button' onclick=\"location.href='./list.do'>목록</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
    
    return mav;
  }
  
  /**
   * 최근 목록을 출력합니다.
   * 
   * @return
   */
  @RequestMapping(value = "/computer/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/computer/list2"); // /webapp/member/list.jsp
     
     
     List<ComputerVO> list = computerDAO.newlist();
     Iterator<ComputerVO> iter = list.iterator(); // 객체를 순차적으로 접근하는 기능
     while(iter.hasNext() == true){  // 다음 요소 검사
       ComputerVO vo = iter.next();  // 요소 추출
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // 문자열 10자 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // 년 월 일
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     
     return mav;
  }

}
