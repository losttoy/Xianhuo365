/**
 * @Title        UserBean.java
 * @Package      kb.business.xianhuo365
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

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName:   UserBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author       Will
 * @date         2016年6月5日 下午2:08:36
 */
public class UserBean {

	private int id;
	private String phone;
	private String wrkId;
	private String nikNam;
	private String pwd;
	private String pwdSha;
	private int pwdEct;
	private String usrSta;
	private String usrAdd;
	private double usrbal;
	private double usrPot;
	private int lvl;
	private Date regtim;
	private String regIp;
	private Timestamp mnttim;
	private String mntIp;
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
   * @return phone
   */
  public String getPhone() {
    return phone;
  }
  /**
   * @param phone the phone to set
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }
  /**
   * @return wrkId
   */
  public String getWrkId() {
    return wrkId;
  }
  /**
   * @param wrkId the wrkId to set
   */
  public void setWrkId(String wrkId) {
    this.wrkId = wrkId;
  }
  /**
   * @return nikNam
   */
  public String getNikNam() {
    return nikNam;
  }
  /**
   * @param nikNam the nikNam to set
   */
  public void setNikNam(String nikNam) {
    this.nikNam = nikNam;
  }
  /**
   * @return pwd
   */
  public String getPwd() {
    return pwd;
  }
  /**
   * @param pwd the pwd to set
   */
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  /**
   * @return pwdSha
   */
  public String getPwdSha() {
    return pwdSha;
  }
  /**
   * @param pwdSha the pwdSha to set
   */
  public void setPwdSha(String pwdSha) {
    this.pwdSha = pwdSha;
  }
  /**
   * @return pwdEct
   */
  public int getPwdEct() {
    return pwdEct;
  }
  /**
   * @param pwdEct the pwdEct to set
   */
  public void setPwdEct(int pwdEct) {
    this.pwdEct = pwdEct;
  }
  /**
   * @return usrSta
   */
  public String getUsrSta() {
    return usrSta;
  }
  /**
   * @param usrSta the usrSta to set
   */
  public void setUsrSta(String usrSta) {
    this.usrSta = usrSta;
  }
  /**
   * @return usrAdd
   */
  public String getUsrAdd() {
    return usrAdd;
  }
  /**
   * @param usrAdd the usrAdd to set
   */
  public void setUsrAdd(String usrAdd) {
    this.usrAdd = usrAdd;
  }
  /**
   * @return usrbal
   */
  public double getUsrbal() {
    return usrbal;
  }
  /**
   * @param usrbal the usrbal to set
   */
  public void setUsrbal(double usrbal) {
    this.usrbal = usrbal;
  }
  /**
   * @return usrPot
   */
  public double getUsrPot() {
    return usrPot;
  }
  /**
   * @param usrPot the usrPot to set
   */
  public void setUsrPot(double usrPot) {
    this.usrPot = usrPot;
  }
  /**
   * @return lvl
   */
  public int getLvl() {
    return lvl;
  }
  /**
   * @param lvl the lvl to set
   */
  public void setLvl(int lvl) {
    this.lvl = lvl;
  }
  /**
   * @return regtim
   */
  public Date getRegtim() {
    return regtim;
  }
  /**
   * @param regtim the regtim to set
   */
  public void setRegtim(Date regtim) {
    this.regtim = regtim;
  }
  /**
   * @return regIp
   */
  public String getRegIp() {
    return regIp;
  }
  /**
   * @param regIp the regIp to set
   */
  public void setRegIp(String regIp) {
    this.regIp = regIp;
  }
  /**
   * @return mnttim
   */
  public Timestamp getMnttim() {
    return mnttim;
  }
  /**
   * @param mnttim the mnttim to set
   */
  public void setMnttim(Timestamp mnttim) {
    this.mnttim = mnttim;
  }
  /**
   * @return mntIp
   */
  public String getMntIp() {
    return mntIp;
  }
  /**
   * @param mntIp the mntIp to set
   */
  public void setMntIp(String mntIp) {
    this.mntIp = mntIp;
  }
  
  /**
   * @return
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "UserBean [id=" + id + ", phone=" + phone + ", wrkId=" + wrkId
        + ", nikNam=" + nikNam + ", pwd=" + pwd + ", pwdSha=" + pwdSha
        + ", pwdEct=" + pwdEct + ", usrSta=" + usrSta + ", usrAdd=" + usrAdd
        + ", usrbal=" + usrbal + ", usrPot=" + usrPot + ", lvl=" + lvl
        + ", regtim=" + regtim + ", regIp=" + regIp + ", mnttim=" + mnttim
        + ", mntIp=" + mntIp + "]";
  }

}
