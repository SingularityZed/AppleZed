package com.zed.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.admin.system.entity.Menu;
import com.zed.admin.system.pojo.dto.MenuDTO;
import com.zed.admin.system.pojo.dto.MenuVerifyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * MenuMapper
 *
 * @author zed
 * @date 2020-01-16
 */
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    MenuVerifyDTO verifyRepeat(MenuDTO dto);
}