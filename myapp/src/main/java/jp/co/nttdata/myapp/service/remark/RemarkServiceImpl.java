package jp.co.nttdata.myapp.service.remark;

import jp.co.nttdata.myapp.common.APIException;
import jp.co.nttdata.myapp.model.remark.RemarkInfo;
import jp.co.nttdata.myapp.model.remark.RemarkInputInfo;
import jp.co.nttdata.myapp.repository.PostRepository;
import jp.co.nttdata.myapp.repository.RemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Service
public class RemarkServiceImpl implements  RemarkService {

    @Autowired
    private RemarkRepository remarkRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    @Override
    public void createRemark(RemarkInputInfo remarkInfo) {
        RemarkInfo info=new RemarkInfo();
        info.setPostId(remarkInfo.getPostId());
        info.setRemarkContent(remarkInfo.getRemarkContent());
        info.setRemarkUser(remarkInfo.getRemarkUser());
        info.setRemarkTime(LocalDateTime.now());
        try {
          int total=  remarkRepository.createRemark(info);
          if(total==0){
              throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "remark information is disabled,created is failed");
          }
          postRepository.updateRemarkCountAdd(info.getPostId());
        }catch (Exception e){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "remark information is wrong,created is failed");
        }
    }

    @Transactional
    @Override
    public void deleteRemark(String remarkUser, Integer remarkId) {
        try {
            postRepository.updateRemarkCountIncrease(remarkId);
            int total=   remarkRepository.deleteRemark(remarkUser,remarkId);
            if(total==0){
                throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "remark information is disabled,deleted is failed");
            }
        }catch (Exception e){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "remark information is wrong,deleted is failed");
        }
    }
}
