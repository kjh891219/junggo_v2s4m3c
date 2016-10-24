<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="web.tool.*" %>
<%
String root = request.getContextPath();
%>
<script>
  function message(){
    var url = './message/list.do?flag=recv';
    var win = window.open(url, '쪽지함', 'width=600px, height=700px');
    
    var x = (screen.width - 500) / 2;
    var y = (screen.height - 440) / 2;
    
    win.moveTo(x, y); // 화면 가운데로 이동
  }
</script>

<header>
   <DIV style='float:right;'>
            <ul class="member-list">
               <% if(session.getAttribute("userid") == null) { // 회원 로그인 여부 검사 %>
               <li class="login"><a class='menu_style'  href="#myModal" data-toggle="modal"><span></span>LOGIN</a></li>
               <li class="join">/member/create.do"><span>JOIN</li>
               <% } else { %>
               <li class="logout">/member/logout.do">${userid }님 로그아웃</li>
               <li class="mytm">/member/mypage.jsp"><span>마이 페이지</li>
               <li class="message"><A href='javascript: message();'><img src="<%=root %>/images/message.png"/></a></li>
               <% } %>   
               <li class="orderDelivery"><span>주문/배송</li>
               <li class="cart"><span>장바구니</li>
               <!--
               <li class="cs"><span>고객센터</li><!-- //활성화 시 class on 추가 -->
               <li class="cs"><span>고객센터</li><!-- //활성화 시 class on 추가 -->
               <%
                String act = (String) session.getAttribute("act");
                if ((Tool.checkNull(act)).equals("M")){
                %>
                  <li>/member/list.do'><span>관리자다</li>
                <%
                }else{ }
                %>
            </ul>
            <div style='clear:both;'></div>
      
   </DIV>
            <div style='clear:both;'></div>
   <jsp:include page="/menu/login.jsp" flush='false' />
</header>