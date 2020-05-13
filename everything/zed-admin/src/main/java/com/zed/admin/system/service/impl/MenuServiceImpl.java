package com.zed.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zed.admin.system.entity.Menu;
import com.zed.admin.system.mapper.MenuMapper;
import com.zed.admin.system.pojo.dto.MenuDTO;
import com.zed.admin.system.pojo.dto.MenuVerifyDTO;
import com.zed.admin.system.pojo.vo.MenuVO;
import com.zed.admin.system.service.MenuService;
import com.zed.common.base.PageParam;
import com.zed.common.utils.AutoMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
     * 分页查询
     *
     * @param pageParam
     * @param dto
     * @return
     */
    @Override
    @Cacheable
    public Page getPageList(PageParam pageParam, MenuDTO dto) {
        Page<Menu> page = pageParam.buildPage();
        Page<Menu> menuPage = this.page(page, new LambdaQueryWrapper<>());
        return AutoMapperUtil.mappingPage(menuPage, MenuVO.class);
    }

    //查询全部，应该可以跟下边的合并

    //列表查询菜单


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
        this.update(new LambdaUpdateWrapper<Menu>().eq(Menu::getId, menuId));
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