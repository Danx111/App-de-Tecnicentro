package espol.poo.tecnicentroapp.Actividades.Tecnicos;

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
import espol.poo.tecnicentroapp.Modelo.TecnicosM.RepositorioTecnicos;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.Tecnico;
import espol.poo.tecnicentroapp.R;

public class TecnicoActivity extends AppCompatActivity {
    private RecyclerView recyclerViewTecnicos;
    private TecnicoAdapter tecnicoAdapter;
    private final ActivityResultLauncher<Intent> agregarTecnicoLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // 3) Refrescar lista al volver
                    cargarTecnicos();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tecnicos);

        //Boton agregar tencico
        findViewById(R.id.btnAgregarTecnico).setOnClickListener(v -> {
            Intent i = new Intent(this, AgregarTecnicosActivity.class);
            agregarTecnicoLauncher.launch(i);
        });

        recyclerViewTecnicos = findViewById(R.id.recyclerTecnicos);
        recyclerViewTecnicos.setLayoutManager(new LinearLayoutManager(this));

        // Obtener lista desde el repositorio
        ArrayList<Tecnico> tecnicos = RepositorioTecnicos.obtenerTecnicos();

        tecnicoAdapter = new TecnicoAdapter(tecnicos);
        recyclerViewTecnicos.setAdapter(tecnicoAdapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pantallaTecnicos), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void cargarTecnicos() {
        tecnicoAdapter = new TecnicoAdapter(RepositorioTecnicos.obtenerTecnicos());
        recyclerViewTecnicos.setAdapter(tecnicoAdapter);
    }
}