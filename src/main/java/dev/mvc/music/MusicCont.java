package dev.mvc.music;
 
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

import dev.mvc.carproduct.CarproductVO;
import dev.mvc.tmember.MemberVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

 
@Controller
public class MusicCont {
  @Autowired
  @Qualifier("dev.mvc.music.MusicDAO")
  private MusicDAOInter musicDAO;
  
  public MusicCont(){
    System.out.println("--> MusicCont created.");
  }
  
  /**
   * 레코드 등록
   * @param
   * @return
   */
 @RequestMapping(value = "/music/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException { 
  // System.out.println("--> create() GET called.");
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/music/create"); // /webapp/music/create.jsp
  
  
  response.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  if (session.getAttribute("userid") == null ){
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
     + "location.href = '../member/login.do';"
     + "</script>"); 
    session.setAttribute("url", "music/list.do");//
   
    
    
  } else {
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>" 
        + "location.href = './create.jsp';"
        + "</script>");
    
  }
  
  
  
  String userid = session.getAttribute("userid").toString();
  MemberVO memberVO = musicDAO.test(userid);
  
  mav.addObject("memberVO", memberVO);
  mav.addObject("userid", userid);
  System.out.println(memberVO);

  return mav;
}
 
 
 /**
  * 등록 처리
  * @param musicVO
  * @param request
  * @param session
  * @return
  */
 @RequestMapping(value = "/music/create.do", 
                            method = RequestMethod.POST)
 public ModelAndView create(MusicVO musicVO, 
                                          HttpServletRequest request, 
                                          HttpSession session) {
   // System.out.println("--> create() POST called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/music/message");
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // 파일 전송
   // -------------------------------------------------------------------
   String thumb = "";
   String file1 = "";
   long size1 = 0;
   String upDir = Tool.getRealPath(request, "/music/storage");
   MultipartFile file1MF = musicVO.getFile1MF();
   System.out.println("file1MF:"+file1MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size1 = file1MF.getSize();
   if (size1 > 0) {
     file1 = Upload.saveFileSpring(file1MF, upDir);
     // -------------------------------------------------------------------
     // Thumb 파일 생성
     // -------------------------------------------------------------------
     if (Tool.isImage(file1)) {
       thumb = Tool.preview(upDir, file1, 140, 180);
     } else {
       thumb = "";
     }
     
     // -------------------------------------------------------------------
   }
   musicVO.setThumb(thumb); // Thumb 이미지
   musicVO.setFile1(file1); // 원본 이미지
   musicVO.setSize1(size1); // 원본 이미지
   // -------------------------------------------------------------------
   
   
   // -------------------------------------------------------------------
   // 파일 전송2
   // -------------------------------------------------------------------
   String file2 = "";
   long size2 = 0;
   MultipartFile file2MF = musicVO.getFile2MF();
   System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size2 = file2MF.getSize();
   if (size2 > 0) {
     file2 = Upload.saveFileSpring(file2MF, upDir);
   }
   musicVO.setFile2(file2); // 원본 이미지
   musicVO.setSize2(size2); // 원본 이미지
   // -------------------------------------------------------------------
   
   // -------------------------------------------------------------------
   // 파일 전송 3
   // -------------------------------------------------------------------
   String file3 = "";
   long size3 = 0;
   MultipartFile file3MF = musicVO.getFile3MF();
   // System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size3 = file3MF.getSize();
   if (size3 > 0) {
     file3 = Upload.saveFileSpring(file3MF, upDir);
   }
   musicVO.setFile3(file3); // 원본 이미지
   musicVO.setSize3(size3); // 원본 이미지
   // -------------------------------------------------------------------

   // -------------------------------------------------------------------
   // 파일 전송 4
   // -------------------------------------------------------------------
   String file4 = "";
   long size4 = 0;
   MultipartFile file4MF = musicVO.getFile4MF();

   size4 = file4MF.getSize();
   if (size4 > 0) {
     file4 = Upload.saveFileSpring(file4MF, upDir);
   }
   musicVO.setFile4(file4); // 원본 이미지
   musicVO.setSize4(size4); // 원본 이미지
   // -------------------------------------------------------------------
   
   
   // -------------------------------------------------------------------
   // 파일 전송 5
   // -------------------------------------------------------------------
   String file5 = "";
   long size5 = 0;
   MultipartFile file5MF = musicVO.getFile5MF();

   size5 = file5MF.getSize();
   if (size5 > 0) {
     file5 = Upload.saveFileSpring(file5MF, upDir);
   }
   musicVO.setFile5(file5); // 원본 이미지
   musicVO.setSize5(size5); // 원본 이미지
   // -------------------------------------------------------------------
   
   
   //usedcarVO.setU_no(4); // 회원 연동시 변경
   // Integer itg = (Integer)(session.getAttribute("mno"));
   // blogVO.setMno(itg.intValue());
    
   if (musicDAO.create(musicVO) == 1) {
     msgs.add("글을 등록했습니다.");
     links.add("<button type='button' onclick=\"location.href='./create.do?ctno="
             + musicVO.getCtno() + "'\">계속 등록</button>");
   } else {
     msgs.add("글 등록에 실패했습니다.");
     msgs.add("다시 시도해주세요.");
     links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
   }

   links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
   links.add("<button type='button' onclick=\"location.href='./list.do?ctno="
           + musicVO.getCtno() + "'\">목록</button>");
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);

   return mav;
 }


 
