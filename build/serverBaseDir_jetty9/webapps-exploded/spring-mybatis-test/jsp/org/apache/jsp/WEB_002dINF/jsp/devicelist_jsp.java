package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class devicelist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Title</title>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/themes/default/easyui.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/themes/icon.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/demo.css\">\n");
      out.write("    <script type=\"text/javascript\" src=\"../js/jquery.min.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"../js/jquery.easyui.min.js\"></script>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        var baseUrl = \"../\";\n");
      out.write("\n");
      out.write("        $(function () {\n");
      out.write("            $('#tb1').datagrid({\n");
      out.write("                url: baseUrl + 'device/pageDevice',\n");
      out.write("                width: 1100,\n");
      out.write("                title: \"设备管理\",\n");
      out.write("                method: 'get',\n");
      out.write("                toolbar: '#tb',\n");
      out.write("                singleSelect: 'true',\n");
      out.write("                columns: [[\n");
      out.write("                    {field: 'id', title: 'id', width: 150},\n");
      out.write("                    {field: 'devNo', title: '设备编号', width: 150},\n");
      out.write("                    {field: 'devName', title: '设备名称', width: 150},\n");
      out.write("                    {field: 'operatation', title: '操作', align: 'center', width:$(this).width()*0.1, formatter:function (value, row, index) {\n");
      out.write("                        var str = '<a href=\"#\" name=\"update\" class=\"easyui-linkbutton\" onclick=\"obj.update('+index+')\"></a>' +\n");
      out.write("                                '<a href=\"#\" name=\"delete\" class=\"easyui-linkbutton\" onclick=\"obj.delete('+index+')\"></a>';\n");
      out.write("                        return str;\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("                ]],\n");
      out.write("                pagination: true,\n");
      out.write("                pageSize: 10,\n");
      out.write("                pageList: [10, 15, 20, 25],\n");
      out.write("                onLoadSuccess: function (data) {\n");
      out.write("                    $(\"a[name='update']\").linkbutton({text:'修改', plain:true, iconCls:'icon-add'});\n");
      out.write("                    $(\"a[name='delete']\").linkbutton({text:'删除', plain:true, iconCls:'icon-add'});\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        obj = {\n");
      out.write("            search: function () {\n");
      out.write("                $('#tb1').datagrid('load', {\n");
      out.write("                    devNo: $('#searchDevNo').val()\n");
      out.write("                });\n");
      out.write("            },\n");
      out.write("            add: function () {\n");
      out.write("                $('#fm').form('clear');\n");
      out.write("                $('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加设备');\n");
      out.write("            },\n");
      out.write("            close: function () {\n");
      out.write("                $('#dlg').dialog('close');\n");
      out.write("            },\n");
      out.write("            update:function (index) {\n");
      out.write("                if (index != null){\n");
      out.write("                    $('#tb1').datagrid('selectRow', index);\n");
      out.write("                }\n");
      out.write("                var row = $('#tb1').datagrid('getSelected');\n");
      out.write("                if (row){\n");
      out.write("                    $('#dlg').dialog('open').dialog('center').dialog('setTitle', '修改设备');\n");
      out.write("                    $('#fm').form('load', {\n");
      out.write("                        id:row.id,\n");
      out.write("                        devNo:row.devNo,\n");
      out.write("                        devName:row.devName\n");
      out.write("                    });\n");
      out.write("                }\n");
      out.write("            },\n");
      out.write("            delete:function (index) {\n");
      out.write("                if (index!=null){\n");
      out.write("                    $('#tb1').datagrid('selectRow',index);\n");
      out.write("                }\n");
      out.write("                var row =$('#tb1').datagrid('getSelected');\n");
      out.write("                if (row){\n");
      out.write("                    $.messager.confirm('Confirm','确定删除设备吗？',function (r) {\n");
      out.write("                        if (r){\n");
      out.write("                            $.post(baseUrl+'device/delete',{id:row.id},function (result) {\n");
      out.write("                                if (result!=null && result.success){\n");
      out.write("                                    $.messager.alert(\"提示\",\"删除成功\",\"info\");\n");
      out.write("                                    $('#tb1').datagrid('reload');\n");
      out.write("                                }else {\n");
      out.write("                                    $.messager.alert(\"提示\",\"删除失败\",\"info\");\n");
      out.write("                                }\n");
      out.write("                            })\n");
      out.write("                        }\n");
      out.write("                    });\n");
      out.write("                }\n");
      out.write("            },\n");
      out.write("            save: function () {\n");
      out.write("                var id = document.getElementById(\"id\").value;\n");
      out.write("                var devNo = document.getElementById(\"devNo\").value;\n");
      out.write("                var devName = document.getElementById(\"devName\").value;\n");
      out.write("                var url = \"\";\n");
      out.write("                if (id){\n");
      out.write("                    url = baseUrl + \"device/update\";\n");
      out.write("                }else {\n");
      out.write("                    url = baseUrl + \"device/insert\";\n");
      out.write("                }\n");
      out.write("                $('#fm').form('submit', {\n");
      out.write("                    url: url,\n");
      out.write("                    onSubmit: function () {\n");
      out.write("                        return $(this).form('validate');\n");
      out.write("                    },\n");
      out.write("                    success: function (result) {\n");
      out.write("                        var res = $.parseJSON(result);\n");
      out.write("                        if (res != null && res.success) {\n");
      out.write("                            if (id) {\n");
      out.write("                                obj.close();\n");
      out.write("                                $.messager.alert(\"提示\", \"更新成功\", \"info\");\n");
      out.write("                                $('#tb1').datagrid('reload');\n");
      out.write("                            }else {\n");
      out.write("                                obj.close();\n");
      out.write("                                $.messager.alert(\"提示\", \"添加成功\", \"info\");\n");
      out.write("                                $('#tb1').datagrid('reload');\n");
      out.write("                            }\n");
      out.write("                        }else {\n");
      out.write("                            $.messager.alert(\"提示\", \"失败\", \"info\");\n");
      out.write("                        }\n");
      out.write("                        $('#fm').form('clear');\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            },\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div style=\"margin: 20px 0;\"></div>\n");
      out.write("    <table id=\"tb1\">\n");
      out.write("\n");
      out.write("    </table>\n");
      out.write("    <div id=\"tb\" style=\"padding: 2px 5px;\">\n");
      out.write("        <div style=\"margin-bottom: 5px\">\n");
      out.write("            <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"obj.add()\">添加</a>\n");
      out.write("        </div>\n");
      out.write("        设备编号：<input id=\"searchDevNo\" class=\"easyui-textbox\"/>\n");
      out.write("        <a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"obj.search()\">Search</a>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div id=\"dlg\" class=\"easyui-dialog\" style=\"width: 500px; height: 300px;\" closed=\"true\" buttons=\"#dlg-buttons\">\n");
      out.write("        <form id=\"fm\" method=\"post\" novalidate>\n");
      out.write("            <div class=\"fitem\" style=\"margin-left: 10px;margin-top: 20px;\">\n");
      out.write("                <label>id:</label>\n");
      out.write("                <input name=\"id\" id=\"id\" class=\"easyui-textbox\"/>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"fitem\" style=\"margin-left: 10px;margin-top: 20px;\">\n");
      out.write("                <label>设备编号：</label>\n");
      out.write("                <input name=\"devNo\" id=\"devNo\" class=\"easyui-textbox\" required=\"true\"/>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"fitem\" style=\"margin-left: 10px;margin-top: 20px;\">\n");
      out.write("                <label>设备名称：</label>\n");
      out.write("                <input name=\"devName\" id=\"devName\" class=\"easyui-textbox\" required=\"true\"/>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("    <div id=\"dlg-buttons\">\n");
      out.write("        <a href=\"javascript:void(0)\" class=\"easyui-linkbutton c6\" iconCls=\"icon-ok\" onclick=\"obj.save()\" style=\"width: 100px;\">Save</a>\n");
      out.write("        <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-cancel\" onclick=\"obj.close()\" style=\"width: 100px;\">Cancel</a>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
