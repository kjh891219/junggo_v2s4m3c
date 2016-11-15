   $(document).ready(function(){
      
         
         $('.homeiFrame').css('display','block');
         
         
         $(".left_list").mouseenter(function(){
           $('.homeiFrame').css('display','none');
           $(this).css('background-color','transparent').css('color','#3b78ce');
           $(this).next().css('display','block');
           $(this).next().find('.left_iframe').css('display', 'block');
           /* Toggle('on'); .delay(5000) */
            $(".left_detail").mouseenter(function(){
               $('.homeiFrame').css('display','none');
               $(this).prev().css('background-color','transparent').css('color','#3b78ce');
               $(this).css('display','block');
               $(this).find('.left_iframe').css('display', 'block');
               
               $(".left_detail_list").children().mouseenter(function(){
                  $(this).find('iframe').css('display','block');
                     $('iframe').mouseeter(function(){
                        $(this).parent().parent().parent().prev().css('background-color','transparent').css('color','#3b78ce');
                        $(this).parent().parent().parent().css('display','block');
                        
                     });
                     
                     $('iframe').mouseleave(function(){
                        $(this).css('display','none');
                        $(this).parent().parent().parent().prev().css('background-color','#3b78ce').css('color','white');
                        $(this).parent().parent().parent().css('display','none');
                        $('.homeiFrame').css('display','block');
                        
                     });
                  });
               $(".left_detail_list").children().mouseleave(function(){
                  $(this).find('iframe').css('display','none');
                  $(this).find('.left_iframe').css('display', 'block');
                  });
               
               $('iframe').mouseeter(function(){
                  $('.homeiFrame').css('display','none');
                  $(this).parent().parent().parent().prev().css('background-color','transparent').css('color','black');
                  $(this).parent().parent().parent().css('display','block');
                  $(this).css('display','block');
                  
               });
   
               $('iframe').mouseleave(function(){
                  $(this).css('display','none');
                  $(this).parent().parent().parent().prev().css('background-color','#3b78ce').css('color','white');
                  $(this).parent().parent().parent().css('display','none');
                  $('.homeiFrame').css('display','block');
   
               });
                  
            });     
            
            $(".left_detail").mouseleave(function(){
               $(this).prev().css('background-color','#3b78ce').css('color','white');
               $(this).css('display','none');
               $('.homeiFrame').css('display','block');
            });     
         });
         
         $(".left_list").mouseleave(function(){
               
               $(this).css('background-color','#3b78ce').css('color','white');
               $(this).next().css('display','none');
               $(this).find('.left_iframe').css('display', 'none');
               $('.homeiFrame').css('display','block');
         });     
            
            /* Toggle('on'); .delay(5000) */
         
         
   
   });
   
   $(document).ready(function(){
      $("#downs").click(function(){
         if($("#down_b").css("display") == "none"){
            $('#down_b').slideDown('slow', function() {
              $(this).show();
              $("#down_img").attr("src","./images/drop_up.png");
            });
         }else{
            $('#down_b').slideUp('slow', function() {
              $(this).hide();
            $("#down_img").attr("src","./images/drop_down.png");
            });
         }
       });
      });
   
   
   function message(){
      var url = '${pageContext.request.contextPath}/message/list.do?flag=recv';
      var win = window.open(url, '쪽지함', 'width=600px, height=700px');
      
      var x = (screen.width - 500) / 2;
      var y = (screen.height - 440) / 2;
      
      win.moveTo(x, y); // 화면 가운데로 이동
    }