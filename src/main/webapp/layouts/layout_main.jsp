<%--
  Created by IntelliJ IDEA.
  User: 天心
  Date: 2016/6/11
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<c:set var='static_host' value='https://www.xracoon.com/s'/>
    <title><decorator:title/></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="${static_host}/font-awesome/latest/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/site.css">
    <link href="<%=request.getContextPath()%>/static/pace-1.0.2/themes/red/pace-theme-flash.css" rel="stylesheet" />
    <link rel="stylesheet" href="${static_host}/lobibox/latest/css/lobibox.css" />
    <link rel="stylesheet" href="${static_host}/lobibox/latest/css/animate.css" />
    <script src="<%=request.getContextPath()%>/static/jquery/js/jquery-2.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/pace-1.0.2/pace.js"></script>
	<style>
		#layout-main{
			position:relative;
			flex-grow:1;
			margin-top:70px;
		}
	</style>   
    <decorator:head/>

</head>
<body class="${OWLET_THEME}">
	<div class="navi-bar" class="clearfix" style="z-index:999;">		
		<ul class="nav" class="clearfix">
			<%-- <li id="home"><a href="<%=request.getContextPath()%>/home"><i class="fa fa-puzzle-piece" aria-hidden="true"></i> 主页</a></li> --%>
			<li id="note"><a href="<%=request.getContextPath()%>/article" target="_self"><i class="fa fa-book" aria-hidden="true"></i> 笔记</a></li>
			<li id="series"><a href="<%=request.getContextPath()%>/article/series" target="_self"><i class="fa fa-list-ol" aria-hidden="true"></i> 系列</a></li>
			<li id="tool"><a href="<%=request.getContextPath()%>/tool"><i class="fa fa-cubes" aria-hidden="true"></i> 工具</a></li>
			<li id="toollink"><a href="<%=request.getContextPath()%>/article/36"><i class="fa fa-hand-o-right" aria-hidden="true"></i> 工具链接</a></li>
			<%-- <li id="aboutme"><a href="<%=request.getContextPath()%>/article/173"><i class="fa fa-user-secret" aria-hidden="true"></i> 关于我</a></li> --%>
			<li id="tag"><a href="<%=request.getContextPath()%>/tag"><i class="fa fa-tags" aria-hidden="true"></i> 标签</a></li>
			<li style="display:none;" id="asset"><a href="<%=request.getContextPath()%>/link" target="_self"><i class="fa fa-link" aria-hidden="true"></i> Link</a></li>
			<li style="display:none;" id="project"><a><i class="fa fa-cubes" aria-hidden="true"></i> Project</a></li>
			
			<li class="non-navi" style="float:right;">
				<div style="padding: 10px 20px; line-height: 35px;" class="span-row">
					<span><a id="tag-color-mode" href="#"><i class='fa ${(OWLET_THEME eq "night-mode")?"fa-moon-o":"fa-sun-o"}' aria-hidden="true"></i>&nbsp;</a> </span>
					<c:if test="${not empty OWLET_USER}">
						<input type="hidden" name="uid" value="${OWLET_USER.id}"/>
						<span><a href="#"><i class="fa fa-user" aria-hidden="true"></i> ${OWLET_USER.username}</a></span>
	  	    			<span><a href="<%=request.getContextPath()%>/user/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> exit</a></span>
	  	    		</c:if>	 
				</div> 	
			</li>
			<li class="non-navi" style="float:right;">
				<form action="<%=request.getContextPath()%>/article" method="GET">
					<div class="combine" style="margin-top:14px;">
						<input name="kw" class="combine-in" style="padding: .36em;" placeholder="综合搜索- (标题)" />
						<!-- <a class="btn combine-in" style="border-left-width:0;" href="#">Search</a> -->
						<button type="submit" class="btn combine-in" style="border-left-width:0;" >Search</button>
					</div>
				</form>
			</li>
		</ul>  	
	</div>
	
	<div id="layout-main">
    	<decorator:body/>
   	</div>

    <div id="layout-footer" class="line">
        <%-- 方案1 --%>
        <%-- <div class="center clearfix" style="margin:10 auto 0 auto; width:800px;">
        	<div class="fl" style="width:300px; padding-top:20px; text-align:right;">
        		<div><i class="fa fa-envelope" aria-hidden="true"></i> <i class="fa fa-weibo" aria-hidden="true"></i> xxxxxx</div>
        		<div><i class="fa fa-qq" aria-hidden="true"></i> xxxxx</div>
        		<div><i class="fa fa-weixin" aria-hidden="true"></i> xxxx</div>
        	</div>
        	<div class="fl" style="width:200px; ">
        		<div style="width:100px; height:100px; margin:0 auto; background-color:yellow;">
        			<img src="<%=request.getContextPath()%>/static/imgs/qr.png" style="width:100px; height:100px;" alt="测试信息" />
        		</div>
        	</div>
        	<div class="fl" style="width:300px; padding-top:20px;">
        		<div><i class="fa fa-copyright" aria-hidden="true"></i> xxxx</div>
        		<div>设计 xracoon</div>
        		<div><img src="<%=request.getContextPath()%>/static/imgs/ghs.png" style="width:15px; height:15px;" alt="" /> 京ICP备16041683</div>
        	</div>
        </div> --%>
        
        <div style="margin:0 auto 15px auto; width: 255px;">
        	<span> @2016 - </span>
        	<span> XRacoon - </span>
        	<span> 京ICP备16041683 </span>
        </div>
        
        <div class="fixed-btn">
		    <a class="go-top btn hide" href="#"> <i class="fa fa-angle-up"></i></a>
		    <a class="writer btn green" href="<%=request.getContextPath()%>/article/new" data-toggle="tooltip" data-placement="left" title="" data-original-title="写篇文章"><i class="fa fa-pencil"></i></a>
		</div>
    </div>
    
    
    
    
