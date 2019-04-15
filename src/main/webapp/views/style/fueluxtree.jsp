<!DOCTYPE html>
<html lang="en" class="fuelux">
  <head>
    <meta charset="utf-8">
    <title>Fuel UX Test</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/ace/css/bootstrap.min.css">
    <link href="<%=request.getContextPath()%>/static/fuelux-tree/fuelux-tree.css" rel="stylesheet"/>
  </head>
  <body>
	<ul id="myTree" class="tree tree-folder-select" role="tree" id="myTree">
	  
	  <li class="tree-branch hide" data-template="treebranch" role="treeitem" aria-expanded="false">
	    <div class="tree-branch-header">
	      <button class="fa icon-caret fa-play"><span class="sr-only">Open</span></button>
	      <button class="tree-branch-name">
	        <span class="fa icon-folder fa-folder"></span>
	        <span class="tree-label"></span>
	      </button>
	    </div>
	    <ul class="tree-branch-children" role="group"></ul>
	    <div class="tree-loader" role="alert">Loading...</div>
	  </li>
	  
	  <li class="tree-item hide" data-template="treeitem" role="treeitem">
	    <button class="tree-item-name">
	      <span class="fa icon-item fa-file"></span>
	      <span class="tree-label"></span>
	    </button>
	  </li>
	  
	</ul>

    <script src="<%=request.getContextPath()%>/static/ace/js/jquery-2.0.3.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/ace/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/fuelux-tree/fuelux-tree.js"></script>
    <script>
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
    
    $(function() {
    	   $('#myTree').tree({
    	      dataSource: staticDataSource,
    	      multiSelect: false,
    	      folderSelect: true
    	    });
    	});
    </script>
  </body>
</html>