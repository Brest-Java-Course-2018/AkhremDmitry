package com.epam.brest.course.dto;

public class CrewDtoWithCall extends AbstractCrewDto{

    /**
     * Property description.
     */
    private String description;

    /**
     * Property number of calls.
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
                "description='" + description + '\'' +
                ", numberOfCalls=" + numberOfCalls +
                ", crewId=" + crewId +
                ", crewName='" + crewName + '\'' +
                '}';
    }
}
