package com.yi.seckill.dao;

import com.yi.seckill.model.SequenceInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * 步长计数，用户订单号生成
 * @author YI
 * @date 2018-12-20 10:25:42
 */
public interface SequenceInfoMapper extends Mapper<SequenceInfo> {
    /**
     * 根据表名查找数据
     * 为数据库中的行上一个排它锁，避免其他用户以该表进行插入,修改或删除等操作,造成表的不一致性
     * @param name 表名
     * @return
     */
    @Select("SELECT * FROM sequence_info AS e1 where e1.name = #{name} for update ;")
    SequenceInfo selectByPrimaryName(String name);

    /**
     * 更新订单的下一个id
     * @param name 表名
     * @param currentValue 下一个订单计数
     * @return
     */
    @Update("UPDATE sequence_info SET current_value = #{currentValue} WHERE ( ( name = #{name} ) )")
    int updateByPrimaryNameInCurrentValue(@Param("name")String name, @Param("currentValue")Integer currentValue);
}
