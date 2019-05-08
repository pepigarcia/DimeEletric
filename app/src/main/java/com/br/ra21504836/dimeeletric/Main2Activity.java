package com.br.ra21504836.dimeeletric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {


    TextView FC,correntecc,trifase,tensaof;
    EditText temperatura, agrupamento;
    Button b;
    CheckBox pvc, epr;
    double fcTemp, fca;
    int temperaturaArray[] = {10,15,20,25,30,35,40,45,50,55,60};
    int agrupamentoArray[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    double pvcArray[] ={1.22,1.17,1.12,1.06,1,0.94,0.87,0.79,0.71,0.61,0.5};
    double eprArray[] ={1.15,1.12,1.07,1.04,1,0.96,0.91,0.87,0.82,0.76,0.71};
    double agrupResultArray[] ={1,0.8,0.7,0.65,0.6,0.57,0.54,0.52,0.5,0.5,0.5,0.45,0.45,0.45,0.45,0.41,0.41,0.41,0.41,0.38};
    boolean erro = false;

    String h;
    public final static String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Chamando o valor pra colocar no layout da corrente de circuito
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setText("Corrente de circuito é: "+message+"A");

        ViewGroup layout = findViewById(R.id.activity_display_message);
        layout.addView(textView);

        //Todas as variaveis puxadas do XML
        tensaof = (TextView) findViewById(R.id.textViewTENSAO);
        trifase =(TextView) findViewById(R.id.textViewTRIFASE);
        correntecc =(TextView) findViewById(R.id.textViewcorrentecc);
        FC = (TextView) findViewById(R.id.textViewFCTemp);
        pvc = (CheckBox) findViewById(R.id.checkBoxPVC);
        epr = (CheckBox) findViewById(R.id.checkBoxEPR);
        b = (Button) findViewById(R.id.button2);
        temperatura = (EditText) findViewById(R.id.editTextTemperatura);
        agrupamento = (EditText) findViewById(R.id.editTextAgrupamento);


        //Métodos setOnClickListener

        //CheckBoxes
        pvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epr.setChecked(false);
            }
        });
        epr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvc.setChecked(false);
            }
        });


        //Botão
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // caso temperatura nula - enviar alerta
                String tempnul = temperatura.getText().toString();
                if (tempnul.matches("")) {
                    Toast toast = Toast.makeText(Main2Activity.this, "Você não entrou com um valor de temperatura válido",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                //caso agrupamento nulo - enviar alerta
                String agrupnul = agrupamento.getText().toString();
                if(agrupnul.matches("")){
                    Toast toast = Toast.makeText(Main2Activity.this, "Você não entrou com um valor de agrupamento válido",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                //caso agrupamento 0 - enviar alerta
                Double agrp = Double.valueOf(agrupamento.getText().toString());
                if (agrp == 0 ){
                    Toast toast = Toast.makeText(Main2Activity.this, "O valor 'zero' não é válido para esse caso",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }else
                //caso um dos checkBoxes esteja checado
                if (pvc.isChecked() || epr.isChecked()) {
                    //executar
                    calcularTemperatura();
                    calcularAgrupamento();
                    //para não voltar de tela em tela.
                    if(erro == false) {
                        calcularCorrenteProjeto();
                        sendMessage2(view);
                    }
                    //caso não tenha nenhum selecinado -  enviar alerta
                }else{
                    Toast toast = Toast.makeText(Main2Activity.this, "Selecionar um dos métodos de isolamento!",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        //End métodos setOnCickListener
    }


// Métodos de Calculo:

    public void calcularTemperatura() { // Da pra simplificar esse método.
        erro = false; // setar a flag
        double t = Double.valueOf(temperatura.getText().toString());
        int contador = 0;
        //Fazendo pelo array para o PVC.
        if (pvc.isChecked()) {
            try {
                while (temperaturaArray[contador] < t) {
                    contador = contador + 1;
                }
            }catch(ArrayIndexOutOfBoundsException exception) {
                Toast toast = Toast.makeText(Main2Activity.this, "Para esse valor de temperatura, não existe fator de correção adequado na tabela NBR 5410 ", Toast.LENGTH_LONG);
                toast.show();
                erro = true; // para quando o valor voltar e der erro, não mudar de tela
                return;
            }
            fcTemp = pvcArray[contador];

        }
        //Fazendo pelo array para o EPR.
        if (epr.isChecked()) {
            erro = false;
            try {
                while (temperaturaArray[contador] < t) {
                    contador = contador + 1;
                }
            }catch(ArrayIndexOutOfBoundsException exception) {
                Toast toast = Toast.makeText(Main2Activity.this, "Para esse valor de temperatura, não existe fator de correção adequado na tabela NBR 5410 ", Toast.LENGTH_LONG);
                toast.show();
                erro = true; // para quando o valor voltar e der erro, não mudar de tela
                return;
            }
            fcTemp = eprArray[contador];
        }

    }

    public void calcularAgrupamento() {
        double a = Double.valueOf(agrupamento.getText().toString());
        int contador = 0;
        try {
            while (agrupamentoArray[contador] < a) {
                contador = contador + 1;
            }
        }catch(ArrayIndexOutOfBoundsException exception) {
                fca = agrupResultArray[19];
                return;
            }
            fca = agrupResultArray[contador];
    }



    public void calcularCorrenteProjeto() {
        Bundle bundle = getIntent().getExtras();
        //Metodo da primeira tela
        String stuff = bundle.getString("CorrenteCircuito");
        String tensaoFase = bundle.getString("Tensao");
        Boolean fase = bundle.getBoolean("Trifasico");
        //Colocando em um Text;

        String faseText = Boolean.toString(fase);
        double correntecircuito = Double.parseDouble(stuff);
        if(fca != 0 && fcTemp != 0){
            double correnteprojeto = correntecircuito/(fca*fcTemp);
            FC.setText(""+correnteprojeto);
        }
        trifase.setText(""+faseText);
        tensaof.setText(""+tensaoFase);
    }
//Metodo mudança de tela
public void sendMessage2(View view){

    String fctest = FC.getText().toString();
    if(!fctest.matches("")) {
        Intent intent2 = new Intent(Main2Activity.this, Main3Activity.class);
        String message2 = FC.getText().toString();
        String faseCheck = trifase.getText().toString();
        String tensaoCheck = tensaof.getText().toString();
        intent2.putExtra(EXTRA_MESSAGE2, message2);
        Bundle bundle = new Bundle();

        //Add your data to bundle
        bundle.putString("CorrenteProjeto", message2);
        bundle.putBoolean("pvc", pvc.isChecked());
        bundle.putString("ValorFaseString", faseCheck);
        bundle.putString("ValorTensaoString", tensaoCheck);
        //Add the bundle to the intent
        intent2.putExtras(bundle);

        //Fire that second activity
        Main2Activity.this.startActivity(intent2);
    }

    }
}

