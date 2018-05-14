package com.example.yendry.room1.view;


import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yendry.room1.App;
import com.example.yendry.room1.MainActivity;
import com.example.yendry.room1.R;
import com.example.yendry.room1.util.OnFragmentInteractionListener;

import javax.inject.Inject;

import static com.example.yendry.room1.util.Constants.EXTRA_ITEM_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;


    private OnFragmentInteractionListener listener;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getActivity().getApplication()).getComponent().inject(this);
    }

    public static DetailFragment newInstance(String itemId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_ITEM_ID, itemId);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);




        return view;
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

}
