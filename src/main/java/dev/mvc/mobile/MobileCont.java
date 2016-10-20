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
  @RequestMapping(value = "/mobile/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mobile/delete"); // /webapp/member/delete.jsp
    mav.addObject("mno", mno);
 
    
    return mav;
  }
  
  /**
   * ���ڵ� 1���� �����մϴ�.
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
