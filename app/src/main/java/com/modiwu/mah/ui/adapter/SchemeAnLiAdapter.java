package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Administrator on 2018/1/23.
 * s
 */

public class SchemeAnLiAdapter extends BaseQuickAdapter<SchemeDetailBean.FanganBean, BaseViewHolder> {
    public SchemeAnLiAdapter(List<SchemeDetailBean.FanganBean> data) {
        super(R.layout.adapter_scheme_body_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SchemeDetailBean.FanganBean bean) {
        ImageView ivBodyPic = baseViewHolder.convertView.findViewById(R.id.ivBodyPic);
        Glide.with(mContext).load(bean.fangan_avatar).apply(GlideUtils.init().options()).into(ivBodyPic);
        baseViewHolder.addOnClickListener(R.id.ivBodyPic)
                .setText(R.id.tvItemTitle, StringUtils.getInstance().isNullable(bean.fangan_name, ""))
                .setText(R.id.tvItemBody, StringUtils.getInstance().isNullable(bean.fangan_desc, ""));
    }
}