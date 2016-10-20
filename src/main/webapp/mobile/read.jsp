<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
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
<DIV class='bottom'>
  목록
</DIV>
<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html>
