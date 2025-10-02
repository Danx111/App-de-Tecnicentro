package espol.poo.tecnicentroapp.Actividades.ReportePorTecnico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import espol.poo.tecnicentroapp.Modelo.TecnicosM.ReporteTecnico;
import espol.poo.tecnicentroapp.R;

public class ReporteTecnicoAdapter extends RecyclerView.Adapter<ReporteTecnicoAdapter.VH> {

    private final DecimalFormat money = new DecimalFormat("$#,##0.00");
    private final ArrayList<ReporteTecnico> items;

    public ReporteTecnicoAdapter(ArrayList<ReporteTecnico> lista){
        this.items = lista;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reporte_tecnico, parent, false);
        return new VH(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        ReporteTecnico rt = items.get(position);
        String nombreTec = rt.getTecnico() != null ? rt.getTecnico().getNombre() : "(sin técnico)";
        h.tvTecnico.setText(nombreTec);
        h.tvTotal.setText(money.format(rt.getTotal()));
    }

    @Override public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvTecnico, tvTotal;
        VH(@NonNull View v) {
            super(v);
            tvTecnico = v.findViewById(R.id.tvTecnico);
            tvTotal   = v.findViewById(R.id.tvTotal);
        }
    }
}
