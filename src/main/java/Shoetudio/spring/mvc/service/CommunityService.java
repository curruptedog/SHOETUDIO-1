package Shoetudio.spring.mvc.service;

import Shoetudio.spring.mvc.vo.Community;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityService {

    boolean newCommunity(Community c, MultipartFile[] img);
    boolean modifyCommunity(Community c);
    boolean removeCommunity(String cno);

    List<Community> readCommunity(String cp);
    List<Community> readCommunity(String cp, String ftype, String fkey);
    Community readOneCommunity(String cno);

    int countCommunity();
    int countCommunity(String ftype, String fkey);
    boolean viewCountCommunity(String cno);

}
