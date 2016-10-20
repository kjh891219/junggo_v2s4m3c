package dev.mvc.gamedevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.game.GameVO;

@Controller
public class GameDeviceCont {

  @Autowired
  @Qualifier("dev.mvc.gamedevice.GameDeviceDAO")
  private GameDeviceDAOInter gamedeviceDAO;

  public GameDeviceCont() {
    System.out.println("--> GameDeviceCont created");
  }

  @RequestMapping(value = "/gamedevice/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/create");
    return mav;
  }

  @RequestMapping(value = "/gamedevice/create.do", method = RequestMethod.POST)
  public ModelAndView create(GameDeviceVO gamedeviceVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (gamedeviceDAO.create(gamedeviceVO) == 1) {
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

  @RequestMapping(value = "/gamedevice/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/list");// /webapp/member/list.jsp
    mav.addObject("list", gamedeviceDAO.list());

    return mav;
  }

  @RequestMapping(value = "/gamedevice/read.do", method = RequestMethod.GET)
  public ModelAndView read(int gdno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/read");
    mav.addObject("gamedeviceVO", gamedeviceDAO.read(gdno));

    return mav;
  }

  @RequestMapping(value = "/gamedevice/update.do", method = RequestMethod.GET)
  public ModelAndView update(int gdno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/update"); // /webapp/blogcategory/update.jsp

    List<GameDeviceVO> gamedevice_list = gamedeviceDAO.list();
    mav.addObject("gamedevice_list", gamedevice_list);

    GameDeviceVO gamedeviceVO = gamedeviceDAO.read(gdno);
    mav.addObject("gamedeviceVO", gamedeviceVO);

    return mav;
  }
  
  @RequestMapping(value = "/gamedevice/update.do", method = RequestMethod.POST)
  public ModelAndView update(GameDeviceVO gamedeviceVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (gamedeviceDAO.update(gamedeviceVO) == 1) {
       mav.setViewName("redirect:/gamedevice/list.do");
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
  @RequestMapping(value = "/gamedevice/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int gdno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/delete"); // /webapp/member/delete.jsp
    mav.addObject("gdno", gdno);
 
    
    return mav;
  }
  
  /**
   * 레코드 1건을 삭제합니다.
   * 
   * @param codeno
   * @return
   */
  @RequestMapping(value = "/gamedevice/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(GameDeviceVO gamedeviceVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gamedevice/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (gamedeviceDAO.delete(gamedeviceVO.getGdno()) == 1) {
      mav.setViewName("redirect:/gamedevice/list.do");
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
