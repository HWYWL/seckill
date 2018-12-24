layui.use(['util', 'laydate', 'layer', 'table', 'form'], function(){
    var table = layui.table, form = layui.form, layer = layui.layer;

    var mm = layui.mm, $ = layui.$;
    var cur = $('.number-cont input').val();
    $('.number-cont .btn').on('click', function () {
        if ($(this).hasClass('add')) {
            cur++;

        } else {
            if (cur > 1) {
                cur--;
            }
        }
        $('.number-cont input').val(cur);
        $('#quantity input').val(cur);
    });

    // 购买商品
    form.on('submit(buy)', function (data) {
        var item = data.field;
        layer.confirm('确定购买?', {icon: 3, title: '购买'}, function (index) {
            $.ajax({
                type: "post",
                url: "/item/buy",
                data: item,
                async: false,
                success: function (result) {
                    if (result.code == 0) {
                        layer.load(1, {time: 1000});
                        setTimeout(function () {
                            layer.msg(result.msg, {icon: 1});
                        }, 1000);
                    } else {
                        layer.load(1, {time: 1000});
                        setTimeout(function () {
                            layer.msg(result.data.errMsg, {icon: 5});
                        }, 1000);
                        setTimeout(function () {
                            // 用户未登录
                            if (result.data.errCode == 20004) {
                                window.parent.location.replace("/login");
                            }
                        }, 2000);
                    }
                }
            });
        });

        //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        return false;
    });
});

// 倒计时
function count() {
    $("input[name='countDown']").each(function () {
        var time_end=this.value;
        var con=$(this).next("span");
        var _=this.dataset;
        countDown(con,{
            title:_.title,//优先级最高,填充在prefix位置
            prefix:_.prefix,//前缀部分
            suffix:_.suffix,//后缀部分
            time_end:time_end//要到达的时间
        })
        //提供3个事件分别为:启动,重启,停止
            .on("countDownStarted countDownRestarted  countDownEnded ",function (arguments) {
                console.info(arguments);
            });
    });
}