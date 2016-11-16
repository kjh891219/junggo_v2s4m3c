<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript" src="../js/event.js"></script>

<script type="text/javascript">
$(document).ready(function() {
  
  if($(".left").height() < $(".right").height()){
     $(".left").height($(".right").height());
  }
  
});
 
/* 
document.oncontextmenu=function(){return false;} // 우클릭 방지
document.onselectstart=function(){return false;} // 드래그 방지
document.ondragstart=function(){return false;} // 선택 방지
document.onmousedown=function(){return false;}
*/

</script>
<style type="text/css">
#active_bg{
  margin:0 auto;
}
</style>

</head>   

<body>
    <jsp:include page="/menu/top.jsp" flush='false' />
    <jsp:include page="/menu/left.jsp" flush='false' /> 
    <jsp:include page="/menu/community_left.jsp" flush='false' />
<div class="float_l right " style="width:80%; margin-top:80px;">
 <div class="container" style="min-height:380px;"> 
<div id="active_bg" class="well" style="max-width:44em;">
      <FORM name='frm' method='POST' action='./delete.do'
                 onsubmit = 'return send();'>
        <input type='hidden' name='noticeno' value='${noticeno}'>
        
        <div class="delete">   
          <div class="" style='text-align: center; margin: 30px;'>
            삭제하면 복구 할 수 없습니다.<br><br>
            <button type = "submit">삭제 진행</button>
            <button type = "button" onclick = "history.back()">취소</button>
          </div>
        </div>   
      </FORM> 

</div>

</div>
</div>
  <div class="both"></div>
  <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 
