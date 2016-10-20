<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dev.mvc.gamedevice.GameDeviceVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
          
 <script type="text/javascript" src="../js/tool.js"></script>
 
<script type="text/javascript">
 
</script>
 
</head> 
<body>
<!-- ----------------------------------------- -->
  <div class="container">
    <jsp:include page="/menu/top.jsp" flush='false' />

     
     
<div class='content_menu'>
  <A href='./list.do'>게시판</A>
</div> 
 
<DIV class = 'content'>
  <FORM name='frm' method='POST' action='./delete.do'
             onsubmit = 'return send();'>
    <input type='hidden' name='gdno' value='${gdno}'>

    
      <div>   
        <div class="col-xs-12 col-lg-12">
      삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.
            <button type = "submit">삭제 진행</button>
          <button type = "button" onclick = "history.back()">취소</button>
        </div>
      </div>  
     </FORM>    
</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</div>
</body>
<!-- -------------------------------------------- -->
</html> 