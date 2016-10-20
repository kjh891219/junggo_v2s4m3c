package dev.mvc.cheat;

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

import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class CheatCont {
  @Autowired
  @Qualifier("dev.mvc.cheat.CheatDAO")
  private CheatDAOInter cheatDAO;
  
  public CheatCont(){
    System.out.println("--> CheatCont created.");
  }
  
  @RequestMapping(value = "/cheat/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called."); 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/create"); // /webapp/cheat/create.jsp

    return mav;
  }
  
  @RequestMapping(value = "/cheat/create.do",  method = RequestMethod.POST)
  public ModelAndView create(CheatVO cheatVO, HttpServletRequest request, HttpSession session) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp

    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String upDir = Tool.getRealPath(request, "/cheat/storage");
    System.out.println(upDir);
    MultipartFile file2MF = cheatVO.getFile2MF();

    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      cheatVO.setFile2(file2); // 전송된 파일명 저장
      cheatVO.setSize2(file2MF.getSize());

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
    cheatVO.setFile1(file1); // Thumb 이미지
    cheatVO.setFile2(file2); // 원본 이미지
    // -------------------------------------------------------------------
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (cheatDAO.create(cheatVO) == 1) {
      msgs.add("허위상품 신고가 등록되었습니다.");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
    } else {
      msgs.add("허위상품 신고를 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
 
  /**
   * 1건 조회
   * @param ctno
   * @param opentype : U(수정), R(상세보기) 
   * @return
   */
  @RequestMapping(value = "/cheat/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno, String opentype) {
    ModelAndView mav = new ModelAndView();
    if(opentype.equals("R")){
      cheatDAO.setCnt(ctno); //조회수 증가
    }
    
    mav.setViewName("/cheat/read");
    mav.addObject("cheatVO", cheatDAO.read(ctno));
    mav.addObject("opentype", opentype);
    return mav;
  }
  
  /**
  * 리스트 목록을 검색+페이징+답변을
  * 적용하여 출력합니다.
  * @param searchDTO 검색 정보         
  * @return 추출된 게시판 목록
  */
 @RequestMapping(value = "/cheat/list.do", 
                            method = RequestMethod.GET)
 public ModelAndView list(SearchDTO searchDTO,
                                       HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/cheat/list");
   int totalRecord = 0;
   
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("col", searchDTO.getCol());
   hashMap.put("word", searchDTO.getWord());
   System.out.println("PAGE:" + searchDTO.getNowPage() );
    
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

   List<CheatVO> list = cheatDAO.list(hashMap); // 검색
   Iterator<CheatVO> iter = list.iterator();
   while (iter.hasNext() == true) { // 다음 요소 검사
     CheatVO vo = iter.next(); // 요소 추출
     vo.setTitle(Tool.textLength(vo.getTitle(), 20));
     vo.setWdate(vo.getWdate().substring(0, 10));
   }
   mav.addObject("list", list);
   mav.addObject("root", request.getContextPath());
   
   totalRecord = cheatDAO.count(hashMap);
   mav.addObject("totalRecord", cheatDAO.count(hashMap)); // 검색된 레코드 갯수
   
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
  * 글과 파일을 수정 처리
  * 
  * @param cheatVO
  * @return
  */
 @RequestMapping(value = "/cheat/update.do", method = RequestMethod.POST)
 public ModelAndView update(CheatVO cheatVO, HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();
   
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("ctno", cheatVO.getCtno());
   hashMap.put("passwd", cheatVO.getPasswd());
   
   if (cheatDAO.passwordCheck(hashMap) == 0)
   {
     mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
     msgs.add("비밀번호가 틀렸습니다.");
     links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
     links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
     mav.addObject("msgs", msgs);
     mav.addObject("links", links);
     return mav;
   }
   // -------------------------------------------------------------------
   // 파일 전송 관련
   // -------------------------------------------------------------------
   String file1 = "";
   String file2 = "";

   String upDir = Tool.getRealPath(request, "/cheat/storage");
   // <input type="file" name='file2MF' id='file2MF' size='40' >
   MultipartFile file2MF = cheatVO.getFile2MF();
   CheatVO oldVO = cheatDAO.read(cheatVO.getCtno());

   if (file2MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
     Tool.deleteFile(upDir, oldVO.getFile2()); // 기존 등록된 파일 삭제
     file2 = Upload.saveFileSpring(file2MF, upDir); // 새로운 파일 저장
     cheatVO.setFile2(file2); // 새로운 파일명 저장
     cheatVO.setSize2(file2MF.getSize()); // 새로운 크기 저장

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
   cheatVO.setFile1(file1);
   cheatVO.setFile2(file2);
   // -------------------------------------------------------------------

   if (cheatDAO.update(cheatVO) == 1) {
     // 수정후 조회로 자동 이동
     mav.setViewName(
         "redirect:/cheat/read.do?ctno=" + cheatVO.getCtno());
   } else {
     mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
     msgs.add("게시판 수정에 실패 하셨습니다.");
     links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
     links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
     mav.addObject("msgs", msgs);
     mav.addObject("links", links);
   }
   return mav;
 }

 /**
  * 삭제폼
  * 
  * @param ctno
  * @return
  */
 @RequestMapping(value = "/cheat/delete.do", method = RequestMethod.GET)
 public ModelAndView delete(int ctno) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/cheat/delete"); // /webapp/cheat/delete.jsp
   mav.addObject("ctno", ctno);
   
   return mav;
 }

 /**
  * 삭제 처리
  * 
  * @param cheatVO
  * @return
  */
 @RequestMapping(value = "/cheat/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(int ctno, String passwd) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/cheat/message");

   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("ctno", ctno);
   hashMap.put("passwd", passwd);
   if (cheatDAO.passwordCheck(hashMap) == 0)
   {
     msgs.add("비밀번호가 틀렸습니다.");
     links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
     links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
     mav.addObject("msgs", msgs);
     mav.addObject("links", links);
     return mav;
   }
   
   if (cheatDAO.delete(ctno) == 1) {
     mav.setViewName("redirect:/cheat/list.do");
   } else {
     msgs.add("글 삭제에 실패했습니다.");
     links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
     links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
   }
   
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);

   return mav;
 }

}
