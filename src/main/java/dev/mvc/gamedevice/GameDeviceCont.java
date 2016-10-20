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
      msgs.add("��ǰ ��� �Ϸ��߽��ϴ�.");
      msgs.add("������ּż� �����մϴ�.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    } else {
      msgs.add("��ǰ ��Ͽ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

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
      msgs.add("��ǰ ���� ���濡 �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
    return mav;
  }
  /**
   * ������
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
   * ���ڵ� 1���� �����մϴ�.
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
      msgs.add("������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

      mav.addObject("msgs", msgs);
      mav.addObject("links", links);

    }

    return mav;
  }



}
