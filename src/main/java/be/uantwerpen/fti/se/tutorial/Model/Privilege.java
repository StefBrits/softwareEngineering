package be.uantwerpen.fti.se.tutorial.Model;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Privilege extends AbstractPersistable<Long> implements GrantedAuthority {
    @Column
    private String name;
    @Column
    private String description;

    public Privilege() {
    }

    public Privilege(String name) {
        this.name = name;
    }

    public Privilege(String name, String desc) {
        this.name = name;
        this.description = desc;
    }

    // ***************
    // Getters
    // ***************

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // ***************
    // Setters
    // ***************

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}