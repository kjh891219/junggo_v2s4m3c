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

  function profile(nickname, userid){
    var url = '../member/profile.do?nickname='+nickname;
    var encodedInputString=escape(url);
    var win = window.open(url, '프로필', 'width=617.5px, height=600px');
    
    var x = (screen.width - 500) / 2;
    var y = (screen.height - 440) / 2;
    
    win.moveTo(x, y); // 화면 가운데로 이동
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

function f_no(){
  var f_no_arr = [];
  $("input[name='f_no']:checked").each(function(i) {
    f_no_arr.push($(this).val());
  });
  if(f_no_arr.length == 0) {
    f_no_arr.push(0);
  }
  location.href="./visible.do?f_no_arr="+ f_no_arr;
}

</script>
<style type="text/css">


 
 
</style>

</head> 
<!-- ----------------------------------------- -->
<body>
<div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
 <form name="frmSearch" method="get" action="./list.do"> 
    <div class='content_menu' style='width: 100%;'>
    <input type='hidden' name='f_no' id='f_no' value='${favoriteVO.f_no}'>
    <input type='hidden' name='userid' id='userid' value='${favoriteVO.userid}'>
    <input type='hidden' name='url' id='url' value='${favoriteVO.url}'>
    <input type='hidden' name='hprice' id='hprice' value='${favoriteVO.hprice}'>
    <A href="javascript:history.back();"  class='top_select'> 뒤로</a> |
    <A href="javascript:location.reload();"  class='top_select'> 위시 리스트</a>
    </div>
  </form>
  </div> 
        <TABLE class='table' style='width: 100%;'>
          <colgroup>
            <col style='width: 40%;'/>
            <col style='width: 25%;'/>
            <col style='width: 20%;'/>
            <col style='width: 15%;'/>
          </colgroup>
          <TR>
            <TH class='th'>제목</TH>
            <TH class='th'>이미지</TH>
            <TH class='th'>닉네임</TH>
            <TH class='th'>희망 가격</TH>
            <TH class='th'><button onclick="f_no()"><img src='./images/trashcan.png' /></button></TH>
        
          </TR>
          
          <c:forEach var="vo" items="${list}">
          <TR>
            <TD class='td'>
            <a href="${vo.url}"  class='list_tag' >${vo.title}</a>
            </TD>
            <TD class='td'>
            <img alt="" src="${vo.thumb }"/>
            </TD>
            <TD class='td'>
            <A href="javascript: profile(' ${vo.nickname}' ,' ${vo.nickname}') ;" class='list_tag' >${vo.nickname}</A> 
            </TD>
            <TD>${vo.hprice}</TD>
            <TD class='td'>
               <input type="checkbox" id="f_no" name="f_no" value="${vo.f_no }">
            </TD>
        </TR>
          </c:forEach>
          </TABLE>

  <DIV class='bottom'>${paging}</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 