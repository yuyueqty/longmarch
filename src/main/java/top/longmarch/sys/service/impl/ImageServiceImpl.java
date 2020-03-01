package top.longmarch.sys.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;
import top.longmarch.core.utils.upload.UploadResult;
import top.longmarch.sys.entity.Image;
import top.longmarch.sys.dao.ImageDao;
import top.longmarch.sys.service.IImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图片管理 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-03-01
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageDao, Image> implements IImageService {

    @Async
    @Override
    public void saveImage(UploadResult uploadResult, MultipartFile file) {
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setFileUrl(uploadResult.getUrl());
        image.setFileSize(file.getSize());
        this.save(image);
    }

}
