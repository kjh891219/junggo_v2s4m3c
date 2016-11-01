package dev.mvc.reviews;

import java.io.IOException;
import java.io.PrintWriter;

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
public class ReviewsCont {
  @Autowired
  @Qualifier("dev.mvc.reviews.ReviewsDAO")
  private ReviewsDAOInter reviewsDAO;
  
  public ReviewsCont(){
    System.out.println("--> ReviewsCont created.");
  }
 
  /**
   * 레코드 등록
   * @param
   * @return
   */
 @RequestMapping(value = "/reviews/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException { 
  // System.out.println("--> create() GET called.");
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/reviews/create"); // /webapp/reviews/create.jsp
  
  
  response.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  if (session.getAttribute("userid") == null ){
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
     + "location.href = '../member/login.do';"
     + "</script>"); 
    session.setAttribute("url", "reviews/list.do");//
   
    
    
  } else {
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>" 
        + "location.href = './create.jsp';"
        + "</script>");
    
  }

  String userid = session.getAttribute("userid").toString();
  MemberVO memberVO = reviewsDAO.test(userid);
  
  mav.addObject("memberVO", memberVO);
  mav.addObject("userid", userid);
  System.out.println(memberVO);

  return mav;
}

 /**
  * 등록 처리
  * @param reviewsVO
  * @param request
  * @param session
  * @return
  */
 @RequestMapping(value = "/reviews/create.do", 
                            method = RequestMethod.POST)
 public ModelAndView create(ReviewsVO reviewsVO, 
                                          HttpServletRequest request, 
                                          HttpSession session) {
   // System.out.println("--> create() POST called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/reviews/message");
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // 파일 전송
   // -------------------------------------------------------------------
   String thumb = "";
   String file1 = "";
   long size1 = 0;
   String upDir = Tool.getRealPath(request, "/reviews/storage");
   MultipartFile file1MF = reviewsVO.getFile1MF();
   System.out.println("file1MF:"+file1MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size1 = file1MF.getSize();
   if (size1 > 0) {
     file1 = Upload.saveFileSpring(file1MF, upDir);
     // -------------------------------------------------------------------
     // Thumb 파일 생성
     // -------------------------------------------------------------------
     if (Tool.isImage(file1)) {
       thumb = Tool.preview(upDir, file1, 120, 80);
     } else {
       thumb = "";
     }
     
     // -------------------------------------------------------------------
   }
   reviewsVO.setThumb(thumb); // Thumb 이미지
   reviewsVO.setFile1(file1); // 원본 이미지
   reviewsVO.setSize1(size1); // 원본 이미지
   // -------------------------------------------------------------------
   
   
   // -------------------------------------------------------------------
   // 파일 전송2
   // -------------------------------------------------------------------
   String file2 = "";
   long size2 = 0;
   MultipartFile file2MF = reviewsVO.getFile2MF();
   System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size2 = file2MF.getSize();
   if (size2 > 0) {
     file2 = Upload.saveFileSpring(file2MF, upDir);
   }
   reviewsVO.setFile2(file2); // 원본 이미지
   reviewsVO.setSize2(size2); // 원본 이미지
   // -------------------------------------------------------------------
   
   // -------------------------------------------------------------------
   // 파일 전송 3
   // -------------------------------------------------------------------
   String file3 = "";
   long size3 = 0;
   MultipartFile file3MF = reviewsVO.getFile3MF();
   // System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size3 = file3MF.getSize();
   if (size3 > 0) {
     file3 = Upload.saveFileSpring(file3MF, upDir);
   }
   reviewsVO.setFile3(file3); // 원본 이미지
   reviewsVO.setSize3(size3); // 원본 이미지
   // -------------------------------------------------------------------

   // -------------------------------------------------------------------
   // 파일 전송 4
   // -------------------------------------------------------------------
   String file4 = "";
   long size4 = 0;
   MultipartFile file4MF = reviewsVO.getFile4MF();

   size4 = file4MF.getSize();
   if (size4 > 0) {
     file4 = Upload.saveFileSpring(file4MF, upDir);
   }
   reviewsVO.setFile4(file4); // 원본 이미지
   reviewsVO.setSize4(size4); // 원본 이미지
   // -------------------------------------------------------------------
   
   
   // -------------------------------------------------------------------
   // 파일 전송 5
   // -------------------------------------------------------------------
   String file5 = "";
   long size5 = 0;
   MultipartFile file5MF = reviewsVO.getFile5MF();

   size5 = file5MF.getSize();
   if (size5 > 0) {
     file5 = Upload.saveFileSpring(file5MF, upDir);
   }
   reviewsVO.setFile5(file5); // 원본 이미지
   reviewsVO.setSize5(size5); // 원본 이미지
   // -------------------------------------------------------------------
   
   //usedcarVO.setU_no(4); // 회원 연동시 변경
   // Integer itg = (Integer)(session.getAttribute("mno"));
   // blogVO.setMno(itg.intValue());
    
   if (reviewsDAO.create(reviewsVO) == 1) {
     msgs.add("글을 등록했습니다.");
     links.add("<button type='button' onclick=\"location.href='./create.do?r_no="
             + reviewsVO.getR_no() + "'\">계속 등록</button>");
   } else {
     msgs.add("글 등록에 실패했습니다.");
     msgs.add("다시 시도해주세요.");
     links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
   }

   links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
   links.add("<button type='button' onclick=\"location.href='./list.do?r_no="
           + reviewsVO.getR_no() + "'\">목록</button>");
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);

   return mav;
 }
 
 /**
  * 한건의 레코드 조회
  * @param r_no
  * @return
  */
 @RequestMapping(value = "/reviews/read.do", method = RequestMethod.GET)
 public ModelAndView read(int r_no, SearchDTO searchDTO) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/reviews/read");
   reviewsDAO.increaseCnt(r_no);
   mav.addObject("reviewsVO", reviewsDAO.read(r_no));
   mav.addObject("searchDTO", searchDTO);
   
   return mav;
 }
 
 /**
  * 수정폼
  * @param r_no
  * @return
  */
 @RequestMapping(value="/reviews/update.do", 
                            method=RequestMethod.GET)
 public ModelAndView update(int r_no){  
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/reviews/update");
   ReviewsVO reviewsVO = reviewsDAO.read(r_no);
   mav.addObject("reviewsVO", reviewsVO);
  

   return mav;

 }
  
 /**
  * 글과 파일을 수정 처리
  * @param reviewsVO
  * @return
  */
 @RequestMapping(value = "/reviews/update.do", 
                            method = RequestMethod.POST)
 public ModelAndView update(ReviewsVO reviewsVO, HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();

   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // 파일 전송 관련
   // -------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/reviews/storage");
   ReviewsVO oldVO = reviewsDAO.read(reviewsVO.getR_no());

   // -------------------------------------------------------------------
   // 1번째 파일
   // -------------------------------------------------------------------  
   String thumb = "";
   String file1 = "";
   long size1 = 0;
   // <input type="file" name='file1MF' id='file1MF' size='40' >
   MultipartFile file1MF = reviewsVO.getFile1MF();
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
   reviewsVO.setThumb(thumb);
   // System.out.println("--> thumb: " + thumb);  
   reviewsVO.setFile1(file1); 
   reviewsVO.setSize1(size1);
   // -------------------------------------------------------------------
       
   // -------------------------------------------------------------------
   // 2번째 파일
   // -------------------------------------------------------------------  
   String file2 = "";
   long size2 = 0;
   // <input type="file" name='file2MF' id='file2MF' size='40' >
   MultipartFile file2MF = reviewsVO.getFile2MF();
   size2 = file2MF.getSize();
   if (size2 > 0) { // 새로운 파일을 전송하는지 확인
     Tool.deleteFile(upDir, oldVO.getFile2()); // 기존 등록된 파일 삭제
     file2 = Upload.saveFileSpring(file2MF, upDir); // 새로운 파일 저장

   } else {
     file2 = oldVO.getFile2(); // 파일 업로드를하지 않는 경우
     size2 = oldVO.getSize2();
   }
   reviewsVO.setFile2(file2); 
   reviewsVO.setSize2(size2);
   // -------------------------------------------------------------------
   
   // -------------------------------------------------------------------
   // 3번째 파일
   // -------------------------------------------------------------------  
   String file3 = "";
   long size3 = 0;
   // <input type="file" name='file3MF' id='file3MF' size='40' >
   MultipartFile file3MF = reviewsVO.getFile3MF();
   size3 = file3MF.getSize();
   if (size3 > 0) { // 새로운 파일을 전송하는지 확인
     Tool.deleteFile(upDir, oldVO.getFile3()); // 기존 등록된 파일 삭제
     file3 = Upload.saveFileSpring(file3MF, upDir); // 새로운 파일 저장

   } else {
     file3 = oldVO.getFile3(); // 파일 업로드를하지 않는 경우
     size3 = oldVO.getSize3();
   }
   reviewsVO.setFile3(file3); 
   reviewsVO.setSize3(size3);
   // -------------------------------------------------------------------
   
   // -------------------------------------------------------------------
   // 4번째 파일
   // -------------------------------------------------------------------  
   String file4 = "";
   long size4 = 0;
   // <input type="file" name='file4MF' id='file4MF' size='40' >
   MultipartFile file4MF = reviewsVO.getFile4MF();
   size4 = file4MF.getSize();
   if (size4 > 0) { // 새로운 파일을 전송하는지 확인
     Tool.deleteFile(upDir, oldVO.getFile4()); // 기존 등록된 파일 삭제
     file4 = Upload.saveFileSpring(file4MF, upDir); // 새로운 파일 저장

   } else {
     file4 = oldVO.getFile4(); // 파일 업로드를하지 않는 경우
     size4 = oldVO.getSize4();
   }
   reviewsVO.setFile4(file4); 
   reviewsVO.setSize4(size4);
   // -------------------------------------------------------------------
   
   // -------------------------------------------------------------------
   // 5번째 파일
   // -------------------------------------------------------------------  
   String file5 = "";
   long size5 = 0;
   // <input type="file" name='file5MF' id='file5MF' size='40' >
   MultipartFile file5MF = reviewsVO.getFile5MF();
   size5 = file5MF.getSize();
   if (size5 > 0) { // 새로운 파일을 전송하는지 확인
     Tool.deleteFile(upDir, oldVO.getFile5()); // 기존 등록된 파일 삭제
     file5 = Upload.saveFileSpring(file5MF, upDir); // 새로운 파일 저장

   } else {
     file5 = oldVO.getFile5(); // 파일 업로드를하지 않는 경우
     size5 = oldVO.getSize5();
   }
   reviewsVO.setFile5(file5); 
   reviewsVO.setSize5(size5);
   // -------------------------------------------------------------------


   if (reviewsDAO.update(reviewsVO) == 1) {
     // 수정후 조회로 자동 이동
     mav.setViewName("redirect:/reviews/read.do?r_no=" + reviewsVO.getR_no()
         + "&r_no=" + reviewsVO.getR_no()); // 확장자 명시
   } else {
     msgs.add("게시판 수정에 실패 하셨습니다.");
     links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
     links.add("<button type='button' onclick=\"location.href='./list.do?r_no="
            + reviewsVO.getR_no() + "'\">목록</button>");
     mav.addObject("msgs", msgs);
     mav.addObject("links", links);
   }

   return mav;
 }
 
 /**
  * 삭제폼
  * @param r_no
  * @return
  */
 @RequestMapping(value = "/reviews/delete.do", method = RequestMethod.GET)
 public ModelAndView delete(int r_no) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/reviews/delete"); // /webapp/member/delete.jsp
   mav.addObject("r_no", r_no);
   
   return mav;
 }

