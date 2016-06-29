package com.mirhoseini.westwing.view.activity;

import android.view.View;
import android.widget.TextView;

import com.mirhoseini.westwing.BuildConfig;
import com.mirhoseini.westwing.R;
import com.mirhoseini.westwing.presentation.MainPresenterImpl;
import com.mirhoseini.westwing.support.ShadowSnackbar;
import com.mirhoseini.westwing.view.fragment.CampaignsListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static com.mirhoseini.westwing.support.Assert.assertAlertDialogIsShown;
import static com.mirhoseini.westwing.support.Assert.assertSnackbarIsShown;
import static com.mirhoseini.westwing.support.Assert.assertViewIsNotVisible;
import static com.mirhoseini.westwing.support.Assert.assertViewIsVisible;
import static com.mirhoseini.westwing.support.ResourceLocator.getString;
import static com.mirhoseini.westwing.support.ViewLocator.getTextView;
import static com.mirhoseini.westwing.support.ViewLocator.getView;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by Mohsen on 29/06/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class MainActivityRobolectricTest {

    private MainActivity activity;
    private CampaignsListFragment fragment;
    final static String TEST_TEXT = "This is a test text.";

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
        fragment = (CampaignsListFragment) activity.getSupportFragmentManager().findFragmentByTag(activity.TAG_CAMPAIGNS_LIST_FRAGMENT);
        assertNotNull(activity);
    }


    @After
    public void tearDown() throws Exception {
//        activity = null;
//        activity.finish();
    }

    @Test
    public void testShowProgress() throws Exception {
        fragment.showProgress();

        View progressContainer = getView(fragment, R.id.progress_container);
        assertViewIsVisible(progressContainer);

        View listContainer = getView(fragment, R.id.list_container);
        assertViewIsNotVisible(listContainer);

        View errorContainer = getView(fragment, R.id.error_container);
        assertViewIsNotVisible(errorContainer);
    }

    @Test
    public void testHideProgress() throws Exception {
        fragment.hideProgress();

        View progressContainer = getView(fragment, R.id.progress_container);
        assertViewIsNotVisible(progressContainer);
    }

    @Test
    public void testShowToastMessage() throws Exception {
        activity.showToastMessage(TEST_TEXT);
        assertThat(TEST_TEXT, equalTo(ShadowToast.getTextOfLatestToast()));
    }

    @Test
    public void testShowProgressMessage() throws Exception {
        fragment.showProgress();
        fragment.updateProgressMessage(TEST_TEXT);

        View progressContainer = getView(fragment, R.id.progress_container);
        assertViewIsVisible(progressContainer);

        View listContainer = getView(fragment, R.id.list_container);
        assertViewIsNotVisible(listContainer);

        View errorContainer = getView(fragment, R.id.error_container);
        assertViewIsNotVisible(errorContainer);

        TextView progressMessage = getTextView(fragment, R.id.progress_message);
        assertViewIsVisible(progressMessage);
        assertThat(TEST_TEXT, equalTo(progressMessage.getText().toString()));
    }

    @Test
    public void testShowOfflineMessage() throws Exception {
        fragment.showOfflineMessage();

        assertSnackbarIsShown(R.string.offline_message);
    }

    @Test
    public void testShowExitMessage() throws Exception {
        activity.showExitMessage();

        assertThat(getString(R.string.msg_exit), equalTo(ShadowToast.getTextOfLatestToast()));
    }

    @Test
    public void testShowInternetConnectionError() throws Exception {
        activity.showInternetConnectionError();

        assertAlertDialogIsShown(R.string.no_connection_title, R.string.no_connection);
    }

    @Test
    public void testShowDoubleInternetConnectionError() throws Exception {
        activity.showInternetConnectionError();
        activity.showInternetConnectionError();

        assertAlertDialogIsShown(R.string.no_connection_title, R.string.no_connection);
    }


    @Test
    public void testShowRetryMessage() throws Exception {
        fragment.showRetryErrorMessage();

        assertSnackbarIsShown(R.string.retry_message);
    }

    @Test
    public void testOnDestroy() throws Exception {
        activity.onDestroy();

        assertNull(activity.presenter);
    }

    @Test
    public void testDoubleOnBackPressed() throws Exception {
        activity.onBackPressed();

        assertThat(getString(R.string.msg_exit), equalTo(ShadowToast.getTextOfLatestToast()));

        activity.onBackPressed();

        assertTrue(activity.isFinishing());

    }

    @Test
    public void testSingleOnBackPressed() throws Exception {
        activity.onBackPressed();

        assertThat(getString(R.string.msg_exit), equalTo(ShadowToast.getTextOfLatestToast()));

        Thread.sleep(MainPresenterImpl.DOUBLE_BACK_PRESSED_DELAY + 1);

        activity.onBackPressed();

        assertFalse(activity.isFinishing());

    }
}
