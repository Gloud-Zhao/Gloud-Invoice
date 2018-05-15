package cn.gloud.invoiceManager.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import cn.gloud.invoiceManager.Entity.InvoiceInfoEntity;
import cn.gloud.invoiceManager.Entity.UserInfo;

import cn.gloud.invoiceManager.greendao.gen.InvoiceInfoEntityDao;
import cn.gloud.invoiceManager.greendao.gen.UserInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig invoiceInfoEntityDaoConfig;
    private final DaoConfig userInfoDaoConfig;

    private final InvoiceInfoEntityDao invoiceInfoEntityDao;
    private final UserInfoDao userInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        invoiceInfoEntityDaoConfig = daoConfigMap.get(InvoiceInfoEntityDao.class).clone();
        invoiceInfoEntityDaoConfig.initIdentityScope(type);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        invoiceInfoEntityDao = new InvoiceInfoEntityDao(invoiceInfoEntityDaoConfig, this);
        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);

        registerDao(InvoiceInfoEntity.class, invoiceInfoEntityDao);
        registerDao(UserInfo.class, userInfoDao);
    }
    
    public void clear() {
        invoiceInfoEntityDaoConfig.clearIdentityScope();
        userInfoDaoConfig.clearIdentityScope();
    }

    public InvoiceInfoEntityDao getInvoiceInfoEntityDao() {
        return invoiceInfoEntityDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

}
