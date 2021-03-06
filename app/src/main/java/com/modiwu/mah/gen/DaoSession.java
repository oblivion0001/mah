package com.modiwu.mah.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.modiwu.mah.mvp.model.bean.ShopCartBean;

import com.modiwu.mah.gen.ShopCartBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig shopCartBeanDaoConfig;

    private final ShopCartBeanDao shopCartBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        shopCartBeanDaoConfig = daoConfigMap.get(ShopCartBeanDao.class).clone();
        shopCartBeanDaoConfig.initIdentityScope(type);

        shopCartBeanDao = new ShopCartBeanDao(shopCartBeanDaoConfig, this);

        registerDao(ShopCartBean.class, shopCartBeanDao);
    }
    
    public void clear() {
        shopCartBeanDaoConfig.clearIdentityScope();
    }

    public ShopCartBeanDao getShopCartBeanDao() {
        return shopCartBeanDao;
    }

}
