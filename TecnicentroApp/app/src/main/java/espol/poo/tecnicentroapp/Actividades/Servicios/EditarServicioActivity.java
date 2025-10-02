package espol.poo.tecnicentroapp.Actividades.Servicios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.ServicioM.RepositorioServicio;
import espol.poo.tecnicentroapp.Modelo.ServicioM.Servicio;
import espol.poo.tecnicentroapp.R;

public class EditarServicioActivity extends AppCompatActivity {
    private String codigo;
    private TextView nombreSer, precioActual;
    private EditText etnuevoPrecio;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.editar_servicio);

        codigo = getIntent().getStringExtra("codigo");

        Servicio s = hallarServicio(codigo);


        nombreSer = findViewById(R.id.nombreServicio);
        precioActual = findViewById(R.id.etPrecioActual);

        etnuevoPrecio = findViewById(R.id.etNuevoPrecioSer);

        mostrarServicio(s);

        btnGuardar = findViewById(R.id.btnGuardarPrecioNuevoSer);
        btnGuardar.setOnClickListener(v->{
            actualizarPrecio(etnuevoPrecio,codigo);
            setResult(Activity.RESULT_OK);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editarServicio), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mostrarServicio(Servicio s){
        nombreSer.setText(s.getNombre());
        precioActual.setText("$"+String.valueOf(s.getPrecio()));
    }

    public Servicio hallarServicio(String cod){
        return RepositorioServicio.getByCodigo(cod);
    }

    public void actualizarPrecio(EditText precioNuevo, String codigo){
        LocalDate hoy = LocalDate.now();
        String fechaFormateada = hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        double precio = Double.parseDouble(precioNuevo.getText().toString());
        RepositorioServicio.actualizarPrecioPorCodigo(codigo,precio,fechaFormateada);
    }

}