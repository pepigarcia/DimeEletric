package com.br.ra21504836.dimeeletric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    Spinner metodos;
    Button botaof, popup;
    TextView fioFinal, teste, secaoexperadaText, teste2, correnteIZtext;
    CheckBox TUG, Ilumin;
    EditText tamanhodofio, percentV;
    double secExpected, lmax, sec, correnteIZ;
    boolean erro = false;
    String message2;

    double metodoEscolhido[];

    double secaofio[] = {0.5, 0.75, 1, 1.5, 2.5, 4, 6, 10, 16, 25, 35, 50, 70, 95, 120, 150, 185, 240, 300, 400, 500, 630, 800, 1000};

    double A12pvc[] = {7, 9, 11, 14.5, 19.5, 26, 34, 46, 61, 80, 99, 119, 151, 182, 210, 240, 273, 321, 367, 438, 502, 578, 669, 767}; // checado
    double A13pvc[] = {7, 9, 10, 13.5, 18, 24, 31, 42, 56, 73, 89, 108, 136, 164, 188, 216, 245, 286, 328, 390, 447, 514, 593, 679}; //checado
    double A22pvc[] = {7, 9, 11, 14, 18.5, 25, 32, 43, 57, 75, 92, 110, 139, 167, 192, 219, 248, 291, 334, 398, 456, 526, 609, 698}; //checado
    double A23pvc[] = {7, 9, 10, 13, 17.5, 23, 29, 39, 52, 68, 83, 99, 125, 150, 172, 196, 223, 261, 298, 355, 406, 467, 540, 618};
    double B12pvc[] = {9, 11, 14, 17.5, 24, 32, 41, 57, 76, 101, 125, 151, 192, 232, 269, 309, 353, 415, 477, 571, 656, 758, 881, 1012};
    double B13pvc[] = {8, 10, 12, 15.5, 21, 28, 36, 50, 68, 89, 110, 134, 171, 207, 239, 275, 314, 370, 426, 510, 587, 678, 788, 906};
    double B22pvc[] = {9, 11, 13, 16.5, 23, 30, 38, 52, 69, 90, 111, 133, 168, 201, 232, 265, 300, 351, 401, 477, 545, 626, 723, 827};
    double B23pvc[] = {8, 10, 12, 15, 20, 27, 34, 46, 62, 80, 99, 118, 149, 179, 206, 236, 268, 313, 358, 425, 486, 559, 645, 738};
    double C2pvc[] = {10, 13, 15, 19.5, 27, 36, 46, 63, 85, 112, 138, 168, 213, 258, 299, 344, 392, 461, 530, 634, 729, 843, 978, 1125};
    double C3pvc[] = {9, 11, 14, 17.5, 24, 32, 41, 57, 76, 96, 119, 144, 184, 223, 259, 299, 341, 403, 464, 557, 642, 743, 865, 996};
    double D2pvc[] = {12, 15, 18, 22, 29, 38, 47, 63, 81, 104, 125, 148, 183, 216, 246, 278, 312, 361, 408, 478, 540, 614, 700, 792};
    double D3pvc[] = {10, 12, 15, 18, 24, 31, 39, 52, 67, 86, 103, 122, 151, 179, 203, 230, 258, 297, 336, 394, 445, 506, 577, 652};

    double A12epr[] = {10, 12, 15, 19, 26, 32, 43, 61, 81, 106, 131, 158, 200, 241, 278, 318, 362, 424, 486, 579, 664, 765, 885, 1014};
    double A13epr[] = {9, 11, 13, 17, 23, 31, 40, 54, 73, 95, 117, 141, 179, 216, 249, 285, 324, 380, 435, 519, 595, 685, 792, 908};
    double A22epr[] = {10, 12, 14, 18.5, 25, 33, 42, 57, 76, 99, 121, 145, 183, 220, 253, 290, 329, 386, 442, 527, 604, 696, 805, 923};
    double A23epr[] = {9, 11, 13, 16.5, 22, 30, 38, 51, 68, 89, 109, 130, 164, 197, 227, 259, 295, 346, 396, 472, 541, 623, 721, 826};
    double B12epr[] = {12, 15, 18, 23, 31, 42, 54, 75, 100, 133, 164, 198, 253, 306, 354, 407, 464, 546, 628, 751, 864, 998, 1158, 1332};
    double B13epr[] = {10, 13, 16, 20, 28, 37, 48, 66, 88, 117, 144, 175, 222, 269, 312, 358, 408, 481, 553, 661, 760, 879, 1020, 1173};
    double B22epr[] = {11, 15, 17, 22, 30, 40, 51, 69, 91, 119, 146, 175, 221, 265, 305, 349, 395, 462, 529, 628, 718, 825, 952, 1088};
    double B23epr[] = {10, 13, 15, 19.5, 26, 35, 44, 60, 80, 105, 128, 154, 194, 233, 268, 307, 348, 407, 465, 552, 631, 725, 837, 957};
    double C2epr[] = {12, 16, 19, 24, 33, 45, 58, 80, 107, 138, 171, 209, 269, 328, 382, 441, 506, 599, 693, 835, 966, 1122, 1311, 1515};
    double C3epr[] = {11, 14, 17, 22, 30, 40, 52, 71, 96, 119, 147, 179, 229, 278, 322, 371, 424, 500, 576, 692, 797, 923, 1074, 1237};
    double D2epr[] = {14, 18, 21, 26, 34, 44, 56, 73, 95, 121, 146, 173, 213, 252, 287, 324, 363, 419, 474, 555, 627, 711, 811, 916};
    double D3epr[] = {12, 15, 17, 22, 29, 37, 46, 61, 79, 101, 122, 144, 178, 211, 240, 271, 304, 351, 396, 464, 525, 596, 679, 767};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent2 = getIntent();
        message2 = intent2.getStringExtra(Main2Activity.EXTRA_MESSAGE2);
        TextView textView = new TextView(this);
        textView.setText("Corrente de projeto: " + message2 + "A");

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);


        correnteIZtext = findViewById(R.id.textViewCorrenteIZ);
        teste2 = (TextView) findViewById(R.id.textViewTESTE2);
        percentV = (EditText) findViewById(R.id.editTextTensaoPercent);
        tamanhodofio = (EditText) findViewById(R.id.editTextDistancia);
        TUG = (CheckBox) findViewById(R.id.checkBoxTUG);
        Ilumin = (CheckBox) findViewById(R.id.checkBoxIluminacao);
        botaof = (Button) findViewById(R.id.button3calc);
        popup = (Button) findViewById(R.id.buttonPOPUP);
        fioFinal = (TextView) findViewById(R.id.textViewFioFinal);
        teste = (TextView) findViewById(R.id.textViewTESTE);
        secaoexperadaText = (TextView) findViewById(R.id.textViewSecaoExperada);
        metodos = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.metodos_de_instalacao, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        metodos.setAdapter(adapter);


        //Métodos setOnClickListener

        //checkboxes
        TUG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ilumin.setChecked(false);
                //jeito porco?
                resetarfioFinal();
            }
        });

        Ilumin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TUG.setChecked(false);
                //jeito porco?
                resetarfioFinal();

            }
        });

        //botao
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpv(view);
            }
        });
        botaof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double x[] = null;
                Bundle bundle = getIntent().getExtras();
                Boolean pvc = bundle.getBoolean("pvc");
                if (pvc == true) {
                    String testar = metodos.getSelectedItem().toString();
                    if (testar.equals("A1 com 2 condutores")) {
                        x = A12pvc;
                    }
                    if (testar.equals("A1 com 3 condutores")) {
                        x = A13pvc;
                    }
                    if (testar.equals("A2 com 2 condutores")) {
                        x = A22pvc;
                    }
                    if (testar.equals("A2 com 3 condutores")) {
                        x = A23pvc;
                    }
                    if (testar.equals("B1 com 2 condutores")) {
                        x = B12pvc;
                    }
                    if (testar.equals("B1 com 3 condutores")) {
                        x = B13pvc;
                    }
                    if (testar.equals("B2 com 2 condutores")) {
                        x = B22pvc;
                    }
                    if (testar.equals("B2 com 3 condutores")) {
                        x = B23pvc;
                    }
                    if (testar.equals("C com 2 condutores")) {
                        x = C2pvc;
                    }
                    if (testar.equals("C com 3 condutores")) {
                        x = C3pvc;
                    }
                    if (testar.equals("D com 2 condutores")) {
                        x = D2pvc;
                    }
                    if (testar.equals("D com 3 condutores")) {
                        x = D3pvc;
                    }
                } else {
                    String testar = metodos.getSelectedItem().toString();
                    if (testar.equals("A1 com 2 condutores")) {
                        x = A12epr;
                    }
                    if (testar.equals("A1 com 3 condutores")) {
                        x = A13epr;
                    }
                    if (testar.equals("A2 com 2 condutores")) {
                        x = A22epr;
                    }
                    if (testar.equals("A2 com 3 condutores")) {
                        x = A23epr;
                    }
                    if (testar.equals("B1 com 2 condutores")) {
                        x = B12epr;
                    }
                    if (testar.equals("B1 com 3 condutores")) {
                        x = B13epr;
                    }
                    if (testar.equals("B2 com 2 condutores")) {
                        x = B22epr;
                    }
                    if (testar.equals("B2 com 3 condutores")) {
                        x = B23epr;
                    }
                    if (testar.equals("C com 2 condutores")) {
                        x = C2epr;
                    }
                    if (testar.equals("C com 3 condutores")) {
                        x = C3epr;
                    }
                    if (testar.equals("D com 2 condutores")) {
                        x = D2epr;
                    }
                    if (testar.equals("D com 3 condutores")) {
                        x = D3epr;
                    }
                }
                calcularsecao(x);
                if (secExpected != 0 && erro == false) {
                    sendMessage3(view);
                }
                //Continuacao


            }
        });

    }

    public void calcularsecao(double x[]) {
        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("CorrenteProjeto");
        double correnteprojeto = Double.parseDouble(stuff);
        int i = 0;
        try {
            while (x[i] < correnteprojeto) {
                i = i + 1;
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            Toast toast = Toast.makeText(Main3Activity.this, "Para esse valor de corrente, não existe seção do fio adequada na tabela NBR 5410 ", Toast.LENGTH_LONG);
            toast.show();
            fioFinal.setText("");
            return;
        }
        //Caso nenhum dos dois esteja checado, mandar alerta
        if (!TUG.isChecked() && !Ilumin.isChecked()) {
            erro = true;
            Toast toast = Toast.makeText(Main3Activity.this, "Selecione um tipo de circuito!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else {
            erro = false;
        }
        //Jeito porco de resolver o problema dos tipos de circuito?
        if (TUG.isChecked() && secaofio[i] < 2.5) {
            sec = 2.5;
            i = 4;
            correnteIZ = x[i];//adicionando nova variavel (25/09/2017)

        } else if (Ilumin.isChecked() && secaofio[i] < 1.5) {
            sec = 1.5;
            i = 3;
            correnteIZ = x[i];
        } else {
            sec = secaofio[i];
            correnteIZ = x[i];
        }
        fioFinal.setText("" + sec);
        correnteIZtext.setText(""+ correnteIZ);

        //Mais um calculo/ seção experada
        String faseTri = bundle.getString("ValorFaseString");
        String tensaoFim = bundle.getString("ValorTensaoString");
        double tensaofinal = Double.parseDouble(tensaoFim);
        double y = Math.sqrt(3);
        String tamanhodofionull = tamanhodofio.getText().toString();

        //Caso PERCENTV || TAMANHODOFIO não tenham sido preenchidos:

        if (tamanhodofionull.matches("")) {
            erro = true;
            Toast toast = Toast.makeText(Main3Activity.this, "Você não entrou com um valor de distância máxima do circuito válido", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else {
            erro = false;
        }

        String percentVnull = percentV.getText().toString();
        if (percentVnull.matches("")) {
            erro = true;
            Toast toast = Toast.makeText(Main3Activity.this, "Você não entrou com um valor de queda de tensão válido", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else {
            erro = false;
        }
        //Caso tamano do fio <= 0 e delta V <= 0
        Double l = Double.valueOf(tamanhodofio.getText().toString());
        Double deltaV = Double.valueOf(percentV.getText().toString());
        if (l <= 0 || deltaV <= 0) {
            erro = true;
            Toast toast = Toast.makeText(Main3Activity.this, "O valor 'zero' não é válido para esse caso", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else {
            erro = false;
        }
        //Calculo da distância máxima:

        //alterar(teste) pra um nome que faça sentido

        //Comparativos
        double secTestTri = (100 * y * l * correnteprojeto) / (deltaV * tensaofinal * 56);
        double secTestMono = (200 * l * correnteprojeto) / (deltaV * tensaofinal * 56);

        teste2.setText("" + secTestMono);
        if (faseTri.equals("true")) {
            lmax = (sec * deltaV * tensaofinal * 56) / (100 * y * correnteprojeto);
            teste.setText("" + lmax);
            if (sec <= secTestTri) {
                try {
                    while (secaofio[i] < secTestTri) {
                        i = i + 1;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    Toast toast = Toast.makeText(Main3Activity.this, "Não existe seção do fio adequada na tabela NBR 5410 ", Toast.LENGTH_LONG);
                    toast.show();
                    fioFinal.setText("");
                    return;
                }
            }
        } else {
            lmax = (sec * deltaV * tensaofinal * 56) / (200 * correnteprojeto);
            teste.setText("" + lmax);
            if (sec <= secTestMono) {
                try {
                    while (secaofio[i] < secTestMono) {
                        i = i + 1;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    Toast toast = Toast.makeText(Main3Activity.this, "Não existe seção do fio adequada na tabela NBR 5410 ", Toast.LENGTH_LONG);
                    toast.show();
                    fioFinal.setText("");
                    return;
                }
            }
        }
        secExpected = secaofio[i];
        correnteIZ = x[i];
        metodoEscolhido = x;
        correnteIZtext.setText(""+ correnteIZ);
        secaoexperadaText.setText("" + secExpected);
    }

    public void resetarfioFinal() {
        sec = 0;
        fioFinal.setText("");
    }

    public void sendMessage3(View view) {

        String sectest = fioFinal.getText().toString();
        if (!sectest.matches("")) {
            Intent intent3 = new Intent(Main3Activity.this, Main4Activity.class);
            String correnteIZString = correnteIZtext.getText().toString();
            String secao1 = fioFinal.getText().toString();
            String distanciaMax = teste.getText().toString();
            String secaoExperada = secaoexperadaText.getText().toString();

            Bundle bundle = new Bundle();

            //Add your data to bundle
            bundle.putDoubleArray("MetodoEscolhido",metodoEscolhido);
            bundle.putString("CorrenteIB", message2);
            bundle.putString("CorrenteIZ", correnteIZString);
            bundle.putString("Secao1", secao1);
            bundle.putString("DistanciaMax", distanciaMax);
            bundle.putString("SecaoExperada", secaoExperada);
            //Add the bundle to the intent
            intent3.putExtras(bundle);

            //Fire that second activity
            Main3Activity.this.startActivity(intent3);
        }

    }

    public void popUpv(View view) {
        Intent intentpop = new Intent(Main3Activity.this, POPUPActivity.class);
        Main3Activity.this.startActivity(intentpop);
    }
}