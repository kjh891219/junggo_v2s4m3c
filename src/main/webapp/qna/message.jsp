<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>알림</title> 

<script type="text/javascript" src="../js/tool.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript" src="../js/event.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
$(function(){ 
  
});
</script>
<style>
	button{
		background-color: transparent;
		border:1px solid lightgray;
		
	}
</style>
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
    <jsp:include page="/menu/top.jsp" flush='false' />
    <jsp:include page="/menu/left.jsp" flush='false' /> 

<!-- ----------------------------------------- -->
 <div class='container'>

<DIV class='message'  style="width:50%;margin:0 auto; border:1px solid lightgray; position: relative;">
<img alt="" src="../images/notice.png" style="position: absolute; top: -18px; left: -23px;">
<div style="width:50%; height:200px; margin:0 auto; padding-top:70px;">    
  <fieldset>
    <ul class="text_c">
      <c:forEach var="msg" items="${msgs}">
        <li>${msg}</li>
      </c:forEach>  
      <li></li>
      <li style="display:block; margin-top:20px;">
      <c:forEach var="link" items="${links}">
        ${link}
      </c:forEach>
      </li>
    </ul>
  </fieldset>
  </div>
</div>
  <jsp:include page="/menu/bottom.jsp" flush='false' />
</div>
<!-- -------------------------------------------- -->
</body>
<!-- -------------------------------------------- -->
</html> 


