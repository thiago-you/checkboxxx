package you.thiago.checkboxxx;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;

/**
 * Checkbox with three states
 */
@SuppressWarnings("unused")
public class CheckBoxxx extends AppCompatCheckBox {

    public static final int UNKNOWN = -1;
    public static final int UNCHECKED = 0;
    public static final int CHECKED = 1;

    private boolean allowUnknown;
    private boolean allowUnchecked;
    private int state;

    public CheckBoxxx(Context context) {
        super(context);
        init(context, null);
    }

    public CheckBoxxx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CheckBoxxx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        state = CheckBoxxx.UNKNOWN;
        allowUnknown = false;
        allowUnchecked = true;

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CheckBoxxx, 0, 0);

            try {
                allowUnknown = a.getBoolean(R.styleable.CheckBoxxx_indeterminate_state, false);
                allowUnchecked = a.getBoolean(R.styleable.CheckBoxxx_unchecked_state, true);
                state = a.getInt(R.styleable.CheckBoxxx_state, CheckBoxxx.UNKNOWN);
            } finally {
                a.recycle();
            }
        }

        updateBtnState();

        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) {
                    if (allowUnknown) {
                        switch (state) {
                            case CheckBoxxx.UNKNOWN: {
                                state = CheckBoxxx.UNCHECKED;
                                break;
                            }
                            case CheckBoxxx.UNCHECKED: {
                                state = CheckBoxxx.CHECKED;
                                break;
                            }
                            case CheckBoxxx.CHECKED: {
                                state = CheckBoxxx.UNKNOWN;
                                break;
                            }
                        }
                    } else if (!allowUnchecked) {
                        switch (state) {
                            case CheckBoxxx.UNCHECKED:
                            case CheckBoxxx.UNKNOWN: {
                                state = CheckBoxxx.CHECKED;
                                break;
                            }
                            case CheckBoxxx.CHECKED: {
                                state = CheckBoxxx.UNKNOWN;
                                break;
                            }
                        }
                    } else {
                        switch (state) {
                            case CheckBoxxx.UNKNOWN:
                            case CheckBoxxx.CHECKED: {
                                state = CheckBoxxx.UNCHECKED;
                                break;
                            }
                            case CheckBoxxx.UNCHECKED: {
                                state = CheckBoxxx.CHECKED;
                                break;
                            }
                        }
                    }

                    updateBtnState();
                }
            }
        });
    }

    private void updateBtnState() {
        int btnDrawable = R.drawable.ic_indeterminate_check_box_black_24dp;
        int btnTint = R.color.checkboxUnknown;

        switch (state) {
            case UNKNOWN: {
                btnTint = R.color.checkboxUnknown;
                btnDrawable = R.drawable.ic_check_box_outline_blank_black_24dp;
                break;
            }
            case UNCHECKED: {
                btnTint = R.color.checkboxError;
                btnDrawable = R.drawable.ic_indeterminate_check_box_black_24dp;
                break;
            }
            case CHECKED: {
                btnTint = R.color.checkboxChecked;
                btnDrawable = R.drawable.ic_check_box_black_24dp;
                break;
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setButtonTintList(ColorStateList.valueOf(getContext().getColor(btnTint)));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), btnTint)));
            } else {
                CompoundButtonCompat.setButtonTintList(this, ColorStateList.valueOf(ContextCompat.getColor(getContext(), btnTint)));
            }
        }

        setButtonDrawable(btnDrawable);
    }

    @Override
    public boolean isChecked() {
        return state == CheckBoxxx.CHECKED;
    }

    public boolean isUnchecked() {
        return state == CheckBoxxx.UNCHECKED;
    }

    public boolean isIndeterminate() {
        return state == CheckBoxxx.UNKNOWN;
    }

    public void allowUnknownState(boolean allow) {
        allowUnknown = allow;
    }

    public void allowUncheckedState(boolean allow) {
        allowUnchecked = allow;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        updateBtnState();
    }
}
