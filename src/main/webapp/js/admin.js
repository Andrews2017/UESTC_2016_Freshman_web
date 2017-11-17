
$(document).ready(function(){
	$(".upper-name").click(function(){	
		$(".down").slideToggle();
	});
	$("li.list1").click(function(){
		$(this).css("color","#ff0000");
	   $("li").not(this).css("color","#909499");
	   $(this).children("span").children("img").attr("src","../images/show-down-red.png");
	   $("li").not(this).children("span").children("img").attr("src","../images/show-right.png");
       $("div.list-info").css("display","none");
       $("div.exerc-info").css("display","none");
       $("div.sett-info").css("display","none");
       $("div.user-info").css("display","block");
       $("#tab2,#tab3,#tab4,#tab5,#tab6,#tab7").hide();
       $("#tabs2,#tabs3,#tabs4,#tabs5,#tabs6,#tabs7").hide();
	});
	$("li.list2").click(function(){
	   $(this).css("color","#ff0000");
	   $("li").not(this).css("color","#909499");
	   $(this).children("span").children("img").attr("src","../images/show-down-red.png");
	   $("li").not(this).children("span").children("img").attr("src","../images/show-right.png");
       $("div.user-info,div.exerc-info,div.sett-info").css("display","none");
       $("#tab2,#tab3,#tab4,#tab5,#tab6,#tab7").hide();
       $("#tabs2,#tabs3,#tabs4,#tabs5,#tabs6,#tabs7").hide();
       //$("#tab1").show();
       $("div.list-info").css("display","block");

	});
	// // $("[href='#tabs1']").click(function(){
	// // 	$("info").css("display","block");
	// });
	$("li.list3").click(function(){
	   $(this).css("color","#ff0000");
	   $("li").not(this).css("color","#909499");
	   $(this).children("span").children("img").attr("src","../images/show-down-red.png");
	   $("li").not(this).children("span").children("img").attr("src","../images/show-right.png");
       $("div.user-info").css("display","none");
       $("div.list-info").css("display","none");
       $("div.sett-info").css("display","none");
       $("div.exerc-info").css("display","block");
       $("#tab2,#tab3,#tab4,#tab5,#tab6,#tab7").hide();
       $("#tabs2,#tabs3,#tabs4,#tabs5,#tabs6,#tabs7").hide();
       //$("#tabs1").show();

	});
	$("li.list4").click(function(){
	   $(this).css("color","#ff0000");
	   $("li").not(this).css("color","#909499");
	   $(this).children("span").children("img").attr("src","../images/show-down-red.png");
	   $("li").not(this).children("span").children("img").attr("src","../images/show-right.png");
       $("div.user-info").css("display","none");
       $("div.list-info").css("display","none");
       $("div.exerc-info").css("display","none");
       $("div.sett-info").css("display","block");
       $("#tab2,#tab3,#tab4,#tab5,#tab6,#tab7").hide();
       $("#tabs2,#tabs3,#tabs4,#tabs5,#tabs6,#tabs7").hide();
	});
	//退出
	$(document).on("click",".down",function(){
		window.location.href="../html/firstItem.html";
	});
	//查看用户信息
	$(document).on('click','.look-info',function() {
		$(".showDetails").css("display","block");
		var id =$(this).attr('data-userid'); 
		$.ajax({
			url:"/admin/showUserInfo/"+id,
			method:"GET",
			success:function(data){
				var str = "";
				var arr = data.object;
				if(data.status==0){
						str=str+'<p class="name2">'+"姓名："+arr.userName+"</p>"+
						'<p class="sex2">'+"性别："+arr.sex+"</p>"+
						'<p class="school2">'+"专业："+arr.major+'</p>'+
						'<p class="ID2">'+"学号："+arr.userNo+'</p>'+
						'<p class="email2">'+"邮箱："+arr.userEmail+'</p>'+
						'<p class="tel2">电话：null</p>'+
						'<p class="photo2">'+'<a href="'+arr.photo+'">'+"照片（啊啊啊）"+'</a></p>'
					}
					$(".details-info2").html(str);
				}
			})
	});

	//用户上传头像
	$(".picupload").change(function(){
		var picUpload = new FormData();
		picUpload.append("photo",$(".picupload")[0].files[0]);
		$.ajax({
			url:"/user/uploadPic",
			method:"POST",
			data:picUpload,
			processData: false,
			contentType: false,
			success:function(data){
				if(data.status==0){
					alert("上转成功");
				}else{
					alert("失败");
				}
			},
			error:function(xhr){
				alert(xhr.statusText);
			}
		})
	});
	//显示所有用户
	$.ajax({
		url:"/admin/showAllUsers",
		method:"POST",
		success:function(data){
			var str = '<div>';
			var arr = data.object;
			for (var i=0;i<arr.length;i++) {
				if(arr[i].photo==null){
					$(".small-logo").attr("src","../images/xiao-touxiang.png");
				}else{
					$(".small-logo").attr("src",arr[i].photo);
				}
				str=str+'<div class="content1">'+
				'<font><img src="../images/xiao-touxiang.png" class="small-logo"></font>'+
				'<font class="xingming">'+arr[i].userName+"</font>"+
				'<font class="sex">'+"性别："+arr[i].sex+"</font>"+
				'<font class="school">'+"学院："+arr[i].major+"</font>"+
				'<font><img data-userid="'+arr[i].userId+'" src="../images/look-info.png" class="look-info"></font>'+
				'<font class="delete">删除</font></div>'
			}
			str=str+"</div>";
			$(".yanyi-content,.dayi-content,.daer-content").html(str);
		}
	})
	//查看各方向排行榜
	$('ul.tabs').each(function(){
	  // For each set of tabs, we want to keep track of
	  // which tab is active and its associated content
	  var $active, $content, $links = $(this).find('a');
	  //var tabs1=$("[href='#tabs1']");

	  // If the location.hash matches one of the links, use that as the active tab.
	  // If no match is found, use the first link as the initial active tab.
	  $active = $($links.filter('[href="'+location.hash+'"]')[0] || $links[0]);
	  $active.addClass('active');
	  alert(window.location.href);
	  $content = $($active[0].hash);

	  // Hide the remaining content
	  $links.not($active).each(function () {
	    $(this.hash).hide();

	  });

	  // Bind the click event handler
	  $(this).on('click', 'a', function(e){
	    // Make the old tab inactive.
	    $active.removeClass('active');
	    $content.hide();

	    // Update the variables with the new link and content
	    $active = $(this);
	    $content = $(this.hash);

	    // Make the tab active.
	    $active.addClass('active');
	    $content.show();
	    $active.css("color","red");
	    $links.not($active).each(function () {
	    	$(this).css("color","#909499");
	    });
	    var value = $(this).html();
	    $.ajax({
		url:"/admin/RankingDesc/"+value+"?pageNo=1",
		method:"GET",
		success:function(result){
			var resArr = result.object;
			var str2 = "<div>";
			for(var i=0;i<resArr.length;i++){
				//if(resArr[i].totalscore.category == "后台"){
					str2=str2+'<div class="list-content1" class="list-info">'+
			    	'<font class="position1">'+(i+1)+'</font>'+
			    	'<img src="../images/xiao-touxiang.png" class="small-logo">'+
			    	'<font class="xingming">'+resArr[i].totalscore.name+"</font>"+
			    	'<font class="sex">'+"性别："+resArr[i].user.sex+"</font>"+
			    	'<font class="school">'+"学院："+resArr[i].user.major+"</font>"+
					'<font class="fenshu">'+"分数："+resArr[i].totalscore.name+"</font>"+
					'<img src="../images/look-info.png" class="look-info">'+
					'<font class="delete">删除</font>'
				//}else{
					// str2=str2+'<div class="list-content1">'+
			  //   	'<font class="position1">'+i+"1"+'</font>'+
			  //   	'<img src="../images/xiao-touxiang.png" class="small-logo">'+
			  //   	'<font class="xingming">'+resArr[i].totalscore.name+"</font>"+
			  //   	'<font class="sex">'+"性别："+resArr[i].user.sex+"</font>"+
			  //   	'<font class="school">'+"学院："+resArr[i].user.major+"</font>"+
					// '<font class="fenshu">'+"分数："+resArr[i].totalscore.name+"</font>"+
					// '<img src="../images/look-info.png" class="look-info">'+
					// '<font class="delete">删除</font>'
				//}
			}
			str2=str2+"</div>";
			$(".list-yanyi-content,.list-dayi-content,.list-daer-content").html(str2);
		}
	})
	$.ajax({
		url:"/villager/showAllProByCategory/"+value,
		method:"GET",
		success:function(data){
			if(data.status==0){
				
			}
		}
	})
	    // Prevent the anchor's default click action
	    e.preventDefault();
	  });
	});			
	// var houtai = $("#tab1");
	// var qianduan = $("#tab2");
	// var ios = $("#tab3");
	// var anzhuo = $("#tab4");
	// var yunwei = $("#tab5");
	// var sheji = $("#tab6");
	// var chanpin = $("#tab7");

	//导出excel
	$(document).on("click",".download-info",function(){
		$.ajax({
			url:"/admin/exportExcel",
			method:"POST",
			success:function(result){
				if(result.status==0){
					alert("修改成功!");
				}else{
					alert(result.statusText);
				}
			}
		})
	});
	//管理员发布题目
	$(document).on('click','.show-exerc,.add-exerc',function() {
		$(".showExerc").css("display","block");
	});
	$(document).on("click",".confirm-btn",function(){
		var title = $(".ex-title").val();
		var category = $(".zubie").val();
		var content = $(".neirong").val();
		var score = $(".zongfen").val();
		var level = $(".nandu").val();
		$.ajax({
			url:"/admin/publishProblem/"+category,
			method:"GET",
			data:{
				title:title,
				content:content,
				score:score,
				level:level
			},
			success:function(data){
				if(data.status==0){
					alert("发布成功");
					window.location.reload();
				}
			},
			error:function(xhr){
				alert("请先填好题目信息!"+"  "+xhr.statusText);
			}
		})
	});
	$(document).on("click",".cancel",function(){
		$(".showExerc").css("display","none");
	});
});