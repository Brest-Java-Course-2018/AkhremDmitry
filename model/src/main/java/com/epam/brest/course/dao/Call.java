package com.epam.brest.course.dao;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
    private Date dateCall;

    /**
     * Property description.
     */
    private String description;

    /**
     * Property address.
     */
    @Size(min = 3, message = "Address cannot contain less "
            + "than 3 characters.")
    @Size(max = 255, message = "Address cannot contain more "
            + "than 255 characters.")
    private String address;

    /**
     * Property crewId.
     */
    @Positive
    private int crewId;

    /**
     * Constructor Call.
     */
    public Call() {
    }

    /**
     * Constructor Call.
     * @param dateCall Date of call
     * @param description Some description
     * @param address The address from which the call was received
     * @param crewId The crew that was sent to the call.
     */
    public Call(final Date dateCall,
                final String description,
                final String address,
                final int crewId) {
        this.dateCall = dateCall;
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
    public final Date getDateCall() {
        return dateCall;
    }

    /**
     * Set date.
     * @param dateCall Date of call.
     */
    public final void setDateCall(final Date dateCall) {
        this.dateCall = dateCall;
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
                ", dateCall=" + dateCall +
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
        if (dateCall != null ? !dateCall.equals(call.dateCall) : call.dateCall != null) return false;
        if (description != null ? !description.equals(call.description) : call.description != null) return false;
        return address != null ? address.equals(call.address) : call.address == null;
    }

    @Override
    public int hashCode() {
        int result = callId;
        result = 31 * result + (dateCall != null ? dateCall.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + crewId;
        return result;
    }
}
