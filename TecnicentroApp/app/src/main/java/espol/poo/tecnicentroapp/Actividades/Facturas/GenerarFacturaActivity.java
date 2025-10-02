package espol.poo.tecnicentroapp.Actividades.Facturas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import espol.poo.tecnicentroapp.Actividades.Clientes.ClienteAdapter;
import espol.poo.tecnicentroapp.Modelo.ClienteM.Cliente;
import espol.poo.tecnicentroapp.Modelo.ClienteM.RepositorioCliente;
import espol.poo.tecnicentroapp.Modelo.FacturaM.Factura;
import espol.poo.tecnicentroapp.Modelo.FacturaM.RepositorioFactura;
import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.OrdenServicio;
import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.RepositorioOrdenesS;
import espol.poo.tecnicentroapp.Modelo.ServicioM.ItemServicio;
import espol.poo.tecnicentroapp.R;

public class GenerarFacturaActivity extends AppCompatActivity {
    private Spinner spmes, spempresa;
    private Button btngenerarFactura,btnGuardarFactura,btnCancelar;
    private EditText anio;
    private ArrayAdapter<Cliente> empresaAdapter;
    private RecyclerView recyclerView;
    private GenerarFacturaAdapter generarFacturaAdapter;
    private TextView tvSubtotal, tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.generar_factura);

        spmes = findViewById(R.id.spMesFact);
        anio = findViewById(R.id.etAnioFact);
        spempresa = findViewById(R.id.spEmpresas);
        btngenerarFactura = findViewById(R.id.btnGenerarFac);
        btnGuardarFactura = findViewById(R.id.btnGuardarFactura);
        tvSubtotal = findViewById(R.id.SubtotalFactura);
        tvTotal = findViewById(R.id.totalfactura);

        desplazarSpinnerMes();//here
        spinnerEmpresas();

        btngenerarFactura.setOnClickListener(v->{
            ArrayList<ItemServicio> lista = obtenerServicios();
            double subtotal = calcularSubtotal(lista);
            tvSubtotal.setText("$"+subtotal);
            double total = subtotal+50.00;
            tvTotal.setText("$"+total);
            recyclerView=findViewById(R.id.rvServicios);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            generarFacturaAdapter = new GenerarFacturaAdapter(lista);
            recyclerView.setAdapter(generarFacturaAdapter);
        });
        btnGuardarFactura.setOnClickListener(v->{
            ArrayList<OrdenServicio> lista = obtenerOS();
            guardarFactura(lista);
            setResult(Activity.RESULT_OK);
            finish();
        });
        btnCancelar = findViewById(R.id.regresarFactura);
        btnCancelar.setOnClickListener(v->{
            setResult(Activity.RESULT_OK);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.generarFacturaPantalla), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void desplazarSpinnerMes(){
        Spinner sp = findViewById(R.id.spMesFact);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(
                this, R.array.meses_array, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);//here
    }
    public void spinnerEmpresas(){
        empresaAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, RepositorioCliente.obtenerClientesEmpresa());
        empresaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spempresa.setAdapter(empresaAdapter);
    }
    public ArrayList<ItemServicio> obtenerServicios(){
        Cliente empresaSel = (Cliente) spempresa.getSelectedItem();
        String idxMes = String.valueOf(spmes.getSelectedItemPosition());
        String anioSel = anio.getText().toString().trim();
        return RepositorioOrdenesS.ordenesEmpresa(empresaSel.getIdentificacion(),idxMes,anioSel);
    }
    public ArrayList<OrdenServicio> obtenerOS(){
        Cliente empresaSel = (Cliente) spempresa.getSelectedItem();
        String idxMes = String.valueOf(spmes.getSelectedItemPosition());
        String anioSel = anio.getText().toString().trim();
        return RepositorioOrdenesS.detalleOrdenes(empresaSel.getIdentificacion(),idxMes,anioSel);
    }
    public void guardarFactura(ArrayList<OrdenServicio> lista){
        Cliente empresaSel = (Cliente) spempresa.getSelectedItem();
        String idxMes = String.valueOf(spmes.getSelectedItemPosition());
        String periodo = idxMes + " 2025";
        Factura f = new Factura(empresaSel,periodo,lista);
        RepositorioFactura.agregarFactura(f);
    }
    public double calcularSubtotal(ArrayList<ItemServicio> lista){
        double subtotal = 0.00;
        for(ItemServicio i : lista){
            subtotal+=i.getSubtotal();
        }return subtotal;
    }

}