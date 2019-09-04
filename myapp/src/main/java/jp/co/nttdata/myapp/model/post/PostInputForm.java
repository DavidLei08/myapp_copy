package jp.co.nttdata.myapp.model.post;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author chao11.lei
 * @version 1.0
 * 帖子前端入力信息
 */
public class PostInputForm implements Serializable {

    private static final long serialVersionUID = -3253216958845154328L;

    @NotEmpty
    @Size(min = 1,max = 32)
    private String postTitle;

    private String postType;

    @NotEmpty
    @Size(min = 1,max =1000)
    private String postContent;

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
}
