package com.joeworld.exception;

import com.joeworld.common.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class CustomExceptionHander {
    // 上传文件超过500k捕获异常
    @ExceptionHandler(MaxUploadSizeExceededException.class)
        public R HandLerMaxUpFile(MaxUploadSizeExceededException ex){
        System.out.println("文件超过五百k = " + ex);
            return R.error("文件大小超过500k,请压缩后重试试");
        }
}
