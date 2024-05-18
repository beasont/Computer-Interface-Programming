package com.NWAS.API.CommunityMembers;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL) // only include non-null properties in JSON serialization
@Entity
public class CommunityMember {

    private String name;
    private String surname;
    private int age;
    private String addressNumber;
    private String phoneNumber;
    private LocalDate hasLivedHereSince; // duration that member has lived in the community
    @Id
    @GeneratedValue
    private Long id;

    public CommunityMember() {
    }

    public CommunityMember(String name, String surname, int age, String addressNumber, String phoneNumber, LocalDate hasLivedHereSince) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.addressNumber = addressNumber;
        this.phoneNumber = phoneNumber;
        this.hasLivedHereSince = hasLivedHereSince;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getHasLivedHereSince() {
        return hasLivedHereSince;
    }

    public void setHasLivedHereSince(LocalDate hasLivedHereSince) {
        this.hasLivedHereSince = hasLivedHereSince;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
