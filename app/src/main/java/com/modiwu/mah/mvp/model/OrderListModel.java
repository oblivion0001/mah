package com.modiwu.mah.mvp.model;

import android.os.BaseBundle;
import android.os.SystemClock;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class OrderListModel {
    public Observable<MeOrderBean> requestOrderBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderListBean()
                .compose(new IoMainSchedule<>());
    }


    public Observable<BaseBean> requestOrderDelBean(String order_id, String reason) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderDelBean(order_id, reason)
                .compose(new IoMainSchedule<>());

    }
}