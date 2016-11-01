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



</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->



<DIV class='center-block'>
<DIV class='title'>댓글 쓰기</DIV>
<FORM name='frm' method='POST' action='./reply.do' class='form-inline'>
  <input type="hidden" name="nickname" id="nickname" value='dfdf'>
  <input type="hidden" name="ctno" id="ctno" value='1'>
  <input type="hidden" name="userid" id="userid" value='master'>
  <div class="col-xs-5">    
        <textarea rows="3" cols="100"  name="rcomment" id="rcomment" placeholder="내용을 입력하세요" class="form-group">댓글입력</textarea>
         <button type="submit" class="btn btn-success btn-lg">등록</button>

  </div>
 
</FORM>
</DIV>



<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 

