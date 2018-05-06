package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by hero on 16-7-27.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = "application/json;")
    public SeckillResult<Exposer> exposer(Long seckillId) {
        SeckillResult<Exposer> seckillResult;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            seckillResult = new SeckillResult(true, exposer);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            seckillResult = new SeckillResult(false, ex.getMessage());
        }
        return seckillResult;
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST)
    public SeckillResult<SeckillExecution> execute(@PathVariable Long seckillId,
                                                   @PathVariable String md5,
                                                   @CookieValue(value = "killPhone", required = false) Long phone) {
        if (phone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        SeckillResult<SeckillExecution> result;
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
            result = new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = new SeckillResult<SeckillExecution>(false, ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
