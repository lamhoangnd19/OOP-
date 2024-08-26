package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;
import vn.viettuts.qlsv.dao.UserDao;
import vn.viettuts.qlsv.entity.User;
import vn.viettuts.qlsv.view.HomeView;
import vn.viettuts.qlsv.view.LoginView;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private HomeController homeController;
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
        loginView.clear();
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            HashMap<String, String> loginInfo = loginView.getUser();
            User user = userDao.getByUsername(loginInfo.get("username"));
            if (Objects.nonNull(user)) {
            	if (user.getPassword().equals(loginInfo.get("password"))) {
                    // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
            		HomeView homeView = new HomeView(user);
            		homeController = new HomeController(LoginController.this, homeView);
                    loginView.setVisible(false);	
                    homeController.showHome();
                    return;
            	}
                loginView.showMessage("Password không đúng.");
            } else {
                loginView.showMessage("Tài khoản không tồn tại");
            }
        }
    }
}