/**
 * 한건의 레코드 조회
 * @param ctno
 * @return
 */
@RequestMapping(value = "/music/read.do", method = RequestMethod.GET)
public ModelAndView read(int ctno, SearchDTO searchDTO) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/music/read");
  
  if(ctno>musicDAO.maxctno()){
    ctno = musicDAO.maxctno();
  }
  
  if(ctno<musicDAO.minctno()){
    ctno = musicDAO.minctno();
  }
  
  musicDAO.increaseCnt(ctno);
  mav.addObject("musicVO", musicDAO.read(ctno));
  mav.addObject("searchDTO", searchDTO);
  return mav;
}

    /**
     * 삭제폼
     * @param ctno
     * @return
     */
    @RequestMapping(value = "/music/delete.do", method = RequestMethod.GET)
    public ModelAndView delete(int ctno) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/music/delete"); // /webapp/member/delete.jsp
      mav.addObject("ctno", ctno);
      
      return mav;
    }

 /**
 * 삭제 처리
 * @param musicVO
 * @return
 */
@RequestMapping(value = "/music/delete.do", 
                           method = RequestMethod.POST)
public ModelAndView delete(MusicVO musicVO) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/music/message");

  ArrayList<String> msgs = new ArrayList<String>();
  ArrayList<String> links = new ArrayList<String>();
  
  if (musicDAO.delete(musicVO.getCtno()) == 1) {
    mav.setViewName("redirect:/music/list.do?ctno=" + musicVO.getCtno());//확장자 명시

  } else {
    msgs.add("글 삭제에 실패했습니다.");
    links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
    links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do?ctno="+musicVO.getCtno()+"'\">목록</button>");
  }
  
  mav.addObject("msgs", msgs);
  mav.addObject("links", links);
  
  return mav;
}
   

/**
 * 수정폼
 * @param ctno
 * @return
 */
@RequestMapping(value="/music/update.do", 
                           method=RequestMethod.GET)
public ModelAndView update(int ctno){  
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/music/update");
  MusicVO musicVO = musicDAO.read(ctno);
  mav.addObject("musicVO", musicVO);
 

  return mav;

}
 
/**
 * 글과 파일을 수정 처리
 * @param musicVO
 * @return
 */
@RequestMapping(value = "/music/update.do", 
                           method = RequestMethod.POST)
