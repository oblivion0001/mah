package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.ProInfoBean;
import com.modiwu.mah.mvp.model.event.SelectDecorateEvent;
import com.modiwu.mah.mvp.presenter.DecorateBasePresenter;
import com.modiwu.mah.ui.adapter.DecorateItemCommonAdapter;
import com.modiwu.mah.ui.dialog.DialogPushDel;
import com.modiwu.mah.ui.dialog.DialogSelectOtherMan;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/9/11.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateProDetailActivity extends BaseCommonActivity {


    @BindView(R.id.recyclerViewOwner)
    RecyclerView mRecyclerViewOwner;
    @BindView(R.id.bgaBanner)
    BGABanner mBGABanner;
    @BindView(R.id.tvProName)
    TextView tvProName;
    @BindView(R.id.tvProIdNum)
    TextView tvProIdNum;
    @BindView(R.id.recyclerViewVisor)
    RecyclerView mRecyclerViewVisor;
    @BindView(R.id.recyclerViewConst)
    RecyclerView mRecyclerViewConst;
    @BindView(R.id.recyclerViewPM)
    RecyclerView mRecyclerViewPM;
    @BindView(R.id.btnDelPro)
    Button btnDelPro;
    private Unbinder mUnbinder;
    private DecorateBasePresenter mPresenter;
    private DecorateItemCommonAdapter mManAdapter;
    private List<ProInfoBean.CommonBean> mCommonManBeans;
    private List<ProInfoBean.CommonBean> mCommonSvsBeans;
    private List<ProInfoBean.CommonBean> mCommonPmsBeans;
    private List<ProInfoBean.CommonBean> mCommonWorkerBeans;
    private DecorateItemCommonAdapter mSuperViewAdapter;
    private DecorateItemCommonAdapter mPmAdapter;
    private DecorateItemCommonAdapter mWorkerAdapter;
    private String mProId;
    private DialogSelectOtherMan mSelectOtherMan;
    private String mIsMan;
    private DialogPushDel dialogPushDel;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_detail_pro;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        mPresenter = new DecorateBasePresenter(this);
        mProId = mBundle.getString("pro_id");
        mPresenter.getProInfo(mProId);
        btnDelPro.setVisibility(View.GONE);
        mIsMan = (String) SharePreUtil.getData(this, "decorate_select", "业主");
        mSelectOtherMan = new DialogSelectOtherMan(this);
        mRecyclerViewOwner.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mManAdapter = new DecorateItemCommonAdapter(null);
        mRecyclerViewOwner.setAdapter(mManAdapter);
        if ("业主".equals(mIsMan)) {
            mManAdapter.addFooterView(View.inflate(this, R.layout.adapter_footer_edit_pro_item, null));
            mManAdapter.getFooterLayout().setOnClickListener(v -> {
                if (mSelectOtherMan != null && !mSelectOtherMan.isShowing()) {
                    mSelectOtherMan.setTip("业主")
                            .setOnFindListener(phone -> {
                                mPresenter.addMan(phone, mProId);
                            }).show();
                }
            });
        }
        mRecyclerViewVisor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSuperViewAdapter = new DecorateItemCommonAdapter(null);
        mRecyclerViewVisor.setAdapter(mSuperViewAdapter);
        if ("业主".equals(mIsMan)) {
            mSuperViewAdapter.addFooterView(View.inflate(this, R.layout.adapter_footer_edit_pro_item, null));
            mSuperViewAdapter.getFooterLayout().setOnClickListener(v -> {
                if (mSelectOtherMan != null && !mSelectOtherMan.isShowing()) {
                    mSelectOtherMan.setTip("负责人")
                            .setOnFindListener(phone -> {
                                mPresenter.addSuperView(phone, mProId);
                            }).show();
                }
            });
        }

        mRecyclerViewPM.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mPmAdapter = new DecorateItemCommonAdapter(null);
        mRecyclerViewPM.setAdapter(mPmAdapter);
        if ("业主".equals(mIsMan)) {
            mPmAdapter.addFooterView(View.inflate(this, R.layout.adapter_footer_edit_pro_item, null));
            mPmAdapter.getFooterLayout().setOnClickListener(v -> {
                if (mSelectOtherMan != null && !mSelectOtherMan.isShowing()) {
                    mSelectOtherMan.setTip("项目经理")
                            .setOnFindListener(phone -> {
                                mPresenter.addPM(phone, mProId);
                            }).show();
                }
            });
        }

        mRecyclerViewConst.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mWorkerAdapter = new DecorateItemCommonAdapter(null);
        mRecyclerViewConst.setAdapter(mWorkerAdapter);
        if ("业主".equals(mIsMan)) {
            mWorkerAdapter.addFooterView(View.inflate(this, R.layout.adapter_footer_edit_pro_item, null));
            mWorkerAdapter.getFooterLayout().setOnClickListener(v -> {
                if (mSelectOtherMan != null && !mSelectOtherMan.isShowing()) {
                    mSelectOtherMan.setTip("施工人员")
                            .setOnFindListener(phone -> {
                                mPresenter.addWorker(phone, mProId);
                            }).show();
                }
            });
        }

        mBGABanner.setAdapter((banner, itemView, model, position) ->
                Glide.with(mBaseActivity)
                        .load(model)
                        .apply(GlideUtils.init().options(R.drawable.placeholder))
                        .into((ImageView) itemView));
        tvBarRight.setVisibility("业主".equals(mIsMan) ? View.VISIBLE : View.INVISIBLE);

        tvBarRight.setText("编辑");
        tvBarRight.setOnClickListener(v -> {
            boolean isEdit = "编辑".equals(tvBarRight.getText().toString());
            tvBarRight.setText(isEdit ? "保存" : "编辑");
            delOne(isEdit);
            btnDelPro.setVisibility("业主".equals(mIsMan) ? View.VISIBLE : View.INVISIBLE);
        });
        mWorkerAdapter.setOnItemClickListener((adapter, view, position) -> {
            boolean isEdit = "编辑".equals(tvBarRight.getText().toString());
            if (!isEdit) {
                dialogPushDel = new DialogPushDel(this);
                dialogPushDel.setTvTip("确定删除该施工人员吗").show(R.id.tvSure, view1 -> {
                    mPresenter.delWorker(mProId, mWorkerAdapter.getData().get(position).user_id + "");
                });
            }
        });
        mSuperViewAdapter.setOnItemClickListener((adapter, view, position) -> {
            boolean isEdit = "编辑".equals(tvBarRight.getText().toString());
            if (!isEdit) {
                dialogPushDel = new DialogPushDel(this);
                dialogPushDel.setTvTip("确定删除该项目监理吗").show(R.id.tvSure, view1 -> {
                    mPresenter.delSV(mProId, mSuperViewAdapter.getData().get(position).user_id + "");
                });
            }
        });
        mPmAdapter.setOnItemClickListener((adapter, view, position) -> {
            boolean isEdit = "编辑".equals(tvBarRight.getText().toString());
            if (!isEdit) {
                dialogPushDel = new DialogPushDel(this);
                dialogPushDel.setTvTip("确定删除该项目经理吗").show(R.id.tvSure, view1 -> {
                    mPresenter.delPM(mProId, mPmAdapter.getData().get(position).user_id + "");
                });
            }
        });
        mManAdapter.setOnItemClickListener((adapter, view, position) -> {
            boolean isEdit = "编辑".equals(tvBarRight.getText().toString());
            if (!isEdit) {
                dialogPushDel = new DialogPushDel(this);
                dialogPushDel.setTvTip("确定删除该业主吗").show(R.id.tvSure, view1 -> {
                    mPresenter.delMan(mProId, mManAdapter.getData().get(position).user_id + "");
                });
            }
        });
        btnDelPro.setOnClickListener(v -> {
            mPresenter.delPro(mProId);
        });
    }

    private void delOne(boolean isEdit) {
        Observable.fromIterable(mCommonManBeans).subscribe(commonBean -> commonBean.isEdit = isEdit);
        Observable.fromIterable(mCommonSvsBeans).subscribe(commonBean -> commonBean.isEdit = isEdit);
        Observable.fromIterable(mCommonPmsBeans).subscribe(commonBean -> commonBean.isEdit = isEdit);
        Observable.fromIterable(mCommonWorkerBeans).subscribe(commonBean -> commonBean.isEdit = isEdit);
        mManAdapter.notifyDataSetChanged();
        mSuperViewAdapter.notifyDataSetChanged();
        mPmAdapter.notifyDataSetChanged();
        mWorkerAdapter.notifyDataSetChanged();
    }

    @Override
    public void delWorker() {
        super.delWorker();
        if (dialogPushDel != null && dialogPushDel.isShowing()) {
            dialogPushDel.dismiss();
        }
        mPresenter.getProInfo(mProId);
        tvBarRight.setText("编辑");
        btnDelPro.setVisibility("业主".equals(mIsMan) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void delMan() {
        super.delMan();
        if (dialogPushDel != null && dialogPushDel.isShowing()) {
            dialogPushDel.dismiss();
        }
        mPresenter.getProInfo(mProId);
        tvBarRight.setText("编辑");
        btnDelPro.setVisibility("业主".equals(mIsMan) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void delSv() {
        super.delSv();
        if (dialogPushDel != null && dialogPushDel.isShowing()) {
            dialogPushDel.dismiss();
        }
        mPresenter.getProInfo(mProId);
        tvBarRight.setText("编辑");
        btnDelPro.setVisibility("业主".equals(mIsMan) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void delPm() {
        super.delPm();
        if (dialogPushDel != null && dialogPushDel.isShowing()) {
            dialogPushDel.dismiss();
        }
        mPresenter.getProInfo(mProId);
        tvBarRight.setText("编辑");
        btnDelPro.setVisibility("业主".equals(mIsMan) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void addMan() {
        super.addMan();
        addSendOk();
    }

    private void addSendOk() {
        if (mSelectOtherMan != null && mSelectOtherMan.isShowing()) {
            mSelectOtherMan.dismiss();
        }
    }

    @Override
    public void delPro(BaseBean bean) {
        super.delPro(bean);
        ToastUtils.init().showSuccessToast(this, bean.msg);
        EventBus.getDefault().post(new SelectDecorateEvent("业主"));
        finish();
    }

    @Override
    public void addSuperView() {
        super.addSuperView();
        addSendOk();
    }

    @Override
    public void addPM() {
        super.addSuperView();
        addSendOk();
    }

    @Override
    public void addWorker() {
        super.addWorker();
        addSendOk();
    }

    @Override
    public void getProInfo(ProInfoBean bean) {
        super.getProInfo(bean);
        ProInfoBean.ProjectBean project = bean.project;
        List<String> imgsurl = project.imgsurl;
        mBGABanner.setData(imgsurl, null);
        tvProName.setText(project.project_name);
        tvProIdNum.setText(project.project_id);
        Gson gson = new Gson();
        mCommonManBeans = gson.fromJson(gson.toJson(bean.owners), new TypeToken<List<ProInfoBean.CommonBean>>() {
        }.getType());
        mManAdapter.setNewData(mCommonManBeans);

        mCommonSvsBeans = gson.fromJson(gson.toJson(bean.svs), new TypeToken<List<ProInfoBean.CommonBean>>() {
        }.getType());
        Observable.fromIterable(mCommonSvsBeans).subscribe(commonBean -> commonBean.work_type = "监理");
        mSuperViewAdapter.setNewData(mCommonSvsBeans);

        mCommonPmsBeans = gson.fromJson(gson.toJson(bean.pms), new TypeToken<List<ProInfoBean.CommonBean>>() {
        }.getType());
        Observable.fromIterable(mCommonPmsBeans).subscribe(commonBean -> commonBean.work_type = "经理");
        mPmAdapter.setNewData(mCommonPmsBeans);

        mCommonWorkerBeans = gson.fromJson(gson.toJson(bean.wokers), new TypeToken<List<ProInfoBean.CommonBean>>() {
        }.getType());
        mWorkerAdapter.setNewData(mCommonWorkerBeans);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
