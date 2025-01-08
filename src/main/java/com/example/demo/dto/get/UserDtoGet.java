package com.example.demo.dto.get;

import com.example.demo.dto.DemarcationDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDtoGet {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String contact;
    private String role;
    private String nic;
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private DemarcationDto demarcationDto;
}
