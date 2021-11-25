package com.example.Jobs.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_of_site")
    private String name_of_site;

    @Column(name="criteria")
    private String criteria;

    @Column(name="criteria_value")
    private String criteria_value;

    @Column(name="period")
    private int period;

    @Column(name="period_mod")
    private int period_mod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_of_site() {
        return name_of_site;
    }

    public void setName_of_site(String name_of_site) {
        this.name_of_site = name_of_site;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria_value() {
        return criteria_value;
    }

    public void setCriteria_value(String criteria_value) {
        this.criteria_value = criteria_value;
    }

    public Job() {
    }

    public Job(Long id, String name_of_site, String criteria, String criteria_value, int period) {
        this.id = id;
        this.name_of_site = name_of_site;
        this.criteria = criteria;
        this.criteria_value = criteria_value;
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod_mod() {
        return period_mod;
    }

    public void setPeriod_mod(int period_mod) {
        this.period_mod = period_mod;
    }
}
