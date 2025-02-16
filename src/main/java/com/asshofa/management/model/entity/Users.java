package com.asshofa.management.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "smallserial")
    private Short id;

    private String username;
    private String password;
    private String gambar;
    private String role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
