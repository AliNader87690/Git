package com.example.swtprojekt2.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data

public class PassData {
    private String fhkennung;
    private String fhpasswort;

    public PassData(String fhkennung, String fhpasswort) {
        this.fhkennung = fhkennung;
        this.fhpasswort = fhpasswort;
    }

    public PassData() {
    }
}
