package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.AliPayInfoBean;
import com.modiwu.mah.mvp.model.bean.DefLocalBean;
import com.modiwu.mah.mvp.model.bean.OrderCreateBean;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.mvp.model.bean.WxPayInfoBean;

import java.util.Map;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class ShopDetailModel {
    public Observable<ShopGoodsInfoBean> requestShopDetailBean(String goods_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getGoodsInfoBean(goods_id)
                .compose(new IoMainSchedule<>());
    }

    public Observable<OrderCreateBean> requestOrderCreateBean(Map<String, String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderCreateBean(map)
                .compose(new IoMainSchedule<>());
    }

    public Observable<OrderCreateBean> requestSchemeCreateBean(Map<String, String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSchemeCreateBean(map)
                .compose(new IoMainSchedule<>());
    }

    public Observable<DefLocalBean> requestOrderLocalBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderLocalBean()
                .compose(new IoMainSchedule<>());
    }

    public Observable<WxPayInfoBean> requestWXPrePay(String orderId, String payType) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderWXPrePay(orderId, payType)
                .compose(new IoMainSchedule<>());
    }

    public Observable<AliPayInfoBean> requestAliPrePay(String orderId, String payType) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderAliPrePay(orderId, payType)
                .compose(new IoMainSchedule<>());
    }

    public Observable<AliPayInfoBean> requestAliPrePayFangAn(String orderId, String payType) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderAliPrePayFangAn(orderId, payType)
                .compose(new IoMainSchedule<>());
    }

    public Observable<WxPayInfoBean> requestWXPrePayFangAn(String orderId, String payType) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderWXPrePayFangAn(orderId, payType)
                .compose(new IoMainSchedule<>());
    }
}
