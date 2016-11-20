/**
 * @Title        UserUtil.java
 * @Package      kb.business.xianhuo365.util
 * @Description  TODO(用一句话描述该文件做什么)
 *
 * @author       Will
 * @designer     (模块设计人)
 * @reviewer     (代码检视人)
 * @version      1.0,2016年6月5日
 *
 * @ReqPresenter 需求提交人:天天鲜活
 *
 * @UpdateHist   1.0,2016年6月5日 Will Created
 ****************
 *               1.1,2016年6月14日 Will Update
 *                          修改原因:计算密码使用UTF-8编码
 *                          需求提交人:天天鲜活
 *                          代码检视人:none
 ****************
 *
 * CopyRight 2016 LostToy. All rights reserved.
 */
package kb.business.xianhuo365.util;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kb.base.crypto.DigestUtils;
import kb.base.db.DataBaseUtil;
import kb.business.xianhuo365.bean.UserBean;

/**
 * @ClassName:   UserUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author       Will
 * @date         2016年6月5日 下午2:59:34
 */
public class UserUtil {

  /**
   * 创建一个新的实例 UserUtil.
   *
   */
  public UserUtil() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @Title:       chgName
   * @Description: 目前个人信息维护只修改姓名
   * @param        ub含员工ID
   * @param        newNam
   * @return       boolean修改结果
   * @throws
   */
  public static boolean chgName(UserBean ub, String newNam) {
    if (null == ub) {
      return false;
    }
    Connection conn = DataBaseUtil.getConnectionDS("sqlserver/default");
    final String UPDATE_SQL = "UPDATE Ditui_user "
        + "SET [usrniknam] = ?, [usrmnttim] = GETDATE()"
        + "WHERE [usrid] = ?";
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(UPDATE_SQL);
      pstmt.setString(1, newNam);
      pstmt.setInt(2, ub.getId());
      int result = pstmt.executeUpdate();
      if (1 == result) {
        return true;
      }
      return false;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      DataBaseUtil.closePreparedStatement(pstmt);
      pstmt = null;
      DataBaseUtil.closeConnection(conn);
      conn = null;
    }
  }
  
  /**
   * @Title:       chgPwd
   * @Description: 修改密码
   * @param        ub含员工ID、手机号和明文密码（旧）
   * @param        newPwd明文密码（新）
   * @return       boolean密码修改结果
   * @throws
   */
  public static boolean chgPwd(UserBean ub, String newPwd) {
    if (null == ub) {
      return false;
    }
    Connection conn = DataBaseUtil.getConnectionDS("sqlserver/default");
    final String SELECT_SQL = "SELECT "
        + "[usrpwdsha]"
        + "FROM Ditui_user WHERE [usrid] = ?";
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;
    try {
      //校验旧密码
      pstmt = conn.prepareStatement(SELECT_SQL);
      pstmt.setInt(1, ub.getId());
      rs = pstmt.executeQuery();
      if (rs.next()) {
        if (!DigestUtils.encodeSHAHex((ub.getPhone() + ub.getPwd()).getBytes())
            .equals(rs.getString("usrpwdsha"))) {
          return false;
        }
      } else {
        return false;
      }
      
      //改密
      final String UPDATE_SQL = "UPDATE Ditui_user "
          + "SET [usrpwdsha] = ?, [usrmnttim] = GETDATE()"
          + "WHERE [usrid] = ?";
      pstmt2 = conn.prepareStatement(UPDATE_SQL);
      pstmt2.setString(1, DigestUtils.encodeSHAHex((ub.getPhone() + newPwd).getBytes()));
      pstmt2.setInt(2, ub.getId());
      int result = pstmt2.executeUpdate();
      if (1 == result) {
        return true;
      }
      return false;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      DataBaseUtil.closeResultSet(rs);
      rs = null;
      DataBaseUtil.closePreparedStatement(pstmt);
      pstmt = null;
      DataBaseUtil.closePreparedStatement(pstmt2);
      pstmt2 = null;
      DataBaseUtil.closeConnection(conn);
      conn = null;
    }
  }
  
  /**
   * @Title:       login
   * @Description: 登录
   * @param        ub含手机号和明文密码
   * @return       UserBean 登录成功回送员工明细，登录失败返回null
   * @throws
   */
  public static UserBean login(UserBean ub) {
    if (null == ub) {
      return null;
    }
    Connection conn = DataBaseUtil.getConnectionDS("sqlserver/default");
    final String SELECT_SQL = "SELECT "
        + "[usrid], [usrphone], [usrwrkid], [usrniknam], [usrpwdsha], [usrsta], [usrmnttim] "
        + "FROM Ditui_user WHERE [usrphone] = ?";

    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = conn.prepareStatement(SELECT_SQL);
      pstmt.setString(1, ub.getPhone());
      rs = pstmt.executeQuery();
      if (rs.next()) {
        //密码校验
        if (!DigestUtils.encodeSHAHex((ub.getPhone() + ub.getPwd()).getBytes())
            .equals(rs.getString("usrpwdsha"))) {
          return null;
        }
        UserBean u = new UserBean();
        u.setId(rs.getInt("usrid"));
        u.setPhone(rs.getString("usrphone"));
        u.setWrkId(rs.getString("usrwrkid"));
        u.setNikNam(rs.getString("usrniknam"));
        u.setUsrSta(rs.getString("usrsta"));
        u.setMnttim(rs.getTimestamp("usrmnttim"));
        return u;
      }
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      DataBaseUtil.closeResultSet(rs);
      rs = null;
      DataBaseUtil.closePreparedStatement(pstmt);
      pstmt = null;
      DataBaseUtil.closeConnection(conn);
      conn = null;
    }
  }
  
  /**
   * @Title:       reg
   * @Description: 员工注册
   * @param        ub
   * @return       boolean 注册结果
   * @throws
   */
  public static boolean reg(UserBean ub) {
    if (null == ub) {
      return false;
    }
    Connection conn = DataBaseUtil.getConnectionDS("sqlserver/default");
    final String INSERT_SQL = "INSERT INTO [Ditui_user]"
        + " ([usrphone], [usrwrkid], [usrniknam], [usrpwdsha], [usrsta], [usrtype], [usrregtim], [usrmnttim])"
        + " VALUES (?, ?, ?, ?, 'A', '0', GETDATE(), GETDATE())";
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(INSERT_SQL);
      pstmt.setString(1, ub.getPhone());
      pstmt.setString(2, ub.getWrkId());
      pstmt.setString(3, ub.getNikNam());
      pstmt.setString(4, DigestUtils.encodeSHAHex((ub.getPhone() + ub.getPwd()).getBytes("UTF-8")));
      int result = pstmt.executeUpdate();
      if (1 == result) {
        return true;
      }
      return false;
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return false;
    } finally {
      DataBaseUtil.closePreparedStatement(pstmt);
      pstmt = null;
      DataBaseUtil.closeConnection(conn);
      conn = null;
    }
  }
  
  public static void main(String[] args) {
  }
}
