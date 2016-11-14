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
  
  function send_wish(hprice, nickname, title, thumb){
    var url = document.location.href;
     location.href = '../favorite/create.do?nickname='+nickname+'&title='+title+'&hprice='+hprice+'&url='+url+'&thumb='+thumb; 
 
  }
  
  function profile(userid, nickname){
    var url = '../member/profile.do?nickname='+nickname;
    var encodedInputString=escape(url);
    var win = window.open(url, '프로필', 'width=617.5px, height=600px');
    
    var x = (screen.width - 500) / 2;
    var y = (screen.height - 440) / 2;
    
    win.moveTo(x, y); // 화면 가운데로 이동
  }
  
  function msg_list(userid){
    $("#detail").css("display","block");
    var url = '../message/create.do?userid='+userid;
    var encodedInputString=escape(url);
    var win = window.open(url, '프로필', 'width=750px, height=800px');
    
    var x = (screen.width - 500) / 2;
    var y = (screen.height - 440) / 2;
    
    win.moveTo(x, y); // 화면 가운데로 이동
   };
 
  
</script>
<style type="text/css">

/* 전체 스타일 */

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
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
<div class="container">
     
     
    <input type="hidden" name="userid" value="${carproductVO.userid}">
  <div class='content_menu' style='width: 100%;'>
   <c:choose>
   <c:when test="${(usedcarVO.userid == userid)}">
   <A href='../carproduct/list.do?&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>자동차용품 목록</A>>
    <A href='./create.do?' class='top_select'>등록</A>
    <A href='./update.do?p_no=${carproductVO.p_no}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>｜ 수정</A>｜
    <A href='./delete.do?p_no=${carproductVO.p_no}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>삭제</A>
    </c:when>
    <c:when test="${usedcarVO.userid != userid}">
    <A href='../carproduct/list.do?&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>자동차용품 목록</A>>
    <A href='./create.do?' class='top_select'>등록</A>
    </c:when>
    </c:choose>
  </div>
  <BR><BR><BR>
   <div class="page-body" style="width:80%; margin:0 auto; margin-top:50px;" >
      <div class="thumb-info" style="float:left; width:50%; min-width: 200px; margin-top:3%;">
           <div class="thumb-wrap">
           <div class="thumb">
           <c:choose>
          <c:when test="${carproductVO.file1.length() > 0}">
             <IMG src='./storage/${carproductVO.file1}'  style=' width:100%;  min-width:200px;'>
          </c:when>       
          <c:otherwise>
            <IMG src='../images/noimage.JPG' style=' width:100%;  min-width:200px;'>
          </c:otherwise>
          </c:choose>
           </div>
    </div>
   </div>
   <FORM style="float:right; width:40%; " name='frm' method="get" action='./update.do'>
     <input type="hidden" name="p_no" value="${carproductVO.p_no}">
      <div class="info "  style= "min-width:200px; margin-top:9%;">
         <h3 class="tit-prd" style="text-align: center">${carproductVO.title}</h3>
         <br>
         <div class="table-opt">
             <table class="table" style="width:100%; margin:0 auto;">
              <caption>&nbsp;상품 옵션</caption>
                <colgroup>
                    <col width="50%" />
                    <col width="50%" />
                </colgroup>
          <tbody>
           <tr>
            <th scope="row"><div class="tb-left">category</div></th>
             <td><div class="category">${carproductVO.category}</div></td>
           </tr>
           <tr>
            <th scope="row"><div class="tb-left">hope price</div></th>
              <td><div class="hprice">${carproductVO.hprice}원</div></td>
           </tr>
           <tr>
            <th scope="row"><div class="tb-left">deal_code</div></th>
             <td><div class="deal_code">${carproductVO.deal_code}</div></td>
           </tr>
           <tr>
            <th scope="row"><div class="tb-left">nickname</div></th>
             <td><div class="nickname">
             <A href="javascript: profile(' ${carproductVO.userid}' ,' ${carproductVO.nickname}') ;" class='list_tag'  title='프로필'>${carproductVO.nickname}</A>
             </div></td>
           </tr>
           <tr>
            <th scope="row"><div class="tb-left">deal_way</div></th>
             <td><div class="deal_way">${carproductVO.deal_way}</div></td>
           </tr>
      </tbody>
      </table>
      <BR><BR>

      <div class="icon" style="text-align: center;border-top:1px solid #d6d6c2; border-bottom:1px solid #d6d6c2;">
      <A href="javascript: send_wish( ' ${carproductVO.hprice}' ,' ${carproductVO.nickname}' , ' ${carproductVO.title}' ,' ${carproductVO.thumb }' )  ;" class='top_select'  title='위시리스트'>
          <IMG src='../images/favorite_love.png' alt="WishList"></A>
      <A href="javascript: msg_list(' ${carproductVO.userid}');" style="margin-left:50px" title='쪽지보내기'><IMG src='../images/Mail.png' alt="msgsend"></A>
      </div>
   
     </div>
   </div>
   </FORM></div>
   
   <br><br><br><br>
