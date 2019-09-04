package jp.co.nttdata.myapp.controller.post;

import jp.co.nttdata.myapp.authorization.annotation.TokenAuthorization;
import jp.co.nttdata.myapp.common.APIException;
import jp.co.nttdata.myapp.common.Constants;
import jp.co.nttdata.myapp.model.post.PostInfo;
import jp.co.nttdata.myapp.model.post.PostInputForm;
import jp.co.nttdata.myapp.model.post.PostInputInfo;
import jp.co.nttdata.myapp.model.post.PostWrapperInfo;
import jp.co.nttdata.myapp.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author chao11.lei
 * @version 1.0
 * 帖子相关操作controller
 */
@CrossOrigin
@RestController
public class PostController {

    /**
     * 帖子操作用service
     */
    @Autowired
    private PostService postService;

    /**
     * 查询所有帖子信息
     * @return  帖子信息
     */
    @TokenAuthorization
    @RequestMapping(value = "/post/findall",method = RequestMethod.GET)
    public List<PostInfo> findAll(){
        //返回所有帖子的信息
        return  postService.findAll();
    }

    /**
     * 模糊检索帖子信息
     * @param words 检索词
     * @return 检索结果
     */
    @TokenAuthorization
    @RequestMapping(value = "/post/search/{words}",method =RequestMethod.GET)
    public List<PostInfo> searchByWords(@PathVariable("words") String words){
        //返回检索后的帖子信息
        return  postService.searchByWords(words);
    }

    /**
     * 检索个人帖子信息
     * @param username 检索词
     * @return 检索结果
     */
    @TokenAuthorization
    @RequestMapping(value = "/selfPost/search/{username}",method =RequestMethod.GET)
    public List<PostInfo> searchByUsername(@PathVariable("username") String username){
        //返回检索后的帖子信息
        return  postService.searchByUsername(username);
    }

    /**
     *获取单个帖子所有相关信息
     * @param postId 帖子Id
     * @return 帖子具体信息
     */

    @TokenAuthorization
    @RequestMapping(value = "/post/finddetail/{postId}",method = RequestMethod.GET)
    public PostWrapperInfo findDetail(@PathVariable("postId") Integer postId ){
        //返回帖子的具体信息
        return  postService.findDetail(postId);
    }

    /**
     * 创建帖子
     * @param postForm 提交的帖子信息
     * @param result 校验结果
     * @param request 请求体
     * @return  创建结果
     */
    @TokenAuthorization
    @RequestMapping(value = "/post/create",method = RequestMethod.POST)
    public String createPost(@RequestBody @Valid PostInputForm postForm,
                             BindingResult result, HttpServletRequest request){
        //判断校验是否正确
        if(!result.hasErrors()){
            //帖子信息初始化
            PostInputInfo postInfo=new PostInputInfo();
            //设定帖子的创建者
            postInfo.setCreateUser(request.getHeader(Constants.REQ_HEADER_USER_NAME_KEY));
            //设定帖子的标题
            postInfo.setPostTitle(postForm.getPostTitle());
            //设定帖子的内容
            postInfo.setPostContent(postForm.getPostContent());
            //设定帖子的类型
            postInfo.setPostType(postForm.getPostType());
            //执行创建帖子
            postService.createPost(postInfo);
        }else {
            //抛出异常-请求入参格式不正确
            throw new APIException(HttpServletResponse.SC_BAD_REQUEST,
                    "request parameters formatter is wrong ");
        }
        return "ok";
    }

    /**
     * 删除帖子
     * @param postId 帖子id
     * @param request 请求体
     * @return 删除结果
     */
    @TokenAuthorization
    @RequestMapping(value = "post/delete/{postId}",method = RequestMethod.GET)
    public String deletePost(@PathVariable("postId")Integer postId, HttpServletRequest request){
        //获取执行删除操作的用户名
        String  username=request.getHeader(Constants.REQ_HEADER_USER_NAME_KEY);
        //执行删除
        postService.deletePost(postId,username);
        return"ok";
    }

}
