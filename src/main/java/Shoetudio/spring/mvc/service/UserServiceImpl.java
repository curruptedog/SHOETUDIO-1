package Shoetudio.spring.mvc.service;

import Shoetudio.spring.mvc.dao.UserDAO;
import Shoetudio.spring.mvc.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("usrv")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO udao;

    @Override
    public String newUser(User u) {
        String result = "회원정보 저장 실패";

        int cnt = udao.insertUser(u);
        if (cnt > 0) result = "회원정보 저장 성공";

        return result;
    }

    @Override
    public String findZipcode(String dong) {
        // daum 우편번호 찾기 이용
        return null;
    }

    @Override
    public String checkUserid(String uid) {

        return udao.selectOneUserid(uid) + "";
    }

    @Override
    public boolean checkLogin(User u, HttpSession sess) {
        boolean isLogin = false;

        if (udao.selectUser(u) > 0) {
            sess.setAttribute("UID", u.getUserid());
            isLogin = true;
        }

        return isLogin;
    }
}
