package com.yk.speedtest.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.activity.MainActivity;
import com.yk.speedtest.constact.AppConstants;
import com.yk.speedtest.util.AppLogger;
import com.yk.speedtest.util.AppUtil;
import com.yk.speedtest.util.PreferenceHelper;
import com.yk.speedtest.util.ThemeUtils;

import java.util.List;

import soup.neumorphism.NeumorphImageView;
import soup.neumorphism.NeumorphTextView;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private NeumorphImageView nIvDark;
    private NeumorphImageView nIvLight;

    private NeumorphImageView nivInstagram;
    private NeumorphImageView nivFacebook;
    private NeumorphImageView nivTwiter;
    private NeumorphImageView nivLinkedIn;
    private AppCompatTextView tvVersionValue;
    private AppCompatTextView tvCounter;
    private AppCompatEditText editTitle;
    private AppCompatEditText editSubject;
    private boolean isSubmitClick;
    private boolean isSelectMail;
    private RatingBar ratingBar;
    private final String linkedLink = "www.linkedin.com/in/yogesh-koushal-30b02b147";
    private final String facbookLink = "https://www.facebook.com/profile.php?id=100007169175371";
    private final String instagramLink = "http://instagram.com/_u/yogesh__koushal";
    private final String twitterLink = "twitter://user?user_id=yogeshkumarkou2";
    private final String gmail = "yogeshkoushal1998@gmail.com";


    private static SettingsFragment fragment;


    public static SettingsFragment getInstance() {
        if (fragment == null) {
            fragment = new SettingsFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ThemeUtils.setTheme(requireActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_settings, container, false);
        ((MainActivity) requireActivity()).setAnimation(false, true, false, false);
        initView(rootview);
        return rootview;
    }

    private void setSelectedBtnTheme() {
        if (ThemeUtils.getsTheme(mContext) == AppConstants.THEME_WHITE) {
            setRadioBtn(nIvLight, AppConstants.FLAT, R.drawable.circle_green);
            setRadioBtn(nIvDark, AppConstants.BASIN, android.R.color.transparent);
        } else if (ThemeUtils.getsTheme(mContext) == AppConstants.THEME_BLACK) {
            setRadioBtn(nIvDark, AppConstants.FLAT, R.drawable.circle_green);
            setRadioBtn(nIvLight, AppConstants.BASIN, android.R.color.transparent);
        }
    }


    private void initView(View rootview) {
        NeumorphTextView tvTitle = rootview.findViewById(R.id.tvTitle);
        nIvDark = rootview.findViewById(R.id.nIvDark);
        nIvLight = rootview.findViewById(R.id.nIvLight);

        nivInstagram = rootview.findViewById(R.id.nivInstagram);
        nivFacebook = rootview.findViewById(R.id.nivFacebook);
        nivLinkedIn = rootview.findViewById(R.id.nivLinkedIn);
        nivTwiter = rootview.findViewById(R.id.nivTwiter);
        tvVersionValue = rootview.findViewById(R.id.tvVersionValue);
        tvCounter = rootview.findViewById(R.id.tvCounter);
        editTitle = rootview.findViewById(R.id.editTitle);
        editSubject = rootview.findViewById(R.id.editSubject);
        ratingBar = rootview.findViewById(R.id.ratingBar);


        rootview.findViewById(R.id.rlLight).setOnClickListener(this);
        rootview.findViewById(R.id.rlDark).setOnClickListener(this);
        rootview.findViewById(R.id.tvSubmit).setOnClickListener(this);
        rootview.findViewById(R.id.tvCancel).setOnClickListener(this);
        rootview.findViewById(R.id.nivInstagram).setOnClickListener(this);
        rootview.findViewById(R.id.nivFacebook).setOnClickListener(this);
        rootview.findViewById(R.id.nivTwiter).setOnClickListener(this);
        rootview.findViewById(R.id.nivLinkedIn).setOnClickListener(this);

        setSelectedBtnTheme();
        tvTitle.setText(R.string.settings);
        AppUtil.changeTextColorAnimation(tvTitle);
        setAnimation();
        setApkVersion();

        editSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {//
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvCounter.setText((charSequence.length() + "/250"));
            }

            @Override
            public void afterTextChanged(Editable editable) {//
            }
        });
        editSubject.setOnTouchListener((v, event) -> {
            if (v.getId() == R.id.editSubject) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            return false;
        });
    }

    private void setRadioBtn(NeumorphImageView nIv, int flat, int p) {
        nIv.setShapeType(flat);
        nIv.setImageResource(p);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlLight:
                if (ThemeUtils.getsTheme(mContext) != AppConstants.THEME_WHITE) {
                    PreferenceHelper.getInstance(mContext).saveBooleanPreference(PreferenceHelper.THEME_SETTING_CHANGE_CHECK,
                            AppConstants.YES);
                    ThemeUtils.changeTheme(AppConstants.THEME_WHITE, mContext);
                    recreateActivity();
                    setSelectedBtnTheme();
                }
                break;
            case R.id.rlDark:
                if (ThemeUtils.getsTheme(mContext) != AppConstants.THEME_BLACK) {
                    PreferenceHelper.getInstance(mContext).saveBooleanPreference(PreferenceHelper.THEME_SETTING_CHANGE_CHECK,
                            AppConstants.YES);
                    ThemeUtils.changeTheme(AppConstants.THEME_BLACK, mContext);
                    recreateActivity();
                    setSelectedBtnTheme();
                }
                break;
            case R.id.tvSubmit:
                if (editTitle.getText().toString().trim().length() > 0) {
                    sendMail();
                } else {
                    Toast.makeText(requireActivity(), "Title is mandatory", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvCancel:
                resetOnCancelBack();
                break;
            case R.id.nivInstagram:
                sendAnotherApk(instagramLink, "com.instagram.android", "http://instagram.com/yogesh__koushal");
                break;
            case R.id.nivFacebook:
                sendAnotherApk(facbookLink, "com.facebook.android", facbookLink);
                break;
            case R.id.nivLinkedIn:
                sendAnotherApk(linkedLink, "com.linkedin.android", "https://www.linkedin.com/in/yogesh-koushal-30b02b147");
                break;
            case R.id.nivTwiter:
                sendAnotherApk(twitterLink, "com.twitter.android", "https://twitter.com/yogeshkumarkou2");
                break;
            default:
                break;
        }
    }

    private void sendAnotherApk(String linkedLink, String s, String s2) {
        Uri linkedIn = Uri.parse(linkedLink);
        Intent ln = new Intent(Intent.ACTION_VIEW, linkedIn);
        ln.setPackage(s);
        if (isIntentAvailable(mContext, ln)) {
            startActivity(ln);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s2)));
        }
    }

    public void setAnimation() {
        AppUtil.setAnimationImage(requireActivity(), R.drawable.ic_facebook, nivFacebook, true);
        AppUtil.setAnimationImage(requireActivity(), R.drawable.ic_instagram, nivInstagram, true);
        AppUtil.setAnimationImage(requireActivity(), R.drawable.ic_linked_in, nivLinkedIn, true);
        AppUtil.setAnimationImage(requireActivity(), R.drawable.ic_twiter, nivTwiter, true);
    }

    private void setApkVersion() {
        PackageInfo pinfo;
        FragmentActivity activity = requireActivity();
        try {
            pinfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            tvVersionValue.setText(("v" + pinfo.versionName));
        } catch (PackageManager.NameNotFoundException e) {
            AppLogger.ex(e);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkSubmitMail(requestCode);
    }

    private void checkSubmitMail(int requestCode) {
        if (requestCode == 1001 && isSubmitClick && isSelectMail) {
            resetOnCancelBack();
            isSelectMail = false;
            isSubmitClick = false;
        }
    }

    public void resetOnCancelBack() {
        editTitle.setText("");
        editSubject.setText("");
        ratingBar.setRating(0);
    }

    private void sendMail() {
        isSubmitClick = true;
        AppLogger.i("Send email", "");
        String[] TO = {
                gmail
        };
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, editTitle.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Rating: " + ratingBar.getRating() + "" + "\n" + editSubject.getText().toString());
        try {
            startActivityForResult(Intent.createChooser(emailIntent, "Send mail..."), 1001);
            AppLogger.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(requireActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

//        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE,
//                Uri.fromParts("mailto", "yogeshkoushal1998@gmail.com", null));
//        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"yogeshkoushal1998@gmail.com"});
//        intent.putExtra(Intent.EXTRA_SUBJECT, editTitle.getText().toString());
//        intent.putExtra(Intent.EXTRA_TEXT, "Rating: " + ratingBar.getRating() + "" + "\n" + editSubject.getText().toString());
//        try {
//            startActivityForResult(intent, 1001);
//        } catch (Exception e) {
//            AppLogger.ex(e);
//        }

    }

    @Override
    public void onStop() {
        if (isSubmitClick) {
            isSelectMail = true;
        }
        super.onStop();
    }

    private boolean isIntentAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private void recreateActivity() {
        ((MainActivity) mContext).recreate();
    }
}