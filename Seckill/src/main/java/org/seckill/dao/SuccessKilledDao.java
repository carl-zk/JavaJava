package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by hero on 16-7-21.
 */
public interface SuccessKilledDao {
    /**
     *
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    SuccessKilled queryByIdWithSeckill(long seckilledId);
}
