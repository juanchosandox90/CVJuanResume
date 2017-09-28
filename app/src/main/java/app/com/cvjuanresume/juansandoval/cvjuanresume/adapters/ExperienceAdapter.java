package app.com.cvjuanresume.juansandoval.cvjuanresume.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.com.cvjuanresume.juansandoval.cvjuanresume.R;
import app.com.cvjuanresume.juansandoval.cvjuanresume.models.Experience;
import app.com.cvjuanresume.juansandoval.cvjuanresume.utils.CustomizedTextView;

/**
 * Created by jsandoval on 12/05/17.
 */

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder> {

    private List<Experience> experienceList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CustomizedTextView title_company, year_company, company, contentinfo_company;
        public ImageView image_company;

        public MyViewHolder(View view) {
            super(view);
            title_company = (CustomizedTextView) view.findViewById(R.id.title_company);
            year_company = (CustomizedTextView) view.findViewById(R.id.yearcompany);
            company = (CustomizedTextView) view.findViewById(R.id.company);
            contentinfo_company = (CustomizedTextView) view.findViewById(R.id.experienceinfo);
            image_company = (ImageView) view.findViewById(R.id.image_company);
        }
    }

    public ExperienceAdapter(Context context, List<Experience> experienceList) {
        this.experienceList = experienceList;
        this.context = context;
    }

    @Override
    public ExperienceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.experience_list_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ExperienceAdapter.MyViewHolder holder, int position) {
        holder.title_company.setText(experienceList.get(position).getTitle_company());
        holder.year_company.setText(experienceList.get(position).getYear_Company());
        holder.company.setText(experienceList.get(position).getCompany());
        holder.contentinfo_company.setText(experienceList.get(position).getContentinfo_company());
        Picasso.with(context).load(experienceList.get(position).getImage_company()).resize(250,110).into(holder.image_company);
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

}
