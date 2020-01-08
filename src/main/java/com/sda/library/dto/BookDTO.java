package com.sda.library.dto;

import java.util.Arrays;
import java.util.Set;

public class BookDTO {
    private String title;
    private int numberOfPages;
    private Integer volume;
    private String section;
    private Set<AuthorDTO> authorsDTO;
    private Boolean available;

    public BookDTO() {
    }

    public BookDTO(String title, int numberOfPages, Integer volume, String section, Set<AuthorDTO> authorsDTO) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.volume = volume;
        this.section = section;
        this.authorsDTO = authorsDTO;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", volume=" + volume +
                ", section='" + section + '\'' +
                ", authorsDTO=" + authorsDTO +
                ", available=" + available +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Set<AuthorDTO> getAuthorsDTO() {
        return authorsDTO;
    }

    public void setAuthorsDTO(Set<AuthorDTO> authorsDTO) {
        this.authorsDTO = authorsDTO;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
