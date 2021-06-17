package Shoetudio.spring.mvc.dao;

import Shoetudio.spring.mvc.vo.Community;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("cmdao")
public class CommunityDAOImpl implements CommunityDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int insertCommunity(Community cm) {
        return sqlSession.insert("community.insertCommunity", cm);
    }

    @Override
    public int updateCommunity(Community cm) {
        return 0;
    }

    @Override
    public int deleteCommunity(String cno) {
        return sqlSession.delete("community.deleteCommunity", cno);
    }

    @Override
    public List<Community> selectCommunity(int cnum) {
        return sqlSession.selectList("community.selectCommunity", cnum);
    }

    @Override
    public List<Community> findSelectCommunity(Map<String, Object> param) {
        return sqlSession.selectList("community.findSelect", param);
    }

    @Override
    public Community selectOneCommunity(String cno) {
        return sqlSession.selectOne("community.selectOne", cno);
    }

    @Override
    public int selectCountCommunity() {
        return sqlSession.selectOne("community.countCommunity");
    }

    @Override
    public int selectCountCommunity(Map<String, Object> param) {
        return sqlSession.selectOne("community.findSelectCount", param);
    }

    @Override
    public int viewCountCommunity(String cno) {
        return sqlSession.update("community.viewsCommunity", cno);
    }
}
