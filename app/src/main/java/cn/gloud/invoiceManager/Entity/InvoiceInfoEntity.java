package cn.gloud.invoiceManager.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/****************************
 * 创建时间：2017年 12月05日 13:19
 * 项目名称：Gloud-Invoice  
 * @author 赵强
 * @version 1.0
 * @since JDK 1.8.0
 * 文件名称：InvoiceInfoEntity  
 * 类说明：  
 ****************************/
@Entity
public class InvoiceInfoEntity {
    //未知,未知,发票左上角的号，发票号，金额，开票日期，校验码，未知
    @Id(autoincrement = true)
    private long id;
    private String _mMd5Info;
    private String _mInvoideInfoLeftNum;
    private String _mInvoideInfoNum;
    private String _mInvoideInfoPrice;
    private String _mInvoideInfoDate;
    private String _mInvoideInfoCheck;
    private String _mMaster;            //报销人
    private String _mAllInfoStr;
    private String _mCreateDate;

    @Keep
    public InvoiceInfoEntity() {
    }

    @Keep
    public InvoiceInfoEntity(long id, String _mMd5Info, String _mInvoideInfoLeftNum, String _mInvoideInfoNum, String _mInvoideInfoPrice, String _mInvoideInfoDate, String _mInvoideInfoCheck, String _mMaster, String _mAllInfoStr, String _mCreateDate) {
        this.id = id;
        this._mMd5Info = _mMd5Info;
        this._mInvoideInfoLeftNum = _mInvoideInfoLeftNum;
        this._mInvoideInfoNum = _mInvoideInfoNum;
        this._mInvoideInfoPrice = _mInvoideInfoPrice;
        this._mInvoideInfoDate = _mInvoideInfoDate;
        this._mInvoideInfoCheck = _mInvoideInfoCheck;
        this._mMaster = _mMaster;
        this._mAllInfoStr = _mAllInfoStr;
        this._mCreateDate = _mCreateDate;
    }

    @Keep
    public InvoiceInfoEntity(String _mMd5Info, String _mInvoideInfoLeftNum, String _mInvoideInfoNum, String _mInvoideInfoPrice, String _mInvoideInfoDate, String _mInvoideInfoCheck, String _mMaster, String _mAllInfoStr, String _mCreateDate) {
        this._mMd5Info = _mMd5Info;
        this._mInvoideInfoLeftNum = _mInvoideInfoLeftNum;
        this._mInvoideInfoNum = _mInvoideInfoNum;
        this._mInvoideInfoPrice = _mInvoideInfoPrice;
        this._mInvoideInfoDate = _mInvoideInfoDate;
        this._mInvoideInfoCheck = _mInvoideInfoCheck;
        this._mMaster = _mMaster;
        this._mAllInfoStr = _mAllInfoStr;
        this._mCreateDate = _mCreateDate;
    }

    public String get_mInvoideInfoLeftNum() {
        return _mInvoideInfoLeftNum;
    }

    public void set_mInvoideInfoLeftNum(String _mInvoideInfoLeftNum) {
        this._mInvoideInfoLeftNum = _mInvoideInfoLeftNum;
    }

    public String get_mInvoideInfoNum() {
        return _mInvoideInfoNum;
    }

    public void set_mInvoideInfoNum(String _mInvoideInfoNum) {
        this._mInvoideInfoNum = _mInvoideInfoNum;
    }

    public String get_mInvoideInfoPrice() {
        return _mInvoideInfoPrice;
    }

    public void set_mInvoideInfoPrice(String _mInvoideInfoPrice) {
        this._mInvoideInfoPrice = _mInvoideInfoPrice;
    }

    public String get_mInvoideInfoDate() {
        return _mInvoideInfoDate;
    }

    public void set_mInvoideInfoDate(String _mInvoideInfoDate) {
        this._mInvoideInfoDate = _mInvoideInfoDate;
    }

    public String get_mInvoideInfoCheck() {
        return _mInvoideInfoCheck;
    }

    public void set_mInvoideInfoCheck(String _mInvoideInfoCheck) {
        this._mInvoideInfoCheck = _mInvoideInfoCheck;
    }

    public String get_mMd5Info() {
        return _mMd5Info;
    }

    public void set_mMd5Info(String _mMd5Info) {
        this._mMd5Info = _mMd5Info;
    }

    public String get_mAllInfoStr() {
        return _mAllInfoStr;
    }

    public void set_mAllInfoStr(String _mAllInfoStr) {
        this._mAllInfoStr = _mAllInfoStr;
    }

    public String get_mMaster() {
        return _mMaster;
    }

    public void set_mMaster(String _mMaster) {
        this._mMaster = _mMaster;
    }

    public String get_mCreateDate() {
        return _mCreateDate;
    }

    public void set_mCreateDate(String _mCreateDate) {
        this._mCreateDate = _mCreateDate;
    }

    @Override
    public String toString() {
        return "InvoiceInfoEntity{" +
                "id=" + id +
                ", _mMd5Info='" + _mMd5Info + '\'' +
                ", _mInvoideInfoLeftNum='" + _mInvoideInfoLeftNum + '\'' +
                ", _mInvoideInfoNum='" + _mInvoideInfoNum + '\'' +
                ", _mInvoideInfoPrice='" + _mInvoideInfoPrice + '\'' +
                ", _mInvoideInfoDate='" + _mInvoideInfoDate + '\'' +
                ", _mInvoideInfoCheck='" + _mInvoideInfoCheck + '\'' +
                ", _mMaster='" + _mMaster + '\'' +
                ", _mAllInfoStr='" + _mAllInfoStr + '\'' +
                ", _mCreateDate='" + _mCreateDate + '\'' +
                '}';
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
