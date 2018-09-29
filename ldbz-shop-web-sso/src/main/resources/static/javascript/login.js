$(function(){

    //用户名失去焦点校验
    $("#username").blur(function(){
    	var _nick = $(this).val();
    	if($.trim(_nick).length==0){
    			layer.tips("用户名不能为空", '#username', {
    				tips: [1, '#3595CC'],
    				time: 2000
    			});
    	}
    });
    
    //密码失去焦点校验
    $("#password").blur(function(){
    	var _nick = $(this).val();
    	if($.trim(_nick).length==0){
    		layer.tips("密码不能为空", '#password', {
    			tips: [1, '#3595CC'],
    			time: 2000
    		});
    	}
    });
    
    layui.use(['layer' , 'form'] , function(){
    	  var form = layui.form , layer = layui.layer;
    	  
    	  //监听提交
    	  form.on('submit(btn_login)', function(data){
    		  $.post(contextPath+"/user/login" , data.field , function(ret){
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