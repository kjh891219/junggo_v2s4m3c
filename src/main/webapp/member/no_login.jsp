<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>
<style>

body {
    width:100%;
    margin: 0px auto;
}
table{
}
</style>
</head> 
<!-- ----------------------------------------- -->

<!-- ----------------------------------------- -->
<body>

<FORM name='frm' method='GET' action='./login.do'>
<DIV>로그인 한 사용자만 이용이 가능합니다.</DIV>
<button type='submit'>로그인</button>
<button type='button' onclick="history.back();">취소</button>
</FORM>


</body>
<!-- -------------------------------------------- -->
<!-- -------------------------------------------- -->
</html> 