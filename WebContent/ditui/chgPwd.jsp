<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kb.business.xianhuo365.bean.UserBean"%>
<!DOCTYPE html>
<%
  //提示客户密码修改结果
  String pwdchgMes = (String)session.getAttribute("pwdchgMes");

  //成功
  if ((null != pwdchgMes) && "chgSuc".equals(pwdchgMes)) {
    session.removeAttribute("pwdchgMes");
%>
<script type="text/javascript">
    alert("密码修改成功！");
</script>
<%
  }

  //失败
  if ((null != pwdchgMes) && "chgErr".equals(pwdchgMes)) {
    session.removeAttribute("pwdchgMes");
%>
<script type="text/javascript">
    alert("密码修改不成功，请重试！");
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
    <title>修改密码 业绩系统</title>
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
            <button class="btn">修改密码</button>
            <button data-toggle="dropdown" class="btn dropdown-toggle">
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
              <li>
                <a href="party.jsp">今日活动</a></li>
              <li>
                <a href="doing.jsp?index=1">所有地推组</a></li>
              <li class="divider"></li>
              <li>
                <a href="quit.do">注销</a></li>
              <li class="divider"></li>
              <li>
                <a href="nav.jsp">回到首页</a></li>
            </ul>
          </div>
          <div class="page-header"></div>
        </div>
      </div><!--END导航按钮-->
      
      <div class="row-fluid"><!--密码修改-->
        <div class="span12">
          <form action="chgpwd.do" method="post" onsubmit="return validate_form(this)">
            <fieldset>
              <legend>修改密码</legend>
              <label>手机号</label>
              <input type="text" value="<%=ub.getPhone() %>" readonly="readonly"></input>
              <label>员工号</label>
              <input type="text" value="<%=ub.getWrkId() %>" readonly="readonly"></input>
              <label></label>
              <input type="password" name="oldpwd" placeholder="输入旧密码..." ></input>
              <label></label>
              <input type="password" name="newPwd1" placeholder="输入新密码..." ></input>
              <label></label>
              <input type="password" name="newPwd2" placeholder="重复一遍新密码..." ></input>
              <label></label>
              <button type="reset" class="btn">取消</button>
              <button type="submit" class="btn">修改</button>
            </fieldset>
          </form>
        </div>
      </div><!--END密码修改-->
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
          if (validate_required(oldpwd, "请输入旧密码!") == false)
          {
        	oldpwd.focus();
            return false;
          }
          
          if (validate_required(newPwd1, "请输入新密码1!") == false)
          {
        	newPwd1.focus();
            return false;
          }
          
          if (validate_required(newPwd2, "请输入新密码2!") == false)
          {
            newPwd2.focus();
            return false;
          }
          
          if (newPwd1.value != newPwd2.value)
          {
            alert("输入的两个密码不一致！");
            newPwd1.value = "";
            newPwd2.value = "";
            newPwd1.focus();
            return false;
          }
          
          if (oldpwd.value == newPwd1.value)
          {
            alert("旧密码和新密码不能一样！");
            oldpwd.value = "";
            newPwd1.value = "";
            newPwd2.value = "";
            oldpwd.focus();
            return false;
          }
        }
      }
    </script>
  </body>
</html>