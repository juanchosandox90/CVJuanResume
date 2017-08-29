package app.com.cvjuanresume.juansandoval.cvjuanresume.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import app.com.cvjuanresume.juansandoval.cvjuanresume.R;

/**
 * Created by jsandoval on 19/04/17.
 */

public class CustomizedTextView extends TextView {
    /**
     * Customized TextView for make easy set a font type from xml and reduce the code management
     **/

    //global variables
    private Context context;
    private AttributeSet attrs;
    private TypedArray typedArray;


    /**
     * Public constructor adding the new font attribute
     **/

    public CustomizedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        this.attrs = attrs;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomizedTextView);

        if (isInEditMode())
            return;

        this.setTypeface(getFont());
    }


    //For Service option in BackFragment.java
    public CustomizedTextView(Context context){
        super(context);
        this.context = context;
    }

    /*
    * private getter for get the requested font
    * */

    private Typeface getFont() {
        return null;
    }

}
