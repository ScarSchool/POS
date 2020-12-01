package com.scarc._lombok_tomassetti.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private String name;
    private String zipTown;
    private String street;
    private String phone;
    private String eMail;
    private String replyTo;
    private String comments;

    private List<Participation> participatesIn;
}
