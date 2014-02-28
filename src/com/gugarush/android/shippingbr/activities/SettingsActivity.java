
package com.gugarush.android.shippingbr.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.shippingbr.R;
import com.gugarush.android.shippingbr.GlobalApp;

public class SettingsActivity extends Activity implements OnClickListener {

    public static final int CODIGO_SEDEX = 40010;
    public static final int CODIGO_SEDEX_A_COBRAR = 40045;
    public static final int CODIGO_SEDEX_DEZ = 40215;
    public static final int CODIGO_SEDEX_HOJE = 40290;
    public static final int CODIGO_PAC = 41106;

    private CheckBox checkPAC, checkSedex, checkSedex10, checkSedexHoje,
            checkSedexACobrar;
    private Button buttonOK, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        checkPAC = (CheckBox) findViewById(R.id.pac);
        checkSedex = (CheckBox) findViewById(R.id.sedex);
        checkSedex10 = (CheckBox) findViewById(R.id.sedex_dez);
        checkSedexACobrar = (CheckBox) findViewById(R.id.sedex_a_cobrar);
        checkSedexHoje = (CheckBox) findViewById(R.id.sedex_hoje);
        buttonOK = (Button) findViewById(R.id.buttonOk);
        buttonOK.setOnClickListener(this);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(this);

        String[] codes = GlobalApp.mSettings.getServicesCode().split(",");

        for (int i = 0; i < codes.length; i++) {
            setCheckBoxEnable(codes[i]);
        }

    }

    private void setCheckBoxEnable(String idService) {
        switch (Integer.valueOf(idService)) {
            case CODIGO_SEDEX:
                checkSedex.setChecked(true);
                break;
            case CODIGO_SEDEX_A_COBRAR:
                checkSedexACobrar.setChecked(true);
                break;
            case CODIGO_SEDEX_DEZ:
                checkSedex10.setChecked(true);
                break;
            case CODIGO_SEDEX_HOJE:
                checkSedexHoje.setChecked(true);
                break;
            case CODIGO_PAC:
                checkPAC.setChecked(true);
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOk:
                saveServicesCode();
                break;
            case R.id.buttonCancel:
                finish();
                break;

            default:
                break;
        }

    }

    private void saveServicesCode() {
        String codes = "";
       if(checkPAC.isChecked()){
           codes = CODIGO_PAC + ",";
       }
       if(checkSedex.isChecked()){
           codes = CODIGO_SEDEX + ",";
       }
       if(checkSedex10.isChecked()){
           codes = CODIGO_SEDEX_DEZ + ",";
       }
       if(checkSedexACobrar.isChecked()){
           codes = CODIGO_SEDEX_A_COBRAR + ",";
       }
       if(checkSedexHoje.isChecked()){
           codes = CODIGO_SEDEX_HOJE + ",";
       }
       if(TextUtils.isEmpty(codes)) {
           Toast.makeText(this, R.string.alert_at_least_one_service, Toast.LENGTH_LONG).show();
       } else {
           GlobalApp.mSettings.setServicesCode(codes.substring(0, codes.length()-2));
       }
               
    }   

}
