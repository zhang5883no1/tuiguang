$(function() {
	$(".inputtxt").keyup(function(event){
		var vv=$(".inputtxt").val();
		var pp=$(".selDiv p");
		var a=0;
		for(var i=0;i<pp.length;i++){
			//如果内容不匹配，隐藏
			if(pp.eq(i).html().indexOf(vv)==-1){
				pp.eq(i).hide();
			}else{
				//匹配成功+1
				a++;
			}
		}
		if($.trim(vv)==""){
			pp.show();
		}
	}); 
	$(".selDiv p").click(function(){
		$(".inputtxt").val($(this).html());
		getCodes();
	});
	$("#subcode").click(function() {
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
		putCodes(s);
	});

});

function getUrl() {
	return $(".inputtxt").val();
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

function putCodes(codes) {
	$.ajax({
		url : "/codes/update?code=" + codes + "&url=" + getUrl(),
		type : "get",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8", // 解决传中文乱码
		success : function(res) {
			if (res == "success") {
				if(confirm("更新成功,是否跳转页面")){
					location.href="http://"+getUrl();
				}
			}
		},
		error : function() {

		}
	});
}

