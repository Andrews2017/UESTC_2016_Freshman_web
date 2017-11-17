$(document).ready(function(){
	$(".upper-name").click(function(){	
		$(".down").slideToggle();
	});
	$(".weipigai").click(function(){
		$(this).css("color","red");
		$(".paihang").css("color","#909499");
		$(".paihang-info-content").css("display","none");
		$(".user-info-content").css("display","block");
	});
	$(".paihang").click(function(){
		$(this).css("color","red");
		$(".weipigai").css("color","#909499");
		$(".paihang-info-content").css("display","block");
		$(".user-info-content").css("display","none");
	});

	//显示每道题目的提交详情
	$(document).on("click",'.tijiao-xiangxi',function(){
		$(".tijiao-xiangxi-box").css("display","block");
		var userId = $(this).attr("userId");
		var proId = $(this).attr("proId");
		$.ajax({
			url:"/correct/showSubmitDetail/"+userId+"/"+proId,
			method:"GET",
			success:function(data){
				var arr1= data.object.problemInfo;
				var arr2=data.object.submitInfo;
				var str1="";
				var str2="";
				var str3="";
				if(data.status==0){
					str1=str1+'<h2 class="timu-name">名称：'+'<span class="name2">'+arr1.title+'</span></h2>'+
					'<font class="fenshu-title"><h4>分数：'+'<span class="point">'+arr1.score+'</span></h4></font>'
					str2=str2+'<h3>任务目的</h3>'+'<p>'+arr1.content+'</p>'
					for(var i=0;i<arr2.lenght;i++){
						//if(arr2[i].correctId==1){
							str3=str3+'<div class="timu-details-1">'+
					    	'<font class="cishu">第<span>'+(i+1)+'</span>次</font>'+
					    	'<font class="check" score="'+arr2[i].score+'" comment="'+arr2[i].comment+'" proId="'+arr1.proId+'" answerId="'+arr2[i].answerId+'"><img src="../images/timu-check.png"></font>'+
					    	'<font class="fenshu2"><span>'+arr2[i].score+'</span>分</font>'+
					    	'<font class="download" answerId="'+arr2[i].answerId+'"><img src="../images/download.png"></font>'+'</div>'
						//}
					}
				}
				$(".title2").html(str1);
				$(".content4").html(str2);
				$(".timu-details-content").html(str3);
			}
		})
	});
	$(".check").click(function(){
		$(".comment-page").css("display","block");
	});
	$(".close-comment").click(function(){
		$(".comment-page").css("display","none");
	});
	//对题目进行打分、写评语
	$(document).on("click",".confirm-btn",function(){
		var answerId=$(".check").attr(answerId);
		var score=$(".mark-area").val();
		var comment=$(".comment-area").val();
		$.ajax({
			url:"/correct/checkPro/"+answerId,
			method:"GET",
			data:{
				score:score,
				comment:comment
			},
			success:function(result){
				if(result.status==0){
					//$(".check").changeAttr("score",score);
					//$(".check").changeAttr("comment",comment);
					alert("题目打分与评语发布成功")
				}
			}
		})
	});

	//显示最近提交未被批改的题目
	$.ajax({
		url:"/correct/showUncorrectList",
		method:"GET",
		success:function(result){
			if(result.status==0){
				var arr =result.object.unCorrectList;
				var str="<div>";
				for(var i=0;i<arr.length;i++){
					str=str+'<div class="content1">'+
					'<font class="xingming">'+arr[i].username+'</font>'+
			        '<font class="tijiao-date">'+'提交时间：'+'<span class="date">'+arr[i].submitTime+'</span></font>'+
			        '<font class="timu-id">'+'问题ID:'+arr[i].proId+'</font>'+
			        '<font class="tijiao-cishu">'+'提交次数：'+arr[i].submitCounts+'</font>'+
			        '<font class="fenshu">'+'最高分数：'+arr[i].highscore+'分'+'</font>'+
					'<font class="tijiao-xiangxi" proId="'+arr[i].proId+'" userId="'+arr[i].proId+'">提交详细</font>'
				}
				str=str+"</div>";
				$(".eval-yanyi-content,.eval-dayi-content,.eval-daer-content").html(str);
			}
		}
	})
	//改题人获得自己方向的排名
	$.ajax({
		url:"/correct/rankListAll",
		method:"GET",
		success:function(result){
			var arr=result.object;
			var str = "";
			if(result.status==0){
				for(var i=0;i<arr.length;i++){
					str =str+'<div class="content1">'+
					'<font class="position">'+'<span class="position-nb">'+(i+1)+'</span></font>'+
			        '<font class="profile-pic"><img src="../images/xiao-touxiang.png"></font>'+
			        '<font class="xingming-ph">'+arr[i].name+'</font>'+
			        '<font class="fenshu-ph">'+'分数：<span class="points">'+arr[i].score+'</span>分</font>'
				}
				str=str+"</div>";
				$(".yanyi-content").html(str);
			}
		}
	})
	//改题人下载题目答案
	$(document).on("click",".download",function{
		var fileName=$(".check").attr(answerId);
		$.ajax({
			url:"/correct/downLoadAnswer",
			method:"GET",
			data:fileName,
			success:function(data){
				if(data.status==0){
					alert("下载题目答案成功!")
				}
			}
		})
	})
});