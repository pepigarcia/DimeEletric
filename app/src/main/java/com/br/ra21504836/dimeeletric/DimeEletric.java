package com.br.ra21504836.dimeeletric;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DimeEletric extends AppCompatActivity {

    double [] IN = {0.5,1,1.6,2,3,4,6,8,10,13,16,20,25,32,40,50,63,80,100,125};
    TextView displayDisjuntor, displaySecao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dime_eletric);

        Bundle bundle = getIntent().getExtras();
        //Metodo da primeira tela
        double IZ = bundle.getDouble("CorrenteIZ");
        double IB = bundle.getDouble("CorrenteIB");
        double secaoFinal = bundle.getDouble("SecaoFinal");
        double[] metodo = bundle.getDoubleArray("MetodoEscolhido");
        int posicao = 0;
        boolean flag = true;


        displayDisjuntor = findViewById(R.id.textViewDisjuntorA);
        displaySecao = findViewById(R.id.textViewSecaoFio);


        for (int j = 0; j < metodo.length; j++) {
            if (IZ == metodo[j]) {
                    posicao = j;
                    break;
            }
        }

        try {
            while (flag) {
                displayDisjuntor = findViewById(R.id.textViewDisjuntorA);
                for (int i = 0; i < IN.length; i++) {
                    if (IB <= IN[i] && IN[i] <= IZ) {
                        displayDisjuntor.setText(IN[i] + " A");
                        flag = false;
                    }
                }
                posicao++;
                IZ = metodo[posicao];
            }
        }catch(ArrayIndexOutOfBoundsException exception) {
            displaySecao.setText("ERRO DESCONHECIDO -"+IB+" "+IZ);
        }
            if (secaoFinal > metodo[posicao]) {
                displaySecao.setText(metodo[posicao] + " mm²");
            } else {
                displaySecao.setText(secaoFinal + " mm²");
            }


    }
}
