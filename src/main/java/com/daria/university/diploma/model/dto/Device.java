package com.daria.university.diploma.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "devices")
public class Device implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotEmpty
    private String label;

    @NotEmpty
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties({"longitude", "latitude" })
    private Location location;

    @Column(name = "active")
    private boolean isActive;

    public Device() {
    }

    public Device(Long id, String label, String type, Location location) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.location = location;
    }

    public Device(String label, String type, Location location) {
        //this.id = id;
        this.label = label;
        this.type = type;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
