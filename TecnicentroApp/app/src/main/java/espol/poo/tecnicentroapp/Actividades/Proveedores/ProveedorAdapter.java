package espol.poo.tecnicentroapp.Actividades.Proveedores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.ProveedoresM.Proveedor;
import espol.poo.tecnicentroapp.Modelo.ProveedoresM.RepositorioProveedores;
import espol.poo.tecnicentroapp.R;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ProveedorViewHolder>{
    private final ArrayList<Proveedor> listaProveedor;

    public ProveedorAdapter(ArrayList<Proveedor> listaProveedor){
        this.listaProveedor =listaProveedor;
    }

    @NonNull
    @Override
    public ProveedorAdapter.ProveedorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_proveedor, parent, false);
        return new ProveedorAdapter.ProveedorViewHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull ProveedorAdapter.ProveedorViewHolder holder, int position){
        Proveedor proveedor = listaProveedor.get(position);

        holder.tvIdentificacion.setText("Identificación: "+ proveedor.getIdentificacion());
        holder.tvNombre.setText(proveedor.getNombre());
        holder.tvTelefono.setText(proveedor.getTelefono());
        holder.tvDescripcion.setText( proveedor.getDescripcion());

        holder.btnEliminar.setOnClickListener(view -> {
            String id = proveedor.getIdentificacion();
            int pos = holder.getAdapterPosition();
            eliminarProveedor(id,pos);
        });
    }

    @Override
    public int getItemCount(){
        return listaProveedor.size();
    }

    public void eliminarProveedor(String id, int pos){
        listaProveedor.remove(pos);
        notifyItemRemoved(pos);
        RepositorioProveedores.eliminarPorId(id);
    }

    static class ProveedorViewHolder extends RecyclerView.ViewHolder{
        TextView tvIdentificacion, tvNombre, tvTelefono, tvDescripcion;
        Button btnEliminar;

        public ProveedorViewHolder(@NonNull View itemView){
            super(itemView);
            tvIdentificacion = itemView.findViewById(R.id.tvIdentificacionP);
            tvNombre = itemView.findViewById(R.id.tvNombreP);
            tvTelefono = itemView.findViewById(R.id.tvTelefonoP);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionP);
            btnEliminar = itemView.findViewById(R.id.btnEliminarP);

        }
    }
}
