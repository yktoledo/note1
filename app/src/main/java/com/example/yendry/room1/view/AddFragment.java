package com.example.yendry.room1.view;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yendry.room1.App;
import com.example.yendry.room1.MainActivity;
import com.example.yendry.room1.R;
import com.example.yendry.room1.base.FragmentBase;
import com.example.yendry.room1.module.Note;
import com.example.yendry.room1.util.OnFragmentInteractionListener;
import com.example.yendry.room1.viewmodel.AddFragmentViewModel;

import java.util.UUID;

import javax.inject.Inject;

import static com.example.yendry.room1.util.Constants.HOME_FRAGMENT_TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends FragmentBase {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    EditText title, content;
    Button add;
    AddFragmentViewModel addFragmentViewModel;

    private OnFragmentInteractionListener listener;

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {

        Bundle args = new Bundle();
        args.putString("TRANS_BUTTON", "TRANS_BUTTON");
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getActivity().getApplication()).getComponent().inject(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        title = view.findViewById(R.id.add_title_id);
        content = view.findViewById(R.id.add_content_id);
        add = view.findViewById(R.id.add_btn_id);
        add.setOnClickListener(v -> {
            if (TextUtils.isEmpty(title.getText().toString()) || TextUtils.isEmpty(content.getText().toString())){
                Toast.makeText(getContext(), "Info missing", Toast.LENGTH_SHORT).show();
            } else {
                Note note = new Note(UUID.randomUUID().toString(), title.getText().toString(), content.getText().toString());
                addFragmentViewModel.insert(note);
                listener.onFragmentInteraction(HOME_FRAGMENT_TAG);

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addFragmentViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddFragmentViewModel.class);
        addFragmentViewModel.setFragment(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        addFragmentViewModel.init();
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
    public void showToast(){
        Toast.makeText(getContext(), "AddFragment", Toast.LENGTH_SHORT).show();
    }
}
