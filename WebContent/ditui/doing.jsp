<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kb.business.xianhuo365.bean.UserBean"%>
<%@ page import="kb.business.xianhuo365.bean.ActivityLstBean"%>
<%@ page import="kb.business.xianhuo365.bean.ActivityFullBean"%>
<%@ page import="kb.business.xianhuo365.util.ActivityFullUtil"%>
<!DOCTYPE html>
<%
  //拒绝未经登录的直接访问
  request.setCharacterEncoding("UTF-8");
  UserBean ub = (UserBean)session.getAttribute("userbean");
  if (null == ub) {
    pageContext.forward("login.jsp");
    return;
  }
  final int NUM_PER_PAGE = 4;
  int index = Integer.parseInt(request.getParameter("index"));
  ActivityLstBean bean = ActivityFullUtil.getActFulLst(index, NUM_PER_PAGE);
  ArrayList<ActivityFullBean> list = bean.getList();
  int pages = bean.getNum();
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>所有地推组 地推管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- 引入 Bootstrap -->
    <link href="res/css/bootstrap-combined.min.css" rel="stylesheet" media="screen">
    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file:// 引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <!--网页主体-->
    <div class="container-fluid"><!--主容器-->
      <div class="row-fluid"><!--导航按钮-->
        <div class="span12">
          <div class="btn-group">
            <button class="btn">所有地推组</button>
            <button data-toggle="dropdown" class="btn dropdown-toggle">
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
              <li>
                <a href="party.jsp">今日活动</a></li>
              <li>
                <a href="perinfo.jsp">维护个人信息</a></li>
              <li class="divider"></li>
              <li>
                <a href="quit.do">注销</a></li>
            </ul>
          </div>
          <div class="page-header"></div>
        </div>
      </div><!--END导航按钮-->
      
      <div class="row-fluid"><!--地推组展示-->
        <div class="span12">
        <%
          for (int i = 1; i <= list.size(); ++i) {
            ActivityFullBean b = ActivityFullUtil.getActFul2(((ActivityFullBean)list.get(i - 1)).getPtyId());
            if (null != b) {
        %>
          <h2><%=i %>：<%=b.getGroupNam() %></h2>
          <p>地点：<%=b.getPtyAdr() %></p>
          <p>时间：<%=b.getPtyDte() %></p>
          <p>组长：<%=b.getOwnNam() %></p>
          <p>销售总额（实时）：<%=b.getSales() %>元</p>
          <p>场地费：：<%=b.getFee1() %>元</p>
          <p>物料费：：<%=b.getFee2() %>元</p>
          <p>车辆费：<%=b.getFee3() %>元</p>
          <p>杂项：<%=b.getFee4() %>元</p>
        <%
            }
          }
        %>
          <div class="pagination pagination-centered">
            <ul>
            <%
              for (int i = 1; i <= pages; ++i) {
                if (i == index) {
            %>
              <li>
                <a><b><i><%=i %></i></b></a></li>
            <%
                } else {
            %>
              <li>
                <a href="doing?index=<%=i %>"><%=i %></a></li>
            <%
                }
              }
            %>
            </ul>
          </div>

        </div>
      </div><!--END地推组展示-->
    </div><!--END主容器-->
    
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script type="text/javascript" src="res/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="res/js/jquery-ui"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="res/js/bootstrap.min.js"></script>
  </body>
</html>