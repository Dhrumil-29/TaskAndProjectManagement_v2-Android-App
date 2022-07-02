package com.example.taskandprojectmanagement_v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskandprojectmanagement_v2.Adapters.TodayTaskAdapter;
import com.example.taskandprojectmanagement_v2.Models.TodayTaskModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AllTodayTasksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_today_tasks, container, false);

        BottomAppBar bottomAppBar = requireActivity().findViewById(R.id.bottomAppBar);
        bottomAppBar.setVisibility(View.GONE);

        FloatingActionButton floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setVisibility(View.GONE);
        TextView backFromTodayTask = view.findViewById(R.id.backFromTodayTask);
        backFromTodayTask.setOnClickListener(v -> backFromTodayTask());

        ArrayList<TodayTaskModel> list1 = new ArrayList<>();
        list1.add(new TodayTaskModel("Meeting Title1", "7:00 PM"));
        list1.add(new TodayTaskModel("Meeting Title2", "5:01 PM"));
        list1.add(new TodayTaskModel("Meeting Title3", "3:03 PM"));
        list1.add(new TodayTaskModel("Meeting Title4", "1:00 PM"));
        list1.add(new TodayTaskModel("Meeting Title5", "2:00 PM"));
        list1.add(new TodayTaskModel("Meeting Title6", "4:00 PM"));
        list1.add(new TodayTaskModel("Meeting Title7", "8:00 PM"));
        list1.add(new TodayTaskModel("Meeting Title8", "10:00 AM"));

        TodayTaskAdapter adapter1 = new TodayTaskAdapter(list1, getContext());

        RecyclerView recycleView1 = view.findViewById(R.id.recycleView1);
        recycleView1.setAdapter(adapter1);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleView1.setLayoutManager(linearLayoutManager1);


        return view;

    }

    public void backFromTodayTask() {
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        replaceFragment(fragmentTransaction, new HomeFragment());
    }

    private void replaceFragment(FragmentTransaction fragmentTransaction, Fragment replaceFragmentName) {
        fragmentTransaction.replace(R.id.frame_layout, replaceFragmentName).addToBackStack("");
        fragmentTransaction.commit();
    }
}