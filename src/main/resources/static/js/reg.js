$(function(){

    function checkPhone(phone){
        var status = true;
        if (phone == '') {
            $('.num2-err').removeClass('hide').find("em").text('请输入手机号');
            return false;
        }
        var param = /^1[34578]\d{9}$/;
        if (!param.test(phone)) {
            $('.num2-err').removeClass('hide');
            $('.num2-err').text('手机号不合法，请重新输入');
            return false;
        }
        $.ajax({
            url: '/user/getotp',
            type: 'post',
            async: false,
            data: {"telphone":phone},
            success:function(data){
                if (data.code == '0') {
                    $('.num2-err').addClass('hide');
                } else {
                    $('.num2-err').removeClass('hide').text(data.msg);
                    status = false;
                }
            },
            error:function(){
                status = false;
            }
        });

        return status;
    }

    // 发送验证码
    $(".form-data").delegate(".send","click",function () {
        var phone = $.trim($('#num2').val());
        if (checkPhone(phone)) {
            var oTime = $(".form-data .time"),
                oSend = $(".form-data .send"),
                num = parseInt(oTime.text()),
                oEm = $(".form-data .time em");
            $(this).hide();
            oTime.removeClass("hide");
            var timer = setInterval(function () {
                var num2 = num-=1;
                oEm.text(num2);
                if(num2==0){
                    clearInterval(timer);
                    oSend.text("重新发送验证码");
                    oSend.show();
                    oEm.text("120");
                    oTime.addClass("hide");
                }
            },1000);
        }
    });
});