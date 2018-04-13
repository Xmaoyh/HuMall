package com.maoyihan.www.kobe.module.home.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseFragment;
import com.maoyihan.www.kobe.db.entity.UserEntity;
import com.maoyihan.www.kobe.module.home.presenter.UserViewModel;
import com.maoyihan.www.kobe.module.home.view.adapter.NewsAdapter;
import com.maoyihan.www.kobe.module.home.view.adapter.UserAdapter;
import com.maoyihan.www.kobe.utils.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * 我
 * Created by MaoYiHan on 2017/9/26 0026
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.newsFg_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.newsFg_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.btn)
    FloatingActionButton btn;
    Unbinder unbinder;

    private UserAdapter mUserAdapter;
    private UserViewModel mUserViewModel;

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.common_single_list, null);
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
    }

    @Override
    protected void initData() {
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.getUserEntityList().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> userEntities) {
                mUserAdapter.setNewData(userEntities);
                smartRefreshLayout.setRefreshing(false);
            }
        });
        mUserViewModel.getUserFromDb();
    }

    @Override
    protected void initListener() {
        AppBarLayout appBarLayout = (AppBarLayout)mActivity.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset == 0){
                    smartRefreshLayout.setEnabled(true);
                }else{
                    smartRefreshLayout.setEnabled(false);
                }
            }
        });
        smartRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUserViewModel.getUserFromDb();
            }
        });
        btn.setOnClickListener(v -> {
            mUserViewModel.deleteMao();
        });
    }

    @Override
    protected void handleBundle(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mUserAdapter = new UserAdapter();
        mUserAdapter.setOnDeleteListener(userEntity -> {
            mUserViewModel.deleteUser(userEntity);
        });
        mUserAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<UserEntity> userEntities = new ArrayList<>();
                UserEntity userEntity = new UserEntity();
                userEntity.setName("Mao"+position);
                userEntities.add(userEntity);
                mUserViewModel.insertUser(userEntities);
            }
        });

        mUserAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                UserEntity userEntity = (UserEntity) adapter.getData().get(position);
                mUserViewModel.updateUser(userEntity);
                return false;
            }
        });
        recyclerView.setAdapter(mUserAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(mUserAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
