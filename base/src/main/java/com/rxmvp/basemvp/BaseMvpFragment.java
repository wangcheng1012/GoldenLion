package com.rxmvp.basemvp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.wlj.base.util.AppManager;
import com.wlj.base.util.UIHelper;

public abstract class BaseMvpFragment<V, T extends BasePresenter<V>> extends Fragment {

    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }

    public abstract T initPresenter();


    public void showLoading() {
        UIHelper.showProgressbar(getActivity(), null);
    }


    public void hideLoading() {
        UIHelper.closeProgressbar();
    }

    public void showMessage(String message) {
        UIHelper.toastMessage(getContext(), message);
    }

}
