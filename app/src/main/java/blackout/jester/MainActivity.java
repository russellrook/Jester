package blackout.jester;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.math.BigDecimal;
import java.util.ArrayList;

import blackout.jester.BarData.BarData;
import blackout.jester.BarData.BarDealData;
import blackout.jester.BarData.BarEventData;
import blackout.jester.BarData.DealType;
import blackout.jester.DealsTab.DealListItem;
import blackout.jester.DealsTab.DealsFragment;
import blackout.jester.EventsTab.EventsFragment;
import blackout.jester.MapTab.MapFragment;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;
    //private BarData barSocialHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generating Bars //

        /** Social House **/
        BarData barSocialHouse =
                new BarData("Social House",
                            "social_house_logo",
                            "social_house_profile"
        );
        // * Deals
        ArrayList<BarDealData> socialDeals = new ArrayList<>();
        socialDeals.add(
                new BarDealData(
                        "2 for 1 Mixed Drinks", new BigDecimal(4.00), DealType.MIXEDDRINK, "Today")
        );
        socialDeals.add(
                new BarDealData(
                        "Domestic Beers", new BigDecimal(3.00), DealType.BEER, "Today")
        );

        // * Events
        ArrayList<BarEventData> socialEvents = new ArrayList<>();
        socialEvents.add(new BarEventData("Event 1"));
        socialEvents.add(new BarEventData("Event 2"));

        // * Adding data to bar.
        barSocialHouse.addDeals(socialDeals);
        barSocialHouse.addEvents(socialEvents);

        /** Blank Bar **/
        BarData barBlankBar =
                new BarData("Blank Bar");

        barBlankBar.addDeal(
                new BarDealData("Free Beer!", new BigDecimal(0.00), DealType.BEER, "Today"));

        // Generating the Deals List //
        ArrayList<DealListItem> dealListItems = new ArrayList<>();
        dealListItems.addAll(barSocialHouse.generateDealList());
        dealListItems.addAll(barBlankBar.generateDealList());
        // Generating the Events List //
        //TODO: Stuff here.

        // Bundling list data for passing to fragments
        Bundle dealsBundle = new Bundle();
        dealsBundle.putParcelableArrayList("deals",dealListItems);

        // Instantiating Fragments
        final Fragment dealsFragment = new DealsFragment();
        dealsFragment.setArguments(dealsBundle);
        final Fragment eventsFragment = new EventsFragment();
        final Fragment mapFragment = new MapFragment();

        // Setting up Bottom Bar navigation
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setMaxFixedTabs(4); //Default is 3, when this number is exceeded the bottombar
                                       //changes styles. we don't want that happening.
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_deals) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, dealsFragment)
                            .commit();
                }
                else if (menuItemId == R.id.bb_menu_events) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, eventsFragment)
                            .commit();
                }
                else if (menuItemId == R.id.bb_menu_map) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, mapFragment)
                            .commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_deals) {
                    // Do Nothing I guess
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

}
