public class Student 
{
     Integer stu_id;
     String name;
     String dept;
     Integer year;
     Long phn;

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getPhn() {
        return phn;
    }

    public void setPhn(Long phn) {
        this.phn = phn;
    }

    public Student(Integer stu_id, String name, String dept, Integer year, Long phn) {
        this.stu_id = stu_id;
        this.name = name;
        this.dept = dept;
        this.year = year;
        this.phn = phn;
    }
}
