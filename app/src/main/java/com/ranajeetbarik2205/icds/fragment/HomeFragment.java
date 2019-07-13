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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.FragmentHomeBinding;
import com.ranajeetbarik2205.icds.models.MPR;
import com.ranajeetbarik2205.icds.viewmodels.MprViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
    private MprViewModel mprViewModel;
    List<PieEntry> entries;
    List<MPR> mprList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entries = new ArrayList<>();
        mprList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mprViewModel = ViewModelProviders.of(getActivity()).get(MprViewModel.class);
        mprViewModel.getMprList().observe(getActivity(), new Observer<List<MPR>>() {
            @Override
            public void onChanged(List<MPR> mprs) {
                entries.clear();
                if (mprs.size() > 0) {
                    for (MPR mpr : mprs) {
                        entries.add(new PieEntry(10, mpr.getCentre()));
                    }
                    if (mprs.size() < 10) {
                        entries.add(new PieEntry(100 - (mprs.size() * 10), "Not Entered"));
                    }
                }
                if (mprs.size() == 0) {
                    entries.add(new PieEntry(10, "No Entries"));
                }
                PieDataSet set = new PieDataSet(entries, "MPR");
                PieData data = new PieData(set);
                set.setColors(ColorTemplate.JOYFUL_COLORS);
                fragmentHomeBinding.piechart.setData(data);
                fragmentHomeBinding.piechart.invalidate(); // refresh
                fragmentHomeBinding.piechart.getDescription().setText("Monthly Progress Report");
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
