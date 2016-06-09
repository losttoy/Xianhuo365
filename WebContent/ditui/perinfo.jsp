<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kb.business.xianhuo365.bean.UserBean"%>
<!DOCTYPE html>
<%
  //提示客户个人信息维护结果
  String perchgMes = (String)session.getAttribute("perchgMes");

  //成功
  if ((null != perchgMes) && "chgSuc".equals(perchgMes)) {
    session.removeAttribute("perchgMes");
%>
<script type="text/javascript">
    alert("个人信息维护成功！");
</script>
<%
  }

  //失败
  if ((null != perchgMes) && "chgErr".equals(perchgMes)) {
    session.removeAttribute("perchgMes");
%>
<script type="text/javascript">
    alert("个人信息维护不成功，请重试！");
</script>
<%
  }
%>

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
    <title>维护个人信息 地推管理</title>
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
            <button class="btn">维护个人信息</button>
            <button data-toggle="dropdown" class="btn dropdown-toggle">
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
              <li>
                <a href="party.jsp">今日活动</a></li>
              <li>
                <a href="doing.jsp">所有地推组</a></li>
              <li class="divider"></li>
              <li>
                <a href="quit.do">注销</a></li>
            </ul>
          </div>
          <div class="page-header"></div>
        </div>
      </div><!--END导航按钮-->
      
      <div class="row-fluid"><!--个人信息展示-->
        <div class="span12">
          <form action="perchg.do" method="post" >
            <fieldset>
              <legend>个人资料</legend>
              <a href="chgPwd.jsp"><span class="help-block">修改密码点击此处</span></a>
              <label>手机号（不允许修改）</label>
              <input type="text" value="<%=ub.getPhone() %>" readonly="readonly"></input>
              <label>员工号（不允许修改）</label>
              <input type="text" value="<%=ub.getWrkId() %>" readonly="readonly"></input>
              <label>姓名</label>
              <input type="text" name="niknam" value="<%=((null != ub.getNikNam())?ub.getNikNam():"") %>" placeholder="请输入姓名..." ></input>
              <label></label>
              <button type="reset" class="btn">取消</button>
              <button type="submit" class="btn">修改</button>
            </fieldset>
          </form>
        </div>
      </div><!--END个人信息展示-->
    </div><!--END主容器-->
    
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script type="text/javascript" src="res/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="res/js/jquery-ui"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="res/js/bootstrap.min.js"></script>
  </body>
</html>