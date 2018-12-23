layui.use(['table', 'form'], function () {
    $ = layui.jquery;
    table = layui.table;
    tableIns = initTable();
});

function initTable() {
    var table = layui.table, form = layui.form;

    layui.use('table', function () {
        table.render({
            elem: '#scriptTable'
            , url: '/item/selectAllItem'
            , cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
                {field: 'id', title: 'ID'}
                , {field: 'title', title: '商品名称'}
                , {field: 'price', title: '商品价格'}
                , {field: 'stock', title: '商品库存'}
                , {field: 'description', title: '商品描述'}
                , {field: 'sales', title: '商品销量'}
                , {field: 'imgUrl', title: '商品描述图片URL'}
                , {field: '', title: '查看', templet: '#buttonl', unresize: true, width: 100}
            ]]
        });
    });

    //工具栏操作
    table.on('tool(useruv)', function (obj) {
        var data = obj.data;

        if (obj.event === 'detail') {
            layer.open({
                type: 2,
                title: 'ID：' + data.id + ' 的订单详情',
                shadeClose: true,
                shade: 0.8,
                area: ['70%', '80%'],
                about: true,

                content: '/details',
                success: function () {
                    $.ajax({
                        type: "post",
                        url: "/item/detail",
                        data: {id: data.id},
                        async: false,
                        success: function (result) {
                            if (result.code == 0) {
                                // 获取子窗口id对象操作
                                var title = layer.getChildFrame("#title");
                                var summary = layer.getChildFrame("#summary");
                                var itemId = layer.getChildFrame("#itemId");
                                var buyButton = layer.getChildFrame("#buyButton");
                                var buyButton = layer.getChildFrame("#buyButton");
                                var promoDate = layer.getChildFrame("#promoDate");

                                title.append("<h4>" + result.data.description + "</h4>");

                                if (result.data.promoModel == null || result.data.promoModel.status == -1) {
                                    summary.append(
                                        "              <p class='activity'><span>活动价</span><strong class='price'><i>￥</i>" + result.data.price + "</strong></p>\n" +
                                        "              <p class='activity'><span>剩余库存</span><strong class='item'><i></i>" + result.data.stock + "</strong></p>" +
                                        "              <p class='activity'><span>销量</span><strong class='item'><i></i>" + result.data.sales + "</strong></p>");

                                    itemId.append("<input type='text' name='itemId' autocomplete='off' class='layui-input' value='" + data.id + "'>");
                                    buyButton.append("<button class=\"layui-btn layui-btn-primary purchase-btn\" lay-submit lay-filter=\"buy\">立刻购买</button>");

                                }else {
                                    summary.append("<div class=\"summary\">\n" +
                                        "              <p class=\"reference\"><span>参考价</span> <del>￥" + result.data.price + "</del></p>\n" +
                                        "              <p class=\"activity\"><span>活动价</span><strong class=\"price\"><i>￥</i>" + result.data.promoModel.promoItemPrice + "</strong></p>\n" +
                                        "              <p class='activity'><span>剩余库存</span><strong class='item'><i></i>" + result.data.stock + "</strong></p>" +
                                        "            </div>\n");
                                    itemId.append("<input type='text' name='itemId' autocomplete='off' class='layui-input' value='" + data.id + "'>");
                                    buyButton.append("<button class=\"layui-btn layui-btn-primary purchase-btn\" lay-submit lay-filter=\"buy\">立刻秒杀</button>");

                                    if (result.data.promoModel.status == 0){
                                        promoDate.value = result.data.promoModel.startData;
                                    } else {
                                        promoDate.value = result.data.promoModel.endData;
                                    }
                                }
                            }

                            form.render();
                        }
                    });
                }
            });
        }
    });

    // 打开添加商品页面
    form.on('submit(addItemPage)', function () {
        layer.open({
            type: 2,
            title: '添加商品',
            shadeClose: true,
            shade: 0.8,
            area: ['40%', '45%'],
            about: true,

            content: '/addItemPage'
        });

        //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        // return false;
    });

    //监听提交
    form.on('submit(addItem)', function (data) {
        var item = data.field;
        layer.confirm('确定提交?', {icon: 3, title: '提交'}, function (index) {
            $.ajax({
                type: "post",
                url: "/item/createItem",
                data: item,
                async: false,
                success: function (result) {
                    if (result.code == 0) {
                        layer.load(1, {time: 1000});
                        setTimeout(function () {
                            layer.msg(result.msg, {icon: 1});
                        }, 1000);

                        setTimeout(function () {
                            parent.layer.close(index);
                            window.parent.location.reload();
                        }, 3000);
                    } else {
                        layer.load(1, {time: 1000});
                        setTimeout(function () {
                            layer.msg(result.msg, {icon: 5});
                        }, 1000);
                    }
                }
            });
        });

        return false;
    });
}