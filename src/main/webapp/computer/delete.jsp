<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/tool.js"></script>

<script type="text/javascript">

</script>

</head> 

<body>
  <div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
    
    <div class='content_menu' style='width: 100%;'>
      <A href='./create.do?ctno=0&opentype=R'>등록</A>｜
      <A href='./read.do?ctno=${cheatVO.ctno }'>상세보기</A>｜
      <A href='./create.do?ctno=${cheatVO.ctno }&opentype=U'>수정</A>｜
      <A href='./list.do'>목록</A>｜
      <A href='./delete.do?ctno=${cheatVO.ctno }'>삭제</A>｜
      <A href="javascript:location.reload();">새로고침</A>
    </div>
  
    <DIV class = 'content'>
      <FORM name='frm' method='POST' action='./delete.do'
                 onsubmit = 'return send();'>
        <input type='hidden' name='ctno' value='${ctno}'>
        
        <div class="form-group">   
          <div class="col-xs-12 col-lg-12" style='text-align: center; margin: 30px;'>
            삭제하면 복구 할 수 없습니다.<br><br>
           <label for='passwd'>삭제할 글의 패스워드: </label>
           <input type='password' name='passwd' id='passwd'  size='5'>
            <button type = "submit">삭제 진행</button>
            <button type = "button" onclick = "history.back()">취소</button>
          </div>
        </div>   
      </FORM>
    </DIV>

     <jsp:include page="/menu/bottom.jsp" flush='false' />     
  </div>
</body>

</html> 
