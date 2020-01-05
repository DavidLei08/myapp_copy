package jp.co.nttdata.myapp.service.phone.message.sms;


import jp.co.nttdata.myapp.service.phone.message.sms.yun.SmsSingleSender;
import jp.co.nttdata.myapp.service.phone.message.sms.yun.SmsSingleSenderResult;

public class SmsSDKDemo {
    public static void main(String[] args) {
    	try {
    		//请根据实际 accesskey 和 secretkey 进行开发，以下只作为演示 sdk 使用
    		String accesskey = "5e11c025efb9a32b40da933e";
    		String secretkey ="23c99914e44c4e6a8a440cc7d9842711";
    		//手机号码
    		String phoneNumber = "136252412xx";
    		 //初始化单发
	    	SmsSingleSender singleSender = new SmsSingleSender(accesskey, secretkey);
	    	SmsSingleSenderResult singleSenderResult;
	
	    	 //普通单发,注意前面必须为【】符号包含，置于头或者尾部。
	    singleSenderResult = singleSender.send(0, "86", phoneNumber, "【MedicialBBS】尊敬的用户：您的验证码：123456，工作人员不会索取，请勿泄漏。", "", "");
	    	System.out.println(singleSenderResult);
	    	
	    	
	    	//语音验证码发送
    		//SmsVoiceVerifyCodeSender smsVoiceVerifyCodeSender = new SmsVoiceVerifyCodeSender(accesskey,secretkey);
    		//SmsVoiceVerifyCodeSenderResult smsVoiceVerifyCodeSenderResult = smsVoiceVerifyCodeSender.send("86",phoneNumber, "444144",2,"");
    		//System.out.println(smsVoiceVerifyCodeSenderResult);

    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
