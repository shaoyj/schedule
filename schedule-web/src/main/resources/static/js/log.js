/****
 * 使用方法是引入当前js到对应的页面
 * 在需要使用的地方
 * <a href="javascript:void(0)" class="showLog" params="{'objectId':XXXX,'objectType':'ORD_ORDER'}">日志</a>
 */
$(function () {
    $(document).on("click", "a.showLog", function () {
        var $this = $(this);
        try {
            var params = eval("(" + $(this).attr("params") + ")");
        } catch (e) {
            console.log(e);
        }
        var uri = "/logs/loadLog.html?objectType=" + params["objectType"] + "&objectId=" + params["objectId"];
        if (typeof(params["logType"]) != "undefined") {
            uri += "&logType=" + params["logType"] + "&logName=" + params["logName"];
        }
        open_window("show_log_dlg", "查看日志", uri, {"width": "80%"});
    });
})
