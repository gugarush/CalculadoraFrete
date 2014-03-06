
package com.gugarush.android.shippingbr.activities;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shippingbr.R;
import com.gugarush.android.shippingbr.GlobalApp;
import com.gugarush.android.shippingbr.util.Constants;

public class HomeActivity extends Activity implements OnClickListener{

    private TextView textResult;
    private Spinner spinnerPeso;
    private RequestQueue queue;
    private EditText editCepOrigem, editCepDestino;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textResult = (TextView) findViewById(R.id.textView1);
        spinnerPeso = (Spinner) findViewById(R.id.spinnerPeso);
        editCepDestino = (EditText) findViewById(R.id.editCepDestino);
        editCepOrigem = (EditText) findViewById(R.id.editCepOrigem);
        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(this);
        queue = Volley.newRequestQueue(this);
        
    }

	private void requestData() {
        String url = Constants.SERVICE_URL
                + "frete/prazo?nCdServico=" + GlobalApp.mSettings.getServicesCode() + 
                "&sCepOrigem=" + editCepOrigem.getText() + "&sCepDestino="+ editCepDestino.getText() +"&nVlPeso="+ spinnerPeso.getSelectedItem() +"&nCdFormato=1&nVlComprimento=20&" +
                "nVlAltura=2&nVlLargura=11&nVlDiametro=20&nVlValorDeclarado=500";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textResult.setText("Response => " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("gofc", error.getMessage());

                    }
                });

        queue.add(jsObjRequest);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.menu_settings);
        item.setEnabled(true);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(this,
                        SettingsActivity.class).setFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                return false;
            default:
                return false;
        }
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonCalculate:
			requestData();
			break;

		default:
			break;
		}
		
	}

}
