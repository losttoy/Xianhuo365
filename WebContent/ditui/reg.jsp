<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  //提示注册失败信息，请客户重新注册
  String regMes = (String)session.getAttribute("regMes");
  if ((null != regMes) && "regFail".equals(regMes)) {
    session.removeAttribute("regMes");
%>
<script type="text/javascript">
    alert("注册没有成功，请检查后重试！");
</script>
<%
  }
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册 地推管理</title>
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
            <h1>注册<small>业绩系统</small>
            </h1>
          </div>
        </div>
      </div><!--标题-->
      
      <div class="row-fluid"><!--注册-->
        <div class="span12">
          <form action="reg.do" method="post" onsubmit="return validate_form(this)">
            <fieldset>
              <legend>注册信息</legend>
              <input type="text" name="mbl" placeholder="手机号..." />
              <label></label>
              <input type="text" name="id" placeholder="员工号（五位数字）..." />
              <label></label>
              <input type="text" name="name" placeholder="姓名..." />
              <label></label>
              <input type="password" name="pwd1" placeholder="输入密码..." />
              <label></label>
              <input type="password" name="pwd2" placeholder="再次输入密码..." />
              <label></label>
              <button type="reset" class="btn">取消</button>
              <button type="submit" class="btn">注册</button>
            </fieldset>
          </form>
        </div>
      </div><!--END注册-->
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
          var reg=/^\d+$/;
          if (!reg.test(id.value)) {
              alert("员工号应该是五位数字");
              id.focus();
              return false;
          }
          if (id.value.length != 5)
          {
        	alert("员工号应该是五位数字");
            id.focus();
            return false;
          }
          if (validate_required(mbl, "请输入手机号码!") == false)
          {
        	mbl.focus();
            return false;
          }
          
          if (validate_required(id, "请输入员工号!") == false)
          {
            id.focus();
            return false;
          }
          
          if (validate_required(name, "请输入姓名!") == false)
          {
            name.focus();
            return false;
          }
          
          if (validate_required(pwd1, "请输入密码1!") == false)
          {
            pwd1.focus();
            return false;
          }
          
          if (validate_required(pwd2, "请输入密码2!") == false)
          {
            pwd2.focus();
            return false;
          }
          if (pwd1.value != pwd2.value)
          {
            alert("输入的两个密码不一致！");
            pwd1.value = "";
            pwd2.value = "";
            pwd1.focus();
            return false;
          }
        }
      }
    </script>
  </body>
</html>