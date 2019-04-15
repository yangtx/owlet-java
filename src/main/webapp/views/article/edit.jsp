<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en" class="fuelux">
  <head>
    <meta charset="utf-8">
    <meta name="navi" content="note"/>
    <title>编辑 - ${it.title }</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/editor.md/css/editormd.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/editormd-adapt.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/katex0.3.0/katex.min.css" />
    
  	<style>
  		.title{
  		    word-break: break-all;
		    font-size: 24px;
		    text-rendering: optimizelegibility;
		    margin: 0 0 15px 0;
		    display: block;
  		}
  		.article-head{
  			padding: 15px 20px 15px 20px;
  			margin: 0 0 10px 0;
  			border-style: dashed;
  			border-width: 0 0 1px 0;
  		}
  		body.night-mode{
  			background-color: rgb(26, 26, 23);
  		}
  		#page-main{
  			display: flex;
  			flex-direction: column;
  		}
  		#editormd-container{
  			position: relative;
  			flex-grow:1;
  		}
  		#nav-show {
			position: fixed;
			left: 1rem;
			margin-top: 2rem;
			display: block;
			font-size: 1.2rem;
			border-radius: .3rem;
			padding: .5rem;
			display: block;
		}
		.day-mode #nav-show {
			background-color: #eee;
			border: 1px solid #ddd;
			color: darkgreen;
		}
		
		.day-mode #nav-show:hover {
			background-color: #fff;
			color: green;
		}
		
		.night-mode #nav-show {
			background-color: darkgreen;
		}
		
		.night-mode #nav-show:hover {
			background-color: green;
			color: #fff;
		}
  	</style>
  </head>
  <body>
  	  <i id="nav-show" class="fa fa-list" aria-hidden="true" style="z-index:999"></i>
	  <form id="edit-form">
	    <div id="page-main">
	     <div>
			<div class='article-head line ${it.status!=1?"draft":"" }' style="padding-left:5%; padding-right:5%;">
	  			<input name="title" class="title implict" style="width:100%;" value="${it.title }" placeholder="请输入标题"/>
		  		<p class="article-status span-row">	
		  			<input type="hidden" name="id" value="${it.id }"/>
		  			<input type="hidden" name="last_modify_time" value="${last_modify_time}"/>
					<span style="display:none;">${it.uid }</span>
					<span>
						<select name="source">
							<c:forEach var="var" items="${sources}" varStatus="state">
								<option ${it.source==var.id?"selected='selected'":""} class="source_${var.id }" value="${var.id }">${var.name }</option>
							</c:forEach>
						</select>
					</span>
					
					<span><i class="fa fa-lightbulb-o" aria-hidden="true"></i> <span id="create_time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${it.createTime }"/></span></span>
					<span><i class="fa fa-pencil" aria-hidden="true"></i> <span id="modify_time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${it.modifyTime }" /></span></span>
					<span>类型: 
						<select name="type">
							<c:forEach var="var" items="${types}" varStatus="state">
								<option ${it.type==var.id?"selected='selected'":""} value="${var.id }">${var.name }</option>
							</c:forEach>
						</select>
					</span>
					<span>状态: 
						<select name="status">
							<option ${it.status==0?"selected='selected'":""} value="0">草稿</option>
							<option ${it.status==1?"selected='selected'":""} value="1">发布</option>
						</select>
					</span>
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
					<span><i class="fa fa-tags" aria-hidden="true"></i> <input name="tags" class="implict" placeholder="请输入标签" value="${tagString }"/></span>
				</p>
			</div>
	     </div>
	     
	     <div id="editormd-container">
		     <div id="editormad-section">
		     	<textarea name="content" style="display:none;">${it.content}</textarea>
			 </div>
		 </div>
		</div>
	 </form>
    <script src="<%=request.getContextPath()%>/static/localcache.js"></script>
    <script src="<%=request.getContextPath()%>/static/editor.md/editormd.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery/js/jquery.md5.js"></script> 
    
    <script type="text/javascript">
    	function canSave(savehandler){
    		$.ajax({
    			url: "<%=request.getContextPath()%>/article/cansave",
    			dataType:'json',
    			data: {
    					'last_modify_time':$("[name=last_modify_time]").val(),
    					'id': $("input[name=id]").val()
    				},
    			cache: false,
    			success:function(data){
    				if(data.code==0){
    					savehandler();
    				}else{
    					notify("error", data.msg);
    				}
    			},
    			error : function(e) {   
    				notify("error","异常！"+JSON.stringify(e));    
  		     	} 
    		});
    	}
    	
    	function local_save(){
    		save($("#edit-form input[name=id]").val(), $("#edit-form textarea[name=content]").text());
    	}
    	
    	function send_save_asyn(handler){
    		if(contentHash==$.md5($("#edit-form").serialize())){
    			notify("warning","内容无变化");    
    			return;
    		}
    		
    		local_save();
    		
    		//可保存判断
    		canSave(function(){
    			$.ajax({
        			url: "<%=request.getContextPath()%>/article",
        			data: $("#edit-form").serialize(),
        			type: "post",
        			cache: false,
        			dataType:'json',
        			success:function(data) {  
        		        if(data.code==0){  
    						var id = $("input[name=id]");
    						if(id.val().trim().length==0)
    							id.val(data.data.id);
    						var ct = $("#create_time");
    						if(ct.html().trim().length==0)
    							ct.html(data.data.time);
    						
    						$("#modify_time").html(data.data.time);
    						$("[name=last_modify_time]").val(data.data.timestamp);
    						document.title = "编辑 - "+$("[name='title']").val();
    						contentHash= $.md5($("#edit-form").serialize());
    						
    						if(handler)
    							handler(data.data.id);
    						else
    							notify("success","保存成功");
        		        }else{  
        		        	alert(data.msg);  
        		            //view(data.msg);  
        		        }  
        		     },  
        		     error : function(e) {   
        		    	 notify("error","异常！"+JSON.stringify(e));  
        		     }  
        		});
    		});
    	};
    
	  	var testEditor;
	  	var contentHash;
	  	
        $(function() {
          var ss= $("select[name=source]");
          ss.attr("class", "source_"+ss.val());
          ss.change(function(){
        	  ss.attr("class", "source_"+ss.val());
          });
          
           $(window).bind('beforeunload',function(){
        	  if($.md5($("#edit-form").serialize())==contentHash)
        	  	return;
        	  return "还有没保存的修改内容！";
          }); 
          
       	  $.get("<%=request.getContextPath()%>/article/content/${it.id}",function(content){
               testEditor = editormd("editormad-section", {
                  //width   : "100%",
                  //height  : "800",
                  //syncScrolling : "single",
                  path    : "<%=request.getContextPath()%>/static/editor.md/lib/",
                  markdown : content,
                  //autoHeight: true,
           		  htmlDecode : "style,script,iframe|on*",
           		  delay                : 1000, 
           		  codeFold             : true,
           		
                  emoji           : false,
                  taskList        : true,
                  tex             : true,  // 默认不解析
                  flowChart       : true,  // 默认不解析
                  sequenceDiagram : true,  // 默认不解析
                  atLink          : false,

               	  // Editor.md theme, default or dark, change at v1.5.0
                  // You can also custom css class .editormd-preview-theme-xxxx
                  theme        : '${(OWLET_THEME eq "night-mode")?"dark":"default"}',
                  // Preview container theme, added v1.5.0
                  // You can also custom css class .editormd-preview-theme-xxxx
                  previewTheme : '${(OWLET_THEME eq "night-mode")?"dark":"default"}', 
                  // Added @v1.5.0 & after version is CodeMirror (editor area) theme
                  editorTheme  : (localStorage.editorTheme) ? localStorage.editorTheme : "pastel-on-dark", 
           
                		  
                  imageUpload    : true,
                  imageFormats   : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                  imageUploadURL : "<%=request.getContextPath()%>/file/upload_editorMd",
                  imageUploadParams : [
                                       {name:'uid', value:$("[name='uid']").val()},
                                       {name:'aid', value:$("[name='id']").val()}, 
                                       {name:'type', value:'0'}, 
                                      ],  
	                  
	                  //cunstomlize toolbar
		           		toolbarIcons : function() {
		           			var fullIcons=editormd.toolbarModes["full"]; // full, simple, mini
		           			fullIcons.unshift("Save", "SaveQuit", "|");
		           			fullIcons.push("|","SelectEditorTheme");
		                    return fullIcons;
		                },
		                toolbarCustomIcons : {
		                	SelectEditorTheme   : '<select id="editor-area-theme-select"><option selected="selected" value="">select editor themes</option></select>'
		                },
		                toolbarIconsClass : {
		                    Save : "fa-floppy-o",  // 指定一个FontAawsome的图标类
		                    SaveQuit : "fa-external-link"
		                },
		                lang : {
		                    toolbar : {
		                        Save 	 : "保存 (Ctrl+S)",
		                        SaveQuit : "保存后退出 (Ctrl+Q)"
		                    }
		                }, 
		                toolbarHandlers : {
		                    /**
		                     * @param {Object}      cm         CodeMirror对象
		                     * @param {Object}      icon       图标按钮jQuery元素对象
		                     * @param {Object}      cursor     CodeMirror的光标对象，可获取光标所在行和位置
		                     * @param {String}      selection  编辑器选中的文本
		                     */
		                    Save : function(cm, icon, cursor, selection) {
		                    	send_save_asyn();
		                    },
		                    SaveQuit : function(cm, icon, cursor, selection) {
		                    	send_save_asyn(function(id){
	                        		 window.location.href="<%=request.getContextPath()%>/article/"+id;
	                        	 });
			                }
		                },
		                onload : function() {
		                	this.addKeyMap({
		                		 "Ctrl-S": function(cm) {
		                			 send_save_asyn();
		                         },
		                         "Ctrl-Q": function(cm) {
		                        	 send_save_asyn(function(id){
		                        		 window.location.href="<%=request.getContextPath()%>/article/"+id;
		                        	 });
		                         }
		                	});
		                	
		                	themeSelect("editor-area-theme-select", editormd.editorThemes, "editorTheme", function($this, theme) {
		                        testEditor.setCodeMirrorTheme(theme); 
		                        // or testEditor.setEditorTheme(theme);
		                    });
		                }
	              });
               		
                  //Save hash
               	  contentHash= $.md5($("#edit-form").serialize());
        	  }); 

/*            themeSelect("editormd-theme-select", editormd.themes, "theme", function($this, theme) {
                  testEditor.setTheme(theme);
              }); */
              
/*            themeSelect("preview-area-theme-select", editormd.previewThemes, "previewTheme", function($this, theme) {
                  testEditor.setPreviewTheme(theme);
              });    */ 
              
              $(".fixed-btn").addClass("hide");
           	  
              var navbar=$(".navi-bar");
	      	  navbar.mouseleave(function(){
	      		 timer=setTimeout(function(){
	      			hideNavi();
	      		 },3000);
	          });
	      	  navbar.mouseenter(function(){
	      		 clearTimeout(timer);
	      	  });
	      	  $("#nav-show").click(function(){
	      		showNavi();
	      	  });
	      	  navbar.trigger('mouseleave');
	              
           //autosave
/*            setInterval(function(){
        	   local_save();
           },10000); */
          });
        
        function hideNavi(){
        	$(".navi-bar").css("display","none");
        	$("#layout-main").css("margin-top","0");
        }
        function showNavi(){
        	$(".navi-bar").css("display","block");
        	$("#layout-main").css("margin-top","70px");
        }
      </script>
      
      <input type="hidden" id="editor_theme_night" value="${OWLET_THEME_EDITOR_NIGHT}">
      <input type="hidden" id="editor_theme_day" value="${OWLET_THEME_EDITOR_DAY}">
      
      <script type="text/javascript">
            function themeSelect(id, themes, lsKey, callback){
            	var is_night=$("body").hasClass("night-mode");
            	var valueSource= is_night? $("#editor_theme_night"):$("#editor_theme_day");
            	
            	var select = $("#" + id);
                for (var i = 0, len = themes.length; i < len; i ++){                    
                    var theme    = themes[i];    
                    var selected = valueSource.val()== theme ?" selected=\"selected\"" : ""; 
                    select.append("<option value=\"" + theme + "\"" + selected + ">" + theme + "</option>");
                }
                
                callback(select, select.val());
                
                select.bind("change", function(){
                    var theme = $(this).val();
                    if (theme === "")
                        return false;
                    $.ajax({
            			url: "<%=request.getContextPath()%>/user/editortheme",
            			dataType:'json',
            			cache: false,
            			data: { 
            					colormode: is_night?"night-mode":"day-mode",
            					editortheme: theme
            				},
            			success:function(data){
            				if(data.code==0){
            					callback(select, theme);
            					valueSource.val(theme);
            					testEditor.settings.editorTheme=theme;
            				}else{
            					alert(data.msg);
            				}
            			},
            			error : function(e) {   
          		          alert("异常！"+JSON.stringify(e));  
          		     	} 
            		});
                }); 
                return select;
            }
        </script>
  </body>
</html>
