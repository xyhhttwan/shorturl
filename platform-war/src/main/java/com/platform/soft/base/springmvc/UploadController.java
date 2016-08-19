package com.platform.soft.base.springmvc;

import com.platform.soft.base.resource.ImagePathFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by baixiaobin on 16/8/16.
 */

@Controller
@RequestMapping("common/upload")
public class UploadController extends BaseController {
    /**
     * 个人实名认证图片上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam(value = "file", required = true) MultipartFile file, String pt)
            throws Exception {
        String path = request.getSession().getServletContext()
                .getRealPath("upload");
        String filePath = ImagePathFactory.getWholeImagePath(pt, file.getOriginalFilename());
        File targetFile = new File(path, filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存
        file.transferTo(targetFile);

        return "upload/" + filePath;
    }
}