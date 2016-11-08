<%@ page contentType="text/html; charset=UTF-8" %>
<%
String root = request.getContextPath();
%>

<DIV id="main_left">
   <div id="main_left_left">
   <div id='logo'><img src="./images/logo_1.png"></div>
   <nav class='left_list_form'>
   <!-- <ul>카테고리</ul> -->
   <UL>
      <li class='left_list'><a href='<%=root %>/member/list.do'>회원 관리</a></li>
      <li class='left_list'><a href='<%=root %>/message/list.do'>메시지 관리</a></li>
   </UL>
   </nav>
   </div>
   <DIV id="main_left_detail">
      <div style=""></div>
   </DIV>
</DIV>