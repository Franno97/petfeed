package com.fran.petfeed.capalogica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fran.petfeed.R;
import com.fran.petfeed.capadatos.ConexionSQLiteHelper;
import com.fran.petfeed.utilidades.Utilidades;

public class CrearUsuarioActivity extends AppCompatActivity {

    EditText txtId, txtNombre, txtTelefono;
    TextView txtVolver;
    Button btnGuardarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        txtId = (EditText) findViewById(R.id.txtRegUserId);
        txtNombre = (EditText) findViewById(R.id.txtRegUserNombre);
        txtTelefono = (EditText) findViewById(R.id.txtRegUserTelefono);
        txtVolver = (TextView) findViewById(R.id.textVolver2);

        btnGuardarUsuario = (Button) findViewById(R.id.btnRegistrarUser);
        btnGuardarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    registrarUsuarios();
                }
                catch (SQLiteConstraintException ex){
                }
            }
        });
        txtVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent miIntent = null;
                miIntent = new Intent(CrearUsuarioActivity.this, MainActivity.class);
                if (miIntent != null){
                    startActivity(miIntent);
                }
            }
        });
    }
    private void registrarUsuarios(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_petfeed", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID, txtId.getText().toString());
            values.put(Utilidades.CAMPO_NOMBRE, txtNombre.getText().toString());
            values.put(Utilidades.CAMPO_TELEFONO, txtTelefono.getText().toString());

            Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);

            if(idResultante!=-1) {
                Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_LONG).show();
                db.close();
                Intent miIntent = new Intent(CrearUsuarioActivity.this, MainActivity.class);
                startActivity(miIntent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Este usuario ya existe", Toast.LENGTH_LONG).show();
                txtId.setText("");
                txtNombre.setText("");
                txtTelefono.setText("");
            }
            db.close();
    }
}