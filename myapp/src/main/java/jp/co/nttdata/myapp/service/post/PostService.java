package jp.co.nttdata.myapp.service.post;

import jp.co.nttdata.myapp.model.post.PostInfo;
import jp.co.nttdata.myapp.model.post.PostInputInfo;
import jp.co.nttdata.myapp.model.post.PostWrapperInfo;

import java.util.List;

/**
 * @author chao11.lei
 * @version 1.0
 * 帖子操作用service
 */
public interface PostService {

    /**
     * 查询所有帖子
     * @return
     */
    public List<PostInfo> findAll();

    /**
     * 检索帖子
     * @param words 检索词
     * @return
     */
    public List<PostInfo> searchByWords(String words);

    /**
     * 检索个人帖子
     * @param username 检索词
     * @return
     */
    public List<PostInfo> searchByUsername(String username);

    /**
     * 查询帖子的所有具体信息
     * @param postId 帖子id
     * @return
     */
    public PostWrapperInfo findDetail(Integer postId);

    /**
     * 创建帖子
     * @param postInfo
     */
    public void  createPost(PostInputInfo postInfo);

    /**
     * 删除帖子
     * @param postId
     * @param username
     */
    public void  deletePost(Integer postId,String username);
}
