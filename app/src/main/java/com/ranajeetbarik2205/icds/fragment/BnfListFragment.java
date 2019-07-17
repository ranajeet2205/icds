package com.ranajeetbarik2205.icds.fragment;


import android.annotation.SuppressLint;
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

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.adapter.BnfListAdapter;
import com.ranajeetbarik2205.icds.databinding.FragmentBnfListBinding;
import com.ranajeetbarik2205.icds.interfaces.RecyclerViewClick;
import com.ranajeetbarik2205.icds.models.BNF;
import com.ranajeetbarik2205.icds.util.AppConstants;
import com.ranajeetbarik2205.icds.util.SharedPrefManager;
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
    private RecyclerViewClick recyclerViewClick;
    private SharedPrefManager sharedPrefManager;
    private List<BNF> bnfList;
    public BnfListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnfViewModel = ViewModelProviders.of(getActivity()).get(BnfViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        sharedPrefManager = new SharedPrefManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBnfListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bnf_list, container, false);
        return fragmentBnfListBinding.getRoot();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (TextUtils.equals(sharedPrefManager.getStr(AppConstants.WHO_IS_USER),AppConstants.CDPO) ||
                TextUtils.equals(sharedPrefManager.getStr(AppConstants.WHO_IS_USER),AppConstants.DSWO)){
            fragmentBnfListBinding.fab.setVisibility(View.GONE);
        }

        recyclerViewClick = new RecyclerViewClick() {
            @Override
            public void onClick(View view, int position) {
                BNF bnf = bnfList.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("BNF",bnf);
                navController.navigate(R.id.action_bnfListFragment_to_BNFFragment,bundle);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        };

        bnfViewModel.getBnfLiveDataList().observe(getActivity(), new Observer<List<BNF>>() {
            @Override
            public void onChanged(List<BNF> bnfs) {
                bnfList = bnfs;
                bnfListAdapter = new BnfListAdapter(bnfs,getActivity(),recyclerViewClick);
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
