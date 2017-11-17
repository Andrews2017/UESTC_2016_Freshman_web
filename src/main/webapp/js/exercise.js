$().ready(function(){
	$(".upper-name").click(function(){	
		$(".down").slideToggle();
	});
	$('.timu').click(function(){
		$(this).css('color','#ff0000');
		$(".paihang").css('color','#909499');
		$(".page1-exerc,.exerc-tabs").css('display','block');
        $(".page1-info,.paihang-tabs").css('display','none');
        $("#tab2,#tab3,#tab4,#tab5,#tab6,#tab7").hide();
       $("#tabs2,#tabs3,#tabs4,#tabs5,#tabs6,#tabs7").hide();
	});
	$('.paihang').click(function(){
		$(this).css('color','#ff0000');
		$(".timu").css('color','#909499');
		$(".page1-info,.paihang-tabs").css('display','block');
        $(".page1-exerc,.exerc-tabs").css('display','none');
        $("#tab2,#tab3,#tab4,#tab5,#tab6,#tab7").hide();
       $("#tabs2,#tabs3,#tabs4,#tabs5,#tabs6,#tabs7").hide();
	});
	$('li a').click(function(){
		$(this).css('color','#ff0000').children('img.show-list').attr('src','../images/show-down-red.png');
		$('li a').not(this).css('color','#909499').children('img.show-list').attr('src','../images/show-right.png');
	});
	$('li a').not("li.houtai a").click(function(){
		$(".page1-info").css('display','none');
        $(".exercise-content").css('display','none');
	});
	$('li.houtai a').click(function(){
		$(this).children('img.logo').attr('src','../images/houtai.png');
		$('li.qianduan a').children('img.logo').attr('src','../images/html2.png');
		$('li.ios a').children('img.logo').attr('src','../images/ios2.png');
		$('li.anzhuo a').children('img.logo').attr('src','../images/android2.png');
		$('li.sheji a').children('img.logo').attr('src','../images/sheji2.png');
		$('li.yunwei a').children('img.logo').attr('src','../images/yunwei2.png');
		$('li.chanpin a').children('img.logo').attr('src','../images/chanpin2.png');
	});
	$('li.qianduan a').click(function(){
		$(this).children('img.logo').attr('src','../images/html.png');
		$('li.houtai a').children('img.logo').attr('src','../images/houtai2.png');
		$('li.ios a').children('img.logo').attr('src','../images/ios2.png');
		$('li.anzhuo a').children('img.logo').attr('src','../images/android2.png');
		$('li.sheji a').children('img.logo').attr('src','../images/sheji2.png');
		$('li.yunwei a').children('img.logo').attr('src','../images/yunwei2.png');
		$('li.chanpin a').children('img.logo').attr('src','../images/chanpin2.png');
	});
	$('li.ios a').click(function(){
		$(this).children('img.logo').attr('src','../images/ios.png');
		$('li.qianduan a').children('img.logo').attr('src','../images/html2.png');
		$('li.houtai a').children('img.logo').attr('src','../images/houtai2.png');
		$('li.anzhuo a').children('img.logo').attr('src','../images/android2.png');
		$('li.sheji a').children('img.logo').attr('src','../images/sheji2.png');
		$('li.yunwei a').children('img.logo').attr('src','../images/yunwei2.png');
		$('li.chanpin a').children('img.logo').attr('src','../images/chanpin2.png');
	});
	$('li.anzhuo a').click(function(){
		$(this).children('img.logo').attr('src','../images/android.png');
		$('li.houtai a').children('img.logo').attr('src','../images/houtai2.png');
		$('li.ios a').children('img.logo').attr('src','../images/iOS2.png');
		$('li.qianduan a').children('img.logo').attr('src','../images/html2.png');
		$('li.sheji a').children('img.logo').attr('src','../images/sheji2.png');
		$('li.yunwei a').children('img.logo').attr('src','../images/yunwei2.png');
		$('li.chanpin a').children('img.logo').attr('src','../images/chanpin2.png');
	});
	$('li.sheji a').click(function(){
		$(this).children('img.logo').attr('src','../images/sheji.png');
		$('li.houtai a').children('img.logo').attr('src','../images/houtai2.png');
		$('li.ios a').children('img.logo').attr('src','../images/iOS2.png');
		$('li.anzhuo a').children('img.logo').attr('src','../images/android2.png');
		$('li.qianduan a').children('img.logo').attr('src','../images/html2.png');
		$('li.yunwei a').children('img.logo').attr('src','../images/yunwei2.png');
		$('li.chanpin a').children('img.logo').attr('src','../images/chanpin2.png');
	});
	$('li.yunwei a').click(function(){
		$(this).children('img.logo').attr('src','../images/yunwei.png');
		$('li.houtai a').children('img.logo').attr('src','../images/houtai2.png');
		$('li.ios a').children('img.logo').attr('src','../images/iOS2.png');
		$('li.anzhuo a').children('img.logo').attr('src','../images/android2.png');
		$('li.sheji a').children('img.logo').attr('src','../images/sheji2.png');
		$('li.qianduan a').children('img.logo').attr('src','../images/html2.png');;
		$('li.chanpin a').children('img.logo').attr('src','../images/chanpin2.png');
	});
	$('li.chanpin a').click(function(){
		$(this).children('img.logo').attr('src','../images/chanpin.png');
		$('li.houtai a').children('img.logo').attr('src','../images/houtai2.png');
		$('li.ios a').children('img.logo').attr('src','../images/iOS2.png');
		$('li.anzhuo a').children('img.logo').attr('src','../images/android2.png');
		$('li.sheji a').children('img.logo').attr('src','../images/sheji2.png');
		$('li.qianduan a').children('img.logo').attr('src','../images/html2.png');;
		$('li.yunwei a').children('img.logo').attr('src','../images/yunwei2.png');
	});
	$('ul.tabs').each(function(){
	  // For each set of tabs, we want to keep track of
	  // which tab is active and its associated content
	  var $active, $content, $links = $(this).find('a');
	  //var tabs1=$("[href='#tabs1']");

	  // If the location.hash matches one of the links, use that as the active tab.
	  // If no match is found, use the first link as the initial active tab.
	  $active = $($links.filter('[href="'+location.hash+'"]')[0] || $links[0]);
	  $active.addClass('active');

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
	    var category = $(this).children("font.type").html();
	    $.ajax({
	    	url:"/villager/showAllProByCategory/"+category,
	    	method:"GET",
	    	success:function(result){
	    		var arr = result.object;
	    		var str1 = "";
	    		var str2 = "";
	    		var str3 = "";
	    		if(result.status==0){
	    			for(var i=0;i<arr.length;i++){
	    				if(arr[i].level=="入门"){
	    					str1=str1+'<div class="content1">'+
			    			'<img src="../images/rumen.png" class="rumen-tu">'+
							'<font class="exercise-title">'+arr[i].title+'</font>'+
							'<font class="tijiao-cishu">'+'提交次数：'+arr[i].submitCounts+'</font>'+
							'<img src="../images/look-info.png" class="look-info">'+
							'<font class="fenshu">'+'最高得分：'+arr[i].score+'分'+'</font>'+"</div>"
	    				}else if(arr[i].level=="进阶"){
	    					str2=str2+'<div class="content1">'+
			    			'<img src="../images/jinjie.png" class="rumen-tu">'+
							'<font class="exercise-title">'+arr[i].title+'</font>'+
							'<font class="tijiao-cishu">'+'提交次数：'+arr[i].submitCounts+'</font>'+
							'<img src="../images/look-info.png" class="look-info">'+
							'<font class="fenshu">'+'最高得分：'+arr[i].score+'分'+'</font>'+"</div>"
	    				}else{
	    					str3=str3+'<div class="content1">'+
			    			'<img src="../images/gaojie.png" class="rumen-tu">'+
							'<font class="exercise-title">'+arr[i].title+'</font>'+
							'<font class="tijiao-cishu">'+'提交次数：'+arr[i].submitCounts+'</font>'+
							'<img src="../images/look-info.png" class="look-info">'+
							'<font class="fenshu">'+'最高得分：'+arr[i].score+'分'+'</font>'+"</div>"
	    				}
		    			
	    			}
	    			$(".rumen-content").html(str1);
	    			$(".jinjie-content").html(str2);
	    			$(".gaojie-content").html(str3);
	    		}
	    	}
	    })

	    // Prevent the anchor's default click action
	    e.preventDefault();
	  });

	});	
	$(document).on('click','.look-info',function() {
		$(".showDetails").css("display","block");
	});		
});