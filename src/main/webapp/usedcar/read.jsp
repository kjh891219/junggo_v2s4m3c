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
<link href="${pageContext.request.contextPath}/css/style.css?ver=1" rel="Stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/event.js?ver=1"></script>

<script type="text/javascript">

  $(function(){
    $('#file1').load(function(){ // 태그 메모리 상주후 작동
      // var width = $('#file2').width();
      //alert('file2: ' + width); 
      if ($('#file1').width() > screen.width * 0.7){
        $('#file1').width('70%');      
      }
    });
  });
 
  window.openModal = function() {
    $( '#myModal' ).modal( 'show' );
    }

  function send_wish(hprice, nickname, title, thumb){
    <% if( session.getAttribute("userid") == null) { %>
    alert('로그인 한 사용자만 이용이 가능합니다');
    window.openModal();
    return false;
    <% } else { %>
     var url = document.location.href;
     location.href = '../favorite/create.do?nickname='+nickname+'&title='+title+'&hprice='+hprice+'&url='+url+'&thumb='+thumb; 
    return true;
    <% } %> 
  }
  
  function profile(userid, nickname){
    var url = '../member/profile.do?nickname='+nickname;
    var encodedInputString=escape(url);
    var win = window.open(url, '프로필', 'width=617.5px, height=600px');
    
    var x = (screen.width - 500) / 2;
    var y = (screen.height - 440) / 2;
    
    win.moveTo(x, y); // 화면 가운데로 이동
  }
  
  function propage(u_no){
   var u_no2 = u_no + 1;
   location.href = "./read.do?u_no="+u_no2; 
  }
  
  function msg_list(userid){
    <% if( session.getAttribute("userid") == null) { %>
    alert('로그인 한 사용자만 이용이 가능합니다');
    window.openModal();
    return false;
    <% } else { %>
    
    $("#detail").css("display","block");
    var url = '../message/create.do?userid='+userid;
    var encodedInputString=escape(url);
    var win = window.open(url, '프로필', 'width=750px, height=800px');
    
    var x = (screen.width - 500) / 2;
    var y = (screen.height - 440) / 2;
    
    win.moveTo(x, y); // 화면 가운데로 이동
    return true;
    <% } %> 
   };
 
</script>

<style type="text/css">


 .Line{
   border-style : solid;
   color : #d6d6c2;
   width:80%;
   margin : auto;
 }
 
</style>


</head>

<!-- ----------------------------------------- -->
<body>
<div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
     
   <input type="hidden" name="userid" value="${carproductVO.userid}">   
  <div class='content_menu' style='width: 100%;'>
   <A href='../usedcar/list.do?&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>중고차 목록</A>
