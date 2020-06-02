<%--
  Created by IntelliJ IDEA.
  User: albert
  Date: 2020/6/1
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script>
        <%  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            System.out.println(basePath);
        %>
        var baseUrl = '<%=basePath%>';

        $(function(){

            var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
                renderRow: function(target, fields, frozen, rowIndex, rowData){
                    var cc = [];
                    cc.push('<td colspan=' + fields.length + ' style="padding:10px 5px; border:0;">');
                    var img = baseUrl + rowData.devImg;
                    cc.push('<img src=' + img + ' style="width: 150px;float: left">');
                    cc.push('<div style="float: left;margin-left: 20px;">');
                    for (var i=0; i<fields.length; i++){
                        var copts  = $(target).datagrid('getColumnOption', fields[i]);
                        var val = rowData[fields[i]];
                        if (fields[i] == 'devType'){
                            val = change.deviceType(val);
                        }else if (fields[i] == 'devStatus'){
                            val = change.deviceStatus(val);
                        }
                        cc.push('<p><span class="c-label">' + copts.title + ':</span>' + val + '</p>');
                    }
                    var str = '<a href="#" name="open" class="easyui-linkbutton" onclick="obj.sendData(\'' + rowData.devNo + '\', 1)"></a>' +
                        '<a href="#" name="close" class="easyui-linkbutton" onclick="obj.sendData(\'' + rowData.devNo + '\', 0)"></a>';
                    cc.push('<p><span class="c-label">操作:</span> ' + str + '</p>');
                    cc.push('</div>');
                    cc.push('</td>');
                    return cc.join('');
                }
            });

            $('#tb1').datagrid({
                url: baseUrl + 'device/pageDevice',
                width: 1100,
                title: '设备在线管理',
                method: 'get',
                singleSelect: 'true',
                columns: [[
                    {field: 'id', title: 'id', width: 150},
                    {field: 'devType', title: '设备类型', width: 150},
                    {field: 'devNo', title: '设备编号', width: 150},
                    {field: 'devName', title: '设备名称', width: 150},
                    {field: 'devStatus', title: '设备状态', width: 150}
                ]],
                pagination: true,
                view: cardview,
                onLoadSuccess:function(data){
                    $("a[name='open']").linkbutton({text:'打开',plain:true,iconCls:'icon-add'});
                    $("a[name='close']").linkbutton({text:'关闭',plain:true,iconCls:'icon-add'});
                }
            });

            obj = {
                sendData: function (devNo, data) {
                    $.post(baseUrl + "mqtt/send",
                        {
                            devNo: devNo,
                            data: data
                        },
                        function(res, status){
                            if (res != null && res.success){
                                $.messager.alert("提示", "操作成功", "info");
                                $('#tb1').datagrid('reload');
                            } else {
                                $.messager.alert("提示", "操作失败", "info");
                            }
                        }
                    );
                }
            }
        });

    </script>
</head>
<body>
    <div style="margin:20px 0"></div>
    <table id="tb1">

    </table>

</body>
</html>
