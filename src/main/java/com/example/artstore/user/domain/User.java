package com.example.artstore.user.domain;

import com.example.artstore.book.basebook.BookReview;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
public class User implements Observer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    @Size(min = 2)
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotNull
    @Size(min = 2)
    private String lastName;

    @Column(name = "USERNAME")
    @NotNull
    @Size(min = 3)
    private String username;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "EMAIL")
    @NotNull
    @Email
    private String email;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> roleEntities = new HashSet<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    private UserCart userCart = new UserCart();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private List<BookReview> bookReviews = new ArrayList<>();
    public void addRole(UserRole roleEntity) {
        this.roleEntities.add(roleEntity);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private List<UserNotification> notifications = new ArrayList<>();

    @Override
    public void update(String content) {
        UserNotification notification = UserNotification.builder()
                .content(content)
                .build();
        this.notifications.add(notification);
    }
}
