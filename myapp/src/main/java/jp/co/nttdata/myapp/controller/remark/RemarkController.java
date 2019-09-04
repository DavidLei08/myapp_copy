package jp.co.nttdata.myapp.controller.remark;

import jp.co.nttdata.myapp.authorization.annotation.TokenAuthorization;
import jp.co.nttdata.myapp.common.APIException;
import jp.co.nttdata.myapp.common.Constants;
import jp.co.nttdata.myapp.model.remark.RemarkInputForm;
import jp.co.nttdata.myapp.model.remark.RemarkInputInfo;
import jp.co.nttdata.myapp.service.remark.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author chao11.lei
 * @version 1.0
 * 评论相关操作用controller
 */
@CrossOrigin
@RestController
public class RemarkController {

    /**
     * 评论用service
     */
    @Autowired
    private RemarkService remarkService;

    /**
     * 评论创建
     * @param inputForm 输入的评论信息
     * @param result 校验结果
     * @param request 请求体
     * @return 创建结果
     */
    @TokenAuthorization
    @RequestMapping(value = "/remark/create",method = RequestMethod.POST)
    public String  createRemark(@RequestBody @Valid RemarkInputForm inputForm,
                                BindingResult result, HttpServletRequest request){
        //判断校验是否正确
        if(!result.hasErrors()){
            //初始化评论信息
            RemarkInputInfo inputInfo=new RemarkInputInfo();
            //设定评论的创建者
            inputInfo.setRemarkUser(request.getHeader(Constants.REQ_HEADER_USER_NAME_KEY));
            //设定评论的帖子id
            inputInfo.setPostId(inputForm.getPostId());
            //设定评论的评论内容
            inputInfo.setRemarkContent(inputForm.getRemarkContent());
            //执行创建评论
            remarkService.createRemark(inputInfo);
        }else {
            //抛出异常-请求入参格式不正确
            throw  new APIException(HttpServletResponse.SC_BAD_REQUEST,
                    "request parameters formatter is wrong ");
        }
        return  "ok";
    }

    /**
     * 删除评论
     * @param remarkId 评论id
     * @param request  请求体
     * @return 删除结果
     */
    @TokenAuthorization
    @RequestMapping(value = "/remark/delete/{remarkId}",method = RequestMethod.GET)
    public String deleteRemark(@PathVariable("remarkId") Integer remarkId,HttpServletRequest request){
        //执行评论删除
        remarkService.deleteRemark(request.getHeader(Constants.REQ_HEADER_USER_NAME_KEY),remarkId);
        return  "ok";
    }
}
