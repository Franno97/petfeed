package com.fran.petfeed.capalogica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fran.petfeed.R;
import com.fran.petfeed.capadatos.ConexionSQLiteHelper;
import com.fran.petfeed.capadatos.Mascota;
import com.fran.petfeed.capadatos.Usuario;

public class DetalleMascotaActivity extends AppCompatActivity {

    TextView textNombre, textEspecie, textGenero, textTamano, textEsterilizacion, textRaza, textNacimiento, textVolver;

    ConexionSQLiteHelper conn;

    Button btnEnfermedades, btnAlimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        conn = new ConexionSQLiteHelper(this, "bd_petfeed", null, 1);

        textNombre = (TextView) findViewById(R.id.textNombreM);
        textEspecie = (TextView) findViewById(R.id.textEspecieM);
        textGenero = (TextView) findViewById(R.id.textGeneroM);
        textTamano = (TextView) findViewById(R.id.textTamanoM);
        textEsterilizacion = (TextView) findViewById(R.id.textEsteriizacion);
        textRaza = (TextView) findViewById(R.id.textRazaM);
        textNacimiento = (TextView) findViewById(R.id.textNacimientoM);
        textVolver = (TextView) findViewById(R.id.textVolver5);
        btnEnfermedades= (Button) findViewById(R.id.btnEnfermedades);
        btnAlimentos = (Button) findViewById(R.id.btnAlimentos);

        Bundle objetoEnviado = getIntent().getExtras();
        Mascota mascota = null;

        if(objetoEnviado != null){
            mascota= (Mascota) objetoEnviado.getSerializable("mascota");

            textNombre.setText(mascota.getNombre().toString());

            textEspecie.setText(mascota.getTipo().toString());

            textGenero.setText(mascota.getGenero().toString());
            textTamano.setText(mascota.getTamano().toString());
            textEsterilizacion.setText(mascota.getCelo().toString());
            textRaza.setText(mascota.getRaza().toString());
            textNacimiento.setText(mascota.getCumpleanos().toString());

            textVolver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle objetoEnviado = getIntent().getExtras();
                    Usuario user = null;
                    user= (Usuario) objetoEnviado.getSerializable("usuario");
                    Intent miIntent = new Intent(DetalleMascotaActivity.this, DetalleUsuarioActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usuario", user);
                    miIntent.putExtras(bundle);
                    startActivity(miIntent);
                }
            });
            Mascota finalMascota = mascota;
            btnEnfermedades.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle objetoEnviado = getIntent().getExtras();
                    Usuario user = null;
                    user= (Usuario) objetoEnviado.getSerializable("usuario");
                    Intent miIntent = new Intent(DetalleMascotaActivity.this, DetalleEnfemedadActivity.class);
                    Bundle bundle = new Bundle();
                    Bundle bundle2 = new Bundle();
                    bundle.putSerializable("usuario", user);
                    bundle2.putSerializable("mascota", finalMascota);
                    miIntent.putExtras(bundle);
                    miIntent.putExtras(bundle2);
                    startActivity(miIntent);
                }
            }));

            btnAlimentos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle objetoEnviado = getIntent().getExtras();
                    Usuario user = null;
                    user= (Usuario) objetoEnviado.getSerializable("usuario");
                    Intent miIntent = new Intent(DetalleMascotaActivity.this, DetalleAlimentoActivity.class);
                    Bundle bundle = new Bundle();
                    Bundle bundle2 = new Bundle();
                    bundle.putSerializable("usuario", user);
                    bundle2.putSerializable("mascota", finalMascota);
                    miIntent.putExtras(bundle);
                    miIntent.putExtras(bundle2);
                    startActivity(miIntent);
                }
            });
        }
    }
}