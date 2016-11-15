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
<script>
     function create_login() {
       <% if( session.getAttribute("userid") == null) { %>
       alert('로그인 한 사용자만 이용이 가능합니다');
       window.openModal();
       <%session.setAttribute("url", "qna/list.do");%>
       return false;
       <% } else { %>
       location.href='./create.do';
       return true;
       <% } %> 
     }
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
<div class="float_l right " style="width:80%;">
<!-- ----------------------------------------- -->
<div class="container" style="margin:0 auto; min-height:400px; margin-top:10%; "> 
<div style="width: 780px; margin: 32px auto 0; display: block;">
<div style=" font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; font-size: 14px;
    line-height: 1.42857143;
    color: #333;overflow: hidden; clear: both; width: 100%; padding: 0 0 19px; border-bottom: 1px solid #c4c5c7; display: block;">
  <h2 style=" width: 270px; height: 27px; padding: 0; margin: 0; font-weight: bold;">비밀번호 확인</h2>
</div> 
<div style="display: block;">
        <span style="margin: 13px 0 0 0; width: 362px; height: 14px; background-position: 0 -251px; ">비밀번호를 입력해 주세요. 본인 인증입니다.</span>
</div>
<DIV class='content' style='width: 60%; '>
<FORM name='frm' method='POST' action='./checkPwd.do'
           onsubmit = 'return send();'>
  <input type='hidden' name='mno' value='${mno }'>         
  <input type='hidden' name='userid' value='${userid }'>         
  <input type='hidden' name='flag' value='${flag }'> 
  <fieldset>
    <ul>
      <li>
      <div class="input-group" style="margin-top:5%;">
        <span class="input-group-addon">  <span class="glyphicon glyphicon-lock"></span> </span>
        <input type='password' name='pwd' id='pwd' value='1234' required="required" maxlength="50" autocomplete="off" required="required" style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
        <button type="submit">확인</button>
        <!-- placeholder="암 호" -->
       </div>
        <!-- <label for='pwd' style='width: 200px;'>비밀번호</label>
        <input type='password' name='pwd' id='pwd' value='1234' required="required"> -->
      </li>  
    </ul>
  </fieldset>
</FORM>
</DIV>
</DIV>
</DIV>
</DIV>
<div class="both"></div>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 