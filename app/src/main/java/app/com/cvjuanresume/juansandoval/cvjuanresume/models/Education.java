package app.com.cvjuanresume.juansandoval.cvjuanresume.models;

/**
 * Created by jsandoval on 28/04/17.
 */

public class Education {
    private String title, year, college, contentinfo;

    public Education(){

    }

    public Education(String title, String year, String college, String contentinfo){
        this.title = title;
        this.year = year;
        this.college = college;
        this.contentinfo = contentinfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getContentinfo() {
        return contentinfo;
    }

    public void setContentinfo(String content) {
        this.contentinfo = content;
    }
}
