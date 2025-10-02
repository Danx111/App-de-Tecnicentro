package espol.poo.tecnicentroapp.Actividades.Facturas;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.ServicioM.ItemServicio;
import espol.poo.tecnicentroapp.Modelo.ServicioM.Servicio;
import espol.poo.tecnicentroapp.R;

public class DetalleAdapter extends RecyclerView.Adapter<DetalleAdapter.DetalleViewHolder> {
    private final ArrayList<ItemServicio> itemServicios;

    public DetalleAdapter(ArrayList<ItemServicio> lista){
        this.itemServicios=lista;
    }
    @NonNull
    @Override
    public DetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_servicio_agregado, parent,false);
        return new DetalleViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalleViewHolder holder, int position){
        ItemServicio i = itemServicios.get(position);
        Servicio s = i.getServicio();

        holder.tvServicio.setText(s.getNombre());
        holder.tvCantidad.setText(""+i.getCantidad());
        holder.tvUnitario.setText("$"+String.valueOf(s.getPrecio()));
        holder.tvSubtotal.setText("$"+String.valueOf(i.getSubtotal()));
    }

    @Override
    public int getItemCount(){
        return itemServicios.size();
    }

    static class DetalleViewHolder extends RecyclerView.ViewHolder{
        TextView tvServicio, tvCantidad,tvSubtotal, tvUnitario;

        public DetalleViewHolder(@NonNull View itemView){
            super(itemView);
            tvServicio = itemView.findViewById(R.id.tvNombreServicio);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
            tvUnitario = itemView.findViewById(R.id.tvCostoUnitario);
        }
    }
}
