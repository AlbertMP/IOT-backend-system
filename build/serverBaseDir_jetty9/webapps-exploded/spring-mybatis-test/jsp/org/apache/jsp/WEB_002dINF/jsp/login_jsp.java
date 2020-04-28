package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>登录</title>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/themes/default/easyui.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/themes/icon.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/demo.css\">\n");
      out.write("    <script type=\"text/javascript\" src=\"../js/jquery.min.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"../js/jquery.easyui.min.js\"></script>\n");
      out.write("    <script>\n");
      out.write("        ");
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            System.out.println(basePath);
        
      out.write("\n");
      out.write("        var baseUrl = '");
      out.print(basePath);
      out.write("';\n");
      out.write("        $(function () {\n");
      out.write("            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '登录');\n");
      out.write("            obj = {\n");
      out.write("                login:function () {\n");
      out.write("                    var userName = document.getElementById(\"userName\").value;\n");
      out.write("                    var userPwd = document.getElementById(\"userPwd\").value;\n");
      out.write("                    var url = baseUrl + \"user/login\";\n");
      out.write("                    $('#fm').form('submit', {\n");
      out.write("                        url:url,\n");
      out.write("                        onSubmit: function () {\n");
      out.write("                            return $(this).form('validate');\n");
      out.write("                        },\n");
      out.write("                        success:function (result) {\n");
      out.write("                            var res = $.parseJSON(result);\n");
      out.write("                            if (res != null && res.success){\n");
      out.write("                                $.messager.alert(\"提示\", \"登陆成功\", \"info\");\n");
      out.write("                                window.location = baseUrl + \"mvc/index\";\n");
      out.write("                            } else {\n");
      out.write("                                $.messager.alert(\"提示\", res.message, \"info\");\n");
      out.write("                            }\n");
      out.write("                        }\n");
      out.write("                    });\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        })\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div id=\"dlg\" class=\"easyui-dialog\" style=\"width: 400px; height: 320px; padding: 10px 20px;\" closed=\"true\" buttons=\"#dlg-buttons\">\n");
      out.write("        <form id=\"fm\" method=\"post\" novalidate>\n");
      out.write("            <div class=\"fitem\">\n");
      out.write("                <label>账号：</label>\n");
      out.write("                <input name=\"userName\" id=\"userName\" class=\"easyui-textbox\" required=\"true\"/>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"fitem\">\n");
      out.write("                <label>密码：</label>\n");
      out.write("                <input name=\"userPwd\" id=\"userPwd\" class=\"easyui-textbox\" required=\"true\"/>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("    <div id=\"dlg-buttons\">\n");
      out.write("        <a href=\"javascript:void(0)\" class=\"easyui-linkbutton c6\" iconCls=\"icon-ok\" onclick=\"obj.login()\" style=\"width: 90px\">login</a>\n");
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
