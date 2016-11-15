<%@ page contentType="text/html; charset=UTF-8" %>
<%
String root = request.getContextPath();
%>


   <UL>
      <li class='left_list'><a href='<%=root %>/member/list.do'>회원 관리</a></li>
      <li class='left_list'><a href='<%=root %>/message/list.do'>메시지 관리</a></li>
   </UL>
