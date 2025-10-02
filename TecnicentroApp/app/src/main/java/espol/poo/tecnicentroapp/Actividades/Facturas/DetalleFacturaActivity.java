package espol.poo.tecnicentroapp.Actividades.Facturas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.FacturaM.Factura;
import espol.poo.tecnicentroapp.Modelo.FacturaM.RepositorioFactura;
import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.OrdenServicio;
import espol.poo.tecnicentroapp.Modelo.ServicioM.ItemServicio;
import espol.poo.tecnicentroapp.R;

public class DetalleFacturaActivity extends AppCompatActivity {
    private int codigo;
    private TextView nombreEmpresa,fechaCreacion,periodo,total;
    private Button btnRegresar;
    private RecyclerView recyclerView;
    private DetalleAdapter detalleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detalle_factura);

        codigo = getIntent().getIntExtra("codigo",0);

        Factura f = RepositorioFactura.getByCodigo(codigo);
        ArrayList<OrdenServicio> lista = f.getOrdenes();
        ArrayList<ItemServicio> servicios = getServicios(lista);


        nombreEmpresa = findViewById(R.id.nombreEmpresaF);
        fechaCreacion = findViewById(R.id.fechaCreacionF);
        periodo = findViewById(R.id.periodoF);
        total = findViewById(R.id.totalF);

        mostrarDatos(f);

        recyclerView = findViewById(R.id.rvDetalleFactura);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        detalleAdapter = new DetalleAdapter(servicios);
        recyclerView.setAdapter(detalleAdapter);

        btnRegresar = findViewById(R.id.btnRegresarListadoFacturas);
        btnRegresar.setOnClickListener(v->{
            setResult(Activity.RESULT_OK);
            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detalleFactura), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void mostrarDatos(Factura f){
        String nombre = f.getCliente().getNombre();
        String fecha = f.getFechaCreacion();
        String periodoFf = f.getPeriodoFactura();
        double totalF = f.getTotalPagar();

        nombreEmpresa.setText(nombre);
        fechaCreacion.setText(fecha);
        periodo.setText(periodoFf);
        total.setText("$"+String.valueOf(totalF));
    }
    public ArrayList<ItemServicio> getServicios(ArrayList<OrdenServicio> lista){
        ArrayList<ItemServicio> servicios = new ArrayList<>();
        for(OrdenServicio o : lista){
            servicios.addAll(o.getItemsServicios());
        }return servicios;
    }
}