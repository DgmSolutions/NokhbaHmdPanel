package fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nokhba.nokhbahmdpanel.InfoScreen;
import com.nokhba.nokhbahmdpanel.R;
import com.nokhba.nokhbahmdpanel.model.Help;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import adapter.HelpAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpFragment extends Fragment  {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    public HelpFragment() {
        // Required empty public constructor
    }


    public static HelpFragment newInstance(String param1, String param2) {
        HelpFragment fragment = new HelpFragment();
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
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private HelpAdapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference help = db.collection("Help");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_id);
        linearLayout= getActivity().findViewById(R.id.layout);
        Query q=help.orderBy("date", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Help> options =new FirestoreRecyclerOptions.Builder<Help>()
                .setQuery(q,Help.class)
                .build();
        adapter = new HelpAdapter(options);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("حذف الطلبات")
                .setCancelable(false)
                .setMessage("هل تريد حذف الطلب ؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.DeleteItem(viewHolder.getAdapterPosition());
                    }
                })
                .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button negative = alertDialog.getButton(alertDialog.BUTTON_NEGATIVE);
        negative.setBackgroundColor(Color.TRANSPARENT);
        negative.setTextColor(Color.BLACK);



    }
}).attachToRecyclerView(recyclerView);
adapter.setClick(new HelpAdapter.ItemClick() {
    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

        Help obj=documentSnapshot.toObject(Help.class);
        Intent i =new Intent(getActivity(), InfoScreen.class);
        i.putExtra("help",obj);
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