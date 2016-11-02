<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="web.tool.*" %>
<%
String root = request.getContextPath();



String url_address = Tool.checkNull(request.getParameter("url_address")); // 로그인 후 이동할 주소

System.out.println("--> 3) login_ck_form.jsp: url_address: " + url_address); 
 
Cookie[] cookies = request.getCookies();
Cookie cookie = null;
 
String ck_userid = ""; // userid 저장 변수
String ck_userid_save = ""; // userid 저장 여부를 체크하는 변수
String ck_pwd = ""; // pwd 저장 변수
String ck_pwd_save = ""; // pwd 저장 여부를 체크하는 변수
 
for (int i=0; i < cookies.length; i++){
  cookie = cookies[i];
  
  if (cookie.getName().equals("ck_userid")){
    ck_userid = cookie.getValue();         // test1@mail.com
  }else if(cookie.getName().equals("ck_userid_save")){
    ck_userid_save = cookie.getValue();  // Y, N
  }else if (cookie.getName().equals("ck_pwd")){
    ck_pwd = cookie.getValue();         // test1@mail.com
  }else if(cookie.getName().equals("ck_pwd_save")){
    ck_pwd_save = cookie.getValue();  // Y, N
  }
}



%>
  
<header>
   <DIV id="member">
   <DIV id="member_b" > 
            <ul class="member-list" style='float:right;'>
               <% if(session.getAttribute("userid") == null) { // 회원 로그인 여부 검사 %>
               <li class="login"><a class='menu_style'  href="#myModal" data-toggle="modal"><span></span>LOGIN</a></li>
               <li class="join"><a href="<%=root %>/member/create.do" class='menu_style'><span></span>JOIN</a></li>
               <% } else { %>
               <li class="logout"><a href="<%=root %>/member/logout.do" class='menu_style'>${userid }님 로그아웃</a></li>
               <li class="mytm"><a href="<%=root %>/member/mypage.jsp" class='menu_style'><span></span>마이 페이지</a></li>
               <li class="message"><A href='javascript: message();'><img src="<%=root %>/images/message.png"/></a></li>
               <% } %>   
               <li class="orderDelivery"><a href="#" class='menu_style'><span></span>주문/배송</a></li>
               <li class="cart"><a href="#" class='menu_style'><span></span>장바구니</a></li>
               <!--
               <li class="cs"><a href="#none" class="on"><span></span>고객센터</a></li><!-- //활성화 시 class on 추가 -->
               <li class="cs"><a class='menu_style'  id="downs">커뮤니티<img id="down_img" style="width:20px;"src="./menu/images/drop_down.png"></a>
                  <div id="down_b" style="display:none; position:absolute; margin-left:17px; width:70px; border:1px solid black;">
                     <dl style="margin-top:7px;">
                        <dd><a href="#">고객센터</a></dd>
                        <dd><a href="#">공지사항</a></dd>
                        <dd><a href="#">Q&A</a></dd>
                     </dl>
                  </div>
               </li>
               <%
                String act = (String) session.getAttribute("act");
                if ((Tool.checkNull(act)).equals("M")){
                %>
                  <li><a class='menu_link' href='<%=root %>/member/list.do' class='menu_style'><span></span>관리자다</a></li>
                <%
                }else{ }
                %> 
            </ul>
            <div style='clear:both;'></div>
      
   </DIV>
   </DIV>
   <div id="logo">
      <img class="logo" alt="" src="${pageContext.request.contextPath}/images/logo.png" >
   </DIV>
   <DIV id="category_b">
   <DIV id="category">
       <A href="#" id='menu_top' style="width:20%; background-color:#F6F6F6; float:left" >카테고리</A>
       <div class='search float_r' style="margin-right:3%;">
            <span style="display:inline-block; width:400px;"><input type="text" style="width:100%; height:40px; border: 1px solid blue;"></span>
            <span style="display:inline-block; vertical-align:top; margin-top:15px;">검색</span>
       </div>
   
            <div style='clear:both;'></div>
   </DIV>
   </DIV>
  </header>
  
  <FORM name='frm' method='POST'
    action='${pageContext.request.contextPath}/member/login.do'>
    <input type='hidden' name='url_address' value='<%=url_address%>'>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
              <span aria-hidden="true">×</span><span class="sr-only">Close</span>
            </button>
            <h4 class="modal-title" id="myModalLabel">로그인</h4>
          </div>
          <div class="modal-body">
            <ul>
              <li class='center'><label class='' for='userid'>아이디</label>
                <input type='text' name='userid' id='userid'
                value='<%=ck_userid%>' style='width: 50%;'
                autocomplete="off"> <label> <input
                  type='checkbox' name='userid_save' value='Y'
                  <%=(ck_userid_save.equals("Y")) ? "checked='checked'" : ""%>>
                  저장
              </label></li>
              <li class='center'><label class='' for='pwd'>패스워드</label>
                <input type='password' name='pwd' id='pwd'
                value='<%=ck_pwd%>' style='width: 50%;'
                autocomplete="off"> <label> <input
                  type='checkbox' name='pwd_save' value='Y'
                  <%=(ck_pwd_save.equals("Y")) ? "checked='checked'" : ""%>>
                  저장
              </label></li>
              <li class='center'><A href="./create.do">회원가입</A></li>
            </ul>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default"
              data-dismiss="modal">취소</button>
            <button type="submit" class="btn btn-primary">로그인</button>
          </div>
        </div>
        <!-- 모달 콘텐츠 -->
      </div>
      <!-- 모달 다이얼로그 -->
    </div>
    <!-- 모달 전체 윈도우 -->
  </FORM>
