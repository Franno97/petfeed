package com.fran.petfeed.capalogica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fran.petfeed.R;
import com.fran.petfeed.capadatos.ConexionSQLiteHelper;
import com.fran.petfeed.capadatos.Mascota;
import com.fran.petfeed.capadatos.Usuario;
import com.fran.petfeed.utilidades.Utilidades;

public class CrearEnfermedadActivity extends AppCompatActivity {

    TextView textVolver;
    EditText txtNombreE;
    Spinner cmbEvitar, cmbAfecta;
    Button btnRegistrarE;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_enfermedad);

        textVolver = (TextView) findViewById(R.id.textVolverE2);
        txtNombreE = (EditText) findViewById(R.id.txtRegNombreE);
        cmbEvitar = (Spinner) findViewById(R.id.cmbEvitar);
        cmbAfecta = (Spinner) findViewById(R.id.cmbAfecta);
        btnRegistrarE = (Button) findViewById(R.id.btnRegistrarEnfermedad);

        conn = new ConexionSQLiteHelper(this, "bd_petfeed", null, 1);

        btnRegistrarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarEnfermedad();
            }
        });

        textVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle objetoEnviado = getIntent().getExtras();
                Usuario user = null;
                Mascota mascota = null;
                user= (Usuario) objetoEnviado.getSerializable("usuario");
                mascota = (Mascota) objetoEnviado.getSerializable("mascota");

                Intent miIntent = new Intent(CrearEnfermedadActivity.this, DetalleEnfemedadActivity.class);
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putSerializable("usuario", user);
                bundle2.putSerializable("mascota", mascota);
                miIntent.putExtras(bundle);
                miIntent.putExtras(bundle2);
                startActivity(miIntent);
            }
        });

    }
    private void registrarEnfermedad() {
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();


        Bundle objetoEnviado = getIntent().getExtras();
        Usuario user = null;
        user= (Usuario) objetoEnviado.getSerializable("usuario");
        Mascota mascota = null;
        mascota = (Mascota) objetoEnviado.getSerializable("mascota");

        values.put(Utilidades.CAMPO_NOMBRE_ENFERMEDAD, txtNombreE.getText().toString());
        values.put(Utilidades.CAMPO_EVITAR, cmbEvitar.getSelectedItem().toString());
        values.put(Utilidades.CAMPO_AFECTA_A, cmbAfecta.getSelectedItem().toString());
        values.put(Utilidades.CAMPO_IDMASCOTA, mascota.getId().toString());

        Long idResultante = db.insert(Utilidades.TABLA_ENFERMEDAD, Utilidades.CAMPO_ID_ENFERMEDAD, values);

        if(idResultante!=-1) {
            Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_LONG).show();
            db.close();
            Intent miIntent = new Intent(CrearEnfermedadActivity.this, DetalleEnfemedadActivity.class);
            Bundle bundle = new Bundle();
            Bundle bundle2 = new Bundle();
            bundle.putSerializable("usuario", user);
            bundle2.putSerializable("mascota", mascota);
            miIntent.putExtras(bundle);
            miIntent.putExtras(bundle2);
            startActivity(miIntent);

        }else {
                Toast.makeText(getApplicationContext(), "Esta enfermedad ya fue registrada", Toast.LENGTH_LONG).show();
                txtNombreE.setText("");
        }
        db.close();
    }
}