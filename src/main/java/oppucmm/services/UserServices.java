package oppucmm.services;

import oppucmm.models.User;
import oppucmm.services.connect.DataBaseRepository;

public class UserServices extends DataBaseRepository<User> {
    public UserServices() { super(User.class); }
}
