<%@ page import="com.cl.common.Contants" %>
<%@ page import="com.cl.model.UserDO" %><%--
  Created by IntelliJ IDEA.
  User: albert
  Date: 2020/4/17
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        var data = [{
            text: '系统管理',
            iconCls: 'fa fa-wpforms',
            state: 'open',
            children: [{
                text: '用户管理',
                url: '../mvc/userlist'
            }]
        }, {
            text: '终端管理',
            iconCls: 'fa fa-at',
            children: [{
                text: '在线设备管理',
                url: '../mvc/devicedatalist'
            },{
                text: '设备管理',
                url: '../mvc/devicelist'
            }]
        }];
        function onSideMenuSelect(item) {
            if (!$('#mainTab').tabs('exists', item.text)) {
                $('#mainTab').tabs('add', {
                    title: item.text,
                    content: '<iframe scrolling="auto" frameborder="0" src="' + item.url +'" style="width: 100%;height: 99%;"></iframe>',
                    closable: true,
                    id: item.id
                });
            } else {
                $('#mainTab').tabs('select', item.text);
            }
        };

        <%  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            System.out.println(basePath);
        %>
        var baseUrl = '<%=basePath%>';
        obj = {
            logout: function () {
                location.href=baseUrl + "user/logout";
            }
        }
    </script>
</head>
<body class="easyui-layout">
    <%  UserDO userDO = (UserDO) request.getSession().getAttribute(Contants.USER_SESSION);
    %>
    <div data-options="region:'north',border:false" style="width: 100%; height: 12%; background-color: #c7e2de">
        <h1 style="float: left; padding-left: 5px">物联网云平台</h1>
        <div style="float: right; padding-right: 5px">
            <h1 style="float: right">用户：<%=userDO.getUserName()%></h1>
            <div id="dlg-buttons">
                <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-no" onclick="obj.logout()" style="float: right">log out</a>
            </div>
        </div>
    </div>
    <div data-options="region:'west',split:true,title:'菜单'" style="width: 250px; height: 99%">
        <div id="leftMenu" class="easyui-sidemenu" data-options="data:data,onSelect:onSideMenuSelect"></div>
    </div>
    <div data-options="region:'center',title:'Center'">
        <div id="mainTab" class="easyui-tabs" data-options="" style="width: 100%; height: 99%">
        </div>
    </div>
</body>
