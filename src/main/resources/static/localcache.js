function save(id, content){
	var store= window.localStorage;
	var article_limit=5;
	
	//处理ID
	var idstring=store.getItem("id_list");
	var ids=idstring?idstring.split(","):[]
	if(arrayContains(ids, id+"")){
		arrayDelete(ids, id+"");
	}else if(ids.length >= article_limit){
		var oldid=ids.shift();
		clean(oldid);
	}
	if(ids[ids.length-1]!=id+""){
		ids.push(id+"");
		store["id_list"]=ids.join(",");
	}
	
	//存储
	var numstring = store.getItem("num_"+id);
	var limit=5;
	var num=numstring?parseInt(numstring):0;
	store["content_"+id+"_"+(num%limit)]= content;
	store["num_"+id]=num+1;
}

function arrayDelete(array, data){
	for(i=0,len=array.length;i<len;i++){
		if(array[i]== data )
			break;
	}
	for(;i<len-1; i++){
		array[i]=array[i+1];
	}
	array.pop();
}

function arrayContains(array, data){
	for(i=0,len=array.length;i<len;i++){
		if(array[i]== data )
			return true;
	}
	return false;
}

function clean(id){
	var num = localStorage.getItem("num_"+id);
	var limit=5;
	if(!num) 
		num=0;
	for(i=0, len=num; i<num && i<limit; i++)
		localStorage.removeItem("content_"+id+"_"+(i%limit));
	
	localStorage.removeItem("num_"+id);
}

function get(id){
	var num = localStorage.getItem("num_"+id);
	if(num)
		return localStorage["content_"+id+"_"+(num%limit)];
}