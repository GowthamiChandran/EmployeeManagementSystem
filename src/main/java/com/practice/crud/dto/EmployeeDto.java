package com.practice.crud.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private long id;
    private String firstName;

    private String lastName;
    private String email;

    public AddressResponseDto getAddressResponseDto() {
        return addressResponseDto;
    }

    public void setAddressResponseDto(AddressResponseDto addressResponseDto) {
        this.addressResponseDto = addressResponseDto;
    }

    private AddressResponseDto addressResponseDto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
