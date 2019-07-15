package com.ranajeetbarik2205.icds.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.FragmentThrBinding;
import com.ranajeetbarik2205.icds.models.THR;
import com.ranajeetbarik2205.icds.viewmodels.ThrViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class THRFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private File photoFile = null;
    private FragmentThrBinding fragmentThrBinding;
    private ArrayAdapter<String> monthSpinnerAdapter;
    private ArrayAdapter<String> centerSpinnerAdapter;
    private ThrViewModel thrViewModel;
    private String month, center, totalBnf, totalPackets, totalCost;
    private String thrPhotoPath;
    private NavController navController;

    public THRFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController =  Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentThrBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_thr, container, false);
        return fragmentThrBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        thrViewModel = ViewModelProviders.of(getActivity()).get(ThrViewModel.class);

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
        fragmentThrBinding.monthSpinner.setAdapter(monthSpinnerAdapter);

        centerSpinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.centre));
        centerSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragmentThrBinding.centerSpinner.setAdapter(centerSpinnerAdapter);

        fragmentThrBinding.centerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                center = fragmentThrBinding.centerSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fragmentThrBinding.monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = fragmentThrBinding.monthSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fragmentThrBinding.thrPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        fragmentThrBinding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalBnf = fragmentThrBinding.totalBnfEdTxt.getText().toString().trim();
                totalPackets = fragmentThrBinding.totalPacketsEdTxt.getText().toString().trim();
                totalCost = fragmentThrBinding.totalCostEdTxt.getText().toString().trim();

                THR thr = new THR(month, center, totalBnf, totalPackets, thrPhotoPath, totalCost, 0);
                boolean valid = thrViewModel.isValid(thr);
                if (fragmentThrBinding.monthSpinner.getSelectedItemPosition()==0){
                    Toasty.info(getActivity(),"Please Select a month",Toast.LENGTH_SHORT,true).show();
                }else if (thrViewModel.getNumberOfThrEntries(center,month)==1){
                    Toasty.info(getActivity(), "You Already Entered For this Centre", Toast.LENGTH_SHORT, true).show();
                }
                else if ( valid){
                    thrViewModel.insertThrData(thr);
                    Toasty.success(getActivity(), "Data Saved Successfully", Toast.LENGTH_LONG, true).show();
                    navController.navigate(R.id.action_THRFragment_to_thrListFragment);

                }
                else{
                    Toasty.info(getActivity(),"Please Provide Valid Data",Toast.LENGTH_SHORT,true).show();
                }

            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int totalBnf = Integer.parseInt(s.toString());
                int totalPackets = totalBnf * 2;
                int totalCost = totalPackets * 100;
                fragmentThrBinding.totalPacketsEdTxt.setText(String.valueOf(totalPackets));
                fragmentThrBinding.totalCostEdTxt.setText(String.valueOf(totalCost));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        fragmentThrBinding.totalBnfEdTxt.addTextChangedListener(textWatcher);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.ranajeetbarik2205.icds.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "ICDS_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        thrPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Create the File where the photo should go

            try {
                //Bitmap centrePhotoBitmap  = android.provider.MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(currentPhotoPath));
                Bitmap thrPhotoBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                fragmentThrBinding.thrPhoto.setImageBitmap(thrPhotoBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            // User Cancelled the action
            Toasty.error(getActivity(), "Picture Is Not Clicked", Toast.LENGTH_LONG).show();
        }
    }

}