public ModelAndView update(MusicVO musicVO, HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();

  ArrayList<String> msgs = new ArrayList<String>();
  ArrayList<String> links = new ArrayList<String>();

  // -------------------------------------------------------------------
  // 파일 전송 관련
  // -------------------------------------------------------------------
  String upDir = Tool.getRealPath(request, "/music/storage");
  MusicVO oldVO = musicDAO.read(musicVO.getCtno());

  // -------------------------------------------------------------------
  // 1번째 파일
  // -------------------------------------------------------------------  
  String thumb = "";
  String file1 = "";
  long size1 = 0;
  // <input type="file" name='file1MF' id='file1MF' size='40' >
  MultipartFile file1MF = musicVO.getFile1MF();
  size1 = file1MF.getSize();
  if (size1 > 0) { // 새로운 파일을 전송하는지 확인
    Tool.deleteFile(upDir, oldVO.getFile1()); // 기존 등록된 파일 삭제
    file1 = Upload.saveFileSpring(file1MF, upDir); // 새로운 파일 저장

    // -------------------------------------------------------------------
    // Thumb 파일 생성
    // -------------------------------------------------------------------
    if (Tool.isImage(file1)) { // 이미지인지 검사
      Tool.deleteFile(upDir, oldVO.getThumb()); // 파일 삭제
      thumb = Tool.preview(upDir, file1, 120, 80); // thumb 이미지 생성
    } else {
      thumb = "";
    }
    // -------------------------------------------------------------------

  } else {
    thumb = oldVO.getThumb();
    file1 = oldVO.getFile1(); // 파일 업로드를하지 않는 경우
    size1 = oldVO.getSize1();
  }
  musicVO.setThumb(thumb);
  // System.out.println("--> thumb: " + thumb);  
  musicVO.setFile1(file1); 
  musicVO.setSize1(size1);
  // -------------------------------------------------------------------
      
  // -------------------------------------------------------------------
  // 2번째 파일
  // -------------------------------------------------------------------  
  String file2 = "";
  long size2 = 0;
  // <input type="file" name='file2MF' id='file2MF' size='40' >
  MultipartFile file2MF = musicVO.getFile2MF();
  size2 = file2MF.getSize();
  if (size2 > 0) { // 새로운 파일을 전송하는지 확인
    Tool.deleteFile(upDir, oldVO.getFile2()); // 기존 등록된 파일 삭제
    file2 = Upload.saveFileSpring(file2MF, upDir); // 새로운 파일 저장

  } else {
    file2 = oldVO.getFile2(); // 파일 업로드를하지 않는 경우
    size2 = oldVO.getSize2();
  }
  musicVO.setFile2(file2); 
  musicVO.setSize2(size2);
  // -------------------------------------------------------------------
  
  // -------------------------------------------------------------------
  // 3번째 파일
  // -------------------------------------------------------------------  
  String file3 = "";
  long size3 = 0;
  // <input type="file" name='file3MF' id='file3MF' size='40' >
  MultipartFile file3MF = musicVO.getFile3MF();
  size3 = file3MF.getSize();
  if (size3 > 0) { // 새로운 파일을 전송하는지 확인
    Tool.deleteFile(upDir, oldVO.getFile3()); // 기존 등록된 파일 삭제
    file3 = Upload.saveFileSpring(file3MF, upDir); // 새로운 파일 저장

  } else {
    file3 = oldVO.getFile3(); // 파일 업로드를하지 않는 경우
    size3 = oldVO.getSize3();
  }
  musicVO.setFile3(file3); 
  musicVO.setSize3(size3);
  // -------------------------------------------------------------------
  
  // -------------------------------------------------------------------
  // 4번째 파일
  // -------------------------------------------------------------------  
  String file4 = "";
  long size4 = 0;
  // <input type="file" name='file4MF' id='file4MF' size='40' >
  MultipartFile file4MF = musicVO.getFile4MF();
  size4 = file4MF.getSize();
  if (size4 > 0) { // 새로운 파일을 전송하는지 확인
    Tool.deleteFile(upDir, oldVO.getFile4()); // 기존 등록된 파일 삭제
    file4 = Upload.saveFileSpring(file4MF, upDir); // 새로운 파일 저장

  } else {
    file4 = oldVO.getFile4(); // 파일 업로드를하지 않는 경우
    size4 = oldVO.getSize4();
  }
  musicVO.setFile4(file4); 
  musicVO.setSize4(size4);
  // -------------------------------------------------------------------
  
  // -------------------------------------------------------------------
  // 5번째 파일
  // -------------------------------------------------------------------  
  String file5 = "";
  long size5 = 0;
  // <input type="file" name='file5MF' id='file5MF' size='40' >
  MultipartFile file5MF = musicVO.getFile5MF();
  size5 = file5MF.getSize();
  if (size5 > 0) { // 새로운 파일을 전송하는지 확인
    Tool.deleteFile(upDir, oldVO.getFile5()); // 기존 등록된 파일 삭제
    file5 = Upload.saveFileSpring(file5MF, upDir); // 새로운 파일 저장

  } else {
    file5 = oldVO.getFile5(); // 파일 업로드를하지 않는 경우
    size5 = oldVO.getSize5();
  }
  musicVO.setFile5(file5); 
  musicVO.setSize5(size5);
  // -------------------------------------------------------------------


  

  if (musicDAO.update(musicVO) == 1) {
    // 수정후 조회로 자동 이동
    mav.setViewName("redirect:/music/read.do?ctno=" + musicVO.getCtno()
        + "&ctno=" + musicVO.getCtno()); // 확장자 명시
  } else {
    msgs.add("게시판 수정에 실패 하셨습니다.");
    links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do?ctno="
           + musicVO.getCtno() + "'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
  }

  return mav;
}



