package jp.co.nttdata.myapp.model.post;

import jp.co.nttdata.myapp.model.remark.RemarkInfo;


import java.util.List;

/**
 * @author chao11.lei
 * @version 1.0
 * 帖子信息的包装类
 */
public class PostWrapperInfo extends  PostInfo{
    private List<RemarkInfo> remarkList;

    public List<RemarkInfo> getRemarkList() {
        return remarkList;
    }

    public void setRemarkList(List<RemarkInfo> remarkList) {
        this.remarkList = remarkList;
    }
}
