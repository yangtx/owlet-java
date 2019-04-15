<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en" class="fuelux">
  <head>
    <meta charset="utf-8">
    <meta name="navi" content="note"/>
    <title>笔记</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/font-awesome/css/font-awesome.min.css">
  	<style>
   		.toolbar{
    		list-style: none;
  			color:white;
  			padding:0;
  			margin-top: 20px;
  		} 
  		.toolbar li{
  			float:left;
  			display:block;
  			background-color:darkgray;
  			margin-right:1px;
  		}
  		.toolbar li.group-in:last-child{
  			margin-right: 0;
  		} 
  		
  		.article-list{
  			list-style: none;
  			padding-top: 10px;
   			-webkit-margin-before: 0;
		    -webkit-margin-after: 0;
		    -webkit-margin-start: 0px;
		    -webkit-margin-end: 0px;
		    -webkit-padding-start: 0; 
  		}
  		.article-list>li{
  			position:relative;
  			width: 100%;
  			border-width:0 0 1px 0;
  			border-style: dashed;
  			box-sizing: border-box;
  			word-wrap: break-word;
  			padding: 15px 5%;
  			/* margin: 8px 0 8px 0; */
  		}
		.article-list .wrap-img {
		    float: right;
		}
		.article-list .wrap-img img{			
  			/*width: 80px;*/
		    height: 60px;
		    width: auto\9;
		    vertical-align: middle;
  		}
  		.article-list li:last-child{
  			border-width:0;
  		}
