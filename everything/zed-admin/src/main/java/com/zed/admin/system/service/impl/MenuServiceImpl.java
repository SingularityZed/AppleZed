package com.zed.admin.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zed.admin.system.entity.Menu;
import com.zed.admin.system.mapper.MenuMapper;
import com.zed.admin.system.pojo.ao.MenuQueryAO;
import com.zed.admin.system.pojo.dto.MenuDTO;
import com.zed.admin.system.pojo.dto.MenuVerifyDTO;
import com.zed.admin.system.pojo.vo.MenuMetaVO;
import com.zed.admin.system.pojo.vo.MenuVO;
import com.zed.admin.system.service.MenuService;
import com.zed.common.utils.AutoMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * MenuServiceImpl
 *
 * @author zed
 * @date 2020-01-16
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "menu")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    /**
     * 构建菜单树
     *
     * @param menuDTOList 原始数据
     * @return
     */
    @Override
    public Map<String, Object> buildTree(List<MenuDTO> menuDTOList) {
        List<MenuDTO> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDTO dto : menuDTOList) {
            if (dto.getPid() == 0) {
                trees.add(dto);
            }
            for (MenuDTO it : menuDTOList) {
                if (it.getPid().equals(dto.getId())) {
                    if (dto.getChildren() == null) {
                        dto.setChildren(new ArrayList<>());
                    }
                    dto.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        Map<String, Object> map = new HashMap<>(2);
        if (trees.isEmpty()) {
            trees = menuDTOList.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        map.put("content", trees);
        map.put("totalElements", menuDTOList.size());
        return map;
    }


    /**
     * 构建菜单
     *
     * @param menuDtos
     * @return
     */
    @Override
    public List<MenuVO> buildMenus(List<MenuDTO> menuDtos) {
        List<MenuVO> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
                    if (menuDTO != null) {
                        List<MenuDTO> menuDtoList = menuDTO.getChildren();
                        MenuVO menuVo = new MenuVO();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName()) ? menuDTO.getComponentName() : menuDTO.getName());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menuDTO.getPid() == 0 ? "/" + menuDTO.getPath() : menuDTO.getPath());
                        menuVo.setIsHidden(menuDTO.getIsHidden());
                        // 如果不是外链
                        if (menuDTO.getIsFrame() == 0) {
                            if (menuDTO.getPid() == 0) {
                                menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                            } else if (!StrUtil.isEmpty(menuDTO.getComponent())) {
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVO(menuDTO.getName(), menuDTO.getIcon(), menuDTO.getIsCache()));
                        if (menuDtoList != null && !menuDtoList.isEmpty()) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menuDTO.getPid() == 0) {
                            MenuVO menuVo1 = new MenuVO();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if (menuDTO.getIsFrame() == 0) {
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVO> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }


    /**
     * 根据pid查询
     *
     * @param pid
     * @return
     */
    @Override
    public List<Menu> findByPid(Long pid) {
        return this.baseMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getPid, pid));
    }


    /**
     * 获取菜单树
     *
     * @param menus
     * @return
     */
    @Override
    public Object getMenuTree(List<Menu> menus) {
        List<Map<String, Object>> list = new LinkedList<>();
        menus.forEach(menu -> {
                    if (menu != null) {
                        List<Menu> menuList = this.baseMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getPid, menu.getPid()));
                        Map<String, Object> map = new HashMap<>(16);
                        map.put("id", menu.getId());
                        map.put("label", menu.getName());
                        if (menuList != null && !menuList.isEmpty()) {
                            map.put("children", getMenuTree(menuList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }



    /**
     * 获取菜单列表
     *
     * @param queryAO
     * @return
     */
    @Override
    public List<MenuDTO> getMenuList(MenuQueryAO queryAO) {
        List<MenuDTO> dtoList = new ArrayList<>();
        List<Menu> menuList = this.baseMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getName, queryAO.getName()));
        if (menuList != null && !menuList.isEmpty()) {
            AutoMapperUtil.mappingList(menuList, dtoList, MenuDTO.class);
        }
        return dtoList;
    }




    /**
     * 获取详情
     *
     * @param menuId
     * @return
     */
    @Override
    @Cacheable(key = "#p0")
    public MenuVO getMenuById(Long menuId) {
        Menu menu = this.getOne(new LambdaQueryWrapper<Menu>().eq(Menu::getId, menuId));
        return AutoMapperUtil.toPOJO(menu, MenuVO.class);
    }

    /**
     * 新增
     *
     * @param dto
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void addMenu(MenuDTO dto) {
        Menu menu = AutoMapperUtil.toPOJO(dto, Menu.class);
        this.saveOrUpdate(menu);
    }

    /**
     * 更新
     *
     * @param dto
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(MenuDTO dto) {
        this.getMenuById(dto.getId());
        Menu menu = AutoMapperUtil.toPOJO(dto, Menu.class);
        this.saveOrUpdate(menu);
    }

    /**
     * 删除
     *
     * @param menuId
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(Long menuId) {
        this.getMenuById(menuId);
        this.removeById(new LambdaUpdateWrapper<Menu>().eq(Menu::getId, menuId));
    }

    /**
     * 校验
     *
     * @param dto
     * @return
     */
    @Override
    public MenuVerifyDTO verifyRepeat(MenuDTO dto) {
        return baseMapper.verifyRepeat(dto);
    }



}