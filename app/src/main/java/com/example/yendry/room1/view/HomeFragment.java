package com.example.yendry.room1.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yendry.room1.App;
import com.example.yendry.room1.MainActivity;
import com.example.yendry.room1.R;
import com.example.yendry.room1.base.FragmentBase;
import com.example.yendry.room1.module.Note;
import com.example.yendry.room1.util.AdapterInterface;
import com.example.yendry.room1.util.OnFragmentInteractionListener;
import com.example.yendry.room1.viewmodel.HomeFragmentViewModel;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import static com.example.yendry.room1.util.Constants.ADD_FRAGMENT_TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends FragmentBase implements AdapterInterface{

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    OnFragmentInteractionListener listener;

    Adapter adapter;
    RecyclerView rv;

    HomeFragmentViewModel homeFragmentViewModel;
    private TextView txt;
    private FloatingActionButton fab;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getActivity().getApplication()).getComponent().inject(this);
        adapter = new Adapter(this, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv = view.findViewById(R.id.rv_home_id);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);


        fab = view.findViewById(R.id.floating_id);
        fab.setOnClickListener(v -> listener.onFragmentInteraction(ADD_FRAGMENT_TAG));





        return view;
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeFragmentViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HomeFragmentViewModel.class);
//        homeFragmentViewModel.removeAll();
        homeFragmentViewModel.setFragment(this);
        homeFragmentViewModel.getAllNotes().observe(this, notes -> {
            if (notes != null) {
                adapter.setList(notes);
            }

        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (MainActivity)context;
    }

    @Override
    public void onItemClick(String id) {
        listener.onItemClicked(id);
    }

    @Override
    public void onDelete(Note note) {
        homeFragmentViewModel.deleteNote(note);
    }


    public void showToast(){
        Toast.makeText(getContext(), "HomeFragment", Toast.LENGTH_SHORT).show();
    }
}
