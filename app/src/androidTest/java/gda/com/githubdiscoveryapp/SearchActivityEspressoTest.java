package gda.com.githubdiscoveryapp;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gda.com.githubdiscoveryapp.searchuser.SearchActivity;
import gda.com.githubdiscoveryapp.usersrepolist.UserRepoListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by sundayakinsete on 23/02/2018.
 */

@RunWith(AndroidJUnit4.class)
public class SearchActivityEspressoTest {

    @Rule
    public ActivityTestRule<SearchActivity> mActivityRule = new ActivityTestRule<>(SearchActivity.class);
//    @Rule
//    public ActivityTestRule<UserRepoListActivity> userActivityRule = new ActivityTestRule<>(UserRepoListActivity.class);



    @Test
    public void ensureSearchButtonIsClickable(){
        onView(withId(R.id.search_text))
                .perform(typeText("akinsete"), closeSoftKeyboard());
        onView(withId(R.id.btn_search)).check(matches(notNullValue() ));
        onView(withId(R.id.btn_search)).check(matches(withText("Search")));
        onView(withId(R.id.btn_search)).perform(click());


        // onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(12,closeSoftKeyboard()));
    }




}
