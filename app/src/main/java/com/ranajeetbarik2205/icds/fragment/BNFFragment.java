package com.ranajeetbarik2205.icds.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.FragmentBnfBinding;
import com.ranajeetbarik2205.icds.models.BNF;
import com.ranajeetbarik2205.icds.util.AppConstants;
import com.ranajeetbarik2205.icds.util.SharedPrefManager;
import com.ranajeetbarik2205.icds.viewmodels.BnfViewModel;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;


public class BNFFragment extends Fragment {

    private FragmentBnfBinding fragmentBnfBinding;
    private ArrayAdapter<String> monthSpinnerAdapter;
    private ArrayAdapter<String> centerSpinnerAdapter;
    private BnfViewModel bnfViewModel;
    private NavController navController;
    private String month, center, totalPm, totalNm, totalBabies, totalPreschool, totalBnf;
    private SharedPrefManager sharedPrefManager;

    public BNFFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        sharedPrefManager = new SharedPrefManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBnfBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bnf, container, false);
        //return inflater.inflate(R.layout.fragment_bnf, container, false);
        return fragmentBnfBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        bnfViewModel = ViewModelProviders.of(getActivity()).get(BnfViewModel.class);

        monthSpinnerAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.month)) {
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH);

            @Override
            public boolean isEnabled(int position) {
                return position == month + 1 || position == month;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == month + 1 || position == month) {
                    tv.setTextColor(Color.BLACK);

                } else {
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }
        };
        monthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragmentBnfBinding.monthSpinner.setAdapter(monthSpinnerAdapter);

        centerSpinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.centre));
        centerSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragmentBnfBinding.centerSpinner.setAdapter(centerSpinnerAdapter);

        fragmentBnfBinding.centerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                center = fragmentBnfBinding.centerSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fragmentBnfBinding.monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = fragmentBnfBinding.monthSpinner.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (TextUtils.equals(sharedPrefManager.getStr(AppConstants.WHO_IS_USER), AppConstants.CDPO)) {
            fragmentBnfBinding.submitBtn.setText("Approve");
            BNF bnf = getArguments().getParcelable("BNF");
            if (bnf.getStatus() == 1) {
                fragmentBnfBinding.submitBtn.setVisibility(View.GONE);
            }

            fragmentBnfBinding.monthSpinner.setSelection(monthSpinnerAdapter.getPosition(bnf.getReporting_month()));
            fragmentBnfBinding.centerSpinner.setSelection(centerSpinnerAdapter.getPosition(bnf.getCentre()));
            fragmentBnfBinding.numberOfPmEdTxt.setText(bnf.getNumber_pm());
            fragmentBnfBinding.numberOfNmEdTxt.setText(bnf.getNumber_nm());
            fragmentBnfBinding.numberOfBabiesEdTxt.setText(bnf.getNumber_babies());
            fragmentBnfBinding.numberOfPreschoolChildrenEdTxt.setText(bnf.getNumber_preschool());
            fragmentBnfBinding.totalBnfEdTxt.setText(bnf.getNumber_total_beneficiary());


            fragmentBnfBinding.monthSpinner.setEnabled(false);
            fragmentBnfBinding.centerSpinner.setEnabled(false);
            fragmentBnfBinding.numberOfPmEdTxt.setEnabled(false);
            fragmentBnfBinding.numberOfNmEdTxt.setEnabled(false);
            fragmentBnfBinding.numberOfBabiesEdTxt.setEnabled(false);
            fragmentBnfBinding.numberOfPreschoolChildrenEdTxt.setEnabled(false);
            fragmentBnfBinding.totalBnfEdTxt.setEnabled(false);
        }


        fragmentBnfBinding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPm = fragmentBnfBinding.numberOfPmEdTxt.getText().toString().trim();
                totalNm = fragmentBnfBinding.numberOfNmEdTxt.getText().toString().trim();
                totalBabies = fragmentBnfBinding.numberOfBabiesEdTxt.getText().toString().trim();
                totalPreschool = fragmentBnfBinding.numberOfPreschoolChildrenEdTxt.getText().toString().trim();
                totalBnf = fragmentBnfBinding.totalBnfEdTxt.getText().toString().trim();
                BNF bnf = new BNF(month, center, totalPm, totalNm, totalBabies, totalPreschool, totalBnf, 0);

                boolean valid = bnfViewModel.isValid(bnf);
                if (TextUtils.equals(sharedPrefManager.getStr(AppConstants.WHO_IS_USER), AppConstants.CDPO)) {
                    bnfViewModel.gotStatusUpdate(center);
                    Toasty.success(getActivity(), "Approved", Toast.LENGTH_LONG, true).show();
                    fragmentBnfBinding.submitBtn.setVisibility(View.GONE);

                } else if (fragmentBnfBinding.monthSpinner.getSelectedItemPosition() == 0) {
                    Toasty.info(getActivity(), "Please Select a month", Toast.LENGTH_SHORT, true).show();
                } else if (bnfViewModel.numberOfEntries(center, month) == 1) {
                    Toasty.info(getActivity(), "You Already Entered For this Centre", Toast.LENGTH_SHORT, true).show();
                } else if (fragmentBnfBinding.monthSpinner.getSelectedItemPosition() != 0 && valid) {
                    bnfViewModel.insertBnfData(bnf);
                    Toasty.success(getActivity(), "Data Saved Successfully", Toast.LENGTH_LONG, true).show();
                    navController.navigate(R.id.action_BNFFragment_to_bnfListFragment);
                } else {
                    Toasty.info(getActivity(), "Please Provide Valid Data", Toast.LENGTH_SHORT, true).show();
                }


            }
        });

        if (TextUtils.equals(sharedPrefManager.getStr(AppConstants.WHO_IS_USER), AppConstants.DSWO)) {
            fragmentBnfBinding.submitBtn.setVisibility(View.GONE);
        }
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
