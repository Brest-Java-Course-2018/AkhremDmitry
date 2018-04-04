package com.epam.brest.course.dto;

/**
 * POJO CrewDto
 */
public class CrewDto extends AbstractCrewDto{

    /**
     * Constructor Crew.
     */
    public CrewDto() {
    }

    /**
     * Constryctor Crew.
     * @param crewName Crew name
     */
    public CrewDto(String crewName) {
        this.crewName = crewName;
    }

    @Override
    public String toString() {
        return "CrewDto{" +
                "crewId=" + crewId +
                ", crewName='" + crewName + '\'' +
                '}';
    }
}
