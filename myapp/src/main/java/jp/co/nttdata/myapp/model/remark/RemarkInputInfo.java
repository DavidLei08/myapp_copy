package jp.co.nttdata.myapp.model.remark;

/**
 * @author chao11.lei
 * @version 1.0
 * 评论信息
 */
public class RemarkInputInfo {

    private Integer postId;

    private String remarkContent;

    private String remarkUser;

    public Integer getPostId() {
        return postId;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public String getRemarkUser() {
        return remarkUser;
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
}
