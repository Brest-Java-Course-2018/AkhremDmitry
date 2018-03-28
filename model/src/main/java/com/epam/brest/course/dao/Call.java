package com.epam.brest.course.dao;

import java.sql.Date;

/**
 * POJO Call.
 */
public class Call {

    /**
     * Property id.
     */
    private int callId;

    /**
     * Property date.
     */
    private Date date;

    /**
     * Property description.
     */
    private String description;

    /**
     * Property address.
     */
    private String address;

    /**
     * Property crewId.
     */
    private int crewId;

    /**
     * Constructor Call.
     */
    public Call() {
    }

    /**
     * Constructor Call.
     * @param date Date of call
     * @param description Some description
     * @param address The address from which the call was received
     * @param crewId The crew that was sent to the call.
     */
    public Call(final Date date,
                final String description,
                final String address,
                final int crewId) {
        this.date = date;
        this.description = description;
        this.address = address;
        this.crewId = crewId;
    }

    /**
     * Get call id.
     * @return CallId.
     */
    public final int getCallId() {
        return callId;
    }

    /**
     * Set call id.
     * @param callId CallId.
     */
    public final void setCallId(final int callId) {
        this.callId = callId;
    }

    /**
     * Get date.
     * @return Date of call.
     */
    public final Date getDate() {
        return date;
    }

    /**
     * Set date.
     * @param date Date of call.
     */
    public final void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Get description.
     * @return Some description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Set description.
     * @param description Some description.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get address.
     * @return Address.
     */
    public final String getAddress() {
        return address;
    }

    /**
     * Set address.
     * @param address address.
     */
    public final void setAddress(final String address) {
        this.address = address;
    }

    /**
     * Get crew id.
     * @return CrewId.
     */
    public final int getCrewId() {
        return crewId;
    }

    /**
     * Set crew id.
     * @param crewId CrewId.
     */
    public final void setCrewId(final int crewId) {
        this.crewId = crewId;
    }

    @Override
    public String toString() {
        return "Call{" +
                "callId=" + callId +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", crewId=" + crewId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        if (callId != call.callId) return false;
        if (crewId != call.crewId) return false;
        if (date != null ? !date.equals(call.date) : call.date != null) return false;
        if (description != null ? !description.equals(call.description) : call.description != null) return false;
        return address != null ? address.equals(call.address) : call.address == null;
    }

    @Override
    public int hashCode() {
        int result = callId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + crewId;
        return result;
    }
}
