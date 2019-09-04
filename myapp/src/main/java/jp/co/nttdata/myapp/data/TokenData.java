package jp.co.nttdata.myapp.data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author chao11.lei
 * @version 1.0
 * token信息
 */
public class TokenData implements Serializable {

    private static final long serialVersionUID = -3226156858354300325L;

    private int tokenId;

    private String username;

    private String accessToken;

    private LocalDateTime accessTokenExpirationTime;

    public int getTokenId() {
        return tokenId;
    }

    public String getUsername() {
        return username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setTokenId( int tokenId) {
        this.tokenId = tokenId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LocalDateTime getAccessTokenExpirationTime() {
        return accessTokenExpirationTime;
    }

    public void setAccessTokenExpirationTime(LocalDateTime accessTokenExpirationTime) {
        this.accessTokenExpirationTime = accessTokenExpirationTime;
    }
}
