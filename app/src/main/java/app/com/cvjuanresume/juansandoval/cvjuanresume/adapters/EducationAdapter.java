package app.com.cvjuanresume.juansandoval.cvjuanresume.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import app.com.cvjuanresume.juansandoval.cvjuanresume.R;
import app.com.cvjuanresume.juansandoval.cvjuanresume.models.Education;
import app.com.cvjuanresume.juansandoval.cvjuanresume.utils.CustomizedTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jsandoval on 28/04/17.
 */

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder> {

    private List<Education> educationList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_u) CustomizedTextView title_u;
        @BindView(R.id.year) CustomizedTextView year;
        @BindView(R.id.college) CustomizedTextView college;
        @BindView(R.id.contentinfo) CustomizedTextView contentinfo;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public EducationAdapter(List<Education> educationList){
        this.educationList = educationList;
    }

    @Override
    public EducationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.education_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EducationAdapter.MyViewHolder holder, int position) {

        Education education = educationList.get(position);
        holder.title_u.setText(education.getTitle());
        holder.year.setText(education.getYear());
        holder.college.setText(education.getCollege());
        holder.contentinfo.setText(education.getContentinfo());
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }
}
