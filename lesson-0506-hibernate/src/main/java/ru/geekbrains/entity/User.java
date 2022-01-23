package ru.geekbrains.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // полу для хранения Enum (UserType.java)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    // mappedBy  обязателен, в единственном числе. Без разницы в классе User или Role.
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    // mappedBy = "user" указывает на то, что это двусторонняя связь
    // cascade = {CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REMOVE} см INSERT 2 из Main06
    // orphanRemoval = true  см DELETE 2 из Main06
    // CascadeType.REMOVE  используется для DELETE 3. Без этого DELETE 3 работать не будет, появится ошибка
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<Contact> contacts;

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    // переопределили equals() и hashCode() т.к. используем множество (Set) в @ManyToMany
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
