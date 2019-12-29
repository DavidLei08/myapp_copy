package jp.co.nttdata.myapp.repository;

import jp.co.nttdata.myapp.data.TokenData;
import org.apache.ibatis.annotations.*;

/**
 * @author chao11.lei
 * @version 1.0
 * app认证token的repository
 */
@Mapper
public interface AuthTokenRepository {


    @Select("SELECT token_id tokenId," +
            "username username," +
            "access_token accessToken," +
            "access_token_expiration_time accessTokenExpirationTime"+
            " FROM token_data" +
            " WHERE" +
            " username=#{username}")
    public TokenData selectTokenInfoByUsername(@Param("username")String username);

    @Select("SELECT token_id tokenId," +
            "username username," +
            "access_token accessToken," +
            "access_token_expiration_time accessTokenExpirationTime"+
            " FROM token_data" +
            " WHERE" +
            " access_token=#{accessToken}" +
            " AND username=#{username}")
    public TokenData selectTokenInfoByAccessToekn(@Param("accessToken") String accessToken,@Param("username") String username);


    @Insert( "INSERT INTO token_data\n" +
            "        (username,access_token,access_token_expiration_time)\n" +
            "        VALUES\n" +
            "        (#{data.username},#{data.accessToken},#{data.accessTokenExpirationTime})")
    public int insertAuthToken(@Param("data") TokenData tokenData);


    @Delete("DELETE FROM token_data " +
            "  WHERE username=#{username}")
    public int deleteTokenByUsername(@Param("username") String username);

    @Delete("DELETE FROM token_data " +
            "  WHERE access_token_expiration_time < CURRENT_TIMESTAMP")
    public int deletTokenByExpirationTime();
}
