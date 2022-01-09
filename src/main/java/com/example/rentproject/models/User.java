package com.example.rentproject.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "USER_UK", columnNames = "USER_EMAIL")
})
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Email(message = "Проверьте правильность ввода")
    @NotBlank(message = "Email не может быть пустым")
    @Column(name = "USER_EMAIL", nullable = false, length = 36)
    private String userEmail;

    @NotBlank(message = "Пароль не может быть пустым")
    @Column(name = "PASSWORD", nullable = false, length = 128)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "ACCOUNT_IS_ACTIVATED", nullable = false)
    private Boolean accountIsActivated = false;

    @Column(name = "ACCOUNT_NON_LOCKED", nullable = false)
    private Boolean accountNonLocked = false;

    @Column(name = "ACTIVATION_CODE", nullable = false, length = 128)
    private String activationCode;

    @NotBlank(message = "Имя не может быть пустым")
    @Column(name = "USERNAME", length = 36)
    private String username;

    @Column(name = "PHONE", length = 36)
    private String phone;

    @Column(name = "FIRST_NAME", length = 128)
    private String firstName;

    @Column(name = "SECOND_NAME", length = 128)
    private String secondName;

    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @Column(name = "DOB")
    private Date dob;

    @Column(name = "COUNTRY", length = 128)
    private String country;

    @Column(name = "CITY", length = 128)
    private String city;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getAccountIsActivated();
    }

    @Override
    public String getUsername() {
        return username;
    }
}