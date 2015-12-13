package com.kyriakosalexandrou.fuse_test_v11.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kyriakosalexandrou.fuse_test_v11.R;
import com.kyriakosalexandrou.fuse_test_v11.Util;

/**
 * Created by Kyriakos on 13/12/2015.
 * <p/>
 * custom widget for an editText with a clear button
 */
public class ClearableEditText extends RelativeLayout {
    public static final String TAG = ClearableEditText.class.getName();

    private static float ENABLED_CLEAR_BUTTON_ALPHA;
    private static float DISABLED_CLEAR_BUTTON_ALPHA;

    private ClearableEditTextCallbacks mClearableEditTextCallbacks;

    private EditText mEditText;
    private ImageView mClearIcon;


    public ClearableEditText(Context context) {
        super(context, null);
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void setClearableEditTextListener(ClearableEditTextCallbacks clearableEditTextCallbacks) {
        mClearableEditTextCallbacks = clearableEditTextCallbacks;
    }

    public EditText getEditText() {
        return mEditText;
    }

    public ImageView getClearIcon() {
        return mClearIcon;
    }

    private void init(Context context) {

        ENABLED_CLEAR_BUTTON_ALPHA = Util.getResFloatValue(getContext(), R.dimen.enabled_clear_button_alpha);
        DISABLED_CLEAR_BUTTON_ALPHA = Util.getResFloatValue(getContext(), R.dimen.disabled_clear_button_alpha);

        View view = inflateView(context);

        bindViews(view);
        setListeners();
    }

    private View inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.widget_clearable_edittext, this, true);
    }

    private void bindViews(View view) {
        mEditText = (EditText) view.findViewById(R.id.edittext);
        mClearIcon = (ImageView) view.findViewById(R.id.clear_icon);
    }

    private void setListeners() {
        setEditTextChangedListener();
        setEditTextOnEditorActionListener();
        setClearIconOnClickListener();
    }

    private void setEditTextChangedListener() {

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                shouldShowClearBtn(count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mClearableEditTextCallbacks.afterTextChanged(s);
            }
        });
    }

    private void shouldShowClearBtn(int count) {
        if (count > 0) {
            mClearIcon.setAlpha(ENABLED_CLEAR_BUTTON_ALPHA);
        } else {
            mClearIcon.setAlpha(DISABLED_CLEAR_BUTTON_ALPHA);
        }
    }

    private void setEditTextOnEditorActionListener() {
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return mClearableEditTextCallbacks.onEditorAction(v, actionId, event);
            }
        });
    }

    private void setClearIconOnClickListener() {
        mClearIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
    }

    public interface ClearableEditTextCallbacks {

        void afterTextChanged(Editable s);

        boolean onEditorAction(TextView v, int actionId, KeyEvent event);
    }
}