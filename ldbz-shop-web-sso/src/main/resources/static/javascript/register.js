$(function(){

    //重新获取验证码
    $("#refreshCode").click(function(){
    	var src = $(this).attr("src");
    	$(this).attr("src" , src+"&r="+Math.random()) ;
    });
    
    //发送邮件验证码
    $("#sendEmail").click(function(){
    	var email = $("#email").val();
    	$.post(contextPath+"/notifyuser/emailCode" , {
    		email : email 
    	} , function(ret){
    		var json = $.parseJSON(ret) ;
    		
    	})
    });
    
    //用户名失去焦点校验，是否可用
    $("#regName").blur(function(){
    	var _nick = $(this).val();
    	if($.trim(_nick).length>1){
    		$.post(contextPath+"/validateuser/nick" , {
    			email : '' ,
    			regName : _nick
    		} , function(ret){
    			var json = $.parseJSON(ret) ;
    			layer.tips(json.info, '#regName', {
    				tips: [1, '#3595CC'],
    				time: 2000
    			});
    		});
    	}
    });
    
    //邮箱失去焦点校验，是否可用
    $("#email").blur(function(){
    	var _email = $(this).val();
    	if($.trim(_email).length>1){
    		$.post(contextPath+"/validateuser/email" , {
    			regName : '' ,
    			email : _email
    		} , function(ret){
    			var json = $.parseJSON(ret) ;
    			layer.tips(json.info, '#email', {
    				tips: [1, '#3595CC'],
    				time: 2000
    			});
    		});
    	}
    });
    
    layui.use(['layer' , 'form'] , function(){
    	  var form = layui.form , layer = layui.layer;
    	  
    	  //监听提交
    	  form.on('submit(btn_register)', function(data){
    		  $.post(contextPath+"/registerByEmail" , data.field , function(ret){
    			  var json = $.parseJSON(ret) ;
    			  if(!json.success){
    				  layer.msg(json.info);
    			  }else{
    				  location.href = json.info ;
    			  }
    		  });
    		  return false;
    	  });
    });
    
})