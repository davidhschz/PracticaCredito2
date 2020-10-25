package com.example.practicacredito2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText identificacion, nombre, fechadeprestamo, valorprestamo;
    RadioButton rbvivienda, rblibreinversion, rbvehiculo, rbeducacion, rb12, rb24, rb36;
    CheckBox cbiva;
    TextView valordeuda, valorcuota;
    RadioGroup rgcreditos, rgcuotas;
    Button btcalcular, btlimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identificacion = findViewById(R.id.identificacion);
        nombre = findViewById(R.id.nombre);
        fechadeprestamo = findViewById(R.id.fechadeprestamo);
        valorprestamo = findViewById(R.id.valorprestamo);
        rbvivienda = findViewById(R.id.rbvivienda);
        rblibreinversion = findViewById(R.id.rblibreinversion);
        rbvehiculo = findViewById(R.id.rbvehiculo);
        rbeducacion = findViewById(R.id.rbeducacion);
        rb12 = findViewById(R.id.rb12);
        rb24 = findViewById(R.id.rb24);
        rb36 = findViewById(R.id.rb36);
        cbiva = findViewById(R.id.cbiva);
        valordeuda = findViewById(R.id.valordeuda);
        valorcuota = findViewById(R.id.valorcuota);
        btcalcular = findViewById(R.id.btcalcular);
        btlimpiar = findViewById(R.id.btlimpiar);
        rgcreditos = findViewById(R.id.rgcreditos);
        rgcuotas = findViewById(R.id.rgcuotas);


        btcalcular.setOnClickListener(this); //Crear el escuchador.


    }

    @Override
    public void onClick(View v) {

        boolean checkedRB = true;
        if (rgcreditos.getCheckedRadioButtonId() == -1 || rgcuotas.getCheckedRadioButtonId() == -1){
            checkedRB = false;
        }


        if (!identificacion.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() && !fechadeprestamo.getText().toString().isEmpty() &&
            !valorprestamo.getText().toString().isEmpty() && checkedRB){

            //1. Defino variables para operar lógica.
            double capital = Double.parseDouble(valorprestamo.getText().toString()); // Defino variable double y le asigno
            // un objeto valorprestamo con una funcion getText que recibe un string, por lo tanto la debo de convertir a double
            // con la funcion parseDouble.
            double intereses = 0;
            double nCuotas = 0;
            double cManejo = 10000;
            double valorTotalPrestamo = 0;
            double valorCuotaMensual = 0;


            // Compuerta lógica para hallar valor de la variable intereses.
            if (rbvivienda.isChecked()){
                intereses = 0.01f;
            }
            else if (rblibreinversion.isChecked()){
                intereses = 0.02f;
            }
            else if (rbvehiculo.isChecked()){
                intereses = 0.015f;
            }
            else{
                intereses = 0.012f;
            }

            if (rb12.isChecked()){
                nCuotas = 12;
            }
            else if (rb24.isChecked()){
                nCuotas = 24;
            }
            else{
                nCuotas = 36;
            }

            if (cbiva.isChecked()){
                cManejo = cManejo * nCuotas;
                valorTotalPrestamo = (capital * intereses) * nCuotas + capital + cManejo;
            }
            else{
                valorTotalPrestamo = (capital * intereses) * nCuotas + capital;
            }

            valorCuotaMensual = valorTotalPrestamo / nCuotas;

            valordeuda.setText(String.valueOf(valorTotalPrestamo));
            valorcuota.setText(String.valueOf(valorCuotaMensual));
        }
        else{
            Toast.makeText(getApplicationContext(), "Complete todos los datos", Toast.LENGTH_SHORT).show();
        }


    }
}