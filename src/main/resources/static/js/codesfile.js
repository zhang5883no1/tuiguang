$(function() {
	$("#s").change(function() {
		getfiles();
	});
});

function getUrl() {
//	return $("#s").val();
	return encodeURI($("#s").val());
}

function getfiles() {
	$.ajax({
		url : "/cf/list?url=" + getUrl(),
		type : "get",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8", // 解决传中文乱码
		success : function(res) {
			//$("#d").html("");
			var a=res.split(",");
			$(".inpt").val("");
			$(".inps").html("");
			$(".inpf").val("");
			for(var i=0;i<a.length;i++){
				$(".inpt").eq(i).val(a[i]);
				$(".inps").eq(i).html("<img src='"+"http://"+$("select option:selected").text()+"/wxpic/"+a[i]+".jpg?timestamp="+new Date().getTime()+"' class='inpi'/>");
				//$("#d").html($("#d").html()+a[i]+"<br/>");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			 alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus);
		}
	});
}

function checkfile(){
	var files=$(".inpf");
	var names=$(".inpt");
	for(var i=0;i<files.length;i++){
		//alert("file:"+files.eq(i).val()+",name:"+names.eq(i).val());
		if(files.eq(i).val()!=""&&$.trim(names.eq(i).val())==""){
			alert("文件对应文件名不能为空");
			return false;
		}
	}
	var nf=true;
	for(var i=0;i<names.length;i++){
		if($.trim(names.eq(i).val())!=""){
			nf=false;
		}
	}
	if(nf){
		alert("必须填写一个微信号");
		return false;
	}
	$("#subform").submit();
}

