package com.epam.brest.course.dto;

/**
 * POJO CrewDto
 */
public class CrewDto {

    /**
     * Property id.
     */
    private int crewId;

    /**
     * Property name.
     */
    private String crewName;

    /**
     * Constructor Crew.
     */
    public CrewDto() {
    }

    /**
     * Constryctor Crew.
     * @param crewId crew id
     * @param crewName Crew name
     */
    public CrewDto(int crewId, String crewName) {
        this.crewId = crewId;
        this.crewName = crewName;
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

    @Override
    public String toString() {
        return "CrewDto{" +
                "crewId=" + crewId +
                ", crewName='" + crewName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrewDto crewDto = (CrewDto) o;

        if (crewId != crewDto.crewId) return false;
        return crewName != null ? crewName.equals(crewDto.crewName) : crewDto.crewName == null;
    }

    @Override
    public int hashCode() {
        int result = crewId;
        result = 31 * result + (crewName != null ? crewName.hashCode() : 0);
        return result;
    }
}
