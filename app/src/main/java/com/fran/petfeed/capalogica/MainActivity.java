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
import android.widget.Toast;

import com.fran.petfeed.R;
import com.fran.petfeed.capadatos.ConexionSQLiteHelper;
import com.fran.petfeed.capadatos.Usuario;
import com.fran.petfeed.utilidades.Utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listUsuarios;

    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;

    ConexionSQLiteHelper conn;

    FloatingActionButton btnAgregarU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_petfeed", null, 1);

        listUsuarios = (ListView) findViewById(R.id.listUsuarios);
        btnAgregarU = (FloatingActionButton) findViewById(R.id.btnAgregarU);

        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        listUsuarios.setAdapter(adaptador);

        btnAgregarU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent miIntent = null;
                miIntent = new Intent(MainActivity.this, CrearUsuarioActivity.class);
                if (miIntent != null){
                    startActivity(miIntent);
                }
            }
        });

        listUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Usuario user = listaUsuarios.get(pos);
                Intent intent = new Intent(MainActivity.this, DetalleUsuarioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;
        listaUsuarios = new ArrayList<Usuario>();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);

            while(cursor.moveToNext()){
                usuario=new Usuario();
                usuario.setId(cursor.getInt(0));
                usuario.setNombre(cursor.getString(1));
                usuario.setTelefono(cursor.getString(2));

                listaUsuarios.add(usuario);
                //Toast.makeText(getApplicationContext(), "Se esta ingresando datos", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "No hay usuarios registrados", Toast.LENGTH_SHORT).show();
        }


        obtenerlista();
    }

    private void obtenerlista() {
        listaInformacion = new ArrayList<String>();

        for(int i=0; i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+" - "+listaUsuarios.get(i).getNombre());

        }
    }
}