package com.embarkx.embarkxfirstJobApp.review;

import com.embarkx.embarkxfirstJobApp.company.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double rating;

    @JsonIgnore
    @ManyToOne
    private Company company;

}
