package servlet;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author gaorj
 * @date 2018/9/4 18:48
 * @description
 */
@Controller
@RequestMapping("new/")
public class NewController {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping("upload")
    public @ResponseBody Map upload(@RequestParam(value = "file")MultipartFile[] file) {
        Map map = new HashMap();
        map.put("flag","success");
        for (MultipartFile f : file) {
            String filename = f.getOriginalFilename();//原始名称
            filename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));//存储到服务器的文件名称
            String path = "E:/temp/"+ filename;
            try {
                f.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
                map.put("flag","failure");
            }
        }
        return map;
    }
}
