package app.com.cvjuanresume.juansandoval.cvjuanresume.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.com.cvjuanresume.juansandoval.cvjuanresume.R;
import app.com.cvjuanresume.juansandoval.cvjuanresume.adapters.EducationAdapter;
import app.com.cvjuanresume.juansandoval.cvjuanresume.models.Education;
import app.com.cvjuanresume.juansandoval.cvjuanresume.utils.DividerItemDecoration;

/**
 * Created by jsandoval on 18/04/17.
 */

public class EducationFragment extends Fragment {

    private List<Education> educationList = new ArrayList<>();
    Context c;
    private RecyclerView recyclerView;
    private EducationAdapter mAdapter;

    public EducationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = getContext();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_education, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        mAdapter = new EducationAdapter(educationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(c, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        prepareEducationData();
        return rootView;
    }

    public void prepareEducationData(){
        Education education = new Education(getString(R.string.title_1),getString(R.string.year_1),getString(R.string.college_1),getString(R.string.contentinfo_1));
        educationList.add(education);

        education = new Education(getString(R.string.title_2),getString(R.string.year_2), getString(R.string.college_2), getString(R.string.contentinfo_2));
        educationList.add(education);

        education = new Education(getString(R.string.title_3),getString(R.string.year_3), getString(R.string.college_3), getString(R.string.contentinfo_3));
        educationList.add(education);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
