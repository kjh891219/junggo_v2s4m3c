package dev.mvc.usedcar;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import dev.mvc.usedcar.UsedcarVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

 
@Controller
public class UsedcarCont {
  @Autowired
  @Qualifier("dev.mvc.usedcar.UsedcarDAO")
  private UsedcarDAOInter usedcarDAO;
  
  public UsedcarCont(){
    System.out.println("--> UsedcarCont created.");
  }
  
 @RequestMapping(value = "/usedcar/create.do", 
      method = RequestMethod.GET)
public ModelAndView create() {
// System.out.println("--> create() GET called.");
ModelAndView mav = new ModelAndView();
mav.setViewName("/usedcar/create"); // /webapp/blog/create.jsp
return mav;
}
  
  /**
   * 전체 목록을 출력합니다.
   * 
   * @return
   */
 /* @RequestMapping(value = "/usedcar/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/list"); //  /webapp/member/list.jsp
   
    List<UsedcarVO> list = usedcarDAO.list();
    Iterator<UsedcarVO> iter = list.iterator();
    while(iter.hasNext() == true){  // 다음 요소 검사
      UsedcarVO vo = iter.next();  // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10)); // 문자열 10자 분리
      vo.setWdate(vo.getWdate().substring(0, 10));  // 년월일
      vo.setFile1(Tool.textLength(vo.getFile1(), 10));
      vo.setFile2(Tool.textLength(vo.getFile2(), 10));
      
    }
       
    mav.addObject("list", list);
 
    return mav;
  }
  */
  
  @RequestMapping(value = "/usedcar/read.do", method = RequestMethod.GET)
  public ModelAndView read(int u_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/read");
    usedcarDAO.increaseCnt(u_no);
    mav.addObject("usedcarVO", usedcarDAO.read(u_no));
    
    return mav;
  }
 
/**
   * 등록 처리
   * @param blogVO
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value = "/usedcar/create.do", 
                             method = RequestMethod.POST)
  public ModelAndView create(UsedcarVO usedcarVO, 
                                           HttpServletRequest request, 
                                           HttpSession session) {
    // System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/message");
    
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
    String upDir = Tool.getRealPath(request, "/usedcar/storage");
    MultipartFile file2MF = usedcarVO.getFile2MF();
    System.out.println("file2MF:"+file2MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      usedcarVO.setFile2(file2); // 전송된 파일명 저장
      usedcarVO.setSize2(file2MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) {
        file1 = Tool.preview(upDir, file2, 120, 80);
      } else {
        file1 = "";
      }
      
      if (Tool.isImage(file4)) {
        file3 = Tool.preview(upDir, file4, 120, 80);
      } else {
        file3 = "";
      }
      
      if (Tool.isImage(file6)) {
        file5 = Tool.preview(upDir, file6, 120, 80);
      } else {
        file5 = "";
      }
      
      if (Tool.isImage(file8)) {
        file7 = Tool.preview(upDir, file8, 120, 80);
      } else {
        file7 = "";
      }
      
      if (Tool.isImage(file10)) {
        file9 = Tool.preview(upDir, file10, 120, 80);
      } else {
        file9 = "";
      }
      // -------------------------------------------------------------------
    }
    usedcarVO.setFile1(file1); // Thumb 이미지
    usedcarVO.setFile2(file2); // 원본 이미지
    usedcarVO.setFile3(file3); // Thumb 이미지
    usedcarVO.setFile4(file4); // 원본 이미지
    usedcarVO.setFile5(file5); // Thumb 이미지
    usedcarVO.setFile6(file6); // 원본 이미지
    usedcarVO.setFile7(file7); // Thumb 이미지
    usedcarVO.setFile8(file8); // 원본 이미지
    usedcarVO.setFile9(file9); // Thumb 이미지
    usedcarVO.setFile10(file10); // 원본 이미지
    // -------------------------------------------------------------------
    
    //usedcarVO.setU_no(4); // 회원 연동시 변경
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
     
    if (usedcarDAO.create(usedcarVO) == 1) {
      msgs.add("글을 등록했습니다.");
      links.add("<button type='button' onclick=\"location.href='./create.do?u_no="
              + usedcarVO.getU_no() + "'\">계속 등록</button>");
    } else {
      msgs.add("글 등록에 실패했습니다.");
      msgs.add("다시 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do?u_no="
            + usedcarVO.getU_no() + "'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  
  /**
   * 수정폼
   * @param blogno
   * @return
   */
  @RequestMapping(value="/usedcar/update.do", 
                             method=RequestMethod.GET)
  public ModelAndView update(int u_no){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/update");
    UsedcarVO usedcarVO = usedcarDAO.read(u_no);
    mav.addObject("usedcarVO", usedcarVO);
   

    return mav;

  }
   
