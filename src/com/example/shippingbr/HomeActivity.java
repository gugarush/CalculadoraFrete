
package com.example.shippingbr;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class HomeActivity extends Activity {
    private static final String SOAP_ACTION = "http://tempuri.org/CalcPrecoPrazo";
    private static final String METHOD_NAME = "sayHello";
    private static final String NAMESPACE = "http://ws.android.com/";
    private static final String URL = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);           
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
             
            envelope.setOutputSoapObject(request);
     
            HttpTransportSE ht = new HttpTransportSE(URL);
            try {
             ht.call(SOAP_ACTION, envelope);
                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                 
                TextView tv = new TextView(this);
                tv.setText("Message :"+response.toString());
                setContentView(tv);
       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

}
