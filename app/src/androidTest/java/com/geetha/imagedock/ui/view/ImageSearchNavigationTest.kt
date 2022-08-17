package com.geetha.imagedock.ui.view


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.geetha.imagedock.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ImageSearchNavigationTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(ImageSearchActivity::class.java)

    /**
     * Checks on navigation functionality,
     * come back from detail screen , original data is persisted
     */
    @Test
    fun imageSearchNavigationTest() {
        val searchAutoComplete = onView(
            allOf(
                withId(androidx.appcompat.R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(androidx.appcompat.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.appcompat.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("cat"), closeSoftKeyboard())
        Thread.sleep(3000);

        val searchAutoComplete2 = onView(
            allOf(
                withId(androidx.appcompat.R.id.search_src_text), withText("cat"),
                childAtPosition(
                    allOf(
                        withId(androidx.appcompat.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.appcompat.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(pressImeActionButton())
        Thread.sleep(3000);


        val cardView = onView(
            allOf(
                withId(R.id.cardView),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rv_images_list),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView.perform(click())
        Thread.sleep(3000);

        pressBack()
        Thread.sleep(3000);


        val cardView2 = onView(
            allOf(
                withId(R.id.cardView),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rv_images_list),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        Thread.sleep(3000);

        cardView2.perform(click())
        Thread.sleep(3000);

        val textView = onView(
            allOf(
                withId(R.id.tv_tags_detail), withText(Matchers.containsString("cat")),
                withParent(withParent(withId(R.id.cardView))),
                isDisplayed()
            )
        )
        textView.check(matches(withText(Matchers.containsString("cat"))))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
