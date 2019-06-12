package ru.ogorodnik.homework342;
import android.app.Activity;
import android.content.Intent;

public class Utils
{
    private static int sTheme;

    public final static int THEME_MARGIN1 = 0;
    public final static int THEME_MARGIN3 = 1;
    public final static int THEME_MARGIN10 = 2;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));

    }

    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case THEME_MARGIN1:
                activity.setTheme(R.style.Margin1);
                break;
            case THEME_MARGIN3:
                activity.setTheme(R.style.Margin4);
                break;
            case THEME_MARGIN10:
                activity.setTheme(R.style.Margin8);
                break;
        }
    }
}