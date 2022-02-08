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
import com.fran.petfeed.capadatos.Alimento;
import com.fran.petfeed.capadatos.ConexionSQLiteHelper;
import com.fran.petfeed.capadatos.Enfermedad;
import com.fran.petfeed.capadatos.Mascota;
import com.fran.petfeed.capadatos.Usuario;
import com.fran.petfeed.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class DetalleAlimentoActivity extends AppCompatActivity {

    ListView listAlimentosR, listAlimentosT;
    ConexionSQLiteHelper conn;
    TextView textVolver;

    ArrayList<String> listaInfoAR;
    ArrayList<Alimento> listaAlimentosR;

    ArrayList<String> listaInfoAT;
    ArrayList<Alimento> listaAlimentosT;

    ArrayList<String> listaInfoE;
    ArrayList<Enfermedad> listaEnfermedades;

    int IDm;
    String tipo;
    String toxicidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alimento);

        conn = new ConexionSQLiteHelper(this, "bd_petfeed", null, 1);
        listAlimentosR = (ListView) findViewById(R.id.listAlimentosR);
        listAlimentosT = (ListView) findViewById(R.id.listAlimentosT);
        textVolver = (TextView) findViewById(R.id.textVolverAl);

        Bundle objetoEnviado = getIntent().getExtras();
        Mascota mascota = null;
        Usuario user = null;

        if(objetoEnviado != null){
            mascota= (Mascota) objetoEnviado.getSerializable("mascota");
            user = (Usuario) objetoEnviado.getSerializable("usuario");
            IDm = mascota.getId();
            tipo = mascota.getTipo().toString();
        }

        consultarListas();

        ArrayAdapter adaptadorAR = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfoAR);
        listAlimentosR.setAdapter(adaptadorAR);
        ArrayAdapter adaptadorAT = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfoAT);
        listAlimentosT.setAdapter(adaptadorAT);

        Mascota finalMascota = mascota;
        Usuario finalUser = user;
        textVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent miIntent = new Intent(DetalleAlimentoActivity.this, DetalleMascotaActivity.class);
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putSerializable("mascota", finalMascota);
                bundle2.putSerializable("usuario", finalUser);
                miIntent.putExtras(bundle);
                miIntent.putExtras(bundle2);
                startActivity(miIntent);
            }
        });

    }

    private void consultarListas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        listaAlimentosR = new ArrayList<Alimento>();
        listaAlimentosT = new ArrayList<Alimento>();
        listaEnfermedades = new ArrayList<Enfermedad>();
        Enfermedad enfermedad = null;
        Alimento alimentoR = null;
        Alimento alimentoT = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ENFERMEDAD
                +" WHERE "+Utilidades.CAMPO_IDMASCOTA+"="+IDm, null);

        while(cursor.moveToNext()){
            enfermedad = new Enfermedad();
            enfermedad.setId(cursor.getInt(0));
            enfermedad.setNombre(cursor.getString(1));
            enfermedad.setEvitar(cursor.getString(2));
            enfermedad.setAfecta_a(cursor.getString(3));

            listaEnfermedades.add(enfermedad);
        }

        toxicidad="No";
        Cursor cursor1 = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ALIMENTO
                +" WHERE "+Utilidades.CAMPO_TIPOANIMAL+"="+"'"+tipo+"'"
                +" AND "+Utilidades.CAMPO_TOXICIDAD+"="+"'"+toxicidad+"'", null);

        while(cursor1.moveToNext()){
            alimentoR = new Alimento();
            alimentoR.setId(cursor1.getInt(0));
            alimentoR.setNombre(cursor1.getString(1));
            alimentoR.setDescripcion(cursor1.getString(2));
            alimentoR.setTipoAnimal(cursor1.getString(3));
            alimentoR.setToxicidad(cursor1.getString(4));
            alimentoR.setCompuesto(cursor1.getString(5));

            listaAlimentosR.add(alimentoR);
        }

        toxicidad="Si";
        Cursor cursor2 = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ALIMENTO
                +" WHERE "+Utilidades.CAMPO_TIPOANIMAL+"="+"'"+tipo+"'"
                +" AND "+Utilidades.CAMPO_TOXICIDAD+"="+"'"+toxicidad+"'", null);

        while(cursor2.moveToNext()){
            alimentoT = new Alimento();
            alimentoT.setId(cursor2.getInt(0));
            alimentoT.setNombre(cursor2.getString(1));
            alimentoT.setDescripcion(cursor2.getString(2));
            alimentoT.setTipoAnimal(cursor2.getString(3));
            alimentoT.setToxicidad(cursor2.getString(4));
            alimentoT.setCompuesto(cursor2.getString(5));

            listaAlimentosT.add(alimentoT);
        }

        obtenerListas();
    }

    private void obtenerListas() {
        listaInfoE = new ArrayList<String>();
        listaInfoAR = new ArrayList<String>();
        listaInfoAT = new ArrayList<String>();

        for(int i=0; i < listaEnfermedades.size();i++){
            listaInfoE.add(listaEnfermedades.get(i).getNombre()+" - "+listaEnfermedades.get(i).getAfecta_a());
        }
        for(int i=0; i < listaAlimentosR.size();i++){
            listaInfoAR.add(listaAlimentosR.get(i).getNombre()+" - "+listaAlimentosR.get(i).getDescripcion());
        }
        for(int i=0; i < listaAlimentosT.size();i++){
            listaInfoAT.add(listaAlimentosT.get(i).getNombre()+" - "+listaAlimentosT.get(i).getDescripcion());
        }
    }
}