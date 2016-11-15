package dev.mvc.mobile;

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


import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class MobileCont {

  @Autowired
  @Qualifier("dev.mvc.mobile.MobileDAO")
  private MobileDAOInter mobileDAO;
  
  @RequestMapping(value = "/mobile/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/create");
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "mobile/list.do");//
     
      
      
    } else {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>" 
          + "location.href = './create.jsp';"
          + "</script>");
      
    }
    
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString();
    String nickname = session.getAttribute("nickname").toString();
    String email = session.getAttribute("email").toString();
    String tel = session.getAttribute("tel").toString();
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    mav.addObject("nickname", nickname);
    mav.addObject("email", email);
    mav.addObject("tel", tel);
    
    
    return mav;
  }
  
  @RequestMapping(value = "/mobile/create.do", method = RequestMethod.POST)
  public ModelAndView create(MobileVO mobileVO, HttpServletRequest request,  HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/message");
    
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
    long size2 = 0;
    long size4 = 0;
    long size6 = 0;
    long size8 = 0;
    long size10 = 0;
    String upDir = Tool.getRealPath(request, "/mobile/storage");
    MultipartFile file2MF = mobileVO.getFile2MF();
    MultipartFile file4MF = mobileVO.getFile4MF();
    MultipartFile file6MF = mobileVO.getFile6MF();
    MultipartFile file8MF = mobileVO.getFile8MF();
    MultipartFile file10MF = mobileVO.getFile10MF();
    size2 = file2MF.getSize();

    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      mobileVO.setFile2(file2); // 전송된 파일명 저장
      mobileVO.setSize2(file2MF.getSize());

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
    mobileVO.setFile1(file1); // Thumb 이미지
    mobileVO.setFile2(file2); // 원본 이미지
    mobileVO.setSize2(size2); // 원본 이미지
   
    size4 = file4MF.getSize();

    // -------------------------------------------------------------------
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir);
      mobileVO.setFile4(file4); // 전송된 파일명 저장
      mobileVO.setSize4(file4MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file3)) {
        file3 = Tool.preview(upDir, file4, 120, 80);
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
    }
    mobileVO.setFile3(file3); // Thumb 이미지
    mobileVO.setFile4(file4); // 원본 이미지
    mobileVO.setSize4(size4); // 원본 이미지
    
    
    size6 = file6MF.getSize();

    // -------------------------------------------------------------------
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir);
      mobileVO.setFile6(file6); // 전송된 파일명 저장
      mobileVO.setSize6(file6MF.getSize());

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
    mobileVO.setFile5(file5); // Thumb 이미지
    mobileVO.setFile6(file6); // 원본 이미지
    mobileVO.setSize6(size6); // 원본 이미지
    
    
    
    size8 = file8MF.getSize();

    // -------------------------------------------------------------------
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir);
      mobileVO.setFile8(file8); // 전송된 파일명 저장
      mobileVO.setSize8(file8MF.getSize());

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
    mobileVO.setFile7(file7); // Thumb 이미지
    mobileVO.setFile8(file8); // 원본 이미지
    mobileVO.setSize8(size8); // 원본 이미지
    
    // -------------------------------------------------------------------
    
    
    size10 = file10MF.getSize();
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir);
      mobileVO.setFile10(file10); // 전송된 파일명 저장
      mobileVO.setSize10(file10MF.getSize());

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
    mobileVO.setFile9(file9); // Thumb 이미지
    mobileVO.setFile10(file10); // 원본 이미지
    mobileVO.setSize10(size10); // 원본 이미지
    
    // -------------------------------------------------------------------
 
  
    
    
    if (mobileDAO.create(mobileVO) == 1) {
      msgs.add("상품 등록 완료했습니다.");
      msgs.add("등록해주셔서 감사합니다.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">로그인</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    } else {
      msgs.add("상품 등록에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  

  @RequestMapping(value = "/mobile/list.do", method = RequestMethod.GET)
  public ModelAndView list2(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/list");// /webapp/member/list.jsp
   
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    int recordPerPage = 10; // 페이지당 출력할 레코드 갯수
    // 페이지에서 출력할 시작 레코드 번호 계산, nowPage는 0부터 시작
    int beginOfPage = (searchDTO.getNowPage() - 1) * 10; // nowPage는 1부터 시작
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // 시작 rownum 1 
    int endNum = beginOfPage + recordPerPage; // 종료 rownum 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    int totalRecord = 0;
    List<MobileVO> list = mobileDAO.list2(hashMap);
    Iterator<MobileVO> iter = list.iterator();
    while(iter.hasNext() == true){  // 다음 요소 검사
      MobileVO vo = iter.next();  // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10)); // 문자열 10자 분리
      vo.setWdate(vo.getWdate().substring(0, 10));  // 년월일
      //vo.setFile1(Tool.textLength(vo.getFile1(), 10));
      //vo.setFile2(Tool.textLength(vo.getFile2(), 10));
      vo.setSize2Label(Tool.unit(vo.getSize2()));
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    totalRecord = mobileDAO.count(hashMap);
    
    mav.addObject("totalRecord", mobileDAO.count(hashMap));
    
    
    String paging = new Paging().paging5( totalRecord,             
        searchDTO.getNowPage(), 
        recordPerPage, 
        searchDTO.getCol(), 
        searchDTO.getWord());
        mav.addObject("paging", paging);
    
    return mav;
  }

  @RequestMapping(value = "/mobile/read.do", method = RequestMethod.GET)
  public ModelAndView read(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/read");
    mav.addObject("mobileVO", mobileDAO.read(mno));
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
   
    mav.setViewName("/mobile/read");
    mav.addObject("mobileVO", mobileDAO.read(mno));
    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
    return mav;
  }

  @RequestMapping(value = "/mobile/update.do", method = RequestMethod.GET)
  public ModelAndView update(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/update"); // /webapp/blogcategory/update.jsp

    List<MobileVO> mobile_list = mobileDAO.list();
    mav.addObject("mobile_list", mobile_list);

    MobileVO mobileVO = mobileDAO.read(mno);
    mav.addObject("mobileVO", mobileVO);
    
    return mav;
  }
  
  @RequestMapping(value = "/mobile/update.do", method = RequestMethod.POST)
  public ModelAndView update(MobileVO mobileVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    
    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    long size2 = 0;
    long size4 = 0;
    long size6 = 0;
    long size8 = 0;
    long size10 = 0;
    String upDir = Tool.getRealPath(request, "/mobile/storage");
    MultipartFile file2MF = mobileVO.getFile2MF();
    MobileVO oldVO = mobileDAO.read(mobileVO.getMno()); 
         
    
    size2 = file2MF.getSize();
    if (file2MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile2()); // 기존 등록된 파일 삭제
      file2 = Upload.saveFileSpring(file2MF, upDir);// 새로운 파일 저장
      mobileVO.setSize2(file2MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
        file1 = Tool.preview(upDir, file2, 120, 80); // thumb 이미지 생성
      } else {
        file1 = "";
      }
    } else {
      file1 = oldVO.getFile1(); // 파일 업로드를하지 않는 경우
      file2 = oldVO.getFile2();
      size2 = oldVO.getSize2();
    }
    mobileVO.setFile1(file1); // Thumb 이미지
    mobileVO.setFile2(file2); // 원본 이미지
    mobileVO.setSize2(size2); // 원본 이미지
     
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
    MultipartFile file4MF = mobileVO.getFile4MF();
    MobileVO oldVO2 = mobileDAO.read(mobileVO.getMno()); 
         
    size4 = file4MF.getSize();

    if (file4MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO2.getFile4()); // 기존 등록된 파일 삭제
      file4 = Upload.saveFileSpring(file4MF, upDir);// 새로운 파일 저장
      mobileVO.setFile4(file4); // 전송된 파일명 저장
      mobileVO.setSize4(file4MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile3()); // 파일 삭제
        file3 = Tool.preview(upDir, file4, 120, 80); // thumb 이미지 생성
      } else {
        file3 = "";
      }
    } else {
      file3 = oldVO.getFile3(); // 파일 업로드를하지 않는 경우
      file4 = oldVO.getFile4();
      size4 = oldVO.getSize4();

    }
    mobileVO.setFile3(file3); // Thumb 이미지
    mobileVO.setFile4(file4); // 원본 이미지
    mobileVO.setSize4(size4); // 원본 이미지
    
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
    MultipartFile file6MF = mobileVO.getFile6MF();
    MobileVO oldVO3 = mobileDAO.read(mobileVO.getMno()); 
         
    size6 = file6MF.getSize();

    if (file6MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO3.getFile6()); // 기존 등록된 파일 삭제
      file6 = Upload.saveFileSpring(file6MF, upDir);// 새로운 파일 저장
      mobileVO.setFile6(file6); // 전송된 파일명 저장
      mobileVO.setSize6(file6MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile5()); // 파일 삭제
        file5 = Tool.preview(upDir, file6, 120, 80); // thumb 이미지 생성
      } else {
        file5 = "";
      }
    } else {
      file5 = oldVO.getFile5(); // 파일 업로드를하지 않는 경우
      file6 = oldVO.getFile6();
      size6 = oldVO.getSize6();

    }
    mobileVO.setFile5(file5); // Thumb 이미지
    mobileVO.setFile6(file6); // 원본 이미지
    mobileVO.setSize6(size6); // 원본 이미지
    
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
    MultipartFile file8MF = mobileVO.getFile8MF();
    MobileVO oldVO4 = mobileDAO.read(mobileVO.getMno()); 
         
    size8 = file8MF.getSize();

    if (file8MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO4.getFile8()); // 기존 등록된 파일 삭제
      file8 = Upload.saveFileSpring(file8MF, upDir);// 새로운 파일 저장
      mobileVO.setFile8(file8); // 전송된 파일명 저장
      mobileVO.setSize8(file8MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile7()); // 파일 삭제
        file7 = Tool.preview(upDir, file8, 120, 80); // thumb 이미지 생성
      } else {
        file7 = "";
      }
    } else {
      file7 = oldVO.getFile7(); // 파일 업로드를하지 않는 경우
      file8 = oldVO.getFile8();
      size8 = oldVO.getSize8();

    }
    mobileVO.setFile7(file7); // Thumb 이미지
    mobileVO.setFile8(file8); // 원본 이미지
    mobileVO.setSize8(size8); // 원본 이미지
    
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
    MultipartFile file10MF = mobileVO.getFile10MF();
    MobileVO oldVO5 = mobileDAO.read(mobileVO.getMno()); 
         
    size10 = file10MF.getSize();
    if (file10MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO5.getFile10()); // 기존 등록된 파일 삭제
      file10 = Upload.saveFileSpring(file10MF, upDir);// 새로운 파일 저장
      mobileVO.setFile10(file10); // 전송된 파일명 저장
      mobileVO.setSize10(file10MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile9()); // 파일 삭제
        file9 = Tool.preview(upDir, file10, 120, 80); // thumb 이미지 생성
      } else {
        file9 = "";
      }
    } else {
      file9 = oldVO.getFile9(); // 파일 업로드를하지 않는 경우
      file10 = oldVO.getFile10();
      size10 = oldVO.getSize10();

    }
    mobileVO.setFile9(file9); // Thumb 이미지
    mobileVO.setFile10(file10); // 원본 이미지
    mobileVO.setSize10(size10); // 원본 이미지
 
    // -------------------------------------------------------------------


    
    if (mobileDAO.update(mobileVO) == 1) {
       mav.setViewName("redirect:/mobile/list.do");
       } else {
      msgs.add("상품 정보 변경에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");

      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
    return mav;
  }
  /**
   * 삭제폼
   * @param blogno
   * @return
   */
  @RequestMapping(value = "/mobile/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/delete"); // /webapp/member/delete.jsp
    mav.addObject("mno", mno);
 
    
    return mav;
  }
  
  /**
   * 레코드 1건을 삭제합니다.
   * 
   * @param codeno
   * @return
   */
  @RequestMapping(value = "/mobile/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(MobileVO mobileVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
System.out.println("delete");
    if (mobileDAO.delete(mobileVO.getMno()) == 1) {
      mav.setViewName("redirect:/mobile/list.do");
    } else {
      msgs.add("삭제에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");

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
  @RequestMapping(value = "/mobile/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/mobile/list2"); // /webapp/member/list.jsp
     
     
     List<MobileVO> list = mobileDAO.newlist();
     Iterator<MobileVO> iter = list.iterator(); // 객체를 순차적으로 접근하는 기능
     while(iter.hasNext() == true){  // 다음 요소 검사
       MobileVO vo = iter.next();  // 요소 추출
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // 문자열 10자 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // 년 월 일
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     
     return mav;
  }

  
}
