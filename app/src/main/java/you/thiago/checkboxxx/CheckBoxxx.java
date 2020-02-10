package you.thiago.checkboxxx;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;

/**
 * Checkbox with three states
 */
@SuppressWarnings("unused")
public class CheckBoxxx extends AppCompatCheckBox {

    public static final int INDETERMINATE = -1;
    public static final int UNCHECKED = 0;
    public static final int CHECKED = 1;

    private boolean allowIndeterminate;
    private int state;

    public CheckBoxxx(Context context) {
        super(context);
        init();
    }

    public CheckBoxxx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckBoxxx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        state = CheckBoxxx.INDETERMINATE;
        allowIndeterminate = false;

        /* set initial state */
        updateBtnState();

        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) {
                    if (allowIndeterminate) {
                        switch (state) {
                            case CheckBoxxx.INDETERMINATE:
                                state = CheckBoxxx.UNCHECKED;
                                break;
                            case CheckBoxxx.UNCHECKED:
                                state = CheckBoxxx.CHECKED;
                                break;
                            case CheckBoxxx.CHECKED:
                                state = CheckBoxxx.INDETERMINATE;
                                break;
                        }
                    } else {
                        switch (state) {
                            case CheckBoxxx.INDETERMINATE:
                            case CheckBoxxx.CHECKED:
                                state = CheckBoxxx.UNCHECKED;
                                break;
                            case CheckBoxxx.UNCHECKED:
                                state = CheckBoxxx.CHECKED;
                                break;
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
            case INDETERMINATE:
                btnTint = R.color.checkboxUnknown;
                btnDrawable = R.drawable.ic_check_box_outline_blank_black_24dp;
                break;
            case UNCHECKED:
                btnTint = R.color.checkboxError;
                btnDrawable = R.drawable.ic_indeterminate_check_box_black_24dp;
                break;
            case CHECKED:
                btnTint = R.color.checkboxChecked;
                btnDrawable = R.drawable.ic_check_box_black_24dp;
                break;
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
        return state == CheckBoxxx.INDETERMINATE;
    }

    public void allowIndeterminateState(boolean allow) {
        allowIndeterminate = allow;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        updateBtnState();
    }
}
