package com.zed.service.impl;

import com.zed.domain.Menu;
import com.zed.service.MenuService;
import com.zed.service.dto.MenuDTO;
import com.zed.service.mapper.MenuMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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
        Page<Menu> menuPage = this.page(page, new LambdaQueryWrapper<Menu>().eq(Menu::getIsDeleted, false));
        return AutoMapperUtil.mappingPage(menuPage, MenuVO.class);
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(key = "#p0")
    public MenuVO getMenuById(Long menuId) {
        Menu menu = this.getOne(new LambdaQueryWrapper<Menu>().eq(Menu::getId, id).eq(Menu::getIsDeleted, false));
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
     * @param id
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(Long menuId) {
        this.getMenuById(id);
        this.update(new LambdaUpdateWrapper<Menu>().eq(Menu::getId, id).set(Menu::getIsDeleted, true));
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