<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en" class="fuelux">
  <head>
    <meta charset="utf-8">
    <meta name="navi" content="asset"/>
    <script src="<%=request.getContextPath()%>/static/ajaxfileupload.js" type="text/javascript"></script>
    <title>Editor</title>
  	<style>
  		.pop-right a:nth-child(1) {
  			color:lightblue;
  			font-size:22px;
  		}
  		.pop-right a:nth-child(2) {
  			color:red;
  		}
  	</style>
  	<script>
  		$(function(){
  			$("#link-snapshot-upload").click(function(){
  				$("#upload-snapshot").click();
  			});
  			
  			$("#upload-snapshot").change(function(){
   			  	$.ajaxFileUpload({
   	  			    url:'<%=request.getContextPath()%>/file/upload',
   	  			    secureuri:false,
   	  			    fileElementId:'upload-snapshot',
   	  			    dataType: 'json',
   	  			    success: function(data,status){
   	  			        alert(data);
   	  			    	if(data.success==1){
   	  			        	alert("上传成功："+data.url);
   	  			        }
   	  			        else{
   	  			        	alert("上传失败");
   	  			        }
   	  			    },
   	  			    error: function(data,status,e){
   	  			        alert(e);
   	  			    }
   	  			  });
  			});

  		});
  	</script>
  </head>
  <body>
  		<div id="action-bar" class="clearfix" style="margin:10px 20px; padding:5px;">
  			<div class="fl" style="font-size:40px;">
  				<a class="block-btn" style="background-color:green;" data-toggle="modal" data-target="#editModal"><i class="fa fa-plus" aria-hidden="true"></i></a>
  			</div>
  			<div class="fl" style="margin-left:30px;">
  				<div class="fl" style="font-size:40px;">
  					<a class="block-btn" style="background-color:blue;"><i class="fa fa-plus" aria-hidden="true"></i></a>
  				</div>
  				<div class="fl" style="font-size:40px; margin-left:2px;">
  					<a class="block-btn" style="background-color:blue;"><i class="fa fa-plus" aria-hidden="true"></i></a>
  				</div>
  			</div>
  		</div>
				
		<div id="link-container" class="clearfix" style="margin: 0 20px 20px 20px;">
			<ul class="blocklist">
				<c:forEach items="${link}" var="var">
					<li data-id="${var.id}">
						<img class="block-bg" src="<%=request.getContextPath()%>/static/imgs/14.jpg" alt="img14"/>
						<div class="block-main" style="background">
							<div>
								<i class="fa fa-star" aria-hidden="true"></i> ${var.title}
							</div>
							<div>
								<i class="fa fa-link" aria-hidden="true"></i> 
								<a href="${var.url }">${var.url}</a>
							</div>
							<div>
								<i class="fa fa-tag" aria-hidden="true"></i> ${var.type}
							</div>
							<i class="fa fa-info-circle" aria-hidden="true"></i> ${var.info}
						</div>
						<div class="pop-right">
							<a><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
							<a href="<%=request.getContextPath()%>/link/delete/${var.id}"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
			

	<!-- Modal -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
				<form class="form-horizontal"
					action="<%=request.getContextPath()%>/link" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">编辑链接</h4>
					</div>
					<div class="modal-body">

						<input type="hidden" name="id" value="" />

						<div class="form-group">
							<label for="title" class="col-sm-2 control-label">标题</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="title" id="title"
									placeholder="标题">
							</div>
						</div>
						<div class="form-group">
							<label for="url" class="col-sm-2 control-label">地址</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="url" id="url"
									placeholder="地址">
							</div>
						</div>
						<div class="form-group">
							<label for="type" class="col-sm-2 control-label">类型</label>
							<div class="col-sm-10">
								<select class="form-control" name="type" id="type">
									<c:forEach items="${types}" var="var">
										<option value="${var.id}">${var.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="snapshot" class="col-sm-2 control-label">快照</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="snapshot"
									id="snapshot" placeholder="快照">	
							</div>
 							<div class="col-sm-2">
								<button type="button" id="link-snapshot-upload" class="btn btn-default">上传</button>	
								<input style="display1:none;" type="file" id="upload-snapshot" name="upload-snapshot"/>
							</div>
						</div>
						<div class="form-group">
							<label for="info" class="col-sm-2 control-label">说明</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="info"
									id="info" placeholder="说明">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">保存</button>
					</div>
				</form>
			</div>
	  </div>
	</div>
	
  </body>
</html>