package espol.poo.tecnicentroapp.Actividades.OrdenServicio;

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

public class DetalleOrdenAdapter extends RecyclerView.Adapter<DetalleOrdenAdapter.DOViewHolder> {
    private final ArrayList<ItemServicio> listaServicios;

    public DetalleOrdenAdapter(ArrayList<ItemServicio> lista){
        this.listaServicios = lista;
    }

    @NonNull
    @Override
    public DOViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_servicio_agregado,parent,false);
        return  new DOViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull DOViewHolder holder, int position){
        ItemServicio i = listaServicios.get(position);
        Servicio s = i.getServicio();

        holder.tvServicio.setText(s.getNombre());
        holder.tvCantidad.setText(""+i.getCantidad());
        holder.tvUnitario.setText("$"+String.valueOf(s.getPrecio()));
        holder.tvSubtotal.setText("$"+String.valueOf(i.getSubtotal()));
    }

    @Override
    public int getItemCount(){
        return listaServicios.size();
    }

    static class DOViewHolder extends RecyclerView.ViewHolder{
        TextView tvServicio, tvCantidad, tvSubtotal, tvUnitario;

        public DOViewHolder(@NonNull View itemView){
            super(itemView);
            tvServicio = itemView.findViewById(R.id.tvNombreServicio);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
            tvUnitario = itemView.findViewById(R.id.tvCostoUnitario);
        }

    }
}
