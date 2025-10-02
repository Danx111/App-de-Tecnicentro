package espol.poo.tecnicentroapp.Actividades.Tecnicos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import espol.poo.tecnicentroapp.Modelo.TecnicosM.RepositorioTecnicos;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.Tecnico;
import espol.poo.tecnicentroapp.R;

public class AgregarTecnicosActivity extends AppCompatActivity {
    EditText identificacion, nombre, telefono,especialidad;
    Button btnGuardar,btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.agregar_tecnicos);

        identificacion = findViewById(R.id.etIdentificacionTec);
        nombre = findViewById(R.id.etNombreTec);
        telefono = findViewById(R.id.etTelefonoTec);
        especialidad = findViewById(R.id.etEspecialidadTec);

        btnGuardar = findViewById(R.id.btnGuardarTecnico);
        btnGuardar.setOnClickListener(v -> {
            guardarTecnico();
            setResult(Activity.RESULT_OK);
            finish();
        });
        btnCancelar = findViewById(R.id.regresarTecnicos);
        btnCancelar.setOnClickListener(v->{
            setResult(Activity.RESULT_OK);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarTecnico), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void guardarTecnico(){
        String identificacionT = identificacion.getText().toString();
        String nombreT = nombre.getText().toString();
        String telefonoT = telefono.getText().toString();
        String especialidadT = especialidad.getText().toString();

        Tecnico t = new Tecnico(identificacionT,nombreT,telefonoT,especialidadT);
        RepositorioTecnicos.agregarTecnico(t);
    }
}