package com.joeworld.config;

import com.joeworld.common.DateUtil;
import com.joeworld.service.OrderStatusService;
import com.joeworld.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderJob {
    @Autowired
    private OrderStatusService orderStatusService;

    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void  OrderTask(){
        orderStatusService.closeOrder();
        System.out.println("定时任务开始"+ DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
    }




}
