package espol.poo.tecnicentroapp.Actividades.Servicios;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import espol.poo.tecnicentroapp.Modelo.ServicioM.*;
import espol.poo.tecnicentroapp.R;

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder> {

    private final ArrayList<Servicio> listaServicios;

    public ServicioAdapter(ArrayList<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio, parent, false);
        return new ServicioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
        Servicio servicio = listaServicios.get(position);

        holder.tvCodigo.setText("Código"+servicio.getCodigo());
        holder.tvNombre.setText(servicio.getNombre());
        holder.tvPrecio.setText("$" + servicio.getPrecio());

        // Aquí puedes agregar lógica si el botón editar va a hacer algo
        holder.btnEditar.setOnClickListener(view -> {
            Context ctx = view.getContext();
            Intent intent = new Intent(ctx, EditarServicioActivity.class);
            intent.putExtra("codigo",servicio.getCodigo());
            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public static class ServicioViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvNombre, tvPrecio;
        Button btnEditar;

        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }
    }
}

