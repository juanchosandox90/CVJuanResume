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
import app.com.cvjuanresume.juansandoval.cvjuanresume.adapters.ExperienceAdapter;
import app.com.cvjuanresume.juansandoval.cvjuanresume.models.Experience;
import app.com.cvjuanresume.juansandoval.cvjuanresume.utils.DividerItemDecoration;

/**
 * Created by jsandoval on 18/04/17.
 */

public class ExperienceFragment extends Fragment {

    private List<Experience> experienceList = new ArrayList<>();
    Context c;
    private RecyclerView recyclerView;
    private ExperienceAdapter mAdapter;

    private final String android_image_urls[] = {
            "https://www.ekosnegocios.com/negocios/EQUIDNA/fotos/f1/862.jpg",
            "https://www.underconsideration.com/brandnew/archives/accenture_logo.png",
            "https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAAT4AAAAJDYxY2YxMDhkLWViNzktNDA2YS04ZTFmLWY1ZTI4YjRjMWE0Yw.png"
    };

    public ExperienceFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_experience, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_experience);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(c, LinearLayoutManager.VERTICAL));
        ArrayList experienceVersions = prepareData();
        mAdapter = new ExperienceAdapter(c, experienceVersions);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private ArrayList prepareData() {

        ArrayList experience_version = new ArrayList<>();

        Experience experience = new Experience();
        experience.setTitle_company(getString(R.string.title_company1));
        experience.setCompany(getString(R.string.company1));
        experience.setYear_Company(getString(R.string.year_company1));
        experience.setContentinfo_company(getString(R.string.content_company1));
        experience.setImage_company(android_image_urls[0]);
        experience_version.add(experience);

        Experience experience1 = new Experience();
        experience1.setTitle_company(getString(R.string.title_company2));
        experience1.setCompany(getString(R.string.company2));
        experience1.setYear_Company(getString(R.string.year_company2));
        experience1.setContentinfo_company(getString(R.string.content_company2));
        experience1.setImage_company(android_image_urls[1]);
        experience_version.add(experience1);

        Experience experience2 = new Experience();
        experience2.setTitle_company(getString(R.string.title_company3));
        experience2.setCompany(getString(R.string.company3));
        experience2.setYear_Company(getString(R.string.year_company3));
        experience2.setContentinfo_company(getString(R.string.content_company3));
        experience2.setImage_company(android_image_urls[2]);
        experience_version.add(experience2);

        return experience_version;
    }
}
