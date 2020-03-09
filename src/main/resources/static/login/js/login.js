const form = layui.form
form.on('submit(login)',function(data){
    let formData={
        'username':data.field.username,
        'password':hex_md5(data.field.password),
        'remember-me':$('#remember').val()
    }
    $.ajax({
        url: '/login',
        type: 'post',
        async:false,
        data:formData,
        success: function (result) {
            let code=1
            if (result.code!=0){
                code=2
            }
            layer.msg(result.message, {
                icon: code, //1 成功 2 失败 3 异常
                time: 1000 //2秒关闭（如果不配置，默认是3秒）
            })
        }
    })
    return false
})