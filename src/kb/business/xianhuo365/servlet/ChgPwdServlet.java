/**
 * @Title        ChgPwdServlet.java
 * @Package      kb.business.xianhuo365.servlet
 * @Description  TODO(用一句话描述该文件做什么)
 *
 * @author       Will
 * @designer     (模块设计人)
 * @reviewer     (代码检视人)
 * @version      1.0,2016年6月8日
 *
 * @ReqPresenter 需求提交人:内部工具
 *
 * @UpdateHist   1.0,2016年6月8日 Will Created
 ****************
 *               1.1,2016年6月8日 Will Update
 *                          修改原因:
 *                          需求提交人:
 *                          代码检视人:
 ****************
 *
 * CopyRight 2016 LostToy. All rights reserved.
 */
package kb.business.xianhuo365.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kb.business.xianhuo365.bean.UserBean;
import kb.business.xianhuo365.util.UserUtil;

/**
 * @ClassName:   ChgPwdServlet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author       Will
 * @date         2016年6月8日 下午11:48:32
 */
public class ChgPwdServlet extends HttpServlet {

  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = 8403901683978001277L;

  /**
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
  
  /**
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    HttpSession session = req.getSession(true);
    session.removeAttribute("pwdchgMes");
    UserBean ub = (UserBean)session.getAttribute("userbean");
    ub.setPwd(req.getParameter("oldpwd"));
    if (UserUtil.chgPwd(ub, req.getParameter("newPwd1"))) {
      session.setAttribute("pwdchgMes", "chgSuc");
    } else {
      session.setAttribute("pwdchgMes", "chgErr");
    }
    RequestDispatcher dispatcher = req.getRequestDispatcher("chgPwd.jsp");
    dispatcher.forward(req,resp);
  }
  
  /**
   * 创建一个新的实例 ChgPwdServlet.
   *
   */
  public ChgPwdServlet() {
    // TODO Auto-generated constructor stub
  }

}
