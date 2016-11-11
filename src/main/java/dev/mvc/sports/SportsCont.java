package dev.mvc.sports;

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
public class SportsCont {
  @Autowired
  @Qualifier("dev.mvc.sports.SportsDAO")
  private SportsDAOInter sportsDAO;
  
  public SportsCont(){
    System.out.println("--> SportsCont created.");
  }
  
  @RequestMapping(value = "/sports/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
    System.out.println("--> create() GET called."); 
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sports/create"); // /webapp/sports/create.jsp

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url",  "sports/list.do");//
      
    }else{
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>"
          + "location.href = './create.jsp'; "
          + "</script>");
      session.setAttribute("url", "sports/list.do"); 
    }
    
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString();
    MemberVO memberVO = sportsDAO.test(userid);
    
    mav.addObject("memberVO", memberVO);
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    System.out.println(memberVO);
    
    
    return mav;
  }
  
  @RequestMapping(value = "/sports/create.do", 
      method = RequestMethod.POST)
  public ModelAndView create(SportsVO sportsVO,
                                        HttpServletRequest request, 
                                        HttpSession session) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sports/message"); // /webapp/sports/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
   
    // -------------------------------------------------------------------
    // 파일 전송
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
    
    String upDir = Tool.getRealPath(request, "/sports/storage");
    MultipartFile file2MF = sportsVO.getFile2MF();
    MultipartFile file4MF = sportsVO.getFile4MF();
    MultipartFile file6MF = sportsVO.getFile6MF();
    MultipartFile file8MF = sportsVO.getFile8MF();
    MultipartFile file10MF = sportsVO.getFile10MF();
    
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      sportsVO.setFile2(file2); // 전송된 파일명 저장
      sportsVO.setSize2(file2MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) {
        file1 = Tool.preview(upDir, file2, 250, 250);
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------
    }
    sportsVO.setFile1(file1); // Thumb 이미지
    sportsVO.setFile2(file2); // 원본 이미지
    // -------------------------------------------------------------------
    
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir);
      sportsVO.setFile4(file4); // 전송된 파일명 저장
      sportsVO.setSize4(file4MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) {
        file3 = Tool.preview(upDir, file4, 250, 250);
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
    }
    sportsVO.setFile3(file3); // Thumb 이미지
    sportsVO.setFile4(file4); // 원본 이미지
    // -------------------------------------------------------------------
    
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir);
      sportsVO.setFile6(file6); // 전송된 파일명 저장
      sportsVO.setSize6(file6MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) {
        file5 = Tool.preview(upDir, file6, 250, 250);
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------
    }
    sportsVO.setFile5(file5); // Thumb 이미지
    sportsVO.setFile6(file6); // 원본 이미지
    // -------------------------------------------------------------------
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir);
      sportsVO.setFile8(file8); // 전송된 파일명 저장
      sportsVO.setSize8(file8MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) {
        file7 = Tool.preview(upDir, file8, 250, 250);
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------
    }
    sportsVO.setFile7(file7); // Thumb 이미지
    sportsVO.setFile8(file8); // 원본 이미지
    // -------------------------------------------------------------------
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir);
      sportsVO.setFile10(file10); // 전송된 파일명 저장
      sportsVO.setSize10(file10MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) {
        file9 = Tool.preview(upDir, file10, 250, 250);
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
    }
    sportsVO.setFile9(file9); // Thumb 이미지
    sportsVO.setFile10(file10); // 원본 이미지
    // -------------------------------------------------------------------
 
    // sportsVO.setsno(2); // 회원 연동시 변경
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
    
    if (sportsDAO.create(sportsVO) == 1) {
      msgs.add("등록되었습니다.");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
    } else {
      msgs.add("등록에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
    }
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
 /* *//**
   * 전체 목록을 출력합니다.
   * 
   * @return
   */
    @RequestMapping(value = "/sports/list.do", 
        method = RequestMethod.GET)
  public ModelAndView list3(SearchDTO searchDTO,
                               HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/sports/list");
  
  int totalRecord = 0;
  
  // HashMap hashMap = new HashMap();
  HashMap<String, Object> hashMap = new HashMap<String, Object>();
  hashMap.put("col", searchDTO.getCol());
  hashMap.put("word", searchDTO.getWord());
  
  int recordPerPage = 5; // 페이지당 출력할 레코드 갯수
  // 페이지에서 출력할 시작 레코드 번호 계산, nowPage는 1부터 시작
  int beginOfPage = (searchDTO.getNowPage() - 1) * 5; 
  // 1 page: 0
  // 2 page: 10
  // 3 page: 20
  int startNum = beginOfPage + 1; // 시작 rownum, 1
  int endNum = beginOfPage + recordPerPage; // 종료 rownum, 10
  hashMap.put("startNum", startNum);
  hashMap.put("endNum", endNum);
  
  List<SportsVO> list = sportsDAO.list3(hashMap); // 검색
  Iterator<SportsVO> iter = list.iterator();
  while (iter.hasNext() == true) { // 다음 요소 검사
    SportsVO vo = iter.next(); // 요소 추출
  vo.setTitle(Tool.textLength(vo.getTitle(), 10));
  vo.setWdate(vo.getWdate().substring(0, 10));
  // vo.setFile1(Tool.textLength(10, vo.getFile1()));
  // vo.setFile2(Tool.textLength(10, vo.getFile2()));
  vo.setSize2Label(Tool.unit(vo.getSize2()));
  }
  mav.addObject("list", list);
  mav.addObject("root", request.getContextPath());
  
  totalRecord = sportsDAO.count(hashMap);
  mav.addObject("totalRecord", sportsDAO.count(hashMap)); // 검색된 레코드 갯수
  
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
   *          전체 목록에서 가져올 게시판 번호
   * @param searchDTO 검색 정보         
   * @return 추출된 게시판 목록
   *//*
  @RequestMapping(value = "/sports/list.do", method = RequestMethod.GET)
  public ModelAndView list2( 
                                        SearchDTO searchDTO,
                                        HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sports/list");
 
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    List<sportsVO> list = sportsDAO.list2(hashMap); // 검색
    Iterator<sportsVO> iter = list.iterator();
    while (iter.hasNext() == true) { // 다음 요소 검사
      sportsVO vo = iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2())); // MB 단위 변환
    }
    mav.addObject("list", list);
 
    mav.addObject("root", request.getContextPath());
    mav.addObject("totalRecord", sportsDAO.count(hashMap)); // 검색된 레코드 갯수
 
    return mav;
  }  */
  
  
    /**
     * 글을 조회합니다
     * @param sno
     * @return
     */
    @RequestMapping(value = "/sports/read.do", method = RequestMethod.GET)
    public ModelAndView read(int sno,
          SearchDTO searchDTO, 
        HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/sports/read");
      mav.addObject("sportsVO", sportsDAO.read(sno));
      
      
      sportsDAO.increaseCnt(sno);
      SportsVO sportsVO = sportsDAO.read(sno);
     
      sportsVO.setSize2Label(Tool.unit(sportsVO.getSize2()));
      sportsVO.setSize4Label(Tool.unit(sportsVO.getSize4()));
      sportsVO.setSize6Label(Tool.unit(sportsVO.getSize6()));
      sportsVO.setSize8Label(Tool.unit(sportsVO.getSize8()));
      sportsVO.setSize10Label(Tool.unit(sportsVO.getSize10()));
      
      mav.addObject("sportsVO", sportsVO);
      mav.addObject("searchDTO", searchDTO);
      
      String content = sportsVO.getContent();
      content = Tool.convertChar(content);
      sportsVO.setContent(content);
      
      
      //게시판에 대한 정보 파악
  //    BlogcategoryVO blogcategoryVO =  blogcategoryDAO.read(blogVO.getBlogcategoryno());
  //    mav.addObject("blogcategoryVO", blogcategoryVO);
      mav.addObject("root", request.getContextPath());
      return mav;
    }
  
  /**
   * 수정폼
   * @param sno
   * @return
   */
  @RequestMapping(value="/sports/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int sno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sports/update"); 
    mav.addObject("sportsVO", sportsDAO.read(sno)); 
 
    return mav;
 
  }
   
  /**
   * 글과 파일을 수정 처리
   * 
   * @param sportsVO
   * @return
   */
  @RequestMapping(value = "/sports/update.do", method = RequestMethod.POST)
  public ModelAndView update(SportsVO sportsVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    // -------------------------------------------------------------------
    // 파일 전송1
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
 
    String upDir = Tool.getRealPath(request, "/sports/storage");
    MultipartFile file2MF = sportsVO.getFile2MF();
    SportsVO oldVO = sportsDAO.read(sportsVO.getSno());
 
    if (file2MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile2()); //기존 파일 삭제
      file2 = Upload.saveFileSpring(file2MF, upDir); //새로운 파일 저장
      sportsVO.setFile2(file2); // 새로운 파일명 저장
      sportsVO.setSize2(file2MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
        file1 = Tool.preview(upDir, file2, 250, 250); //thumb 이미지 생성
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file1 = oldVO.getFile1(); //기존 파일들을 사용
      file2 = oldVO.getFile2();
    }
    sportsVO.setFile1(file1);
    sportsVO.setFile2(file2);
    // -------------------------------------------------------------------
 
    // -------------------------------------------------------------------
    // 파일 전송2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
 
    String upDir2 = Tool.getRealPath(request, "/sports/storage");
    MultipartFile file4MF = sportsVO.getFile4MF();
    SportsVO oldVO2 = sportsDAO.read(sportsVO.getSno());
 
    if (file4MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir2, oldVO2.getFile4()); //기존 파일 삭제
      file4 = Upload.saveFileSpring(file4MF, upDir2); //새로운 파일 저장
      sportsVO.setFile4(file4); // 새로운 파일명 저장
      sportsVO.setSize4(file4MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) { // 이미지인지 검사
        Tool.deleteFile(upDir2, oldVO2.getFile3()); // 파일 삭제
        file3 = Tool.preview(upDir2, file4, 250, 250); //thumb 이미지 생성
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file3 = oldVO2.getFile3(); //기존 파일들을 사용
      file4 = oldVO2.getFile4();
    }
    sportsVO.setFile3(file3);
    sportsVO.setFile4(file4);
    // -------------------------------------------------------------------
    
    
    // -------------------------------------------------------------------
    // 파일 전송3
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
 
    String upDir3 = Tool.getRealPath(request, "/sports/storage");
    MultipartFile file6MF = sportsVO.getFile6MF();
    SportsVO oldVO3 = sportsDAO.read(sportsVO.getSno());
 
    if (file6MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir3, oldVO3.getFile6()); //기존 파일 삭제
      file6 = Upload.saveFileSpring(file6MF, upDir3); //새로운 파일 저장
      sportsVO.setFile6(file6); // 새로운 파일명 저장
      sportsVO.setSize6(file6MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) { // 이미지인지 검사
        Tool.deleteFile(upDir3, oldVO3.getFile5()); // 파일 삭제
        file5 = Tool.preview(upDir3, file6, 250, 250); //thumb 이미지 생성
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file5 = oldVO3.getFile5(); //기존 파일들을 사용
      file6 = oldVO3.getFile6();
    }
    sportsVO.setFile5(file5);
    sportsVO.setFile6(file6);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 파일 전송4
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
 
    String upDir4 = Tool.getRealPath(request, "/sports/storage");
    MultipartFile file8MF = sportsVO.getFile8MF();
    SportsVO oldVO4 = sportsDAO.read(sportsVO.getSno());
 
    if (file8MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir4, oldVO4.getFile8()); //기존 파일 삭제
      file8 = Upload.saveFileSpring(file8MF, upDir4); //새로운 파일 저장
      sportsVO.setFile8(file8); // 새로운 파일명 저장
      sportsVO.setSize8(file8MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) { // 이미지인지 검사
        Tool.deleteFile(upDir4, oldVO4.getFile7()); // 파일 삭제
        file7 = Tool.preview(upDir4, file8, 250, 250); //thumb 이미지 생성
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file7 = oldVO4.getFile7(); //기존 파일들을 사용
      file8 = oldVO4.getFile8();
    }
    sportsVO.setFile7(file7);
    sportsVO.setFile8(file8);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 파일 전송5
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
 
    String upDir5 = Tool.getRealPath(request, "/sports/storage");
    MultipartFile file10MF = sportsVO.getFile10MF();
    SportsVO oldVO5 = sportsDAO.read(sportsVO.getSno());
 
    if (file10MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir5, oldVO5.getFile10()); //기존 파일 삭제
      file10 = Upload.saveFileSpring(file10MF, upDir5); //새로운 파일 저장
      sportsVO.setFile10(file10); // 새로운 파일명 저장
      sportsVO.setSize10(file10MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) { // 이미지인지 검사
        Tool.deleteFile(upDir5, oldVO5.getFile9()); // 파일 삭제
        file9 = Tool.preview(upDir5, file10, 250, 250); //thumb 이미지 생성
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file9 = oldVO5.getFile9(); //기존 파일들을 사용
      file10 = oldVO5.getFile10();
    }
    sportsVO.setFile9(file9);
    sportsVO.setFile10(file10);
    // -------------------------------------------------------------------
    
    
    if (sportsDAO.update(sportsVO) == 1) {
      // 수정후 조회로 자동 이동
      mav.setViewName("redirect:/sports/read.do?sno=" + sportsVO.getSno() ); // 확장자 명시
    } else {
      msgs.add("게시판 수정에 실패 하셨습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
  /**
   * 삭제폼
   * @param sno
   * @return
   */
  @RequestMapping(value = "/sports/delete.do", 
                            method = RequestMethod.GET)
  public ModelAndView delete(int sno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sports/delete"); // /webapp/sports/delete.jsp
    mav.addObject("sno", sno);
   
    return mav;
  }
  
  /**
   * 삭제 처리
   * @param sportsVO
   * @return
   */
  @RequestMapping(value = "/sports/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(SportsVO sportsVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sports/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (sportsDAO.delete(sportsVO.getSno()) == 1) {
      msgs.add("글 삭제에 성공했습니다.");
      links.add("<button type='button' onclick=\"location.href='./list.do?sno="+sportsVO.getSno()+"'\">목록</button>");
      
    } else {
      msgs.add("글 삭제에 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./index.do'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?sno="+sportsVO.getSno()+"'\">목록</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
}
