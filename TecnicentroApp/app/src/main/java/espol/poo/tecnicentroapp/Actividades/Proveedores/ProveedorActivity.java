package espol.poo.tecnicentroapp.Actividades.Proveedores;

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
import espol.poo.tecnicentroapp.Actividades.Tecnicos.TecnicoAdapter;
import espol.poo.tecnicentroapp.Modelo.ClienteM.RepositorioCliente;
import espol.poo.tecnicentroapp.Modelo.ProveedoresM.Proveedor;
import espol.poo.tecnicentroapp.Modelo.ProveedoresM.RepositorioProveedores;
import espol.poo.tecnicentroapp.R;

public class ProveedorActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProveedores;
    private ProveedorAdapter proveedorAdapter;
    private final ActivityResultLauncher<Intent> agregarProveedorLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // 3) Refrescar lista al volver
                    cargarProveedores();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.proveedor);

        //Boton agregar tencico
        findViewById(R.id.btnAgregarProveedor).setOnClickListener(v -> {
            Intent i = new Intent(this, AgregarProveedorActivity.class);
            agregarProveedorLauncher.launch(i);
        });

        recyclerViewProveedores = findViewById(R.id.recyclerProvedores);
        recyclerViewProveedores.setLayoutManager(new LinearLayoutManager(this));

        // Obtener lista desde el repositorio
        ArrayList<Proveedor> proveedores = RepositorioProveedores.obtenerProveedores();

        proveedorAdapter = new ProveedorAdapter(proveedores);
        recyclerViewProveedores.setAdapter(proveedorAdapter);
        

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pantallaProveedor), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void cargarProveedores() {
        proveedorAdapter = new ProveedorAdapter(RepositorioProveedores.obtenerProveedores());
        recyclerViewProveedores.setAdapter(proveedorAdapter);
    }
}