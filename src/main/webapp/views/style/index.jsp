<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>样式测试</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/ace/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/ace/css/ace.min.css">

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

.navbar-jianshu {
	background-color: red;
}

.navbar {
	margin-bottom: 0;
	overflow: visible;
	min-height:0;
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

.writer-nav-header {
	position: relative;
	height: 0px;
	background-color: white;
	border-bottom: 1px solid #d9d9d9;
	z-index: 1001;
	background-color: green;
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

.nav-user {
	position: absolute;
	bottom: 0;
	width: 100%;
	padding-bottom: 5px;
}

@media screen and (max-height: 420px) {
	.nav-user {
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

.nav-user a {
	padding: 5px 10px;
}

.expanded .dropdown span, .navbar-jianshu .nav-user span.title {
	display: none;
}

.navbar-jianshu .nav-user a:hover, .navbar-jianshu .nav-user a.active {
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
		<div class="row-fluid normal-mode active">
			<div class="navbar">
				<div class="writer-nav-header"></div>

				<!-- 窄屏，收缩为一个图标 -->
				<div class="navbar navbar-jianshu shrink" style="display: none"></div>

				<!-- 宽屏，图标列 -->
				<div class="navbar navbar-jianshu expanded">
					<div class="dropdown">
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

					<div class="nav-user">
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
		<div class="row-fluid preview-mode"></div>
		<div class="writing-mode"></div>
		<div class="row-fluid history-mode"></div>
		<div class="row-fluid recycle-mode"></div>
	</div>
</body>
</html>
<script src="<%=request.getContextPath()%>/static/ace/js/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/static/ace/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/ace/js/ace-elements.min.js"></script>
<script src="<%=request.getContextPath()%>/static/ace/js/ace.min.js"></script>
<script src="<%=request.getContextPath()%>/static/ace/js/fuelux/fuelux.tree.min.js"></script>
<script src="<%=request.getContextPath()%>/static/ace/js/fuelux/data/fuelux.tree-sampledata.js"></script>
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
	
	$('#tree1').ace_tree({
		dataSource: treeDataSource ,
		multiSelect:true,
		loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
		'open-icon' : 'icon-minus',
		'close-icon' : 'icon-plus',
		'selectable' : true,
		'selected-icon' : 'icon-ok',
		'unselected-icon' : 'icon-remove'
	});
	
 	$('#tree2').ace_tree({
		dataSource: treeDataSource2 ,
		loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
		'open-icon' : 'icon-folder-open',
		'close-icon' : 'icon-folder-close',
		'selectable' : true,
		'folderSelect' : true,
		'selected-icon' : null,
		'unselected-icon' : null
	}); 
	
/* 	$('#myTree').tree({
	      dataSource: staticDataSource,
	      multiSelect: false,
	      folderSelect: true
	    });
	
	$('#myTree').on('deselected.fu.tree selected.fu.tree', function(event) {
	  // insert JSON text of selected items after tree
	  $('#myTree').after('SELECTED ITEMS: ' + JSON.stringify( $('#myTree').tree('selectedItems') ) + '<br>' );
	});

	$('#myTree').on('disclosedFolder.fu.tree closed.fu.tree', function(event, parentData) {
	  // insert JSON text of selected items after tree
	  $('#myTree').after('OPENED/CLOSED FOLDER DATA: ' + JSON.stringify( parentData ) + ' ' );
	});
 */})

function staticDataSource(openedParentData, callback) {
  childNodesArray = [
    { "name": "Ascending and Descending", "type": "folder" },
    { "name": "Sky and Water I", "type": "item" },
    { "name": "Drawing Hands", "type": "folder" },
    { "name": "waterfall", "type": "item" },
    { "name": "Belvedere", "type": "folder" },
    { "name": "Relativity", "type": "item" },
    { "name": "House of Stairs", "type": "folder" },
    { "name": "Convex and Concave", "type": "item" }
  ];
  callback({
    data: childNodesArray
  });
}

function staticDataSourceWithAttr(parentData, callback) {
	  // includes rebeccapurple styling
 	  //attr.id   element ID
 	  //cssClass  css class
	  //attr.data-icon  icon class
	  //attr.hasChildren  whether to hide the caret if there are no child nodes.
	  //attr.style
	  
	  childNodesArray = [
	    {
	      "name": "Ascending and Descending",
	      "type": "folder",
	      "attr": {
	        "id": "ascending-and-descending"
	      }
	    },
	    {
	      "name": "Sky and Water I",
	      "type": "item",
	      "attr": {
	        "id": "sky-and-water-i",
	        "data-icon": "glyphicon glyphicon-file",
	        "style": "color: rebeccapurple;"
	      }
	    },
	    {
	      "name": "Drawing Hands",
	      "type": "folder",
	      "attr": {
	        "id": "drawing-hands"
	      }
	    },
	    {
	      "name": "Waterfall",
	      "type": "item",
	      "attr": {
	        "id": "waterfall",
	        "data-icon": "glyphicon glyphicon-cloud",
	        "style": "color: rebeccapurple;"
	      }
	    },
	    {
	      "name": "Belvedere",
	      "type": "folder",
	      "attr": {
	        "id": "belvedere"
	      }
	    },
	    {
	      "name": "Relativity",
	      "type": "item",
	      "attr": {
	        "id": "relativity",
	        "data-icon": "glyphicon glyphicon-picture",
	        "style": "color: rebeccapurple;"
	      }
	    },
	    {
	      "name": "House of Stairs",
	      "type": "folder",
	      "attr": {
	        "id": "house-of-stairs"
	      }
	    },
	    {
	      "name": "Convex and Concave",
	      "type": "item",
	      "attr": {
	        "id": "convex-and-concave",
	        "data-icon": "glyphicon glyphicon-tags",
	        "style": "color: rebeccapurple;"
	      }
	    }
	  ];

	  callback({
	    data: childNodesArray
	  });
	}
	
function dynamicDataSource(openedParentData, callback) {
	  var childNodesArray = [];

	  // call API, posting options
	  $.ajax({
	    'type': 'post',
	    'url': '/tree/data',
	    'data': openedParentData  // first call with be an empty object
	  })
	  .done(function(data) {
	    // configure datasource
	    var childObjectsArray = data;

	    // pass an array with the key 'data' back to the tree
	    // [ {'name': [string], 'type': [string], 'attr': [object] } ]
	    callback({
	      data: childNodesArray
	    });

	  });
	}
</script>