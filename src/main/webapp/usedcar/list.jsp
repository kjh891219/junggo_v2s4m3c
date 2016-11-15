<%@ page contentType="text/html; charset=UTF-8" %>
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
  $('#panel_frm').hide();
});
 
function profile(userid,nickname){
  var url = '../member/profile.do?nickname='+nickname;
  var encodedInputString=escape(url);
  var win = window.open(url, '프로필', 'width=617.5px, height=600px');
  
  var x = (screen.width - 500) / 2;
  var y = (screen.height - 440) / 2;
  
  win.moveTo(x, y); // 화면 가운데로 이동
}

window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }

function create_login() {
  <% if( session.getAttribute("userid") == null) { %>
  alert('로그인 한 사용자만 이용이 가능합니다');
  window.openModal();
  return false;
  <% } else { %>
  location.href='./create.do';
  return true;
  <% } %> 
}  


function create(){
  $('#panel_frm').show();
  $('#frm').attr('action', './create.do');
  $('#sort').val('');
  $('#submit').html('등록');
  $('#sort').focus();
}
 
function create_cancel(){
  $('#panel_frm').hide();
}

function update(codeno, sort, seqno){
  $('#panel_frm').show();
  $('#frm').attr('action', './update.do');
  // $('#codeno').val(codeno); // Chrome Elements에 변경이 안됨
  $('#codeno').attr('value', codeno);
  // $('#sort').val(sort);          // Chrome Elements에 변경이 안됨
  $('#sort').attr('value', sort);
  // $('#seqno').val(seqno); // Chrome Elements에 변경이 안됨
  $('#seqno').attr('value', seqno);
  $('#submit').html('저장');
  $('#sort').focus();
}


</script>

