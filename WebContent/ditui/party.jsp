<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kb.business.xianhuo365.bean.UserBean"%>
<%@ page import="kb.business.xianhuo365.util.ActivityFullUtil"%>
<%@ page import="kb.business.xianhuo365.bean.ActivityFullBean"%>
<!DOCTYPE html>
<%
  //拒绝未经登录的直接访问
  request.setCharacterEncoding("UTF-8");
  UserBean ub = (UserBean)session.getAttribute("userbean");
  if (null == ub) {
    pageContext.forward("login.jsp");
    return;
  }
  //当前一个员工只属于一个地推组、一个地推组一天只有一个活动；使用List预留扩展可能性，但目前只使用list.get(0)
  ArrayList<ActivityFullBean> list = ActivityFullUtil.getActFul1(ub.getId());
%>
  <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>今日活动 地推管理</title>
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
            <button class="btn">今日活动</button>
            <button data-toggle="dropdown" class="btn dropdown-toggle">
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
              <li>
                <a href="doing.jsp">所有地推组</a></li>
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
      
      <%
        if ((null == list) || (0 ==list.size())) {
      %>
      <div class="row-fluid"><!--没有信息展示-->
        <div class="span12">
          <div class="page-header">
            <h1>今日名下没有活动，请联系管理员录入
              <small></small></h1>
          </div>
        </div>
      </div><!--END没有信息展示-->
      <%
        } else {
          ActivityFullBean bean = list.get(0);
          session.setAttribute("activity", bean);
      %>
      <div class="row-fluid"><!--信息展示-->
        <div class="span12">
          <h2>活动信息</h2>
          <p>
            <ul>
              <li>地推组名称：<%=bean.getGroupNam() %></li>
              <li>地点：<%=bean.getPtyAdr() %></li>
              <li>时间：<%=bean.getPtyDte() %></li>
              <li>组长：<%=bean.getOwnNam() %></li>
              <li>销售总额（实时）：<%=bean.getSales() %>元</li>
              <li>场地费：<%=bean.getFee1() %>元</li>
              <li>物料费：<%=bean.getFee2() %>元</li>
              <li>车辆费：<%=bean.getFee3() %>元</li>
              <li>杂项：<%=bean.getFee4() %>元</li>
            </ul>
          </p>
          <p>
            <a class="btn" href="sales.jsp">销售量录入»</a></p>
        </div>
      </div><!--END信息展示-->
      <%
        }
      %>

    </div><!--END主容器-->
    
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script type="text/javascript" src="res/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="res/js/jquery-ui"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="res/js/bootstrap.min.js"></script>
  </body>
</html>