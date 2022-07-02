package com.example.taskandprojectmanagement_v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.taskandprojectmanagement_v2.Adapters.CardAdapter;
import com.example.taskandprojectmanagement_v2.Adapters.TodayTaskAdapter;
import com.example.taskandprojectmanagement_v2.Models.CardModel;
import com.example.taskandprojectmanagement_v2.Models.TodayTaskModel;
import com.example.taskandprojectmanagement_v2.databinding.FragmentHomeBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerViewClickListener {

    FragmentHomeBinding binding;
    ArrayList<CardModel> list = new ArrayList<>();
    ArrayList<TodayTaskModel> list1 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        BottomAppBar bottomAppBar = requireActivity().findViewById(R.id.bottomAppBar);
        bottomAppBar.setVisibility(View.VISIBLE);
        FloatingActionButton floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setVisibility(View.VISIBLE);

        TextView RecentProjectSeeAll = binding.RecentProjectSeeAll;
        TextView TodayTaskSeeAll = binding.TodayTaskSeeAll;

        RecentProjectSeeAll.setOnClickListener(v -> RecentProjectSeeAll());
        TodayTaskSeeAll.setOnClickListener(v -> TodayTaskSeeAll());


        list.add(new CardModel("Project 1", "Project Descriptions", "80", R.drawable.project_background1));
        list.add(new CardModel("Project 2", "Project Descriptions", "10", R.drawable.project_background2));
        list.add(new CardModel("Project 3", "Project Descriptions", "30", R.drawable.project_background3));
        list.add(new CardModel("Project 4", "Project Descriptions", "40", R.drawable.project_background4));
        list.add(new CardModel("Project 5", "Project Descriptions", "70", R.drawable.project_background5));

        list1.add(new TodayTaskModel("Meeting Title1", "7:00 PM"));
        list1.add(new TodayTaskModel("Meeting Title2", "5:01 PM"));
        list1.add(new TodayTaskModel("Meeting Title3", "3:03 PM"));
        list1.add(new TodayTaskModel("Meeting Title4", "1:00 PM"));
        list1.add(new TodayTaskModel("Meeting Title5", "2:00 PM"));

        CardAdapter adapter = new CardAdapter(list, getContext());
        binding.recycleView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recycleView.setLayoutManager(linearLayoutManager);

        TodayTaskAdapter adapter1 = new TodayTaskAdapter(list1, getContext());
        binding.recycleViewForTodayTask.setAdapter(adapter1);
        adapter.setItemClick(this);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recycleViewForTodayTask.setLayoutManager(linearLayoutManager1);
        return binding.getRoot();
    }


    private void replaceFragment(FragmentTransaction fragmentTransaction, Fragment replaceFragmentName) {
        fragmentTransaction.replace(R.id.frame_layout, replaceFragmentName).addToBackStack("");
        fragmentTransaction.commit();
    }

    public void RecentProjectSeeAll() {
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        replaceFragment(fragmentTransaction, new AllRecentProjectsFragment());
    }

    public void TodayTaskSeeAll() {
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        replaceFragment(fragmentTransaction, new AllTodayTasksFragment());
    }


    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.project1) {
            String projectName = list.get(position).getProjectName();
            String projectDescription = list.get(position).getProjectDescription();
            String projectProgress = list.get(position).getProjectProgress();
            String projectBackground = Integer.toString(list.get(position).getProjectBackground());
            Bundle bundle = new Bundle();
            bundle.putString("projectName", projectName);
            bundle.putString("projectDescription", projectDescription);
            bundle.putString("projectProgress", projectProgress);
            bundle.putString("projectBackground", projectBackground);

            ProjectDetailFragment fragment = new ProjectDetailFragment();
            fragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

        }
    }

}