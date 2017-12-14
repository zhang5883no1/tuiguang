function topage(url){
	location.href="/ff/?url="+url;
}
function toprev(){
	var cu=$("#curl").html();
	var cs=cu.split("\\");
	var url="";
	for(var i=0;i<cs.length-1;i++){
		url+=cs[i]+"\\";
	}
	url=url.substring(0,url.length-1);
	location.href="/ff/?url="+encodeURI(url);
}