<%@ page contentType="text/html; charset=UTF-8" %>
<%
String root = request.getContextPath();
%>

<div class='float_l left'  style="width:20%; height:100%; padding-top: 40px; background-color:#f5f5f5; ">
	<div class="text_l" style="width:80%; margin:0 20%;">
      <img alt="" src="../images/community.png" style="position:relative; left:-20px;">
   <UL style="line-height:40px; margin-top:30px;">
      <li class='left_list member_left' style="padding-top:10px; border-bottom:1px solid lightgray; box-shadow:0px 2px white;">
         <a href='<%=root %>/notice/list.do'>공지사항</a>
      </li>
      <li class='left_list member_left' style="border-bottom:1px solid lightgray; box-shadow:0px 2px white;">
         <a href='<%=root %>/qna/list.do'>고객센터</a>
      </li>
      <li class='left_list member_left' style="border-bottom:1px solid lightgray; box-shadow:0px 2px white;">
         <a href='<%=root %>/reviews/list.do'>상품후기</a>
      </li>
      <li class='left_list member_left' style="border-bottom:1px solid lightgray; box-shadow:0px 2px white;">
         <a href='<%=root %>/cheat/list.do'>신고센터</a>
      </li>
   </UL>
   </div> 
</div>   