package com.epam.brest.course.dao;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class DatesRange {

    @NotNull(message = "has1 null")
    private Date dateFrom;
    @NotNull(message = "has2 null")
    private Date dateTo;

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public DatesRange() {
    }

    @Override
    public String toString() {
        return "DatesRange{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
