/**
 * Created by shen on 2016/6/14.
 */
function pageDivDo(nowPage,pageSize,totalPages,totalSize){
    var html = "";
    var shortCutsNum = 10;
    var aTag='';
    var step= parseInt(shortCutsNum/2) ;
    var start=(nowPage-1)*pageSize+1;
    var minPage=(((start-1)/pageSize)+1)-step;
    var maxPage=0;
    var pageNum=pageSize;
    var curentPage=nowPage;
    if(minPage<=0){
        minPage=1;
        maxPage=shortCutsNum<totalPages?shortCutsNum:totalPages;
    }else{
        maxPage=((start-1)/pageNum)+1+step<totalPages?(((start-1)/pageNum)+1+step):totalPages;
        minPage=maxPage-shortCutsNum+1>0?maxPage-shortCutsNum+1:1;
    }
    var html = "<div class='col-md-3 col-sm-12'>";
    html += "<div style='padding:5px 0px;'>第"+curentPage+"页/共"+totalPages+"条</div></div>";
    html += "<div class='col-md-9 col-sm-12'><div class='text-right'>";
    html += "<ul class='pagination' style='visibility: visible;margin:0px;float:right;'>";
    //var html = "共"+totalPages+"条记录";
    for(var i=minPage;i<=maxPage ;i++){
        var aTag;
        if(i==curentPage){
            //aTag="<li><a href='javascript:void(0)'>"+i+"</a></li>";
            aTag = "<li class='active'><a href='javascript:void(0);' class='actived'>"+i+"</a></li>";
        }else{
            //aTag="<li><a href='javascript:void(0)' onclick='goPage("+i+")'>"+i+"</a></li>";
            aTag = "<li><a href='javascript:void(0);' page='"+i+"' onclick='goPage("+i+")'>"+i+"</a></li>";
        }
        html += aTag;
    }
    html += "</ul><div class='input-group' style='width: 120px;float:right; margin-right: 10px;'>";
    html += "<div class='input-group-addon' style='padding:6px 8px'>跳至</div>";
    html += "<input type='text' id='targetPage' class='form-control pageInput'>";
    html += "<a href='javascript:void(0)' class='input-group-addon btn btn-default pageBtn' page='' onclick='findPage("+totalPages+")' >»</a></div></div>";
    //html += "第<input type='text' id='targetPage' value='"+curentPage+"' maxlength='6' onkeyup='value=value.replace(/[^0-9]/g,\"\")' style='width: 40px'>/"+totalPages+"页<a href='javascript:void(0)' onclick='findPage("+totalPages+")'>前往</a>";
    $(".row").html(html);
}
function findPage(maxNum){
    var targetpage = $.trim($("#targetPage").val());
    if(targetpage != ""){
        //if(Number(targetpage) < 1){
        //    goPage(1);
        //}else
        if(Number(maxNum)<Number(targetpage)){
            goPage(maxNum);
        }else{
            goPage(targetpage);
        }
    }
}

function localTime(time){
    var d = new Date(time);
    var month = d.getMonth()+1;
    if(month < 10){
        month = "0"+month;
    }
    var date = d.getDate();
    if(date < 10){
        date = "0"+date;
    }
    return d.getFullYear()+"-"+month+"-"+date+(d+"").substr(15,9);
    //return new Date(time).toLocaleString();
}