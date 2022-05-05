package com.joeworld.controller.center;

import com.joeworld.common.*;
import com.joeworld.pojo.Users;
import com.joeworld.pojo.bo.center.CenterUserBo;
import com.joeworld.resource.FileUpload;
import com.joeworld.service.UsersService;
import com.joeworld.service.center.CenterUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("userInfo")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "用户中心",tags = {"center-用户中心相关的接口"})
public class CenterUserController extends BaseConller {

    @Autowired
    private UsersService usersService;
    @Autowired
    private CenterUsersService centerUsersService;
    @Autowired
    private FileUpload fileUpload;


    /**
     * 用户头像上传
     * @param userId
     * @param file
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "用户头像上传")
    @PostMapping("instantOssFileAvatar")
    public R instantOssFileAvatar(@RequestParam String userId,
                            MultipartFile file,
                              HttpServletRequest request,
                              HttpServletResponse response){
        //自定义用户上传路径
//        String  fileSpace = IMAGE_USER_FACE_LOCATION;
        String fileSpace = fileUpload.getImageUserFaceLocation();


//        centerUsersService.instantOssFileAvatar(file); //oss
        // 在路径上添加一个yserID 用于区分呢不同的用户上传
        String uploadPathFreix="/"+userId;
        if (file == null) {
            return R.error("文件不能为空");
        }
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
        //获取原始文件名
        String fileName = file.getOriginalFilename();
        if (StringUtils.isNotBlank(fileName)) {
            // 吧文件进行拆分成数组  以。进行分割
            String[] fileNameArr = fileName.split("\\.");
            // 获取追后一位 （后缀名）
            String suffix = fileNameArr[fileNameArr.length - 1];
            if (!suffix.equalsIgnoreCase("jpg")&&!suffix.equalsIgnoreCase("png")&&!suffix.equalsIgnoreCase("jpeg")) {
                return R.error("图片格式不正确");
            }

            //重新进行拼接
            //这个用的是覆盖式 就是只有一个头像 一直进行覆盖
            String newFileName="file"+userId+"."+ suffix;
            //这个用的是增量式  额外拼接uuid 或者是当前时间
//            String newFileNames="file"+userId+ UUID.randomUUID().toString()+"."+ suffix;
            //拼接图片保存的位置
            String finalFacePath = fileSpace + uploadPathFreix + File.separator + newFileName;
            //用于提供个web服务的地址
            uploadPathFreix+=("/"+newFileName);

            File outFile = new File(finalFacePath);
            if (outFile.getParentFile()!=null){
                // 创建文件夹
                outFile.getParentFile().mkdirs();
            }
            //文件保存到目录
            fileOutputStream = new FileOutputStream(outFile);
            inputStream = file.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=fileOutputStream) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //路径加上时间戳
       String finalUrl=  fileUpload.getImgServiceUrl()+uploadPathFreix+"?t"+ DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);

        Users users = centerUsersService.updateFileAvatar(userId, finalUrl);
        users = setNullProperty(users);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(users),true);
        return R.ok(users);
    }





    @ApiOperation(value = "修改用户信息")
    @PostMapping("updateOrder")
    public R findUserByIdInfo(@RequestParam String userId,
                              @RequestBody @Valid  CenterUserBo centerUserBo,
                              BindingResult result,
                              HttpServletRequest request,
                              HttpServletResponse response){
        // 判断result中是否有错误 如果有直接return
        if (result.hasErrors()) {
            Map<String, Object> errors = this.getErrors(result);
            return R.errorMap(errors);
        }
        Users users = centerUsersService.updateOrder(userId, centerUserBo);
        users = setNullProperty(users);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(users),true);
        // TODO: 2022/5/2  后续改成令牌token 整合redis 分布式会话
        return R.ok();
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }


    private Map<String, Object> getErrors(BindingResult bindingResult){
        Map<String, Object> map = new HashMap();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            /**
             * fieldError.getField() 错误属性
             * fieldError.getDefaultMessage() 错误信息
             */
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return map;

    }


}
