package com.epam.brest.course.dto;

public class CrewDtoWithCall {

    /**
     * Property id.
     */
    private int crewId;

    /**
     * Property name.
     */
    private String crewName;

    /**
     * Property description.
     */
    private String description;

    /**
     * Property carId.
     */
    private int numberOfCalls;

    /**
     * Constructor CrewDtoWithCall.
     */
    public CrewDtoWithCall() {
    }

    /**
     * Constryctor CrewDtoWithCall.
     * @param crewName Crew name
     * @param description Some description
     * @param numberOfCalls number of calls for crew.
     */
    public CrewDtoWithCall(String crewName, String description, int numberOfCalls) {
        this.crewName = crewName;
        this.description = description;
        this.numberOfCalls = numberOfCalls;
    }

    /**
     * Get crew id.
     * @return Unique identifier.
     */
    public final int getCrewId() {
        return crewId;
    }

    /**
     * Set crewId.
     * @param crewId Unique identifier.
     */
    public final void setCrewId(final int crewId) {
        this.crewId = crewId;
    }

    /**
     * Get crew name.
     * @return Crew name.
     */
    public final String getCrewName() {
        return crewName;
    }

    /**
     * Set crew name.
     * @param crewName Crew name.
     */
    public final void setCrewName(final String crewName) {
        this.crewName = crewName;
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
     * Get car id.
     * @return number of calls for crew.
     */
    public final int getNumberOfCalls() {
        return numberOfCalls;
    }

    /**
     * Set car id.
     * @param numberOfCalls number of calls for crew.
     */
    public final void setNumberOfCalls(final int numberOfCalls) {
        this.numberOfCalls = numberOfCalls;
    }

    @Override
    public String toString() {
        return "CrewDtoWithCall{" +
                "crewId=" + crewId +
                ", crewName='" + crewName + '\'' +
                ", description='" + description + '\'' +
                ", numberOfCalls=" + numberOfCalls +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrewDtoWithCall that = (CrewDtoWithCall) o;

        if (crewId != that.crewId) return false;
        if (numberOfCalls != that.numberOfCalls) return false;
        if (crewName != null ? !crewName.equals(that.crewName) : that.crewName != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = crewId;
        result = 31 * result + (crewName != null ? crewName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + numberOfCalls;
        return result;
    }
}
