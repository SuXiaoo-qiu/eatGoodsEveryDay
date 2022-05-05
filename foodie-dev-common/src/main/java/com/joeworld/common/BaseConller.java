package com.joeworld.common;

import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class BaseConller {
    public static final String FOODIE_SHOPCART = "shopcart";
    // 微信支付成功 -- 》支付中心 --》吃货平台
                    // 回调通知的url
    public static final  String  payReturnURL = "http://localhost:8088//orderStatusController/notifyMerchantOrderId";
    // 支付中心的调用地址
    public static final  String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";		// produce

    public static final  String IMAGE_USER_FACE_LOCATION ="F:"+File.separator+"file";
}
