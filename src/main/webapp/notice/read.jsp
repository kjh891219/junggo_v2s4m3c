<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<!-- 합쳐지고 최소화된 최신 CSS -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="./css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
 
</script>

<script type="text/javascript">
  $(function(){
    $('#file2').load(function(){ // 태그 메모리 상주후 작동
      // var width = $('#file2').width();
      //alert('file2: ' + width); 
      if ($('#file2').width() > screen.width * 0.7){
        $('#file2').width('70%');      
      }
    });
    $('#file4').load(function(){ // 태그 메모리 상주후 작동
      // var width = $('#file2').width();
      //alert('file2: ' + width); 
      if ($('#file4').width() > screen.width * 0.7){
        $('#file4').width('70%');      
      }
    });
  });
  
</script>


</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->


  <h1 style="text-align: center;">${noticeVO.title}</h1>
  <div style="float: left;">내용 : ${noticeVO.content}</div>
  <li><label for="file1">업로드 파일1: </label> <span> 
  <c:if test="${noticeVO.size2 > 0}">
        <A href='${pageContext.request.contextPath}/download?dir=/notice/storage&filename=${noticeVO.file2}'>${noticeVO.file2}</A> (${noticeVO.size2Label})
  </c:if>
  </span>
    <div id='file2Panel'>
      <c:set var='file2' value="${fn:toLowerCase(noticeVO.file2)}" />
      <c:choose>
        <c:when test="${fn:endsWith(file2, '.jpg')}">
          <IMG id='file2' src='./storage/${noticeVO.file2}'>
        </c:when>
        <c:when test="${fn:endsWith(file2, '.gif')}">
          <IMG id='file2' src='./storage/${noticeVO.file2}'>
        </c:when>
        <c:when test="${fn:endsWith(file2, '.png')}">
          <IMG id='file2' src='./storage/${noticeVO.file2}'>
        </c:when>
      </c:choose>
    </div></li>
  <div style="float: right;">
    <h2>상품정보</h2>


    <h2>판매자 정보</h2>
    <table border="1">
      <tr>
        <td style="text-align: center;">판매자</td>
        <td style="text-align: center;">${noticeVO.nickname}</td>
      </tr>
        <tr>
        <td style="text-align: center;">등록일</td>
        <td style="text-align: center;">${noticeVO.wdate}</td>
      </tr>
    </table>
  </div>

<DIV style="text-align: right;">
  <c:if test="${(noticeVO.userid eq userid)}">
  <button type='button' onclick="location.href='./update.do?noticeno=${noticeVO.noticeno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
  <button type='button' onclick="location.href='./delete.do?noticeno=${noticeVO.noticeno}&col=${searchDTO.col}&word=${searchDTO.word}'">삭제</button>
  </c:if>
  <button type='button' onclick="location.href='./list.do'">목록</button>
  
</DIV>

<iframe src="${pageContext.request.contextPath}/nt_reply/list.do?noticeno=${noticeVO.noticeno}" scrolling=no name=ce width=900 height=900 frameborder=0 style="border-width:0px; border-color:white; border-style:solid;"></iframe>  

<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 