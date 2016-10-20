<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="dev.mvc.game.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<script type="text/javascript">
  /*
   function customize(imgObj){
   alert('file2 ' + imgObj.width);
   alert('file2 ' + imgObj.height);
   }
   */

  $(function() {
    $('#file2').load(function() { // 태그 메모리 상주후 작동
      var width = $('#file2').width();
      //alert(width);
      if ($('#file2').width() > screen.width * 0.6) {
        $('#file2').width('60%');
      }
    });
  });
</script>
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
  <!-- ----------------------------------------- -->
  <h1 style="text-align: center;">${gameVO.title}</h1>
  <div style="float: left;">내용 : ${gameVO.content}</div>
  <li><label for="file1">업로드 파일: </label> <span> 
  <c:if test="${gameVO.size2 > 0}">
        <A href='${pageContext.request.contextPath}/download?dir=/game/storage&filename=${gameVO.file2}'>${gameVO.file2}</A> (${gameVO.size2Label})
  </c:if>
  </span>
    <div id='file2Panel'>
      <c:set var='file2' value="${fn:toLowerCase(gameVO.file2)}" />
      <c:choose>
        <c:when test="${fn:endsWith(file2, '.jpg')}">
          <IMG id='file2' src='./storage/${gameVO.file2}'>
        </c:when>
        <c:when test="${fn:endsWith(file2, '.gif')}">
          <IMG id='file2' src='./storage/${gameVO.file2}'>
        </c:when>
        <c:when test="${fn:endsWith(file2, '.png')}">
          <IMG id='file2' src='./storage/${gameVO.file2}'>
        </c:when>
      </c:choose>
    </div></li>
  <div style="float: right;">
    <h2>상품정보</h2>
    <table border="1">
      <tr>
        <td style="text-align: center;">거래구분</td>
        <td style="text-align: center;">${gameVO.deal_code}</td>
      </tr>
      <tr>
        <td style="text-align: center;">분류</td>
        <td style="text-align: center;">${gameVO.category}</td>
      </tr>
      <tr>
        <td style="text-align: center;">상품구분</td>
        <td style="text-align: center;">${gameVO.product_code}</td>
      </tr>
      <tr>
        <td style="text-align: center;">거래지역</td>
        <td style="text-align: center;">${gameVO.region}</td>
      </tr>
      <tr>
        <td style="text-align: center;">거래방식</td>
        <td style="text-align: center;">${gameVO.deal_way}</td>
      </tr>
      <tr>
        <td style="text-align: center;">구입시기</td>
        <td style="text-align: center;">${gameVO.purc_date}</td>
      </tr>
      <tr>
        <td style="text-align: center;">수량</td>
        <td style="text-align: center;">${gameVO.quantity}</td>
      </tr>
      <tr>
        <td style="text-align: center;">희망가격</td>
        <td style="text-align: center;">${gameVO.hprice}</td>
      </tr>
    </table>



    <h2>판매자 정보</h2>
    <table border="1">
      <tr>
        <td style="text-align: center;">판매자</td>
        <td style="text-align: center;">${gameVO.nickname}</td>
      </tr>
      <tr>
        <td style="text-align: center;">이메일</td>
        <td style="text-align: center;">${gameVO.email}</td>
      </tr>
      <tr>
        <td style="text-align: center;">전화번호</td>
        <td style="text-align: center;">${gameVO.tel}</td>
      </tr>
      <tr>
        <td style="text-align: center;">등록일</td>
        <td style="text-align: center;">${gameVO.wdate}</td>
      </tr>
    </table>
  </div>
  <DIV class='bottom'>목록</DIV>
  <!-- -------------------------------------------- -->
  <%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html>