/**
* 삭제 처리
* @param reviewsVO
* @return
*/
@RequestMapping(value = "/reviews/delete.do", 
                        method = RequestMethod.POST)
public ModelAndView delete(ReviewsVO reviewsVO) {
ModelAndView mav = new ModelAndView();
mav.setViewName("/reviews/message");

ArrayList<String> msgs = new ArrayList<String>();
ArrayList<String> links = new ArrayList<String>(); 

if (reviewsDAO.delete(reviewsVO.getR_no()) == 1) {
 mav.setViewName("redirect:/reviews/list.do?R_no=" + reviewsVO.getR_no());//확장자 명시

} else {
 msgs.add("글 삭제에 실패했습니다.");
 links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
 links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
 links.add("<button type='button' onclick=\"location.href='./list.do?r_no="+reviewsVO.getR_no()+"'\">목록</button>");
}

mav.addObject("msgs", msgs);
mav.addObject("links", links);

return mav;
}

 
 /**
  * r_no 별로 게시판 목록을 검색+페이징하여 출력합니다.
  * 
  * @param r_no
  *          전체 목록에서 가져올 게시판 번호
  * @param searchDTO 검색 정보         
  * @return 추출된 게시판 목록
  */
 @RequestMapping(value = "/reviews/list.do", 
                            method = RequestMethod.GET)
 public ModelAndView list(
                                       SearchDTO searchDTO,
                                       HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/reviews/list");
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

   List<ReviewsVO> list = reviewsDAO.list(hashMap); // 검색
   Iterator<ReviewsVO> iter = list.iterator();
   while (iter.hasNext() == true) { // 다음 요소 검사
     ReviewsVO vo = iter.next(); // 요소 추출
     vo.setTitle(Tool.textLength(vo.getTitle(), 10));
     vo.setWdate(vo.getWdate().substring(0, 10));
     // vo.setFile1(Tool.textLength(10, vo.getFile1()));
     // vo.setFile2(Tool.textLength(10, vo.getFile2()));
     vo.setSize2Label(Tool.unit(vo.getSize2()));
   }
   mav.addObject("list", list);

   
   totalRecord = reviewsDAO.count(hashMap);
   mav.addObject("totalRecord", reviewsDAO.count(hashMap)); // 검색된 레코드 갯수

   String paging = new Paging().paging5(
                                          totalRecord, 
                                          searchDTO.getNowPage(), 
                                          recordPerPage, 
                                          searchDTO.getCol(), 
                                          searchDTO.getWord());
   mav.addObject("paging", paging);
   return mav;
 }
 


  
}
