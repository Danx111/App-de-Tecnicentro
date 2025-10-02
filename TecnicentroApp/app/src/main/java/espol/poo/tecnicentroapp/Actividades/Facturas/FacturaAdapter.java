package espol.poo.tecnicentroapp.Actividades.Facturas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import espol.poo.tecnicentroapp.Modelo.ClienteM.Cliente;
import espol.poo.tecnicentroapp.Modelo.FacturaM.Factura;
import espol.poo.tecnicentroapp.R;

public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.FViewHolder> {
    private final ArrayList<Factura> listaFactura;

    public FacturaAdapter(ArrayList<Factura> lista){
        this.listaFactura=lista;
    }

    @NonNull
    @Override
    public FViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_factura, parent, false);
        return new FViewHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull FViewHolder holder, int position){
        Factura f = listaFactura.get(position);
        String periodo = f.getPeriodoFactura().split(" ")[0];
        String anio = f.getPeriodoFactura().split(" ")[1];
        ArrayList<String> meses = new ArrayList<>(
                Arrays.asList("Enero ","Febrero","Marzo","Abril","Mayo","Junio",
                        "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre")
        );

        Cliente empresa = f.getCliente();
        holder.tvnombre.setText(empresa.getNombre());
        holder.tvFecha.setText(f.getFechaCreacion());
        holder.tvPeriodo.setText(meses.get(Integer.parseInt(periodo)-1)+" "+anio);
        holder.tvtotal.setText("$"+String.valueOf(f.getTotalPagar()));

        holder.btnMasDetalle.setOnClickListener(v->{
            Context context = v.getContext();
            Intent intent = new Intent(context, DetalleFacturaActivity.class);
            intent.putExtra("codigo",f.getCodigo());
            context.startActivity(intent);
        });

    }
    @Override
    public int getItemCount(){
        return listaFactura.size();
    }

    static class FViewHolder extends RecyclerView.ViewHolder{
        TextView tvnombre, tvFecha, tvPeriodo, tvtotal;
        Button btnMasDetalle;
        public FViewHolder(@NonNull View itemView){
            super(itemView);
            tvnombre = itemView.findViewById(R.id.tvEmpresa);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvPeriodo = itemView.findViewById(R.id.tvPeriodo);
            tvtotal = itemView.findViewById(R.id.tvTotal);
            btnMasDetalle = itemView.findViewById(R.id.btnDetalleFactura);

        }
    }



}
