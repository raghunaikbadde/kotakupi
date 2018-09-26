package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ezcollect.raghu.ezcollect.MainActivity;
import com.ezcollect.raghu.ezcollect.R;

public class FragmentSideMenu extends Fragment implements View.OnClickListener {


    private View rootView;
    private View containerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Activity mContext;
    private MainActivity.MenuSelectionListener menuItemClickListener;
    private LinearLayout mHistory;
    private LinearLayout mLogout;
    private LinearLayout mChallan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.nav_header_main, container, false);
            init();
        } else {
            ViewParent parent = rootView.getParent();
            if (parent != null)
                ((ViewGroup) parent).removeView(rootView);
        }
        return rootView;
    }

    private void init() {
        mHistory = (LinearLayout) rootView.findViewById(R.id.book_history);
        mLogout = (LinearLayout) rootView.findViewById(R.id.logout);
        mChallan = (LinearLayout) rootView.findViewById(R.id.challan);
        mHistory.setOnClickListener(this);
        mChallan.setOnClickListener(this);
        mLogout.setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {


        containerView = mContext.findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(mContext, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(containerView);
    }

    public void openDrawer() {
        if (mDrawerLayout != null) {
            if (isDrawerOpen()) {
                mDrawerLayout.closeDrawer(containerView);
            } else {
                mDrawerLayout.openDrawer(containerView);
            }
        } else {
            mContext.finish();
        }
    }

    public void setMenuItemClickListener(MainActivity.MenuSelectionListener menuItemClickListener) {
        this.menuItemClickListener = menuItemClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_history:
                menuItemClickListener.onMenuSeleted(1);
                break;
            case R.id.challan:
                menuItemClickListener.onMenuSeleted(2);
                break;
            case R.id.logout:
                menuItemClickListener.onMenuSeleted(3);
                break;
        }
    }
}
