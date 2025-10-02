package espol.poo.tecnicentroapp.Actividades.Servicios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import espol.poo.tecnicentroapp.Modelo.ServicioM.RepositorioServicio;
import espol.poo.tecnicentroapp.Modelo.ServicioM.Servicio;
import espol.poo.tecnicentroapp.R;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class AgregarServicioActivity extends AppCompatActivity {
    EditText nombre, precio;
    Button btnguardar, btncancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.agregar_servicio);

        nombre = findViewById(R.id.etNombreServ);
        precio = findViewById(R.id.etPrecioServ);

        btnguardar = findViewById(R.id.btnGuardarServicio);
        btnguardar.setOnClickListener(v ->{
            agregarServicio();
            setResult(Activity.RESULT_OK);
            finish();
        });

        btncancelar = findViewById(R.id.regresarServicio2);
        btncancelar.setOnClickListener(v ->{
            setResult(Activity.RESULT_OK);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarServicio), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void agregarServicio(){
        String nombreSer = nombre.getText().toString();
        double precioSer = Double.parseDouble(precio.getText().toString());
        LocalDate hoy = LocalDate.now();
        String fechaFormateada = hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        //arreglar para que haya un identificador que se incremente
        Servicio s = new Servicio(nombreSer,precioSer,fechaFormateada);
        RepositorioServicio.agregarServicio(s);
    }
}