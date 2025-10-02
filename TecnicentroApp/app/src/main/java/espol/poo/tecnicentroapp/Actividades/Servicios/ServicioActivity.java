package espol.poo.tecnicentroapp.Actividades.Servicios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.tecnicentroapp.Actividades.Clientes.AgregarClienteActivity;
import espol.poo.tecnicentroapp.Actividades.Clientes.ClienteAdapter;
import espol.poo.tecnicentroapp.Modelo.ClienteM.RepositorioCliente;
import espol.poo.tecnicentroapp.Modelo.ServicioM.RepositorioServicio;
import espol.poo.tecnicentroapp.Modelo.ServicioM.Servicio;
import espol.poo.tecnicentroapp.R;

public class ServicioActivity extends AppCompatActivity {
    private RecyclerView recyclerViewServicios;
    private ServicioAdapter servicioAdapter;
    private Button btnAgregarServicio;
    private final ActivityResultLauncher<Intent> agregarServicioLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    cargarServicios();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.servicio);
        findViewById(R.id.btnAgregarServicio).setOnClickListener(v -> {
            Intent i = new Intent(this, AgregarServicioActivity.class);
            agregarServicioLauncher.launch(i);
        });

        recyclerViewServicios = findViewById(R.id.recyclerServicios);
        recyclerViewServicios.setLayoutManager(new LinearLayoutManager(this));

        //Obtener lista desde el repositorio
        ArrayList<Servicio> servicios = RepositorioServicio.getServicios();

        servicioAdapter = new ServicioAdapter(servicios);
        recyclerViewServicios.setAdapter(servicioAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.servicio), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void cargarServicios() {
        servicioAdapter = new ServicioAdapter(RepositorioServicio.getServicios());
        recyclerViewServicios.setAdapter(servicioAdapter);
    }
    @Override protected void onResume() {
        super.onResume();
        cargarServicios();
    }
}