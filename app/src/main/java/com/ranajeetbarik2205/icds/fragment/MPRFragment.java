package com.ranajeetbarik2205.icds.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ranajeetbarik2205.icds.MainActivity;
import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.FragmentMprBinding;
import com.ranajeetbarik2205.icds.models.MPR;
import com.ranajeetbarik2205.icds.models.Weight;
import com.ranajeetbarik2205.icds.viewmodels.MprViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class MPRFragment extends Fragment {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private FragmentMprBinding fragmentMprBinding;
    private ArrayAdapter<String> monthSpinnerAdapter;
    private ArrayAdapter<String> centerSpinnerAdapter;
    private String centre, month, awwName, awhName, pmNumber, nmNumber, babiesNumber,
            preschoolChildren, healthCheckup, immunized, referal, babiesNormalWeight, babiesModerateWeight,
            babiesSnmWeight, preschoolNormalWeight, preschoolModerateWeight, preschoolSnmWeight;
    private MprViewModel mprViewModel;
    private Uri imageUri;
    private String currentPhotoPath;
    private String signaturePath;
    private File photoFile = null;
    private File centrePhotoFile, childrenPhotoFile;
    private int photoFlag = 0;

    public MPRFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment Using dataBinding
        fragmentMprBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mpr, container, false);
        return fragmentMprBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mprViewModel = ViewModelProviders.of(getActivity()).get(MprViewModel.class);
        checkAndRequestPermissions();

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
        fragmentMprBinding.monthSpinner.setAdapter(monthSpinnerAdapter);

        centerSpinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.centre));
        centerSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragmentMprBinding.centerSpinner.setAdapter(centerSpinnerAdapter);

        fragmentMprBinding.monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = fragmentMprBinding.monthSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fragmentMprBinding.centerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                centre = fragmentMprBinding.centerSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        fragmentMprBinding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                awwName = fragmentMprBinding.nameOfAwwEdTxt.getText().toString().trim();
                awhName = fragmentMprBinding.nameOfAwhEdTxt.getText().toString().trim();
                pmNumber = fragmentMprBinding.numberOfPmEdTxt.getText().toString().trim();
                nmNumber = fragmentMprBinding.numberOfNmEdTxt.getText().toString().trim();
                babiesNumber = fragmentMprBinding.numberOfBabiesEdTxt.getText().toString().trim();
                preschoolChildren = fragmentMprBinding.numberOfPreschoolChildrenEdTxt.getText().toString().trim();
                healthCheckup = fragmentMprBinding.numberOfHealthCheckupChildrenEdTxt.getText().toString().trim();
                immunized = fragmentMprBinding.numberOfImmunizedChildrenEdTxt.getText().toString().trim();
                referal = fragmentMprBinding.numberOfReferalChildrenEdTxt.getText().toString().trim();
                babiesNormalWeight = fragmentMprBinding.weightOfNormalBabiesEdTxt.getText().toString().trim();
                babiesModerateWeight = fragmentMprBinding.weightOfModerateBabiesEdTxt.getText().toString().trim();
                babiesSnmWeight = fragmentMprBinding.weightOfSnmBabiesEdTxt.getText().toString().trim();
                preschoolNormalWeight = fragmentMprBinding.weightOfNormalPreschoolEdTxt.getText().toString().trim();
                preschoolModerateWeight = fragmentMprBinding.weightOfModeratePreschoolEdTxt.getText().toString().trim();
                preschoolSnmWeight = fragmentMprBinding.weightOfSnmPreschoolEdTxt.getText().toString().trim();

                MPR mpr = null;
                Weight weight = null;
                try{
                    mpr = new MPR(month, centre, awwName, awhName, pmNumber, nmNumber, babiesNumber,
                            preschoolChildren, immunized
                            , healthCheckup, referal, centrePhotoFile.getAbsolutePath(),
                            childrenPhotoFile.getAbsolutePath(), signaturePath, 0);

                    weight = new Weight(centre, month, babiesNumber, babiesNormalWeight,
                            babiesModerateWeight, babiesSnmWeight
                            , preschoolNormalWeight, preschoolModerateWeight, preschoolSnmWeight, 0);

                }catch (Exception e){
                    e.printStackTrace();
                }

                if (mprViewModel.isValid(mpr, weight)) {
                    mprViewModel.insertMprData(mpr);
                    mprViewModel.insertWeightData(weight);
                    Bitmap signatureBitmap = fragmentMprBinding.signaturePad.getSignatureBitmap();
                    if (addJpgSignatureToGallery(signatureBitmap)) {
                        Toast.makeText(getActivity(), "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(), path, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Unable to store the signature", Toast.LENGTH_SHORT).show();
                    }
                    Toasty.success(getActivity(), "Data Saved Successfully", Toast.LENGTH_LONG, true).show();
                } else {

                    Toasty.info(getActivity(), "Please provide The Required Data", Toast.LENGTH_SHORT, true).show();
                }


            }
        });

        fragmentMprBinding.centrePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoFlag = 1;
                dispatchTakePictureIntent();

            }
        });

        fragmentMprBinding.childrenPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoFlag = 2;
                dispatchTakePictureIntent();
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


    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
            Toast.makeText(getActivity(), photo.toString(), Toast.LENGTH_SHORT).show();
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        signaturePath = contentUri.getPath();
        //Toast.makeText(this, contentUri.getpa, Toast.LENGTH_SHORT).show();
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    private boolean checkAndRequestPermissions() {
        int permissionReadExternalStorage = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeExternalStorage = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionReadExternalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (writeExternalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {


            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++) {


                    if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Log.e("msg", "read external storage granted");

                        }
                    } else if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Log.e("msg", "write external storage granted");

                        }
                    } else if (permissions[i].equals(Manifest.permission.CAMERA)) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Log.e("msg", "camera  granted");

                        }
                    }
                }

            }

        }
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
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Create the File where the photo should go

            try {
                //Bitmap centrePhotoBitmap  = android.provider.MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(currentPhotoPath));
                Bitmap centrePhotoBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                if (photoFlag == 1 && centrePhotoBitmap != null) {
                    fragmentMprBinding.centrePhoto.setImageBitmap(centrePhotoBitmap);
                    centrePhotoFile = photoFile;
                } else {
                    fragmentMprBinding.childrenPhoto.setImageBitmap(centrePhotoBitmap);
                    childrenPhotoFile = photoFile;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            // User Cancelled the action
            Toasty.error(getActivity(), "Picture Is Not Clicked", Toast.LENGTH_LONG).show();
        }
    }
}
