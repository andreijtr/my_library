package com.sda.library.dto;

import java.util.Set;

public class AuthorDTO {
    private String firstName;
    private String surname;
    private String country;

    public AuthorDTO(String firstName, String surname, String country) {
        this.firstName = firstName;
        this.surname = surname;
        this.country = country;
    }

    public AuthorDTO() {
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
