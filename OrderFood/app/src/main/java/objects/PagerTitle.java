package objects;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 3/1/2018.
 */

public class PagerTitle {
    private String title;
    private Fragment page;

    public PagerTitle() {
    }

    public PagerTitle(String title, Fragment page) {
        this.title = title;
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getPage() {
        return page;
    }

    public void setPage(Fragment page) {
        this.page = page;
    }
}
