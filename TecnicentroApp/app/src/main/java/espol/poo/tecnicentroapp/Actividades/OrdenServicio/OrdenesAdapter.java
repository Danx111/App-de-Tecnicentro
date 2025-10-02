package espol.poo.tecnicentroapp.Actividades.OrdenServicio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.OrdenServicio;
import espol.poo.tecnicentroapp.R;

import espol.poo.tecnicentroapp.Modelo.ClienteM.Cliente;
import espol.poo.tecnicentroapp.Modelo.VehiculoM.Vehiculo;

public class OrdenesAdapter extends RecyclerView.Adapter<OrdenesAdapter.OrdenViewHolder> {

    private final ArrayList<OrdenServicio> listaOrdenes;

    public OrdenesAdapter(ArrayList<OrdenServicio> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;

    }

    @NonNull
    @Override
    public OrdenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_orden_servicio, parent, false);
        return new OrdenViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdenViewHolder holder, int position) {
        OrdenServicio orden = listaOrdenes.get(position);

        Cliente  c = orden.getCliente();
        Vehiculo v = orden.getVehiculo();

        holder.tvCliente.setText(c.getNombre());
        holder.tvFecha.setText(orden.getFecha());
        holder.tvPlaca.setText(v.getPlaca());
        holder.tvTotal.setText("$" + String.valueOf(orden.calcularTotal()));

        holder.btnDetalle.setOnClickListener(view -> {
            Context ctx = view.getContext();
            Intent intent = new Intent(ctx, DetalleOrdenActivity.class);
            intent.putExtra("codigo", orden.getCodigo()); // Orden debe implementar Serializable o Parcelable
            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaOrdenes.size();
    }

    static class OrdenViewHolder extends RecyclerView.ViewHolder {
        Button btnDetalle;
        TextView tvCliente, tvFecha, tvPlaca, tvTotal;

        public OrdenViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvFecha   = itemView.findViewById(R.id.tvFecha);
            tvPlaca   = itemView.findViewById(R.id.tvPlaca);
            tvTotal   = itemView.findViewById(R.id.tvTotal);
            btnDetalle = itemView.findViewById(R.id.btnDetalle);
        }
    }

}
