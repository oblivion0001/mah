package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DockerBean;

import java.util.List;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class DockerAdapter extends BaseQuickAdapter<DockerBean.RecordsBean, BaseViewHolder> {


    public DockerAdapter(List<DockerBean.RecordsBean> data) {
        super(R.layout.adapter_home_item_single, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DockerBean.RecordsBean item) {

    }
}
