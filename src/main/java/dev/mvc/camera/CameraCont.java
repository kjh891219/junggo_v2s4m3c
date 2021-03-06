package dev.mvc.camera;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tmember.MemberDAOInter;
import dev.mvc.tmember.MemberVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

import org.springframework.beans.factory.annotation.Qualifier;

@Controller
public class CameraCont {
  @Autowired
  @Qualifier("dev.mvc.camera.CameraDAO")
  private CameraDAOInter cameraDAO;
  
  
 
  public CameraCont() {
    System.out.println("--> CameraCont created."); 
  }
  
    
  
  /**
   * 전체 목록을 출력합니다.
   * 
   * @return
   */
  @RequestMapping(value = "/camera/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/list"); // /webapp/member/list.jsp
    
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
    
    
    
    
    System.out.println(searchDTO.getCol());
    System.out.println(searchDTO.getWord());
    
    
    int totalRecord = 0;
    List<CameraVO> list = cameraDAO.list3(hashMap);
    Iterator<CameraVO> iter = list.iterator();
    while (iter.hasNext() == true) { // 다음 요소 검사
      CameraVO vo = iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2())); // MB 단위 변환
      vo.setSize4Label(Tool.unit(vo.getSize4()));
      
    }
    
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    
    totalRecord = cameraDAO.count(hashMap);
    mav.addObject("totalRecord", cameraDAO.count(hashMap));
    
    String paging = new Paging().paging5(
        totalRecord, 
        searchDTO.getNowPage(), 
        recordPerPage, 
        searchDTO.getCol(), 
        searchDTO.getWord());
    mav.addObject("paging", paging);
    
    System.out.println(paging);
    
    
    return mav;
  }

  @RequestMapping(value = "/camera/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
    System.out.println("--> create() GET called.");
    
  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/create"); // /webapp/member/create.jsp
    
    
    
   /* response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null ){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "camera/list.do");//
     
      
      
    } else {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>" 
          + "location.href = './create.jsp';"
          + "</script>");
      
    }*/
    
    
    
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString();
    String tel = session.getAttribute("tel").toString();
    MemberVO memberVO = cameraDAO.test(userid);
    
    mav.addObject("memberVO", memberVO);
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    mav.addObject("tel", tel);
    System.out.println(memberVO);
    
    
    
    
    
    
    return mav;
  }

  @RequestMapping(value = "/camera/create.do", method = RequestMethod.POST)
  public ModelAndView create(CameraVO cameraVO, HttpServletRequest request) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/message"); // webapp/member/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    
 // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String upDir = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file2MF = cameraVO.getFile2MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      cameraVO.setFile2(file2); // 전송된 파일명 저장
      cameraVO.setSize2(file2MF.getSize());

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
    cameraVO.setFile1(file1); // Thumb 이미지
    cameraVO.setFile2(file2); // 원본 이미지
    // -------------------------------------------------------------------
    
 
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
    
    
    
 // -------------------------------------------------------------------
    // 파일 전송2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
    String upDir2 = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file4MF = cameraVO.getFile4MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir2);
      cameraVO.setFile4(file4); // 전송된 파일명 저장
      cameraVO.setSize4(file4MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) {
        file3 = Tool.preview(upDir2, file4, 250, 250);
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile3(file3); // Thumb 이미지
    cameraVO.setFile4(file4); // 원본 이미지
    // -------------------------------------------------------------------
    
    
 // -------------------------------------------------------------------
    // 파일 전송3
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";
    String upDir3 = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file6MF = cameraVO.getFile6MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file6MF.getSize() > 0) {
      file6 = Upload.saveFileSpring(file6MF, upDir3);
      cameraVO.setFile6(file6); // 전송된 파일명 저장
      cameraVO.setSize6(file6MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) {
        file5 = Tool.preview(upDir3, file6, 250, 250);
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile5(file5); // Thumb 이미지
    cameraVO.setFile6(file6); // 원본 이미지
    // -------------------------------------------------------------------
    
 
    
    
    // -------------------------------------------------------------------
    // 파일 전송4
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";
    String upDir4 = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file8MF = cameraVO.getFile8MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file8MF.getSize() > 0) {
      file8 = Upload.saveFileSpring(file8MF, upDir4);
      cameraVO.setFile8(file8); // 전송된 파일명 저장
      cameraVO.setSize8(file8MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) {
        file7 = Tool.preview(upDir4, file8, 250, 250);
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile7(file7); // Thumb 이미지
    cameraVO.setFile8(file8); // 원본 이미지
    // -------------------------------------------------------------------
    
    
    
 // -------------------------------------------------------------------
    // 파일 전송5
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";
    String upDir5 = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file10MF = cameraVO.getFile10MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file10MF.getSize() > 0) {
      file10 = Upload.saveFileSpring(file10MF, upDir5);
      cameraVO.setFile10(file10); // 전송된 파일명 저장
      cameraVO.setSize10(file10MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) {
        file9 = Tool.preview(upDir5, file10, 250, 250);
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile9(file9); // Thumb 이미지
    cameraVO.setFile10(file10); // 원본 이미지
    // -------------------------------------------------------------------
    
    
    
    
    
    
    
    
    if (cameraDAO.create(cameraVO) == 1) {
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
  
  @RequestMapping(value = "/camera/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno, SearchDTO searchDTO, HttpServletRequest request, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    cameraDAO.increaseCnt(ctno); //조회수 증가
    mav.setViewName("/camera/read");
    CameraVO cameraVO = cameraDAO.read(ctno);
    cameraVO.setSize2Label(Tool.unit(cameraVO.getSize2()));
    cameraVO.setSize4Label(Tool.unit(cameraVO.getSize4()));
    cameraVO.setSize6Label(Tool.unit(cameraVO.getSize6()));
    cameraVO.setSize8Label(Tool.unit(cameraVO.getSize8()));
    cameraVO.setSize10Label(Tool.unit(cameraVO.getSize10()));
    mav.addObject("cameraVO", cameraVO);
    mav.addObject("searchDTO", searchDTO);
    mav.addObject("root", request.getContextPath());

    return mav;
  }
  
  /**
   * 수정폼
   * @param ctno
   * @return
   */
  @RequestMapping(value="/camera/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int ctno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/update"); 
    mav.addObject("cameraVO", cameraDAO.read(ctno)); 
    return mav;
 
  }
  
  
  

  
  
  
  
  
  
  
  
  /**
   * 글과 파일을 수정 처리
   * 
   * @param cameraVO
   * @return
   */
  @RequestMapping(value = "/camera/update.do", method = RequestMethod.POST)
  public ModelAndView update(CameraVO cameraVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
 // -------------------------------------------------------------------
    // 파일 전송 관련
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";

    String upDir = Tool.getRealPath(request, "/camera/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = cameraVO.getFile2MF();
    CameraVO oldVO = cameraDAO.read(cameraVO.getCtno());

    if (file2MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile2()); // 기존 등록된 파일 삭제
      file2 = Upload.saveFileSpring(file2MF, upDir); // 새로운 파일 저장
      cameraVO.setFile2(file2); // 새로운 파일명 저장
      cameraVO.setSize2(file2MF.getSize()); // 새로운 크기 저장

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
        file1 = Tool.preview(upDir, file2, 250, 250); // thumb 이미지 생성
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------

    } else {
      file1 = oldVO.getFile1(); // 파일 업로드를하지 않는 경우
      file2 = oldVO.getFile2();
    }
    cameraVO.setFile1(file1); 
    cameraVO.setFile2(file2);
    
    
    
    
    
    
 // -------------------------------------------------------------------
    // 파일 전송 관련2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";

    String upDir2 = Tool.getRealPath(request, "/camera/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file4MF = cameraVO.getFile4MF();
    CameraVO oldVO2 = cameraDAO.read(cameraVO.getCtno());

    if (file4MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir2, oldVO2.getFile4()); // 기존 등록된 파일 삭제
      file4 = Upload.saveFileSpring(file4MF, upDir2); // 새로운 파일 저장
      cameraVO.setFile4(file4); // 새로운 파일명 저장
      cameraVO.setSize4(file4MF.getSize()); // 새로운 크기 저장

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) { // 이미지인지 검사
        Tool.deleteFile(upDir2, oldVO2.getFile3()); // 파일 삭제
        file3 = Tool.preview(upDir2, file4, 250, 250); // thumb 이미지 생성
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------

    } else {
      file3 = oldVO2.getFile3(); // 파일 업로드를하지 않는 경우
      file4 = oldVO2.getFile4();
    }
    cameraVO.setFile3(file3); 
    cameraVO.setFile4(file4);
    
    
    
 // -------------------------------------------------------------------
    // 파일 전송 관련3
    // -------------------------------------------------------------------
    String file5 = "";
    String file6 = "";

    String upDir3 = Tool.getRealPath(request, "/camera/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file6MF = cameraVO.getFile6MF();
    CameraVO oldVO3 = cameraDAO.read(cameraVO.getCtno());

    if (file6MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir3, oldVO3.getFile6()); // 기존 등록된 파일 삭제
      file6 = Upload.saveFileSpring(file6MF, upDir3); // 새로운 파일 저장
      cameraVO.setFile6(file6); // 새로운 파일명 저장
      cameraVO.setSize6(file6MF.getSize()); // 새로운 크기 저장

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file6)) { // 이미지인지 검사
        Tool.deleteFile(upDir3, oldVO3.getFile5()); // 파일 삭제
        file5 = Tool.preview(upDir3, file6, 250, 250); // thumb 이미지 생성
      } else {
        file5 = "";
      }
      // -------------------------------------------------------------------

    } else {
      file5 = oldVO3.getFile5(); // 파일 업로드를하지 않는 경우
      file6 = oldVO3.getFile6();
    }
    cameraVO.setFile5(file5); 
    cameraVO.setFile6(file6);
    
    
    
 // -------------------------------------------------------------------
    // 파일 전송 관련4
    // -------------------------------------------------------------------
    String file7 = "";
    String file8 = "";

    String upDir4 = Tool.getRealPath(request, "/camera/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file8MF = cameraVO.getFile8MF();
    CameraVO oldVO4 = cameraDAO.read(cameraVO.getCtno());

    if (file8MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir4, oldVO4.getFile8()); // 기존 등록된 파일 삭제
      file8 = Upload.saveFileSpring(file8MF, upDir4); // 새로운 파일 저장
      cameraVO.setFile8(file8); // 새로운 파일명 저장
      cameraVO.setSize8(file8MF.getSize()); // 새로운 크기 저장

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file8)) { // 이미지인지 검사
        Tool.deleteFile(upDir4, oldVO4.getFile7()); // 파일 삭제
        file7 = Tool.preview(upDir4, file8, 250, 250); // thumb 이미지 생성
      } else {
        file7 = "";
      }
      // -------------------------------------------------------------------

    } else {
      file7 = oldVO4.getFile7(); // 파일 업로드를하지 않는 경우
      file8 = oldVO4.getFile8();
    }
    cameraVO.setFile7(file7); 
    cameraVO.setFile8(file8);
    
    
    
    
 // -------------------------------------------------------------------
    // 파일 전송 관련2
    // -------------------------------------------------------------------
    String file9 = "";
    String file10 = "";

    String upDir5 = Tool.getRealPath(request, "/camera/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file10MF = cameraVO.getFile10MF();
    CameraVO oldVO5 = cameraDAO.read(cameraVO.getCtno());

    if (file10MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir5, oldVO5.getFile10()); // 기존 등록된 파일 삭제
      file10 = Upload.saveFileSpring(file10MF, upDir5); // 새로운 파일 저장
      cameraVO.setFile10(file10); // 새로운 파일명 저장
      cameraVO.setSize10(file10MF.getSize()); // 새로운 크기 저장

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file10)) { // 이미지인지 검사
        Tool.deleteFile(upDir5, oldVO5.getFile9()); // 파일 삭제
        file9 = Tool.preview(upDir5, file10, 250, 250); // thumb 이미지 생성
      } else {
        file10 = "";
      }
      // -------------------------------------------------------------------

    } else {
      file9 = oldVO5.getFile9(); // 파일 업로드를하지 않는 경우
      file10 = oldVO5.getFile10();
    }
    cameraVO.setFile9(file9); 
    cameraVO.setFile10(file10);
    
 
    
    
    
 
    if (cameraDAO.update(cameraVO) == 1) {
      // 수정후 조회로 자동 이동
      mav.setViewName("redirect:/camera/read.do?ctno=" + cameraVO.getCtno());
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
   * 삭제폼
   * @param ctno
   * @return
   */
  @RequestMapping(value = "/camera/delete.do", 
                              method = RequestMethod.GET)
  public ModelAndView delete(int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/delete"); // /webapp/blog/delete.jsp
    mav.addObject("ctno", ctno);
    return mav;
  }
  
  /**
   * 삭제 처리
   * @param blogVO
   * @return
   */
  @RequestMapping(value = "/camera/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(CameraVO cameraVO) {
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (cameraDAO.delete(cameraVO.getCtno()) == 1) {
      
      mav.setViewName("redirect:/camera/list.do");//확장자 명시

    } else {
      msgs.add("글 삭제에 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./index.jsp'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'>목록</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  @RequestMapping(value = "/camera/list2.do", method = RequestMethod.GET)
  public ModelAndView newlist() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/camera/list2"); // /webapp/member/list.jsp
     
     
     List<CameraVO> list = cameraDAO.newlist();
     Iterator<CameraVO> iter = list.iterator(); // 객체를 순차적으로 접근하는 기능
     while(iter.hasNext() == true){  // 다음 요소 검사
       CameraVO vo = iter.next();  // 요소 추출
       vo.setTitle(Tool.textLength(vo.getTitle(),10));   // 문자열 10자 
       //vo.setWdate(vo.getWdate().substring(0, 10));      // 년 월 일
       // vo.setFile1(Tool.textLength(vo.getFile1(),10));  
       //vo.setThumb(Tool.textLength(vo.getThumb(),10)); 
       
     }
     mav.addObject("list2", list);
     
     
     return mav;
  }

}
