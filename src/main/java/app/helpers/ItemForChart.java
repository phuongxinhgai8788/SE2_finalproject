package app.helpers;

import java.sql.Date;

public class ItemForChart {
    private Date createdDate;
    private int sum;

    public ItemForChart(Date createdDate, int sum) {
        this.createdDate = createdDate;
        this.sum = sum;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
