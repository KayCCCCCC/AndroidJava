package hieutt.com.sqlitedatabase;

public class Job {
    private int IdCv;
    private String TenCv;

    public Job(int idCv, String tenCv) {
        IdCv = idCv;
        TenCv = tenCv;
    }

    public int getIdCv() {
        return IdCv;
    }

    public void setIdCv(int idCv) {
        IdCv = idCv;
    }

    public String getTenCv() {
        return TenCv;
    }

    public void setTenCv(String tenCv) {
        TenCv = tenCv;
    }


}
