package com.fran.petfeed.capalogica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fran.petfeed.R;
import com.fran.petfeed.capadatos.ConexionSQLiteHelper;
import com.fran.petfeed.capadatos.Mascota;
import com.fran.petfeed.capadatos.Usuario;
import com.fran.petfeed.utilidades.Utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetalleUsuarioActivity extends AppCompatActivity {

    TextView campoId, campoNombre, campoTelefono, textVolver;
    ListView listMascota;

    ArrayList<String> listaInformacion;
    ArrayList<Mascota> listaMascotas;
    FloatingActionButton btnAgregarM;

    int ID;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        conn = new ConexionSQLiteHelper(this, "bd_petfeed", null, 1);

        listMascota = (ListView) findViewById(R.id.listMascotas);
        campoId = (TextView) findViewById(R.id.textId);
        campoNombre = (TextView) findViewById(R.id.textNombreU);
        campoTelefono = (TextView) findViewById(R.id.textTelefono);
        btnAgregarM = (FloatingActionButton) findViewById(R.id.btnAgregarM);
        textVolver = (TextView) findViewById(R.id.textVolver3);

        Bundle objetoEnviado = getIntent().getExtras();
        Usuario user = null;


        if(objetoEnviado != null){
            user= (Usuario) objetoEnviado.getSerializable("usuario");
            campoId.setText(user.getId().toString());
            ID = user.getId();
            campoNombre.setText(user.getNombre().toString());
            campoTelefono.setText(user.getTelefono().toString());
        }

        consultarListaPersonas();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listMascota.setAdapter(adaptador);

        Usuario finalUser = user;
        btnAgregarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent miIntent = null;
                miIntent = new Intent(DetalleUsuarioActivity.this, CrearMascotaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", finalUser);
                miIntent.putExtras(bundle);
                if (miIntent != null){
                    startActivity(miIntent);
                }
            }
        });
        listMascota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Mascota mascota = listaMascotas.get(pos);
                Intent intent = new Intent(DetalleUsuarioActivity.this, DetalleMascotaActivity.class);
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("usuario", finalUser);
                bundle.putSerializable("mascota", mascota);
                intent.putExtras(bundle);
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });
        textVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent miIntent = null;
                miIntent = new Intent(DetalleUsuarioActivity.this, MainActivity.class);
                if (miIntent != null){
                    startActivity(miIntent);
                }
            }
        });
    }

    private void consultarListaPersonas(){
        SQLiteDatabase db = conn.getReadableDatabase();


        Mascota mascota = null;
        listaMascotas = new ArrayList<Mascota>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA
                +" WHERE "+Utilidades.CAMPO_IDDUENO+"="+ID, null);

        while(cursor.moveToNext()){
            mascota = new Mascota();
            mascota.setId(cursor.getInt(0));
            mascota.setNombre(cursor.getString(1));
            mascota.setGenero(cursor.getString(2));
            mascota.setTipo(cursor.getString(3));
            mascota.setRaza(cursor.getString(4));
            mascota.setTamano(cursor.getString(5));
            mascota.setCumpleanos(cursor.getString(6));
            mascota.setCelo(cursor.getString(7));

            listaMascotas.add(mascota);
            //Toast.makeText(getApplicationContext(), "Se esta ingresando datos", Toast.LENGTH_LONG).show();
        }
        obtenerLista();

    }
    private void obtenerLista(){
        listaInformacion = new ArrayList<String>();

        for(int i=0; i < listaMascotas.size();i++){
            listaInformacion.add(listaMascotas.get(i).getNombre()+" - "+listaMascotas.get(i).getTipo());
        }
    }
}