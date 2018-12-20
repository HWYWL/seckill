layui.use(['table','form'], function() {
    $ = layui.jquery;
    table = layui.table;
    tableIns = initTable();
});

function initTable() {
    var table = layui.table,form = layui.form;

    layui.use('table', function(){
        table.render({
            elem: '#scriptTable'
            ,url:'/item/selectAllItem'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'ID'}
                ,{field:'title', title: '商品名称'}
                ,{field:'price', title: '商品价格'}
                ,{field:'stock', title: '商品库存'}
                ,{field:'description', title: '商品描述'}
                ,{field:'sales', title: '商品销量'}
                ,{field:'imgUrl', title: '商品描述图片URL'}
            ]]
        });
    });

    // 打开添加商品页面
    form.on('submit(addItemPage)', function(){
        layer.open({
            type: 2,
            title: '添加商品',
            shadeClose: true,
            shade: 0.8,
            area: ['50%', '65%'],
            about:true,

            content: '/addItemPage'
        });

        //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        // return false;
    });

    //监听提交
    form.on('submit(addItem)', function(data){
        var item = data.field;
        layer.confirm('确定提交?', {icon: 3, title:'提交'}, function(index){
            $.ajax({
                type:"post",
                url:"/item/createItem",
                data:item,
                async : false,
                success:function(result){
                    if(result.code == 0){
                        layer.load(1,{time: 1000});
                        setTimeout(function(){
                            layer.msg(result.msg,{icon:1});
                        },1000);

                        setTimeout(function(){
                            parent.layer.close(index);
                            window.parent.location.reload();
                        },3000);
                    }else {
                        layer.load(1,{time: 1000});
                        setTimeout(function(){
                            layer.msg(result.msg,{icon:5});
                        },1000);
                    }
                }
            });
        });

        return false;
    });
}