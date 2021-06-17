package Shoetudio.spring.mvc.util;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("imgutil")
public class ImgUploadUtil {

    // 이미지 업로드 경로 설정
    private String uploadPath = "";
    private String thumbnailPath = "";

    public boolean checkGalleryFiles(MultipartFile[] img) {
        boolean isFiles = false;
        //
        for(MultipartFile f : img) {

            if (!f.getOriginalFilename().isEmpty()) {
                isFiles = true;
                break;
            }
        }

        return isFiles;

    }

    public String ImageUpload(MultipartFile f, String uuid) {

        String ofname = f.getOriginalFilename();
        int pos = ofname.lastIndexOf(".");
        String nfname = ofname.substring(0, pos) + uuid + "." + ofname.substring(pos+1);    // 파일명 생성

        try {
            f.transferTo(new File(uploadPath + nfname));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ofname + "/" + (f.getSize()/1024);
    }

    public void imageCropResize(String fname, String id) {

        String ofname = uploadPath + fname;

        String imgtype = fname.substring(fname.lastIndexOf(".")+1);

        String tfname = thumbnailPath + "small_" + id + "_" + fname;

        try {

            BufferedImage image = ImageIO.read(new File(ofname));

            int imgwidth = Math.min(image.getHeight(), image.getWidth());
            int imgheight = imgwidth;

            BufferedImage scaledImg = Scalr.crop( image,
                    (image.getWidth() - imgwidth) / 2,
                    (image.getHeight() - imgheight) / 2,
                    imgwidth, imgheight, null );

            BufferedImage resizedImg = Scalr.resize(
                    scaledImg, 220, 220, null);

            ImageIO.write(resizedImg, imgtype, new File(tfname));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public String makeUUID() {
        String fmt = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);

        return sdf.format(new Date());
    }

}