/*   		.articlelist li:hover{
  			background-color:#fff;
  		} */
  		.article-list .article-block{
  			padding-right:140;
  		}

  		.article-list h2{
  			font-size: 1.4em;
  			font-weight: normal;
  			dispaly: inline;
  		}
  		.article-list .article-status{
  			margin: 10px 0;
  		}
  		.article-list .abstract{
  			max-height:30px;
  			overflow:hidden;
  		}
  		.pop-left > a:first-child{
  			color:  #3bf;
  		}
  		.pop-left > a:last-child{
  			color:  #f55;
  		}
  	</style>
  	<style>
  		#page-main{
  			margin: 0 auto;
  			display: flex;
			justify-content: center;
  		}
  		#page-left{
  			width:12rem; 
			height:30rem;
  		}
  		#page-center{
  			max-width: 1000px;
  			flex-grow: 1;
  		}
  		#page-right{
  			width:10rem;
  			display:none; 
  		}
  		#index-tag-watermark{
  			pointer-events: none;
			background-size: 100%;
			background-repeat: no-repeat;
			background-position: 75px -50px;
			opacity: 0.1;
			filter: alpha(opacity = 10);
			-moz-opacity: 0.1;
  		}
  		
  		#page-left ul{
  			list-style:none;
  			padding-left:0;
  		}
  		#page-left li{
  			overflow: hidden;
  			font-size:1rem;
  			margin: 0.5rem 1rem 0.5rem 0;
  			padding: 0.25rem 0;
  			border: 1px solid transparent;
  			position:relative;
  		}
  		.tag-rank-bgimage{
  			position: absolute;
  			width:100%;
  			height:100%;
  			left:0;
  			top:0;
  			
  			background-size: 2rem;
			background-repeat: no-repeat;
			background-position:right center;
			opacity: 0.2;
			filter: alpha(opacity = 20);
			-moz-opacity: 0.2;
  		}
  	</style>
  </head>
  <body>

  	<div id="page-main" class="clearfix">
   		<div id="page-left" class="fl">
   			<h2>标签排行</h2>
  			<ul>
  				<c:forEach items="${tagRank}" var="tr">
  					<li>
  					  	<c:if test="${tr.img!=null }">
  							<div class="tag-rank-bgimage" style="background-image: url(<%=request.getContextPath()%>/assets/tag_icons/${tr.img });">
	  						</div>
				   		</c:if>
				   		<span>
				   			<a href="<%=request.getContextPath()%>/article?tag=${vart.id}">${tr.tname } (${tr.count})</a>
				   		</span>
  					</li>
  				</c:forEach>
  			</ul>
  		</div>
	  	<div id="page-center">
	  	    <ul class="toolbar clearfix" style="padding: 0 5%;">
	  	    	<c:if test="${not empty OWLET_USER}">
	  	    		<li><a class="btn" href="<%=request.getContextPath()%>/article/new"><i class="fa fa-plus" aria-hidden="true"></i></a></li>
	  	    	</c:if>
	  	    	<li style="display:none; margin-left: 1em;"><a class="btn" href="#"><i class="fa fa-align-justify" aria-hidden="true"></i></a></li>
	  	    	<li style="display:none;"><a class="btn" href="#"><i class="fa fa-th-list" aria-hidden="true"></i></a></li>
	  	    	<li style="display:none;"><a class="btn" href="#"><i class="fa fa-table" aria-hidden="true"></i></a></li>
	  	    	<li style="display:none;"><a class="btn" href="#">条件过滤</a></li>
	  	    </ul>
	  	    
	  	    <ul class="article-list">
	  	    	<c:forEach items="${it}" var="var">
		  	    	<li class='clearfix line ${var.status==0?"draft":"" }' lang="${var.id}">
		  	    		<c:if test="${not empty OWLET_USER}">
			  	    		<div class="pop-left">
								<a type="button" class="btn edit" data-idx="${var.id}" href="<%=request.getContextPath()%>/article/${var.id}/edit"> 
									<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
								</a>
								<a type="button" class="btn" href="<%=request.getContextPath()%>/article/delete/${var.id}"> 
									<i class="fa fa-trash-o" aria-hidden="true"></i>
								</a>
							</div>
						</c:if>
						<c:if test="${tagMap.get(var.id)!=null && tagMap.get(var.id).size()>0 && tagMap.get(var.id).get(0).img!=null }">
		  	    			<div class="wrap-img"><img src="<%=request.getContextPath()%>/assets/tag_icons/${tagMap.get(var.id).get(0).img }"/></div>
		  	    		</c:if>
		  	    		
		  	    		<div class="article-block">
		  	    			<h2>
		  	    				<a class='${var.isTop? "bold":"" }' href="<%=request.getContextPath()%>/article/${var.id}"> 
		  	    				 	${var.status==0?"[草稿] ":"" }
		  	    				 	${var.isTop?"[置顶] ":"" }
		  	    				 	${var.title}
		  	    				</a>
		  	    			</h2>
		  	    			<p class="article-status span-row">
		  	    				<span style="display:none;">${var.uid }</span>
		  	    				<span class="source_${var.source }"></span>
		  	    				<span><i class="fa fa-lightbulb-o" aria-hidden="true"></i> <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${var.createTime }"/></span>
		  	    				<span><i class="fa fa-pencil" aria-hidden="true"></i> <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${var.modifyTime }" /></span>
		  	    				<span>类型: 
									<c:forEach var="item" items="${types}" varStatus="state">
										<c:if test="${var.type==item.id }">${item.name }</c:if>
									</c:forEach>
								</span>
								<c:if test="${var.uid == OWLET_USER.id}">
									<span>状态: 
										<c:if test="${var.status==0 }">草稿</c:if>
										<c:if test="${var.status==1 }">发布</c:if>
									</span>
								</c:if>
		  	    				<span><i class="fa fa-eye" aria-hidden="true"></i> ${var.viewNum }</span>
		  	    				<span><i class="fa fa-comment" aria-hidden="true"></i> ${var.commentNum }</span>
		  	    				<span>
		  	    					<i class="fa fa-tags" aria-hidden="true"></i>
		  	    					<c:forEach items="${tagMap.get(var.id) }" var="vart" varStatus="state">
										<c:if test="${state.index==0 }">
											<a href="<%=request.getContextPath()%>/article?tag=${vart.id}">${vart.tname}</a>
										</c:if>
										<c:if test="${state.index>0 }">
											, <a href="<%=request.getContextPath()%>/article?tag=${vart.id}">${vart.tname}</a>
										</c:if>
									</c:forEach>
		  	    				</span>
		  	    			</p>
		  	    			<p class="abstract">${var.abstracts}</p>
		  	    		</div>

		  	    	</li>
		  	    </c:forEach>
	  	    </ul>
	
			<form name="index-form" action="<%=request.getContextPath()%>/article" method="GET">
				<input type="hidden" name="page" value="${page}"/>
				<input type="hidden" name="kw" value="${kw}"/>
			</form>
	  	    <ul id="pagger" class="toolbar clearfix" style="padding: 0 5%;">
	  	    	<c:forEach items="${pageidxs }" var="idx">
	  	    		<li>
	  	    			<a class='btn ${idx==page?"active":"" }' data-page="${idx}" href="#"> 
	  	    				${idx==1?"<":"" }${idx}${idx==pagecount?">":"" }
	  	    		 	</a>
	  	    		</li>
	  	    	</c:forEach>
	  	    </ul>
  	    </div>
  	    
   	    <div id="page-right" class="fr">
  			<div style="margin-bottom:5px; height:200px; width:100%; background-color:darkgray;"></div>
  			<div style="height:300px; width:100%; background-color:darkgray;"></div>
  		</div>
 	 </div>
 	 <div style="height:10px;"></div>
 	<script>
	 	$(function() {
/*  	 	$(".edit").each(function(){
	 		    $(this).click(function(){
	 		    	var form=$("#edit-form");
	 		    	form.attr("action",form.attr("data-path")+"/"+$(this).attr("data-idx"));
	 		    	form.submit();
	 		    })
	 		});  */
 	 		$("#pagger li a").click(function(){
 	 			var form=$("[name='index-form']");
 	 			var page= $(this).attr("data-page");
 	 			form.find("[name='page']").val(page);
 	 			form.submit();
 	 		});
	 		
<%--  	 		$.ajax({
    			url: "<%=request.getContextPath()%>/tag",
    			dataType:'json',
    			data: {
    					'last':0,
    					'num':10
    				},
    			cache: false,
    			success:function(data){
    				if(data.code==0){
    					alert("success "+JSON.stringify(data.data));
    				}else{
    					notify("error", data.msg);
    				}
    			},
    			error : function(e) {   
    				notify("error","异常！"+JSON.stringify(e));    
  		     	} 
    		}); --%>
	 	});
 	</script>
 	<%-- <form id="edit-form" method="post" data-path="<%=request.getContextPath()%>/article"></form> --%>
  </body>
</html>