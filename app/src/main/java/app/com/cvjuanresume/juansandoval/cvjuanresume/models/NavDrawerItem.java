package app.com.cvjuanresume.juansandoval.cvjuanresume.models;

/**
 * Created by jsandoval on 18/04/17.
 */

public class NavDrawerItem {

    private boolean showNotify;
    private String titile;

    public NavDrawerItem(){

    }

    public NavDrawerItem(boolean showNotify, String titile){
        this.showNotify = showNotify;
        this.titile = titile;
    }

    public boolean isShowNotify(){
        return showNotify;
    }

    public void setShowNotify(boolean showNotify){
        this.showNotify = showNotify;
    }

    public String getTitile(){
        return titile;
    }

    public void setTitle(String titile){
        this.titile=titile;
    }
}
