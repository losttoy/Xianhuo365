/**
 * @Title        ProductUtil.java
 * @Package      kb.business.xianhuo365.util
 * @Description  TODO(用一句话描述该文件做什么)
 *
 * @author       Will
 * @designer     (模块设计人)
 * @reviewer     (代码检视人)
 * @version      1.0,2016年6月6日
 *
 * @ReqPresenter 需求提交人:天天鲜活
 *
 * @UpdateHist   1.0,2016年6月6日 Will Created
 ****************
 *               1.1,2016年6月11日 Will Update
 *                          修改原因:销售录入的时候记录活动信息表的成本价
 *                          需求提交人:天天鲜活
 *                          代码检视人:none
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

import kb.base.db.DataBaseUtil;
import kb.business.xianhuo365.bean.ProductBean;

/**
 * @ClassName:   ProductUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author       Will
 * @date         2016年6月6日 下午7:34:28
 */
public class ProductUtil {

  /**
   * @Title:       sale
   * @Description: 销售录入
   * @param        usrId员工ID
   * @param        prdId商品ID
   * @param        quantity销售量
   * @return       boolean录入是否成功
   * @throws
   */
  public static boolean sale(int usrId, int prdId, double quantity) {
    Connection conn = DataBaseUtil.getConnectionDS("sqlserver/default");
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    PreparedStatement pstmt4 = null;
    PreparedStatement pstmt5 = null;
    PreparedStatement pstmt6 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    try {
      //进事务控制
      conn.setAutoCommit(false);
      //1.INSERT人员商品销售表
      final String SELECT_SQL1 = "SELECT a.relptyid, a.relprdpriO, a.relprdpriI, "
          + "b.ptygrpid, c.usrniknam"
          + " FROM Ditui_ptyprd a"
          + " LEFT JOIN Ditui_party b ON a.relptyid = b.ptyid,"
          + " Ditui_user c"
          + " WHERE a.relprdid = ? AND c.usrid = ?";
      pstmt = conn.prepareStatement(SELECT_SQL1);
      pstmt.setInt(1, prdId);
      pstmt.setInt(2, usrId);
      rs = pstmt.executeQuery();
      if (!rs.next()) {
        //根据商品ID和员工ID查找不到对应的数据，事务回滚，返回失败
        conn.rollback();
        conn.setAutoCommit(true);
        return false;
      }
      //活动ID和销售单价、进货单价需要单独保存以备后用
      int ptyId = rs.getInt("relptyid");
      double price = rs.getDouble("relprdpriO");
      double priceIn = rs.getDouble("relprdpriI");
      
      final String INSERT_SQL1 = "INSERT INTO [Ditui_usrprd]"
          + " ([relusrid], [relusrnam], [relgrpid], [relptyid],"
          + " [relprdid], [relprdamt], [relprdpri], [relcrttim]) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE())";
      pstmt2 = conn.prepareStatement(INSERT_SQL1);
      pstmt2.setInt(1, usrId);
      pstmt2.setString(2, rs.getString("usrniknam"));
      pstmt2.setInt(3, rs.getInt("ptygrpid"));
      pstmt2.setInt(4, ptyId);
      pstmt2.setInt(5, prdId);
      pstmt2.setDouble(6, quantity);
      pstmt2.setDouble(7, price);
      if(0 == pstmt2.executeUpdate()) {
        //新增不成功，回滚事务，返回失败
        conn.rollback();
        conn.setAutoCommit(true);
        return false;
      }
      //1.INSERT人员商品销售表成功
      
      //2.UPDATE活动商品信息表
      //处理数据不一致，使用乐观锁
      //销售总数（斤） ++
      final String SELECT_SQL2 = "SELECT [relprdsal], [relmnttim]"
          + " FROM Ditui_ptyprd"
          + " WHERE relprdid = ?";
      pstmt3 = conn.prepareStatement(SELECT_SQL2);
      pstmt3.setInt(1, prdId);
      rs2 = pstmt3.executeQuery();
      if (!rs2.next()) {
        //根据商品ID查找活动商品信息表失败，事务回滚，返回失败
        conn.rollback();
        conn.setAutoCommit(true);
        return false;
      }
      final String UPDATE_SQL2 = "UPDATE Ditui_ptyprd"
          + " SET [relprdsal] = ?, [relmnttim] = GETDATE()"
          + " WHERE [relprdid] = ? AND [relmnttim] = ?";
      pstmt4 = conn.prepareStatement(UPDATE_SQL2);
      pstmt4.setDouble(1, rs2.getDouble("relprdsal") + quantity);
      pstmt4.setInt(2, prdId);
      pstmt4.setTimestamp(3, rs2.getTimestamp("relmnttim"));
      if (0 == pstmt4.executeUpdate()) {
        //更新活动商品信息表失败，事务回滚，返回失败
        conn.rollback();
        conn.setAutoCommit(true);
        return false;
      }
      //2.UPDATE活动商品信息表成功
      
      //3.UPDATE活动信息表
      //处理数据不一致，使用乐观锁
      //活动销售总额  ++
      final String SELECT_SQL3 = "SELECT [ptysalamt], [ptysalamtIn], [ptymnttim]"
          + " FROM [Ditui_party]"
          + " WHERE [ptyid] = ?";
      pstmt5 = conn.prepareStatement(SELECT_SQL3);
      pstmt5.setInt(1, ptyId);
      rs3 = pstmt5.executeQuery();
      if (!rs3.next()) {
        //根据活动ID查找活动信息表失败，事务回滚，返回失败
        conn.rollback();
        conn.setAutoCommit(true);
        return false;
      }
      final String UPDATE_SQL3 = "UPDATE [Ditui_party]"
          + " SET [ptysalamt] = ?, [ptysalamtIn] = ?, [ptymnttim] = GETDATE()"
          + " WHERE [ptyid] = ? AND [ptymnttim] = ?";
      pstmt6 = conn.prepareStatement(UPDATE_SQL3);
      pstmt6.setDouble(1, rs3.getDouble("ptysalamt") + quantity * price);
      pstmt6.setDouble(2, rs3.getDouble("ptysalamtIn") + quantity * priceIn);
      pstmt6.setInt(3, ptyId);
      pstmt6.setTimestamp(4, rs3.getTimestamp("ptymnttim"));
      if (0 == pstmt6.executeUpdate()) {
        //更新活动信息表失败，事务回滚，返回失败
        conn.rollback();
        conn.setAutoCommit(true);
        return false;
      }
      //3.UPDATE活动信息表成功
      
      //成功执行则提交事务
      conn.commit();
      conn.setAutoCommit(true);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      try {
        //出现异常的情况下回滚事务
        conn.rollback();
        conn.setAutoCommit(true);
      } catch (SQLException e1) {
        e1.printStackTrace();
        return false;
      }
      return false;
    } finally {
      DataBaseUtil.closeResultSet(rs);
      rs = null;
      DataBaseUtil.closeResultSet(rs2);
      rs2 = null;
      DataBaseUtil.closeResultSet(rs3);
      rs3 = null;
      DataBaseUtil.closePreparedStatement(pstmt);
      pstmt = null;
      DataBaseUtil.closePreparedStatement(pstmt2);
      pstmt2 = null;
      DataBaseUtil.closePreparedStatement(pstmt3);
      pstmt3 = null;
      DataBaseUtil.closePreparedStatement(pstmt4);
      pstmt4 = null;
      DataBaseUtil.closePreparedStatement(pstmt5);
      pstmt5 = null;
      DataBaseUtil.closePreparedStatement(pstmt6);
      pstmt6 = null;
      DataBaseUtil.closeConnection(conn);
      conn = null;
    }
  }
  
  /**
   * @Title:       getProList
   * @Description: 根据活动ID返回所有商品的信息（列表）
   * @param        ptyId
   * @return       ArrayList<ProductBean>
   * @throws
   */
  public static ArrayList<ProductBean> getProList(int ptyId) {
    Connection conn = DataBaseUtil.getConnectionDS("sqlserver/default");
    final String SELECT_SQL = "SELECT [relprdid],"
        + " [relprdnam], [relprdpriI], [relprdpriO]"
        + " FROM Ditui_ptyprd WHERE relptyid = ?";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = conn.prepareStatement(SELECT_SQL);
      pstmt.setInt(1, ptyId);
      rs = pstmt.executeQuery();
      ArrayList<ProductBean> list = new ArrayList<ProductBean>();
      while (rs.next()) {
        ProductBean bean = new ProductBean();
        bean.setId(rs.getInt("relprdid"));
        bean.setNam(rs.getString("relprdnam"));
        bean.setPriceIn(rs.getDouble("relprdpriI"));
        bean.setPriceOut(rs.getDouble("relprdpriO"));
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
   * 创建一个新的实例 ProductUtil.
   *
   */
  public ProductUtil() {
    // TODO Auto-generated constructor stub
  }

}
