package com.ranajeetbarik2205.icds.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranajeetbarik2205.icds.MainActivity;
import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.adapter.MprListAdapter;
import com.ranajeetbarik2205.icds.databinding.FragmentMprListBinding;
import com.ranajeetbarik2205.icds.interfaces.RecyclerViewClick;
import com.ranajeetbarik2205.icds.models.MPR;
import com.ranajeetbarik2205.icds.util.AppConstants;
import com.ranajeetbarik2205.icds.util.SharedPrefManager;
import com.ranajeetbarik2205.icds.viewmodels.MprViewModel;

import java.util.ArrayList;
import java.util.List;


public class MprListFragment extends Fragment  {

    private FragmentMprListBinding fragmentMprListBinding;
    MprViewModel mprViewModel;
    MprListAdapter mprListAdapter;
    NavController navController;
    List<MPR> mprList;
    SharedPrefManager sharedPrefManager;
    RecyclerViewClick recyclerViewClick;
    public MprListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mprViewModel = ViewModelProviders.of(getActivity()).get(MprViewModel.class);
        navController =  Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        sharedPrefManager = new SharedPrefManager(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMprListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mpr_list, container, false);
        return fragmentMprListBinding.getRoot();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        if (TextUtils.equals(sharedPrefManager.getStr(AppConstants.WHO_IS_USER),AppConstants.CDPO) ||
                TextUtils.equals(sharedPrefManager.getStr(AppConstants.WHO_IS_USER),AppConstants.DSWO)){
            fragmentMprListBinding.fab.setVisibility(View.GONE);
        }

        recyclerViewClick = new RecyclerViewClick() {
            @Override
            public void onClick(View view, int position) {
                MPR mpr = mprList.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("MPR",mpr);
                navController.navigate(R.id.action_mprListFragment_to_MPRFragment,bundle);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        };

        mprViewModel.getMprList().observe(getActivity(), new Observer<List<MPR>>() {
            @Override
            public void onChanged(List<MPR> mprs) {
                mprList = mprs;
                mprListAdapter = new MprListAdapter(mprList,getActivity(),recyclerViewClick);
                fragmentMprListBinding.mprListRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
                fragmentMprListBinding.mprListRcv.setAdapter(mprListAdapter);
            }
        });

        fragmentMprListBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               navController.navigate(R.id.action_mprListFragment_to_MPRFragment);

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
