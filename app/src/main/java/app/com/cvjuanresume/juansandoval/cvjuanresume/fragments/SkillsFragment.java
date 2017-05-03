package app.com.cvjuanresume.juansandoval.cvjuanresume.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import app.com.cvjuanresume.juansandoval.cvjuanresume.R;
import app.com.cvjuanresume.juansandoval.cvjuanresume.utils.MyToast;

/**
 * Created by jsandoval on 19/04/17.
 */

public class SkillsFragment extends Fragment implements OnChartValueSelectedListener {

    private static String TAG = "SkillsFragment";
    Context c;

    private float[] yData = {30.0f, 50.0f, 10.0f, 10.0f};
    String[] xData;
    PieChart pieChart;

    public SkillsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        c = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        xData = new String[]{getString(R.string.skill_android), getString(R.string.skill_java), getString(R.string.skill_javascript), getString(R.string.HTLMCSS)};

        View rootView = inflater.inflate(R.layout.fragment_skills, container, false);


        pieChart = (PieChart) rootView.findViewById(R.id.idPieChart);
        //pieChart.setDescription(getString(R.id.idPieChart));
        pieChart.getDescription().setEnabled(false);
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText(getString(R.string.title_skills));
        pieChart.setCenterTextSize(10);
        // Inflate the layout for this fragment


        addDataSet();

        pieChart.setOnChartValueSelectedListener(this);

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

    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for (int i = 1; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, getString(R.string.title_skills));
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setValueTextSize(12);

        pieDataSet.setIconsOffset(new MPPointF(0, 40));
        pieDataSet.setSelectionShift(5f);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        //colors.add(Color.MAGENTA);
        //colors.add(Color.GREEN);
        //colors.add(Color.CYAN);
        //colors.add(Color.YELLOW);
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.d("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
        if (h.getX() == 0.0) {
            MyToast.makeText(c, getString(R.string.skill_android)+" "+e.getY() + "%" + " " + getString(R.string.based_on), Toast.LENGTH_LONG).show();
        } else if (h.getX() == 1.0) {
            MyToast.makeText(c, getString(R.string.skill_java)+" "+e.getY() + "%" + " " + getString(R.string.based_on), Toast.LENGTH_LONG).show();
        } else if (h.getX() == 2.0) {
            MyToast.makeText(c, getString(R.string.skill_javascript)+" "+e.getY() + "%" + " " + getString(R.string.based_on), Toast.LENGTH_LONG).show();
        } else if (h.getX() == 3.0) {
            MyToast.makeText(c, getString(R.string.HTLMCSS)+" "+e.getY() + "%" + " " + getString(R.string.based_on), Toast.LENGTH_LONG).show();
        }

        //Toast.makeText(c, h.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected() {
        Log.d("PieChart", "nothing selected");
    }
}
