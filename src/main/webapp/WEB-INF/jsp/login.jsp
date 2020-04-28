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
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '登录');
            obj = {
                login:function () {
                    var userName = document.getElementById("userName").value;
                    var userPwd = document.getElementById("userPwd").value;
                    var url = baseUrl + "user/login";
                    $('#fm').form('submit', {
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
                }
            }
        })
    </script>
</head>
<body>
    <div id="dlg" class="easyui-dialog" style="width: 400px; height: 320px; padding: 10px 20px;" closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>账号：</label>
                <input name="userName" id="userName" class="easyui-textbox" required="true"/>
            </div>
            <div class="fitem">
                <label>密码：</label>
                <input name="userPwd" id="userPwd" class="easyui-textbox" required="true"/>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="obj.login()" style="width: 90px">login</a>
    </div>
</body>
</html>
