/**
 * @Title        CalPwdSHA1Servlet.java
 * @Package      kb.business.xianhuo365.servlet
 * @Description  TODO(用一句话描述该文件做什么)
 *
 * @author       Will
 * @designer     (模块设计人)
 * @reviewer     (代码检视人)
 * @version      1.0,2016年6月9日
 *
 * @ReqPresenter 需求提交人:内部工具
 *
 * @UpdateHist   1.0,2016年6月9日 Will Created
 ****************
 *               1.1,2016年6月9日 Will Update
 *                          修改原因:计算密码使用UTF-8编码
 *                          需求提交人:天天鲜活
 *                          代码检视人:none
 ****************
 *
 * CopyRight 2016 LostToy. All rights reserved.
 */
package kb.business.xianhuo365.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kb.base.crypto.DigestUtils;

/**
 * @ClassName:   CalPwdSHA1Servlet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author       Will
 * @date         2016年6月9日 下午3:47:55
 */
public class CalPwdSHA1Servlet extends HttpServlet {

  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -4573543551704879445L;

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
    // TODO Auto-generated method stub
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
    String phone = req.getParameter("phone");
    String pwd = req.getParameter("pwd");
    resp.getWriter().print(DigestUtils.encodeSHAHex((phone + pwd).getBytes("UTF-8")));
  }

  /**
   * 创建一个新的实例 CalPwdSHA1Servlet.
   *
   */
  public CalPwdSHA1Servlet() {
    // TODO Auto-generated constructor stub
  }

}
