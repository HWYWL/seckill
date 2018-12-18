package com.yi.seckill.common;

import java.util.List;

/**
 * 公共接口
 * @param <T>   实体类
 * @author YI
 * @date 2018-12-16 11:48:42
 */
public interface CommonService<T> {
    /**
     * 查找所有数据
     * @param page 当前页
     * @param size 页大小
     * @return
     */
//    List<T> selectAll(int page, int size);

    /**
     * 根据id查找数据
     * @return
     */
    T selectById(Integer id);
}
