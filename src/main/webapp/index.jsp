<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("UTF-8"); %>
<% session.setAttribute("url", "index.jsp"); //test %> 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/home.css?ver=1" rel="Stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/home.js?ver=1"></script>
<script>

</script>
<style type="text/css">
 
</style>
</head> 

<body leftmargin="0" topmargin="0">

   <jsp:include page="/menu/top.jsp" flush='false' />
   <jsp:include page="/menu/left_home.jsp" flush='false' />
   
<div class="container">
<div style="width:100%; "></div>
   
</div>

<div style='clear:both;'></div>
   <jsp:include page="/menu/bottom.jsp" flush='false' />
   

 
</body>
</html> 