<style type="text/css">
  .items{
     margin-top:0px;
    }
 
  .panel-footer{
   float:center;
   margin-top:10px;
   background-color: #FFFFFF;
   width:20%;
   border:none;
  margin:0 auto;
  }
  
  .style_featured > div > div{
    padding: 10px;
    border: 1px solid transparent;
    border-radius: 4px;
    transition: 0.5s;
}
.style_featured > div:hover > div{
    margin-top: +15px;
    border: 1px solid  rgb(245, 245, 240);
    box-shadow: rgba(0, 0, 0, 0.1) 0px 9px 9px 9px;
    background: rgba(255, 255, 255, 0.1);
    transition: 0.99s;
 
</style>

</head> 

<body>
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />

 <div class="container">
 <form name="frmSearch" method="get" action="./list.do"> 
<div class='content_menu' style='width: 100%;'>
 <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>중고차 목록</A>>

      <select name="col"> 
        <option value="">선택</option> 
        <option value="title" ${searchDTO.col == "title" ? "selected=selected" : "" }>제목</option> 
        <option value="category" ${searchDTO.col == "category" ? "selected=selected" : "" }>카테고리</option> 
        <option value="nickname" ${searchDTO.col == "nickname" ? "selected=selected" : "" }>닉네임</option> 
        <option value="title_category" ${searchDTO.col == "title_category" ? "selected=selected" : "" }>제목+카테고리</option> 
        <option value="total" ${searchDTO.col == "" ? "selected=selected" : "" }>전체 목록</option>
      </select>
      <c:choose>
        <c:when test="${searchDTO.col != 'total' }"> <!-- 검색 상태 -->
          <input type="text" name="word" size="15" value="${searchDTO.word }">
        </c:when>
        <c:when test="${searchDTO.col == 'total' }"> <!-- 전체 레코드 -->
          <input type="text" name="word" size="15" value="">
        </c:when>
      </c:choose>
     
      <input type="submit" value="검색">
    </div>
  </form> 
 
<DIV id='panel_frm' class='content' style='padding: 10px 0px 10px 0px; background-color: #DDDDDD; width: 70%; text-align: center;'>
<FORM name='frm' id='frm' method='POST' >
  
  <label for=nickname>닉네임</label>
  <input type='text' name='nickname' id='nickname' size='20' value='닉네임' required="required">
 
  <label for='category'>카테고리</label>
  <input type='text' name='category' id='category' value='HYUNDAI' required="required">

  <label for='deal_code'>거래구분</label>
  <input type='text' name='deal_code' id='deal_code' value='팝니다'  required="required">
   
  <button type="submit" id='submit'>등록</button>
  <button type="button" onclick="create_cancel()">취소</button>
</FORM>
</DIV>
 
 <c:forEach var="vo" items="${list }" varStatus="status"> 
  <c:if test="${status.index != 0 and status.index % 3 == 0}">
    </c:if>
  <ul class = "prdList">
    <li class = "items">
      <div style='background-color: #FFFFFF; float: left; position: relative; left: 0%; margin: 1%; width: 30%; text-align: center;
        padding: 1%;'>
        
          <c:choose>
          <c:when test="${vo.thumb.length() > 0}">
            <div class="container" style='height:150px; width:200px'>
            <div class="row style_featured"><div class="col-md-4"><div style='display:inline-block; width:180px; width:220px;'>
             <A href="./read.do?u_no=${vo.u_no}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}"  class='list_tag' >
               <IMG src='./storage/${vo.thumb}'  style='height:150px; width:200px;'></A></DIV>
             </DIV></div></div>
          </c:when>
          
          <c:otherwise>
          <div class="container" style='height:150px; width:200px'>
           <div class="row style_featured"><div class="col-md-4"><div style='display:inline-block; width:180px; width:220px;'>
          <A href="./read.do?u_no=${vo.u_no}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}"  class='list_tag' >
            <IMG src='../images/noimage.JPG' style='height:150px; width:200px'></A>
            </DIV></div></div>
            </DIV>
          </c:otherwise>
          </c:choose>

<BR><BR>

<div class="product_contents_info" style="margin-left:15%;">
        <strong class="">
          <a href="./read.do?u_no=${vo.u_no}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}"  class='list_tag' >${vo.title}</a>
        </strong>
        <BR><BR>
    <ul class="product_list_item" >
             
            <li>
            <strong class="category">카테고리 :</strong> 
            <span class="category"> ${vo.category}</span>
            </li>
           
    
            <li>
            <strong class="hprice">판매가 :</strong> 
            <span class="hprice"> ${vo.hprice}원</span>
            </li>
   
           <li class="color_type">
            <strong class="deal_way">방식 :</strong> 
            <span class= "deal_way">${vo.deal_way}</span>
           </li>
           
            <li class="color_type">
            <strong class="wdate">등록일 :</strong> 
            <span class= "wdate">${vo.wdate}</span>
           </li>
           
           
            <li class="color_type">
            <strong class="nickname">닉네임 :</strong> 
            <span class= "nickname"> 
            <A href="javascript: profile(' ${vo.userid}' ,' ${vo.nickname}') ;" class='list_tag' title='프로필보기'>${vo.nickname}</A>
            </span>
           </li>
           
             <li class="color_type">
            <strong class="cnt">조회수 :</strong> 
            <span class= "cnt">${vo.cnt}</span>
           </li>
    </ul>
        </div>
      </div>
      </li>
   </ul>
  </c:forEach>

</div>

  <DIV class='bottom'>${paging}</DIV>
  
  
        <div class="panel-footer">
                <div class="row">
                    <div class="col-md-6">
                        <button onclick="create_login();" type='button' onclick="location.href='./create.do'" 
                                         type="button" class="btn btn-success btn-sm btn-block">
                            <span class="fa fa-send"></span>등록</button>
                    </div>
                    <div class="col-md-6">
                    
                       <button type="button" onclick="location.reload();" class="btn btn-primary btn-sm btn-block">
                            <span class="fa fa-send"></span> 새로 고침</button>
                    </div>
                </div>
            </div>


<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html> 