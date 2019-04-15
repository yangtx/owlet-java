<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en" class="fuelux">
  <head>
    <meta charset="utf-8">
    <meta name="navi" content="tag"/>
    <title>标签管理</title>
  </head>
  <body>
  	<div style="margin: 15px auto; width:80%;">
  		总共${tags.size() }个标签
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>标签</th>
					<th>图片</th>
					<th>说明</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tags}" var="var" varStatus="state">
				<tr lang="${var.id }">
					<td>${state.count}</td>
					<td>${var.tname }</td>
					<td> 
						<c:if test="${var.img!=null }">
							<img src="<%=request.getContextPath()%>/assets/tag_icons/${var.img }" width="64"/>
						</c:if>
					</td>
					<td>${var.note }</td>
					<td>
						<a class="btn" href="#">编辑</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
  </body>
  </html>