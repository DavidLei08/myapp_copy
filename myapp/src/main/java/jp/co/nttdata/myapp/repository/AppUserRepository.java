package jp.co.nttdata.myapp.repository;
import jp.co.nttdata.myapp.model.login.RegisterUserInfo;
import jp.co.nttdata.myapp.model.user.UpdatePasswordInfo;
import jp.co.nttdata.myapp.model.user.UpdateUserInfo;
import jp.co.nttdata.myapp.model.login.LoginDetail;
import jp.co.nttdata.myapp.model.login.LoginUserInfo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

/**
 * @author chao11.lei
 * @version 1.0
 * app用户repository
 */
@Mapper
public interface AppUserRepository {


    @Insert("INSERT INTO app_user\n" +
            "        (username,password,telephone_num,dear_name,email,create_time)\n" +
            "        VALUES\n" +
            "        (#{user.username},#{user.password},#{user.telephoneNum},#{user.dearName},#{user.email},#{user.createTime})")
    public int  register(@Param("user") RegisterUserInfo user);


    @Select(" SELECT\n" +
            "        username  username,\n" +
            "        dear_name dearName\n" +
            "        FROM\n" +
            "        app_user\n" +
            "        WHERE\n" +
            "        username=#{loginUser.username}\n" +
            "        AND password=#{loginUser.password}")
    public LoginUserInfo login(@Param("loginUser")LoginDetail loginDetail);


    @Select(" SELECT\n" +
            "        username  username,\n" +
            "        dear_name dearName,\n" +
            "        telephone_num telephoneNum,\n"+
            "        email email\n"+
            "        FROM\n" +
            "        app_user\n" +
            "        WHERE\n" +
            "        username=#{username}")
    public UpdateUserInfo selUserInfoByUsername(@Param("username")String username);

    @Update("UPDATE app_user" +
            " SET last_login_time=#{dateTime}" +
            " WHERE username=#{username}")
   public int  updateLoginTime(@Param("username") String username,@Param("dateTime") LocalDateTime dateTime);

    @Update("UPDATE app_user" +
            " SET last_logout_time=#{dateTime}" +
            " WHERE username=#{username}")
  public int  updateLogoutTime(@Param("username") String username,@Param("dateTime") LocalDateTime dateTime);

    @Update("UPDATE app_user" +
            " SET telephone_num=#{user.telephoneNum}," +
            " dear_name=#{user.dearName}," +
            " email=#{user.email}" +
            " WHERE username=#{user.username}")
   public int  updateUserInfo(@Param("user") UpdateUserInfo userInfo);

    @Update("UPDATE  app_user" +
            " SET password=#{info.newPassword}"+
            " WHERE username=#{info.username} AND" +
            " password=#{info.oldPassword}")
    public int updatePassword(@Param("info")UpdatePasswordInfo passwordInfo);

}
