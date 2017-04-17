package com.rxmvp.basemvp;

import android.os.Bundle;

import com.wlj.base.ui.BaseFragmentActivity;
import com.wlj.base.util.UIHelper;

public abstract class BaseMvpActivity<V,T extends BasePresenter<V>> extends BaseFragmentActivity {

    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }

    public abstract T initPresenter();

    public void showLoading() {
        UIHelper.showProgressbar(this,null);
    }

    public void hideLoading() {
        UIHelper.closeProgressbar();
    }

    public void showMessage(String message) {
        UIHelper.toastMessage(getApplication(),message);
    }


}
