<%@ page contentType="text/html; charset=UTF-8" %>
<%
String root = request.getContextPath();
%>

<div class='float_l left'  style="width:20%; height:100%; padding-top: 40px; background-color:#f5f5f5; ">
   <div class="text_l" style="width:80%; margin:0 20%;">
      <img alt="" src="../images/admin.png" style="position:relative; left:-20px; width:150px;">
   <UL style="line-height:40px; margin-top:30px;">
      <li class='left_list member_left' style="padding-top:10px; border-bottom:1px solid lightgray; box-shadow:0px 2px white;"><a href='<%=root %>/member/list.do'>회원 관리</a></li>
      <li  class='left_list member_left' style="border-bottom:1px solid lightgray; box-shadow:0px 2px white;"><a href='<%=root %>/message/list.do'>메시지 관리</a></li>
   </UL>
   </div> 
</div>

