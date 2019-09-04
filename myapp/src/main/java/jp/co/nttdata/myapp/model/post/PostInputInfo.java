package jp.co.nttdata.myapp.model.post;

/**
 * @author chao11.lei
 * @version 1.0
 * 帖子信息
 */
public class PostInputInfo {

    private String postTitle;

    private String postType;

    private String postContent;

    private String createUser;

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostType() {
        return postType;
    }

    public String getPostContent() {
        return postContent;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
