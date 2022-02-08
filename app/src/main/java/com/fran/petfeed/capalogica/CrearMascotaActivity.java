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
import com.fran.petfeed.capadatos.Usuario;
import com.fran.petfeed.utilidades.Utilidades;

public class CrearMascotaActivity extends AppCompatActivity {

    TextView textVolver;
    EditText txtNombreM, txtRaza, txtNacimiento;
    Spinner cmbEspecie, cmbGenero, cmbTamano, cmbEsterilizacion;
    Button btnRegistrarMascota;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mascota);

        textVolver = (TextView) findViewById(R.id.textVolver);
        txtNombreM = (EditText) findViewById(R.id.txtRegMascotaNombre);
        txtRaza = (EditText) findViewById(R.id.txtRegMascotaRaza);
        txtNacimiento = (EditText) findViewById(R.id.txtRegMascotaNac);
        cmbEspecie = (Spinner) findViewById(R.id.cmbEspecie);
        cmbGenero = (Spinner) findViewById(R.id.cmbGenero);
        cmbTamano = (Spinner) findViewById(R.id.cmbTamano);
        cmbEsterilizacion = (Spinner) findViewById(R.id.cmbEsterilizacion);
        btnRegistrarMascota = (Button) findViewById(R.id.btnRegistrarMascota);

        conn = new ConexionSQLiteHelper(this, "bd_petfeed", null, 1);

        btnRegistrarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarMascota();
            }
        });

        textVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle objetoEnviado = getIntent().getExtras();
                Usuario user = null;
                user= (Usuario) objetoEnviado.getSerializable("usuario");
                Intent miIntent = new Intent(CrearMascotaActivity.this, DetalleUsuarioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", user);
                miIntent.putExtras(bundle);
                startActivity(miIntent);
            }
        });
    }
    private void registrarMascota() {
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        Bundle objetoEnviado = getIntent().getExtras();
        Usuario user = null;
        user= (Usuario) objetoEnviado.getSerializable("usuario");

        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA, txtNombreM.getText().toString());
        values.put(Utilidades.CAMPO_GENERO, cmbGenero.getSelectedItem().toString());
        values.put(Utilidades.CAMPO_TIPO, cmbEspecie.getSelectedItem().toString());
        values.put(Utilidades.CAMPO_RAZA, txtRaza.getText().toString());
        values.put(Utilidades.CAMPO_TAMANO, cmbTamano.getSelectedItem().toString());
        values.put(Utilidades.CAMPO_CUMPLEANOS, txtNacimiento.getText().toString());
        values.put(Utilidades.CAMPO_CELO, cmbEsterilizacion.getSelectedItem().toString());
        values.put(Utilidades.CAMPO_IDDUENO, user.getId().toString() );

        Long idResultante=db.insert(Utilidades.TABLA_MASCOTA, Utilidades.CAMPO_ID_MASCOTA, values);

        if(idResultante!=-1) {

            Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_LONG).show();
            db.close();
            Intent miIntent = new Intent(CrearMascotaActivity.this, DetalleUsuarioActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("usuario", user);
            miIntent.putExtras(bundle);
            startActivity(miIntent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Esta mascota ya existe", Toast.LENGTH_LONG).show();
            txtNombreM.setText("");
            txtRaza.setText("");
            txtNacimiento.setText("");
        }
        db.close();
    }
}