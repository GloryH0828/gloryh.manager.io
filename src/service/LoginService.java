package service;

import dao.LoginDao;
import domain.User;

public class LoginService {
    private LoginDao loginDao=new LoginDao();
    public User state(User user, String role) {
        User user1=loginDao.loginState(user.getUsername(),role);
        return user1;
    }
}
