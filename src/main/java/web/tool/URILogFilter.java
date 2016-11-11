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
    // /WEB-INF/web.xml파일의 <init-param>태그의 값
    charSet = config.getInitParameter("charSet");
 
    System.out.println("────────────");
    System.out.println(" URI Logger start...");
    System.out.println("────────────");
  }
 
  // 요청이 오면 자동 실행
  public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
   
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    
    
    // ------------------------------------------------------------------------------
    // session의 사용 ★
    // ------------------------------------------------------------------------------
    // 새로운 세션을 생성하지않고 기존의 세션 객체를 반환
  
    HttpSession session = request.getSession(false);

    
    boolean login = true;
    if (session != null) {
      /*camera*/
      if (session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/camera/create.do")|| request.getRequestURI().equals("/junggo/camera/update.do")|| request.getRequestURI().equals("/junggo/camera/delete.do"))) {
        login = false;
      }
      /*usedcar*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/usedcar/create.do")|| request.getRequestURI().equals("/junggo/usedcar/update.do")|| request.getRequestURI().equals("/junggo/usedcar/delete.do"))){
        login = false;
      } /*carproduct*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/carproduct/create.do")|| request.getRequestURI().equals("/junggo/carproduct/update.do")|| request.getRequestURI().equals("/junggo/carproduct/delete.do"))){
        login = false;
      }/*cloth*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/cloth/create.do")|| request.getRequestURI().equals("/junggo/cloth/update.do")|| request.getRequestURI().equals("/junggo/cloth/delete.do"))){
        login = false;
      }/*cosmetic*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/cosmetic/create.do")|| request.getRequestURI().equals("/junggo/cosmetic/update.do")|| request.getRequestURI().equals("/junggo/cosmetic/delete.do"))){
        login = false;
      }/*product*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/product/create.do")|| request.getRequestURI().equals("/junggo/product/update.do")|| request.getRequestURI().equals("/junggo/product/delete.do"))){
        login = false;
      }/*mobile*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/mobile/create.do")|| request.getRequestURI().equals("/junggo/mobile/update.do")|| request.getRequestURI().equals("/junggo/mobile/delete.do"))){
        login = false;
      }/*game*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/game/create.do")|| request.getRequestURI().equals("/junggo/game/update.do")|| request.getRequestURI().equals("/junggo/game/delete.do"))){
        login = false;
      }/*gamedevice*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/gamedevice/create.do")|| request.getRequestURI().equals("/junggo/gamedevice/update.do")|| request.getRequestURI().equals("/junggo/gamedevice/delete.do"))){
        login = false;
      }/*computer*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/computer/create.do")|| request.getRequestURI().equals("/junggo/computer/update.do")|| request.getRequestURI().equals("/junggo/computer/delete.do"))){
        login = false;
      }/*music*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/music/create.do")|| request.getRequestURI().equals("/junggo/music/update.do")|| request.getRequestURI().equals("/junggo/music/delete.do"))){
        login = false;
      }/*art*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/art/create.do")|| request.getRequestURI().equals("/junggo/art/update.do")|| request.getRequestURI().equals("/junggo/art/delete.do"))){
        login = false;
      }/*book*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/book/create.do")|| request.getRequestURI().equals("/junggo/book/update.do")|| request.getRequestURI().equals("/junggo/book/delete.do"))){
        login = false;
      }/*living*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/living/create.do")|| request.getRequestURI().equals("/junggo/living/update.do")|| request.getRequestURI().equals("/junggo/living/delete.do"))){
        login = false;
      }/*sports*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/sports/create.do")|| request.getRequestURI().equals("/junggo/sports/update.do")|| request.getRequestURI().equals("/junggo/sports/delete.do"))){
        login = false;
      }/*notice*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/notice/create.do")|| request.getRequestURI().equals("/junggo/notice/update.do")|| request.getRequestURI().equals("/junggo/notice/delete.do"))){
        login = false;
      }/*reviews*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/reviews/create.do")|| request.getRequestURI().equals("/junggo/reviews/update.do")|| request.getRequestURI().equals("/junggo/reviews/delete.do"))){
        login = false;
      }/*cheat*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/cheat/create.do")|| request.getRequestURI().equals("/junggo/cheat/update.do")|| request.getRequestURI().equals("/junggo/cheat/delete.do"))){
        login = false;
      }/*member*/
      else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/member/list.do")){
        login = false;
      }else if(session.getAttribute("userid") != null && !session.getAttribute("userid").toString().equals("master") && request.getRequestURI().equals("/junggo/member/list.do")){
        login = false;
      }/*message*/
      else if(session.getAttribute("userid") == null && (request.getRequestURI().equals("/junggo/message/create.do")|| request.getRequestURI().equals("/junggo/message/list_msg.do")|| request.getRequestURI().equals("/junggo/message/delete.do"))){
        login = false;
      }else if(session.getAttribute("userid") == null && request.getRequestURI().equals("/junggo/message/list.do")){
        login = false;
      }else if(session.getAttribute("userid") != null && !session.getAttribute("userid").toString().equals("master") && request.getRequestURI().equals("/junggo/message/list.do")){
        login = false;
      }
      
    }
    
    if (login) {
      chain.doFilter(request, response);
    } else { // 손님이면 로그인 페이지로 이동
      
      System.out.println("잘못된 접근입니다.");    
      RequestDispatcher dispatcher = request.getRequestDispatcher("../error/error.jsp");
      dispatcher.forward(request, response);
    }

    // 한글 문자셋 변경
    request.setCharacterEncoding(charSet);
 
    // 요청 uri 추출 부분
    String uri = request.getRequestURI();
    System.out.println(uri);
  }
 
  public void destroy() {
 
  }
}