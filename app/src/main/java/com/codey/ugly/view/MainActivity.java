package com.codey.ugly.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codey.ugly.R;
import com.codey.ugly.model.StringKey;
import com.codey.ugly.view.adapter.MyFragmentAdapter;
import com.codey.ugly.view.mainFragment.Common;
import com.codey.ugly.view.mainFragment.Deploy;
import com.codey.ugly.view.mainFragment.Gift;
import com.codey.ugly.view.navmenu.PointMall;
import com.codey.ugly.view.navmenu.RateActivity;
import com.codey.ugly.utils.SharedPreferencesUtil;
import com.umeng.comm.core.constants.Constants;
import com.umeng.comm.core.utils.CommonUtils;
import com.umeng.comm.ui.imagepicker.widgets.CommunityViewPager;
import com.umeng.commm.ui.activities.UserInfoActivity;
import com.umeng.commm.ui.fragments.CommunityMainFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

   // private ViewPager mViewpager;
    private CommunityViewPager communityViewPager;
    private TabLayout mTablayout;
    private ArrayList<Fragment> fragments;

    private NavigationView navigationView;

    private static final int  IS_RATE=1;
    private static final int  IS_Main=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //viewpager
        mTablayout= (TabLayout) findViewById(R.id.tab);
       // mViewpager= (ViewPager) findViewById(R.id.viewpager);
        communityViewPager= (CommunityViewPager) findViewById(R.id.com_viewpager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        toolbar.setNavigationIcon(R.drawable.ic_reorder_black_24dp);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setHeaderLayout();

        toolbar.setOnMenuItemClickListener(onMenuClick);

        setTabs();



    }

    private void setHeaderLayout()
    {
        View view= navigationView.getHeaderView(0);
        ImageView imageView= (ImageView) view.findViewById(R.id.main_head);
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i("header","click");
                Intent intent=new Intent(getApplicationContext(), UserInfoActivity.class);
                intent.putExtra(Constants.TAG_USER, CommonUtils.getLoginUser(getApplicationContext()));
                startActivity(intent);
            }
        });

        TextView mtvLogout= (TextView) view.findViewById(R.id.tv_logout);
        mtvLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferencesUtil.savaData(getApplicationContext(), StringKey.LOGIN_STATUS,0);
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }

    private void setTabs()
    {
        fragments=new ArrayList<>();
        Common common=new Common();
        Deploy deploy=new Deploy();
        Gift gift =new Gift();
       // Social social=new Social();
        CommunityMainFragment mFeedsFragment = new CommunityMainFragment();
//设置Feed流页面的返回按钮不可见
        mFeedsFragment.setBackButtonVisibility(View.INVISIBLE);

        fragments.add(deploy);
        fragments.add(gift);
        fragments.add(common);
        fragments.add(mFeedsFragment);
        String[] pageName={"穿搭","礼物","常识","社区"};
        MyFragmentAdapter adapter=new MyFragmentAdapter(getSupportFragmentManager(),fragments,this,pageName);
        communityViewPager.setAdapter(adapter);
        communityViewPager.setCurrentItem(0);
        communityViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        mTablayout.setupWithViewPager(communityViewPager);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //搜索功能
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
   /*     else if(id == R.id.action_search)
        {
           // Toast.makeText(MainActivity.this, "搜搜", Toast.LENGTH_SHORT).show();
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_main)
        {
            item.setChecked(true);
        } else
        if (id == R.id.nav_mark)
        {
            item.setChecked(true);
            Intent intent=new Intent(MainActivity.this, RateActivity.class);
            startActivity(intent);
        }else if (id==R.id.nav_points_mall)
        {
            item.setChecked(true);
            Intent intent=new Intent(MainActivity.this, PointMall.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_favorite)
        {

        } else if (id == R.id.nav_attention)
        {

        } else if (id == R.id.nav_manage)
        {

        } else if (id == R.id.nav_share)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuClick = new Toolbar.OnMenuItemClickListener()
    {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.action_search:
                    break;
                case R.id.action_notice:
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }
}
