package com.powerrangers.linkedhu.entity.common;

import com.powerrangers.linkedhu.entity.Authority;
import com.powerrangers.linkedhu.entity.Graduate;
import com.powerrangers.linkedhu.entity.Student;
import com.powerrangers.linkedhu.repository.AuthorityRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.awt.*;
import java.util.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name="type")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;                     //BıgInt Integer

    protected String username;
    protected String password;

    protected Byte usertype;

    public boolean isStudent() { return usertype == 1; }
    public boolean isGraduate() { return usertype == 2; }
    public boolean isAcademician() { return usertype == 3; }
    public boolean isAdmin() { return usertype == 4; }
    public boolean isStudentRepresentative() {
        if (!this.isStudent()) return false;

        for (Authority authority : this.authorities)
            if(authority.getAuthority().equals("REPRESENTATIVE"))
                return true;
        return false;
    }

    public void setStudentRespentative(Authority authority) {
        if (!this.isStudent()) return;

        this.authorities.add(authority);
    }

    public void deleteRepresentativeRole() {
        for (Authority authority : this.authorities)
            if(authority.getAuthority().equals("REPRESENTATIVE")) {
                this.authorities.remove(authority);
                break;
            }
        System.out.printf("User: %s student respentative Role deleted.\n", this.username);
    }

    protected String name;

    protected String surname;

    protected String birthdate;

    protected Byte gender;

    protected String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_LOGIN")
    protected Date lastLogin;

    //@Column(columnDefinition = "text")
    public String text;

    public User(Byte usertype, String username, String password, String name, String surname, String birthdate, Byte gender, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.usertype = usertype;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITIES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @ElementCollection
    @CollectionTable(
            name="userblocks",
            joinColumns=@JoinColumn(name="OWNER_ID")
    )
    private List<Long> blockedUser;

    public boolean isBlockedUser(Long userId) { return blockedUser.contains((long)userId); }
    public void addBlockedUser(Long userId) { blockedUser.add(userId); }
    public void removeBlockedUser(Long userId) { blockedUser.remove(userId); }


    private Boolean Enable;

    private String avatarID;

    @Override
    public String getUsername() {
        return this.username;
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
    public boolean isEnabled() { return true; }

    // STUDENT METHOD
    public Byte getDepartment() {
        if(!this.isStudent()) return null;
        Student student = (Student)this;
        return student.getDepartment();
    }

    // STUDENT METHOD
    public Byte getDegree() {
        if(!this.isStudent()) return null;
        Student student = (Student)this;
        return student.getDegree();
    }

    // GRADUATE METHOD
    public String getGraduate_year() {
        if(!this.isGraduate()) return null;
        Graduate graduate = (Graduate)this;
        return graduate.getGraduate_year();
    }

    // STUDENT METHOD
    public void setDepartment(Byte department) {
        if(!this.isStudent()) return;
        Student student = (Student)this;
        student.setDepartment(department);
    }

    // STUDENT METHOD
    public void setDegree(Byte degree) {
        if(!this.isStudent()) return;
        Student student = (Student)this;
        student.setDegree(degree);
    }

    // GRADUATE METHOD
    public void setGraduate_year(String graduate_year) {
        if(!this.isGraduate()) return;
        Graduate graduate = (Graduate)this;
        graduate.setGraduate_year(graduate_year);
    }
}
