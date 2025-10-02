package espol.poo.tecnicentroapp.Actividades.OrdenServicio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.OrdenServicio;
import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.RepositorioOrdenesS;
import espol.poo.tecnicentroapp.Modelo.ServicioM.ItemServicio;
import espol.poo.tecnicentroapp.Modelo.VehiculoM.RepositorioVehiculo;
import espol.poo.tecnicentroapp.R;


public class DetalleOrdenActivity extends AppCompatActivity {
    private int codigo;
    private TextView nombre,placa, fecha, tecnico;
    private Button btnregresar;
    private RecyclerView recyclerView;
    private DetalleOrdenAdapter detalleOrdenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detalle_orden);

        codigo = getIntent().getIntExtra("codigo",0);

        OrdenServicio o = RepositorioOrdenesS.getByCodigo(codigo);

        nombre = findViewById(R.id.nombreClienteDOS);
        placa = findViewById(R.id.placaDOS);
        fecha = findViewById(R.id.fechaDOS);
        tecnico = findViewById(R.id.tecnicoDOS);

        if (o.equals(null)){
            Toast.makeText(this, "objeto null", Toast.LENGTH_SHORT).show();
        }else{
            mostrarDatos(o);
        }
        ArrayList<ItemServicio> l = o.getItemsServicios();
        recyclerView = findViewById(R.id.recyclerServiciosOrden);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        detalleOrdenAdapter = new DetalleOrdenAdapter(l);
        recyclerView.setAdapter(detalleOrdenAdapter);

        btnregresar = findViewById(R.id.btnRegresarListadoOS);
        btnregresar.setOnClickListener(v->{
            setResult(Activity.RESULT_OK);
            finish();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detalleOrden), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mostrarDatos(OrdenServicio o){
        String nombreCliente = o.getCliente().getNombre();
        String placaOrden = o.getVehiculo().getPlaca();
        String tecnicoNombre = o.getTecnico().getNombre();
        nombre.setText(nombreCliente);
        placa.setText(placaOrden);
        fecha.setText(o.getFecha());
        tecnico.setText(tecnicoNombre);
    }

}