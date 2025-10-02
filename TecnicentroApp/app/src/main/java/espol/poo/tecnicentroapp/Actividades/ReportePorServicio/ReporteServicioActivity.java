package espol.poo.tecnicentroapp.Actividades.ReportePorServicio;

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
import espol.poo.tecnicentroapp.Modelo.ServicioM.ReporteServicio;
import espol.poo.tecnicentroapp.R;

public class ReporteServicioActivity extends AppCompatActivity {
    private Button btnConsultar;
    private EditText etAnio;
    private Spinner spMes;
    private ReporteServicioAdapter servicioAdapter;
    private RecyclerView recyclerReporteServicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.reporte_servicio);

        btnConsultar = findViewById(R.id.btnConsultarServicio);
        etAnio = findViewById(R.id.etAnioS);
        spMes = findViewById(R.id.spMesS);

        desplazarSpinner();

        btnConsultar.setOnClickListener(v ->{
            String anio = etAnio.getText().toString().trim();
            String idxMes = String.valueOf(spMes.getSelectedItemPosition());
            recyclerReporteServicio = findViewById(R.id.recyclerReporteServicio);
            recyclerReporteServicio.setLayoutManager(new LinearLayoutManager(this));
            ArrayList<ReporteServicio> rs = RepositorioOrdenesS.reportePorServicio(idxMes,anio);
            servicioAdapter = new ReporteServicioAdapter(rs);
            recyclerReporteServicio.setAdapter(servicioAdapter);

        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reportexServicio), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void desplazarSpinner(){
        Spinner sp = findViewById(R.id.spMesS);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(
                this, R.array.meses_array, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
    }
}