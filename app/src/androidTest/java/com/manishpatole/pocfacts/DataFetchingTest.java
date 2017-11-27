package com.manishpatole.pocfacts;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.manishpatole.pocfacts.adapter.FactFeedAdapter;
import com.manishpatole.pocfacts.model.Fact;
import com.manishpatole.pocfacts.utility.Utility;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Tests the data fetching mechanism.
 *
 * @author manishpatole
 */
@RunWith(AndroidJUnit4.class)
public class DataFetchingTest {
  private static final long WAIT = 10000;

  @Rule
  public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

  /**
   * Tests if downloaded data is set to recycler view.
   *
   * @throws InterruptedException
   * @throws NoSuchFieldException
   */
  @Test
  public void testDataDownload() throws InterruptedException, NoSuchFieldException {
    MainActivity activity = rule.getActivity();
    View viewById = activity.findViewById(R.id.rv_fact_list);
    assertThat(viewById, notNullValue());

    RecyclerView listView = (RecyclerView) viewById;
    FactFeedAdapter adapter = (FactFeedAdapter) listView.getAdapter();
    assertThat(adapter, instanceOf(RecyclerView.Adapter.class));

    if (Utility.isConnectedToInternet) {
      Thread.sleep(WAIT);
      assertThat(adapter.getItemCount(), greaterThan(5));
    }
  }

  /**
   * Tests if downloaded data doesn't have any feed values.
   *
   * @throws InterruptedException
   * @throws NoSuchFieldException
   */
  @Test
  public void testEmptyList() throws InterruptedException, NoSuchFieldException {
    final MainActivity activity = rule.getActivity();
    View list = activity.findViewById(R.id.tv_no_data_available);
    assertThat(list, notNullValue());

    final TextView text = (TextView) list;

    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        activity.showFeeds(new Fact());
        assertThat(text.getVisibility(), lessThanOrEqualTo(View.VISIBLE));
      }
    });
  }
}
