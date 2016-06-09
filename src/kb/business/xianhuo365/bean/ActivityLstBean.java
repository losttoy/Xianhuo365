/**
 * @Title        ActivityLstBean.java
 * @Package      kb.business.xianhuo365.bean
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
package kb.business.xianhuo365.bean;

import java.util.ArrayList;

/**
 * @ClassName:   ActivityLstBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author       Will
 * @date         2016年6月5日 下午11:20:03
 */
public class ActivityLstBean {

  private int num;
  private ArrayList<ActivityFullBean> list;

  /**
   * 创建一个新的实例 ActivityLstBean.
   *
   */
  public ActivityLstBean() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @return num
   */
  public int getNum() {
    return num;
  }

  /**
   * @param num the num to set
   */
  public void setNum(int num) {
    this.num = num;
  }

  /**
   * @return list
   */
  public ArrayList<ActivityFullBean> getList() {
    return list;
  }

  /**
   * @param list the list to set
   */
  public void setList(ArrayList<ActivityFullBean> list) {
    this.list = list;
  }

  /**
   * @return
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ActivityLstBean [num=" + num + ", list=" + list + "]";
  }

}
