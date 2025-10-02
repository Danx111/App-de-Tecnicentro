package espol.poo.tecnicentroapp.Actividades.OrdenServicio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import espol.poo.tecnicentroapp.Modelo.ClienteM.Cliente;
import espol.poo.tecnicentroapp.Modelo.ClienteM.RepositorioCliente;
import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.OrdenServicio;
import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.RepositorioOrdenesS;
import espol.poo.tecnicentroapp.Modelo.ServicioM.ItemServicio;
import espol.poo.tecnicentroapp.Modelo.ServicioM.RepositorioServicio;
import espol.poo.tecnicentroapp.Modelo.ServicioM.Servicio;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.RepositorioTecnicos;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.Tecnico;
import espol.poo.tecnicentroapp.Modelo.VehiculoM.TipoVehiculo;
import espol.poo.tecnicentroapp.Modelo.VehiculoM.Vehiculo;
import espol.poo.tecnicentroapp.R;

public class AgregarOrdenServicioActivity extends AppCompatActivity {
    private Spinner spCliente, spTecnico,spTipoVehiculo;
    private ArrayAdapter<Cliente> clienteAdapter;
    private ArrayAdapter<Servicio> servicioAdapter;
    private ArrayAdapter<String> tipoVehiculoAdapter;
    private ArrayAdapter<Tecnico> tecnicoAdapter;
    private LinearLayout contenedorServicios;
    private Button btnAgregarServicio, btnGuardar, btnconsultarTotal,btnCancelar;
    private TextView tvTotal;
    private EditText fecha, etplaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.agregar_orden_servicio);

        spCliente  = findViewById(R.id.spCliente);
        spTecnico = findViewById(R.id.spTecnico);
        fecha = findViewById(R.id.etFechaOS);
        etplaca = findViewById(R.id.etPlaca);
        spTipoVehiculo=findViewById(R.id.spTipoVehiculo);

        spinnerClientes();
        spinnerTecnicos();
        spinnerTipoVehiculo();

        tvTotal = findViewById(R.id.tvTotal);
        contenedorServicios = findViewById(R.id.contenedorSelectores);
        btnconsultarTotal = findViewById(R.id.btnConsultarTotal);
        btnAgregarServicio = findViewById(R.id.btnAgregarServicio);
        btnAgregarServicio.setOnClickListener(v ->agregarFilaServicios());
        btnconsultarTotal.setOnClickListener(v-> calcularTotal());
        btnGuardar = findViewById(R.id.btnGuardarOrden);
        btnGuardar.setOnClickListener(v -> {
            guardar();
            setResult(Activity.RESULT_OK);
            finish();
        });
        btnCancelar = findViewById(R.id.regresarOrdenServicio1);
        btnCancelar.setOnClickListener(v->{
            setResult(Activity.RESULT_OK);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarOrdenS), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void agregarFilaServicios(){
        View fila = getLayoutInflater().inflate(R.layout.fila_agregar_servicio, contenedorServicios, false);

        Spinner sp = fila.findViewById(R.id.spServicioRow);
        //EditText etCant = fila.findViewById(R.id.etCantidadRow);
        ImageButton btnQuitar = fila.findViewById(R.id.btnQuitarRow);
        servicioAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, RepositorioServicio.getServicios());
        servicioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(servicioAdapter);

        btnQuitar.setOnClickListener(v -> {
            contenedorServicios.removeView(fila);
        });

        contenedorServicios.addView(fila);
    }

    public void spinnerClientes(){
        clienteAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, RepositorioCliente.obtenerClientes());
        clienteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCliente.setAdapter(clienteAdapter);
    }
    public void spinnerTecnicos(){
        tecnicoAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, RepositorioTecnicos.obtenerTecnicos());
        tecnicoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTecnico.setAdapter(tecnicoAdapter);
    }
    public void spinnerTipoVehiculo(){
        Spinner sp = findViewById(R.id.spTipoVehiculo);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(
                this, R.array.tipoVehiculo, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
    }
    public void guardar(){
        Cliente clienteSel = (Cliente) spCliente.getSelectedItem();
        String fechar = fecha.getText().toString().trim();
        String placa = etplaca.getText().toString().trim();
        String tipoV = (String) spTipoVehiculo.getSelectedItem();
        TipoVehiculo t = null;
        if(tipoV=="Bus"){
            t = TipoVehiculo.BUS;
        }
        if(tipoV=="Automóvil"){
            t = TipoVehiculo.AUTOMOVIL;
        }
        if(tipoV=="Motocicleta"){
            t = TipoVehiculo.MOTOCICLETA;
        }
        Vehiculo vehiculo = new Vehiculo(placa,t);

        Tecnico tecnicoSel = (Tecnico) spTecnico.getSelectedItem();
        //agregar servicios
        OrdenServicio o = new OrdenServicio(clienteSel,vehiculo,fechar,tecnicoSel);
        colocarServicios(o);
        RepositorioOrdenesS.agregarOrden(o);

    }
    public void colocarServicios(OrdenServicio ordenServicio){
        for (int i = 0; i < contenedorServicios.getChildCount(); i++) {
            View vista = contenedorServicios.getChildAt(i);
            if (vista instanceof LinearLayout) {
                LinearLayout servicio = (LinearLayout) vista;
                Spinner sp = (Spinner) servicio.findViewById(R.id.spServicioRow);
                EditText etCant = servicio.findViewById(R.id.etCantidadRow);
                Servicio s = (Servicio) sp.getSelectedItem();
                int cant = 1;
                try {
                    String t = etCant != null ? etCant.getText().toString().trim() : "1";
                    if (!t.isEmpty()) cant = Integer.parseInt(t);
                } catch (Exception ignored) {}
                if (cant < 1) cant = 1;
                ordenServicio.agregarItemServicio(new ItemServicio(s, cant));
            }
        }
    }
    public void calcularTotal(){
        double total = 0.00;
        for (int i = 0; i < contenedorServicios.getChildCount(); i++) {
            View vista = contenedorServicios.getChildAt(i);
            if (vista instanceof LinearLayout) {
                LinearLayout servicio = (LinearLayout) vista;
                Spinner sp = (Spinner) servicio.findViewById(R.id.spServicioRow);
                EditText etCant = servicio.findViewById(R.id.etCantidadRow);
                Servicio s = (Servicio) sp.getSelectedItem();
                int cant = 1;
                try {
                    String t = etCant != null ? etCant.getText().toString().trim() : "1";
                    if (!t.isEmpty()) cant = Integer.parseInt(t);
                } catch (Exception ignored) {}
                if (cant < 1) cant = 1;
                double precio = s.getPrecio();
                total+= (precio*cant);
            }
        }tvTotal.setText("Total: $"+String.valueOf(total));
    }


}