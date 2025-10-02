package espol.poo.tecnicentroapp.Actividades.Proveedores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import espol.poo.tecnicentroapp.Modelo.ProveedoresM.Proveedor;
import espol.poo.tecnicentroapp.Modelo.ProveedoresM.RepositorioProveedores;
import espol.poo.tecnicentroapp.R;

public class AgregarProveedorActivity extends AppCompatActivity {
    EditText identificacion, nombre, telefono, descripcion;
    Button btnGuardar,btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.agregar_proveedor);

        identificacion = findViewById(R.id.etIdentificacionPro);
        nombre = findViewById(R.id.etNombrePro);
        telefono = findViewById(R.id.etTelefonoPro);
        descripcion = findViewById(R.id.etDescripcionPro);
        btnGuardar = findViewById(R.id.btnGuardarProveedor);
        btnGuardar.setOnClickListener(v -> {
            agregarProveedor();
            setResult(Activity.RESULT_OK);
            finish();
        });

        btnCancelar = findViewById(R.id.regresarProveedor1);
        btnCancelar.setOnClickListener(v->{
            setResult(Activity.RESULT_OK);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarProveedor), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void  agregarProveedor(){
        String identificacionP = identificacion.getText().toString();
        String nombreP = nombre.getText().toString();
        String telefonoP = telefono.getText().toString();
        String descripcionP = descripcion.getText().toString();

        Proveedor p = new Proveedor(identificacionP,nombreP,telefonoP,descripcionP);
        RepositorioProveedores.agregarProveedor(p);
    }
}