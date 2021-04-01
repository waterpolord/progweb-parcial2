package oppucmm.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Entity
@Table(name = "User")
public class User implements Serializable {
    @Id
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<RoleApp> rolesList;

    /*Constructor empty*/
    public User() {

    }
    /*To login*/
    public User(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }
    /*To register*/
    public User(String fullName, String username, String password, Set<RoleApp> rolesList) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.rolesList = rolesList;
    }
    /*Getters and setters*/
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Set<RoleApp> getRolesList() { return rolesList; }

    public void setRolesList(Set<RoleApp> rolesList) { this.rolesList = rolesList; }
}
