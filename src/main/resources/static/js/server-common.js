layui.define(['jquery', 'layer'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer;
    /**
     *
     * @param url 地址
     * @param type 请求方式 post、get
     * @param data  数据，json格式
     * @param onLoginResult
     * @param shadeLayer
     */
    api = function (url, type, data, onLoginResult) {
        $.ajax(
            {
                type: type,
                url: url,//发送请求
                contentType: "application/json; charset=utf-8",
                async: true,
                data: JSON.stringify(data),
                dataType: "json",
                success: function (result) {
                    onLoginResult(result);
                }
            }
        );
    };
    /**
     *
     * @param url post地址
     * @param type 请求方式 post、get
     * @param data  数据，json格式
     * @param msg  提示信息
     * @param onLoginResult
     * @param shadeLayer
     */
    apiWithNotice = function (url, type, data, msg, onLoginResult) {
        var msgIndex = layer.open({
            title: false,
            content: msg
        });
        $.ajax(
            {
                type: type,
                url: url,//发送请求
                contentType: "application/json; charset=utf-8",
                async: true,
                data: JSON.stringify(data),
                dataType: "json",
                success: function (result) {
                    layer.close(msgIndex);
                    onLoginResult(result);
                }
            }
        );
    };
    /**
     *
     * @param url post地址
     * @param type 请求方式 post、get
     * @param data  数据，json格式
     * @param msg  提示信息
     * @param onLoginResult
     * @param shadeLayer
     */
    apiWithShadeAndNotice = function (url, type, data, msg, onLoginResult) {
        var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
        var msgIndex = layer.open({
            title: false,
            content: msg
        });
        $.ajax(
            {
                type: type,
                url: url,//发送请求
                contentType: "application/json; charset=utf-8",
                async: true,
                data: JSON.stringify(data),
                dataType: "json",
                success: function (result) {
                    layer.close(msgIndex);
                    layer.close(loadIndex);
                    onLoginResult(result);
                }
            }
        );
    }

    getUrlParam = function(paraName) {
        var url = document.location.toString();
        var arrObj = url.split("?");
        if (arrObj.length > 1) {
            var arrPara = arrObj[1].split("&");
            var arr;
            for (var i = 0; i < arrPara.length; i++) {
                arr = arrPara[i].split("=");
                if (arr != null && arr[0] == paraName) {
                    return arr[1];
                }
            }
            return "";
        }
        else {
            return "";
        }
    }
});
