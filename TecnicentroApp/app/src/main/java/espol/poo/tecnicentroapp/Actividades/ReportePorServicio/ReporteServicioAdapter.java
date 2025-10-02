package espol.poo.tecnicentroapp.Actividades.ReportePorServicio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.ServicioM.ReporteServicio;
import espol.poo.tecnicentroapp.R;

public class ReporteServicioAdapter extends RecyclerView.Adapter<ReporteServicioAdapter.RServicioVH> {
    private final DecimalFormat money = new DecimalFormat("$#,##0.00");
    private final ArrayList<ReporteServicio> items;

    public ReporteServicioAdapter(ArrayList<ReporteServicio> lista){
        this.items = lista;
    }

    @NonNull
    @Override
    public RServicioVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reporte_servicio, parent, false);
        return new RServicioVH(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull RServicioVH h, int position) {
        ReporteServicio rs = items.get(position);
        String nombreSer = rs.getServicio() != null ? rs.getServicio().getNombre() : "(sin servicio)";
        h.tvServicio.setText(nombreSer);
        h.tvTotal.setText(money.format(rs.getTotal()));
    }
    @Override public int getItemCount() { return items.size(); }

    static class RServicioVH extends RecyclerView.ViewHolder {
        TextView tvServicio, tvTotal;
        RServicioVH(@NonNull View v) {
            super(v);
            tvServicio = v.findViewById(R.id.tvServicio);
            tvTotal   = v.findViewById(R.id.tvTotalS);
        }
    }
}
