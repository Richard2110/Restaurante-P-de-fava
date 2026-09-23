package com.example.restaurante;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaração dos componentes da interface
    private EditText edtConsumo, edtCouvert, edtPessoas;
    private TextView tvTaxaServico, tvContaTotal, tvValorPessoa;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mapeamento dos componentes com os IDs do XML
        edtConsumo = findViewById(R.id.edtConsumo);
        edtCouvert = findViewById(R.id.edtCouvert);
        edtPessoas = findViewById(R.id.edtPessoas);

        tvTaxaServico = findViewById(R.id.tvTaxaServico);
        tvContaTotal = findViewById(R.id.tvContaTotal);
        tvValorPessoa = findViewById(R.id.tvValorPessoa);

        btnCalcular = findViewById(R.id.btnCalcular);

        // Ação do botão "Calcular conta final"
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularConta();
            }
        });
    }

    private void calcularConta() {
        String strConsumo = edtConsumo.getText().toString().trim();
        String strCouvert = edtCouvert.getText().toString().trim();
        String strPessoas = edtPessoas.getText().toString().trim();

        // Validação básica para garantir que o consumo foi inserido
        if (strConsumo.isEmpty()) {
            Toast.makeText(this, "Por favor, insira o valor do consumo.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Conversão dos valores digitados
        double consumo = Double.parseDouble(strConsumo);
        double couvert = strCouvert.isEmpty() ? 0.0 : Double.parseDouble(strCouvert);
        int pessoas = strPessoas.isEmpty() ? 1 : Integer.parseInt(strPessoas);

        // Garantir que a divisão não seja por zero ou números negativos
        if (pessoas <= 0) {
            pessoas = 1;
        }

        // Cálculos da conta do Pé de Fava
        double taxaServico = consumo * 0.10; // Taxa de serviço fixa de 10%
        double contaTotal = consumo + couvert + taxaServico;
        double valorPorPessoa = contaTotal / pessoas;

        // Exibição dos resultados formatados com 2 casas decimais
        tvTaxaServico.setText(String.format("%.2f", taxaServico));
        tvContaTotal.setText(String.format("%.2f", contaTotal));
        tvValorPessoa.setText(String.format("%.2f", valorPorPessoa));
    }
}