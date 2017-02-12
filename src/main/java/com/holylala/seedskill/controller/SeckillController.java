package com.holylala.seedskill.controller;

import com.holylala.seedskill.dto.Exposer;
import com.holylala.seedskill.dto.SeckillExecution;
import com.holylala.seedskill.dto.SeckillResult;
import com.holylala.seedskill.enums.SeckillStatEnum;
import com.holylala.seedskill.exception.RepeatKillException;
import com.holylala.seedskill.exception.SeckillCloseException;
import com.holylala.seedskill.exception.SeckillException;
import com.holylala.seedskill.model.Seckill;
import com.holylala.seedskill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author huangzhenqiong@sina.cn
 * @version V1.0
 * @Package com.holylala.seedskill.controller
 * @Description:
 * @date 2016/11/15 22:57
 * @Company:
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取秒杀商品列表
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(Model model) {
        //list.jsp + model = modelAndView
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list"; //WEB-INF/jsp/list.jsp
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId , Model model) {

        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    /**
     * 输出Json  输出秒杀地址
     * @param seckillId
     */
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    //DTO web层和Service层对象传递

    /**
     * 执行秒杀
     * @param seckillId
     * @param md5
     * @param userPhone
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone",required = false) String userPhone) {

        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>(false,"未注册,手机号为空");
        }
        SeckillResult<SeckillExecution> result;

        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId,userPhone,md5);
            return  new SeckillResult<SeckillExecution>(true,execution);
        } catch (RepeatKillException e1) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return  new SeckillResult<SeckillExecution>(false,execution);
        } catch (SeckillCloseException e2) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return  new SeckillResult<SeckillExecution>(false,execution);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return  new SeckillResult<SeckillExecution>(false,execution);
        }

    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }

}
