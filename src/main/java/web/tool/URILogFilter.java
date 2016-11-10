package web.tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class URILogFilter implements Filter{
  private String charSet = null;
  
  @Override
  public void init(FilterConfig config) throws ServletException {
    // /WEB-INF/web.xml������ <init-param>�±��� ��
    charSet = config.getInitParameter("charSet");
 
    System.out.println("������������������������");
    System.out.println(" URI Logger start...");
    System.out.println("������������������������");
  }
 
  // ��û�� ���� �ڵ� ����
  public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
   
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    
    
    // ------------------------------------------------------------------------------
    // session�� ��� ��
    // ------------------------------------------------------------------------------
    // ���ο� ������ ���������ʰ� ������ ���� ��ü�� ��ȯ
  
    HttpSession session = request.getSession(false);

    
    boolean login = true;
    if (session != null) {
      if (session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/camera/create.do")) {
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/usedcar/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/carproduct/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/cloth/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/cosmetic/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/product/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/mobile/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/game/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/gamedevice/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/computer/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/music/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/art/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/book/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/living/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/sports/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/notice/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/reviews/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/cheat/create.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/member/list.do")){
        login = false;
      }else if(session.getAttribute("userid") != null && !session.getAttribute("userid").toString().equals("master") && request.getRequestURI().equals("/junggo/member/list.do")){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/message/list.do")){
        login = false;
      }
      
    }
    
    if (login) {
      chain.doFilter(request, response);
    } else { // �մ��̸� �α��� �������� �̵�
      
      System.out.println("�߸��� �����Դϴ�.");    
      RequestDispatcher dispatcher = request.getRequestDispatcher("../error/error.jsp");
      dispatcher.forward(request, response);
    }

    // �ѱ� ���ڼ� ����
    request.setCharacterEncoding(charSet);
 
    // ��û uri ���� �κ�
    String uri = request.getRequestURI();
    System.out.println(uri);
  }
 
  public void destroy() {
 
  }
}