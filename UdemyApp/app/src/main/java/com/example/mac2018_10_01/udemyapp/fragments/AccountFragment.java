package com.example.mac2018_10_01.udemyapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.activity.ApplicationClass;
import com.example.mac2018_10_01.udemyapp.activity.BrowseActivity;
import com.example.mac2018_10_01.udemyapp.activity.MainActivity;
import com.example.mac2018_10_01.udemyapp.activity.SigninActivity;
import com.example.mac2018_10_01.udemyapp.modalClasses.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

public class AccountFragment extends Fragment{

    CardView cvUser, cvInstructor, cvPreferences, cvSupport;
    String user;
    Gson gson;
    TextView tvUsername, tvQualityValue, tvNotificationPref, tvSupport, tvShare, tvViewPolicy, tvTerms, tvViewIPPolicy, tvSignIn, tvSignOut;
    Switch swLecture, swVideo, swSDcard;
    RelativeLayout rlVideoDownload;
    UserData userData;
    AlertDialog alertDialog1;
    CharSequence[] values = {" 360p "," 720p "," 1024p "};

    public AccountFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        initViews(view);

        return view;
    }

    public void initViews(View view){

        cvUser = view.findViewById(R.id.cvUser);
        cvInstructor = view.findViewById(R.id.cvInstructor);
        cvPreferences = view.findViewById(R.id.cvPreferences);
        cvSupport = view.findViewById(R.id.cvSupport);

        tvUsername = view.findViewById(R.id.tvUsername);
        tvQualityValue = view.findViewById(R.id.tvQualityValue);
        tvNotificationPref = view.findViewById(R.id.tvNotificationPref);
        tvSupport = view.findViewById(R.id.tvSupport);
        tvShare = view.findViewById(R.id.tvShare);
        tvViewPolicy = view.findViewById(R.id.tvViewPolicy);
        tvTerms = view.findViewById(R.id.tvTerms);
        tvViewIPPolicy = view.findViewById(R.id.tvViewIPPolicy);
        tvSignIn = view.findViewById(R.id.tvSignIn);
        tvSignOut = view.findViewById(R.id.tvSignOut);

        swLecture = view.findViewById(R.id.swLecture);
        swSDcard = view.findViewById(R.id.swVideo);
        swVideo = view.findViewById(R.id.swSDcard);

        rlVideoDownload = view.findViewById(R.id.rlVideoDownload);

        if(ApplicationClass.pref.contains("userDetail"))
        {
            cvUser.setVisibility(View.VISIBLE);
            cvInstructor.setVisibility(View.VISIBLE);
            cvPreferences.setVisibility(View.VISIBLE);

            gson=new Gson();
            user = ApplicationClass.pref.getString("userDetail","");
            userData=gson.fromJson(user,UserData.class);
            tvUsername.setText(userData.getUserName());

            tvSignOut.setVisibility(View.VISIBLE);
            tvSignIn.setVisibility(View.GONE);

        }

        tvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Warning");
                builder.setMessage("Are you sure you want to sign out from Udemy?");

                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if(userData.getLoginType().equalsIgnoreCase("google")){
                            ApplicationClass.mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    ApplicationClass.editor.clear();
                                    ApplicationClass.editor.commit();
                                    Intent intent=new Intent(getActivity(),MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            });
                        }else{
                            ApplicationClass.editor.clear();
                            ApplicationClass.editor.commit();
                            Intent intent=new Intent(getActivity(),MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog1.dismiss();
                    }
                });

                alertDialog1 = builder.create();
                alertDialog1.show();

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SigninActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

        tvTerms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigateToBrowser("https://www.udemy.com/terms/");
            }
        });

        tvViewPolicy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigateToBrowser("https://www.udemy.com/terms/privacy/");
            }
        });

        tvViewIPPolicy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigateToBrowser("https://www.udemy.com/terms/copyright/");
            }
        });

        rlVideoDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAlertDialogWithRadioButtonGroup();
            }
        });

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp();
            }
        });
    }

    public void navigateToBrowser(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(url));
        startActivity(browserIntent);
    }

    public void CreateAlertDialogWithRadioButtonGroup(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Select video download qoality");

        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch(item)
                {
                    case 0:
                        tvQualityValue.setText("360p");
                        break;
                    case 1:
                        tvQualityValue.setText("720p");
                        break;
                    case 2:
                        tvQualityValue.setText("1024p");
                        break;
                }
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();

    }

    public void shareApp(){
        String shareBody = "https://play.google.com/store/apps/details?id=************************";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Udemy");

        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share Udemy app"));
    }
}
