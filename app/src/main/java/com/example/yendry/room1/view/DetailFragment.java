package com.example.yendry.room1.view;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yendry.room1.App;
import com.example.yendry.room1.MainActivity;
import com.example.yendry.room1.R;
import com.example.yendry.room1.util.OnFragmentInteractionListener;
import com.example.yendry.room1.viewmodel.DetailFragmentViewModel;

import javax.inject.Inject;

import static com.example.yendry.room1.util.Constants.EXTRA_ITEM_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final String TAG = DetailFragment.class.getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    TextView title, content;

    private OnFragmentInteractionListener listener;
    private DetailFragmentViewModel detailFragmentViewModel;
    private String id;

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
        Bundle arguments = getArguments();
        assert arguments != null;
        id = arguments.getString(EXTRA_ITEM_ID);

        title = view.findViewById(R.id.detail_title_id);
        content = view.findViewById(R.id.detail_content_id);


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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        detailFragmentViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailFragmentViewModel.class);
        detailFragmentViewModel.getNote(id).observe(this, note -> {
           title.setText(note.getTitle());
           content.setText(note.getContent());
        });
    }
}
