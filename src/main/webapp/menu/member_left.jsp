<%@ page contentType="text/html; charset=UTF-8" %>
<%
String root = request.getContextPath();
%>

<div class='float_l left'  style="width:20%; height:100%; padding-top: 40px; background-color:#f5f5f5; ">
	<div class="text_l" style="width:80%; margin:0 20%;">
      <img alt="" src="../images/mypage.png" style="position:relative; left:-20px;">
   <UL style="line-height:40px; margin-top:30px;">
      <li class='left_list member_left' style="padding-top:10px; border-bottom:1px solid lightgray; box-shadow:0px 2px white;">
         <a href='<%=root %>/member/checkPwd.do?mno=${mno}&flag=1'>정보 수정</a>
      </li>
      <li class='left_list member_left' style="border-bottom:1px solid lightgray; box-shadow:0px 2px white;">
         <a href='<%=root %>/member/checkPwd.do?mno=${mno}&flag=2'>회원 탈퇴</a>
      </li>
      <li class='left_list member_left' style="border-bottom:1px solid lightgray; box-shadow:0px 2px white;">
         <a href='<%=root %>/member/mylist.do'>내가 쓴 글</a>
      </li>
      <li class='left_list member_left' style="border-bottom:1px solid lightgray; box-shadow:0px 2px white;">
         <a href='<%=root %>/qna/list.do?flag=my'>문의 내역</a>
      </li>
   </UL>
   </div> 
</div>   