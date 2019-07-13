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

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.FragmentImmunizationBinding;
import com.ranajeetbarik2205.icds.models.Immunization;
import com.ranajeetbarik2205.icds.viewmodels.ImmunizationViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;


public class ImmunizationFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private File photoFile = null;
    private String thrPhotoPath;
    private FragmentImmunizationBinding fragmentImmunizationBinding;
    private ArrayAdapter<String> monthSpinnerAdapter;
    private ArrayAdapter<String> centerSpinnerAdapter;
    private String centre, month, totalDue, totalReceived;
    private ImmunizationViewModel immunizationViewModel;

    public ImmunizationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentImmunizationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_immunization, container, false);
        return fragmentImmunizationBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        immunizationViewModel = ViewModelProviders.of(getActivity()).get(ImmunizationViewModel.class);

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
        fragmentImmunizationBinding.monthSpinner.setAdapter(monthSpinnerAdapter);

        centerSpinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.centre));
        centerSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragmentImmunizationBinding.centerSpinner.setAdapter(centerSpinnerAdapter);

        fragmentImmunizationBinding.monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = fragmentImmunizationBinding.monthSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fragmentImmunizationBinding.centerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                centre = fragmentImmunizationBinding.centerSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fragmentImmunizationBinding.immunizationPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        fragmentImmunizationBinding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalDue = fragmentImmunizationBinding.totalDueEdTxt.getText().toString().trim();
                totalReceived = fragmentImmunizationBinding.totalReceivedEdTxt.getText().toString().trim();

                Immunization immunization = new Immunization(month, centre, totalDue, totalReceived, thrPhotoPath, 0);
                boolean valid = immunizationViewModel.isValid(immunization);
                if (fragmentImmunizationBinding.monthSpinner.getSelectedItemPosition()==0){
                    Toasty.info(getActivity(),"Please Select a month",Toast.LENGTH_SHORT,true).show();
                }else if (fragmentImmunizationBinding.monthSpinner.getSelectedItemPosition()!=0 && valid ){
                    immunizationViewModel.insertImmunizationData(immunization);
                    Toasty.success(getActivity(), "Data Saved Successfully", Toast.LENGTH_LONG, true).show();
                }
                else{
                    Toasty.info(getActivity(),"Please Provide Valid Data",Toast.LENGTH_SHORT,true).show();
                }
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
                Bitmap immunizationPhotoBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                fragmentImmunizationBinding.immunizationPhoto.setImageBitmap(immunizationPhotoBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            // User Cancelled the action
            Toasty.error(getActivity(), "Picture Is Not Clicked", Toast.LENGTH_LONG).show();
        }
    }

}
