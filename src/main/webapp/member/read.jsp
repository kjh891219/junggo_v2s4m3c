<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<link href="${pageContext.request.contextPath}/css/style.css?ver=2" rel="Stylesheet" type="text/css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/event.js?ver=2"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script> 
 <style>
       a:HOVER {
      text-decoration: none;
    }
      
    a{
      color:black;
    }
 </style>
 
<script type="text/javascript">

$(function(){
  $.removeCookie('checkPwd'); // 기존의 쿠기 값을 삭제  
  $.removeCookie('checkNickname'); // 기존의 쿠기 값을 삭제  
  $.removeCookie('checkEmail'); // 기존의 쿠기 값을 삭제  
  
  $.cookie('checkNickname', 'PASS');
  $.cookie('checkEmail', 'PASS');
  $.cookie('checkPwd', 'PASS');
  
  $('#pwd2').focusout(function(){
      if ( $('#pwd').val() == $('#pwd2').val() ) {
        $('#panel_pwd').css('color', '#737373');
        $('#panel_pwd').html('일치합니다.');
        $.cookie('checkPwd', 'PASS'); // 쿠키 생성
      } else {
        $('#panel_pwd').css('color','#FF0000')
        $('#panel_pwd').html('일치하지 않습니다');
        $('#pwd2').focus();
      }
    });
  
  $('#nickname').focusout(function(){
    $.post('./checkNickname_update.do', {nickname: $('#nickname').val(),
      userid: $('#userid').val()}, 
                              checkNickname_res, 'json');
    });
  
  $('#email').focusout(function(){
    var params = 'email=' + $('#email').val();
    $.post('./checkEmail_update.do', {email: $('#email').val(),
      userid: $('#userid').val()}, checkEmail_res, 'json');
    });
});

function send(){
   var check2 = $.cookie('checkPwd');
   var check3 = $.cookie('checkNickname');
   var check4 = $.cookie('checkEmail');
   if(check2 != 'PASS' || check3 != 'PASS' || check4 != 'PASS'){
      return false;
      }   
   if(check2 == 'PASS' && check3 == 'PASS' && check4 == 'PASS'){
      checkEmail_res2();
      return true;
   }
 }

function checkNickname_res(data){
  if(data.cnt == 0){
    $('#panel_nickname').css('color', '#737373');
    $('#panel_nickname').html('사용 가능합니다.');
    $.cookie('checkNickname', 'PASS'); // 쿠키 생성
  }else if(data.cnt == 1){
    $('#panel_nickname').css('color', '#FF0000');
    $('#panel_nickname').html('닉네임이 중복됩니다.');
  }
}

function checkEmail_res(data){
  if(data.cnt == 0){
    $('#panel_email').css('color', '#737373');
    $('#panel_email').html('사용 가능합니다.');
    $.cookie('checkEmail', 'PASS'); // 쿠키 생성
  }else if(data.cnt == 1){
    $('#panel_email').css('color', '#FF0000');
    $('#panel_email').html('이미 사용 중인 이메일입니다.');
  }
}

function checkEmail_res2(){
  if( "${memberVO.email}" == $('#email').val() ) {
      $('#updateFlag').val("0");
  } else {
      $('#updateFlag').val("1");
  }
}
$(document).ready(function() {
  
  if($(".left").height() < $(".right").height()){
     $(".left").height($(".right").height());
  }
  
});



</script>
 
</head> 
<!-- ----------------------------------------- -->
<body style="width:80%; margin:0 auto;">
<jsp:include page="/menu/top.jsp" flush='false' />
<jsp:include page="/menu/left.jsp" flush='false' />
<c:choose>
  <c:when test="${flag == '1' }"> 
    <jsp:include page="/member/menu/left.jsp" flush='false' />
  </c:when>
  <c:when test="${flag != '1' }">
   <jsp:include page="/member/menu/admin_left.jsp" flush='false' />
  </c:when> 
</c:choose>
<!-- ----------------------------------------- -->
<div class="float_l right " style="width:80%;">
<div class="container">
<div style="width: 780px; margin: 32px auto 0; display: block;">
<div style=" font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; font-size: 14px;
    line-height: 1.42857143;
    color: #333;overflow: hidden; clear: both; width: 100%; padding: 0 0 19px; border-bottom: 1px solid #c4c5c7; display: block;">
  <h2 style=" width: 270px; height: 27px; padding: 0; margin: 0; font-weight: bold;">개인 정보 수정</h2>
</div> 
<div style="display: block;">
        <span style="margin: 13px 0 0 0; width: 362px; height: 14px; background-position: 0 -251px; ">회원정보를 입력해 주세요. 모두 입력하셔야 수정이 가능합니다.</span>
</div>
<FORM name='frm' method='POST' action='./update.do'
           onsubmit = 'return send()'>
  <input type='hidden' name='mno' value='${memberVO.mno}'>         
  <input type='hidden' name='confirm' value='${memberVO.confirm}'>         
  <input type='hidden' name='act' value='${memberVO.act}'>         
  <input type='hidden' name='auth' value='${memberVO.auth}'>         
  <input type='hidden' name='updateFlag' id='updateFlag'>   
 <!-- 본문 시작 -->
