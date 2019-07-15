package com.ranajeetbarik2205.icds.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.adapter.ThrListAdapter;
import com.ranajeetbarik2205.icds.databinding.FragmentThrListBinding;
import com.ranajeetbarik2205.icds.models.Immunization;
import com.ranajeetbarik2205.icds.models.THR;
import com.ranajeetbarik2205.icds.viewmodels.ImmunizationViewModel;
import com.ranajeetbarik2205.icds.viewmodels.ThrViewModel;

import java.util.List;


public class ThrListFragment extends Fragment {


   private FragmentThrListBinding fragmentThrListBinding;
    private NavController navController;
    ThrViewModel thrViewModel;
    ThrListAdapter thrListAdapter;
    public ThrListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController =  Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        thrViewModel = ViewModelProviders.of(getActivity()).get(ThrViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentThrListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_thr_list, container, false);
        return fragmentThrListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        thrViewModel.getThrLiveDataList().observe(getActivity(), new Observer<List<THR>>() {
            @Override
            public void onChanged(List<THR> thrs) {
                thrListAdapter = new ThrListAdapter(thrs,getActivity());
                fragmentThrListBinding.thrListRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
                fragmentThrListBinding.thrListRcv.setAdapter(thrListAdapter);
            }
        });

        fragmentThrListBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_thrListFragment_to_THRFragment);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
