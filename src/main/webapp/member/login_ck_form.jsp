<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="web.tool.*" %>
 
<%
String url_address = Tool.checkNull(request.getParameter("url_address")); // 로그인 후 이동할 주소

System.out.println("--> 3) login_ck_form.jsp: url_address: " + url_address); 
 
Cookie[] cookies = request.getCookies();
Cookie cookie = null;
 
String ck_userid = "chan"; // userid 저장 변수
String ck_userid_save = ""; // userid 저장 여부를 체크하는 변수
String ck_pwd = "1234"; // pwd 저장 변수
String ck_pwd_save = ""; // pwd 저장 여부를 체크하는 변수
 
for (int i=0; i < cookies.length; i++){
  cookie = cookies[i];
  
  if (cookie.getName().equals("ck_userid")){
    ck_userid = cookie.getValue();         // test1@mail.com
  }else if(cookie.getName().equals("ck_userid_save")){
    ck_userid_save = cookie.getValue();  // Y, N
  }else if (cookie.getName().equals("ck_pwd")){
    ck_pwd = cookie.getValue();         // test1@mail.com
  }else if(cookie.getName().equals("ck_pwd_save")){
    ck_pwd_save = cookie.getValue();  // Y, N
  }
}
%>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>로그인</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="../js/tool.js"></script>
<script src="../js/event.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
<DIV class=container> 
<DIV class='title' style='width: 20%;'>로그인</DIV>
 
<DIV class='content_form' style='width: 50%;'>
<FORM name='frm' method='POST' action='../login.do'>
<input type='hidden' name='url_address' value='<%=url_address %>'> 
  <fieldset>
    <ul>
      <li class='center'>
        <label class='label' for='userid'>아이디</label> &nbsp;&nbsp;
        <input type='text' name='userid' id='userid' value='<%=ck_userid %>' style='width: 50%;' autocomplete="off">
        <label>
        <input type='checkbox' name='userid_save' value='Y' <%=(ck_userid_save.equals("Y"))?"checked='checked'": "" %>> 저장
        </label>
      </li>
      <li class='center'>
        <label class='label' for='pwd'>패스워드</label>
        <input type='password' name='pwd' id='pwd' value='<%=ck_pwd %>' style='width: 50%;' autocomplete="off">
        <label>
        <input type='checkbox' name='pwd_save' value='Y' <%=(ck_pwd_save.equals("Y"))?"checked='checked'":"" %>> 저장
        </label>
      </li>
      <li class='center'>
        <button type='submit'>로그인</button>
        <button type='button' onclick="history.back();">취소</button>
        <A href="./create.do">회원가입</A>
      </li>
    </ul>
  </fieldset>
</FORM>
</DIV>
</DIV>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 