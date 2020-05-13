package com.zed.admin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.admin.system.entity.Menu;
import com.zed.admin.system.pojo.dto.MenuDTO;
import com.zed.admin.system.pojo.dto.MenuVerifyDTO;
import com.zed.admin.system.pojo.vo.MenuVO;
import com.zed.common.base.PageParam;

import java.util.List;
import java.util.Map;

/**
 * MenuService
 *
 * @author zed
 * @date 2020-01-16
 */
public interface MenuService extends IService<Menu> {


    /**
     * 
     * @param menuVOList 
     * @return
     */
    Map<String,Object> buildMenus(List<MenuVO> menuVOList);
    
    
    
    /**
     * 分页查询
     *
     * @param pageParam 条件参数
     * @param dto       分页参数
     * @return
     */
    Page getPageList(PageParam pageParam, MenuDTO dto);

    /**
     * 获取详情
     *
     * @param menuId ID
     * @return MenuVO
     */
    MenuVO getMenuById(Long menuId);

    /**
     * 新增
     *
     * @param dto
     */
    void addMenu(MenuDTO dto);

    /**
     * 更新
     *
     * @param dto
     */
    void updateMenu(MenuDTO dto);

    /**
     * 删除
     *
     * @param menuId
     */
    void deleteMenu(Long menuId);

    /**
     * 校验
     *
     * @param dto
     * @return
     */
    MenuVerifyDTO verifyRepeat(MenuDTO dto);

    List<MenuVO> buildTree(List<MenuDTO> menuDTOList);

 
}