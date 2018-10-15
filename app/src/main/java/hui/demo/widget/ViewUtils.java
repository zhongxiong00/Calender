package hui.demo.widget;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class ViewUtils {
    public static void setTextViewDrawableBottom(TextView tv, Drawable drawable) {
        if (tv != null && drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            tv.setCompoundDrawables(null, null, null, drawable);
        }
    }

}
