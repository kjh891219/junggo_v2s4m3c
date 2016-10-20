package dev.mvc.usedcar;
 
import java.util.ArrayList;
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
  @RequestMapping(value = "/usedcar/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/list"); //  /webapp/member/list.jsp
    mav.addObject("list", usedcarDAO.list());
 
    return mav;
  }
  
  @RequestMapping(value = "/usedcar/read.do", method = RequestMethod.GET)
  public ModelAndView read(int u_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/read");
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
      // -------------------------------------------------------------------
    }
    usedcarVO.setFile1(file1); // Thumb 이미지
    usedcarVO.setFile2(file2); // 원본 이미지
    // -------------------------------------------------------------------
    
    //usedcarVO.setU_no(4); // 회원 연동시 변경
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
     
    if (usedcarDAO.create(usedcarVO) == 1) {
      msgs.add("글을 등록했습니다.");
      links.add("<button type='button' onclick=\"location.href='./create.do?blogcategoryno="
              + usedcarVO.getU_no() + "'\">계속 등록</button>");
    } else {
      msgs.add("글 등록에 실패했습니다.");
      msgs.add("다시 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do?blogcategoryno="
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
 
    String upDir = Tool.getRealPath(request, "/blog/storage");
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
      links.add("<button type='button' onclick=\"location.href='./list.do?blogcategoryno="
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
   * 글을 조회합니다
   * @param u_no
   * @return
   */
/*  @RequestMapping(value = "/usedcar/read.do", 
                             method = RequestMethod.GET)
  public ModelAndView read(int u_no, SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/read");
    UsedcarVO usedcarVO = usedcarDAO.read(u_no);
    
   // String content = usedcarVO.getContent();
    //content = Tool.convertChar(content); // 특수 문자 처리
    //usedcarVO.setContent(content);
    
    mav.addObject("usedcarVO", usedcarVO);
    
    return mav;
  }*/
  
}