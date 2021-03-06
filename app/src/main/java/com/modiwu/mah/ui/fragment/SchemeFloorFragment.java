package com.modiwu.mah.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.mvp.model.event.BGAPagerSelectEvent;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Administrator on 2018/1/23.
 * 硬装，软装Fragment
 */

public class SchemeFloorFragment extends SuperBaseFragment {

    SchemeDetailActivity mActivity;
    SchemeDetailBean.LoupanBean mLoupanBeans;
    @BindView(R.id.building_adv_img)
    ImageView mBuildingAdvImg;
    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.tvPrice)
    TextView mTvPrice;
    @BindView(R.id.tvStatus)
    TextView mTvStatus;
    @BindView(R.id.tvFloorLocal)
    TextView mTvFloorLocal;
    @BindView(R.id.tvSellLocal)
    TextView mTvSellLocal;
    @BindView(R.id.tvPhone)
    TextView mTvPhone;
    @BindView(R.id.tvJFSJ)
    TextView mTvJFSJ;
    @BindView(R.id.tvZLHX)
    TextView mTvZLHX;
    @BindView(R.id.tvWYLX)
    TextView mTvWYLX;
    @BindView(R.id.tvCQNX)
    TextView mTvCQNX;
    @BindView(R.id.tvXMJJ)
    TextView mTvXMJJ;
    @BindView(R.id.bgaBanner)
    BGABanner bgaBanner;
    private Unbinder mUnbinder;
    private List<SchemeDetailBean.LoupanhuxingBean> loupanhuxingBeans;

    @Override
    protected void initData(View rootView) {
        mUnbinder = ButterKnife.bind(this, rootView);
        mActivity = (SchemeDetailActivity) getActivity();
        mLoupanBeans = mActivity.mSchemeDetailBean.loupan;
        loupanhuxingBeans = mActivity.mSchemeDetailBean.loupanhuxing;
        MultipleStatusView multiplestatusview = rootView.findViewById(R.id.multiplestatusview);
        if (loupanhuxingBeans != null && loupanhuxingBeans.size() > 0) {
            multiplestatusview.showContent();
            Glide.with(getContext()).load(mLoupanBeans.building_adv_img).apply(GlideUtils.init().options()).into(mBuildingAdvImg);
            mTvName.setText(StringUtils.getInstance().isNullable(mLoupanBeans.building_name, getString(R.string.app_name)));
            mTvPrice.setText(String.format(Locale.CHINA, "均价%d元/平米", mLoupanBeans.building_price));
            mTvStatus.setText(String.format(Locale.CHINA, "开盘：%s",
                    StringUtils.getInstance().isNullable(mLoupanBeans.building_kaipan, "未知")));
            mTvFloorLocal.setText(String.format(Locale.CHINA, "地址：%s",
                    StringUtils.getInstance().isNullable(mLoupanBeans.building_address, "未知")));
            mTvSellLocal.setText(String.format(Locale.CHINA, "售楼地址：%s",
                    StringUtils.getInstance().isNullable(mLoupanBeans.building_sell_address, "未知")));
            mTvPhone.setText(String.format(Locale.CHINA, "联系电话：%s",
                    StringUtils.getInstance().isNullable(mLoupanBeans.building_phone, "未知")));
            mTvJFSJ.setText(String.format(Locale.CHINA, "交房时间：%s",
                    StringUtils.getInstance().isNullable(mLoupanBeans.building_ruzhu, "未知")));
            mTvZLHX.setText(String.format(Locale.CHINA, "主力户型：%s",
                    StringUtils.getInstance().isNullable(mLoupanBeans.building_zlhx, "未知")));
            mTvWYLX.setText(String.format(Locale.CHINA, "物业户型：%s",
                    StringUtils.getInstance().isNullable(mLoupanBeans.building_wylx, "未知")));
            mTvCQNX.setText(String.format(Locale.CHINA, "产权年限：%s",
                    StringUtils.getInstance().isNullable(mLoupanBeans.building_cqnx, "未知")));
            mTvXMJJ.setText(String.format(Locale.CHINA, "%s",
                    StringUtils.getInstance().isNullable(mLoupanBeans.building_xmjj, "未知")));
            bgaBanner.setAdapter((banner, itemView, model, urlPosition) -> {
                ImageView imageView = (ImageView) itemView;
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(getContext()).load(model)
                        .apply(GlideUtils.init().options())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView);
            });
            List<String> mImgUrls = new ArrayList<>();
            List<String> mTypeUrls = new ArrayList<>();
            for (SchemeDetailBean.LoupanhuxingBean bean : loupanhuxingBeans) {
                mImgUrls.add(bean.huxing_avatar);
                mTypeUrls.add(bean.huxing_type);
            }
            bgaBanner.setData(mImgUrls, mTypeUrls);
            bgaBanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    EventBus.getDefault().post(new BGAPagerSelectEvent(loupanhuxingBeans.get(position)));
                }
            });
        } else {
            multiplestatusview.showEmpty();
        }

    }

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme_floor;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
