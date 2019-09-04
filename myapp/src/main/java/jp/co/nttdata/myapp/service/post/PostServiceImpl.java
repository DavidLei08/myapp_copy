package jp.co.nttdata.myapp.service.post;

import jp.co.nttdata.myapp.common.APIException;
import jp.co.nttdata.myapp.model.post.PostInfo;
import jp.co.nttdata.myapp.model.post.PostInputInfo;
import jp.co.nttdata.myapp.model.post.PostWrapperInfo;
import jp.co.nttdata.myapp.repository.PostRepository;
import jp.co.nttdata.myapp.repository.RemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements  PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RemarkRepository remarkRepository;

    @Override
    public List<PostInfo> findAll() {
        List<PostInfo> postInfos=new ArrayList<PostInfo>();
        postInfos=postRepository.findAll();
       if(postInfos==null) {
        throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "not get information list");
        }

        for(PostInfo postInfo:postInfos){
           if(postInfo.getPostContent().length()>100){
               postInfo.setPostContent(postInfo.getPostContent().substring(0,96)+"...");
           }
        }
        return postInfos;
    }

    @Override
    public List<PostInfo> searchByWords(String words) {
        List<PostInfo> postInfos=new ArrayList<PostInfo>();
       try{
          postInfos=postRepository.searchByWords(words);
       }catch (Exception e){
           throw  new APIException(HttpServletResponse.SC_UNAUTHORIZED, "search information is disabled");
       }
        return postInfos;
    }

    @Override
    public List<PostInfo> searchByUsername(String username) {
        List<PostInfo> postInfos=new ArrayList<PostInfo>();
        try{
            postInfos=postRepository.searchByUsername(username);
        }catch (Exception e){
            throw  new APIException(HttpServletResponse.SC_UNAUTHORIZED, "search information is disabled");
        }
        return postInfos;
    }


    @Override
    public PostWrapperInfo findDetail(Integer postId) {
        PostWrapperInfo postWrapperInfo= new PostWrapperInfo();
        PostInfo postInfo=postRepository.findOne(postId);
        postWrapperInfo.setPostId(postInfo.getPostId());
        postWrapperInfo.setPostTitle(postInfo.getPostTitle());
        postWrapperInfo.setPostType(postInfo.getPostType());
        postWrapperInfo.setPostContent(postInfo.getPostContent());
        postWrapperInfo.setCreateTime(postInfo.getCreateTime());
        postWrapperInfo.setCreateUser(postInfo.getCreateUser());
        postWrapperInfo.setRemarkCount(postInfo.getRemarkCount());
        if(postWrapperInfo==null){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "detail information not get");
        }
        postWrapperInfo.setRemarkList(remarkRepository.findByPostId(postId));

        return postWrapperInfo;
    }

    @Override
    public void createPost(PostInputInfo postInfo) {
        PostInfo info=new PostInfo();
        info.setPostTitle(postInfo.getPostTitle());
        info.setPostType(postInfo.getPostType());
        info.setPostContent(postInfo.getPostContent());
        info.setCreateUser(postInfo.getCreateUser());
        info.setRemarkCount(0);
        info.setCreateTime(LocalDateTime.now());
        try{
          int total=  postRepository.createPost(info);
          if(total==0){
              throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "post information is disabled");
          }
        }catch (Exception e){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "create post is failed");
        }
    }

    @Transactional
    @Override
    public void deletePost(Integer postId, String username) {
        try {
          int total=  postRepository.deletePost(username,postId);
          remarkRepository.deleteRemarkByPostId(postId);
          if(total==0){
              throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "post information is disabled");
          }
        }catch (Exception e){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "delete post is failed");
        }
    }
}
