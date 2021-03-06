<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
 
 
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
<script src="${pageContext.request.contextPath}/js/event.js?ver=1"></script>

<style>
      div.a {
        margin: auto;
        width: 500px;
        height: 2000px;
        border: 1px solid #bcbcbc;
      }
      a.top {
        position: fixed;
        left: 50%;
        bottom: 50px;
        display: none;
      }
</style>

<script type="text/javascript">
$( document ).ready( function() {
    $( window ).scroll( function() {
      if ( $( this ).scrollTop() > 200 ) {
        $( '.top' ).fadeIn();
      } else {
        $( '.top' ).fadeOut();
      }
    } );
    $( '.top' ).click( function() {
      $( 'html, body' ).animate( { scrollTop : 0 }, 400 );
      return false;
    } );
  } );

  $(document).ready(function() {

    $(".photo_img img").attr("src", $("#file2").parent("a").attr("href"));

    //Slide Gallery 이미지 샐랙터
    $(".photo_line ul li").click(function() {
      $(this).addClass("active").siblings();
      $(".photo_img img").attr("src", $(this).children("a").attr("href"));
      return false;
    });

  });

  $(document).ready(function() {

    $(".photo_line ul li a").parent().addClass("float_l");
    $(".photo_line ul li div").parent().addClass("float_r");

  });

  $(function() {
    $("iframe.myFrame").load(function() { //iframe 컨텐츠가 로드 된 후에 호출됩니다.
      $(this).height($(this).contents().find('body')[0].scrollHeight + 120);
      /*       var frame = $(this).get(0);
      var doc = (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document;
      $(this).height(doc.body.scrollHeight);
      $(this).width(doc.body.scrollWidth); */
    });
  });
  
  $(document).ready(function() {
     
     if($(".left").height() < $(".right").height()){
        $(".left").height($(".right").height());
     }
     
   });
</script>

<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
</script>


</head>
<body> 
<jsp:include page="/menu/top.jsp" flush='false' />
<jsp:include page="/menu/left.jsp" flush='false' />
    <jsp:include page="/menu/community_left.jsp" flush='false' />

<!-- ----------------------------------------- -->
<div class="float_l right " style="width:80%;">
 <div class="container" style="width:90%; min-height:380px;"> 


 <div style="overflow: hidden; position: relative; padding-top: 20px; margin: 0 auto; width: 100%;">
 <h1 style="padding-left: 0px; margin: 0; padding: 10px; float: left; display: block; font-size: 20px; line-height: 22px; font-weight: bold; color: #444;">
  <a href="${pageContext.request.contextPath}/cheat/list.do">허위/사기신고 게시판</a>
 </h1>


<div style="clear: both;"></div>




<div style="padding: 11px; border-top: 1px solid #CCC; border-bottom: 1px solid #CCC; background: #FCFCFC; line-height: 1.5em; display: block;">
<span style="float: right; display: inline; font-weight: 400; letter-spacing: 0; color: #444; font-size: 12px;">${fn:substring(cheatVO.wdate, 0, 16) }</span>
<h1 style="font-size: 20px; font-weight: bold; color: #444; overflow: hidden; margin: 0; padding: 0 8px; line-height: 18px;">☞ ${cheatVO.title}</h1>
</div>

