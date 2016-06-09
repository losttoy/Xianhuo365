/**
 * @Title        DateFormatUtil.java
 * @Package      kb.base.dateformat
 * @Description  日期格式类
 *
 * @author       Will
 * @designer     (模块设计人)
 * @reviewer     (代码检视人)
 * @version      1.0,2016年6月5日
 *
 * @ReqPresenter 需求提交人:内部工具
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
package kb.base.dateformat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:   DateFormatUtil
 * @Description: 日期格式类
 * @author       Will
 * @date         2016年6月5日 下午8:46:44
 */
public class DateFormatUtil {

  public static String getYYYYMMdd() {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    return format.format(new Date());
  }

  /**
   * 创建一个新的实例 DateFormatUtil.
   *
   */
  public DateFormatUtil() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @Title:       main
   * @Description: TODO(这里用一句话描述这个方法的作用)
   * @param args
   * @return       void
   * @throws
   */
  public static void main(String[] args) {
  }

}
