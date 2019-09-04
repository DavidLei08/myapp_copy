package jp.co.nttdata.myapp.model.remark;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author chao11.lei
 * @version 1.0
 * 评论输入信息
 */
public class RemarkInputForm {
    @NotNull
    private Integer postId;

    @NotEmpty
    @Size(min = 1,max =200)
    private String remarkContent;

    public Integer getPostId() {
        return postId;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }
}
