package com.panic.crudEvento.evento;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Table
@Entity
public class evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    private LocalDate fecha;
    @Transient
    private Integer antiguedad;

    public evento() {
    }

    public evento(Long id, String name, float price, LocalDate fecha) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fecha = fecha;
    }

    public evento(String name, float price, LocalDate fecha) {
        this.name = name;
        this.price = price;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getAntiguedad() {
        return Period.between(this.fecha, LocalDate.now()).getYears();
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }
}
