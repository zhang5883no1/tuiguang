$(function() {
	$("#s").change(function() {
		getfiles();
	});
});

function getUrl() {
	return $("#s").val();
}

function getfiles() {
	$.ajax({
		url : "/file/list?url=" + getUrl(),
		type : "get",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8", // 解决传中文乱码
		success : function(res) {
			//$("#t").val(""+res);
			$("#d").html("");
			var a=res.split(",");
			for(var i=0;i<a.length;i++){
				$("#d").html($("#d").html()+a[i]+"<br/>");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			 alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus);
		}
	});
}


