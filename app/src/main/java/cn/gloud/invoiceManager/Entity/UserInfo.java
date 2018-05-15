package cn.gloud.invoiceManager.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class UserInfo{
    /**
     * account_id : 1
     * account_name : admin
     * logintoken : 198ztjxEcgKVwilTnq2EGKHUUDFuiKLb
     * is_admin : 1
     */
    @Id(autoincrement = true)
    private long currentid=9527;
    private int account_id;
    private String account_name;
    private String logintoken;
    private int is_admin;

    public UserInfo() {
    }

    @Generated(hash = 1630592333)
    public UserInfo(long currentid, int account_id, String account_name,
            String logintoken, int is_admin) {
        this.currentid = currentid;
        this.account_id = account_id;
        this.account_name = account_name;
        this.logintoken = logintoken;
        this.is_admin = is_admin;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getLogintoken() {
        return logintoken;
    }

    public void setLogintoken(String logintoken) {
        this.logintoken = logintoken;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "account_id=" + account_id +
                ", account_name='" + account_name + '\'' +
                ", logintoken='" + logintoken + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }

    public long getCurrentid() {
        return this.currentid;
    }

    public void setCurrentid(long currentid) {
        this.currentid = currentid;
    }
}

