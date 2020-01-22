package top.longmarch.core.utils.upload;

import cn.hutool.json.JSONUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class QiniuUpload {

    public static UploadResult upload(InputStream inputStream, UploadConfig config) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration();
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;//file.getOriginalFilename();
        Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        String upToken = auth.uploadToken(config.getBucket(), null, config.getExpireSeconds(), putPolicy);
        UploadResult putRet = null;
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            // 解析上传成功的结果
            putRet = JSONUtil.toBean(response.bodyString(), UploadResult.class);
            putRet.setUrl(config.getDownloadUrl().concat("/").concat(putRet.getHash()));
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                throw new RuntimeException(r.bodyString());
            } catch (QiniuException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败：" + e.getMessage());
        }
        return putRet;
    }

    public static UploadResult upload(File file, UploadConfig config) {
        return upload(file, config);
    }

//    public static UploadResult upload(MultipartFile file, JSONObject p) {
//        // 构造一个带指定Zone对象的配置类
//        Configuration cfg = new Configuration(Zone.zone0());
//        // ...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
//
//        // 默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = null;//file.getOriginalFilename();
//        Auth auth = Auth.create(p.getString("accessKey"), p.getString("secretKey"));
//        StringMap putPolicy = new StringMap();
//        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
//        String upToken = auth.uploadToken(p.getString("bucket"), null, p.getLongValue("expireSeconds"), putPolicy);
//        UploadResult putRet = null;
//        try {
//            Response response = uploadManager.put(file.getBytes(), key, upToken);
//            // 解析上传成功的结果
//            putRet = new Gson().fromJson(response.bodyString(), UploadResult.class);
//            putRet.setUrl(p.getString("downloadUrl").concat("/").concat(putRet.getHash()));
//        } catch (QiniuException ex) {
//            Response r = ex.response;
//            try {
//                throw new RuntimeException(r.bodyString());
//            } catch (QiniuException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("上传失败：" + e.getMessage());
//        }
//        return putRet;
//    }

}
