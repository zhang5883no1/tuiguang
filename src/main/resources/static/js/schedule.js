$(function() {
	$("#subcode").click(function() {
		var s="";
		var ys=$(".ymdvel");
		var hs=$(".hourvel");
		var ds=$(".detailvel");
		for(var i=0;i<ys.length;i++){
			var y=$(ys[i]).val().trim();
			var h=$(hs[i]).val().trim();
			var d=$(ds[i]).val().trim();
			if(y==""){
				y=" ";
			}
			if(h==""){
				h=" ";
			}
			if(d==""){
				d=" ";
			}
			s+=y+"◆"+h+"◆1◆"+d+"◇";
		}
		putCodes(s);
	});

});


function putCodes(a) {
	$.ajax({
		url : "/schedule/update?a=" + a ,
		type : "get",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8", // 解决传中文乱码
		success : function(res) {
			if (res == "success") {
				alert(res);
			}
		},
		error : function() {

		}
	});
}

