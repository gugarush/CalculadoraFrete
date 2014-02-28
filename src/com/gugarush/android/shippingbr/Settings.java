
package com.gugarush.android.shippingbr;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    private final SharedPreferences mSettings;
    // private final Resources res;
    private final String pref_file_name = "PrefFile";

    public Settings(final Context ctx) {
        this.mSettings = ctx.getSharedPreferences(this.pref_file_name,
                Context.MODE_PRIVATE);

        // this.res = ctx.getResources();
    }

    public final String getServicesCode() {
        return this.mSettings.getString("servicos", "41106,40010");

    }

    public final void setServicesCode(String codes) {
        this.mSettings
                .edit()
                .putString("servicos", codes)
                .commit();

    }
}
