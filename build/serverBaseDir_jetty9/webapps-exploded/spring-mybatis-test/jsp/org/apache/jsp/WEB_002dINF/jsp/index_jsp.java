package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cl.common.Contants;
import com.cl.model.UserDO;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>首页</title>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/themes/default/easyui.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/themes/icon.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/demo.css\">\n");
      out.write("    <script type=\"text/javascript\" src=\"../js/jquery.min.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"../js/jquery.easyui.min.js\"></script>\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("        var data = [{\n");
      out.write("            text: '系统管理',\n");
      out.write("            iconCls: 'fa fa-wpforms',\n");
      out.write("            state: 'open',\n");
      out.write("            children: [{\n");
      out.write("                text: '用户管理',\n");
      out.write("                url: '../mvc/userlist'\n");
      out.write("            }]\n");
      out.write("        }, {\n");
      out.write("            text: '终端管理',\n");
      out.write("            iconCls: 'fa fa-at',\n");
      out.write("            children: [{\n");
      out.write("                text: '设备管理',\n");
      out.write("                url: '../mvc/devicelist'\n");
      out.write("            }]\n");
      out.write("        }];\n");
      out.write("        function onSideMenuSelect(item) {\n");
      out.write("            if (!$('#mainTab').tabs('exists', item.text)) {\n");
      out.write("                $('#mainTab').tabs('add', {\n");
      out.write("                    title: item.text,\n");
      out.write("                    content: '<iframe scrolling=\"auto\" frameborder=\"0\" src=\"' + item.url +'\" style=\"width: 100%;height: 99%;\"></iframe>',\n");
      out.write("                    closable: true,\n");
      out.write("                    id: item.id\n");
      out.write("                });\n");
      out.write("            } else {\n");
      out.write("                $('#mainTab').tabs('select', item.text);\n");
      out.write("            }\n");
      out.write("        };\n");
      out.write("\n");
      out.write("        ");
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            System.out.println(basePath);
        
      out.write("\n");
      out.write("        var baseUrl = '");
      out.print(basePath);
      out.write("';\n");
      out.write("        obj = {\n");
      out.write("            logout: function () {\n");
      out.write("                location.href=baseUrl + \"user/logout\";\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body class=\"easyui-layout\">\n");
      out.write("    ");
  UserDO userDO = (UserDO) request.getSession().getAttribute(Contants.USER_SESSION);
    
      out.write("\n");
      out.write("    <div data-options=\"region:'north',border:false\" style=\"width: 100%; height: 12%; background-color: #c7e2de\">\n");
      out.write("        <h1 style=\"float: left; padding-left: 5px\">物联网云平台</h1>\n");
      out.write("        <div style=\"float: right; padding-right: 5px\">\n");
      out.write("            <h1 style=\"float: right\">用户：");
      out.print(userDO.getUserName());
      out.write("</h1>\n");
      out.write("            <div id=\"dlg-buttons\">\n");
      out.write("                <a href=\"javascript:void(0)\" class=\"easyui-linkbutton c6\" iconCls=\"icon-no\" onclick=\"obj.logout()\" style=\"float: right\">log out</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <div data-options=\"region:'west',split:true,title:'菜单'\" style=\"width: 250px; height: 99%\">\n");
      out.write("        <div id=\"leftMenu\" class=\"easyui-sidemenu\" data-options=\"data:data,onSelect:onSideMenuSelect\"></div>\n");
      out.write("    </div>\n");
      out.write("    <div data-options=\"region:'center',title:'Center'\">\n");
      out.write("        <div id=\"mainTab\" class=\"easyui-tabs\" data-options=\"\" style=\"width: 100%; height: 99%\">\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
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
