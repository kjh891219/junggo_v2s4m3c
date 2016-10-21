package dev.mvc.carproduct;
 
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

import web.tool.Tool;
import web.tool.Upload;

 
@Controller
public class CarproductCont {
  @Autowired
  @Qualifier("dev.mvc.carproduct.CarproductDAO")
  private CarproductDAOInter carproductDAO;
  
  public CarproductCont(){
    System.out.println("--> CarproductCont created.");
  }
  
  /**
   * 레코드 등록
   * @param
   * @return
   */
 @RequestMapping(value = "/carproduct/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create() {
  // System.out.println("--> create() GET called.");
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/carproduct/create"); // /webapp/blog/create.jsp
  return mav;
  }
 
 
 /**
  * 등록 처리
  * @param carproductVO
  * @param request
  * @param session
  * @return
  */
 @RequestMapping(value = "/carproduct/create.do", 
                            method = RequestMethod.POST)
 public ModelAndView create(CarproductVO carproductVO, 
                                          HttpServletRequest request, 
                                          HttpSession session) {
   // System.out.println("--> create() POST called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/carproduct/message");
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // 파일 전송
   // -------------------------------------------------------------------
   String file1 = "";
   String file2 = "";
   String upDir = Tool.getRealPath(request, "/carproduct/storage");
   MultipartFile file2MF = carproductVO.getFile2MF();
   System.out.println("file2MF:"+file2MF);
   
   //System.out.println("file2MF.getSize(): " + file2MF.getSize());
   if (file2MF.getSize() > 0) {
     file2 = Upload.saveFileSpring(file2MF, upDir);
     carproductVO.setFile2(file2); // 전송된 파일명 저장
     carproductVO.setSize2(file2MF.getSize());

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
   carproductVO.setFile1(file1); // Thumb 이미지
   carproductVO.setFile2(file2); // 원본 이미지
   // -------------------------------------------------------------------
   
   //usedcarVO.setU_no(4); // 회원 연동시 변경
   // Integer itg = (Integer)(session.getAttribute("mno"));
   // blogVO.setMno(itg.intValue());
    
   if (carproductDAO.create(carproductVO) == 1) {
     msgs.add("글을 등록했습니다.");
     links.add("<button type='button' onclick=\"location.href='./create.do?p_no="
             + carproductVO.getP_no() + "'\">계속 등록</button>");
   } else {
     msgs.add("글 등록에 실패했습니다.");
     msgs.add("다시 시도해주세요.");
     links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
   }

   links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
   links.add("<button type='button' onclick=\"location.href='./list.do?p_no="
           + carproductVO.getP_no() + "'\">목록</button>");
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);

   return mav;
 }

 /**
  * 전체 목록을 출력합니다.
  * 
  * @return
  */
@RequestMapping(value = "/carproduct/list.do", method = RequestMethod.GET)
 public ModelAndView list() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/carproduct/list"); //  /webapp/member/list.jsp
   
  List<CarproductVO> list = carproductDAO.list();
   Iterator<CarproductVO> iter = list.iterator();
   while(iter.hasNext() == true){  // 다음 요소 검사
     CarproductVO vo = iter.next();  // 요소 추출
     vo.setTitle(Tool.textLength(vo.getTitle(), 10)); // 문자열 10자 분리
     vo.setWdate(vo.getWdate().substring(0, 10));  // 년월일
     vo.setFile1(Tool.textLength(vo.getFile1(), 10));
     vo.setFile2(Tool.textLength(vo.getFile2(), 10));
     
   }
      
   mav.addObject("list", list);

   return mav;
 }
 
/**
 * 한건의 레코드 조회
 * @param p_no
 * @return
 */
@RequestMapping(value = "/carproduct/read.do", method = RequestMethod.GET)
public ModelAndView read(int p_no) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/carproduct/read");
 // carproductDAO.increaseCnt(p_no);
  mav.addObject("carproductVO", carproductDAO.read(p_no));
  
  return mav;
}
   


  
}