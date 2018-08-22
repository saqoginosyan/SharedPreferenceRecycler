package com.example.sargis.sharedprefrecyclerview.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sargis.sharedprefrecyclerview.adapter.PrefAdapter;
import com.example.sargis.sharedprefrecyclerview.model.PrefData;
import com.example.sargis.sharedprefrecyclerview.R;
import com.example.sargis.sharedprefrecyclerview.dialogfragment.AddDialogFragment;
import com.example.sargis.sharedprefrecyclerview.utilities.ButtonClickListener;
import com.example.sargis.sharedprefrecyclerview.utilities.PrefUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ButtonClickListener {

    private static final String TAG = "tag";
    private Context context = MainActivity.this;
    private List<PrefData> list;
    private PrefAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private AddDialogFragment dialogFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        final FragmentManager transaction = getFragmentManager();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment = new AddDialogFragment();
                dialogFragment.show(transaction, TAG);
            }
        });
    }

    private void initViews() {
        floatingActionButton = findViewById(R.id.fab);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view_pref_values);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        list = PrefUtil.getAllValues(context);
        adapter = new PrefAdapter(context, list);
        recyclerView.setAdapter(adapter);
    }

    public void saveValueInPref() {
        final PrefData prefData = new PrefData(dialogFragment.key, dialogFragment.value);
        if (list.contains(prefData)) {
            int index = list.indexOf(prefData);
            list.remove(index);
            adapter.notifyItemRemoved(index);
        }

        list.add(prefData);
        int position = adapter.getItemCount();
        adapter.notifyItemInserted(position);
    }

    @Override
    public void setButtonClickListener() {
        saveValueInPref();
        adapter.notifyDataSetChanged();
    }
}
