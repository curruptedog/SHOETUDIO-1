package Shoetudio.spring.mvc.service;

import Shoetudio.spring.mvc.dao.ArtistDAO;
import Shoetudio.spring.mvc.vo.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("asrv")
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistDAO adao;

    @Override
    public String newArtist(Artist a) {
        String result = "작가 정보 저장 실패";

        int cnt = adao.insertArtist(a);
        if (cnt > 0) result = "작가 정보 저장 성공";

        return result;
    }

    @Override
    public String checkAuid(String aid) {
        return adao.selectOneArtid(aid)+"";
    }

    @Override
    public boolean checkLogin(Artist a, HttpSession sess) {
        boolean isLogin = false;

        if (adao.selectLogin(a) > 0) {
            sess.setAttribute("UID", a.getAuserid());
            isLogin = true;
        }

        return isLogin;
    }
}
