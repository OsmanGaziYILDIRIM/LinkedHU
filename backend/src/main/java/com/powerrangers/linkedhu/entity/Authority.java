package com.powerrangers.linkedhu.entity;

import com.powerrangers.linkedhu.entity.common.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> members;

    private String authority;

    public Authority(String authority) { this.authority = authority; }
    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}