/**
 * u_no 별로 게시판 목록을 검색+페이징하여 출력합니다.
 * 
 * @param u_no
 *          전체 목록에서 가져올 게시판 번호
 * @param searchDTO 검색 정보         
 * @return 추출된 게시판 목록
 */
@RequestMapping(value = "/music/list.do", 
                           method = RequestMethod.GET)
public ModelAndView list(
                                      SearchDTO searchDTO,
                                      HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/music/list");
  int totalRecord = 0;

  // HashMap hashMap = new HashMap();
  HashMap<String, Object> hashMap = new HashMap<String, Object>();
  hashMap.put("col", searchDTO.getCol());
  hashMap.put("word", searchDTO.getWord());
  
  int recordPerPage = 10; // 페이지당 출력할 레코드 갯수
  // 페이지에서 출력할 시작 레코드 번호 계산, nowPage는 1부터 시작
  int beginOfPage = (searchDTO.getNowPage() - 1) * 10; 
  // 1 page: 0
  // 2 page: 10
  // 3 page: 20
  int startNum = beginOfPage + 1; // 시작 rownum, 1
  int endNum = beginOfPage + recordPerPage; // 종료 rownum, 10
  hashMap.put("startNum", startNum);
  hashMap.put("endNum", endNum);

  List<MusicVO> list = musicDAO.list(hashMap); // 검색
  Iterator<MusicVO> iter = list.iterator();
  while (iter.hasNext() == true) { // 다음 요소 검사
    MusicVO vo = iter.next(); // 요소 추출
    vo.setTitle(Tool.textLength(vo.getTitle(), 10));
    vo.setWdate(vo.getWdate().substring(0, 10));
    // vo.setFile1(Tool.textLength(10, vo.getFile1()));
    // vo.setFile2(Tool.textLength(10, vo.getFile2()));
    vo.setSize2Label(Tool.unit(vo.getSize2()));
  }
  mav.addObject("list", list);
  
  totalRecord = musicDAO.count(hashMap);
  mav.addObject("totalRecord", musicDAO.count(hashMap)); // 검색된 레코드 갯수

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
 * 최근 목록을 출력합니다.
 * 
 * @return
 */
@RequestMapping(value = "/music/list2.do", method = RequestMethod.GET)
public ModelAndView newlist() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/music/list2"); // /webapp/member/list.jsp
   
   
   List<MusicVO> list = musicDAO.newlist();
   Iterator<MusicVO> iter = list.iterator(); // 객체를 순차적으로 접근하는 기능
   while(iter.hasNext() == true){  // 다음 요소 검사
     MusicVO vo = iter.next();  // 요소 추출
     vo.setTitle(Tool.textLength(vo.getTitle(),10));   // 문자열 10자 
     //vo.setWdate(vo.getWdate().substring(0, 10));      // 년 월 일
     // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
     //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
     
   }
   mav.addObject("list2", list);
   
   
   return mav;
}


  
}