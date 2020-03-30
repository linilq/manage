package com.linilq.manage.core.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhijian
 * @description
 * @date 2020/3/30
 */
public class HttpRequestUtil {
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