<!--     <script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?8ea622be3ae21b631b6c4bb3cb52e670";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
	</script> -->
</body>
</html>

 <script src="${static_host}/lobibox/latest/js/lobibox.js"></script>
 <script>
    $(function(){    	
    	//具体页面head节点指定激活哪项 <meta name="navi" content="note"/>
    	$(".nav .active").removeClass("active");
    	$(".nav #"+$("meta[name=navi]").attr("content")).addClass("active");
    	
    	$("#tag-color-mode").click(function(){    		
    		var is_night=$("body").hasClass("night-mode");
    		
    		$.ajax({
    			url: "<%=request.getContextPath()%>/user/theme",
    			data: {theme: is_night?"day-mode":"night-mode"},
    			type: "post",
    			cache: false,
    			dataType:'json',
    			success:function(data) {  
    		        if(data.code==0){  
    		    		//$("body").toggleClass("day-mode");
    		    		//$("body").toggleClass("night-mode");
    		        	//$("#tag-color-mode i").attr("class", "fa "+(is_night?"fa-sun-o":"fa-moon-o")); 
    		        	window.location.reload();
    		        }else{  
    		        	alert("失败! "+data.msg);  
    		            //view(data.msg);  
    		        }  
    		     },  
    		     error : function() {   
    		          alert("异常！");  
    		     }  
    		});
    	});
    	
    	$(window).scroll(function () {
    		if($(window).scrollTop()>100){
    			$(".go-top").removeClass("hide");
    		}
    		else{
    			$(".go-top").addClass("hide");
    		}
    		
/*     		if ($(window).scrollTop() == $(document).height() - $(window).height()) {
    			alert('bottom!!');
    		} */
    	});
    }); 
    
	function notify(type, msg){
		//info, success, error, warning
		Lobibox.notify(type, {
			soundPath: '<%=request.getContextPath()%>/static/lobibox/sounds/',
			
			showClass: 'zoomInDown',   //animate.css:   rollIn, bounceIn, fadeInDown, zoomInUp
	        hideClass: 'zoomOutUp',  //animate.css: rollOut, bounceOut, fadeOutUp, zoomOutDown
	        //title: 'Info title', //false
	        //sound: true,
	        //icon: false, //true
	        delay: type=='error'?8000:2000, //false
	        position: 'top right',  //top left,  top right,  bottom left, bottom right
	        //size: 'large',  //mini, normal
	        // img: IMG_PREFIX + '1.jpg',
	        // closable,  //true
	        // delayIndicator: true,   //show indicator
	        // closeOnClick: true,   
	        width: $(window).width(),
	        msg: msg&&msg.length>100?msg.substr(0,100):msg
    	});
	}
</script>



