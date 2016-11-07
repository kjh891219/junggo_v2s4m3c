<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
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

<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
</script>


</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' /> 
<!-- ----------------------------------------- -->
<h1 style="text-align: center;">${mobileVO.title}</h1>
<div style="float: left;">
  내용 : ${mobileVO.content}
</div>
<div style="float: right;">
<h2>상품정보</h2>
<table border="1">
  <tr>
  <td style="text-align: center;">거래구분</td>
  <td style="text-align: center;">${mobileVO.deal_code}
  </td>
  </tr>
   <tr>
  <td style="text-align: center;">통신사</td>
  <td style="text-align: center;">${mobileVO.telecome}
  </td>
  </tr>
  <tr>
  <td style="text-align: center;">분류</td>
  <td style="text-align: center;">${mobileVO.category}
  </td>
  </tr>
  <tr>
  <td style="text-align: center;">상품구분</td>
  <td style="text-align: center;">${mobileVO.product_code}
  </td>
  </tr>
  <tr>
  <td style="text-align: center;">거래지역</td>
  <td style="text-align: center;">${mobileVO.region}</td>
  </tr>
  <tr>
  <td style="text-align: center;">거래방식</td>
  <td style="text-align: center;">${mobileVO.deal_way}</td>
  </tr>
  <tr>
  <td style="text-align: center;">구입시기</td>
  <td style="text-align: center;">${mobileVO.purc_date}</td>
  </tr>
  <tr>
  <td style="text-align: center;">수량</td>
  <td style="text-align: center;">${mobileVO.quantity}</td>
  </tr>
  <tr>
  <td style="text-align: center;">희망가격</td>
  <td style="text-align: center;">${mobileVO.hprice}</td>
  </tr>
  </table>
 


<h2>판매자 정보</h2>
 <table border="1">
  <tr>
  <td style="text-align: center;">판매자</td>
  <td style="text-align: center;">${mobileVO.nickname}</td>
  </tr>
  <tr>
  <td style="text-align: center;">이메일</td>
  <td style="text-align: center;">${mobileVO.email}</td>
  </tr>
  <tr>
  <td style="text-align: center;">전화번호</td>
  <td style="text-align: center;">${mobileVO.tel}</td>
  </tr>
  <tr>
  <td style="text-align: center;">등록일</td>
  <td style="text-align: center;">${mobileVO.wdate}</td>
  </tr>
</table>
 </div>
 <div  style="clear: both;"> 
 <li>
