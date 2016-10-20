package dev.mvc.mobile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MobileCont {

  @Autowired
  @Qualifier("dev.mvc.mobile.MobileDAO")
  private MobileDAOInter mobileDAO;
  
  @RequestMapping(value = "/mobile/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/create");
    return mav;
  }
  
  @RequestMapping(value = "/mobile/create.do", method = RequestMethod.POST)
  public ModelAndView create(MobileVO mobileVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

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
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/list");// /webapp/member/list.jsp
    mav.addObject("list", mobileDAO.list());

    return mav;
  }

  @RequestMapping(value = "/mobile/read.do", method = RequestMethod.GET)
  public ModelAndView read(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/read");
    mav.addObject("mobileVO", mobileDAO.read(mno));

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
  public ModelAndView update(MobileVO mobileVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

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
}
