package cn.gloud.invoiceManager.Utils;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

import cn.gloud.invoiceManager.Entity.UserInfo;
import cn.gloud.invoiceManager.greendao.gen.DaoMaster;
import cn.gloud.invoiceManager.greendao.gen.DaoSession;
import cn.gloud.invoiceManager.greendao.gen.UserInfoDao;

public class UserInfoUtils {
    private static UserInfoUtils mInstances = null;
    private UserInfoDao mDao = null;
    private DaoSession mDbSession = null;
    private Context mContext = null;

    private final String DBNAME = "GloudUserInfoDb";

    public UserInfoUtils(Context ctx) {
        mContext = ctx;
        initDb();
    }

    public static UserInfoUtils getInstances(Context ctx) {
        if (null == mInstances) {
            mInstances = new UserInfoUtils(ctx);
        }
        return mInstances;
    }

    /*初始化数据库相关*/
    private void initDb() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DBNAME, null);
        Database db = helper.getEncryptedWritableDb("super-secret");
        DaoMaster daoMaster = new DaoMaster(db);
        mDbSession = daoMaster.newSession();
    }

    public UserInfo GetUserinfo() {
        if (null == mDbSession) {
            initDb();
        }
        UserInfo bean = mDbSession.getUserInfoDao().load(9527L);
        return bean;
    }


    public void SaveUserInfo(UserInfo userinfo) {
        if (null == mDbSession) {
            initDb();
        }
        mDbSession.getUserInfoDao().insertOrReplace(userinfo);

    }

}
