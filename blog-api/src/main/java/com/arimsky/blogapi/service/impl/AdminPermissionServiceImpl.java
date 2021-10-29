package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.pojo.entity.AdminPermission;
import com.arimsky.blogapi.dao.AdminPermissionMapper;
import com.arimsky.blogapi.service.AdminPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AdminPermission)表服务实现类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Service("adminPermissionService")
public class AdminPermissionServiceImpl implements AdminPermissionService {
    @Resource
    private AdminPermissionMapper adminPermissionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AdminPermission queryById(Long id) {
        return this.adminPermissionMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AdminPermission> queryAllByLimit(int offset, int limit) {
        return this.adminPermissionMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param adminPermission 实例对象
     * @return 实例对象
     */
    @Override
    public AdminPermission insert(AdminPermission adminPermission) {
        this.adminPermissionMapper.insert(adminPermission);
        return adminPermission;
    }

    /**
     * 修改数据
     *
     * @param adminPermission 实例对象
     * @return 实例对象
     */
    @Override
    public AdminPermission update(AdminPermission adminPermission) {
        this.adminPermissionMapper.update(adminPermission);
        return this.queryById(adminPermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.adminPermissionMapper.deleteById(id) > 0;
    }
}