package jp.co.nttdata.myapp.model.remark;


import java.time.LocalDateTime;


/**
 * @author chao11.lei
 * @version 1.0
 * 评论信息
 */
public class RemarkInfo {

    private Integer remarkId;

    private Integer postId;

    private String remarkContent;

    private String remarkUser;

    private LocalDateTime remarkTime;

    public Integer getRemarkId() {
        return remarkId;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public String getRemarkUser() {
        return remarkUser;
    }

    public LocalDateTime getRemarkTime() {
        return remarkTime;
    }

    public void setRemarkId(Integer remarkId) {
        this.remarkId = remarkId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public void setRemarkUser(String remarkUser) {
        this.remarkUser = remarkUser;
    }

    public void setRemarkTime(LocalDateTime remarkTime) {
        this.remarkTime = remarkTime;
    }
}
