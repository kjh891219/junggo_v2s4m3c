<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.carproduct.CarproductVO" %>

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
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">
</script>


</head> 
<body>
  <DIV class='content' style='width: 90%;'>
    <FORM name='frm' method='POST' action='./create.do'
              enctype="multipart/form-data">
  <input type='hidden' name='userid' id='userid' value='${param.userid.trim()}' />
  <input type='hidden' name='nickname' id='nickname' value=' ${param.nickname.trim()}' />
  <input type='hidden' name='url' id='url' value=' ${param.url.trim()}' />
  <input type='hidden' name='title' id='title' value='${param.title.trim()}' />
  <input type='hidden' name='thumb' id='thumb' value='${param.title.thumb.trim()}' />
  <input type='hidden' name='hprice' id='hprice' value='${param.hprice.trim()}' />
  
  <ul>
     <li>
     닉네임:${param.nickname.trim()}
     아이디:${param.userid}
     링크 왜 널이야:${param.url}
     제목:${param.title}
     가격:${param.hprice}
     썸네일:${param.thumb}
     <a href="javascript:window.close();"><button type="submit">담기</button></a>
      </li>
  </ul>
  
  </FORM>
  </DIV>
  </body>
  </html>