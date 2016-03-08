package ar.recicl.reciclar.activity;

import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.RecyclerAdapter;
import ar.recicl.reciclar.RecyclerItemViewHolder;

public class Profile extends Base {
    private static final String TAG = Profile.class.getSimpleName();
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private static final int HISTORY_FRAGMENT = 0;
    private static final int FRIENDS_FRAGMENT = 1;
    private static final int ACHIEVEMENTS_FRAGMENT = 2;

    public Profile() {
        super(R.layout.activity_profile, R.menu.profile, R.string.title_activity_profile, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupTabs() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                return onActionLogoutSelected();
            case R.id.action_edit_profile:
                return onActionEditProfileSelected();
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean onActionEditProfileSelected() {
        showSnackbarMessage("Ahora se abre la vista de editar perfil", null, null);
        return true;
    }

    public static class Fragment extends android.support.v4.app.Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_SECTION_ITEMS = "section_items";

        public Fragment() {}

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber, int itemsCount) {
            Fragment fragment = new Fragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(ARG_SECTION_ITEMS, itemsCount);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                    R.layout.fragment_recycler, container, false);
            setupRecyclerView(recyclerView);

//            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
//                case HISTORY_FRAGMENT:
//                    break;
//                case FRIENDS_FRAGMENT:
//                    break;
//                case ACHIEVEMENTS_FRAGMENT:
//                    break;
//            }

            return recyclerView;
        }

        private void setupRecyclerView(RecyclerView recyclerView) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList());
            recyclerView.setAdapter(recyclerAdapter);
        }

        private List<String> createItemList() {
            List<String> itemList = new ArrayList<>();
            Bundle bundle = getArguments();
            if(bundle!=null) {
                int itemsCount = bundle.getInt(ARG_SECTION_ITEMS);
                for (int i = 0; i < itemsCount; i++) {
                    itemList.add("Item " + i);
                }
            }
            return itemList;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a Fragment (defined as a static inner class below).
            return Fragment.newInstance(position, 20);
        }

        @Override
        public int getCount() {return 3;}

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case HISTORY_FRAGMENT:
                    return getString(R.string.title_fragment_history);
                case FRIENDS_FRAGMENT:
                    return getString(R.string.title_fragment_friends);
                case ACHIEVEMENTS_FRAGMENT:
                    return getString(R.string.title_fragment_achievements);
            }
            return null;
        }
    }
}
