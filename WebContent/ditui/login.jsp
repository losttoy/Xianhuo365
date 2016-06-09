<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  //提示注册成功信息，请客户登录
  String regMes = (String)session.getAttribute("regMes");
  if ((null != regMes) && "regSuc".equals(regMes)) {
    session.removeAttribute("regMes");
%>
<script type="text/javascript">
    alert("注册成功，请登录！");
</script>
<%
  }
  
  //提示登录失败信息，请客户重新登录
  String loginMes = (String)session.getAttribute("loginMes");
  if ((null != loginMes) && "loginErr".equals(loginMes)) {
    session.removeAttribute("loginMes");
%>
<script type="text/javascript">
    alert("登录不成功，请检查手机号、密码重试！");
</script>
<%
  }
  
  //提示用户状态不正确
  if ((null != loginMes) && "loginNotValid".equals(loginMes)) {
    session.removeAttribute("loginMes");
%>
<script type="text/javascript">
    alert("用户已被锁定，请联系管理员解锁后登录！");
</script>
<%
  }
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录 地推管理</title>
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
      <div class="row-fluid"><!--标题-->
        <div class="span12">
          <div class="page-header">
            <h1>登录<small>地推管理</small>
            </h1>
          </div>
        </div>
      </div><!--END标题-->

      <div class="row-fluid"><!--登录-->
        <div class="span12">
          <form action="login.do" method="post" onsubmit="return validate_form(this)">
            <fieldset>
              <legend>登录信息</legend>
              <input type="text" name="mbl" placeholder="手机号..." />
              <label></label>
              <input type="password" name="pwd" placeholder="密码..." />
              <a href="reg.jsp"><span class="help-block">没有用户？点击注册</span></a>
              <button type="reset" class="btn">取消</button>
              <button type="submit" class="btn">登录</button>
            </fieldset>
          </form>
        </div>
      </div><!--END登录-->
    </div><!--END主容器-->
    
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script type="text/javascript" src="res/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="res/js/jquery-ui"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="res/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
      function validate_required(field, alerttxt)
      {
        with (field)
        {
          if (value == null || value == "")
          {
            alert(alerttxt);
            return false;
          }
          else
          {
            return true;
          }
        }
      }   
  
      function validate_form(thisform)
      {
        with (thisform)
        {
          if (validate_required(mbl, "请输入手机号码!") == false)
          {
            mbl.focus();
            return false;
          }
          
          if (validate_required(pwd, "请输入密码!") == false)
          {
            pwd.focus();
            return false;
          }
        }
      }
    </script>
  </body>
</html>