package com.epam.brest.course.dao;

import java.sql.Date;

public class DatesRange {

    private Date dateFrom;

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
}
