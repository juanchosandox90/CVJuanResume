package app.com.cvjuanresume.juansandoval.cvjuanresume.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.com.cvjuanresume.juansandoval.cvjuanresume.R;
import app.com.cvjuanresume.juansandoval.cvjuanresume.adapters.SocialMediaAdapter;
import app.com.cvjuanresume.juansandoval.cvjuanresume.models.SocialMedia;

/**
 * Created by jsandoval on 19/04/17.
 */

public class SocialMediaFragment extends Fragment {

    public RecyclerView recyclerView;
    private SocialMediaAdapter adapter;
    private List<SocialMedia> socialMedias;
    Context c;

    public SocialMediaFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_socialmedia, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_social);
        socialMedias = new ArrayList<>();
        adapter = new SocialMediaAdapter(c, socialMedias);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(c, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        prepareSocial();

        // Inflate the layout for this fragment

        return rootView;
    }

    private void prepareSocial() {
        int[] social = new int[]{
                R.drawable.facebookcover,
                R.drawable.linkedincover,
                R.drawable.youtubecover,
        };

        SocialMedia a = new SocialMedia(getString(R.string.facebook_media), social[0]);
        socialMedias.add(a);

        a = new SocialMedia(getString(R.string.linkedin_media),  social[1]);
        socialMedias.add(a);

        a = new SocialMedia(getString(R.string.youtube_media), social[2]);
        socialMedias.add(a);

        adapter.notifyDataSetChanged();
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
