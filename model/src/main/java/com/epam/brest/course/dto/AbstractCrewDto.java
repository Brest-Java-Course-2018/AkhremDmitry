package com.epam.brest.course.dto;

/**
 * AbstractCrewDto.
 */
public abstract class AbstractCrewDto {

    /**
     * Property id.
     */
    protected int crewId;

    /**
     * Property name.
     */
    protected String crewName;

    /**
     * Get crew id.
     * @return Unique identifier.
     */
    public int getCrewId() {
        return crewId;
    }

    /**
     * Set crewId.
     * @param crewId Unique identifier.
     */
    public void setCrewId(final int crewId) {
        this.crewId = crewId;
    }

    /**
     * Get crew name.
     * @return Crew name.
     */
    public String getCrewName() {
        return crewName;
    }

    /**
     * Set crew name.
     * @param crewName Crew name.
     */
    public void setCrewName(final String crewName) {
        this.crewName = crewName;
    }
}
