package br.fecapccp.CalculadoraIMC;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculoIMCActivity extends AppCompatActivity {
    EditText edtPeso, edtAltura;
    Button btnCalcular, btnLimpar, btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imcactivity);

        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnFechar = findViewById(R.id.btnFechar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPeso.setText("");
                edtAltura.setText("");
            }
        });

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void calcularIMC() {
        String pesoStr = edtPeso.getText().toString();
        String alturaStr = edtAltura.getText().toString();

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);

        if (altura == 0) {
            Toast.makeText(this, "Altura não pode ser zero!", Toast.LENGTH_SHORT).show();
            return;
        }

        double imc = peso / (altura * altura);
        String classificacao = "";

        Class<?> activityDestino;

        if (imc < 18.5) {
            classificacao = "Abaixo do Peso";
            activityDestino = AbaixoDoPesoActivity.class;
        } else if (imc < 25) {
            classificacao = "Peso Normal";
            activityDestino = PesoNormalActivity.class;
        } else if (imc < 30) {
            classificacao = "Sobrepeso";
            activityDestino = SobrepesoActivity.class;
        } else if (imc < 35) {
            classificacao = "Obesidade Grau 1";
            activityDestino = Obesidade1Activity.class;
        } else if (imc < 40) {
            classificacao = "Obesidade Grau 2";
            activityDestino = Obesidade2Activity.class;
        } else {
            classificacao = "Obesidade Grau 3";
            activityDestino = Obesidade3Activity.class;
        }

        Intent intent = new Intent(CalculoIMCActivity.this, activityDestino);
        intent.putExtra("peso", peso);
        intent.putExtra("altura", altura);
        intent.putExtra("imc", imc);
        intent.putExtra("classificacao", classificacao);
        startActivity(intent);
    }
}