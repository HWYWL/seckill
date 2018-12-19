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
            ,url:'/user/selectAllUser'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'ID'}
                ,{field:'name', title: '用户名'}
                ,{field:'gender', title: '性别'}
                ,{field:'age', title: '年龄'}
                ,{field:'telphone', title: '电话号码'}
                ,{field:'registerMode', title: '注册方式'}
                ,{field:'thirdPartyId', title: '第三方登录id'}
            ]]
            ,done:function () {
                //分类显示中文名称
                $("[data-field='gender']").children().each(function(){
                    if($(this).text()=='1'){
                        $(this).text("男")
                    }else {
                        $(this).text("女")
                    }
                });
            }
        });
    });
}