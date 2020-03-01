package top.longmarch.sys.service;

import org.springframework.web.multipart.MultipartFile;
import top.longmarch.core.utils.upload.UploadResult;
import top.longmarch.sys.entity.Image;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 图片管理 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-03-01
 */
public interface IImageService extends IService<Image> {

    void saveImage(UploadResult uploadResult, MultipartFile file);

}
