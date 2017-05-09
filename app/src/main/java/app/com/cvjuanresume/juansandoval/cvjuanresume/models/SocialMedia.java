package app.com.cvjuanresume.juansandoval.cvjuanresume.models;

/**
 * Created by jsandoval on 8/05/17.
 */

public class SocialMedia {
    private String nameSocial;
    private int thumbnail;

    public SocialMedia(){

    }

    public SocialMedia(String nameSocial, int thumbnail){
        this.nameSocial = nameSocial;
        this.thumbnail = thumbnail;
    }

    public String getNameSocial() {
        return nameSocial;
    }

    public void setNameSocial(String nameSocial) {
        this.nameSocial = nameSocial;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
