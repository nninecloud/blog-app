package com.arimsky.blogapi.service;

import com.arimsky.blogapi.pojo.entity.AdminPermission;
import java.util.List;

/**
 * (AdminPermission)表服务接口
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public interface AdminPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AdminPermission queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AdminPermission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param adminPermission 实例对象
     * @return 实例对象
     */
    AdminPermission insert(AdminPermission adminPermission);

    /**
     * 修改数据
     *
     * @param adminPermission 实例对象
     * @return 实例对象
     */
    AdminPermission update(AdminPermission adminPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}