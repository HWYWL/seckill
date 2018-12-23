layui.use(['util', 'laydate', 'layer', 'table', 'form'], function(){
    var util = layui.util
        ,laydate = layui.laydate
        ,layer = layui.layer;
    //固定块
    util.fixbar({
        bar1: true
        ,bar2: true
        ,css: {right: 50, bottom: 100}
        ,bgcolor: '#393D49'
        ,click: function(type){
            if(type === 'bar1'){
                layer.msg('icon是可以随便换的')
            } else if(type === 'bar2') {
                layer.msg('两个bar都可以设定是否开启')
            }
        }
    });

    //倒计时
    var thisTimer, setCountdown = function(y, M, d, H, m, s){
        var endTime = new Date(y, M||0, d||1, H||0, m||0, s||0) //结束日期
            ,serverTime = new Date(); //假设为当前服务器时间，这里采用的是本地时间，实际使用一般是取服务端的

        clearTimeout(thisTimer);
        util.countdown(endTime, serverTime, function(date, serverTime, timer){
            var str = date[0] + '天' + date[1] + '时' +  date[2] + '分' + date[3] + '秒';
            lay('#test2').html(str);
            thisTimer = timer;
        });
    };
    setCountdown(2099,1,1);

    laydate.render({
        elem: '#test1'
        ,type: 'datetime'
        ,done: function(value, date){
            setCountdown(date.year, date.month - 1, date.date, date.hours, date.minutes, date.seconds);
        }
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