package espol.poo.tecnicentroapp.Actividades.Facturas;

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
import espol.poo.tecnicentroapp.Modelo.FacturaM.Factura;
import espol.poo.tecnicentroapp.Modelo.FacturaM.RepositorioFactura;
import espol.poo.tecnicentroapp.R;

public class FacturaActivity extends AppCompatActivity {
    private Button btnGenerarFactura;
    private RecyclerView recyclerViewFactura;
    private FacturaAdapter facturaAdapter;
    private final ActivityResultLauncher<Intent> agregarfacturaLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // 3) Refrescar lista al volver
                    cargarFacturas();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.factura);

        findViewById(R.id.btnGenerarFactura).setOnClickListener(v -> {
            Intent i = new Intent(this, GenerarFacturaActivity.class);
            agregarfacturaLauncher.launch(i);
        });

        recyclerViewFactura = findViewById(R.id.recyclerFacturas);
        recyclerViewFactura.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Factura> lista = RepositorioFactura.obtenerFacturas();

        facturaAdapter = new FacturaAdapter(lista);
        recyclerViewFactura.setAdapter(facturaAdapter);

        /*btnGenerarFactura = findViewById(R.id.btnGenerarFactura);
        btnGenerarFactura.setOnClickListener(v->{
            Intent intent = new Intent(FacturaActivity.this, GenerarFacturaActivity.class);
            startActivity(intent);
        });*/
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.factura), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void cargarFacturas() {
        facturaAdapter = new FacturaAdapter(RepositorioFactura.obtenerFacturas());
        recyclerViewFactura.setAdapter(facturaAdapter);
    }
    @Override protected void onResume() {
        super.onResume();
        cargarFacturas();
    }
}