var rootPath=null;
//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPath=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPath);
}
rootPath=getRootPath();

function inputdate(now){
//格式化日，如果小于9，前面补0
    var day = ("0" + now.getDate()).slice(-2);
//格式化月，如果小于9，前面补0
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
//拼装完整日期格式
    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
//完成赋值
    return today;
}

function payid(now,len){
//格式化日，如果小于9，前面补0
    var day = ("0" + now.getDate()).slice(-2);
//格式化月，如果小于9，前面补0
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
//拼装完整日期格式
    var today = now.getFullYear()+(month)+(day) ;
//完成赋值

    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }

    return today+pwd;
}

function notReback(){
    if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
            window.history.pushState('forward', null, '#');
            window.history.forward(1);
            //alert("不可回退");
            location.replace(document.referrer);//刷新
        });
    }

    window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
    window.history.forward(1);
}

//时间戳转换
function formatDate(now) {
    var times=parseInt(now)
    var d=new Date(times);
    var year=d.getFullYear();
    var month=("0" + (d.getMonth() + 1)).slice(-2);
    var date=("0" + d.getDate()).slice(-2);
    var hour=d.getHours();
    var minute=d.getMinutes();
    var second=d.getSeconds();
    return year+"-"+month+"-"+date+' '+hour+':'+minute+':'+second;
}