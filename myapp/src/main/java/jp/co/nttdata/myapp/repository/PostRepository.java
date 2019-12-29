package jp.co.nttdata.myapp.repository;

import jp.co.nttdata.myapp.model.post.PostInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author chao11.lei
 * @version 1.0
 * 帖子repository
 */
@Mapper
public interface PostRepository {

    @Select("SELECT post_id postId," +
            " post_title postTitle," +
            " post_type postType,"+
            " post_content postContent," +
            " create_user createUser," +
            " create_time createTime," +
            " remark_count remarkCount" +
            " FROM post_info ORDER BY remarkCount desc" )
    public List<PostInfo> findAll();

    @Select("SELECT post_id postId," +
            " post_title postTitle," +
            " post_type postType,"+
            " post_content postContent," +
            " create_user createUser," +
            " create_time createTime," +
            " remark_count remarkCount" +
            " FROM post_info" +
            " WHERE post_id =#{postId} ORDER BY remarkCount desc" )
    public PostInfo findOne(@Param("postId")Integer postId);


    @Select("SELECT post_id postId," +
            " post_title postTitle," +
            " post_type postType,"+
            " post_content postContent," +
            " create_user createUser," +
            " create_time createTime," +
            " remark_count remarkCount" +
            " FROM post_info" +
            " WHERE post_title like CONCAT(CONCAT('%',#{words}),'%')"+
            " OR create_user like CONCAT(CONCAT('%',#{words}),'%')" +
            " OR post_content like CONCAT(CONCAT('%',#{words}),'%') ORDER BY remarkCount desc")
    public List<PostInfo> searchByWords(@Param("words") String words);

    @Select("SELECT post_id postId," +
            " post_title postTitle," +
            " post_type postType,"+
            " post_content postContent," +
            " create_user createUser," +
            " create_time createTime," +
            " remark_count remarkCount" +
            " FROM post_info" +
            " WHERE create_user like CONCAT(CONCAT(#{username})) ORDER BY remarkCount desc" )
    public List<PostInfo> searchByUsername(@Param("username") String username);


    @Insert("INSERT INTO post_info" +
            " (post_title," +
            " post_type," +
            " post_content," +
            " create_user," +
            " create_time," +
            " remark_count)" +
            " VALUES" +
            " (#{postInfo.postTitle}," +
            " #{postInfo.postType}," +
            " #{postInfo.postContent}," +
            " #{postInfo.createUser}," +
            " #{postInfo.createTime}," +
            " #{postInfo.remarkCount})")
    public int createPost(@Param("postInfo") PostInfo postInfo);

    @Delete("DELETE FROM post_info" +
            " WHERE create_user=#{createUser}" +
            " AND post_id=#{postId}")
    public int deletePost(@Param("createUser") String createUser,@Param("postId") Integer postId);

    @Update("UPDATE post_info" +
            " SET remark_count=remark_count+1" +
            " WHERE post_id=#{postId}")
    public int updateRemarkCountAdd(@Param("postId") Integer postId);

    @Update("UPDATE post_info" +
            " SET remark_count=remark_count-1" +
            " WHERE post_id=(SELECT post_id FROM post_remark WHERE remark_id =#{remarkId})")
    public int updateRemarkCountIncrease(@Param("remarkId") Integer remarkId);
}
