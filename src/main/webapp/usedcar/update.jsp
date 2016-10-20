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

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>



<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
   
   find('email_dns').addEventListener('change', email_dns_p);

  };

  function email_dns_p(){
    // alert('변경됨 ' + find('email_dns').value);
    // find('email').value = find('email_dns').value;
    var email = find('email').value;
    var position = email.indexOf('@'); // test1@mail.com
    if (position >= 0){
      email = email.substring(0, position); // test1
    }
    var email_dns = find('email_dns').value;
    if (email_dns == 'none'){ // 직접 입력
      find('email').value = email + '@';
      find('email').focus(); // 입력 커서 이동
    }else{
      find('email').value = email + '@' + email_dns;
    }
  }
  
  </script>


</head> 

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
     
  <div class='content_menu' style='width: 90%;'>
    <A href='../usedcar/list.do'>게시판 목록</A> > 
<%--     <A href='./list.do?u_no=${usedcarVO.u_no }'>${usedcarVO.title }</A>｜
    <A href='./create.do?u_no=${usedcarVO.u_no }'>중고차 등록</A>｜ --%>
    <A href="javascript:location.reload();">새로고침</A>
  </div>
  
  <DIV class='content' style='width: 90%;'>
    <FORM name='frm' method='POST' action='./update.do'
                enctype="multipart/form-data">
      <input type='hidden' name='u_no' id='u_no' value='${usedcarVO.u_no}'>

      <div class="form-group">  
      
      <ul>
       <li>
        <label class='label' for='userid'>아이디</label>
        <input type='text' name='userid' id='userid' value='' required="required">
         <label class='label' for='passwd'>비밀번호</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required"><br><br>
         <label class='label' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value='' required="required">
       </li>
        
        <label for="title" class="col-xs-2 col-lg-2 control-label">글 제목</label>
        <div class="col-xs-10 col-lg-10">
          <input type='text' class="form-control" name='title' size='70' id='title' value='${usedcarVO.title}' required="required">
        </div>
      </div>   
      <div class="form-group">   
        <label for="content" class="col-xs-2 col-lg-2 control-label">내용</label>
        <div class="col-xs-10 col-lg-10">
          <textarea class="form-control" name='content' id='content'  rows='10' style='width: 100%;'>${usedcarVO.content}</textarea>
        </div>
      </div>   
      <div id='file2Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file2' value="${fn:toLowerCase(usedcarVO.file2)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file2, '.jpg')}">
              <IMG src='./storage/${usedcarVO.file1}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.gif')}">
              <IMG id='file2'  src='./storage/${usedcarVO.file2}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.png')}">
              <IMG id='file2'  src='./storage/${usedcarVO.file2}'>
            </c:when>
            <c:when test="${usedcarVO.file2.length() > 0}">
              ${usedcarVO.file2 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      <div class="form-group">   
        <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>  
      <DIV style='text-align: right;'>
        <button type="submit">수정</button>
        <button type="button" onclick="location.href='./list.do?u_no= ${usedcarVO.u_no}'">목록[취소]</button>
      </DIV>         
    </FORM>
  </DIV>


     <jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>

</html> 


