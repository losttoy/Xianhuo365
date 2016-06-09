/**
 * @Title        ActivityFullUtil.java
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
 *               1.1,2016年6月5日 Will Update
 *                          修改原因:
 *                          需求提交人:
 *                          代码检视人:
 ****************
 *
 * CopyRight 2016 LostToy. All rights reserved.
 */
package kb.business.xianhuo365.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kb.base.dateformat.DateFormatUtil;
import kb.base.db.DataBaseUtil;
import kb.business.xianhuo365.bean.ActivityFullBean;
import kb.business.xianhuo365.bean.ActivityLstBean;

/**
 * @ClassName:   ActivityFullUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author       Will
 * @date         2016年6月5日 下午7:00:56
 */
public class ActivityFullUtil {

  /**
   * 创建一个新的实例 ActivityFullUtil.
   *
   */
  public ActivityFullUtil() {
    // TODO Auto-generated constructor stub
  }
  
  /**
   * @Title:       getActFul2
   * @Description: 根据活动ID查找返回活动明细
   * @param        ptyId活动ID
   * @return       ActivityFullBean
   * @throws
   */
  public static ActivityFullBean getActFul2(int ptyId) {
    Connection conn = DataBaseUtil.getConnectionDS("sqlserver/default");
    final String SELECT_SQL = "SELECT a.ptygrpid, b.grpnam, c.refusrnam, "
        + "a.ptyid, a.ptyadr, a.ptydte, a.ptysalamt, "
        + "a.ptyfee1, a.ptyfee2, a.ptyfee3, a.ptyfee4 "
        + "FROM Ditui_party a "
        + "LEFT JOIN Ditui_group b ON a.ptygrpid = b.grpid "
        + "RIGHT JOIN Ditui_grpusrrel c ON a.ptygrpid = c.relgrpid "
        + "WHERE a.ptyid = ? AND a.ptydte = ? AND c.refOwn = 'Y';";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = conn.prepareStatement(SELECT_SQL);
      pstmt.setInt(1, ptyId);
      pstmt.setString(2, DateFormatUtil.getYYYYMMdd());
      rs = pstmt.executeQuery();
      if (rs.next()) {
        ActivityFullBean bean = new ActivityFullBean();
        bean.setGroupId(rs.getInt("ptygrpid"));
        bean.setGroupNam(rs.getString("grpnam"));
        bean.setOwnNam(rs.getString("refusrnam"));
        bean.setPtyId(rs.getInt("ptyid"));
        bean.setPtyAdr(rs.getString("ptyadr"));
        bean.setPtyDte(rs.getString("ptydte"));
        bean.setSales(rs.getDouble("ptysalamt"));
        bean.setFee1(rs.getDouble("ptyfee1"));
        bean.setFee2(rs.getDouble("ptyfee2"));
        bean.setFee3(rs.getDouble("ptyfee3"));
        bean.setFee4(rs.getDouble("ptyfee4"));
        return bean;
      } else {
        return null;
      }
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
   * @Title:       getActFul1
   * @Description: 根据员工ID查找返回活动明细
   * @param        usrId员工ID
   * @return       ArrayList<ActivityFullBean>
   * @throws
   */
  public static ArrayList<ActivityFullBean> getActFul1(int usrId) {
    Connection conn = DataBaseUtil.getConnectionDS("sqlserver/default");
    final String SELECT_SQL = "SELECT a.relgrpid, b.grpnam, d.refusrnam, "
        + "c.ptyid, c.ptyadr, c.ptydte, c.ptysalamt, "
        + "c.ptyfee1, c.ptyfee2, c.ptyfee3, c.ptyfee4 "
        + "FROM(Ditui_grpusrrel a "
        + "LEFT JOIN Ditui_group b ON a.relgrpid = b.grpid "
        + "LEFT JOIN Ditui_party c ON a.relgrpid = c.ptygrpid "
        + "RIGHT JOIN Ditui_grpusrrel d ON a.relgrpid = d.relgrpid) "
        + "WHERE a.relusrid = ? AND c.ptydte = ? AND d.refOwn = 'Y';";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = conn.prepareStatement(SELECT_SQL);
      pstmt.setInt(1, usrId);
      pstmt.setString(2, DateFormatUtil.getYYYYMMdd());
      rs = pstmt.executeQuery();
      ArrayList<ActivityFullBean> list = new ArrayList<ActivityFullBean>();
      while (rs.next()) {
        ActivityFullBean bean = new ActivityFullBean();
        bean.setGroupId(rs.getInt("relgrpid"));
        bean.setGroupNam(rs.getString("grpnam"));
        bean.setOwnNam(rs.getString("refusrnam"));
        bean.setPtyId(rs.getInt("ptyid"));
        bean.setPtyAdr(rs.getString("ptyadr"));
        bean.setPtyDte(rs.getString("ptydte"));
        bean.setSales(rs.getDouble("ptysalamt"));
        bean.setFee1(rs.getDouble("ptyfee1"));
        bean.setFee2(rs.getDouble("ptyfee2"));
        bean.setFee3(rs.getDouble("ptyfee3"));
        bean.setFee4(rs.getDouble("ptyfee4"));
        list.add(bean);
      }
      return list;
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
   * @Title:       getActFulLst
   * @Description: 返回页数 + 活动List（仅活动ID）
   * @param        page页数 从1开始
   * @param        num 每页记录数
   * @return       ActivityLstBean
   * @throws
   */
  public static ActivityLstBean getActFulLst(int page, int num) {
    ActivityLstBean aLbean = new ActivityLstBean();
    Connection conn = DataBaseUtil.getConnectionDS("sqlserver/default");
    final String SELECT_SQL = "SELECT COUNT(ptyid) FROM (Ditui_party a "
        + "INNER JOIN Ditui_group b ON a.ptygrpid = b.grpid) "
        + "WHERE a.ptydte = ? AND b.grpshwflg = 'Y';";
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    try {
      pstmt = conn.prepareStatement(SELECT_SQL);
      pstmt.setString(1, DateFormatUtil.getYYYYMMdd());
      rs = pstmt.executeQuery();
      if (rs.next()) {
        int total = rs.getInt(1);
        //计算总页数
        if (0 == total) {
          aLbean.setNum(1);
        } else {
          aLbean.setNum((total - 1) / num + 1);
        }
      }
      final String SELECT_SQL2 = "SELECT TOP " + num +  " ptyid FROM (Ditui_party a "
          + "INNER JOIN Ditui_group b ON a.ptygrpid = b.grpid) "
          + "WHERE ((ptyid NOT IN(SELECT TOP " + ((page - 1) * num) + " ptyid FROM Ditui_party ORDER BY ptyid))"
          + " AND (ptydte = ?)"
          + " AND b.grpshwflg = 'Y')"
          + " ORDER BY ptyid;";
      pstmt2 = conn.prepareStatement(SELECT_SQL2);
      pstmt2.setString(1, DateFormatUtil.getYYYYMMdd());
      rs2 = pstmt2.executeQuery();
      ArrayList<ActivityFullBean> list = new ArrayList<ActivityFullBean>();
      while (rs2.next()) {
        ActivityFullBean bean = new ActivityFullBean();
        bean.setPtyId(rs2.getInt("ptyid"));
        list.add(bean);
      }
      aLbean.setList(list);
      return aLbean;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      DataBaseUtil.closeResultSet(rs);
      rs = null;
      DataBaseUtil.closeResultSet(rs2);
      rs2 = null;
      DataBaseUtil.closePreparedStatement(pstmt);
      pstmt = null;
      DataBaseUtil.closePreparedStatement(pstmt2);
      pstmt2 = null;
      DataBaseUtil.closeConnection(conn);
      conn = null;
    }
  }
  
  public static void main(String[] args) {
    System.out.println(ActivityFullUtil.getActFul2(5));
  }
}
