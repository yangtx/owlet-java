<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="fuelux">
  <head>
    <meta charset="utf-8">
    <meta name="navi" content="home"/>
    <title>Home</title>
  	<style>
  		body{
  			background: url(<%=request.getContextPath()%>/static/imgs/home-bg.jpg) no-repeat center center fixed;
    		background-size: cover;
  		}
  		#home-header{
  			margin-top: -100px;
  			padding-top: 200px;
  			padding-bottom: 150px;
  			background-color: rgba(26,58,59,0.3);
  		}
  		.night-mode .navi-bar{
  			background-color: rgba(58,58,58,0.3) !important;
  		}
  		.navi-bar{
  			background-color: rgba(248,248,248,0.2) !important;
  		}
  		.navi-bar a, .navi-barr a:link, .navi-bar a:visited, .navi-bar a:active {
		    color: #ddd; !important;
		}
		.navi-bar a:hover {
		    color: #fff; !important;
		}
		.night-mode .nav li:hover:not(.non-navi) {
		    background-color: rgba(48,48,48,0.5) !important;
		}
		.nav li:hover:not(.non-navi) {
		    background-color: rgba(248,248,248,0.3) !important;
		}
		
		#home-title{
			text-align:center;
			color: white;
			font-size:5em;
			display:block;
			margin: 0 auto;
		}
		#home-title2{
			width: 70%;
			margin: 20px auto 0 auto;
			display: block;
			text-align:center;
			color: white;
			font-size:2em;
		}
		#tech-stack{
			height:400px;
			background-color: rgba(255,255,128,0.5);
		}
		#project-show{
			height:300px;
			background-color: rgba(0,200,0,0.3);
		}
		#article-show{
			display:flex;
			align-items: center;
			justify-content: center ;
			background-color: rgba(128,128,128,0.3);
		}
		[name=weather_inc]{
			position: absolute;
			right: 10px;
			top: 10px;
			width: 210px;
			padding: 5px 5px 5px 15px;
			border-radius:15px;
			border-width:0;
			background-color: rgba(248,248,248,0.1);
		}
		
		#project-container{
			max-width:800px;
			margin: 0 auto;
			height: 100%;
			display:flex;
			align-items: center;
			justify-content: flex-start ;
			flex-wrap: wrap;
			align-content: space-around;
		}
		#project-container span{
			background-color: orange;
			color: white;
			padding: 10px 20px;
			margin: 5px;
		}
		#article-show .article-list{
			padding: 50px;
			
		}
  	</style>
  </head>
  <body>
 		<div id="home-header">
			<p id="home-title">Enjoy study. Enjoy life.</p>
			<p id="home-title2">怕什么真理无穷，进一寸有一寸的欢喜</p>
			
			<div id="weather-container">
				
			</div>
		</div>
		<div id="tech-stack">
 			<div id="main" style="width: 800px;height:400px; margin:0 auto;"></div>
 		</div>
 		<div id="project-show">
 			<div id="project-container">
 				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
				<span>111111</span>
			</div>
 		</div>
 		<div id="article-show">
 			<div class="article-list">
 				<span>最新推荐</span>
	 			<ul>
	 				<li>111111</li>
	 				<li>111111</li>
	 				<li>111111</li>
	 			</ul>
 			</div>
 			<div class="article-list">
 				<span>最热</span>
	 			<ul>
	 				<li>22222222</li>
	 				<li>22222222</li>
	 				<li>22222222</li>
	 			</ul>
 			</div>
 		</div>
 	
   <script src="<%=request.getContextPath()%>/static/echarts3/echarts.simple.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	setTimeout(function(){
    		$("#weather-container").html("<iframe name='weather_inc' src='http://i.tianqi.com/index.php?c=code&id=10' width='300' height='25'></iframe>");
    	} ,1);
    	
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    });
    </script>
  </body>
  </html>
  
