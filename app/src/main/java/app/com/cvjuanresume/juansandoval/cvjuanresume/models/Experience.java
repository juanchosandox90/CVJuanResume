package app.com.cvjuanresume.juansandoval.cvjuanresume.models;

/**
 * Created by jsandoval on 12/05/17.
 */

public class Experience  {
    private String title_company, year, company, contentinfo_company, image_company;

    public Experience(){

    }

    public Experience(String title_company, String year, String company, String contentinfo_company, String image_company){
        this.title_company = title_company;
        this.year = year;
        this.company = company;
        this.contentinfo_company = contentinfo_company;
        this.image_company = image_company;
    }

    public String getTitle_company() {
        return title_company;
    }

    public void setTitle_company(String title_company) {
        this.title_company = title_company;
    }

    public String getYear_Company() {
        return year;
    }

    public void setYear_Company(String year) {
        this.year = year;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContentinfo_company() {
        return contentinfo_company;
    }

    public void setContentinfo_company(String contentinfo_company) {
        this.contentinfo_company = contentinfo_company;
    }

    public String getImage_company() {
        return image_company;
    }

    public void setImage_company(String image_company) {
        this.image_company = image_company;
    }
}