<div style="clear: both;"></div>
<div style=" margin-top: 40px; ">
  <div style="float: left; width: 400px; position: relative; z-index: 0; display: block; ">
    <div style="position: relative; text-align: center; border-left: 1px solid #dadada; border-right: 1px solid #dadada; border-top: 1px solid #dadada; border-bottom: 1px solid #dadada; display: block; ">
      <div  style="display: table; width: 400px; height: 400px; text-align: center; vertical-align: top;">
       <p class="photo_img" style="width:400px; height:400px; border:1px solid lightgray; border-bottom:none;">
          <img src="../images/no_image.gif" width="100%" height="100%" >
       </p>
         <div style="position: relative; float: right; width: 400px; margin-top: 50px; ">
          <strong style="font-size: 28px; font-weight: bold; color: #111; margin-top: 21px; text-align:center;">
           구입가격 :  <fmt:formatNumber value="${cheatVO.buyprice }" pattern="#,###원"/>
          </strong>
       </div>        
     
         <div style="margin: 0; padding: 0; display: block;">
            <div class='photo_line' style="width: 400px;">
              <ul>
                 <li style="float: left; margin: 0; padding: 0;">
                          <c:set var='file2' value="${fn:toLowerCase(cheatVO.file2)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file2, '.jpg')}">
                              <a href="./storage/${cheatVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${cheatVO.file2}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.gif')}">
                              <a href="./storage/${cheatVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${cheatVO.file2}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.png')}">
                              <a href="./storage/${cheatVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${cheatVO.file2}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:80px; height:80px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 80px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li style="float: left; margin: 0; padding: 0;">
                          <c:set var='file4' value="${fn:toLowerCase(cheatVO.file4)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file4, '.jpg')}">
                              <a href="./storage/${cheatVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${cheatVO.file4}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.gif')}">
                              <a href="./storage/${cheatVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${cheatVO.file4}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.png')}">
                              <a href="./storage/${cheatVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${cheatVO.file4}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:80px; height:80px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 80px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file6' value="${fn:toLowerCase(cheatVO.file6)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file6, '.jpg')}">
                              <a href="./storage/${cheatVO.file6}">
                                 <IMG id='file6' class="gallery" src='./storage/${cheatVO.file6}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file6, '.gif')}">
                              <a href="./storage/${cheatVO.file6}">
                                 <IMG id='file6' class="gallery" src='./storage/${cheatVO.file6}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file6, '.png')}">
                              <a href="./storage/${cheatVO.file6}">
                                 <IMG id='file6' class="gallery" src='./storage/${cheatVO.file6}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:80px; height:80px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 80px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file8' value="${fn:toLowerCase(cheatVO.file8)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file8, '.jpg')}">
                              <a href="./storage/${cheatVO.file8}">
                                 <IMG id='file8' class="gallery" src='./storage/${cheatVO.file8}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file8, '.gif')}">
                              <a href="./storage/${cheatVO.file8}">
                                 <IMG id='file8' class="gallery" src='./storage/${cheatVO.file8}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file8, '.png')}">
                              <a href="./storage/${cheatVO.file8}">
                                 <IMG id='file8' class="gallery" src='./storage/${cheatVO.file8}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:80px; height:80px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 80px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file10' value="${fn:toLowerCase(cheatVO.file10)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file10, '.jpg')}">
                              <a href="./storage/${cheatVO.file10}">
                                 <IMG id='file10' class="gallery" src='./storage/${cheatVO.file10}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file10, '.gif')}">
                              <a href="./storage/${cheatVO.file10}">
                                 <IMG id='file10' class="gallery" src='./storage/${cheatVO.file10}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file10, '.png')}">
                              <a href="./storage/${cheatVO.file10}">
                                 <IMG id='file10' class="gallery" src='./storage/${cheatVO.file10}' width="80" height="80" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:80px; height:80px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 80px;">이미지 없음</p></div>                            
                            </c:otherwise>
                          </c:choose>
                        </li>
              </ul>
            </div>
         </div>
      </div>
    
    </div>
  </div>
  <div style="position: relative; float: right; width: 50%; min-width:290px; padding-right: 30px;">
   <strong style="font-size: 20px; font-weight: bold; color: #111; margin-top: 21px; text-align:center;">
           신고자 정보
   </strong>
   <table style="width: 100%; border-top: 1px solid #cccccc; border-spacing: 0; display: table; border-collapse: separate; border-color: grey;'">
    
    <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit;">
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        신고자
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        ${cheatVO.nickname }
      </td>
    </tr>
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        이메일
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        ${cheatVO.email }
      </td>
    </tr>
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        전화번호
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        ${cheatVO.tel }
      </td>
    </tr>
    </tbody>
   </table>
  </div>
  
  
  <div style="position: relative; float: right; width: 50%; min-width:290px; padding-right: 30px; margin-top: 50px;">
   <strong style="font-size: 20px; font-weight: bold; color: #111; margin-top: 21px; text-align:center;">
           상품정보
   </strong>
   <table style="width: 100%; border-top: 1px solid #cccccc; border-spacing: 0; display: table; border-collapse: separate; border-color: grey;'">
    <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit;">
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        허위상품등록자ID
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        ${cheatVO.cheatid }
      </td>
    </tr>
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        허위상품등록자연락처
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        ${cheatVO.cheattel}
      </td>
    </tr>
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        허위상품등록자이메일
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
         ${cheatVO.cheatemail}
      </td>
    </tr>
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
         분류
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
         ${cheatVO.gubun}
      </td>
    </tr>
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        사기금액
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
         ${cheatVO.buyprice}
      </td>
    </tr>
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        지역
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        ${cheatVO.region }
      </td>
    </tr>
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        허위상품등록자연락처
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        ${cheatVO.cheattel}      
        </td>
    </tr>
    <tr>
      <th style="padding: 14px 10px 14px 20px; background: #f5f5f5; font: normal 12px '맑은 고딕'; color: #333; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        발생시기
      </th>
      <td style="padding: 14px 10px 14px 20px;  font: normal 12px '맑은 고딕'; color: #666; text-align: left; vertical-align: top; border-bottom: 1px solid #e5e5e5; letter-spacing: 0">
        ${cheatVO.occurday}   
        </td>
    </tr>
    </tbody>
   </table>
  </div>
</div>
<div style="clear: both;"></div>

  

 <div style="padding: 11px; border-top: 1px solid #CCC; border-bottom: 1px solid #CCC; background: #FCFCFC; display: block; margin-top: 50px; margin-bottom: 50px; display: block;">
    ${cheatVO.content} 
  </div>
  
  <div style="text-align: right; margin-bottom:30px;">
   <c:if test="${(cheatVO.userid eq userid)}">
  <button type='button' onclick="location.href='./update.do?ctno=${cheatVO.ctno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
  <button type='button' onclick="location.href='./delete.do?ctno=${cheatVO.ctno}&col=${searchDTO.col}&word=${searchDTO.word}'">삭제</button>
  </c:if>
  <button type='button' onclick="location.href='./list.do?ctno=${cheatVO.ctno}&col=${searchDTO.col}&word=${searchDTO.word}'">목록</button>
</div>

<!-- -------------------------------------------- -->
<iframe src="${pageContext.request.contextPath}/cheat_reply/list.do?ctno=${cheatVO.ctno}" class='myFrame' width="100%" style="border-style: none;"></iframe>
</div>
</div>
</div>
<div class="both"></div>
 <jsp:include page="/menu/bottom.jsp" flush='false' /> 

</body>


<!-- -------------------------------------------- -->
</html> 