package dev.mvc.qna;
 
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

import dev.mvc.qna.QnADAOInter;
import dev.mvc.qna.QnAVO;
import dev.mvc.tmember.MemberDAOInter;
import dev.mvc.tmember.MemberVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;
 
@Controller
public class QnACont {
  @Autowired
  @Qualifier("dev.mvc.qna.QnADAO")
  private QnADAOInter qnaDAO;
  
  @Autowired
  @Qualifier("dev.mvc.tmember.MemberDAO")
  private MemberDAOInter memberDAO;
  
  
  /**
   * 전체 목록을 출력합니다.
   * @return
   */

  @RequestMapping(value = "/qna/list.do", 
                             method = RequestMethod.GET)
  public ModelAndView list(QnAVO qnavo, SearchDTO searchDTO,
        HttpServletRequest request) {
     
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/list"); // /webapp/code/list.jsp

    int totalRecord = 0;
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    
    
    int recordPerPage = 10;
    int beginOfPage = (searchDTO.getNowPage() -1) * 10;
    
    int startNum = beginOfPage + 1 ;
    int endNum = beginOfPage + recordPerPage;
      hashMap.put("startNum", startNum);
      hashMap.put("endNum", endNum);
      
    List<QnAVO> list = qnaDAO.list(hashMap);
    Iterator<QnAVO> iter = list.iterator();
    while (iter.hasNext() == true) {
       QnAVO vo = iter.next();
       int rownum = (qnavo.getRownum());
       qnavo.setRownum(rownum + 1);
       vo.setRownum(qnavo.getRownum());
       vo.setTitle(Tool.textLength(vo.getTitle(), 10));
       vo.setQdate(vo.getQdate());
    }
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    totalRecord = qnaDAO.count(hashMap);
    mav.addObject("totalRecord", qnaDAO.count(hashMap));
    
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
   * 아이디별 목록 출력합니다
   * @return
   */
/*
  @RequestMapping(value = "/qna/idlist.do", 
        method = RequestMethod.GET)
  public ModelAndView idlist(String userid) {
   
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/qna/idlist"); // /webapp/code/list.jsp
      userid = "chanmi";
      List<QnAVO> idlist = qnaDAO.idlist(userid);
      mav.addObject("idlist", qnaDAO.idlist(userid));
      
      return mav;
  }
*/  

  public QnACont(){
    System.out.println("--> QnACont created.");
  }
  
  @RequestMapping(value = "/qna/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView(); 
    mav.setViewName("/qna/create"); // /webapp/code/create.jsp
    
    MemberVO memberVO = memberDAO.read_userid("chanmi");
    System.out.println(memberVO.getEmail());
    System.out.println(memberVO.getNickname());
    System.out.println(memberVO.getPwd());
    mav.addObject("memberVO", memberVO);
    
    return mav;
  }
  
  @RequestMapping(value = "/qna/create.do", method = RequestMethod.POST)
  public ModelAndView create(QnAVO qnaVO,
        HttpServletRequest request, 
        HttpSession session) {
    System.out.println("--> create() POST called.");
    System.out.println("-->"+qnaVO.getContent());
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String file3 = "";
    long size1 = 0;
    long size2 = 0;
    long size3 = 0;
    String upDir = Tool.getRealPath(request, "/qna/storage");
    MultipartFile file1MF = qnaVO.getFile1MF();
    MultipartFile file2MF = qnaVO.getFile2MF();
    MultipartFile file3MF = qnaVO.getFile3MF();
    System.out.println("file2MF:"+file2MF);
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size1 = file1MF.getSize();
    size2 = file2MF.getSize();
    size3 = file3MF.getSize();
    
    if (size1 > 0) {
      file1 = Upload.saveFileSpring(file1MF, upDir);
      // -------------------------------------------------------------------
    }
    if (size2 > 0) {
    	file2 = Upload.saveFileSpring(file2MF, upDir);
    	// -------------------------------------------------------------------
    }
    if (size3 > 0) {
    	file3 = Upload.saveFileSpring(file3MF, upDir);
    	// -------------------------------------------------------------------
    }
    qnaVO.setFile1(file1); // 원본 이미지
    qnaVO.setFile2(file2); // 원본 이미지
    qnaVO.setFile3(file3); // 원본 이미지
    qnaVO.setSize1(size1); // 원본 이미지
    qnaVO.setSize2(size2); // 원본 이미지
    qnaVO.setSize3(size3); // 원본 이미지
    // -------------------------------------------------------------------
      
    qnaVO.setUserid("chanmi"); // 회원 연동시 변경
  /*    Integer itg = (Integer)(session.getAttribute("mno"));
    qnaVO.setMno(itg.intValue());*/
    
 
    if (qnaDAO.create(qnaVO) == 1) { 
      msgs.add("등록되었습니다.");
      links.add("<button type='button' onclick=\"location.href='./create.jsp'\">계속등록</button>");
    } else {
      msgs.add("등록을 실패했습니다.");
      msgs.add("다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
     
   @RequestMapping(value = "/qna/reply.do", 
           method = RequestMethod.GET)
   public ModelAndView reply(QnAVO qnavo) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/qna/reply"); // /webapp/qna/reply.jsp
   MemberVO memberVO = memberDAO.read_userid("chanmi");
   System.out.println(memberVO.getEmail());
   System.out.println(memberVO.getNickname());
   System.out.println(memberVO.getPwd());
   mav.addObject("memberVO", memberVO);
   mav.addObject("qnaVO", qnavo);
   
   return mav;
   }
   
   @RequestMapping(value = "/qna/reply.do", method = RequestMethod.POST)
   public ModelAndView reply(QnAVO qnaVO, HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/qna/message");
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // 파일 전송
   // -------------------------------------------------------------------
   String file1 = "";
   String file2 = "";
   String file3 = "";
   long size1 = 0;
   long size2 = 0;
   long size3 = 0;
   String upDir = Tool.getRealPath(request, "/qna/storage");
   MultipartFile file1MF = qnaVO.getFile1MF();
   MultipartFile file2MF = qnaVO.getFile2MF();
   MultipartFile file3MF = qnaVO.getFile3MF();
   System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   size1 = file1MF.getSize();
   size2 = file2MF.getSize();
   size3 = file3MF.getSize();
   
   if (size1 > 0) {
     file1 = Upload.saveFileSpring(file1MF, upDir);
     // -------------------------------------------------------------------
   }
   if (size2 > 0) {
     file2 = Upload.saveFileSpring(file2MF, upDir);
     // -------------------------------------------------------------------
   }
   if (size3 > 0) {
     file3 = Upload.saveFileSpring(file3MF, upDir);
     // -------------------------------------------------------------------
   }
   qnaVO.setFile1(file1); // 원본 이미지
   qnaVO.setFile2(file2); // 원본 이미지
   qnaVO.setFile3(file3); // 원본 이미지
   qnaVO.setSize1(size1); // 원본 이미지
   qnaVO.setSize2(size2); // 원본 이미지
   qnaVO.setSize3(size3); // 원본 이미지
   // -------------------------------------------------------------------
   
   qnaVO.setUserid("chanmi"); // 회원 연동시 변경
   // -------------------------------------------------------------------
   
   // ---------- 답변 관련 코드 시작 ---------- 
   QnAVO parentVO = qnaDAO.read(qnaVO.getQnano()); // 부모글 정보 추출
   
   qnaVO.setGrpno(parentVO.getGrpno()); // 그룹 번호
   qnaVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
   
   
   System.out.println("테스트테스트" + parentVO.getGrpno());
   
   
   qnaDAO.updateAnsnum(qnaVO); // 현재 등록된 답변 뒤로 +1 처리함.
   
   qnaVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
   qnaVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
   // ---------- 답변 관련 코드 종료 ---------- 
   
   if (qnaDAO.reply(qnaVO) == 1) {
   msgs.add("글을 등록했습니다.");
   links
   .add("<button type='button' onclick=\"location.href='./create.do'>계속 등록</button>");
   } else {
   msgs.add("글 등록에 실패했습니다.");
   msgs.add("다시 시도해주세요.");
   links
   .add("<button type='button' onclick=\"history.back()\">다시시도</button>");
   }
   
   links
   .add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
   links
   .add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);
   
   return mav;
   }
  
  /**
   * 글을 조회합니다
   * @param mno
   * @return
   */
  @RequestMapping(value = "/qna/read.do", method = RequestMethod.GET)
  public ModelAndView read(int qnano, 
                                SearchDTO searchDTO,
                                      HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/read");
    QnAVO qnaVO = qnaDAO.read(qnano);
    qnaDAO.increaseCnt(qnaVO.getQnano());
    mav.addObject("qnaVO", qnaVO); 
    
    String content = qnaVO.getContent();
    content = Tool.convertChar(content);
    qnaVO.setContent(content);
    mav.addObject("qnaVO",qnaVO);

    // 게시판에 대한 정보 파악
    mav.addObject("searchDTO", searchDTO);
 
    return mav;
  }
  
  
  /**
   * 삭제폼
   * @param qnano
   * @return
   */
  @RequestMapping(value = "/qna/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int qnano) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/delete"); // /webapp/member/delete.jsp
    mav.addObject("qnano", qnano);
    
    return mav;
  }
  
  /**
   * 삭제 처리
   * @param qnaVO
   * @return
   */
  @RequestMapping(value = "/qna/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(QnAVO qnaVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (qnaDAO.delete(qnaVO.getQnano()) == 1) {
       mav.setViewName("redirect:/qna/list.do");//확장자 명시
 
    } else {
      msgs.add("글 삭제에 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do\">목록</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  /**
   * 수정폼
   * @param qnano
   * @return
   */
  @RequestMapping(value="/qna/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int qnano){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/update"); 
    mav.addObject("qnaVO", qnaDAO.read(qnano)); 
 
    return mav;
 
  }
   
  /**
   * 글과 파일을 수정 처리
   * 
   * @param qnaVO
   * @return
   */
  @RequestMapping(value = "/qna/update.do", method = RequestMethod.POST)
  public ModelAndView update2(QnAVO qnaVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String file3 = "";
    long size1 = 0;
    long size2 = 0;
    long size3 = 0;
    
    String upDir = Tool.getRealPath(request, "/qna/storage");
    MultipartFile file1MF = qnaVO.getFile1MF();
    MultipartFile file2MF = qnaVO.getFile2MF();
    MultipartFile file3MF = qnaVO.getFile3MF();
    System.out.println("file2MF:"+file2MF);
    QnAVO oldVO = qnaDAO.read(qnaVO.getQnano());
    
    //System.out.println("file2MF.getSize(): " + file2MF.getSize());
    size1 = file1MF.getSize();
    size2 = file2MF.getSize();
    size3 = file3MF.getSize();
    if (size1 > 0) {
      file1 = Upload.saveFileSpring(file1MF, upDir);
      qnaVO.setFile1(file1); // 새로운 전송된 파일명 저장
      qnaVO.setSize1(file1MF.getSize()); //새로운 크기 저장 
    }else{
       file1 = oldVO.getFile1(); // 파일 업로드를 하지 않는 경우
    }
    if (size2 > 0) {
    	file2 = Upload.saveFileSpring(file2MF, upDir);
    	qnaVO.setFile2(file2); // 새로운 전송된 파일명 저장
    	qnaVO.setSize2(file2MF.getSize()); //새로운 크기 저장 
    }else{
    	file2 = oldVO.getFile2(); // 파일 업로드를 하지 않는 경우
    }
    if (size3 > 0) {
    	file3 = Upload.saveFileSpring(file3MF, upDir);
    	qnaVO.setFile3(file3); // 새로운 전송된 파일명 저장
    	qnaVO.setSize3(file3MF.getSize()); //새로운 크기 저장 
    }else{
    	file3 = oldVO.getFile3(); // 파일 업로드를 하지 않는 경우
    }
    
       qnaVO.setFile1(file1); // 원본 이미지
       qnaVO.setFile2(file2); // 원본 이미지
       qnaVO.setFile3(file3); // 원본 이미지
       qnaVO.setSize1(size1); // 원본 이미지 사이즈
       qnaVO.setSize2(size2); // 원본 이미지 사이즈
       qnaVO.setSize3(size3); // 원본 이미지 사이즈
    // -------------------------------------------------------------------
       
       qnaVO.setUserid("chanmi"); // 회원 연동시 변경
       
    // -------------------------------------------------------------------
       
    if (qnaDAO.update(qnaVO) == 1) {
      // 수정후 조회로 자동 이동
      mav.setViewName("redirect:/qna/read.do?qnano=" + qnaVO.getQnano()); // 확장자 명시
    } else {
      msgs.add("게시판 수정에 실패 하셨습니다.");
      links
          .add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      links
          .add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
}
 