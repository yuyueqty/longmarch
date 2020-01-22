package top.longmarch.sys.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.upload.QiniuUpload;
import top.longmarch.core.utils.upload.UploadConfig;
import top.longmarch.sys.entity.Parameter;
import top.longmarch.sys.service.IParameterService;

import java.io.IOException;

@Api(value = "文件上传", tags = "文件上传接口")
@RestController
@RequestMapping("/file")
public class UploadController {

    @Autowired
    private IParameterService parameterService;

    @ApiOperation(value = "图片上传")
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.getSize() == 0) {
            return Result.fail("无效的参数");
        }
        Parameter parameter = parameterService.getParameterByName(Constant.QINIU_UPLOAD);
        UploadConfig uploadConfig = JSONUtil.toBean(parameter.getParamValue(), UploadConfig.class);
        try {
            return Result.ok().add(QiniuUpload.upload(file.getInputStream(), uploadConfig));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

}
