package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nokhbahmdpanel.R;
import com.example.nokhbahmdpanel.model.Help;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Document;

public class HelpAdapter extends FirestoreRecyclerAdapter <Help,HelpAdapter.HelpHolder>{
private ItemClick click;

    public HelpAdapter(@NonNull FirestoreRecyclerOptions<Help> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HelpHolder holder, int position, @NonNull Help model) {
           holder.info.setText(model.getNom()+" "+model.getPrenom());
           holder.type.setText(model.getService());
           holder.date.setText(model.getDate());
    }

    @NonNull
    @Override
    public HelpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_row,parent,false);
        return new HelpHolder(v);
    }
    public void DeleteItem(int position){
     getSnapshots().getSnapshot(position).getReference().delete();
    }

    class HelpHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
       TextView info,type,date;

       public HelpHolder(@NonNull View itemView) {
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
        void onItemClick(DocumentSnapshot documentSnapshot ,int position);
   }
   public void setClick(ItemClick click){
        this.click=click;
   }
}
