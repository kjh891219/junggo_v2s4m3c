<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="web.tool.*" %>
<%
String root = request.getContextPath();
%>


  
<header>
   <DIV id="member">
   <DIV id="member_b" > 
            <ul class="member-list" style='float:right;'>
               <% if(session.getAttribute("userid") == null) { // 회원 로그인 여부 검사 %>
               <li class="login " style="background: url(./images/login.png) no-repeat left center; background-size:16px; background-position: 6px"><a class='menu_style'  href="#myModal" data-toggle="modal" ><span></span>LOGIN</a></li>
               <li class="join" style="background: url(./images/pencil.png) no-repeat left center; background-size:16px; background-position: 6px"><a href="<%=root %>/member/create.do" class='menu_style'><span></span>JOIN</a></li>
               <% } else { %>
               <li class="logout"  style="background: url(./images/logout.png) no-repeat left center; background-size:16px; background-position: 6px"><a href="<%=root %>/member/logout.do" class='menu_style'>${userid }님 로그아웃</a></li>
               <li class="mytm"><a href="<%=root %>/mypage.jsp" class='menu_style'><span></span>마이 페이지</a></li>
               <li class="message"><A href='javascript: message();'><img src="<%=root %>/images/message.png"/></a></li>
               <% } %>   
               <li class="orderDelivery"  style="background: url(./images/truck.png) no-repeat left center; background-size:20px;"><a href="#" class='menu_style'><span></span>주문/배송</a></li>
               <li class="cart" style="background: url(./images/cart.png) no-repeat left center; background-size:20px;"><a href="#" class='menu_style'><span></span>장바구니</a></li>
               <!--
               <li class="cs"><a href="#none" class="on"><span></span>고객센터</a></li><!-- //활성화 시 class on 추가 -->
               <li class="cs" style="background: url(./images/headphones.png) no-repeat left center; background-size:18px;"><a href="#" class='menu_style'><span></span>고객센터</a></li><!-- //활성화 시 class on 추가 -->
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
   <div id="logo" style="width:100%;  height:91px;">
      <img class="logo" alt="" src="./images/logo.png" >
   </DIV>
   
   <DIV id="category_b">
   <DIV id="category">
       <A href="#" id='menu_top' style="width:20%; background-color:#F6F6F6; float:left" >카테고리</A>
       <div class='search float_r' style="margin-right:3%;">
            <span style="display:inline-block; width:400px; height:30px; border:1px solid black; margin-top:10px;"><input type="text" style="width:100%; border:none;"></span>
            <span style="display:inline-block; vertical-align:top; margin-top:15px;">검색</span>
       </div>
          
    <!-- <div class='' style="width:80%;  height:52px;"> -->
    <div style='clear:both;'></div> 
   </DIV>
   </DIV>
</header>