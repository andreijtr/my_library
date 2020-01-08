package com.sda.library.dto;

import java.util.Set;

public class SubscriberDTO {
    private String firstName;
    private String surname;
    private Long personalID;

    public SubscriberDTO() {
    }

    public SubscriberDTO(String firstName, String surname, Long personalID) {
        this.firstName = firstName;
        this.surname = surname;
        this.personalID = personalID;
    }

    @Override
    public String toString() {
        return "SubscriberDTO{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", personalID=" + personalID +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getPersonalID() {
        return personalID;
    }

    public void setPersonalID(Long personalID) {
        this.personalID = personalID;
    }
}
