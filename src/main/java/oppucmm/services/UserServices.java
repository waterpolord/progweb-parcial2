package oppucmm.services;

import oppucmm.models.User;
import oppucmm.services.connect.DataBaseRepository;

public class UserServices extends DataBaseRepository<User> {
    private static UserServices userServices;

    public UserServices() { super(User.class); }

    public static UserServices getInstance(){
        if(userServices == null){
            userServices = new UserServices();
        }
        return userServices;
    }
}
