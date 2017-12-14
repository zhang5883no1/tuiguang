/**
 * vlstat 浏览器统计脚本
 */
var statIdName = "ucpagestatId";
var xmlHttp;
var docopyf=true;
var wxcode="";

/**
 * 获取域名
 */
function getDomain(){
	var url=location.href+"";
	var u1=url.replace("http://","");
	var u2=u1.split("/");
	return u2[0];
}

/**
 * 设置cookieId
 */
function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    //document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString()) + ";path=/;domain=*.cecb2b.com";
    document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString()) + ";path=/;domain="+getDomain();
}
/**
 * 获取cookieId
 */
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}
/**
 * 获取当前时间戳
 */
function getTimestamp() {
    var timestamp = Date.parse(new Date());
    return timestamp;
}
/**
 * 生成statId
 */
function genStatId() {
    var cookieId = getTimestamp();
    cookieId = "ucpagestat" + "-" + cookieId + "-" + Math.round(Math.random() * 3000000000);
    return cookieId;
}
/**
 * 设置StatId
 */
function setStatId() {
    var cookieId = genStatId();
    setCookie(statIdName, cookieId, 365);
}
/**
 * 获取StatId
 */
function getStatId() {
	return Math.floor(Math.random()*100+1);
    /**var statId = getCookie(statIdName);
    if (statId != null && statId.length > 0) {
        return statId;
    } else {
        setStatId();
        return getStatId();
    }*/
}
/**
 * 获取UA
 */
function getUA() {
    var ua = navigator.userAgent;
    if (ua.length > 250) {
        ua = ua.substring(0, 250);
    }
    return ua;
}
/**
 * 获取浏览器类型
 */
function getBrower() {
    var ua = getUA();
    if (ua.indexOf("Maxthon") != -1) {
        return "Maxthon";
    } else if (ua.indexOf("MSIE") != -1) {
        return "MSIE";
    } else if (ua.indexOf("Firefox") != -1) {
        return "Firefox";
    } else if (ua.indexOf("Chrome") != -1) {
        return "Chrome";
    } else if (ua.indexOf("Opera") != -1) {
        return "Opera";
    } else if (ua.indexOf("Safari") != -1) {
        return "Safari";
    } else {
        return "ot";
    }
}
/**
 * 获取浏览器语言
 */
function getBrowerLanguage() {
    var lang = navigator.browserLanguage;
    return lang != null && lang.length > 0 ? lang : "";
}
/**
 * 获取操作系统
 */
function getPlatform() {
    return navigator.platform;
}

/**
 * 获取路径
 * @returns
 */
function getURL(){
	var u = window.location.host;
	var r = window.location.pathname;
	if(r==""){
		return u;
	}
	return u+r.substring(0,r.length-1);
}
function doInit(){
	var suparam="?u=&b="+getBrower()+"&s="+getStatId()+"&c="+wxcode+"&a=0&l="+getURL();
	var sa="<script src='http://localhost:2222/stat/s"+suparam+"'></script>";
	$("body").append(sa);
}

function docopy(){
	if(docopyf){
		var suparam="?u=&b="+getBrower()+"&s="+getStatId()+"&c="+wxcode+"&a=1&l="+getURL();
		var sa="<script src='http://localhost:2222/stat/s"+suparam+"'></script>";
		$("body").append(sa);
		docopyf=false;
	}
}
