package espol.poo.tecnicentroapp.Actividades.OrdenServicio;

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

import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.*;
import espol.poo.tecnicentroapp.R;

public class OrdenesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewOrdenes;
    private OrdenesAdapter ordenesAdapter;
    private final ActivityResultLauncher<Intent> agregarOSLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    cargarOrdenesServicio();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ordenes);

        recyclerViewOrdenes = findViewById(R.id.recyclerOrdenes);
        recyclerViewOrdenes.setLayoutManager(new LinearLayoutManager(this));

        // Obtener datos desde el repositorio
        ArrayList<OrdenServicio> listaOrdenes= RepositorioOrdenesS.obtenerOrdenes();

        // Configurar adapter
        ordenesAdapter = new OrdenesAdapter(listaOrdenes);
        recyclerViewOrdenes.setAdapter(ordenesAdapter);

        findViewById(R.id.btnCrearOrden).setOnClickListener(v -> {
            Intent i = new Intent(this, AgregarOrdenServicioActivity.class);
            agregarOSLauncher.launch(i);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listaOrdenes), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void cargarOrdenesServicio() {
        ordenesAdapter = new OrdenesAdapter(RepositorioOrdenesS.obtenerOrdenes());
        recyclerViewOrdenes.setAdapter(ordenesAdapter);
    }
    @Override protected void onResume() {
        super.onResume();
        cargarOrdenesServicio();
    }
}