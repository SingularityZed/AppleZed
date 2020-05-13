package com.zed.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.admin.system.entity.Menu;
import com.zed.admin.system.pojo.ao.MenuQueryAO;
import com.zed.admin.system.pojo.dto.MenuDTO;
import com.zed.admin.system.pojo.dto.MenuVerifyDTO;
import com.zed.admin.system.pojo.vo.MenuVO;

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
     * 构建菜单树
     *
     * @param menuDTOList 原始数据
     * @return
     */
    Map<String, Object> buildTree(List<MenuDTO> menuDTOList);


    /**
     * 构建菜单
     *
     * @param menuDTOList
     * @return
     */
    List<MenuVO> buildMenus(List<MenuDTO> menuDTOList);


    /**
     * 获取菜单树
     *
     * @param menuList
     * @return
     */
    Object getMenuTree(List<Menu> menuList);

    /**
     * 获取菜单列表
     *
     * @param queryAO
     * @return
     */
    List<MenuDTO> getMenuList(MenuQueryAO queryAO);

    /**
     * 根据pid查询
     *
     * @param pid
     * @return
     */
    List<Menu> findByPid(Long pid);

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


}