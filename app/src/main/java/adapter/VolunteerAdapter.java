package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nokhbahmdpanel.R;
import com.example.nokhbahmdpanel.model.Valunteer;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class VolunteerAdapter extends FirestoreRecyclerAdapter<Valunteer,VolunteerAdapter.VolunteerHolder> {
    private HelpAdapter.ItemClick click;


    public VolunteerAdapter(@NonNull FirestoreRecyclerOptions<Valunteer> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VolunteerHolder holder, int position, @NonNull Valunteer model) {
        holder.info.setText(model.getNom()+" "+model.getPrenom());
        holder.type.setText(model.getService());
        holder.date.setText(model.getDate());
    }
    @NonNull
    @Override
    public VolunteerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_row,parent,false);
        return new VolunteerHolder(v);
    }

    public void DeleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }



    class VolunteerHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView info,type,date;

        public VolunteerHolder (@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.RowimageView);
            info = itemView.findViewById(R.id.InfotextView);
            type = itemView.findViewById(R.id.TypetextView);
            date = itemView.findViewById(R.id.DatetextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && click != null){
                        click.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
        }
    }
    public interface ItemClick{
        void onItemClick(DocumentSnapshot documentSnapshot , int position);
    }
    public void setClick(HelpAdapter.ItemClick click){
        this.click=click;
    }

}
