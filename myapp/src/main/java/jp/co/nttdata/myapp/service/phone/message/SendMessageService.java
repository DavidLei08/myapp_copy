package jp.co.nttdata.myapp.service.phone.message;

public interface SendMessageService {

    public int sendPhoneMessage(String message,String phoneNum);
    public int sendphoneVoiceMessage(String message,String phoneNum);
}
