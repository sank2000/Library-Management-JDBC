public class Book 
{
    Integer b_id;
    String b_name;
    String author;
    String section;
    Integer copies;

    public Book(Integer b_id, String b_name, String author, String section, Integer copies) {
        this.b_id = b_id;
        this.b_name = b_name;
        this.author = author;
        this.section = section;
        this.copies = copies;
    }

    public Integer getB_id() {
        return b_id;
    }

    public void setB_id(Integer b_id) {
        this.b_id = b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

   
}
