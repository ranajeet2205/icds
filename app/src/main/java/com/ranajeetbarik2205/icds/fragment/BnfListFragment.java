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
import com.ranajeetbarik2205.icds.adapter.BnfListAdapter;
import com.ranajeetbarik2205.icds.databinding.FragmentBnfListBinding;
import com.ranajeetbarik2205.icds.models.BNF;
import com.ranajeetbarik2205.icds.viewmodels.BnfViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BnfListFragment extends Fragment {

    private FragmentBnfListBinding fragmentBnfListBinding;
    private BnfViewModel bnfViewModel;
    private NavController navController;
    private BnfListAdapter bnfListAdapter;
    public BnfListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnfViewModel = ViewModelProviders.of(getActivity()).get(BnfViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBnfListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bnf_list, container, false);
        return fragmentBnfListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bnfViewModel.getBnfLiveDataList().observe(getActivity(), new Observer<List<BNF>>() {
            @Override
            public void onChanged(List<BNF> bnfs) {
                bnfListAdapter = new BnfListAdapter(bnfs,getActivity());
                fragmentBnfListBinding.bnfListRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
                fragmentBnfListBinding.bnfListRcv.setAdapter(bnfListAdapter);
            }
        });

        fragmentBnfListBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.BNFFragment);
            }
        });
    }
}
