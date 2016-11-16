<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title> 
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script>

$(function(){
  $('#panel_frm').hide();
});
function create(rno){
  <% if( session.getAttribute("userid") == null) { %>
  alert('로그인 한 사용자만 사용이 가능합니다.');
  window.parent.openModal();
  <%String bno = request.getParameter("bno");%>
  <%session.setAttribute("url", "book/read.do?bno="+bno);%>
  return false;
  <% } else { %>
  
  var e = window.event, btn = e.target || e.srcElement; 
  alert("댓글을 달 글 번호: "+rno);
  
  var tag = 
   "<DIV id='panel_frm' class='content' style='padding: 10px 0px 10px 0px; width: 100%; text-align: center;'>"+
   "<FORM name='frm' id='frm' method='POST' action='./reply.do'>" +
   "<input type='hidden' name='nickname' id='nickname' value='<c:out value="${nickname}"/>'>" +
   "<input type='hidden' name='bno' id='bno' value='<c:out value="${bno}"/>'>" +
   "<input type='hidden' name='userid' id='userid' value='<c:out value="${userid}"/>'>" +
   "<input type='hidden' name='rno' id='rno' value='" + rno + "'>" +
   "<div style='width:100%;'>" +
   "<textarea rows='3' cols='100'  name='rcomment' id='rcomment' placeholder='내용을 입력하세요' style='width:100%;'>댓글입력</textarea>"+
   "<div class='text_r'>" + 
   "<h1 class='num_reply'>(<span>250</span>/250)" + 
   "<button type='submit' id='submit'>등록</button>" + 
   "<button type='button' onclick='create_cancel(this.form)'>닫기</button></h1>" + 
   "</div>" + 
   "</div>"+
   "</FORM>"+
   "</DIV>"; 
  $('#comment'+rno).html(tag);
  
  return true;
  <% } %>
 }  
 
function create_cancel(frm){
  frm.style.display='none';
}

function delete_form(rno){
  var tag = 
    "<FORM name='delete_from' id='delete_from' method='POST' action='./delete.do'>" +
    "<input type='hidden' name='nickname' id='nickname' value='<c:out value="${nickname}"/>'>" +
    "<input type='hidden' name='bno' id='bno' value='<c:out value="${bno}"/>'>" +
    "<input type='hidden' name='userid' id='userid' value='<c:out value="${userid}"/>'>" +
    "<input type='hidden' name='rno' id='rno' value='" + rno + "'>" +
    "<div>정말로 삭제 하겠습니까?" +
    "<button type='submit' id='submit'>삭제</button>" +
    "<button type='button' onclick='create_cancel(this.form)''>닫기</button>" +
    "</div>"+
    "</FORM>";
    $('#comment'+rno).html(tag);  
}
$('textarea').keyup(function () {
   // 남은 글자 수를 구합니다.
   var inputLength = $(this).val().length;
   var remain = 250 - inputLength;

   // 문서 객체에 입력합니다.
   $(this).next().find('span').html(remain);

   // 문서 객체의 색상을 변경합니다.
   if (remain >= 0) {
      $(this).next().find('span').css('color', 'black');
   } else {
      if(remain <0) {
         $(this).next().find('span').text(0);
      }
   }
});

$('textarea').on('keyup', function() {

  if($(this).val().length > 250) {
      $(this).val($(this).val().substring(0, 250));
      $(this).focus();  
  }

});
</script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/breply/create.jsp?bno=${bno}" flush='false'/>

<div class="content" style='width: 90%; margin:0 auto;'>
    <div style='width:100%;'>
          <ul>
        <c:forEach var="vo" items="${list }">
            <li style="display: block; width:95%; word-break:break-all;  ">
              <div style="margin-bottom:20px;">
              <c:choose>
                <c:when test="${vo.ansnum == 0 }">
                     <img src='./images/1479295674_yahoo_messenger.png' style="width:25px">
                     ${vo.nickname } <span style="font-size: 12px;">(${fn:substring(vo.wdate, 0, 10) }) </span>
                </c:when>
                <c:when test="${vo.ansnum > 0 }">
                  <img src='./images/reply3.jpg'>
                  <c:forEach var="indent"  begin="1" end="${vo.indent }" step="1">
                     <img src='./images/1479295674_yahoo_messenger.png' style="width:25px">
                     ${vo.nickname } <span style="font-size: 12px;">(${fn:substring(vo.wdate, 0, 10) }) </span>
                  </c:forEach>
                </c:when>
              </c:choose>
                     <div>
              <c:choose>
                <c:when test="${vo.ansnum == 0 }">
                  
                </c:when>
                <c:when test="${vo.ansnum > 0 }">
                  <c:forEach var="indent"  begin="1" end="${vo.indent }" step="1">
                   <img src='./images/white.jpg' style='width: 25px; opacity: 0.0;'>
                  </c:forEach>
                  　
                </c:when>
              </c:choose>
                       ${vo.rcomment}
                       <button type='button' onclick="create('${vo.rno}');">댓글</button>
                        
                        <c:if test="${(vo.userid eq userid)}">
                       <button type='button' onclick="delete_form('${vo.rno}');">삭제</button>
                       </c:if>
                     
                     </div>
              
              </div>
               
               
              
              <DIV id="comment${vo.rno }">
              <!-- 여기에 Tag를 넣어줍니다 -->
              </DIV> 
            </li>
            <hr>
        </c:forEach>
          </ul>
        </div>
    <br><br>
  </div>




<!-- -------------------------------------------- -->
</body>
<!-- -------------------------------------------- -->
</html> 