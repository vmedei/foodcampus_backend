package com.ps.foodcampus.domain.model;

import com.ps.foodcampus.domain.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "senha", nullable = false)
    private String password;
    @Column(name = "tipo", nullable = false)
    private String type;
    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;
    @OneToOne(mappedBy = "user")
    private Seller seller;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.type.toUpperCase().equals(UserTypeEnum.VENDEDOR.toString())) {
            return List.of(new SimpleGrantedAuthority("ROLE_VENDEDOR"), new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
