package espol.poo.tecnicentroapp.Actividades.Tecnicos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.TecnicosM.RepositorioTecnicos;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.Tecnico;
import espol.poo.tecnicentroapp.R;

public class TecnicoAdapter extends RecyclerView.Adapter<TecnicoAdapter.TecnicoViewHolder> {
    private final ArrayList<Tecnico> listaTecnicos;

    public TecnicoAdapter(ArrayList<Tecnico> listaTecnicos){
        this.listaTecnicos=listaTecnicos;
    }

    @NonNull
    @Override
    public TecnicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tecnico, parent, false);
        return new TecnicoViewHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull TecnicoViewHolder holder, int position){
        Tecnico tecnico = listaTecnicos.get(position);

        holder.tvIdentificacion.setText("Identificación: "+ tecnico.getIdentificacion());
        holder.tvNombre.setText(tecnico.getNombre());
        holder.tvTelefono.setText(tecnico.getTelefono());
        holder.tvEspecialidad.setText( tecnico.getEspecialidad());

        holder.btnEliminar.setOnClickListener(view -> {
            String id = tecnico.getIdentificacion();
            int pos = holder.getAdapterPosition();
            eliminarTecnico(id,pos);
        });
    }

    @Override
    public int getItemCount(){
        return listaTecnicos.size();
    }

    public void eliminarTecnico(String id, int pos){
        listaTecnicos.remove(pos);
        notifyItemRemoved(pos);
        RepositorioTecnicos.eliminarPorId(id);
    }

    static class TecnicoViewHolder extends RecyclerView.ViewHolder{
        TextView tvIdentificacion, tvNombre, tvTelefono, tvEspecialidad;
        Button btnEliminar;

        public TecnicoViewHolder(@NonNull View itemView){
            super(itemView);
            tvIdentificacion = itemView.findViewById(R.id.tvIdentificacionT);
            tvNombre = itemView.findViewById(R.id.tvNombreT);
            tvTelefono = itemView.findViewById(R.id.tvTelefonoT);
            tvEspecialidad = itemView.findViewById(R.id.tvEspecialidadT);
            btnEliminar = itemView.findViewById(R.id.btnElimarTecnico);

        }
    }
}
