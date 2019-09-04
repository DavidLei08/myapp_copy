package jp.co.nttdata.myapp.service.remark;


import jp.co.nttdata.myapp.model.remark.RemarkInputInfo;

/**
 * @author chao11.lei
 * @version 1.0
 * 评论用service
 */
public interface  RemarkService {
    /**
     * 创建评论
     * @param remarkInfo 评论信息
     */
    public void createRemark(RemarkInputInfo remarkInfo);

    /**
     * 删除评论
     * @param remarkUser 评论者
     * @param remarkId 评论id
     */
    public void deleteRemark(String remarkUser,Integer remarkId);

}
