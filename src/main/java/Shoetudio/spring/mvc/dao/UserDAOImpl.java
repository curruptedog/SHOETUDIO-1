package Shoetudio.spring.mvc.dao;

import Shoetudio.spring.mvc.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("udao")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int insertUser(User u) {
        return sqlSession.insert("user.insertUser", u);
    }

    @Override
    public int selectOneUserid(String uid) {

        return sqlSession.selectOne("user.checkUserid", uid);
    }

    @Override
    public int selectUser(User u) {
        return sqlSession.selectOne("user.checkUser", u);
    }
}
