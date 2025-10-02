package espol.poo.tecnicentroapp.Actividades.Facturas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.ServicioM.ItemServicio;
import espol.poo.tecnicentroapp.Modelo.ServicioM.Servicio;
import espol.poo.tecnicentroapp.R;

public class GenerarFacturaAdapter extends RecyclerView.Adapter<GenerarFacturaAdapter.GFViewHodler> {
    private final ArrayList<ItemServicio> itemServicios;

    private final DecimalFormat money = new DecimalFormat("$#,##0.00");

    public GenerarFacturaAdapter( ArrayList<ItemServicio> listaOrdenes) {
        this.itemServicios = listaOrdenes;

    }

    @NonNull
    @Override
    public GFViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_servicio_agregado, parent, false);
        return new GFViewHodler(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull GFViewHodler holder, int position) {
        ItemServicio i = itemServicios.get(position);
        Servicio s = i.getServicio();

        holder.tvServicio.setText(s.getNombre());
        holder.tvCantidad.setText(""+i.getCantidad());
        holder.tvUnitario.setText("$"+String.valueOf(s.getPrecio()));
        holder.tvSubtotal.setText("$"+String.valueOf(i.getSubtotal()));

    }

    @Override
    public int getItemCount() {
        return itemServicios.size();
    }

    static class GFViewHodler extends RecyclerView.ViewHolder {
        TextView tvServicio, tvCantidad, tvSubtotal, tvUnitario;

        public GFViewHodler(@NonNull View itemView){
            super(itemView);
            tvServicio = itemView.findViewById(R.id.tvNombreServicio);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
            tvUnitario = itemView.findViewById(R.id.tvCostoUnitario);
        }
    }

}
