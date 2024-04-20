package com.SAR.ReservationsSAR.context.user.infrastructure.persistence.jpa.entities;

import com.SAR.ReservationsSAR.context.auth.infrastructure.persistence.jpa.entities.SessionTokenEntity;
import com.SAR.ReservationsSAR.context.reservation.infrastructure.persistence.jpa.entities.ReservationEntity;
import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.context.user.domain.UserRole;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 60)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(unique = true, nullable = false, columnDefinition = "CHAR(9)")
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "VARCHAR(8)")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SessionTokenEntity> sessionTokens = new ArrayList<>();

    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL)
    private List<ReservationEntity> reservations = new ArrayList<>();

    @PrePersist
    public void setDefaultValues() {
        this.role = UserRole.USER;
    }

    public static UserEntity fromDomainModel(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .birthdate(user.getBirthdate())
                .role(user.getRole())
                .build();
    }

    public User toDomainModel() {
        return new User(id, firstName, lastName, birthdate, phoneNumber, role, email, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return true;
    }
}
