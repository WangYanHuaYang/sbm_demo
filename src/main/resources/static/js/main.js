
var script_array = [
    "https://cdn.jsdelivr.net/npm/vue/dist/vue.js"
    ,"https://cdnjs.cloudflare.com/ajax/libs/axios/0.15.2/axios.js"
    // ,
    // "https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js",
    // "https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
];
var css_array = [
    // "https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
];

for (var i = 0; i < css_array.length; i++) {
    document.write('<link rel="stylesheet" type="text/css" href="' + css_array[i] + '">');
}
for (var i = 0; i < script_array.length; i++) {
    document.write('<script language=javascript src="' + script_array[i] + '"></script>');
}

//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPath);
}
