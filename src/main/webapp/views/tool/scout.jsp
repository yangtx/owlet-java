<!DOCTYPE html>
<html>
<head>
<title>Scout (Http Request Helper)</title>
<meta name="navi" content="tool"/>
<style>
.block{
  background-color: #fafafa;
  margin: 10px 10px 0 10px;
  padding: 10px;
}
</style>
</head>
<body>
	<h1>Express</h1>
	<form id="edit-from">
		<div class="block">
			<select id="method" name="method"><option value="GET">GET</option>
				<option value="POST">POST</option>
				<option value="PUT">PUT</option>
				<option value="PATCH">PATCH</option>
				<option value="DELETE">DELETE</option>
				<option value="OPTIONS">OPTIONS</option>
				<option value="HEAD">HEAD</option></select><input id="url" name="url"><input
				id="btn-send" type="button" value="Send">
		</div>
		<div class="block">
			headers<input type="button" value="+" class="add-item">
			<ul id="list-header">
				<li><input name=""><span>:</span><input><input
					type="button" value="-"></li>
			</ul>
		</div>
		<div class="block">
			parameters<input type="button" value="+" class="add-item">
			<ul id="list-param">
				<li><input><span>:</span><input><input
					type="button" value="-"></li>
			</ul>
		</div>
		<div class="block">
			cookies<input type="button" value="+" class="add-item">
			<ul id="list-cookie">
				<li><input><span>:</span><input><input
					type="button" value="-"></li>
			</ul>
		</div>
		<div class="block">
			authorization<select><option value="No">No Auth</option>
				<option value="Basic">Basic Auth</option>
				<option value="Digest">Digest Auth</option>
				<option value="OAuth1">OAuth 1.0</option>
				<option value="OAuth2">OAuth 2.0</option>
				<option value="Hawk">Hawk Authentication</option>
				<option value="AWSSign">AWS Signature</option></select>
			<ul>
				<li><span>keyname</span><input></li>
			</ul>
		</div>
		<div class="block">
			body
			<div>
				<input type="radio" name="body-type" value="x-www-form-urlencoded"><span>x-www-form-urlencoded</span><input
					type="radio" name="body-type" value="multipart/form-data"><span>multipart/form-data</span><input
					type="radio" name="body-type" value="raw"><span>raw</span><input
					type="radio" name="body-type" value="binary"><span>binary</span>
			</div>
			<div>
				x-www-form-urlencoded<input type="button" value="+" class="add-item">
				<ul>
					<li><input><span>:</span><input><input
						type="button" value="-"></li>
				</ul>
			</div>
			<div>
				raw
				<textarea></textarea>
			</div>
			<div>
				binary
				<textarea></textarea>
			</div>
		</div>
	</form>
	
	<script>
    $(document).ready(function () {
        function del_bind() {
            $(this).parent('li').detach();
        }

        $(".add-item").bind("click", function () {
            var li = $("<li>" +
                "<input/><span>:</span><input/>" +
                "<input type='button' value='-'>" +
                "</li>");
            li.find("input[type='button']:last-child").bind("click",del_bind);
            $(this).siblings("ul")[0].appendChild(li[0]);
        });

        $("li > input[type='button']:last-child").bind("click", del_bind);


        $("#btn-send").bind("click",function () {
            $.ajax({
                url: "<%=request.getContextPath()%>/tool/scout",
                method: "POST",
                dataType:'json',
                headers:{
                	"Content-Type":'application/json'
                },
                data: build_data(),
                success: function(result){
                    alert( result.code+", "+result.msg );
                },
                error: function (xhr, msg, ex) {
                    alert('error: '+ msg);
                }
            });
        })

        function build_data(){
            var headers={};
            $("#list-header > li").each(function () {
                var kv=$(this).find("input[type!=button]");
                if(kv.eq(0).val())
                    headers[kv.eq(0).val()]=kv.eq(1).val();
            });

            var params={};
            $("#list-param > li").each(function () {
                var kv=$(this).find("input[type!=button]");
                if(kv.eq(0).val())
                    params[kv.eq(0).val()]=kv.eq(1).val();
            });

            var cookies={};
            $("#list-cookie > li").each(function () {
                var kv=$(this).find("input[type!=button]");
                if(kv.eq(0).val())
                    cookies[kv.eq(0).val()]=kv.eq(1).val();
            });

            var data={
                url:$("#url").val(),
                method:$("#method").val()
            };

            if(Object.getOwnPropertyNames(params).length>0)
                data.params=params;
            if(Object.getOwnPropertyNames(headers).length>0)
                data.headers=headers;
            if(Object.getOwnPropertyNames(cookies).length>0)
                data.cookies=cookies;

            return JSON.stringify(data);
        }
    });
	</script>
</body>
</html>