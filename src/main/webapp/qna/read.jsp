<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
 $(function(){
     $('#file2').load(function(){ 
       var file2 = $('#file2');
/*         var width = $('#file2').width(); */
        if(file2.size != null){
         if(file2.width() > screen.width*0.6){
           file2.width('100%');
         }
        }
     }); 
 });
</script>
 
</head>
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<div class="container">
  <!-- ----------------------------------------- -->
    <div class='content_menu' style='width: 90%;'>
    <!-- <A href='../qua/list.do'>게시판 목록</A> >  -->
    <A href='./list.do?'>목록</A>｜
    <A href='./create.do'>등록</A>｜
    <A href='./reply.do?qnano=${qnaVO.qnano}&categoryno=${qnaVO.categoryno }&col=${searchDTO.col}&word=${searchDTO.word}'>답변</A>｜
    <A href='./update.do?qnano=${qnaVO.qnano }&col=${searchDTO.col}&word=${searchDTO.word}'>수정</A>｜
    <A href='./delete.do?qnano=${qnaVO.qnano }&col=${searchDTO.col}&word=${searchDTO.word}'>삭제</A>｜
    <A href="javascript:location.reload();">새로고침</A>
  </div>
  <div class='container'>
  <DIV class='content'>
    <FORM name='frm' method="get" action='./update.do' 
              enctype="multipart/form-data">
      <input type="hidden" name="qnano" value="${qnaVO.qnano}">
      <fieldset class="fieldset">
        <ul>
          <li>
            <label for="categoryno" >카테고리 > </label>
            <c:choose>
               <c:when test="${qnaVO.categoryno == '1'}">
               <span class='td'>회원가입 및 로그인</span>
               </c:when>
               <c:when test="${qnaVO.categoryno == '2'}">
               <span class='td'>배송문의</span>
               </c:when>
               <c:when test="${qnaVO.categoryno == '3'}">
               <span class='td'>기타문의</span>
               </c:when>
             </c:choose>
          </li>
          <li>
            <label for='title' style="width:150px;">제목 : </label>
            <span>${qnaVO.title}</span><br>
          </li>
          <li>
            <label for='content' style="width:150px;">내용 : </label>
            <span>${qnaVO.content}</span>
          </li>
          <li>
            <div id="file1Panel">
              <c:set var='file1' value="${fn:toLowerCase(qnaVO.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <IMG id='file1' src='./storage/${qnaVO.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}">
                  <IMG id='file1'  src='./storage/${qnaVO.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <IMG id='file1'  src='./storage/${qnaVO.file1}'>
                </c:when>
              </c:choose>
            </div>
            <div id="file2Panel">
              <c:set var='file2' value="${fn:toLowerCase(qnaVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${qnaVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${qnaVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${qnaVO.file2}'>
                </c:when>
              </c:choose>
            </div>
            <div id="file3Panel">
              <c:set var='file3' value="${fn:toLowerCase(qnaVO.file3)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file3, '.jpg')}">
                  <IMG id='file3' src='./storage/${qnaVO.file3}'>
                </c:when>
                <c:when test="${fn:endsWith(file3, '.gif')}">
                  <IMG id='file3'  src='./storage/${qnaVO.file3}'>
                </c:when>
                <c:when test="${fn:endsWith(file3, '.png')}">
                  <IMG id='file3'  src='./storage/${qnaVO.file3}'>
                </c:when>
              </c:choose>
            </div>
          </li>
          <li>
            <label for="qdate" style="width:150px;">등록일 : </label>
            <span>${qnaVO.qdate}</span>
          </li>
          <li>
            <label for="userid" style="width:150px;">작성자 : </label>
            <span>${qnaVO.userid}</span>
          </li>
          <li>
           <label class='label' for='email'>이메일</label>
           <input type="text" name='email' id='email' value='${qnaVO.email}'> 
         </li>
         <li>
           <label class='label' for='tel'>연락처</label>
           <input type="text" name='tel' id='tel' value='${qnaVO.tel}'> 
         </li> 
          <li class='text_r'>
            <button type="button" onclick="location.href='./list.do?categoryno=&col=&word='">목록보기</button>
            <button type="button" onclick="location.href='./update.do?qnano=${qnaVO.qnano}'">수정</button>
            <button type="button"
              onclick="location.href='./delete.do?qnano=${qnaVO.qnano}&categoryno=${qnaVO.categoryno}'">삭제</button>
          </li>
        </ul>
      </fieldset>
    </FORM>
  </DIV>
 
  <!-- -------------------------------------------- -->
  </div>
  </div>
</body>
<!-- -------------------------------------------- -->
</html>