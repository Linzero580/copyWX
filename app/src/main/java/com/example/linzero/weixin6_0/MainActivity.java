package com.example.linzero.weixin6_0;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private ImageView iv_wx;
    private ImageView iv_contact;
    private ImageView iv_find;
    private ImageView iv_my;

    private ImageView img_add;
    private ImageView img_search;

    private LinearLayout layout_wx;
    private LinearLayout layout_contact;
    private LinearLayout layout_find;
    private LinearLayout layout_my;

    private TextView tv_wx;
    private TextView tv_contact;
    private TextView tv_find;
    private TextView tv_my;

    //侧滑菜单
    private DrawerLayout drawer_layout;
    private ListView list_left_drawer;
    private ArrayList<Item> menuLists;
    private MyAdapter<Item> myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        setSelect(0);
        drawerLayout();
    }

    private void drawerLayout() {
        menuLists = new ArrayList<Item>();
        menuLists.add(new Item(R.mipmap.meizu, "魅族"));
        menuLists.add(new Item(R.mipmap.huawei, "华为"));
        menuLists.add(new Item(R.mipmap.xiaomi, "小米"));
        menuLists.add(new Item(R.mipmap.oppo, "OPPO"));
        myAdapter = new MyAdapter<Item>(menuLists, R.layout.item_list) {
            @Override
            public void bindView(ViewHolder holder, Item obj) {
                holder.setImageResource(R.id.img_icon, obj.getIconId());
                holder.setText(R.id.txt_content, obj.getIconName());
            }
        };
        list_left_drawer.setAdapter(myAdapter);
        list_left_drawer.setOnItemClickListener(this);
    }

    private void initEvent() {
        layout_wx.setOnClickListener(this);
        layout_contact.setOnClickListener(this);
        layout_find.setOnClickListener(this);
        layout_my.setOnClickListener(this);

        img_add.setOnClickListener(this);
        img_search.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        img_add = (ImageView) findViewById(R.id.img_add);
        img_search = (ImageView) findViewById(R.id.img_search);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        list_left_drawer = (ListView) findViewById(R.id.list_left_drawer);

        //layout
        layout_wx = (LinearLayout) findViewById(R.id.layout_wx);
        layout_contact = (LinearLayout) findViewById(R.id.layout_contact);
        layout_find = (LinearLayout) findViewById(R.id.layout_find);
        layout_my = (LinearLayout) findViewById(R.id.layout_my);
        //imageview
        iv_wx = (ImageView) findViewById(R.id.iv_wx);
        iv_contact = (ImageView) findViewById(R.id.iv_contact);
        iv_find = (ImageView) findViewById(R.id.iv_find);
        iv_my = (ImageView) findViewById(R.id.iv_my);
        //TextView
        tv_wx = (TextView) findViewById(R.id.tv_wx);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_find = (TextView) findViewById(R.id.tv_find);
        tv_my = (TextView) findViewById(R.id.tv_my);

        mFragments = new ArrayList<Fragment>();
        Fragment mFragment01 = new Fragment01();
        Fragment mFragment02 = new Fragment02();
        Fragment mFragment03 = new Fragment03();
        Fragment mFragment04 = new Fragment04();
        mFragments.add(mFragment01);
        mFragments.add(mFragment02);
        mFragments.add(mFragment03);
        mFragments.add(mFragment04);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_wx:
                setSelect(0);
                break;

            case R.id.layout_contact:
                setSelect(1);
                break;

            case R.id.layout_find:
                setSelect(2);
                break;

            case R.id.layout_my:
                setSelect(3);
                break;

            case R.id.img_add:
                PopupMenu popup = new PopupMenu(MainActivity.this, img_add);
                popup.getMenuInflater().inflate(R.menu.menu_img_add_popup, popup.getMenu());
                //显示popupmenu图标
                try {
                    Field field = popup.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popup);
                    mHelper.setForceShowIcon(true);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.id_chat:
                                Toast.makeText(MainActivity.this, "点击了发起群聊", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.id_add_friend:
                                Toast.makeText(MainActivity.this, "点击了添加朋友", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.id_scan:
                                Toast.makeText(MainActivity.this, "点击了扫一扫", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.id_help:
                                Toast.makeText(MainActivity.this, "点击了帮助反馈", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
                break;
        }
    }

    public void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    public void setTab(int i) {
        resetImg();
        switch (i) {
            case 0:
                iv_wx.setImageResource(R.drawable.wx_touch);
                tv_wx.setTextColor(Color.RED);
                break;

            case 1:
                iv_contact.setImageResource(R.drawable.contact_touch);
                tv_contact.setTextColor(Color.RED);
                break;

            case 2:
                iv_find.setImageResource(R.drawable.find_touch);
                tv_find.setTextColor(Color.RED);
                break;

            case 3:
                iv_my.setImageResource(R.drawable.my_touch);
                tv_my.setTextColor(Color.RED);
                break;
        }
    }

    private void resetImg() {
        iv_wx.setImageResource(R.drawable.wx_normal);
        iv_contact.setImageResource(R.drawable.contact_normal);
        iv_find.setImageResource(R.drawable.find_normal);
        iv_my.setImageResource(R.drawable.my_normal);

        tv_wx.setTextColor(Color.BLACK);
        tv_contact.setTextColor(Color.BLACK);
        tv_find.setTextColor(Color.BLACK);
        tv_my.setTextColor(Color.BLACK);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text", menuLists.get(position).getIconName());
        contentFragment.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.ly_content, contentFragment).commit();
        drawer_layout.closeDrawer(list_left_drawer);
    }
}