<div style="clear:both;"></div>
  <div id="detail_detail" style=" text-align: center; margin-top:8%;">
  <div class="Line"></div><BR>
    <h3 class="tit-detail">DETAIL</h3>
    <BR>
    <div class="Line" ></div>
    <div>
    <FONT color=#000000 face=바탕><SPAN style="FONT-SIZE: 9pt">
      <BR><BR>region : ${carproductVO.region}
      <BR><BR>purchase date :  ${carproductVO.purc_date}
      <BR><BR>product_code : ${carproductVO.product_code}
      <BR><BR>E-mail : ${carproductVO.email}
      <BR><BR>tel : ${carproductVO.tel}
      </SPAN></FONT>
      <BR><BR>
      <c:choose>
          <c:when test="${carproductVO.file1.length() > 0}">
             <IMG src='./storage/${carproductVO.file1}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
     <BR><BR><BR><BR>
         <c:choose>
           <c:when test="${carproductVO.file2.length() > 0}">
           <IMG src='./storage/${carproductVO.file2}'  style='width:50%;'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
     <BR><BR><BR><BR>
         <c:choose>
           <c:when test="${carproductVO.file3.length() > 0}">
           <IMG src='./storage/${carproductVO.file3}'  style='width:50%;'>
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
           <c:choose>
           <c:when test="${usedcarVO.file5.length() > 0}">
           <IMG src='./storage/${usedcarVO.file5}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose> 
          <BR><BR><BR>
     ${carproductVO.content}
    </div>
    
    <BR><BR><BR>
    
     <iframe style="clear:both; text-align: center;" src="${pageContext.request.contextPath}/carproduct_reply/list.do?p_no=${carproductVO.p_no}" 
     scrolling=no name=ce width=900 height=900 frameborder=0 style="border-width:0px; border-color:white; border-style:solid;">
</iframe>
 
  </div>
  

<%--    

          <li>
            <label for="file1" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${carproductVO.size1 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/carproduct/storage&filename=${carproductVO.file1}'>${carproductVO.file1}</A> (${carproductVO.size1Label})
              </c:if>
            </span>    
            <div id='file1Panel'>
              <c:set var='file1' value="${fn:toLowerCase(carproductVO.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <IMG id='file1' src='./storage/${carproductVO.file1}' >
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}">
                  <IMG id='file1'  src='./storage/${carproductVO.file1}' >
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <IMG id='file1'  src='./storage/${carproductVO.file1}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file2" style="width:150px;">업로드 파일2: </label>
            <span>
              <c:if test="${carproductVO.size2 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/carproduct/storage&filename=${carproductVO.file2}'>${carproductVO.file2}</A> (${carproductVO.size2Label})
              </c:if>
            </span>    
            <div id='file2Panel'>
              <c:set var='file2' value="${fn:toLowerCase(carproductVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${carproductVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${carproductVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${carproductVO.file2}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file3" style="width:150px;">업로드 파일3: </label>
            <span>
              <c:if test="${carproductVO.size3 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/carproduct/storage&filename=${carproductVO.file3}'>${carproductVO.file3}</A> (${carproductVO.size3Label})
              </c:if>
            </span>    
            <div id='file3Panel'>
              <c:set var='file3' value="${fn:toLowerCase(carproductVO.file3)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file3, '.jpg')}">
                  <IMG id='file3' src='./storage/${carproductVO.file3}' >
                </c:when>
                <c:when test="${fn:endsWith(file3, '.gif')}">
                  <IMG id='file3'  src='./storage/${carproductVO.file3}' >
                </c:when>
                <c:when test="${fn:endsWith(file3, '.png')}">
                  <IMG id='file3'  src='./storage/${carproductVO.file3}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file4" style="width:150px;">업로드 파일4: </label>
            <span>
              <c:if test="${carproductVO.size4 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/carproduct/storage&filename=${carproductVO.file4}'>${carproductVO.file4}</A> (${carproductVO.size4Label})
              </c:if>
            </span>    
            <div id='file4Panel'>
              <c:set var='file4' value="${fn:toLowerCase(carproductVO.file4)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file4, '.jpg')}">
                  <IMG id='file4' src='./storage/${carproductVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.gif')}">
                  <IMG id='file4'  src='./storage/${carproductVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.png')}">
                  <IMG id='file4'  src='./storage/${carproductVO.file4}' >
                </c:when>  
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file5" style="width:150px;">업로드 파일5: </label>
            <span>
              <c:if test="${carproductVO.size5 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/carproduct/storage&filename=${carproductVO.file5}'>${carproductVO.file5}</A> (${carproductVO.size5Label})
              </c:if>
            </span>    
            <div id='file5Panel'>
              <c:set var='file5' value="${fn:toLowerCase(carproductVO.file5)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file5, '.jpg')}">
                  <IMG id='file5' src='./storage/${carproductVO.file5}' >
                </c:when>
                <c:when test="${fn:endsWith(file5, '.gif')}">
                  <IMG id='file5'  src='./storage/${carproductVO.file5}' >
                </c:when>
                <c:when test="${fn:endsWith(file5, '.png')}">
                  <IMG id='file5'  src='./storage/${carproductVO.file5}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          <li>
         <label for='email'>E-mail</label><br>
            <span>${carproductVO.email}</span><br>
          </li>
          
          <li>
        <label for='tel'>Tel</label><br>
         <span>${carproductVO.tel}</span><br>
      </li>

        </ul>
      </fieldset>
    </FORM>
  </DIV> --%>
 


<!-- -------------------------------------------- -->
</div>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
