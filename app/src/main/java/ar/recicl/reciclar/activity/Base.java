package ar.recicl.reciclar.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.recicl.reciclar.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class Base extends AppCompatActivity {
    private final int mLayoutResId;
    private final int mMenuResId;
    private final int mMenuTitleResId;
    private final boolean mMenuBackEnabled;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Bind(R.id.coordinator_layout) CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.progress_spinner) ProgressBar mProgressBar;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    public Base(int layoutResId, int menuResId, int menuTitleResId, boolean menuBackEnabled) {
        mLayoutResId = layoutResId;
        mMenuResId = menuResId;
        mMenuTitleResId = menuTitleResId;
        mMenuBackEnabled = menuBackEnabled;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(mLayoutResId);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null)
            return;
        if (mMenuBackEnabled) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        actionBar.setTitle(mMenuTitleResId);
    }

    @Override
    public void setTitle(CharSequence title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            super.setTitle(title);
        } else {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            super.setTitle(titleId);
        } else {
            actionBar.setTitle(titleId);
        }
    }

    protected void showAlert(int headingResId, int bodyResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(bodyResId)
                .setTitle(headingResId)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null);
        builder.create().show();
    }

    protected String getTextAsString(EditText view) {
        if (view != null && view.getText() != null)
            return view.getText().toString();
        return "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(mMenuResId, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Menu "Navigate Up" acts like hardware back button
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setProgressBarVisibility(int visibility){
        mProgressBar.setVisibility(visibility);
    }

    public void showSnackbarMessage(String message, String actionText, View.OnClickListener action) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
                .setAction(actionText, action)
                .show();
    }

    public void showSnackbarMessage(int messageResId, int actionTextResId, View.OnClickListener action) {
        Snackbar.make(mCoordinatorLayout, messageResId, Snackbar.LENGTH_LONG)
                .setAction(actionTextResId, action)
                .show();
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        System.out.println(matcher.matches());
        return matcher.matches();
    }

    Paint getDividerPaint() {
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(ContextCompat.getColor(this, R.color.lineBreak));
        paint.setAntiAlias(true);

        return paint;
    }
}
