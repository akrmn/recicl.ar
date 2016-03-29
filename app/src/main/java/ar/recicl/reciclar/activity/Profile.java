package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.RecyclerAdapter;
import ar.recicl.reciclar.RecyclerItemViewHolder;
import ar.recicl.reciclar.adapter.AchievementsAdapter;
import ar.recicl.reciclar.adapter.FriendsAdapter;
import ar.recicl.reciclar.adapter.HistoryAdapter;
import ar.recicl.reciclar.application.SaveSharedPreference;
import ar.recicl.reciclar.data.Person;
import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends Base {
    private static final String TAG = Profile.class.getSimpleName();

    private static final int HISTORY_FRAGMENT = 0;
    private static final int FRIENDS_FRAGMENT = 1;
    private static final int ACHIEVEMENTS_FRAGMENT = 2;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Bind(R.id.circle_image_view) CircleImageView mCircleImageView;
    @Bind(R.id.name_text_view) TextView mNameTextView;
    @Bind(R.id.bio_text_view) TextView mBioTextView;
    @Bind(R.id.location_text_view) TextView mLocationTextView;
    @Bind(R.id.points_text_view) TextView mPointsTextView;

    private Person mPerson;

    public Profile() {
        super(R.layout.activity_profile, R.menu.profile, R.string.title_activity_profile, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTabs();

        String id = getIntent().getStringExtra("personId");
        mPerson = Person.getPerson(id);

        Picasso.with(this).load(mPerson.getPictureRes()).into(mCircleImageView);
        mNameTextView.setText(mPerson.getName());
        mBioTextView.setText(mPerson.getBio());
        mLocationTextView.setText(mPerson.getLocation());
        mPointsTextView.setText(getResources().getQuantityString(R.plurals.recypoints, mPerson.getPoints(), mPerson.getPoints()));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!mPerson.getEmail().equals(SaveSharedPreference.getUserName(this))) {
            menu.removeItem(R.id.action_edit_profile);
        }
        return super.onPrepareOptionsMenu(menu);
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
        Intent intent = new Intent(Profile.this, EditProfile.class);
        startActivity(intent);
        finish();
        return true;
    }

    public static class Fragment extends android.support.v4.app.Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_SECTION_ITEMS = "section_items";
        private static final String ARG_CURRENT_ID = "current_id";

        public Fragment() {}

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber, String currentId) {
            Fragment fragment = new Fragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(ARG_CURRENT_ID, currentId);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                    R.layout.fragment_recycler, container, false);
            setupRecyclerView(recyclerView, getArguments().getInt(ARG_SECTION_NUMBER), getArguments().getString(ARG_CURRENT_ID));

            return recyclerView;
        }

        private void setupRecyclerView(RecyclerView recyclerView, int sectionNumber, String currentId) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.addItemDecoration(getHorizontalDivider(getContext()));

            switch (sectionNumber) {
                case HISTORY_FRAGMENT:
                    HistoryAdapter historyAdapter = new HistoryAdapter();
                    recyclerView.setAdapter(historyAdapter);
                    break;
                case FRIENDS_FRAGMENT:
                    FriendsAdapter friendsAdapter = new FriendsAdapter(getContext(), currentId);
                    friendsAdapter.setOnItemClickListener(new FriendsAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(String id) {
                            Intent intent = new Intent(getContext(), Profile.class);
                            intent.putExtra("personId", id);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(friendsAdapter);
                    break;
                case ACHIEVEMENTS_FRAGMENT:
                    AchievementsAdapter achievementsAdapter = new AchievementsAdapter(getContext());
                    recyclerView.setAdapter(achievementsAdapter);
                    break;
            }
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
            return Fragment.newInstance(position, mPerson.getEmail());
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
