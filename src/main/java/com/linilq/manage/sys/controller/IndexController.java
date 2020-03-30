package com.linilq.manage.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linilq.manage.core.Constants;
import com.linilq.manage.core.utils.Digests;
import com.linilq.manage.core.utils.Encodes;
import com.linilq.manage.core.utils.VerifyCodeUtil;
import com.linilq.manage.sys.entity.Do.UserDo;
import com.linilq.manage.sys.entity.dto.BaseResultDto;
import com.linilq.manage.sys.entity.dto.LoginInfoDto;
import com.linilq.manage.sys.service.UserDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author lizhijian
 * @description
 * @date 2020/3/30
 */
@Controller
public class IndexController {
    @Autowired
    UserDoService userDoService;

    @GetMapping("index")
    public ModelAndView index(HttpServletRequest request, ModelAndView modelAndView) {
        return modelAndView;
    }

    @GetMapping("login")
    public ModelAndView login(HttpServletRequest request, ModelAndView modelAndView) {
        return modelAndView;
    }

    @PostMapping("doLogin")
    @ResponseBody
    public BaseResultDto<String> doLogin(HttpServletRequest request, @RequestBody LoginInfoDto loginInfoDto) {
        String username = loginInfoDto.getUsername();
        String password = loginInfoDto.getPassword();
        String code = loginInfoDto.getCode();

        BaseResultDto<String> resultDto = new BaseResultDto<>();
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
            resultDto.setMsg("用户名或者密码不能为空");
            return resultDto;
        }
        if (StrUtil.isEmpty(code)) {
            resultDto.setMsg("验证码不能为空");
            return resultDto;
        }

        HttpSession session = request.getSession();
        String verifyCode = (String) session.getAttribute(Constants.VALIDATE_CODE);
        if (!StrUtil.isEmpty(code) && !code.equalsIgnoreCase(verifyCode)) {
            resultDto.setMsg("验证码错误");
            return resultDto;
        }

        UserDo queryParam = new UserDo();
        queryParam.setLoginName(username);
        UserDo userDo = userDoService.getOne(new QueryWrapper<>(queryParam));

        String saltPassword = Encodes.encodeHex(Digests.sha1(password.getBytes(), userDo.getSalt().getBytes(), 1024));
        if (userDo == null || !saltPassword.equals(userDo.getPassword())) {
            resultDto.setMsg("用户名或密码错误");
            return resultDto;
        } else {
            session.setAttribute("username", username);
            resultDto.setData("/index");
        }

        resultDto.setCode(Constants.ResposeCodeSuc);
        resultDto.setMsg("登陆成功");
        return resultDto;
    }

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @GetMapping("/genCaptcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO 验证码可以无限刷新  有危险
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249, 205, 173), null, null);
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

}
