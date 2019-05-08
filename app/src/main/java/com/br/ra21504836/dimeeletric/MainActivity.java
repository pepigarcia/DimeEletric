package com.br.ra21504836.dimeeletric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    Button b;
    CheckBox mono, bi, tri;
    EditText potenciaW,fatorPotencia,potencia, tensao,corrente;
    private AdView m2AdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m2AdView = (AdView) findViewById(R.id.adView2);
        fatorPotencia = (EditText) findViewById(R.id.editTextFatorDePotencia);
        corrente = (EditText) findViewById(R.id.edit_message);
        potenciaW = (EditText) findViewById(R.id.editTextPotenciaW);
        tensao = (EditText) findViewById(R.id.editTextTensao);
        mono = (CheckBox) findViewById(R.id.checkBoxmono);
        bi = (CheckBox) findViewById(R.id.checkBoxbi);
        tri = (CheckBox) findViewById(R.id.checkBoxtri);
        b = findViewById(R.id.button);

        MobileAds.initialize(this,
                "ca-app-pub-1811606266661231~3679108688");


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("1E3AD09BAC8AD572303FC5542A1F26FD")
                .build();
        m2AdView.loadAd(adRequest);


        //Métodos setOnClickListener:
        //CheckBoxes
        mono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bi.setChecked(false);
                tri.setChecked(false);

            }
        });
        bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mono.setChecked(false);
                tri.setChecked(false);

            }
        });
        tri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mono.setChecked(false);
                bi.setChecked(false);

            }
        });

        //Botão

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // caso potencia nula ou tensao nula - mandar alerta
                String potencianul = potenciaW.getText().toString();
                if (potencianul.matches("")) {
                   Toast toast = Toast.makeText(MainActivity.this, "Você não entrou com um valor de potência válido",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                String fatorPnull = fatorPotencia.getText().toString();
                if (fatorPnull.matches("")) {
                    Toast toast = Toast.makeText(MainActivity.this, "Você não entrou com um valor de fator de potência válido",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                String tensaonul = tensao.getText().toString();
                if(tensaonul.matches("")){
                    Toast toast = Toast.makeText(MainActivity.this, "Você não entrou com um valor de tensão válido",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                Double p = Double.valueOf(potenciaW.getText().toString());
                Double fp = Double.valueOf(fatorPotencia.getText().toString());
                Double t = Double.valueOf(tensao.getText().toString());
                //caso fp > 1;
                if(fp > 1){
                    Toast toast = Toast.makeText(MainActivity.this, "O valor máximo para o fator de potência é '1'",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                // caso potencia 0 ou tensao 0 - mandar alerta
                if( t <= 0 || p <= 0 || fp <= 0 ){
                   Toast toast = Toast.makeText(MainActivity.this, "O valor 'zero' não é válido para esse caso", Toast.LENGTH_SHORT);
                   toast.show();
                    return;

                }else
                    //caso algum dos checkbox esteja checado.
                if(mono.isChecked() || bi.isChecked() || tri.isChecked()) {
                    //executar
                    if (tri.isChecked()) {
                        calculartri(view);
                        sendMessage(view);
                    }else
                        calcular(view);
                        sendMessage(view);

                    //se nenhum checkbox estiver checado - mandar alerta
                }else{
                   Toast toast = Toast.makeText(MainActivity.this, "Selecione uma das fases para o seu circuito!",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        });
    }


    public void sendMessage(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        String t = tensao.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        Bundle bundle = new Bundle();

//Add your data to bundle
        bundle.putString("CorrenteCircuito", message);
        bundle.putBoolean("Trifasico", tri.isChecked());
        bundle.putString("Tensao",t);
//Add the bundle to the intent
        intent.putExtras(bundle);

//Fire that second activity
        startActivity(intent);
    }
    //Métodos de cálculo;
    // Calcula o valor da corrente do circuito para mono/bi fases
    public void calcular(View view) {
        double pW = Double.valueOf(potenciaW.getText().toString());
        double fp = Double.valueOf(fatorPotencia.getText().toString());
        double p = pW/fp;
        double t = Double.valueOf(tensao.getText().toString());
        String i = "" + p/t;
        corrente.setText(i);
    }

    // Calcula o valor da corrente do circuito para tri fase
    public void calculartri(View view) {
        double pW = Double.valueOf(potenciaW.getText().toString());
        double fp = Double.valueOf(fatorPotencia.getText().toString());
        double p = pW/fp;
        double t = Double.valueOf(tensao.getText().toString());
        String i = "" + p / (t * 1.73);
        corrente.setText(i);
    }

}
