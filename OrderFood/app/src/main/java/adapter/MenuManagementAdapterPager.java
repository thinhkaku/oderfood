package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import objects.PagerTitle;

/**
 * Created by Administrator on 3/1/2018.
 */

public class MenuManagementAdapterPager extends FragmentPagerAdapter {
    private ArrayList<PagerTitle>arrPage=new ArrayList<>();

    public MenuManagementAdapterPager(FragmentManager fm,ArrayList<PagerTitle>pagerTitles){
        super(fm);
        arrPage =pagerTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arrPage.get(position).getTitle();
    }


    @Override
    public Fragment getItem(int position) {
        return arrPage.get(position).getPage();
    }

    @Override
    public int getCount() {
        return arrPage.size();
    }
}
