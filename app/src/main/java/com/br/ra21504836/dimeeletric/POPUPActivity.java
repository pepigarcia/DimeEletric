package com.br.ra21504836.dimeeletric;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class POPUPActivity extends AppCompatActivity {

    Spinner metodos2;
    TextView explicacao;
    Button mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);


        explicacao = (TextView) findViewById(R.id.textViewExplicacao);
        metodos2 = (Spinner) findViewById(R.id.spinnerPOP);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.metodos_de_instalacao, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        metodos2.setAdapter(adapter);

    metodos2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String testar = metodos2.getSelectedItem().toString();
            if (testar.equals("A1 com 2 condutores")) {
                explicacao.setText("A1: Condutores isolados ou unipolares\n" +
                        "\nInstalado em: eletroduto de seção circular embutido em parede termicamente isolante.\n");
            }
            if (testar.equals("A1 com 3 condutores")) {
                explicacao.setText("A1: Condutores isolados ou unipolares\n" +
                        "\nInstalado em: eletroduto de seção circular embutido em parede termicamente isolante.\n");
            }
            if (testar.equals("A2 com 2 condutores")) {
                explicacao.setText("A2: Cabo multipolar\n" +
                        "\nInstalado em: eletroduto de seção circular embutido em parede termicamente isolante.\n");
            }
            if (testar.equals("A2 com 3 condutores")) {
                explicacao.setText("A2: Cabo multipolar\n" +
                        "\nInstalado em: eletroduto de seção circular embutido em parede termicamente isolante.\n");
            }
            if (testar.equals("B1 com 2 condutores")) {
                explicacao.setText("B1: Condutores isolados ou unipolares\n" +
                        "\nSituações possíveis:\n" +
                        "\nInstalado em: eletroduto de seção circular aparente na parede ou eletroduto de seção não circular aparente sobre parede;\n" +
                        "\nInstalado em: eletroduto de seção circular embutido na alvenaria (parede) ou em canaleta fechada embutida no piso;\n");
            }
            if (testar.equals("B1 com 3 condutores")) {
                explicacao.setText("B1: Condutores isolados ou unipolares\n" +
                        "\nSituações possíveis:\n" +
                        "\nInstalado em: eletroduto de seção circular aparente na parede ou eletroduto de seção não circular aparente sobre parede;\n" +
                        "\nInstalado em: eletroduto de seção circular embutido na alvenaria (parede) ou em canaleta fechada embutida no piso;\n");
            }
            if (testar.equals("B2 com 2 condutores")) {
                explicacao.setText("B2: Cabo multipolar\n" +
                        "\nSituações possíveis:\n" +
                        "\nInstalado em: eletroduto de seção circular aparente na parede ou eletroduto de seção não circular aparente sobre parede;\n" +
                        "\nInstalado em: eletroduto de seção circular embutido na alvenaria (parede) ou em canaleta fechada embutida no piso;\n");
            }
            if (testar.equals("B2 com 3 condutores")) {
                explicacao.setText("B2: Cabo multipolar\n" +
                        "\nSituações possíveis:\n" +
                        "\nInstalado em: eletroduto de seção circular aparente na parede ou eletroduto de seção não circular aparente sobre parede;\n" +
                        "\nInstalado em: eletroduto de seção circular embutido na alvenaria (parede) ou em canaleta fechada embutida no piso;\n");
            }
            if (testar.equals("C com 2 condutores")) {
                explicacao.setText("C: Cabo unipolar ou multipolar \n" +
                        "\nSituações:\n" +
                        "\nInstalado em: sobre a parede.\n" +
                        "\nInstalado em: cabo unipolar ou multipolar fixado teto.\n" +
                        "\nInstalado em: cabo unipolar ou multipolar instalado em bandeja não perfurada.\n" +
                        "\nInstalado em: cabo unipolar ou multipolar embutido em alvenaria sem proteção mecânica ou com proteção mecânica.\n");
            }
            if (testar.equals("C com 3 condutores")) {
                explicacao.setText("C: Cabo unipolar ou multipolar \n" +
                        "\nSituações:\n" +
                        "\nInstalado em: sobre a parede.\n" +
                        "\nInstalado em: cabo unipolar ou multipolar fixado teto.\n" +
                        "\nInstalado em: cabo unipolar ou multipolar instalado em bandeja não perfurada.\n" +
                        "\nInstalado em: cabo unipolar ou multipolar embutido em alvenaria sem proteção mecânica ou com proteção mecânica.\n");
            }
            if (testar.equals("D com 2 condutores")) {
                explicacao.setText("D: Cabo unipolar\n" +
                        "\nInstalado em: em eletroduto de seção circular ou canaleta não ventilada enterrado no solo.\n" +
                        "\nD: Cabo multipolar\n" +
                        "\nInstalado em: em eletroduto de seção circular ou canaleta não ventilada enterrado no solo.\n");
            }
            if (testar.equals("D com 3 condutores")) {
                explicacao.setText("D: Cabo unipolar\n" +
                        "\nInstalado em: eletroduto de seção circular ou canaleta não ventilada enterrado no solo.\n" +
                        "\nD: Cabo multipolar\n" +
                        "\nInstalado em: eletroduto de seção circular ou canaleta não ventilada enterrado no solo.\n");
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            explicacao.setText("A1: Condutores isolados ou unipolares\n" +
                    "Instalado em: eletroduto de seção circular embutido em parede termicamente isolante.\n");
        }
    });



    }

}



