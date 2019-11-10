package com.openclassroom.mareu;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;

import com.openclassroom.mareu.ui.MainActivity;
import com.openclassroom.mareu.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassroom.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MareuInstrumentedTest {
    // This is fixed
    private static int ITEMS_COUNT = 4;
    private MainActivity mActivity;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
          }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.openclassroom.mareu", appContext.getPackageName());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void meetingList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.meetings_list))
                .check(matches(hasMinimumChildCount(1)));
    }
    /**
     * When filter by 13/11/2019 only one meeting
     */
    @Test
    public void meetingList_filterByDate_shouldContainOnlyThisDate() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Filter by date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2019, 11, 13));
        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.meetings_list)).check(withItemCount(1));
    }

    /**
     *
     */
    @Test
    public void meetingList_filterByRoom_shouldContainOnlyThisRoom() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Filter by room"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.meetings_list),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_frame_layout),
                                        0),
                                0),
                        isDisplayed()));
        ViewInteraction textView = onView(
                allOf(withId(R.id.item_meeting_date), withText(" -  11/13 13:00 - Room 1"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText(" -  11/13 13:00 - Room 1")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.item_meeting_date), withText(" -  11/24 10:00 - Room 1"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText(" -  11/24 10:00 - Room 1")));
    }
    /**
     * When we add an item, the item is shown
     */
    @Test
    public void meetingList_addAction_shouldAddItem() {
        // Given Meeting list with 4 elements
        onView(ViewMatchers.withId(R.id.meetings_list)).check(withItemCount(ITEMS_COUNT));

        // When add a new meeting
        onView(ViewMatchers.withId(R.id.fab)).perform(click());
        onView(ViewMatchers.withId(R.id.topic)).perform(replaceText("Soutenance p4"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.participant)).perform(replaceText("gilles@lamezone.com"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.add_participant_button)).perform(click());
        onView(ViewMatchers.withId(R.id.action_save)).perform(click());

        // Then : the number of element is 5
        onView(ViewMatchers.withId(R.id.meetings_list)).check(withItemCount(ITEMS_COUNT+1));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void meetingList_deleteAction_shouldRemoveItem() {
        RecyclerView recyclerView = mActivity.findViewById(R.id.meetings_list);
        int itemCount = recyclerView.getAdapter().getItemCount();

        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.meetings_list)).check(withItemCount(itemCount));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.meetings_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // confirm neighbour delete
        onView(withText("Yes")).perform(click());
        // Then : the number of element is 3
        onView(ViewMatchers.withId(R.id.meetings_list)).check(withItemCount(itemCount-1));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
