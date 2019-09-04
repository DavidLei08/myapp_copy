package jp.co.nttdata.myapp.model.post;



import java.time.LocalDateTime;

/**
 * @author chao11.lei
 * @version 1.0
 * 数据库对应实体类
 */
public class PostInfo {

    private Integer postId;

    private String postTitle;

    private String postType;

    private String postContent;

    private String createUser;

    private LocalDateTime createTime;

    private Integer remarkCount;

    public Integer getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostType() {
        return postType;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getCreateUser() {
        return createUser;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Integer getRemarkCount() {
        return remarkCount;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setRemarkCount(Integer remarkCount) {
        this.remarkCount = remarkCount;
    }
}
