package com.br.ra21504836.dimeeletric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Main4Activity extends AppCompatActivity {

    TextView lmax,secfinal,textao,testando;
    Button voltar,disjuntores;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent = getIntent();

        mAdView = (AdView) findViewById(R.id.adView);
        testando = findViewById(R.id.textViewTESTANDO);
        lmax = (TextView)findViewById(R.id.textViewDistanciaMaxima);
        secfinal = (TextView)findViewById(R.id.textViewSecaoFio);
        textao = (TextView) findViewById(R.id.textViewTextao);
        voltar = (Button) findViewById(R.id.buttonVOLTAR);
        disjuntores = findViewById(R.id.botaoDisjuntores);


        MobileAds.initialize(this,
                "ca-app-pub-1811606266661231~3679108688");


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("1E3AD09BAC8AD572303FC5542A1F26FD")
                .build();
        mAdView.loadAd(adRequest);


        Bundle bundle = getIntent().getExtras();

        String secaoprimeira = bundle.getString("Secao1");
        String distanciamaxima = bundle.getString("DistanciaMax");
        String novasecao = bundle.getString("SecaoExperada");
        String correnteIB = bundle.getString("CorrenteIB");
        String correnteIZ = bundle.getString("CorrenteIZ");


        final double[] metodoUsado = bundle.getDoubleArray("MetodoEscolhido");
        final double s1 = Double.parseDouble(secaoprimeira);
        final double lm = Double.parseDouble(distanciamaxima);
        final double s2 = Double.parseDouble(novasecao);
        final double IB = Double.parseDouble(correnteIB);
        final double IZ = Double.parseDouble(correnteIZ);

        testando.setText(""+ Arrays.toString(metodoUsado));
       // String abreviado = String.format( "%.2f", lm );

        if(s2 > s1){
            secfinal.setText(""+s2+"mm²");
            textao.setText("    Bom, o seu condutor deveria ser de "+s1+"mm². " +
                    "Porém, você usou uma distância maior do que a permitida, " +
                    "logo, a seção ficou maior por segurança.");
        }else{
            secfinal.setText(""+s1+" mm²");
            textao.setText("");
        }

        DecimalFormat precision = new DecimalFormat("##.##");
        String gambiarra = precision.format(lm);
        String gambiarra2 = gambiarra.replace(",",".");
        lmax.setText(gambiarra2 + "m");



        //Métodos setOnClickListener
        //Teste de retorno
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                intent.addFlags(IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        disjuntores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(Main4Activity.this, DimeEletric.class);
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putDoubleArray("MetodoEscolhido",metodoUsado);
                bundle.putDouble("CorrenteIB", IB);
                bundle.putDouble("CorrenteIZ", IZ);
                bundle.putDouble("SecaoFinal", s2);
                //Add the bundle to the intent
                intent5.putExtras(bundle);

                //Fire that second activity

                Main4Activity.this.startActivity(intent5);
            }
        });
    }

}
