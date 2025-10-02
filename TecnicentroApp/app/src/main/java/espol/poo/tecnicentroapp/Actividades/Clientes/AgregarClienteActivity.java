package espol.poo.tecnicentroapp.Actividades.Clientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import espol.poo.tecnicentroapp.Modelo.ClienteM.*;
import espol.poo.tecnicentroapp.R;

public class AgregarClienteActivity extends AppCompatActivity {
    EditText identificacion, nombre, telefono,direccion;
    Spinner tipoCliente;
    Button btnGuardar,btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.agregar_cliente);

        desplazarSpinner();

        identificacion = findViewById(R.id.etIdentificacion);
        nombre = findViewById(R.id.etNombre);
        telefono = findViewById(R.id.etTelefono);
        direccion = findViewById(R.id.etDireccion);
        tipoCliente = findViewById(R.id.spTipoCliente);

        //Guardar
        btnGuardar = findViewById(R.id.btnGuardarCliente);
        btnGuardar.setOnClickListener(v -> {
            agregarCliente();
            setResult(Activity.RESULT_OK);
            finish();

        });
        btnCancelar = findViewById(R.id.regresarCliente);
        btnCancelar.setOnClickListener(v->{
            setResult(Activity.RESULT_OK);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarClientes), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void desplazarSpinner(){
        Spinner sp = findViewById(R.id.spTipoCliente);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(
                this, R.array.tipos_cliente, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
    }

    public void agregarCliente(){
        String identificacionC = identificacion.getText().toString();
        String nombreC = nombre.getText().toString();
        String telefonoC = telefono.getText().toString();
        String direccionC = direccion.getText().toString();
        String tipoC = tipoCliente.getSelectedItem() != null
                ? tipoCliente.getSelectedItem().toString()
                : "";
        TipoCliente tipo = null;
        if (tipoC=="Personal"){
            tipo = TipoCliente.PERSONAL;
        }else{
            tipo = TipoCliente.EMPRESARIAL;
        }
        Cliente nuevo = new Cliente(identificacionC,nombreC,telefonoC,direccionC,tipo);
        RepositorioCliente.agregarCliente(nuevo);
    }

}