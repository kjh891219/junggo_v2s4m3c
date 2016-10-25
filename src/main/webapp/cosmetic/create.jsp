<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

});
</script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
    
};
</script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->

<DIV class='title'>물품 등록</DIV>

<DIV class='content' >
<FORM name='frm' method='POST' action='./create.do'  
                                enctype="multipart/form-data">
 <input type='hidden' name='cno' id='cno' value='2'>                                
  <fieldset>
    <ul>
    
      <li>
        <label class='label' for='category'>분류</label>
           <select name='category' id='category'>
            <option value="여성화장품" selected="selected">여성화장품</option>
            <option value="남성화장품">남성화장품</option>
            <option value="메이크업/클렌징">메이크업/클렌징</option>
            <option value="향수/아로마">향수/아로마</option>
            <option value="헤어/바디케어">헤어/바디케어</option>
            <option value="기타미용제품">기타미용제품</option>
           </select>
           
      </li>
      <li> 
        <label class='label' for='writer'>글쓴이</label>
        <input type='text' name='writer' id='writer' value='글쓴이' required="required">
        <label  class='label' for='passwd'>비밀번호</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required">
      </li>
      <li>  
        <label class='label' for='deal_code' >거래 구분</label>
        <select name='deal_code' id='deal_code'>
            <option value="팝니다" selected="selected">팝니다</option>
            <option value="삽니다">삽니다</option>
            <option value="교환">교환</option>
            <option value="무료드림">무료드림</option>
            <option value="판매완료">판매완료</option>
            <option value="구매완료">구매완료</option>
        </select>        
      </li>   
      <li>  
        <label class='label' for='product_code'>상품 구분</label>
        <select name='product_code' id='product_code'>
            <option value="중고품" selected="selected">중고품</option>
            <option value="신상품">신상품</option>
        </select>        
      </li>
     
      <li>  
        <label class='label' for='region'>거래 지역</label>
        <select name='region' id='region'>
           <option value="서울" selected="selected">서울</option>
           <option value="인천">인천</option>
           <option value="대구">대구</option>
           <option value="대전">대전</option>
           <option value="광주">광주</option>
           <option value="울산">울산</option>
           <option value="부산">부산</option>
           <option value="경기">경기</option>
           <option value="강원">강원</option>
           <option value="경북">경북</option>
           <option value="경남">경남</option>
           <option value="전북">전북</option>
           <option value="전남">전남</option>
           <option value="충남">충남</option>
           <option value="충북">충북</option>
           <option value="제주">제주</option>
        </select>
      </li> 
    
      <li>  
        <label class='label' for='deal_way' >거래방식</label>
        <select name='deal_way' id='deal_way'>
            <option value="직거래" selected="selected">직거래</option>
            <option value="택배">택배</option>
            <option value="등기우편">등기우편</option>
            <option value="안전거래">안전거래</option>
        </select>        
      </li>     
           
      <li> 
        <label class='label' for='hprice'>희망가격</label>
        <input type='text' name='hprice' id='hprice' value='1000' required="required">
      </li>
      
      <li> 
        <label class='label' for='purc_date'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='xxxx-xx-xx' required="required">
      </li>
      
      <li> 
        <label class='label' for='quantity'>수량</label>
        <input type='text' name='quantity' id='quantity' value='1' required="required">
      </li>
      
      <li> 
        <label class='label' for='email'>이메일</label>
        <input type='text' name='email' id='email' value='xxx@xxx.com' required="required">
      </li>
      
      <li> 
        <label class='label' for='tel'>연락처</label>
        <input type='text' name='tel' id='tel' placeholder='000-0000-0000' required="required">
      </li>
      
      <li> 
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' placeholder='제목을 입력하세요' required="required">
      </li>
      
      <li> 
        <label class='label' for='content'>글내용</label>
        <textarea rows="10" cols="40" name="content"  id="content" placeholder="내용을 입력하세요." ></textarea>
      </li>            
      
      <li> 
        <label class='label' for='userid'>아이디</label>
        <input type='text' name='userid' id='userid' value='userid' required="required">
      </li>
       <li> 
        <label class='label' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value='nickname' required="required">
      </li>
      
       <li>  
        <label for='file1'>Thumb 파일1</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
      </li>
      
      <li>
        <label for='file2MF'>업로드 파일1</label>
        <input type="file" name='file2MF' id='file2MF' size='40'>
      </li>
       <br>
       
        <li>  
        <label for='file3'>Thumb 파일2</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
      </li>
  
      <li>
        <label for='file4MF'>업로드 파일2</label>
        <input type="file" name='file4MF' id='file4MF' size='40'>
      </li>
       <br>
           
       <li>  
        <label for='file5'>Thumb 파일3</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
      </li>
     
      <li>
        <label for='file6MF'>업로드 파일3</label>
        <input type="file" name='file6MF' id='file6MF' size='40'>
      </li>
      
      <br>
      
       <li>  
        <label for='file7'>Thumb 파일4</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
      </li>
       
      <li>
        <label for='file8MF'>업로드 파일4</label>
        <input type="file" name='file8MF' id='file8MF' size='40'>
      </li>
      <br>
      
       <li>  
        <label for='file9'>Thumb 파일5</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
      </li>
       
      <li>
        <label for='file10MF'>업로드 파일5</label>
        <input type="file" name='file10MF' id='file10MF' size='40'>
      </li>
      
      <br>
       <li class='right'>
        <button type="submit">등록</button>
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