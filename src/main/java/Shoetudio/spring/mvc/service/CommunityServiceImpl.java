package Shoetudio.spring.mvc.service;

import Shoetudio.spring.mvc.dao.CommunityDAO;
import Shoetudio.spring.mvc.util.ImgUploadUtil;
import Shoetudio.spring.mvc.vo.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("csrv")
public class CommunityServiceImpl implements CommunityService {

    private CommunityDAO cdao;
    private ImgUploadUtil imgutil;

    @Autowired
    public CommunityServiceImpl(CommunityDAO cdao, ImgUploadUtil imgutil) {
        this.cdao = cdao;
        this.imgutil = imgutil;
    }

    @Override
    public boolean newCommunity(Community c, MultipartFile[] img) {
        String uuid = imgutil.makeUUID();
        if(imgutil.checkGalleryFiles(img)) {
            List<String> imgs = new ArrayList<>();

            for(MultipartFile f : img) {
                if (!f.getOriginalFilename().isEmpty())
                    imgs.add(imgutil.ImageUpload(f, uuid));
                else
                    imgs.add("-/-");
            }   // for
            String fnames = "";
            String fsizes = "";

            for (int i = 0; i < imgs.size(); ++i) {
                fnames += imgs.get(i).split("[/]")[0] + "/";
                fsizes += imgs.get(i).split("[/]")[0] + "/";
            }

            c.setFnames( fnames );
            c.setFsizes( fsizes );
            c.setUuid(uuid);

        }

        int id = cdao.insertCommunity(c);
        String ofname = c.getFnames().split("/")[0];
        int pos = ofname.lastIndexOf(".");
        String fname = ofname.substring(0, pos+1);
        String fext = ofname.substring(pos+1);
        ofname = fname + uuid + "." + fext;

        imgutil.imageCropResize(ofname, id + "");

        return true;
    }

    @Override
    public boolean modifyCommunity(Community c) {
        boolean isUpdated = false;
        if (cdao.updateCommunity(c) > 0) isUpdated = true;

        return isUpdated;
    }

    @Override   // ?
    public boolean removeCommunity(String cno) {
        boolean isUpdated = false;
        Community c = cdao.selectOneCommunity(cno);
        if (cdao.deleteCommunity(cno) > 0) isUpdated = true;

        return isUpdated;
    }

    @Override
    public List<Community> readCommunity(String cp) {
        int cnum = (Integer.parseInt(cp) - 1) * 20;

        return cdao.selectCommunity(cnum);
    }

    @Override
    public List<Community> readCommunity(String cp, String ftype, String fkey) {
        int cnum = (Integer.parseInt(cp) - 1) * 20;

        Map<String, Object> params = new HashMap<>();
        params.put("cnum", cnum);
        params.put("ftype", ftype);
        params.put("fkey", fkey);

        return cdao.findSelectCommunity(params);
    }

    @Override
    public Community readOneCommunity(String cno) {
        return cdao.selectOneCommunity(cno);
    }

    @Override
    public int countCommunity() {
        return cdao.selectCountCommunity();
    }

    @Override
    public int countCommunity(String ftype, String fkey) {
        Map<String, Object> params = new HashMap<>();
        params.put("ftype", ftype);
        params.put("fkey", fkey);

        return cdao.selectCountCommunity();
    }

    @Override
    public boolean viewCountCommunity(String cno) {
        boolean isUpdate = false;
        if (cdao.viewCountCommunity(cno) > 0) isUpdate = true;
        return isUpdate;
    }
}
