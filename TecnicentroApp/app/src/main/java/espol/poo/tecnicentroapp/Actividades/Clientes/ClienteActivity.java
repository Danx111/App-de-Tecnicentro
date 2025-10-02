package espol.poo.tecnicentroapp.Actividades.Clientes;

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

import espol.poo.tecnicentroapp.Modelo.ClienteM.Cliente;
import espol.poo.tecnicentroapp.Modelo.ClienteM.RepositorioCliente;
import espol.poo.tecnicentroapp.R;

public class ClienteActivity extends AppCompatActivity {
    private RecyclerView recyclerViewClientes;
    private ClienteAdapter clienteAdapter;
    private final ActivityResultLauncher<Intent> agregarClienteLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // 3) Refrescar lista al volver
                    cargarClientes();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cliente);

        findViewById(R.id.btnAgregarCliente).setOnClickListener(v -> {
            Intent i = new Intent(this, AgregarClienteActivity.class);
            agregarClienteLauncher.launch(i);
        });


        recyclerViewClientes = findViewById(R.id.recyclerClientes);
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));

        // Obtener lista desde RepositorioCliente

        ArrayList<Cliente> clientes = RepositorioCliente.obtenerClientes();

        clienteAdapter = new ClienteAdapter(clientes);
        recyclerViewClientes.setAdapter(clienteAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pantallaCliente), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void cargarClientes() {
        clienteAdapter = new ClienteAdapter(RepositorioCliente.obtenerClientes());
        recyclerViewClientes.setAdapter(clienteAdapter);
    }
}