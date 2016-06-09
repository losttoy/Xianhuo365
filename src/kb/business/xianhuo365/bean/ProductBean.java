/**
 * @Title        ProductBean.java
 * @Package      kb.business.xianhuo365.bean
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
 *               1.1,2016年6月6日 Will Update
 *                          修改原因:
 *                          需求提交人:
 *                          代码检视人:
 ****************
 *
 * CopyRight 2016 LostToy. All rights reserved.
 */
package kb.business.xianhuo365.bean;

/**
 * @ClassName:   ProductBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author       Will
 * @date         2016年6月6日 下午7:30:49
 */
public class ProductBean {

  private int id;
  private String nam;
  private double priceIn;
  private double priceOut;
  
  /**
   * 创建一个新的实例 ProductBean.
   *
   */
  public ProductBean() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return nam
   */
  public String getNam() {
    return nam;
  }

  /**
   * @param nam the nam to set
   */
  public void setNam(String nam) {
    this.nam = nam;
  }

  /**
   * @return priceIn
   */
  public double getPriceIn() {
    return priceIn;
  }

  /**
   * @param priceIn the priceIn to set
   */
  public void setPriceIn(double priceIn) {
    this.priceIn = priceIn;
  }

  /**
   * @return priceOut
   */
  public double getPriceOut() {
    return priceOut;
  }

  /**
   * @param priceOut the priceOut to set
   */
  public void setPriceOut(double priceOut) {
    this.priceOut = priceOut;
  }

  /**
   * @return
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ProductBean [id=" + id + ", nam=" + nam + ", priceIn=" + priceIn
        + ", priceOut=" + priceOut + "]";
  }

}
