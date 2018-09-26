package utils;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.ezcollect.raghu.ezcollect.R;


/**
 * CustomTextView allows to set the custom font to the text view
 */
public class CustomEditTextView extends EditText {
    public CustomEditTextView(Context context) {
        super(context);
    }

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);

    }

    public CustomEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomEditTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTextView, 0, 0);
        try {
            String typefaceName = a.getString(R.styleable.CustomTextView_texttypeface);
            Typeface typeface;
            if (typefaceName != null) {
                try {
                    // get the font type face
                    typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/" + typefaceName + ".ttf");
                    // set the font to the text view
                    setTypeface(typeface);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } finally {
            a.recycle();
        }
    }
}
