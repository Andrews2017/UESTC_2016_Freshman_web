$(document).ready(function(){
	$("#show-register").click(function(){
			$(".login-form").slideUp();
		    $(".register-form").slideDown();
		});
	$("#show-login").click(function(){
		$(".register-form").slideUp();
		$(".login-form").slideDown();
	});
	$(".signup-submit").click(function(){
		var lgEmail = $(".login-email").val();
		var lgPsword = $(".login-password").val();
		$.ajax({
			url:"/login",
			method:"POST",
			data:{ userEmail:lgEmail , userPasswd:lgPsword },
			success:function(){
				window.location.href="../html/admin.html";
			},
			error:function(){
				alert("请先注册");
		    }
	    });
	});
	
	$(".reg-submit").click(function(){
		var rgEmail = $(".reg-email").val();
		var rgPsword1 = $(".reg-password1").val();
		var rgPsword2 = $(".reg-password2").val();
		var stdID = $(".stdID").val();
		var idNumber = $(".idNum").val();
		var codeNumber = $("#code-number").val();
		$.ajax({
		url:"/register",
		method:"POST",
		data:{ userEmail:rgEmail,
			   passwd:rgPsword1, 
			   rePasswd:rgPsword2,
               stdNo:stdID,
               idNum:idNumber,
               checkedWords:codeNumber
		    },
		error:function(xhr){
			alert(xhr.statusText+" "+"验证码错误");
		}
	    });
	});
});	
