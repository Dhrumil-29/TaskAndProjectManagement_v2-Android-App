package com.example.taskandprojectmanagement_v2;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BuildWireframeFragment extends Fragment {

    Dialog dialog;
    RadioButton radioButton;
    RadioGroup radioGroup;
    String projectName;
    String projectDescription;
    String projectProgress;
    int projectBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_build_wireframe, container, false);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            projectName = bundle.getString("ProjectName");
            projectDescription = bundle.getString("projectDescription");
            projectProgress = bundle.getString("projectProgress");
            projectBackground = Integer.parseInt(bundle.getString("projectBackground"));

            ImageView bgImag = view.findViewById(R.id.projectBackground);

            bgImag.setImageResource(projectBackground);
        }

        BottomAppBar bottomAppBar = requireActivity().findViewById(R.id.bottomAppBar);
        bottomAppBar.setVisibility(View.GONE);

        FloatingActionButton floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setVisibility(View.GONE);

        TextView status = view.findViewById(R.id.status);
        status.setOnClickListener(v -> status());

        TextView dueDate = view.findViewById(R.id.dueDate);
        dueDate.setOnClickListener(v -> dueDate());
        TextView addAttachment = view.findViewById(R.id.addAttachment);
        addAttachment.setOnClickListener(v -> addAttachment());

        TextView backFromBuildWireframe = view.findViewById(R.id.backFromBuildWireframe);
        backFromBuildWireframe.setOnClickListener(v -> backFromBuildWireframe());
        return view;
    }

    private void status() {
        showDialog();
    }

    private void backFromBuildWireframe() {
        String ProjectBackground = Integer.toString(projectBackground);
        Bundle bundle = new Bundle();
        bundle.putString("projectBackground", ProjectBackground);
        bundle.putString("projectName", projectName);
        bundle.putString("projectProgress", projectProgress);
        bundle.putString("projectDescription", projectDescription);

        ProjectDetailFragment fragment = new ProjectDetailFragment();
        fragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

    }

    private void replaceFragment(FragmentTransaction fragmentTransaction, Fragment replaceFragmentName) {
        fragmentTransaction.replace(R.id.frame_layout, replaceFragmentName).addToBackStack("");
        fragmentTransaction.commit();
    }

    private void dueDate() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.due_date_dialog);
        dialog.show();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void addAttachment() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.attachment_dialog);
        dialog.show();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.status_dialog);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}