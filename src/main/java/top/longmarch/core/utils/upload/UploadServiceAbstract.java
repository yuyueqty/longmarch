package top.longmarch.core.utils.upload;

public abstract class UploadServiceAbstract implements UploadService {

    enum UploadType{
        QINIU
    }

    private UploadType type;

    public void setType(UploadType type) {
        this.type = type;
    }

}
