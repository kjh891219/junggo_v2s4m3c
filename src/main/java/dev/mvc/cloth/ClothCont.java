package dev.mvc.cloth;

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
public class ClothCont {
  @Autowired
  @Qualifier("dev.mvc.cloth.ClothDAO")
  private ClothDAOInter clothDAO;
  
  public ClothCont(){
    System.out.println("--> ClothCont created.");
  }
  
  @RequestMapping(value = "/cloth/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
    System.out.println("--> create() GET called."); 
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cloth/create"); // /webapp/cloth/create.jsp

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url",  "cloth/list.do");//
      
    }else{
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>"
          + "location.href = './create.jsp'; "
          + "</script>");
      session.setAttribute("url", "cloth/list.do"); 
    }
    
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString();
    MemberVO memberVO = clothDAO.test(userid);
    
    mav.addObject("memberVO", memberVO);
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    System.out.println(memberVO);
    
    
    return mav;
  }
  
  @RequestMapping(value = "/cloth/create.do", 
      method = RequestMethod.POST)
  public ModelAndView create(ClothVO clothVO,
                                        HttpServletRequest request, 
                                        HttpSession session) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cloth/message"); // /webapp/cloth/message.jsp

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
    
    String upDir = Tool.getRealPath(request, "/cloth/storage");
    MultipartFile file2MF = clothVO.getFile2MF();
    MultipartFile file4MF = clothVO.getFile4MF();
    MultipartFile file6MF = clothVO.getFile6MF();
    MultipartFile file8MF = clothVO.getFile8MF();
    MultipartFile file10MF = clothVO.getFile10MF();
    
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      clothVO.setFile2(file2); // 전송된 파일명 저장
      clothVO.setSize2(file2MF.getSize());
 
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
    clothVO.setFile1(file1); // Thumb 이미지
    clothVO.setFile2(file2); // 원본 이미지
    // -------------------------------------------------------------------
    
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir);
      clothVO.setFile4(file4); // 전송된 파일명 저장
      clothVO.setSize4(file4MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) {
        file3 = Tool.preview(upDir, file4, 120, 80);
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
    }
    clothVO.setFile3(file3); // Thumb 이미지
    clothVO.setFile4(file4); // 원본 이미지
    // -------------------------------------------------------------------
    
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir);
      clothVO.setFile6(file6); // 전송된 파일명 저장
      clothVO.setSize6(file6MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) {
        file5 = Tool.preview(upDir, file6, 120, 80);
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------
    }
    clothVO.setFile5(file5); // Thumb 이미지
    clothVO.setFile6(file6); // 원본 이미지
    // -------------------------------------------------------------------
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir);
      clothVO.setFile8(file8); // 전송된 파일명 저장
      clothVO.setSize8(file8MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) {
        file7 = Tool.preview(upDir, file8, 120, 80);
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------
    }
    clothVO.setFile7(file7); // Thumb 이미지
    clothVO.setFile8(file8); // 원본 이미지
    // -------------------------------------------------------------------
 // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir);
      clothVO.setFile10(file10); // 전송된 파일명 저장
      clothVO.setSize10(file10MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) {
        file9 = Tool.preview(upDir, file10, 120, 80);
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
    }
    clothVO.setFile9(file9); // Thumb 이미지
    clothVO.setFile10(file10); // 원본 이미지
    // -------------------------------------------------------------------
 
    // clothVO.setclothno(2); // 회원 연동시 변경
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
    
    if (clothDAO.create(clothVO) == 1) {
      msgs.add("등록되었습니다.");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
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
    @RequestMapping(value = "/cloth/list.do", 
        method = RequestMethod.GET)
  public ModelAndView list3(SearchDTO searchDTO,
                               HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/cloth/list");
  
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
  
  List<ClothVO> list = clothDAO.list3(hashMap); // 검색
  Iterator<ClothVO> iter = list.iterator();
  while (iter.hasNext() == true) { // 다음 요소 검사
    ClothVO vo = iter.next(); // 요소 추출
  vo.setTitle(Tool.textLength(vo.getTitle(), 10));
  vo.setWdate(vo.getWdate().substring(0, 10));
  // vo.setFile1(Tool.textLength(10, vo.getFile1()));
  // vo.setFile2(Tool.textLength(10, vo.getFile2()));
  vo.setSize2Label(Tool.unit(vo.getSize2()));
  }
  mav.addObject("list", list);
  mav.addObject("root", request.getContextPath());
  
  totalRecord = clothDAO.count(hashMap);
  mav.addObject("totalRecord", clothDAO.count(hashMap)); // 검색된 레코드 갯수
  
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
  @RequestMapping(value = "/cloth/list.do", method = RequestMethod.GET)
  public ModelAndView list2( 
                                        SearchDTO searchDTO,
                                        HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cloth/list");
 
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    List<clothVO> list = clothDAO.list2(hashMap); // 검색
    Iterator<clothVO> iter = list.iterator();
    while (iter.hasNext() == true) { // 다음 요소 검사
      clothVO vo = iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2())); // MB 단위 변환
    }
    mav.addObject("list", list);
 
    mav.addObject("root", request.getContextPath());
    mav.addObject("totalRecord", clothDAO.count(hashMap)); // 검색된 레코드 갯수
 
    return mav;
  }  */
  
  
    /**
     * 글을 조회합니다
     * @param clothno
     * @return
     */
    @RequestMapping(value = "/cloth/read.do", method = RequestMethod.GET)
    public ModelAndView read(int clothno,
          SearchDTO searchDTO, 
        HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/cloth/read");
      mav.addObject("clothVO", clothDAO.read(clothno));
      
      
      clothDAO.increaseCnt(clothno);
      ClothVO clothVO = clothDAO.read(clothno);
     
      clothVO.setSize2Label(Tool.unit(clothVO.getSize2()));
      clothVO.setSize4Label(Tool.unit(clothVO.getSize4()));
      clothVO.setSize6Label(Tool.unit(clothVO.getSize6()));
      clothVO.setSize8Label(Tool.unit(clothVO.getSize8()));
      clothVO.setSize10Label(Tool.unit(clothVO.getSize10()));
      
      mav.addObject("clothVO", clothVO);
      mav.addObject("searchDTO", searchDTO);
      
      
      
      //게시판에 대한 정보 파악
  //    BlogcategoryVO blogcategoryVO =  blogcategoryDAO.read(blogVO.getBlogcategoryno());
  //    mav.addObject("blogcategoryVO", blogcategoryVO);
      mav.addObject("root", request.getContextPath());
      return mav;
    }
  
  /**
   * 수정폼
   * @param clothno
   * @return
   */
  @RequestMapping(value="/cloth/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int clothno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cloth/update"); 
    mav.addObject("clothVO", clothDAO.read(clothno)); 
 
    return mav;
 
  }
   
  /**
   * 글과 파일을 수정 처리
   * 
   * @param clothVO
   * @return
   */
  @RequestMapping(value = "/cloth/update.do", method = RequestMethod.POST)
  public ModelAndView update(ClothVO clothVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    // -------------------------------------------------------------------
    // 파일 전송1
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
 
    String upDir = Tool.getRealPath(request, "/cloth/storage");
    MultipartFile file2MF = clothVO.getFile2MF();
    ClothVO oldVO = clothDAO.read(clothVO.getClothno());
 
    if (file2MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile2()); //기존 파일 삭제
      file2 = Upload.saveFileSpring(file2MF, upDir); //새로운 파일 저장
      clothVO.setFile2(file2); // 새로운 파일명 저장
      clothVO.setSize2(file2MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
        file1 = Tool.preview(upDir, file2, 120, 80); //thumb 이미지 생성
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file1 = oldVO.getFile1(); //기존 파일들을 사용
      file2 = oldVO.getFile2();
    }
    clothVO.setFile1(file1);
    clothVO.setFile2(file2);
    // -------------------------------------------------------------------
 
    // -------------------------------------------------------------------
    // 파일 전송2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
 
    String upDir2 = Tool.getRealPath(request, "/cloth/storage");
    MultipartFile file4MF = clothVO.getFile4MF();
    ClothVO oldVO2 = clothDAO.read(clothVO.getClothno());
 
    if (file4MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir2, oldVO2.getFile4()); //기존 파일 삭제
      file4 = Upload.saveFileSpring(file4MF, upDir2); //새로운 파일 저장
      clothVO.setFile4(file4); // 새로운 파일명 저장
      clothVO.setSize4(file4MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) { // 이미지인지 검사
        Tool.deleteFile(upDir2, oldVO2.getFile3()); // 파일 삭제
        file3 = Tool.preview(upDir2, file4, 120, 80); //thumb 이미지 생성
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file3 = oldVO2.getFile3(); //기존 파일들을 사용
      file4 = oldVO2.getFile4();
    }
    clothVO.setFile3(file3);
    clothVO.setFile4(file4);
    // -------------------------------------------------------------------
    
    
    // -------------------------------------------------------------------
    // 파일 전송3
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
 
    String upDir3 = Tool.getRealPath(request, "/cloth/storage");
    MultipartFile file6MF = clothVO.getFile6MF();
    ClothVO oldVO3 = clothDAO.read(clothVO.getClothno());
 
    if (file6MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir3, oldVO3.getFile6()); //기존 파일 삭제
      file6 = Upload.saveFileSpring(file6MF, upDir3); //새로운 파일 저장
      clothVO.setFile6(file6); // 새로운 파일명 저장
      clothVO.setSize6(file6MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) { // 이미지인지 검사
        Tool.deleteFile(upDir3, oldVO3.getFile5()); // 파일 삭제
        file5 = Tool.preview(upDir3, file6, 120, 80); //thumb 이미지 생성
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file5 = oldVO3.getFile5(); //기존 파일들을 사용
      file6 = oldVO3.getFile6();
    }
    clothVO.setFile5(file5);
    clothVO.setFile6(file6);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 파일 전송4
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
 
    String upDir4 = Tool.getRealPath(request, "/cloth/storage");
    MultipartFile file8MF = clothVO.getFile8MF();
    ClothVO oldVO4 = clothDAO.read(clothVO.getClothno());
 
    if (file8MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir4, oldVO4.getFile8()); //기존 파일 삭제
      file8 = Upload.saveFileSpring(file8MF, upDir4); //새로운 파일 저장
      clothVO.setFile8(file8); // 새로운 파일명 저장
      clothVO.setSize8(file8MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) { // 이미지인지 검사
        Tool.deleteFile(upDir4, oldVO4.getFile7()); // 파일 삭제
        file7 = Tool.preview(upDir4, file8, 120, 80); //thumb 이미지 생성
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file7 = oldVO4.getFile7(); //기존 파일들을 사용
      file8 = oldVO4.getFile8();
    }
    clothVO.setFile7(file7);
    clothVO.setFile8(file8);
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 파일 전송5
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
 
    String upDir5 = Tool.getRealPath(request, "/cloth/storage");
    MultipartFile file10MF = clothVO.getFile10MF();
    ClothVO oldVO5 = clothDAO.read(clothVO.getClothno());
 
    if (file10MF.getSize() > 0) { //새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir5, oldVO5.getFile10()); //기존 파일 삭제
      file10 = Upload.saveFileSpring(file10MF, upDir5); //새로운 파일 저장
      clothVO.setFile10(file10); // 새로운 파일명 저장
      clothVO.setSize10(file10MF.getSize());
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) { // 이미지인지 검사
        Tool.deleteFile(upDir5, oldVO5.getFile9()); // 파일 삭제
        file9 = Tool.preview(upDir5, file10, 120, 80); //thumb 이미지 생성
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
 
    } else { //파일업로드를 사용하지 않는 경우
      file9 = oldVO5.getFile9(); //기존 파일들을 사용
      file10 = oldVO5.getFile10();
    }
    clothVO.setFile9(file9);
    clothVO.setFile10(file10);
    // -------------------------------------------------------------------
    
    
    if (clothDAO.update(clothVO) == 1) {
      // 수정후 조회로 자동 이동
      mav.setViewName("redirect:/cloth/read.do?clothno=" + clothVO.getClothno() ); // 확장자 명시
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
   * @param clothno
   * @return
   */
  @RequestMapping(value = "/cloth/delete.do", 
                            method = RequestMethod.GET)
  public ModelAndView delete(int clothno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cloth/delete"); // /webapp/cloth/delete.jsp
    mav.addObject("clothno", clothno);
   
    return mav;
  }
  
  /**
   * 삭제 처리
   * @param clothVO
   * @return
   */
  @RequestMapping(value = "/cloth/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(ClothVO clothVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cloth/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (clothDAO.delete(clothVO.getClothno()) == 1) {
      msgs.add("글 삭제에 성공했습니다.");
      links.add("<button type='button' onclick=\"location.href='./list.do?clothno="+clothVO.getClothno()+"'\">목록</button>");
      
    } else {
      msgs.add("글 삭제에 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?clothno="+clothVO.getClothno()+"'\">목록</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  /**
   * 최근 목록을 출력합니다.
   * 
   * @return
   */
  @RequestMapping(value = "/cloth/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/cloth/list2"); // /webapp/member/list.jsp
     
     
     List<ClothVO> list = clothDAO.newlist();
     Iterator<ClothVO> iter = list.iterator(); // 객체를 순차적으로 접근하는 기능
     while(iter.hasNext() == true){  // 다음 요소 검사
       ClothVO vo = iter.next();  // 요소 추출
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // 문자열 10자 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // 년 월 일
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     
     return mav;
  }

  
  
}
