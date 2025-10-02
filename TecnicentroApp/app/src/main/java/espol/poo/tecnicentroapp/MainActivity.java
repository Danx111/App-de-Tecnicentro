package espol.poo.tecnicentroapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import espol.poo.tecnicentroapp.Actividades.Clientes.ClienteActivity;
import espol.poo.tecnicentroapp.Actividades.Facturas.FacturaActivity;
import espol.poo.tecnicentroapp.Actividades.OrdenServicio.OrdenesActivity;
import espol.poo.tecnicentroapp.Actividades.Proveedores.ProveedorActivity;
import espol.poo.tecnicentroapp.Actividades.ReportePorServicio.ReporteServicioActivity;
import espol.poo.tecnicentroapp.Actividades.ReportePorTecnico.ReporteTecnicoActivity;
import espol.poo.tecnicentroapp.Actividades.Servicios.ServicioActivity;
import espol.poo.tecnicentroapp.Actividades.Tecnicos.TecnicoActivity;
import espol.poo.tecnicentroapp.Modelo.ClienteM.RepositorioCliente;
import espol.poo.tecnicentroapp.Modelo.FacturaM.RepositorioFactura;
import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.RepositorioOrdenesS;
import espol.poo.tecnicentroapp.Modelo.ProveedoresM.RepositorioProveedores;
import espol.poo.tecnicentroapp.Modelo.ServicioM.RepositorioServicio;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.RepositorioTecnicos;
import espol.poo.tecnicentroapp.Modelo.VehiculoM.RepositorioVehiculo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RepositorioCliente.cargarDatos();
        RepositorioTecnicos.cargarDatos();
        RepositorioProveedores.cargarDatos();
        RepositorioServicio.cargarDatos();
        RepositorioVehiculo.cargarDatos();
        RepositorioOrdenesS.cargarDatos();
        RepositorioFactura.cargarDatos();

        //Boton de cliente
        ImageButton btnClientes = findViewById(R.id.btnClientes);
        btnClientes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
            startActivity(intent);
        });
        //Boton de proveedor
        ImageButton btnProveedor = findViewById(R.id.btnProveedores);
        btnProveedor.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProveedorActivity.class);
            startActivity(intent);
        });
        //Boton de Tecnicos
        ImageButton btnTecnico = findViewById(R.id.btnTecnicos);
        btnTecnico.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TecnicoActivity.class);
            startActivity(intent);
        });
        //Boton facturas
        ImageButton btnFacturas = findViewById(R.id.btnFacturas);
        btnFacturas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FacturaActivity.class);
            startActivity(intent);
        });

        //Boton Servicios
        ImageButton btnServicios = findViewById(R.id.btnServicios);
        btnServicios.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ServicioActivity.class);
            startActivity(intent);
        });

        //Boton Ordenes de servicio
        ImageButton btnOrdenesServicio = findViewById(R.id.btnOrdenesServicio);
        btnOrdenesServicio.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrdenesActivity.class);
            startActivity(intent);
        });

        //Boton Reporte por tecnico
        ImageButton btnReporteTecnico = findViewById(R.id.btnReporteTecnicos);
        btnReporteTecnico.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReporteTecnicoActivity.class);
            startActivity(intent);
        });

        //Boton Reporte por servicio
        ImageButton btnReporteServicio = findViewById(R.id.btnReporteServicio);
        btnReporteServicio.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReporteServicioActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarProveedor), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}