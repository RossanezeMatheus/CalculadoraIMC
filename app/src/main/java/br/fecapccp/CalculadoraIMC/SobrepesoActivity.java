package br.fecapccp.CalculadoraIMC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SobrepesoActivity extends AppCompatActivity {

    TextView txtResultado;
    Button btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobrepeso);

        txtResultado = findViewById(R.id.txtResultado);
        btnFechar = findViewById(R.id.btnFechar);

        double peso = getIntent().getDoubleExtra("peso", 0);
        double altura = getIntent().getDoubleExtra("altura", 0);
        double imc = getIntent().getDoubleExtra("imc", 0);
        String classificacao = getIntent().getStringExtra("classificacao");

        String resultado =
                "Peso: " + peso + "\n"
                + "Altura: " + altura + "\n"
                + "IMC: " + String.format("%.2f", imc) + "\n"
                + "Classificação: " + classificacao + "\n\n"
                + "Pequenas mudanças geram grandes resultados. Você consegue!";

        txtResultado.setText(resultado);

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SobrepesoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}