<div style="margin: 31px 0 0 0; display: block;">
  <div style="padding: 20px 0 30px 65px; border-bottom: 1px solid #dcdcdc;">
    
    <table style="border-collapse: collapse; width: 100%; border-spacing: 0; border-color: gray;">
      <caption style="font-size: 0;  overflow: hidden; line-height: 0;">개인정보입력</caption>
      <colgroup style="display: table-column-group;">
        <col width="145px">
        <col width="*">
      </colgroup>
      <tbody style="display: table-row-group; vertical-align: middle; border-color: inherit; border-collapse: collapse;">
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label style="display: inline-block; line-height: 20px; vertical-align: middle; cursor: default;">
              ID
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
            <input type='text' name='userid' id='userid' value='${memberVO.userid}' readonly maxlength="50" autocomplete="off" style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
          </td>
        </tr>
        
         <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label style="display: inline-block; line-height: 20px; vertical-align: middle; cursor: default;">
              성명
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
            <input type='text' name='name' id='name' value='${memberVO.name}' readonly style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
          </td>      
        </tr>
        
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label style="display: inline-block; line-height: 20px; vertical-align: middle; ">
              비밀번호
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
            <input type='password' name='pwd' id='pwd' value='1234' required="required" style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
          </td>      
        </tr>

         <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label style="display: inline-block; line-height: 20px; vertical-align: middle;">
              비밀번호 확인
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
            <input type='password' name='pwd2' id='pwd2' value='1234' required="required" style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
            <SPAN id='panel_pwd'></SPAN>
          </td>      
        </tr>
        
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label for="nickname" style="display: inline-block; line-height: 20px; vertical-align: middle; cursor: default;">
              닉네임
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
            <input type='text' name='nickname' id='nickname' value='${memberVO.nickname}' placeholder="닉네임을 입력하세요" required="required" style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
            <SPAN id='panel_nickname'></SPAN>
          </td>      
        </tr>
        
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label style="display: inline-block; line-height: 20px; vertical-align: middle; cursor: default;">
              전화번호
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
            <input type='tel' name='tel' id='tel' value='${memberVO.tel}' placeholder="예) 010-0000-0000" required="required" style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
          </td>      
        </tr>
        
        
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label style="display: inline-block; line-height: 20px; vertical-align: middle; cursor: default;">
              이메일
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
            <input type='email' name='email' id='email' value='${memberVO.email}' placeholder="이메일을 입력하세요" required="required" style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
            <SPAN id='panel_email'></SPAN>
          </td>      
        </tr>
        
        
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label style="display: inline-block; line-height: 20px; vertical-align: middle; cursor: default;">
              우편번호
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
             <input type='text' name='zipcode' id='zipcode' value='${memberVO.zipcode}' placeholder="우편번호" style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
            <button type='button' onclick="sample3_execDaumPostcode()" style="height: 35px; border-color: #676767; background: #7f7f7f; color: #fff; line-height: 37px; font-size: 12px; font-weight: bold; letter-spacing: 0; display: inline-block; border: 1px solid #c4c4c4; border-radius: 2px; font: 11px/18px dotum, '돋움';">
            <span style="padding: 0 11px; display: inline-block; color: #FFF; font-weight: bold;">
            우편번호 찾기
            </span>
            </button>
            <div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
            <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
            </div>
          </td>
        </tr>
        
        
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label style="display: inline-block; line-height: 20px; vertical-align: middle; cursor: default;">
              주소
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
            <input type='text' name='address1' id='address1' value='${memberVO.address1}' maxlength='60' placeholder="주소" style="width: 500px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
          </td>      
        </tr>
        
        
        <tr style="display: table-row; vertical-align: inherit; border-color: inherit;">
          <th scope="row" style="position: relative; min-height: 28px; text-align: left; font-size: 15px; font-weight: normal; vertical-align: middle; display: table-cell; border-collapse: collapse;">
            <label style="display: inline-block; line-height: 20px; vertical-align: middle; cursor: default;">
              상세주소
            </label>
          </th>
          <td style="padding: 10px 0; vertical-align: middle; word-break:break-all; display: table-cell;">
            <input type='text' name='address2' id='address2' value='${memberVO.address2}' maxlength='40' placeholder="상세 주소" style="width: 256px; color: #666; line-height: 26px; height: 28px; padding: 6px 10px 0 15px; margin: 0 5px 0 0; border: 1px solid #b1b1b1; font-family: '맑은고딕'; vertical-align: middle; font-size: 15px; background-color: #fff; font-weight: normal;">
            </td>      
          </tr>
          
        </tbody>
      </table>
    </div>
  </div>
   <div style="margin: 10px 0 0 0; text-align: center; padding-top: 20px; display: block;">
  <button type="submit" style="height: 43px; background: #ed2f2f; color:#FFF; line-height: 43px; font-size: 14px; font-weight: bold; border-radius: 2px;">수정</button>
</div>
</FORM>
</div>
</div>



  <!-- 주소 API시작 -->
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <script>
      // 우편번호 찾기 찾기 화면을 넣을 element
      var element_wrap = document.getElementById('wrap');

      function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
      }

      function sample3_execDaumPostcode() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop,
            document.documentElement.scrollTop);
        new daum.Postcode({
          oncomplete : function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = data.address; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 기본 주소가 도로명 타입일때 조합한다.
            if (data.addressType === 'R') {
              //법정동명이 있을 경우 추가한다.
              if (data.bname !== '') {
                extraAddr += data.bname;
              }
              // 건물명이 있을 경우 추가한다.
              if (data.buildingName !== '') {
                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName
                    : data.buildingName);
              }
              // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
              fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('address1').value = fullAddr;

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_wrap.style.display = 'none';

            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
            document.body.scrollTop = currentScroll;
          },
          // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
          onresize : function(size) {
            element_wrap.style.height = size.height + 'px';
          },
          width : '100%',
          height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
      }
    </script>
  <!-- 주소 API끝 -->

</DIV>
<div class="both"></div>


  <!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 