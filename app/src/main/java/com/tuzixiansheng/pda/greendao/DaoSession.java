package com.tuzixiansheng.pda.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.tuzixiansheng.pda.bean.ShopBean;

import com.tuzixiansheng.pda.greendao.ShopBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig shopBeanDaoConfig;

    private final ShopBeanDao shopBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        shopBeanDaoConfig = daoConfigMap.get(ShopBeanDao.class).clone();
        shopBeanDaoConfig.initIdentityScope(type);

        shopBeanDao = new ShopBeanDao(shopBeanDaoConfig, this);

        registerDao(ShopBean.class, shopBeanDao);
    }
    
    public void clear() {
        shopBeanDaoConfig.clearIdentityScope();
    }

    public ShopBeanDao getShopBeanDao() {
        return shopBeanDao;
    }

}
