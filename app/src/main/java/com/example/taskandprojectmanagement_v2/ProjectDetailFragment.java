package com.example.taskandprojectmanagement_v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProjectDetailFragment extends Fragment {

    String projectName;
    String projectDescription;
    String projectProgress;
    int projectBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_detail, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            projectName = bundle.getString("projectName");
            projectDescription = bundle.getString("projectDescription");
            projectProgress = bundle.getString("projectProgress");
            projectBackground = Integer.parseInt(bundle.getString("projectBackground"));

            TextView projectTitle = view.findViewById(R.id.projectTitle);
            TextView projectDes = view.findViewById(R.id.projectDescription);
            TextView progress = view.findViewById(R.id.projectProgress);
            ProgressBar progressBar = view.findViewById(R.id.processBar);
            ImageView background = view.findViewById(R.id.project_background_pic);
            ImageView BuildWireFramebackground = view.findViewById(R.id.background);

            projectTitle.setText(projectName);
            projectDes.setText(projectDescription);
            progress.setText(projectProgress);
            progressBar.setProgress(Integer.parseInt(projectProgress));
            background.setImageResource(projectBackground);
            BuildWireFramebackground.setImageResource(projectBackground);
        }


        BottomAppBar bottomAppBar = requireActivity().findViewById(R.id.bottomAppBar);
        bottomAppBar.setVisibility(View.GONE);

        FloatingActionButton floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setVisibility(View.GONE);

        TextView backFromProjectDetail = view.findViewById(R.id.backFromProjectDetail);
        backFromProjectDetail.setOnClickListener(v -> backFromProjectDetail());

        TextView topMenuBar = view.findViewById(R.id.TopMenuBar);
        topMenuBar.setOnClickListener(v -> popup_window());

        CardView buildWireframe = view.findViewById(R.id.buildWireframe);

        buildWireframe.setOnClickListener(v -> buildWireframe());
        return view;
    }

    public void popup_window() {
        View menuItemView = getView().findViewById(R.id.TopMenuBar);
        PopupMenu popupMenu = new PopupMenu(getActivity(), menuItemView);
        popupMenu.getMenuInflater().inflate(R.menu.top_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addCover:
                        Toast.makeText(getActivity(), "Clicked Add Add Cover", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.addLogo:
                        Toast.makeText(getActivity(), "Clicked Add Logo", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.setColor:
                        Toast.makeText(getActivity(), "Clicked Set Color", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.editProfile:
                        Toast.makeText(getActivity(), "Clicked Edit Profile", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.deleteProject:
                        Toast.makeText(getActivity(), "Clicked Delete Project", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }

    private void backFromProjectDetail() {
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        replaceFragment(fragmentTransaction, new ProjectFragment());
    }

    private void buildWireframe() {

        String ProjectBackground = Integer.toString(projectBackground);
        Bundle bundle = new Bundle();
        bundle.putString("projectBackground", ProjectBackground);
        bundle.putString("projectName", projectName);
        bundle.putString("projectProgress", projectProgress);
        bundle.putString("projectDescription", projectDescription);

        BuildWireframeFragment fragment = new BuildWireframeFragment();
        fragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

    }

    private void replaceFragment(FragmentTransaction fragmentTransaction, Fragment replaceFragmentName) {
        fragmentTransaction.replace(R.id.frame_layout, replaceFragmentName).addToBackStack("");
        fragmentTransaction.commit();
    }
}