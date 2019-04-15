<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>样式测试</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/font-awesome/css/font-awesome.min.css">

<style>
body {
	margin: 0;
	padding: 0;
	font-family: Helvetica, Arial, "Hiragino Sans GB", sans-serif;
	font-size: 14px;
	line-height: 20px;
	color: #555555;
	background-color: #F8F8F8;
}
li {
    display: list-item;
    text-align: -webkit-match-parent;
}
a {
	color: #555555;
	text-decoration: none;
}

a:hover, a:focus {
	text-decoration: none;
}

.row-fluid {
	width: 100%;
}

.row-fluid:before, .row-fluid:after, .container-fluid:before,
	.container-fluid:after {
	display: table;
	content: "";
	line-height: 0;
}

.row-fluid:after, .container-fluid:after {
	clear: both;
}

.container-fluid .row-fluid .aside {
	height: 338px; /*动态设置*/
	width: 16.66667% !important;
	min-width:300px;
	padding-left: 45px;
	overflow-x: hidden;
}

.container-fluid .row-fluid .middle {
	height: 338px;
	width: 25.0% !important;
	border-left: 1px solid #d9d9d9;
	border-right: 1px solid #d9d9d9;
	overflow-y: auto;
	overflow-x: hidden;
}

.container-fluid .row-fluid .main {
	height: 338px;
	width: 58.33333% !important;
	overflow-y: auto;
	overflow-x: hidden;
}

.row-fluid [class*="span"] {
	display: block;
	width: 100%;
	min-height: 30px;
	height:100%;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	float: left;
	margin-left: 0%;
	position: relative;
}

.expanded {
	left: 0;
	top: 0;
	height: 100%;
	background: #2a2a2a;
	z-index: 1000;
}

.navbar-jianshu {
	position: fixed;
	width: 45px;
	z-index: 1049;
	-webkit-backface-visibility: hidden;
}

.dropdown {
	position: relative;
}

.nav-tail-items {
	position: absolute;
	bottom: 0;
	width: 100%;
	padding-bottom: 5px;
}

@media screen and (max-height: 420px) {
	.nav-tail-items {
		display: none;
	}
}

.navbar-jianshu a {
	display: block;
	padding: 10px;
	font-size: 16px;
	line-height: 25px;
	text-align: center;
}

.expanded a {
	color: #999999;
	text-shadow: 0 1px 0 black;
}

.nav-tail-items a {
	padding: 5px 10px;
}

.expanded .dropdown span, .navbar-jianshu .nav-tail-items span.title {
	display: none;
}

.navbar-jianshu .nav-tail-items a:hover, .navbar-jianshu .nav-tail-items a.active {
	color: #eeeeee;
}

.expanded .dropdown a:hover, .expanded .dropdown a:active, .expanded .dropdown a.active
	{
	background-color: #e78170;
	color: white;
	text-shadow: 0 1px 0 #de533a;
}

.expanded .dropdown a:hover {
	background-color: #f49484;
}

.navbar-jianshu i {
	width: 25px;
	display: inline-block;
	text-align: center;
	line-height: 25px;
}

.expanded .dropdown .fa-home {
	font-size: 20px;
}

.fa, [class*="icon-"] {
	display: inline-block;
	font: normal normal normal 14px/1 FontAwesome;
	font-size: inherit;
	text-rendering: auto;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	transform: translate(0, 0);
}


.notebook{
	font-size:16px;
	position:relative;	
	padding: 10px;
	background-color: #e78170;
    /*
    box-shadow: inset 1px 1px 2px rgba(0,0,0,0.35),0 1px 4px rgba(0,0,0,0.15);
    -webkit-transition: background-color 0.3s linear;
    -moz-transition: background-color 0.3s linear;
    -o-transition: background-color 0.3s linear;
    transition: background-color 0.3s linear;
    */
}
.notebook .notebook-name span, .notebook .notebook-name i{
	color:white;
}
.notebook .edit-notebook{
	color:white;
	float:right;
}

.tree {
    padding-left: 9px;
    overflow-x: hidden;
    overflow-y: auto;
    position: relative;
    margin-left:8px;
}
.tree .tree-item, .tree .tree-folder{
	border: 0 solid #FFF;
}
.tree .tree-folder .tree-folder-header>[class*="icon-"]:first-child {
    margin: -2px 0 0 -2px;
}
.icon-folder-close:before {
    content: "\f07b";
}
.icon-folder-open:before {
    content: "\f07c";
}

.clearfix:before,
.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	visibility: hidden;
}
.clearfix:after 
{
	clear: both;
}
.clearfix { /* IE < 8 */
	zoom: 1;
} 
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="_navbars"> <!-- 不同风格的navbar -->
				<div class="navbar-jianshu expanded">
					<div class="nav-head-items">
						<a href="#" data-title="个人主页" role="button"> <i
							class="fa fa-home"></i> <span class="title">首页</span>
						</a> <a href="#" data-title="专题" data-placement="right"
							data-toggle="tooltip"> <i class="fa fa-th"></i> <span
							class="title">专题</span>
						</a> <a href="#" class="active" data-title="简友圈"
							data-placement="right" data-toggle="tooltip"> <i
							class="fa fa-users"></i> <span class="title">简友圈</span>
						</a>
					</div>

					<div class="nav-tail-items">
						<a href="#" data-title="我的主页" data-placement="right"
							data-toggle="tooltip"> <i class="fa fa-users"></i> <span
							class="title">我的主页</span>
						</a> <a href="#" data-title="我的主页" data-placement="right"
							data-toggle="tooltip"> <i class="fa fa-sign-out"></i> <span
							class="title">登出</span>
						</a>
					</div>
				</div>
			</div>
			<div class="span2 aside">
				<div class="notebook clearfix">
					<a href="javascript:void(0)" data-type="active" class="notebook-name">
					 	<i class="fa fa-book"></i>
					 	<span>4343 </span>
					</a>
					<a href="javascript:void(0)" data-type="edit" class="edit-notebook dropdown-toggle" data-toggle="dropdown"> 
					 	<i class="fa fa-gear"></i>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="#" tabindex="-1">Action</a>
						</li>
						<li>
							<a href="#" tabindex="-1">Another action</a>
						</li>
						<li>
							<a href="#" tabindex="-1">Something else here</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#" tabindex="-1">Separated link</a>
						</li>
					</ul>
				</div>
				<div id="tree2" class="tree tree-unselectable">
				</div>	
				<div id="tree1" class="tree tree-selectable">					
				</div>
			</div>
			<div class="span3 middle">
			</div>
			<div class="span7 main"></div>
		</div>

	</div>
</body>
</html>
<script src="<%=request.getContextPath()%>/static/ace/js/jquery-1.10.2.min.js"></script>
<script>
function resetHeight() {
    $('.row-fluid [class*="span"]').height($(".expanded").height()-21);
}
window.onload = resetHeight;
window.onresize = resetHeight;

$(function(){
	$(".dropdown-toggle").bind("click",function(){
		$(this).next(".dropdown-menu").show();
	});
})
</script>

