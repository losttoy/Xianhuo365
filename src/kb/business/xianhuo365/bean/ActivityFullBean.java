/**
 * @Title        ActivityFullBean.java
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

/**
 * @ClassName:   ActivityFullBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author       Will
 * @date         2016年6月5日 下午6:50:23
 */
public class ActivityFullBean {

  private int groupId;
  private String groupNam;
  private String ownNam;
  private int ptyId;
  private String ptyAdr;
  private String ptyDte;
  private double sales;
  private double fee1;
  private double fee2;
  private double fee3;
  private double fee4;
  /**
   * @return groupId
   */
  public int getGroupId() {
    return groupId;
  }
  /**
   * @param groupId the groupId to set
   */
  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }
  /**
   * @return groupNam
   */
  public String getGroupNam() {
    return groupNam;
  }
  /**
   * @param groupNam the groupNam to set
   */
  public void setGroupNam(String groupNam) {
    this.groupNam = groupNam;
  }
  /**
   * @return ownNam
   */
  public String getOwnNam() {
    return ownNam;
  }
  /**
   * @param ownNam the ownNam to set
   */
  public void setOwnNam(String ownNam) {
    this.ownNam = ownNam;
  }
  /**
   * @return ptyId
   */
  public int getPtyId() {
    return ptyId;
  }
  /**
   * @param ptyId the ptyId to set
   */
  public void setPtyId(int ptyId) {
    this.ptyId = ptyId;
  }
  /**
   * @return ptyAdr
   */
  public String getPtyAdr() {
    return ptyAdr;
  }
  /**
   * @param ptyAdr the ptyAdr to set
   */
  public void setPtyAdr(String ptyAdr) {
    this.ptyAdr = ptyAdr;
  }
  /**
   * @return ptyDte
   */
  public String getPtyDte() {
    return ptyDte;
  }
  /**
   * @param ptyDte the ptyDte to set
   */
  public void setPtyDte(String ptyDte) {
    this.ptyDte = ptyDte;
  }
  /**
   * @return sales
   */
  public double getSales() {
    return sales;
  }
  /**
   * @param sales the sales to set
   */
  public void setSales(double sales) {
    this.sales = sales;
  }
  /**
   * @return fee1
   */
  public double getFee1() {
    return fee1;
  }
  /**
   * @param fee1 the fee1 to set
   */
  public void setFee1(double fee1) {
    this.fee1 = fee1;
  }
  /**
   * @return fee2
   */
  public double getFee2() {
    return fee2;
  }
  /**
   * @param fee2 the fee2 to set
   */
  public void setFee2(double fee2) {
    this.fee2 = fee2;
  }
  /**
   * @return fee3
   */
  public double getFee3() {
    return fee3;
  }
  /**
   * @param fee3 the fee3 to set
   */
  public void setFee3(double fee3) {
    this.fee3 = fee3;
  }
  /**
   * @return fee4
   */
  public double getFee4() {
    return fee4;
  }
  /**
   * @param fee4 the fee4 to set
   */
  public void setFee4(double fee4) {
    this.fee4 = fee4;
  }
  /**
   * @return
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ActivityFullBean [groupId=" + groupId + ", groupNam=" + groupNam
        + ", ownNam=" + ownNam + ", ptyId=" + ptyId + ", ptyAdr=" + ptyAdr
        + ", ptyDte=" + ptyDte + ", sales=" + sales + ", fee1=" + fee1
        + ", fee2=" + fee2 + ", fee3=" + fee3 + ", fee4=" + fee4 + "]";
  }
  
}
