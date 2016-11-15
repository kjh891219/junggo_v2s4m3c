<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="web.tool.*" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
 
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="../js/event.js?ver=1"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">

$(document).ready(function() {
   
   $(".home_list").mouseenter(function(){
      $(this).children(".home_img").find("#file1").css('padding','0px');
      $(this).children(".home_detail").show().css("background-color","rgba( 255, 255, 255, 0.5 )");
   });

   $(".home_list").mouseleave(function(){
      $(this).children(".home_img").find("#file1").css('padding','5px');
      $(this).children(".home_detail").hide();
      
   });
   
   /* lide Gallery 이미지 샐랙터
   $(".list_wrap ul li").click(function() {
   $(this).addClass("active").siblings();
   $(".photo_img img").attr("src",$(this).children("a").attr("href"));
   return false; */
   
   $(".home_detail a").click(function(){
      window.parent.location.href = $(this).attr('href') ;
      window.close();
      return false;
   });
   $(".home_img a").click(function(){
      window.parent.location.href = $(this).attr('href') ;
      window.close();
      return false;
   });
   

});

$(function(){
 
});
</script>

<style type="text/css">
   .li:hover{
      background-color:#dcdcdc;
   }
   .content_from li{
      border-top:1px solid gray;
   }
     *{ 
    font-family: dotum,"돋움";
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
    list-style: none;
  }
  
  .newlist{
    margin-top:10px;
    margin-left:15px;
  }
</style>
 
<script type="text/javascript">
</script>
</head>
 
<body leftmargin="0" topmargin="0" style="width:100%;">

  <div class="newlist">
    <ul >
      <c:forEach var="vo" items="${list2 }">
      <li class="float_l home_list" style="width:190px; height:190px; position: relative;">
         <div class="home_img">
              <c:set var='file1' value="${fn:toLowerCase(vo.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <a href="./read.do?clothno=${vo.clothno}"><IMG id='file1' style="padding:5px; width:190px; height:190px; " src='./storage/${vo.file1}' ></a>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}" >
                  <a href="./read.do?clothno=${vo.clothno}"><IMG id='file1'  style="padding:5px;width:190px; height:190px; "  src='./storage/${vo.file1}'></a>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <a href="./read.do?clothno=${vo.clothno}"><IMG id='file1' style="padding:5px;width:190px; height:190px; "  src='./storage/${vo.file1}'></a>
                </c:when>
                <c:otherwise>
                  <a href="./read.do?clothno=${vo.clothno}"><img id='file1' src="http://i1.daumcdn.net/cfs.tistory/static/images/xBoxReplace_250.png" style="padding:5px; width:100%; height:cover" ></a>
                </c:otherwise>
              </c:choose>
            <div class='both'></div>
        </div>    
            <div class="home_detail" style="width:100%; height:100%; position: absolute; top:0; left:0; padding-top:10%; padding-left:10%; display:none;">
              <a style="display:block; width:100%; height:100%; position:absolute; top:0; left:0;" href="./read.do?clothno=${vo.clothno}"></a>  
               <span>[${vo.deal_code }]</span>
               <span>[${vo.product_code }]</span><br>
               <strong>${vo.title }</strong>
               <br><span>${vo.userid }</span>
               <br><span>${vo.wdate.substring(0,10) }</span>
               <br><strong style="font-family: dotum,'돋움'; font-size: 25px;"><fmt:formatNumber value="${vo.hprice }" pattern="#,###원"/></strong>
            </div>
            <div class='both'></div>
      </li>
      </c:forEach>
    </ul>
   
  </div>
</body>
 
</html>