<%--     <c:if test="${(usedcarVO.userid eq userid)}">
    <A href='./create.do?' class='top_select'>등록</A>
    <A href='./update.do?u_no=${usedcarVO.u_no}&col=${searchDTO.col}&word=${searchDTO.word}' class='top_select'>｜ 수정</A>｜
    <A href='./delete.do?u_no=${usedcarVO.u_no}&col=${searchDTO.col}&word=${searchDTO.word}' class='top_select'>삭제</A>
    </c:if> --%>
  </div>
  <BR><BR><BR>
  
  <div style="float:left; margin-top:15%">
   <A href="./read.do?u_no=${usedcarVO.u_no + 1}"   class='page_move'  title='이전페이지'>

  <IMG src='../images/Left.png' style='min-width:50%;'></A></div>
  
    <div style="float:right; margin-top:15%">
   <A href="./read.do?u_no=${usedcarVO.u_no - 1}"   class='page_move'  title='다음페이지'>

  <IMG src='../images/Right.png' style='min-width:50%;'></A></div>
  
  <div class="page-body" style="width:80%; margin:0 auto;" >
      <div class="thumb-info" style="float:left; width:50%; min-width: 200px; margin-top:3%;">
           <div class="thumb-wrap">
           <div class="thumb">
           <c:choose>
          <c:when test="${usedcarVO.file1.length() > 0}">
             <IMG src='./storage/${usedcarVO.file1}'  style='width:100%;  min-width:200px; '>
          </c:when>       
          <c:otherwise>
            <IMG src='../images/noimage.JPG' style='width:100%;  min-width:200px; '>
          </c:otherwise>
          </c:choose>
           </div>
        </div>
     </div>
     
     <FORM style="float:right; width:40%; " name='frm' method="get" action='./update.do'>
     <input type="hidden" name="u_no" value="${usedcarVO.u_no}">
      <div class="info "  style= "min-width:200px;">
         <h3 class="tit-prd" style="text-align: center">${usedcarVO.title}</h3>
         <div class="table-opt">
            <table class="table" style="width:100%; margin:0 auto;">
             <caption>&nbsp;상품 옵션</caption>
               <colgroup>
                   <col width="50%" />
                   <col width="50%" />
               </colgroup>
               <tbody>
           <tr>
            <th scope="row"><div class="tb-left">카테고리</div></th>
             <td><div class="category">${usedcarVO.category}</div></td>
           </tr>
           <tr>
            <th scope="row"><div class="tb-left">희망가격</div></th>
              <td><div class="hprice">${usedcarVO.hprice}원</div></td>
           </tr>
           <tr>
            <th scope="row"><div class="tb-left">거래구분</div></th>
             <td><div class="deal_code">${usedcarVO.deal_code}</div></td>
           </tr>
           <tr>
            <th scope="row"><div class="tb-left">닉네임</div></th>
             <td><div class="nickname">
             <A href="javascript: profile(' ${usedcarVO.userid}' ,' ${usedcarVO.nickname}') ;" class='list_tag'  title='프로필'>${usedcarVO.nickname}</A>
             </div></td>
           </tr>
           <tr>
            <th scope="row"><div class="tb-left">거래방법</div></th>
             <td><div class="deal_way">${usedcarVO.deal_way}</div></td>
           </tr>
      </tbody>
      </table>
    <BR><BR>

     <div class="icon" style="text-align: center;border-top:1px solid #d6d6c2; border-bottom:1px solid #d6d6c2;">
       <c:choose>
       <c:when test="${(act eq 'M')}">
       <IMG src="../images/modify.png" onclick="location.href='./update.do?u_no=${usedcarVO.u_no}&col=${searchDTO.col}&word=${searchDTO.word}'" title='수정'>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <IMG src="../images/delete.png"  onclick="location.href='./delete.do?u_no=${usedcarVO.u_no}&col=${searchDTO.col}&word=${searchDTO.word}'" title='삭제'>
       </c:when>
       <c:when test="${(usedcarVO.userid eq userid)}">
       <IMG src="../images/modify.png" onclick="location.href='./update.do?u_no=${usedcarVO.u_no}&col=${searchDTO.col}&word=${searchDTO.word}'" title='수정'>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <IMG src="../images/delete.png"  onclick="location.href='./delete.do?u_no=${usedcarVO.u_no}&col=${searchDTO.col}&word=${searchDTO.word}'" title='삭제'>
       </c:when>
       <c:when test ="${(usedcarVO.userid ne userid)}">
       <A href="javascript: send_wish( ' ${usedcarVO.hprice}' ,' ${usedcarVO.nickname}' , ' ${usedcarVO.title}' ,' ${usedcarVO.thumb }' )  ;" class='top_select'  title='위시리스트'>
          <IMG src='../images/favorite_love.png' alt="WishList"></A>
        <A href="javascript: msg_list(' ${usedcarVO.userid}');" style="margin-left:50px" title='쪽지보내기'><IMG src='../images/Mail.png' alt="msgsend"></A>
       </c:when>
       </c:choose>
      </div>
     </div>
   </div>
   </FORM></div>
   
   <br><br><br><br>
   <div style="clear:both;"></div>
  <div id="detail_detail" style=" text-align: center; margin-top:8%">
  <div class="Line"></div>
  <br>
    <h3 class="tit-detail">DETAIL</h3>
    <BR>
    <div class="Line" ></div>
    <div>
    <FONT color=#000000 face=바탕><SPAN style="FONT-SIZE: 9pt">
      <BR><BR>지역 : ${usedcarVO.region}
      <BR><BR>구매날짜 :  ${usedcarVO.purc_date}
      <BR><BR>상품구분 : ${usedcarVO.product_code}
      <BR><BR>이메일 : ${usedcarVO.email}
      <BR><BR>tel : ${usedcarVO.tel}
      </SPAN></FONT>
      <BR><BR>
      <c:choose>
          <c:when test="${usedcarVO.file1.length() > 0}">
             <IMG src='./storage/${usedcarVO.file1}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
     <BR><BR><BR><BR>
         <c:choose>
           <c:when test="${usedcarVO.file2.length() > 0}">
           <IMG src='./storage/${usedcarVO.file2}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
     <BR><BR><BR><BR>
         <c:choose>
           <c:when test="${usedcarVO.file3.length() > 0}">
           <IMG src='./storage/${usedcarVO.file3}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
          <BR><BR><BR><BR>
           <c:choose>
           <c:when test="${usedcarVO.file4.length() > 0}">
           <IMG src='./storage/${usedcarVO.file4}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
          <BR><BR><BR><BR>
           <c:choose>
           <c:when test="${usedcarVO.file5.length() > 0}">
           <IMG src='./storage/${usedcarVO.file5}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
          <BR><BR><BR>
     ${usedcarVO.content}
    </div>

 <BR><BR><BR>
 

 
  <BR><BR><BR> <BR>
 <iframe style="clear:both; text-align: center;" src="${pageContext.request.contextPath}/usedcar_reply/list.do?u_no=${usedcarVO.u_no}" 
     scrolling=no name=ce width="100%" height=900 frameborder=0 style="border-width:0px; border-color:white; border-style:solid;">
</iframe>

</div>
<!-- -------------------------------------------- -->
</div>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
