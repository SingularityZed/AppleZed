package com.zed.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zed.admin.system.entity.User;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.dto.UserVerifyDTO;
import com.zed.admin.system.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author zed
 * @date 2019/12/12 19:12
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询
     *
     * @param page
     * @param dto
     * @return
     */
    List<UserVO> getPageList(@Param("page") Page page, @Param("dto") UserDTO dto);


    /**
     * 校验
     *
     * @param dto
     * @return
     */
    UserVerifyDTO verifyRepeat(@Param("dto") UserDTO dto);


}
