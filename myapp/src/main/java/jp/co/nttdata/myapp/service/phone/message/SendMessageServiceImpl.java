package jp.co.nttdata.myapp.service.phone.message;

import jp.co.nttdata.myapp.service.phone.message.sms.yun.SmsSingleSender;
import jp.co.nttdata.myapp.service.phone.message.sms.yun.SmsSingleSenderResult;
import jp.co.nttdata.myapp.service.phone.message.sms.yun.SmsVoiceVerifyCodeSender;
import jp.co.nttdata.myapp.service.phone.message.sms.yun.SmsVoiceVerifyCodeSenderResult;
import jp.co.nttdata.myapp.utils.client.HttpClient4;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImpl implements  SendMessageService {

    /**
     * 发送文字验证短信.
     * @param message
     * @param phoneNum
     * @return
     */
    @Override
    public int sendPhoneMessage(String message, String phoneNum) {
        try {
            //认证key
            String accesskey = "5e11c025efb9a32b40da933e";
            //加密key
            String secretkey ="23c99914e44c4e6a8a440cc7d9842711";
            //手机号码
            String phoneNumber = phoneNum;
            //初始化单发
            SmsSingleSender singleSender = new SmsSingleSender(accesskey, secretkey);
            SmsSingleSenderResult singleSenderResult;

            //普通单发,注意前面必须为【】符号包含，置于头或者尾部。
            singleSenderResult = singleSender.send(0, "86", phoneNumber,
                    "【kewail科技】尊敬的用户：您的验证码："+message+"，工作人员不会索取，请勿泄漏。", "", "");
            return  singleSenderResult.result;
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }

    /**
     * 发送语音验证.
     * @param message
     * @param phoneNum
     * @return
     */
    @Override
    public int sendphoneVoiceMessage(String message, String phoneNum) {
        try {
            String accesskey = "5e11c025efb9a32b40da933e";
            String secretkey ="23c99914e44c4e6a8a440cc7d9842711";
            //手机号码
            String phoneNumber = phoneNum;
            SmsVoiceVerifyCodeSender smsVoiceVerifyCodeSender = new SmsVoiceVerifyCodeSender(accesskey,secretkey);
            SmsVoiceVerifyCodeSenderResult smsVoiceVerifyCodeSenderResult =
                    smsVoiceVerifyCodeSender.send("86",phoneNumber, "尊敬的MBBS的用户：本次验证码是"+message,2,"");
            return smsVoiceVerifyCodeSenderResult.result;
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }
}
