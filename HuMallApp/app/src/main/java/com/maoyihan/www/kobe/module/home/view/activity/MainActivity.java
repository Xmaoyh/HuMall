package com.maoyihan.www.kobe.module.home.view.activity;

import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseBarActivity;
import com.maoyihan.www.kobe.module.home.view.fragment.GoodsFragment;
import com.maoyihan.www.kobe.module.home.view.fragment.MeFragment;
import com.maoyihan.www.kobe.module.home.view.fragment.NewsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseBarActivity {
//    private String tag = getClass().getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.bottomBar)
    BottomNavigationBar bottomNavigationBar;

    private NewsFragment mNewsFragment;
    private GoodsFragment mGoodsFragment;
    private MeFragment mMeFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //加打开Drawer的按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        initFragments();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void handleBundle(Bundle savedInstanceState) {

    }


/*    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityControl.addAty(tag, this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //加打开Drawer的按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }*/

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        // 关联检索配置和SearchView
        /*SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //Can be replaced with getComponentName()
        //if this searchable activity is the current activity
        ComponentName componentName = new ComponentName(MainActivity.this, SearchActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFragments() {
        mFragmentManager = getFragmentManager();
        mNewsFragment = (NewsFragment) mFragmentManager.findFragmentByTag("news_fg");
        mGoodsFragment = (GoodsFragment) mFragmentManager.findFragmentByTag("goods_fg");
        mMeFragment = (MeFragment) mFragmentManager.findFragmentByTag("me_fg");

        if (mNewsFragment == null) {
            mNewsFragment = NewsFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_main, mNewsFragment, "news_fg").commit();
        }
        if (mGoodsFragment == null) {
            mGoodsFragment = GoodsFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_main, mGoodsFragment, "goods_fg").commit();
        }
        if (mMeFragment == null) {
            mMeFragment = MeFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_main, mMeFragment, "me_fg").commit();
        }
        mFragmentManager.beginTransaction().show(mNewsFragment).hide(mGoodsFragment).hide(mMeFragment).commitAllowingStateLoss();
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.news_active, "新闻").setInactiveIconResource(R.mipmap.news_inactive).setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.goods_active, "商品").setInactiveIconResource(R.mipmap.goods_inactive).setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.me_active, "我").setInactiveIconResource(R.mipmap.me_inactive).setActiveColorResource(R.color.black))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (position == 0) {
                    mFragmentManager.beginTransaction().show(mNewsFragment).hide(mGoodsFragment).hide(mMeFragment).commitAllowingStateLoss();
                } else if (position == 1) {
                    mFragmentManager.beginTransaction().show(mGoodsFragment).hide(mNewsFragment).hide(mMeFragment).commitAllowingStateLoss();
                } else if (position == 2) {
                    mFragmentManager.beginTransaction().show(mMeFragment).hide(mNewsFragment).hide(mGoodsFragment).commitAllowingStateLoss();
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }


}
