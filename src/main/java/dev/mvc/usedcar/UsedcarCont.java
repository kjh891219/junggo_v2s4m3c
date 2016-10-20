package dev.mvc.usedcar;
 
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.usedcar.UsedcarVO;

 
@Controller
public class UsedcarCont {
  @Autowired
  @Qualifier("dev.mvc.usedcar.UsedcarDAO")
  private UsedcarDAOInter usedcarDAO;
  
  public UsedcarCont(){
    System.out.println("--> UsedcarCont created.");
  }
  
  @RequestMapping(value = "/usedcar/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/create"); // member�� create.do�� ���� ��� �̵� -> /webapp/member/create.jsp
 
    return mav;
  }
 
  @RequestMapping(value = "/usedcar/create.do", method = RequestMethod.POST)
  public ModelAndView create(UsedcarVO usedcarVO) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar/message"); // /webapp/member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (usedcarDAO.create(usedcarVO) == 1) {
      msgs.add("�߰��� ����� �Ϸ�Ǿ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    } else {
      msgs.add("�߰��� ��Ͽ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  /**
   * ��ü ����� ����մϴ�.
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
  
  @RequestMapping(value = "/code/update.do", method = RequestMethod.POST)
  public ModelAndView update(UsedcarVO usedcarVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/code/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (usedcarDAO.update(usedcarVO) == 1) {
      mav.setViewName("redirect:/usedcar/list.do");
    } else {
      msgs.add("�Ǹ� ���� ���濡 �����߽��ϴ�.");
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