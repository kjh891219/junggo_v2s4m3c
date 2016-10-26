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

import dev.mvc.cheat.CheatVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class CheatCont {
  @Autowired
  @Qualifier("dev.mvc.cheat.CheatDAO")
  private CheatDAOInter cheatDAO;

  @Autowired
  @Qualifier("dev.mvc.cheat.CReplyDAO")
  private CReplyDAOInter cReplyDAO;

  public CheatCont() {
    System.out.println("--> CheatCont created.");
  }

  /**
   * 1건 조회
   * 
   * @param ctno
   * @return
   */
  @RequestMapping(value = "/cheat/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno) {
    ModelAndView mav = new ModelAndView();
    cheatDAO.setCnt(ctno); // 조회수 증가

    mav.setViewName("/cheat/read");
    mav.addObject("cheatVO", cheatDAO.read(ctno));
    
    mav.addObject("cReplylist", cReplyDAO.listReply(ctno));
    return mav;
  }

  /**
   * 리스트 목록을 검색+페이징+답변을 적용하여 출력합니다.
   * 
   * @param searchDTO
   *          검색 정보
   * @return 추출된 게시판 목록
   */
  @RequestMapping(value = "/cheat/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/list");
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

    String paging = new Paging().paging5(totalRecord, searchDTO.getNowPage(), recordPerPage, searchDTO.getCol(),
        searchDTO.getWord());
    mav.addObject("paging", paging);
    return mav;
  }

  
  /**
   * 
   * @param opentype
   *          : U(수정), R(등록)
   * @return
   */
  @RequestMapping(value = "/cheat/create.do", method = RequestMethod.GET)
  public ModelAndView create(int ctno, String opentype) {
    System.out.println("--> create() GET called");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/create"); // /webapp/cheat/create.jsp
    if (opentype.equals("U")) {
      System.out.println("--> create() GET called [U].");
      mav.addObject("cheatVO", cheatDAO.read(ctno));
    }

    mav.addObject("opentype", opentype);
    return mav;
  }

  @RequestMapping(value = "/cheat/create.do", method = RequestMethod.POST)
  public ModelAndView create(CheatVO cheatVO, HttpServletRequest request, HttpSession session, String opentype) {
    System.out.println("--> create() POST called.");

    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp

    /* 진입 모드 Log */
    if (opentype.equals("U")) {
      System.out.println("--> create() POST 수정 모드.");
    } else if (opentype.equals("R")) {
      System.out.println("--> create() POST 등록 모드.");
    }

    /* 수정인 경우 비밀번호 체크 */
    if (opentype.equals("U")) {
      System.out.println("--> create() POST 수정 모드.");

      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("ctno", cheatVO.getCtno());
      hashMap.put("passwd", cheatVO.getPasswd());

      if (cheatDAO.passwordCheck(hashMap) == 0) {
        mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
        msgs.add("비밀번호가 틀렸습니다.");
        links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
        links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
        mav.addObject("msgs", msgs);
        mav.addObject("links", links);

      }
    }

    // -------------------------------------------------------------------
    // 파일 전송 관련
    // -------------------------------------------------------------------
    
    String thumb = "";
    String file1 = "";
    String file2 = "";
    String file3 = "";
    String file4 = "";
    String file5 = "";
     
    String upDir = Tool.getRealPath(request, "/cheat/storage");
    MultipartFile file1MF = cheatVO.getFile1MF();
    MultipartFile file2MF = cheatVO.getFile2MF();
    MultipartFile file3MF = cheatVO.getFile3MF();
    MultipartFile file4MF = cheatVO.getFile4MF();
    MultipartFile file5MF = cheatVO.getFile5MF();
    
    if (opentype.equals("U")) {
      
      CheatVO oldVO = cheatDAO.read(cheatVO.getCtno());
      /*파일 저장 1*/ 
      if (file1MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
        Tool.deleteFile(upDir, oldVO.getFile1()); // 기존 등록된 파일 삭제
        file1 = Upload.saveFileSpring(file1MF, upDir); // 새로운 파일 저장
        cheatVO.setFile1(file1); // 새로운 파일명 저장
        cheatVO.setSize1(file1MF.getSize()); // 새로운 크기 저장

        // -------------------------------------------------------------------
        // Thumb 파일 생성
        // -------------------------------------------------------------------
        if (Tool.isImage(file1)) { // 이미지인지 검사
          Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
          thumb = Tool.preview(upDir, file1, 120, 80); // thumb 이미지 생성
        } else {
          thumb = "";
        }
        // -------------------------------------------------------------------

      } else {
        thumb = oldVO.getThumb(); // 파일 업로드를하지 않는 경우
        file1 = oldVO.getFile1();
      }
      cheatVO.setThumb(thumb);
      cheatVO.setFile1(file1);
      
      /*파일 저장 file1MF*/ 
      if (file1MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
        Tool.deleteFile(upDir, oldVO.getFile1()); // 기존 등록된 파일 삭제
        file1 = Upload.saveFileSpring(file1MF, upDir); // 새로운 파일 저장
        cheatVO.setFile1(file1); // 새로운 파일명 저장
        cheatVO.setSize1(file1MF.getSize()); // 새로운 크기 저장

        // -------------------------------------------------------------------
        // Thumb 파일 생성
        // -------------------------------------------------------------------
        if (Tool.isImage(file1)) { // 이미지인지 검사
          Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
          thumb = Tool.preview(upDir, file1, 120, 80); // thumb 이미지 생성
        } else {
          thumb = "";
        }
        // -------------------------------------------------------------------

      } else {
        thumb = oldVO.getThumb(); // 파일 업로드를하지 않는 경우
        file1 = oldVO.getFile1();
      }
      cheatVO.setThumb(thumb);
      cheatVO.setFile1(file1);
      
      /*파일 저장 file2MF */  
      if (file2MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
        Tool.deleteFile(upDir, oldVO.getFile2());  // 기존 등록된 파일 삭제
        file2 = Upload.saveFileSpring(file2MF, upDir); // 새로운 파일 저장
        cheatVO.setFile2(file2);  // 새로운 파일명 저장
        cheatVO.setSize2(file2MF.getSize()); // 새로운 크기 저장
      
      } else {
        file2 = oldVO.getFile2();
      }
     
      cheatVO.setFile2(file2);
      
      /*파일 저장 file3MF */  
      if (file3MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
        Tool.deleteFile(upDir, oldVO.getFile3());  // 기존 등록된 파일 삭제
        file3 = Upload.saveFileSpring(file3MF, upDir); // 새로운 파일 저장
        cheatVO.setFile3(file3);  // 새로운 파일명 저장
        cheatVO.setSize3(file3MF.getSize()); // 새로운 크기 저장
      
      } else {
        file3 = oldVO.getFile3();
      }
     
      cheatVO.setFile3(file3);
      
      /*파일 저장 file4MF */  
      if (file4MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
        Tool.deleteFile(upDir, oldVO.getFile4());  // 기존 등록된 파일 삭제
        file4 = Upload.saveFileSpring(file4MF, upDir); // 새로운 파일 저장
        cheatVO.setFile4(file4);  // 새로운 파일명 저장
        cheatVO.setSize4(file4MF.getSize()); // 새로운 크기 저장
      
      } else {
        file4 = oldVO.getFile4();
      }
     
      cheatVO.setFile4(file4);
      
      
      /*파일 저장 file5MF */  
      if (file5MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
        Tool.deleteFile(upDir, oldVO.getFile5());  // 기존 등록된 파일 삭제
        file5 = Upload.saveFileSpring(file5MF, upDir); // 새로운 파일 저장
        cheatVO.setFile5(file5);  // 새로운 파일명 저장
        cheatVO.setSize5(file5MF.getSize()); // 새로운 크기 저장
      
      } else {
        file5 = oldVO.getFile5();
      }
     
      cheatVO.setFile5(file5);
    } else {
      /*파일 저장 file1MF */  
      if (file1MF.getSize() > 0) {
        file1 = Upload.saveFileSpring(file1MF, upDir);
        cheatVO.setFile1(file1); // 전송된 파일명 저장
        cheatVO.setSize1(file1MF.getSize());

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
      
      cheatVO.setThumb(thumb); // Thumb 이미지
      cheatVO.setFile1(file1); // 원본 이미지
      
      /*파일 저장 file2MF */  
      if (file2MF.getSize() > 0) {
        file2 = Upload.saveFileSpring(file2MF, upDir);
        cheatVO.setFile2(file2); // 전송된 파일명 저장
        cheatVO.setSize2(file2MF.getSize());
      }
 
      cheatVO.setFile2(file2); // 원본 이미지
      
      /*파일 저장 file3MF */  
      if (file3MF.getSize() > 0) {
        file3 = Upload.saveFileSpring(file3MF, upDir);
        cheatVO.setFile3(file3); // 전송된 파일명 저장
        cheatVO.setSize3(file3MF.getSize());
      }
 
      cheatVO.setFile3(file3); // 원본 이미지

      /*파일 저장 file4MF */  
      if (file4MF.getSize() > 0) {
        file4 = Upload.saveFileSpring(file4MF, upDir);
        cheatVO.setFile4(file4); // 전송된 파일명 저장
        cheatVO.setSize4(file4MF.getSize());
      }
 
      cheatVO.setFile4(file4); // 원본 이미지

      /*파일 저장 file5MF */  
      if (file5MF.getSize() > 0) {
        file5 = Upload.saveFileSpring(file5MF, upDir);
        cheatVO.setFile5(file5); // 전송된 파일명 저장
        cheatVO.setSize5(file5MF.getSize());
      }
 
      cheatVO.setFile5(file5); // 원본 이미지

    }
    
    int result = 0;
    if (opentype.equals("U")) {
      result = cheatDAO.update(cheatVO);
    } else {
      result = cheatDAO.create(cheatVO);
    }

    if (result == 1) {
      msgs.add("저장을 완료했습니다.");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
    } else {
      mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp
      msgs.add("저장을 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

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
    if (cheatDAO.passwordCheck(hashMap) == 0) {
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

  /**
   * 답글 등록
   * 
   * @param cReplyVO
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value = "/cheat/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(CReplyVO cReplyVO, HttpServletRequest request, HttpSession session) {
    System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/message"); // /webapp/cheat/message.jsp

    // ---------- 답변 관련 코드 시작 ----------
    
    CReplyVO parentVO = cReplyDAO.read(cReplyVO.getRno()); // 부모글 정보 추출
    
    if (parentVO == null) {
    
      int maxgrp = cReplyDAO.getMaxgrpno(cReplyVO.getCtno()) + 1;
      cReplyVO.setGrpno(maxgrp); // 그룹 번호
      cReplyVO.setIndent(1);
      cReplyVO.setAnsnum(1); // 답변 순서
    } else {
      cReplyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호
      cReplyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서

      cReplyDAO.updateAnsnum(cReplyVO); // 현재 등록된 답변 뒤로 +1 처리함.

      cReplyVO.setIndent(parentVO.getIndent() + 1); // 답변 차수 증가
      cReplyVO.setAnsnum(parentVO.getAnsnum() + 1); // 부모 바로 아래 등록
    }
    // ---------- 답변 관련 코드 종료 ----------

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (cReplyDAO.reply(cReplyVO) == 1) {
      mav.setViewName("redirect:/cheat/read.do?ctno=" + cReplyVO.getCtno());
    } else {
      msgs.add("답글 등록을 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /**
   * 댓글 삭제 처리
   * 
   * @param rno
   * @return
   */
  @RequestMapping(value = "/cheat/delete_reply.do", method = RequestMethod.GET)
  public ModelAndView delete_reply(int rno, int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat/message");
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    if (cReplyDAO.delete_reply(rno) == 1) {
      mav.setViewName("redirect:/cheat/read.do?ctno=" + ctno);
      
    } else {
      msgs.add("답글 삭제에 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
}
