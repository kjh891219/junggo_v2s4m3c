 $(document).ready(function(){
    
    $("#left_list_f").css('background-color','#3b78ce').css('color','white');
    $("#left_list_f").next().css('display','block');
    
    
    $(".left_list").mouseenter(function(){
      $("#left_list_f").css('background-color','transparent').css('color','black');
      $("#left_list_f").next().css('display','none');
      $(this).css('background-color','#3b78ce').css('color','white');
      $(this).next().css('display','block');
      /* Toggle('on'); .delay(5000) */
       $(".left_detail").mouseenter(function(){
          $("#left_list_f").css('background-color','transparent').css('color','black');
          $("#left_list_f").next().css('display','none');
         $(this).prev().css('background-color','#3b78ce').css('color','white');
         $(this).css('display','block');
             
       });     
       $(".left_detail").mouseleave(function(){
          $(this).prev().css('background-color','transparent').css('color','black');
          $(this).css('display','none');
          $("#left_list_f").css('background-color','#3b78ce').css('color','white');
          $("#left_list_f").next().css('display','block');
       });     
    });
    
    $(".left_list").mouseleave(function(){
          
          $(this).css('background-color','transparent').css('color','black');
          $(this).next().css('display','none');
          
          $("#left_list_f").css('background-color','#3b78ce').css('color','white');
          $("#left_list_f").next().css('display','block');
          
    });     
       
       /* Toggle('on'); .delay(5000) */
    
 });
 
 
 function message(){
   var url = '<%=root%>/message/list.do?flag=recv';
   var win = window.open(url, '쪽지함', 'width=600px, height=700px');
   var x = (screen.width - 500) / 2;
   var y = (screen.height - 440) / 2;
   
   win.moveTo(x, y); // 화면 가운데로 이동
 }

