package cn.gloud.invoiceManager.Entity;

import java.io.Serializable;

public class LoginRespon implements Serializable{


    /**
     * ret : 0
     * msg : 登录成功
     * data : {"account_id":1,"account_name":"admin","logintoken":"198ztjxEcgKVwilTnq2EGKHUUDFuiKLb","is_admin":1}
     */

    private int ret;
    private String msg;
    private UserInfo data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginRespon{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
