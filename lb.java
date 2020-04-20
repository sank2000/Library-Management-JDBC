import java.sql.Date;
public class lb 
{
    Integer sid;
    Integer bid;
    Date td;
    Date rd;

    public lb(Integer sid, Integer bid, Date td, Date rd) {
        this.sid = sid;
        this.bid = bid;
        this.td = td;
        this.rd = rd;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Date getTd() {
        return td;
    }

    public void setTd(Date td) {
        this.td = td;
    }

    public Date getRd() {
        return rd;
    }

    public void setRd(Date rd) {
        this.rd = rd;
    }
    
    
}
