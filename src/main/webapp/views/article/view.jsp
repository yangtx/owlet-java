<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en" class="fuelux">
  <head>
    <meta charset="utf-8">
    <meta name="navi" content="note"/>
    <title>浏览 - ${it.title }</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/font-awesome/css/font-awesome.min.css">
<%--   	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/editor.md/css/editormd.preview.min.css" /> --%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/editormd-adapt.css" />
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/katex0.3.0/katex.min.css" />
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/editor.md/css/editormd.css" />
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/articleview.css" />
  </head>
  <body>
  	<div id="view-all">
	  	<div id="toc-section-column">
	  		<div id="toc-section-panel">
				<div id="toc-close"><i class="fa fa-window-close" aria-hidden="true"></i></div>
		  		<div id="toc-title">目录</div>
		 		<div id="toc-container" data-uk-scrollspy-nav="{closest:'li', smoothscroll:true, cls:'active'}">
		 			loading...
		 		</div>
	 		</div>
	  	</div>
	  	
		<div id="view-page" data-spy="scroll" data-target="#toc-container" data-offset="100">
			<i id="toc-show" class="fa fa-list" aria-hidden="true"></i>
			
		  	<c:if test="${tags!=null && tags.size()>0 && tags.get(0).img!=null }">
	 			<div id="tag-watermark" style="background-image: url(<%=request.getContextPath()%>/assets/tag_icons/${tags.get(0).img });">
				</div>
	   		</c:if>
		   		
	  		<div class='article-head line'>
	  			<div id="article-tags">
		  			<c:if test="${it.status==0}">
		  				<div id="draft-tag">稿</div>
		  			</c:if>
		  			<c:if test="${it.isTop }">
		  				<div id="top-tag">顶</div>
		  			</c:if>
		  			<c:if test="${it.isElite}">
		  				<div id="elite-tag">精</div>
		  			</c:if>
	  			</div>
	
	  			<span id="article-title" class="title">${it.title }</span>
		  		<p id="article-status" class='article-status span-row ${it.status==0?"draft":"" }'>
					<span style="display:none;">${it.uid }</span>
					<span class="source_${it.source }"></span>
					<span><i class="fa fa-lightbulb-o" aria-hidden="true"></i> <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${it.createTime }"/></span>
					<span><i class="fa fa-pencil" aria-hidden="true"></i> <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${it.modifyTime }" /></span>
					<span>类型: 
						<c:forEach var="var" items="${types}" varStatus="state">
							<c:if test="${it.type==var.id }">${var.name }</c:if>
						</c:forEach>
					</span>
					<c:if test="${it.uid == OWLET_USER.id}">
						<span>状态: 
							<c:if test="${it.status==0 }">草稿</c:if>
							<c:if test="${it.status==1 }">发布</c:if>
						</span>
					</c:if>
					<span><i class="fa fa-eye" aria-hidden="true"></i> ${it.viewNum }</span>
					<span><i class="fa fa-comment" aria-hidden="true"></i> ${it.commentNum }</span>
					
					<c:forEach items="${tags }" var="var" varStatus="state">
						<c:if test="${state.index==0 }">
							<c:set var="tagString" value="${var.tname}"/>
						</c:if>
						<c:if test="${state.index>0 }">
							<c:set var="tagString" value="${tagString }, ${var.tname}"/>
						</c:if>
					</c:forEach>
					<span><i class="fa fa-tags" aria-hidden="true"></i> ${tagString } </span>
					<span>
						<c:if test="${it.uid == OWLET_USER.id}">
							<a id="submit_btn" href="<%=request.getContextPath()%>/article/${it.id}/edit" class="btn" style="display: inline; font-size:1em; padding:0 6px 2px 6px;">编辑</a>
						</c:if>
					</span>
				</p>
			</div>
			  	    		  	    			
	<!--   	<select id="preview-area-theme-select">
	             <option selected="selected" value="">select preview area themes</option>
	         </select> -->
	        <div id="article-content" class='${(OWLET_THEME eq "night-mode")?"editormd-preview-theme-dark":""}' style="background-color:transparent;">
		  		<div id="editormd-view" class="markdown-body editormd-preview-container" style="background-color:transparent;">
		 			<textarea id="editormd" style="display:none;">${it.content}</textarea>
		 		</div>
	 		</div> 	
	 	</div>
 	</div>
 	<script src="<%=request.getContextPath()%>/static/jquery/js/jquery.md5.js"></script>
 	<script src="<%=request.getContextPath()%>/static/math.js"></script>
 	<script src="<%=request.getContextPath()%>/static/editor.md/editormd.js"></script>
    <script src="<%=request.getContextPath()%>/static/editor.md/lib/marked.js"></script>
    <script src="<%=request.getContextPath()%>/static/editor.md/lib/prettify.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/editor.md/lib/raphael.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/editor.md/lib/underscore.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/editor.md/lib/sequence-diagram.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/editor.md/lib/flowchart.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/editor.md/lib/jquery.flowchart.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/uikit-2.25.0/js/uikit.js"></script>
    <script id="-cdnjs-cloudflare-com-ajax-libs-KaTeX-0-3-0-katex-min" type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/KaTeX/0.3.0/katex.min.js"></script>
    <script type="text/javascript">
          $(function() {
/*         	$("#submit_btn").click(function(){
        		$("#edit-form").submit();
        	}); */
        	
          	var editormdView;
          	$.get("<%=request.getContextPath()%>/article/content/${it.id}",function(content){
            		editormdView = editormd.markdownToHTML("editormd-view", {
                       markdown        : content, //$("#editormd").text() ,//+ "\r\n" + $("#append-test").text(),
                       //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                       htmlDecode      : "style,script,iframe",  // you can filter tags decode
                       //toc             : false,
                       tocm            : true,    // Using [TOCM]
                       tocContainer    : "#toc-container", // 自定义 ToC 容器层
                       //gfm             : false,
                       //tocDropdown     : true,
                       //markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
                       emoji           : false,
                       taskList        : true,
                       tex             : true,  // 默认不解析
                       flowChart       : true,  // 默认不解析
                       sequenceDiagram : true,  // 默认不解析
                       atLink          : false,
                       onRendered : function(){
                    	  if(window.localStorage["toc_show"] && window.localStorage["toc_show"]=='false'){
                    		  $("#toc-section-column").css("display","none");
                    		  $("#toc-show").css("display","block");
                    	  }else{
                    		  $("#toc-section-column").css("display","block");
                    		  $("#toc-show").css("display","none");
                    	  }
                    	 
                    	 //$($("#toc-section-column a:first-child")[0]).toggleClass("active");
                    	  
                    	 
                    	 $("#toc-close").on("click",function(){
                    		  $("#toc-section-column").css("display","none");
                    		  $("#toc-show").css("display","block");
                    		  window.localStorage["toc_show"]="false";
                    	  });
                    	  $("#toc-show").on("click",function(){
                    		  $("#toc-section-column").css("display","block");
                    		  $("#toc-show").css("display","none");
                    		  window.localStorage["toc_show"]="true";
                    	  });
 						}
                   }); 
                   
                   //console.log("返回一个 jQuery 实例 =>", testEditormdView);
                   // 获取Markdown源码
                   //console.log(testEditormdView.getMarkdown());
            	});
            });
        </script>
  </body>
</html>