package com.ranajeetbarik2205.icds.fragment;


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
import com.ranajeetbarik2205.icds.adapter.ImmunListAdapter;
import com.ranajeetbarik2205.icds.databinding.FragmentImmunizListBinding;
import com.ranajeetbarik2205.icds.databinding.FragmentImmunizationBinding;
import com.ranajeetbarik2205.icds.models.Immunization;
import com.ranajeetbarik2205.icds.viewmodels.ImmunizationViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImmunizListFragment extends Fragment {


    FragmentImmunizListBinding fragmentImmunizListBinding;
    private  NavController navController;
    private ImmunizationViewModel immunizationViewModel;
    private ImmunListAdapter immunListAdapter;
    public ImmunizListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController =  Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        immunizationViewModel = ViewModelProviders.of(getActivity()).get(ImmunizationViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentImmunizListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_immuniz_list, container, false);
        return fragmentImmunizListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        immunizationViewModel.getImmunizationListLiveData().observe(getActivity(), new Observer<List<Immunization>>() {
            @Override
            public void onChanged(List<Immunization> immunizations) {
                immunListAdapter = new ImmunListAdapter(immunizations,getActivity());
                fragmentImmunizListBinding.immunListRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
                fragmentImmunizListBinding.immunListRcv.setAdapter(immunListAdapter);
            }
        });

        fragmentImmunizListBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_immunizListFragment_to_immunizationFragment2);
            }
        });
    }
}
