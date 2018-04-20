package com.maoyihan.www.kobe.module.home.view.activity;


import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.ActivityControl;
import com.maoyihan.www.kobe.module.home.view.fragment.GoodsFragment;
import com.maoyihan.www.kobe.module.home.view.fragment.MeFragment;
import com.maoyihan.www.kobe.module.home.view.fragment.NewsAndThreadFragment;
import com.maoyihan.www.kobe.module.home.view.fragment.OldPicFragment;
import com.maoyihan.www.kobe.module.home.view.fragment.VideoFragment;
import com.maoyihan.www.kobe.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    private String tag = getClass().getSimpleName();
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.bottomBar)
    BottomNavigationBar bottomNavigationBar;


    private NewsAndThreadFragment mNewsFragment;
    private GoodsFragment mGoodsFragment;
    private OldPicFragment mPicFragment;
    private VideoFragment mVideoFragment;
    private MeFragment mMeFragment;
    private FragmentManager mFragmentManager;
    private boolean isExit;
    private String fixBug;

//    @Override
//    protected int getLayout() {
//        return R.layout.activity_main;
//
//    }
//
//    @Override
//    protected void initVariables() {
//
//    }
//
//    @Override
//    protected void initView() {
//        setSupportActionBar(toolbar);
//        //加打开Drawer的按钮
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.setDrawerListener(toggle);
//        toggle.syncState();
//        initFragments();
//    }
//
//    @Override
//    protected void loadData() {
//
//    }
//
//    @Override
//    protected void initListener() {
//        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.nav_camera:
//
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });
//
//        ivLogin.setOnClickListener(v -> startActivity(new Intent(this,LoginActivity.class)));
//    }
//
//    @Override
//    protected void handleBundle(Bundle savedInstanceState) {
//
//    }


    @Override
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
        initFragments();
        initListener();
    }

    private void initListener() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_camera:

                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        ImageView ivLogin = (ImageView) navView.getHeaderView(0).findViewById(R.id.imageView);
        ivLogin.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            exitBy2Click();
        }
    }

    private void exitBy2Click() {
        Timer tExit;
        if (!isExit) {
            isExit = true; // 准备退出
            ToastUtils.show(this, "再按一次退出程序", Toast.LENGTH_SHORT);
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 3000); // 如果3秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            ActivityControl.finishAll();    //获取PID
            this.finish();
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
        mFragmentManager = getSupportFragmentManager();
        mNewsFragment = (NewsAndThreadFragment) mFragmentManager.findFragmentByTag("news_fg");
        mPicFragment = (OldPicFragment) mFragmentManager.findFragmentByTag("pic_fg");
        mVideoFragment = (VideoFragment) mFragmentManager.findFragmentByTag("video_fg");
        mGoodsFragment = (GoodsFragment) mFragmentManager.findFragmentByTag("goods_fg");
        mMeFragment = (MeFragment) mFragmentManager.findFragmentByTag("me_fg");

        if (mNewsFragment == null) {
            mNewsFragment = NewsAndThreadFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_main, mNewsFragment, "news_fg").commit();
        }
        if (mPicFragment == null) {
            mPicFragment = OldPicFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_main, mPicFragment, "pic_fg").commit();
        }
        if (mVideoFragment == null) {
            mVideoFragment = VideoFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_main, mVideoFragment, "video_fg").commit();
        }
        if (mGoodsFragment == null) {
            mGoodsFragment = GoodsFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_main, mGoodsFragment, "goods_fg").commit();
        }
        if (mMeFragment == null) {
            mMeFragment = MeFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_main, mMeFragment, "me_fg").commit();
        }
        mFragmentManager.beginTransaction().show(mNewsFragment).hide(mPicFragment).hide(mVideoFragment).hide(mGoodsFragment).hide(mMeFragment).commitAllowingStateLoss();
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.news_active, "新闻").setInactiveIconResource(R.mipmap.news_inactive).setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.pic_active, "老照片").setInactiveIconResource(R.mipmap.pic_inactive).setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.video_active, "视屏").setInactiveIconResource(R.mipmap.video_inactive).setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.goods_active, "商品").setInactiveIconResource(R.mipmap.goods_inactive).setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.me_active, "我").setInactiveIconResource(R.mipmap.me_inactive).setActiveColorResource(R.color.black))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (position == 0) {
                    mFragmentManager.beginTransaction().show(mNewsFragment).hide(mPicFragment).hide(mVideoFragment).hide(mGoodsFragment).hide(mMeFragment).commitAllowingStateLoss();
                } else if (position == 1) {
                    mFragmentManager.beginTransaction().show(mPicFragment).hide(mNewsFragment).hide(mVideoFragment).hide(mGoodsFragment).hide(mMeFragment).commitAllowingStateLoss();
                } else if (position == 2) {
                    mFragmentManager.beginTransaction().show(mVideoFragment).hide(mNewsFragment).hide(mPicFragment).hide(mGoodsFragment).hide(mMeFragment).commitAllowingStateLoss();
                } else if (position == 3) {
                    mFragmentManager.beginTransaction().show(mGoodsFragment).hide(mNewsFragment).hide(mPicFragment).hide(mVideoFragment).hide(mMeFragment).commitAllowingStateLoss();
                } else if (position == 4) {
                    mFragmentManager.beginTransaction().show(mMeFragment).hide(mNewsFragment).hide(mPicFragment).hide(mVideoFragment).hide(mGoodsFragment).commitAllowingStateLoss();
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
