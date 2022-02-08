package com.fran.petfeed.capalogica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fran.petfeed.R;
import com.fran.petfeed.capadatos.ConexionSQLiteHelper;
import com.fran.petfeed.capadatos.Enfermedad;
import com.fran.petfeed.capadatos.Mascota;
import com.fran.petfeed.capadatos.Usuario;
import com.fran.petfeed.utilidades.Utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetalleEnfemedadActivity extends AppCompatActivity {

    TextView textNombreM, textVolver;
    ConexionSQLiteHelper conn;
    ListView listEnfermedad;

    ArrayList<String> listaInformacion;
    ArrayList<Enfermedad> listaEnfermedades;
    FloatingActionButton btnAgregarE;
    int IDm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_enfemedad);

        conn = new ConexionSQLiteHelper(this, "bd_petfeed", null, 1);

        listEnfermedad = (ListView) findViewById(R.id.listEnfermedades);
        textNombreM = (TextView) findViewById(R.id.textNombreME);
        textVolver = (TextView) findViewById(R.id.textVolverE);
        btnAgregarE = (FloatingActionButton) findViewById(R.id.btnAgregarE);

        Bundle objetoEnviado = getIntent().getExtras();
        Mascota mascota = null;

        if(objetoEnviado != null){
            mascota= (Mascota) objetoEnviado.getSerializable("mascota");
            textNombreM.setText(mascota.getNombre().toString());
            IDm = mascota.getId();
        }
        consultarListaPersonas();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listEnfermedad.setAdapter(adaptador);

        Mascota finalMascota = mascota;
        textVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle objetoEnviado = getIntent().getExtras();
                Usuario user = null;
                user= (Usuario) objetoEnviado.getSerializable("usuario");
                Intent miIntent = new Intent(DetalleEnfemedadActivity.this, DetalleMascotaActivity.class);
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putSerializable("mascota", finalMascota);
                bundle2.putSerializable("usuario", user);
                miIntent.putExtras(bundle);
                miIntent.putExtras(bundle2);
                startActivity(miIntent);
            }
        });
        btnAgregarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle objetoEnviado = getIntent().getExtras();
                Usuario user = null;
                user= (Usuario) objetoEnviado.getSerializable("usuario");
                Intent miIntent = new Intent(DetalleEnfemedadActivity.this, CrearEnfermedadActivity.class);
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putSerializable("mascota", finalMascota);
                bundle2.putSerializable("usuario", user);
                miIntent.putExtras(bundle);
                miIntent.putExtras(bundle2);
                startActivity(miIntent);
            }
        });
    }

    private void consultarListaPersonas(){
        SQLiteDatabase db = conn.getReadableDatabase();

        Enfermedad enfermedad = null;
        listaEnfermedades = new ArrayList<Enfermedad>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ENFERMEDAD
                +" WHERE "+Utilidades.CAMPO_IDMASCOTA+"="+IDm, null);

        while(cursor.moveToNext()){
            enfermedad = new Enfermedad();
            enfermedad.setId(cursor.getInt(0));
            enfermedad.setNombre(cursor.getString(1));
            enfermedad.setEvitar(cursor.getString(2));
            enfermedad.setAfecta_a(cursor.getString(3));

            listaEnfermedades.add(enfermedad);
            //Toast.makeText(getApplicationContext(), "Se esta ingresando datos", Toast.LENGTH_LONG).show();
        }
        obtenerLista();

    }
    private void obtenerLista(){
        listaInformacion = new ArrayList<String>();

        for(int i=0; i < listaEnfermedades.size();i++){
            listaInformacion.add(listaEnfermedades.get(i).getNombre()+" - "+listaEnfermedades.get(i).getAfecta_a());
        }
    }
}