package app.com.cvjuanresume.juansandoval.cvjuanresume.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linkedin.platform.DeepLinkHelper;
import com.linkedin.platform.errors.LIDeepLinkError;
import com.linkedin.platform.listeners.DeepLinkListener;

import java.util.List;

import app.com.cvjuanresume.juansandoval.cvjuanresume.R;
import app.com.cvjuanresume.juansandoval.cvjuanresume.models.SocialMedia;

/**
 * Created by jsandoval on 8/05/17.
 */

public class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.MyViewHolder> {


    private Context mContext;
    private List<SocialMedia> socialMedias;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_social);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }

    }

    public SocialMediaAdapter(Context mContext, List<SocialMedia> socialMedias) {
        this.mContext = mContext;
        this.socialMedias = socialMedias;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.social_media_content, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        SocialMedia socialMedia = socialMedias.get(position);
        holder.title.setText(socialMedia.getNameSocial());

        Glide.with(mContext).load(socialMedia.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(holder.overflow);
            }
        });

    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.action_add_facebook:
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(mContext);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    mContext.startActivity(facebookIntent);
                    return true;
                case R.id.action_add_linkedin:
                    getLinkedinAccount(mContext);
                    return true;
                case R.id.action_add_youtube:
                    getYoutubeChannel();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return socialMedias.size();
    }

    public String getFacebookPageURL(Context context) {
        String FACEBOOK_URL = "https://www.facebook.com/costa27";
        String FACEBOOK_PAGE_ID = "586245740";

        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }

    public String getLinkedinAccount(Context context){
        String targetID = "juan-sandoval-a33955a5";
        DeepLinkHelper deepLinkHelper = DeepLinkHelper.getInstance();
        deepLinkHelper.openOtherProfile(context, targetID, new DeepLinkListener() {
            @Override
            public void onDeepLinkSuccess() {
                Intent LinkedinIntent = new Intent(Intent.ACTION_VIEW);
                mContext.startActivity(LinkedinIntent);
            }

            @Override
            public void onDeepLinkError(LIDeepLinkError error) {

            }
        });
        return targetID;
    }

    public void getYoutubeChannel(){
        String url = "https://www.youtube.com/channel/UCuEi9WK2ZEyFe3xUnwhFmwA";
        Intent intent;
        try {
            intent =new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse(url));
            mContext.startActivity(intent);
        }catch (ActivityNotFoundException e) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            mContext.startActivity(intent);
        }
    }
}
