var showPriceDialog=function(orderNo){
    open_window("price_dialog","",rc_contextPath+"/orders/show_price.html?orderNo="+orderNo);
}

var showNoteDialog=function(orderNo){
    open_window("note_dialog","",rc_contextPath+"/orders/show_note.html?orderNo="+orderNo);
}

var showSetDeliveryDialog=function(orderNo){
    open_window("set_delivery_dialog","",rc_contextPath+"/orders/show_set_delivery.html?orderNo="+orderNo);
}

var showChangeDeliveryDialog=function(orderNo){
    open_window("change_delivery_dialog","",rc_contextPath+"/orders/show_change_delivery.html?orderNo="+orderNo);
}
var showPaymentDialog=function(orderNo){
    open_window("show_payment_dialog","",rc_contextPath+"/orders/dialog_payment.html?orderNo="+orderNo);
}

var shoCloseOrderDialog=function(orderNo){
    open_window("show_close_order_dialog","",rc_contextPath+"/orders/show_close_dialog.html?orderNo="+orderNo);
}

var showStorageExpressCompanyDialog=function(orderNos){
    open_window("show_storage_express_company_dialog","",rc_contextPath+"/orders/show_storage_express_company?orderNos="+orderNos);
}
var search = function(){
    var url = rc_contextPath+"/orders/search.html";
    var data = $("#search_form").serialize();
    $.get(url, data, function (data) {
        $("#search_content").html(data);
    });
}