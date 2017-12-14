$(function() {
	$("#s").change(function() {
		getCodes();
	});
	$("#subcode").click(function() {
		putCodes(getAll(),getUrl());
	});
	$("#clearcode").click(function(){
		$(".ti").val("");
		alert("清空成功");
	});
	$("#copycode").click(function(){
		$("#sourceLink").html(getUrl());
		$("#copyDiv").show();
	});
	$("#subCopyBtn").click(function(){
		putCodes(getAll(),$("#targetLink").val());
	});
	$("#closeBtn").click(function(){
		$("#copyDiv").hide();
	});
});

function getAll(){
	var s="";
	var inplist=$(".ti");
	for(var i=0;i<inplist.length;i++){
		var vs=$(inplist[i]).val().trim();
		if(vs!=null&&vs!=""){
			if(s!=""){
				s+=",";
			}
			s+=vs;
		}
	}
	return s;
}
function getUrl() {
	return $("#s").val();
}

function getCodes() {
	$.ajax({
		url : "/codes/list?url=" + getUrl(),
		type : "get",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8", // 解决传中文乱码
		success : function(res) {
			//$("#t").val(""+res);
			$(".ti").val("");
			var a=res.split(",");
			var inplist=$(".ti");
			for(var i=0;i<a.length;i++){
				$(inplist[i]).val(a[i]);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			 alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus);
		}
	});
}

function putCodes(codes,url) {
	$.ajax({
		url : "/codes/update?code=" + codes + "&url=" + url,
		type : "get",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8", // 解决传中文乱码
		success : function(res) {
			if (res == "success") {
				if(confirm("更新成功,是否跳转页面")){
					location.href="http://"+url;
				}else{
					location.href="";
				}
			}
		},
		error : function() {

		}
	});
}


