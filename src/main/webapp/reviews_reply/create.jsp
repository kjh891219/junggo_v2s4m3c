<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>

<style>
   .panel-footer{
   float:center;
   margin-top:5%;
   background-color: #FFFFFF;
   width:15%;
   border:none;
  margin:0 auto;
  }</style>


</head> 
<!-- ----------------------------------------- -->
<body style="width:100%;">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->


    
<DIV class='center-block' style='width: 80%; margin:0 auto;'>
<DIV class='' style="margin-left:6%;">댓글 쓰기</DIV>
<FORM name='frm' method='POST' action='${pageContext.request.contextPath}/reviews_reply/create.do' class='form-inline'>
  <input type="hidden" name="nickname" id="nickname" value='${nickname}'>
  <input type="hidden" name="r_no" id="r_no" value='<%=request.getParameter("r_no")%>'>
  <input type="hidden" name="userid" id="userid" value='${userid }'>
<div class="col-xs-15" style="border-style : solid #d6d6c2; margin-left : 6%"">
        <textarea rows="5" cols="60"  name="rcomment" id="rcomment" placeholder="내용을 입력하세요" class="form-group"><%=request.getParameter("r_no")%></textarea>
     <div class="panel-footer" style="margin-left:85%">
     <button onclick="create_login();" type='button' onclick="location.href='./create.do'" 
                 type="button" class="btn btn-success btn-sm btn-block">
      <span class="fa fa-send" ></span>등록</button></div>
  </div>
 
</FORM>
</DIV>



<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 

