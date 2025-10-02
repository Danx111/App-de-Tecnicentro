package espol.poo.tecnicentroapp.Actividades.Clientes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import espol.poo.tecnicentroapp.Modelo.ClienteM.*;
import espol.poo.tecnicentroapp.R;

import java.util.ArrayList;


public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private final ArrayList<Cliente> listaClientes;

    public ClienteAdapter(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = listaClientes.get(position);

        holder.tvIdentificacion.setText("Identificación: " + cliente.getIdentificacion());
        holder.tvNombre.setText(cliente.getNombre());
        holder.tvDireccion.setText(cliente.getDireccion());
        holder.tvTelefono.setText(cliente.getTelefono());
        holder.tvTipo.setText("" + cliente.getTipoCliente());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    static class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdentificacion, tvNombre, tvDireccion, tvTelefono, tvTipo;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdentificacion = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvTipo = itemView.findViewById(R.id.tvTipo);
        }
    }
}