  /**
   * 글과 파일을 수정 처리
   * @param blogVO
   * @return
   */
  @RequestMapping(value = "/usedcar/update.do", 
                             method = RequestMethod.POST)
  public ModelAndView update(UsedcarVO usedcarVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    // -------------------------------------------------------------------
    // 파일 전송 관련
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
 
    String upDir = Tool.getRealPath(request, "/usedcar/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = usedcarVO.getFile2MF();
    UsedcarVO oldVO = usedcarDAO.read(usedcarVO.getU_no());
 
    if (file2MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile2()); // 기존 등록된 파일 삭제
      file2 = Upload.saveFileSpring(file2MF, upDir); // 새로운 파일 저장
      usedcarVO.setFile2(file2); // 새로운 파일명 저장
      usedcarVO.setSize2(file2MF.getSize()); // 새로운 크기 저장
 
      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
        file1 = Tool.preview(upDir, file2, 120, 80); // thumb 이미지 생성
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------
 
    } else {
      file1 = oldVO.getFile1(); // 파일 업로드를하지 않는 경우
      file2 = oldVO.getFile2();
    }
    usedcarVO.setFile1(file1); 
    usedcarVO.setFile2(file2);
    // -------------------------------------------------------------------
 
    if (usedcarDAO.update(usedcarVO) == 1) {
      // 수정후 조회로 자동 이동
      mav.setViewName("redirect:/usedcar/read.do?u_no=" + usedcarVO.getU_no()
          + "&u_no=" + usedcarVO.getU_no()); // 확장자 명시
    } else {
      msgs.add("게시판 수정에 실패 하셨습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?u_no="
             + usedcarVO.getU_no() + "'\">목록</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
  /**
   * 삭제폼
   * @param u_no
   * @return
   */
  @RequestMapping(value = "/usedcar/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int u_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/delete"); // /webapp/member/delete.jsp
    mav.addObject("u_no", u_no);
    
    return mav;
  }
  
   /**
   * 삭제 처리
   * @param blogVO
   * @return
   */
  @RequestMapping(value = "/usedcar/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(UsedcarVO usedcarVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (usedcarDAO.delete(usedcarVO.getU_no()) == 1) {
      mav.setViewName("redirect:/usedcar/list.do?u_no=" + usedcarVO.getU_no());//확장자 명시
 
    } else {
      msgs.add("글 삭제에 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do?u_no="+usedcarVO.getU_no()+"'\">목록</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  /**
   *  별로 게시판 목록을 검색하여 출력합니다.
   * 
   * @param 
   *          전체 목록에서 가져올 게시판 번호
   * @param searchDTO 검색 정보         
   * @return 추출된 게시판 목록
   */
  @RequestMapping(value = "/usedcar/list2.do", 
                             method = RequestMethod.GET)
  public ModelAndView list2(
                                        SearchDTO searchDTO,
                                        HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/list");

    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    List<UsedcarVO> list = usedcarDAO.list2(hashMap); // 검색
    Iterator<UsedcarVO> iter = list.iterator();
    while (iter.hasNext() == true) { // 다음 요소 검사
      UsedcarVO vo = iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2())); // MB 단위 변환
    }
    mav.addObject("list", list);

    mav.addObject("root", request.getContextPath());
    mav.addObject("totalRecord", usedcarDAO.count(hashMap)); // 검색된 레코드 갯수
    
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
  @RequestMapping(value = "/usedcar/list.do", 
                             method = RequestMethod.GET)
  public ModelAndView list(
                                        SearchDTO searchDTO,
                                        HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/list");
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

    List<UsedcarVO> list = usedcarDAO.list(hashMap); // 검색
    Iterator<UsedcarVO> iter = list.iterator();
    while (iter.hasNext() == true) { // 다음 요소 검사
      UsedcarVO vo = iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2()));
    }
    mav.addObject("list", list);

    
    totalRecord = usedcarDAO.count(hashMap);
    mav.addObject("totalRecord", usedcarDAO.count(hashMap)); // 검색된 레코드 갯수
 
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