<label for="file1">업로드 파일1: </label> <span> 
  <c:if test="${mobileVO.size2 > 0}">
        <A href='${pageContext.request.contextPath}/download?dir=/mobile/storage&filename=${gamedeviceVO.file2}'>${mobileVO.file2}</A> (${mobileVO.size2Label})
  </c:if>
  </span>
    <div id='file2Panel'>
      <c:set var='file2' value="${fn:toLowerCase(mobileVO.file2)}" />
      <c:choose>
        <c:when test="${fn:endsWith(file2, '.jpg')}">
          <IMG id='file2' src='./storage/${mobileVO.file2}'>
        </c:when>
        <c:when test="${fn:endsWith(file2, '.gif')}">
          <IMG id='file2' src='./storage/${mobileVO.file2}'>
        </c:when>
        <c:when test="${fn:endsWith(file2, '.png')}">
          <IMG id='file2' src='./storage/${mobileVO.file2}'>
        </c:when>
      </c:choose>
    </div></li>
   <li>
    <label for="file1">업로드 파일2: </label> <span> 
  <c:if test="${mobileVO.size4 > 0}">
        <A href='${pageContext.request.contextPath}/download?dir=/gamedevice/storage&filename=${mobileVO.file4}'>${mobileVO.file4}</A> (${mobileVO.size4Label})
  </c:if>
  </span>
    <div id='file4Panel'>
      <c:set var='file4' value="${fn:toLowerCase(mobileVO.file4)}" />
      <c:choose>
        <c:when test="${fn:endsWith(file4, '.jpg')}">
          <IMG id='file4' src='./storage/${mobileVO.file4}'>
        </c:when>
        <c:when test="${fn:endsWith(file4, '.gif')}">
          <IMG id='file4' src='./storage/${mobileVO.file4}'>
        </c:when>
        <c:when test="${fn:endsWith(file4, '.png')}">
          <IMG id='file4' src='./storage/${mobileVO.file4}'>
        </c:when>
      </c:choose>
    </div></li>
    <li>
    <label for="file5">업로드 파일3: </label> <span> 
  <c:if test="${mobileVO.size6 > 0}">
        <A href='${pageContext.request.contextPath}/download?dir=/mobile/storage&filename=${mobileVO.file6}'>${mobileVO.file6}</A> (${mobileVO.size6Label})
  </c:if>
  </span>
    <div id='file6Panel'>
      <c:set var='file6' value="${fn:toLowerCase(mobileVO.file6)}" />
      <c:choose>
        <c:when test="${fn:endsWith(file6, '.jpg')}">
          <IMG id='file6' src='./storage/${mobileVO.file6}'>
        </c:when>
        <c:when test="${fn:endsWith(file6, '.gif')}">
          <IMG id='file6' src='./storage/${mobileVO.file6}'>
        </c:when>
        <c:when test="${fn:endsWith(file6, '.png')}">
          <IMG id='file6' src='./storage/${mobileVO.file6}'>
        </c:when>
      </c:choose>
    </div></li>
    <li><label for="file7">업로드 파일4: </label> <span> 
  <c:if test="${mobileVO.size8 > 0}">
        <A href='${pageContext.request.contextPath}/download?dir=/mobile/storage&filename=${mobileVO.file8}'>${mobileVO.file8}</A> (${mobileVO.size8Label})
  </c:if>
  </span>
    <div id='file8Panel'>
      <c:set var='file8' value="${fn:toLowerCase(mobileVO.file8)}" />
      <c:choose>
        <c:when test="${fn:endsWith(file8, '.jpg')}">
          <IMG id='file8' src='./storage/${mobileVO.file8}'>
        </c:when>
        <c:when test="${fn:endsWith(file8, '.gif')}">
          <IMG id='file8' src='./storage/${mobileVO.file8}'>
        </c:when>
        <c:when test="${fn:endsWith(file8, '.png')}">
          <IMG id='file8' src='./storage/${mobileVO.file8}'>
        </c:when>
      </c:choose>
    </div></li>
    <li><label for="file9">업로드 파일5: </label> <span> 
  <c:if test="${mobileVO.size10 > 0}">
        <A href='${pageContext.request.contextPath}/download?dir=/mobile/storage&filename=${mobileVO.file10}'>${mobileVO.file10}</A> (${mobileVO.size10Label})
  </c:if>
  </span>
    <div id='file10Panel'>
      <c:set var='file10' value="${fn:toLowerCase(mobileVO.file10)}" />
      <c:choose>
        <c:when test="${fn:endsWith(file10, '.jpg')}">
          <IMG id='file10' src='./storage/${mobileVO.file10}'>
        </c:when>
        <c:when test="${fn:endsWith(file10, '.gif')}">
          <IMG id='file10' src='./storage/${mobileVO.file10}'>
        </c:when>
        <c:when test="${fn:endsWith(file10, '.png')}">
          <IMG id='file10' src='./storage/${mobileVO.file10}'>
        </c:when>
      </c:choose>
    </div></li>
  </div>
  
  <DIV style="text-align: right;">
  <c:if test="${(mobileVO.userid eq userid)}">
  <button type='button' onclick="location.href='./update.do?mno=${mobileVO.mno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
  <button type='button' onclick="location.href='./delete.do?mno=${mobileVO.mno}&col=${searchDTO.col}&word=${searchDTO.word}'">삭제</button>
  </c:if>
  <button type='button' onclick="location.href='./list.do'">목록</button>
  
</DIV>
<iframe src="${pageContext.request.contextPath}/mo_reply/list.do?mno=${mobileVO.mno}" scrolling=no name=ce width=900 height=900 frameborder=0 style="border-width:0px; border-color:white; border-style:solid;"></iframe>  

<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html>
