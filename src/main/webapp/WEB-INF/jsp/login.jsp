<%--
  Created by IntelliJ IDEA.
  User: albert
  Date: 2020/4/24
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script>
        <%  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            System.out.println(basePath);
        %>
        var baseUrl = '<%=basePath%>';
        $(function () {
            $('#dlg1').dialog('open').dialog('center').dialog('setTitle', '登录');
            obj = {
                login:function () {
                    var userName = document.getElementById("userName").value;
                    var userPwd = document.getElementById("userPwd").value;
                    var url = baseUrl + "user/login";
                    $('#fm1').form('submit', {
                        url:url,
                        onSubmit: function () {
                            return $(this).form('validate');
                        },
                        success:function (result) {
                            var res = $.parseJSON(result);
                            if (res != null && res.success){
                                $.messager.alert("提示", "登陆成功", "info");
                                window.location = baseUrl + "mvc/index";
                            } else {
                                $.messager.alert("提示", res.message, "info");
                            }
                        }
                    });
                },
                add: function () {
                    $('#fm').form('clear');
                    $('#dlg1').dialog('close');
                    $('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加用户');
                },
                close: function () {
                    $('#dlg').dialog('close');
                    $('#dlg1').dialog('open').dialog('center').dialog('setTitle', '登录');
                },
                save: function () {
                    var userNo = document.getElementById("regUserNo").value;
                    var userName = document.getElementById("regUserName").value;
                    var userPwd = document.getElementById("regUserPwd").value;
                    var roleKey = document.getElementById("regRoleKey").value;
                    var url = "";
                    url = baseUrl + "user/register";
                    $('#fm').form('submit', {
                        url: url,
                        onSubmit: function () {
                            return $(this).form('validate');
                        },
                        success: function (result) {
                            var res = $.parseJSON(result);
                            if (res != null && res.success) {
                                obj.close();
                                $.messager.alert("提示", "注册成功", "info");
                                window.location = baseUrl + "mvc/index";
                            }else {
                                $.messager.alert("提示", "注册失败", "info");
                            }
                            $('#fm').form('clear');
                        }
                    });
                }
            }
            var data = [
                {'text': '管理员', 'value': 'ADMIN'},
                {'text': '普通用户', 'value': 'NORMAL'}
            ];

            $('#regRoleKey').combobox({
                textField: 'text',
                valueField: 'value',
                panelHeight: 'auto',
                data: data
            });
        })
    </script>
</head>
<body>

    <%--登陆界面--%>
    <div id="dlg1" class="easyui-dialog" style="width: 400px; height: 320px; padding: 10px 20px;" closed="true" buttons="#dlg-buttons">
        <form id="fm1" method="post" novalidate>
            <div class="fitem" style="margin-top: 5px">
                <label>账号：</label>
                <input name="userName" id="userName" class="easyui-textbox" required="true"/>
            </div>
            <div class="fitem" style="margin-top: 5px">
                <label>密码：</label>
                <input name="userPwd" id="userPwd" class="easyui-textbox" required="true"/>
            </div>

            <div id="dlg1-buttons" style="margin-top: 15px">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="obj.add()" style="width: 90px">注册</a>
            </div>

        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="obj.login()" style="width: 90px">login</a>
    </div>

    <%--注册界面--%>
    <div id="dlg" class="easyui-dialog" style="width: 500px; height: 300px;" closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post" novalidate>
            <div class="fitem" style="margin-left: 10px;margin-top: 20px;">
                <label>用户编号：</label>
                <input name="regUserNo" id="regUserNo" class="easyui-textbox" required="true"/>
            </div>
            <div class="fitem" style="margin-left: 10px;margin-top: 20px;">
                <label>角色   ：</label>
                <input name="regRoleKey" id="regRoleKey" required="true"/>
            </div>
            <div class="fitem" style="margin-left: 10px;margin-top: 20px;">
                <label>用户名称：</label>
                <input name="regUserName" id="regUserName" class="easyui-textbox" required="true"/>
            </div>
            <div class="fitem" style="margin-left: 10px;margin-top: 20px;">
                <label>用户密码：</label>
                <input name="regUserPwd" id="regUserPwd" class="easyui-textbox" required="true"/>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="obj.save()" style="width: 100px;">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="obj.close()" style="width: 100px;">Cancel</a>
    </div>
</body>
</html>
