<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<link href="${pageContext.request.contextPath}/css/style.css?ver=2" rel="Stylesheet" type="text/css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/event.js?ver=2"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script> 
 
<script type="text/javascript">
$(document).ready(function() {
  
  if($(".left").height() < $(".right").height()){
     $(".left").height($(".right").height());
  }
  
});

</script>
<style>
       a:HOVER {
      text-decoration: none;
    }
      
    a{
      color:black;
    }
</style>
</head> 
<!-- ----------------------------------------- -->
<body style="width:80%; margin:0 auto;">
<jsp:include page="/menu/top.jsp" flush='false' />
<jsp:include page="/menu/left.jsp" flush='false' />
<jsp:include page="/member/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
<div class="float_l right " style="width:80%; min-height: 400px;">
 
<div class="container" style='width:50%; background-color: #e8e8e8'>
<DIV class='content' style='width: 500px; text-align: center;'>
<FORM name='frm' method='POST' action='./dropout.do'>
  <input type='text' name='userid' value='${userid }'>         
  <div class="form-group">   
          <div class="col-xs-12 col-lg-12" style='text-align: center; margin: 30px;'>
            탈퇴하면 복구할 수 없습니다.<br><br>
            <button type = "submit">탈퇴 진행</button>
            <button type = "button" onclick = "history.back()">취소</button>
          </div>
        </div>   
      </FORM>
</DIV>
</DIV>
</DIV>
 <div class="both"></div>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 