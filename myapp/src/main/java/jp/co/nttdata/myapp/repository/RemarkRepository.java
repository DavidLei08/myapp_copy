package jp.co.nttdata.myapp.repository;

import jp.co.nttdata.myapp.model.remark.RemarkInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author chao11.lei
 * @version 1.0
 * 评论repository
 */
@Mapper
public interface RemarkRepository {

    @Select("SELECT remark_id remarkId," +
            " post_id postId," +
            " remark_content remarkContent," +
            " remark_user remarkUser," +
            " remark_time remarkTime" +
            " FROM post_remark"+
            " WHERE post_id=#{postId}")
    public List<RemarkInfo> findByPostId(@Param("postId") Integer postId);

    @Insert("INSERT INTO post_remark" +
            " (remark_id," +
            " post_id," +
            " remark_content," +
            " remark_user," +
            " remark_time)" +
            " VALUES" +
            " (COALESCE((SELECT MAX(remark_id)+1 FROM post_remark),0)," +
            " #{remark.postId}," +
            " #{remark.remarkContent}," +
            " #{remark.remarkUser}," +
            " #{remark.remarkTime})")
    public int createRemark(@Param("remark") RemarkInfo remark);

    @Delete("DELETE FROM post_remark" +
            " WHERE remark_user=#{remarkUser}" +
            " AND remark_id=#{remarkId}")
    public int deleteRemark(@Param("remarkUser") String remarkUser,@Param("remarkId") Integer remarkId);

    @Delete("DELETE FROM post_remark" +
            " WHERE post_id=#{postId}")
    public int deleteRemarkByPostId(Integer postId);
}
