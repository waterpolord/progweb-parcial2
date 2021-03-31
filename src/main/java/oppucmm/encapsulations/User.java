package oppucmm.encapsulations;

import oppucmm.utilities.RolesApp;

import java.util.Set;

public class User {
    private String fullName;
    private String username;
    private String password;
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
