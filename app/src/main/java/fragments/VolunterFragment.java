package fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nokhbahmdpanel.InfoScreen;
import com.example.nokhbahmdpanel.R;
import com.example.nokhbahmdpanel.VinfoScreen;
import com.example.nokhbahmdpanel.model.Help;
import com.example.nokhbahmdpanel.model.Valunteer;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import adapter.HelpAdapter;
import adapter.RecyclerAdapter;
import adapter.VolunteerAdapter;


public class VolunterFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public VolunterFragment() {

    }


    public static VolunterFragment newInstance(String param1, String param2) {
        VolunterFragment fragment = new VolunterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private RecyclerView recyclerView;
    private VolunteerAdapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference volunteer = db.collection("Volunteer");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_volunter, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_id);
        Query q=volunteer.orderBy("date", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Valunteer> options =new FirestoreRecyclerOptions.Builder<Valunteer>()
                .setQuery(q,Valunteer.class)
                .build();
        adapter = new VolunteerAdapter(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {

                adapter.DeleteItem(viewHolder.getAdapterPosition());


            }
        }).attachToRecyclerView(recyclerView);
        adapter.setClick(new HelpAdapter.ItemClick() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

                Valunteer obj=documentSnapshot.toObject(Valunteer.class);
                Intent i =new Intent(getActivity(), VinfoScreen.class);
                i.putExtra("valunteer",obj);
                startActivity(i);
            }
        });
        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}