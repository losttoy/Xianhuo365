<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kb.business.xianhuo365.bean.UserBean"%>
<!DOCTYPE html>
<%
  //拒绝未经登录的直接访问
  request.setCharacterEncoding("UTF-8");
  UserBean ub = (UserBean)session.getAttribute("userbean");
  if (null == ub) {
    pageContext.forward("login.jsp");
    return;
  }
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>导航</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- 引入 Bootstrap -->
    <link href="res/css/bootstrap.min.css" rel="stylesheet">
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
      <div class="row-fluid">
        <div class="span12">
          <div class="container-fluid"><!--仪表盘-->
            <div class="row">
              <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
              
                <!--仪表盘标题-->
                <h1 class="page-header">天天鲜活<small>地推管理</small></h1>
                
                <!--仪表盘List-->
                <div class="row placeholders">
                
                  <!--今日活动-->
                  <div class="col-xs-6 col-sm-3 placeholder">
                    <a href="party.jsp">
                      <img class="img-responsive" alt="20sx200" src="res/icon/ic_accessibility_black_48dp.png" /></a>
                    <h4>今日活动</h4>
                    <span class="text-muted">所在地推组信息</span>
                  </div><!--END 今日活动-->
                  
                  <!--看看同事们在做什么-->
                  <div class="col-xs-6 col-sm-3 placeholder">
                    <a href="doing.jsp?index=1">
                      <img class="img-responsive" alt="200x200" src="res/icon/ic_account_balance_black_48dp.png" /></a>
                    <h4>所有地推组</h4>
                    <span class="text-muted">看看同事们在做什么</span>
              </div><!--END 看看同事们在做什么-->
                  
                  <!--维护个人信息-->
                  <div class="col-xs-6 col-sm-3 placeholder">
                    <a href="perinfo.jsp">
                      <img class="img-responsive" alt="200x200" src="res/icon/ic_account_box_black_48dp.png" /></a>
                    <h4>维护个人信息</h4>
                    <span class="text-muted">修改姓名、密码</span>
              </div><!--END 维护个人信息-->
                  
                  <!--注销-->
                  <div class="col-xs-6 col-sm-3 placeholder">
                    <a href="quit.do">
                      <img class="img-responsive" alt="200x200" src="res/icon/ic_input_black_48dp.png" /></a>
                    <h4>注销</h4>
                    <span class="text-muted">退出登录</span>
                  </div><!--END 注销-->
                  
                </div><!--END 仪表盘List-->
                
              </div>
            </div>
          </div><!--END 仪表盘-->
        </div>
      </div>
    </div><!--END 主容器-->
    
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="res/js/jquery.js"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="res/js/bootstrap.min.js"></script>
  </body>
</html>