package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.seckill.dto.SeckillExecution;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by hero on 16-7-25.
 */
public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;
}
