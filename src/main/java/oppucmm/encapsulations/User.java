package oppucmm.encapsulations;

import oppucmm.utilities.RolesApp;

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
    private Set<RolesApp> rolesList;

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
    public User(String fullName, String username, String password, Set<RolesApp> rolesList) {
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

    public Set<RolesApp> getRolesList() { return rolesList; }

    public void setRolesList(Set<RolesApp> rolesList) { this.rolesList = rolesList; }
}
