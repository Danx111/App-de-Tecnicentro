package espol.poo.tecnicentroapp.Actividades.ReportePorTecnico;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.RepositorioOrdenesS;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.ReporteTecnico;
import espol.poo.tecnicentroapp.R;

public class ReporteTecnicoActivity extends AppCompatActivity {
    private Button btnConsultar;
    private EditText etAnio;
    private Spinner spMes;
    private ReporteTecnicoAdapter tecnicoAdapter;
    private RecyclerView recyclerReporteTecnico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.reporte_tecnico);

        btnConsultar = findViewById(R.id.btnConsultarTecnico);
        etAnio = findViewById(R.id.etAnio);
        spMes = findViewById(R.id.spMes);

        desplazarSpinner();

        btnConsultar.setOnClickListener(v -> {
            String anio = etAnio.getText().toString().trim();
            String idxMes = String.valueOf(spMes.getSelectedItemPosition());
            recyclerReporteTecnico = findViewById(R.id.recyclerReporteTecnico);
            recyclerReporteTecnico.setLayoutManager(new LinearLayoutManager(this));
            ArrayList<ReporteTecnico> rt = RepositorioOrdenesS.reportePorTecnico(idxMes,anio);
            tecnicoAdapter = new ReporteTecnicoAdapter(rt);
            recyclerReporteTecnico.setAdapter(tecnicoAdapter);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reportexTecnico), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void desplazarSpinner(){
        Spinner sp = findViewById(R.id.spMes);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(
                this, R.array.meses_array, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
    }

}