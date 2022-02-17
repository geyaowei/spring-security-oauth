package com.geyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geyy.entity.TUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author geyaowei
 * @title UserMapper
 * @description: TODO
 * @since 2022/2/16 11:06
 */
public interface UserMapper extends BaseMapper<TUser> {

    @Select("SELECT DISTINCT tp.`code` FROM `t_user_role` tur \n" +
            "INNER JOIN `t_role_permission` trp ON tur.`role_id` = trp.`role_id`\n" +
            "INNER JOIN `t_permission` tp ON trp.`permission_id` = tp.`id`\n" +
            "WHERE tur.`user_id` = #{userId};")
    List<String> findAllPermissions(@Param("userId") Integer userId);
}
