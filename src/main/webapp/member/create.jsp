<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
 
<script type="text/javascript">
  
  $(function(){
    $.removeCookie('checkId'); // 기존의 쿠기 값을 삭제  
    $.removeCookie('checkPwd'); // 기존의 쿠기 값을 삭제  
    $.removeCookie('checkNickname'); // 기존의 쿠기 값을 삭제  
    $.removeCookie('checkEmail'); // 기존의 쿠기 값을 삭제  
    
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
      var params = 'nickname=' + $('#nickname').val();
      $.post('./checkNickname.do', params, checkNickname_res, 'json');
      });
    
    $('#email').focusout(function(){
      var params = 'email=' + $('#email').val();
      $.post('./checkEmail.do', params, checkEmail_res, 'json');
      });
    
  });
  
  function checkId(){
    var params = 'id=' + $('#userid').val();
    // 요청 주소, 전달 값, 응답 처리 함수, 전송 받는 형식
    $.post('./checkId.do', params, checkId_res, 'json');
  }
  
  function checkId_res(data){
    if(data.cnt == 0){
      $('#panel_id').css('color', '#737373');
      $('#panel_id').html('아이디가 사용 가능합니다.');
      $.cookie('checkId', 'PASS'); // 쿠키 생성
    }else if(data.cnt == 1){
      $('#panel_id').css('color', '#FF0000');
      $('#panel_id').html('아이디가 중복됩니다.');
      $('#userid').focus();
    }
  }
  function send(){
     var check1 = $.cookie('checkId');
     var check2 = $.cookie('checkPwd');
     var check3 = $.cookie('checkNickname');
     var check4 = $.cookie('checkEmail');
     if(check1 != 'PASS'){
        $('#panel_id').css('color','#FF0000')
        $('#panel_id').html('중복 ID 검사를 해주세요');
        $('#id').focus();
        return false;
     } else {
       if(check1 != 'PASS' || check2 != 'PASS' || check3 != 'PASS' || check4 != 'PASS'){
        return false;
        }
     }
     if(check1 == 'PASS'&& check2 == 'PASS' && check3 == 'PASS' && check4 == 'PASS'){
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
  
  

  
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
<DIV class='title'>회원가입</DIV>
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./create.do'
           onsubmit = 'return send()'>
  <fieldset>
    <ul>
      <li>
        <label class='label' for='id'>아이디</label>
        <input type='text' name='userid' id='userid' value='user1' required="required">
        <button type='button' onclick='checkId()'>중복확인</button>
        <SPAN id='panel_id'></SPAN> <!-- ID 중복 관련 메시지 -->
      </li>
      <li>
        <label class='label' for='pwd'>비밀번호</label>
        <input type='password' name='pwd' id='pwd' value='1234' required="required">
      </li>
      <li>
        <label class='label' for='pwd2'>비밀번호 확인</label>
        <input type='password' name='pwd2' id='pwd2' value='1234' required="required">
        <!-- <button type='button' onclick='checkPwd()'>비밀번호 확인</button> -->
        <SPAN id='panel_pwd'></SPAN> <!-- ID 중복 관련 메시지 -->
      </li>
      <li>
        <label class='label' for='name'>성명</label>
        <input type='text' name='name' id='name' value='성명' required="required">
      </li>
      <li>
        <label class='label' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value='닉네임' required="required">
        <SPAN id='panel_nickname'></SPAN>
      </li>
      <li>
        <label class='label' for='tel'>전화번호</label>
        <input type="tel" name='tel' id='tel' value='010-1111-1111'> 예) 010-0000-0000
      </li>
      <li>
        <label class='label' for='email'>이메일</label>
        <input type='email' name='email' id='email' value='test@mail.com' required="required">
        <SPAN id='panel_email'></SPAN>
      </li>
      <li>
        <label class='label' for='zipcode'>우편번호</label>
        <input type='text' name='zipcode' id='zipcode' value='' placeholder="우편번호">
        <input type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>        
      </li>
      <li>
        <label class='label' for='address1'>주소</label>
        <input type='text' name='address1' id='address1' value='' size='60' placeholder="주소">  
      </li>
      <li>
        <label class='label' for='address2'>상세 주소</label>
        <input type='text' name='address2' id='address2' value='' size='40' placeholder="상세 주소">      
      </li>
      <li>
        <label class='label'></label>  
<!-- ----- DAUM 우편번호 API 시작 ----- -->
 
<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>
 
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');
 
    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }
 
    function DaumPostcode() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
 
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수
 
                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){0
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }
 
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address1').value = fullAddr;
 
                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';
 
                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
                
                $('#address2').focus();
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);
 
        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
</script>
<!-- ----- DAUM 우편번호 API 종료----- -->
        
      </li>
      <li class='right'>
        <button type="submit">저장</button>
        <button type="button" onclick="location.href='./list.do'">목록</button>
      </li>         
    </ul>
  </fieldset>
</FORM>
</DIV>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
  