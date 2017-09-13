//左侧边栏树形
function leftTree(){
	$('.leftbar-group li:has(ul)').addClass('parent_li');
	$('.parent_li').find('>ul>li').hide();
	$('.leftbar-group li.parent_li > a').on('click', function (e) {
		var children = $(this).parent('li.parent_li').find(' > ul > li');
		var sibling=$(this).parent('li.parent_li').siblings('li');
		$('.nodeThree').removeClass('cur');
		if (children.is(":visible")) {
			children.hide();
			$(this).find(' > i').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
		} else {
			children.show();
			//sibling.find('li').hide();
			sibling.find('i').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
			$(this).find(' > i').addClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
		}
		e.stopPropagation();
	});
	//对应右侧iframe切换
	$('.nodeThree').on('click',function(){
		$('#rightMain').scrollTop(0);
		if($(this).data('random')){
			var _random=$(this).data('random');
			for(var i=0;i<=$('.rightTabBox>.pull-left').length;i++){
				if($('.rightTabBox>.pull-left').eq(i).data("random")==_random){
					$('.nodeThree').removeClass('cur');
					$(this).addClass('cur');
					$('.rightTabBox> .pull-left').eq(i).addClass('cur').siblings('.pull-left').removeClass('cur');
					$('#iframeContainer iframe').eq(i).show().siblings('iframe').hide();
					$('#iframeContainer iframe').eq(i).attr('src',$(this).attr('rel'));
					return;
				}
			}
		}else{
			var _random=new Date().getTime();
			$('.nodeThree').removeClass('cur');
			$(this).addClass('cur').attr('data-random',_random);
			$('.rightTabBox>.pull-left').removeClass('cur');
			$('.rightTabBox').append('<li data-random="'+_random+'" class="pull-left cur">'+$(this).text()+'<a href="javascript:;" class="closeWin"  title="点击关闭">X</a></li>');
			//下拉框
			var tabWidth=0;
			$('.rightTabBox').width('auto');
			$('.rightTabBox li').each(function(){
				tabWidth+=$(this)[0].getBoundingClientRect().width;
				// tabWidth+=$(this).outerWidth();
			});

			$('.rightTabBox').width(tabWidth);
			if(tabWidth>$('.rightTabBoxW').outerWidth()){
				$('.rightTabBoxW').height(46);
			}
			$(window).resize(function(){
				if(tabWidth>$('.rightTabBoxW').outerWidth()){
					$('.rightTabBoxW').height(46);
				}else{
					$('.rightTabBoxW').height('auto');
				}
			});
			$('#iframeContainer').find('iframe').hide();
			$('.rightMain .slogan').hide();
			$('#iframeContainer').append('<iframe scrolling="auto" frameborder="0" src="'+$(this).attr('rel')+'" data-random="'+_random+'" style="display:block"></iframe>');
		}
		/*关闭*/
		$('.rightTabBox .closeWin').each(function(index){
			$(this).click(function(){
				var _random=$(this).parent('.pull-left').data("random");
				/*显示对应项*/
				if($(this).parent('.pull-left').hasClass('cur')){
					var _total=$('.rightTabBox .pull-left').length;
					if(_total==1){
						$('.rightMain .slogan').show();
					}else{//显示相邻项目
						var _next=$(this).parent('.pull-left').next();
						var _prev=$(this).parent('.pull-left').prev();
						if(_next.length>0){
							_next.trigger('click');
						}else if(_prev.length>0){
							_prev.trigger('click');
						}else{
						}
					}
				}
				$('#iframeContainer iframe').each(function(){
					if($(this).data("random")==_random){
						$(this).remove();
					}
				})
				$(this).parent('.pull-left').remove();
				$('.nodeThree').each(function(){
					if($(this).data('random')==_random){
						$(this).attr("data-random",null);
						$(this).removeData("random");
					}
				});

				//下拉框
				var tabWidth=0;
				$('.rightTabBox li').each(function(){
					tabWidth+=$(this).outerWidth()+1;
				});
				$('.rightTabBox').width(tabWidth);
				if(tabWidth>$('.rightTabBoxW').outerWidth()){
					$('.rightTabBoxW').height(46);
				}else{
					$('.rightTabBoxW').height('auto');
				}
				return;
			})
		});
	});
}
/*左侧边栏拉伸*/
function leftbarDrag(){
	var oDiv=document.getElementById("leftbarDragCorner");
	var oDiv2=document.getElementById("leftbarDragWrapper");
	var right=document.getElementById("leftbarDrag_r");
	var rightMain=document.getElementById("rightMain");
	var mouseStart={};
	var divStart={};
	var rightStart={};
	document.getElementById("rightMain").style.marginLeft=oDiv2.offsetWidth+'px';
	//往右拽
	right.onmousedown=function(ev) {
		var oEvent=ev||event;
		mouseStart.x=oEvent.clientX;
		mouseStart.y=oEvent.clientY;
		rightStart.x=right.offsetLeft;
		if(right.setCapture){
			right.onmousemove=doDrag1;
			right.onmouseup=stopDrag1;
			right.setCapture();
		}else{
			document.addEventListener("mousemove",doDrag1,true);
			document.addEventListener("mouseup",stopDrag1,true);
		}
	};
	function doDrag1(ev){
		var oEvent=ev||event;
		var l=oEvent.clientX-mouseStart.x+rightStart.x;
		var w=l+oDiv.offsetWidth;
		if(w<oDiv.offsetWidth){
			w=oDiv.offsetWidth;
		}else if(w>document.documentElement.clientWidth-oDiv2.offsetLeft){
			w=document.documentElement.clientWidth-oDiv2.offsetLeft-2;
		}
		rightMain.style.width=document.documentElement.clientWidth-w+'px';
		document.getElementById("rightMain").style.marginLeft=w+'px';
		oDiv2.style.width=w+"px";
	};
	function stopDrag1(){
		if(right.releaseCapture){
			right.onmousemove=null;
			right.onmouseup=null;
			right.releaseCapture();
		}else{
			document.removeEventListener("mousemove",doDrag1,true);
			document.removeEventListener("mouseup",stopDrag1,true);
		}
	};
};
$(function(){
	leftTree();
	openMenupage1();
	if(document.getElementById("rightMain")){leftbarDrag();}
});
$(window).resize(function(){
	$('#rightMain').css('width',$(window).width()-parseInt($('#rightMain').css("margin-left")));
});
function noticeModel(con,times){
	var moticeModelStr='';
	moticeModelStr+='<div id="noticeModel">'
	moticeModelStr+='<div class="modal-content">'+con+'</div>'
	moticeModelStr+='</div>';
	$('body').append(moticeModelStr);
	setTimeout(function(){
		$('#noticeModel').remove();
	},times);
}
/*
 function alertModel(con,_target){
 var alertModelStr='';
 alertModelStr+='<div class="modal fade '+_target+'">'
 alertModelStr+='<div class="modal-dialog modal-sm">'
 alertModelStr+='<div class="modal-content">'
 alertModelStr+='<div class="modal-header" style="border:0 none">'
 alertModelStr+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>'
 alertModelStr+='</div>'
 alertModelStr+='<div class="modal-body">'+con+'</div>'
 alertModelStr+='<div class="modal-footer" style="border:0 none">'
 alertModelStr+='<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
 alertModelStr+='</div>'
 alertModelStr+='</div>'
 alertModelStr+='</div>'
 alertModelStr+='</div>'
 $('body').append(alertModelStr);
 }*/
function popModel(con){
	var popModel='';
	popModel+='<div id="popModel">'
	popModel+='<div class="modal-content">'
	popModel+='<button type="button" class="close popModelClose">×</button>',
		popModel+='<div class="popModel-text">'+con+'</div>',
		popModel+='<div class="btnBox"><button type="button" class="btn btn-primary btn-sm" id="popModelSureBtn">确定</botton><button type="button" class="btn btn-primary btn-sm" id="popModelCancle">取消</button></div>',
		popModel+='</div>'
	popModel+='</div>';
	$('body').append(popModel);
	$('.popModelClose,#popModelCancle').click(function(){
		$('#popModel').remove();
	})

}
function tipsModel(con){
	var tipsModel='';
	tipsModel+='<div id="tipsModel">'
	tipsModel+='<div class="modal-content">'
	tipsModel+='<button type="button" class="close tipsModelClose">×</button>',
		tipsModel+='<div class="tipsModel-text">'+con+'</div>',
		tipsModel+='<div class="btnBox"><button type="button" class="btn btn-primary btn-sm" id="popModelSureBtn">确定</button></div>',
		tipsModel+='</div>'
	tipsModel+='</div>';
	$('body').append(tipsModel);
	$('.tipsModelClose,#popModelSureBtn').click(function(){
		$('#tipsModel').remove();
	})

}
function popModelHide(){
	$('#popModel').remove();
}
function tipsModelHide(){
	$('#tipsModel').remove();
}

function popupModel(params){
	var popupModel='';
	popupModel+='<div id="popupModel">'
	popupModel+='<div class="modal-content">'
	popupModel+='<button type="button" class="close popupModelClose">×</button>'
	if(params.title.length>0){
		popupModel+='<p class="popupModel-title">'+params.title+'</p>'
	}
	popupModel+='<div class="popupModel-con">'+params.content.html()+'</div>'
	popupModel+='<div class="btnBox">'
	if(params.sureBtnFun){
		popupModel+='<button type="button" class="btn btn-primary btn-sm" id="popupModelSureBn">确定</button>'
	}
	if(params.cancleBtnFun){
		popupModel+='<button type="button" class="btn btn-primary btn-sm" id="popupModelCancleBtn">取消</button>'
	}
	popupModel+='</div>'
	popupModel+='</div>'
	popupModel+='</div>'
	$('body').append(popupModel);
	$('#popupModelSureBn').click(function(){
		var t=params.sureBtnFun();
		if(t){
			popupModelHide();
		}
	});
	$('#popupModelCancleBtn').click(function(){
		var t=params.cancleBtnFun();
		if(t){
			popupModelHide();
		}
	})
	$('.popupModelClose').click(function(){
		popupModelHide();
	})
}
function popupModelHide(){
	$('#popupModel').remove();
}
//空白框架

function frameModel(url){
	var popupModel='';
	popupModel+='<div id="frameModel">'
	popupModel+='<div class="modal-content">'
	popupModel+='<button type="button" class="close frameModelClose">×</button>'
	popupModel+='<object data="'+url+'" type="text/html"></object>'
	popupModel+='</div>'
	popupModel+='</div>'
	$('body').append(popupModel);
	$('.frameModelClose').click(function(){
		$('#frameModel').remove();
	})
}
function frameModelHide(windowReload){
	if(windowReload==true){
		window.parent.location.reload();
	}else{
		$(window.parent.document).find("#frameModel").remove();
	}
}

//子页面打开菜单栏目页面  rel和文本内容匹配
function openMenupage1(){
	if($('.openMenupage')){
		$('.openMenupage').each(function(){
			$(this).click(function(){
				var _o=this.rel;
				var _t=this.name;
				if(_o){
					var _parent=$(window.parent.document);
					var _nodeThree=_parent.find('.leftbar-group .nodeThree');
					_nodeThree.each(function(index){
						if(this.rel==_o && $(this).text()==_t){
							var _this=_nodeThree.eq(index);
							_parent.find('#rightMain').scrollTop(0);
							if(_this.data('random')){
								var _random=_this.data('random');
								for(var i=0;i<=_parent.find('.rightTabBox>.pull-left').length;i++){
									if(_parent.find('.rightTabBox>.pull-left').eq(i).data("random")==_random){
										_parent.find('.nodeThree').removeClass('cur');
										_this.addClass('cur');
										_parent.find('.rightTabBox> .pull-left').eq(i).addClass('cur').siblings('.pull-left').removeClass('cur');
										_parent.find('#iframeContainer iframe').eq(i).show().siblings('iframe').hide();
										_parent.find('#iframeContainer iframe').eq(i).attr('src',_this.attr('rel'));
										return;
									}
								}
							}else{
								var _random=new Date().getTime();
								_parent.find('.nodeThree').removeClass('cur');
								_this.addClass('cur').attr('data-random',_random);
								_parent.find('.rightTabBox>.pull-left').removeClass('cur');
								_parent.find('.rightTabBox').append('<li data-random="'+_random+'" class="pull-left cur">'+_this.text()+'<a href="javascript:;" class="closeWin"  title="点击关闭">X</a></li>');
								//下拉框
								var tabWidth=_parent.find('.rightTabBox').outerWidth();
								_parent.find('.rightTabBox li').each(function(i){
									tabWidth+=_parent.find('.rightTabBox li').eq(i).outerWidth();
								});
								_parent.find('.rightTabBox').width(tabWidth);
								if(tabWidth>$('.rightTabBoxW').outerWidth()){
									_parent.find('.rightTabBoxW').height(46);
								}
								$(window).resize(function(){
									if(tabWidth>$('.rightTabBoxW').outerWidth()){
										_parent.find('.rightTabBoxW').height(46);
									}else{
										_parent.find('.rightTabBoxW').height('auto');
									}
								});
								_parent.find('#iframeContainer').find('iframe').hide();
								_parent.find('.rightMain .slogan').hide();
								_parent.find('#iframeContainer').append('<iframe scrolling="auto" frameborder="0" src="'+_this.attr('rel')+'" data-random="'+_random+'" style="display:block"></iframe>');
							}
						}
					});
				}
			});
		});
		$('.rightTabBox').on('click','.closeWin',function(){
			var _target=$(this);
			$('.rightTabBox .closeWin').each(function(index){
				var _random=_target.parent('.pull-left').data("random");
				/*显示对应项*/
				if(_target.parent('.pull-left').hasClass('cur')){
					var _total=$('.rightTabBox .pull-left').length;
					if(_total==1){
						$('.rightMain .slogan').show();
					}else{//显示相邻项目
						var _next=_target.parent('.pull-left').next();
						var _prev=_target.parent('.pull-left').prev();
						if(_next.length>0){
							_next.trigger('click');
						}else if(_prev.length>0){
							_prev.trigger('click');
						}else{
						}
					}
				}
				$('#iframeContainer iframe').each(function(){
					if($(this).data("random")==_random){
						$(this).remove();
					}
				})
				_target.parent('.pull-left').remove();
				$('.nodeThree').each(function(){
					if($(this).data('random')==_random){
						$(this).attr("data-random",null);
						$(this).removeData("random");
					}
				});
				//下拉框
				var tabWidth=0;
				$('.rightTabBox li').each(function(){
					tabWidth+=$(this).outerWidth()+1;
				});
				$('.rightTabBox').width(tabWidth);
				if(tabWidth>$('.rightTabBoxW').outerWidth()){
					$('.rightTabBoxW').height(46);
				}else{
					$('.rightTabBoxW').height('auto');
				}
				return;
			})
		});
	}
}
function openMenupage(_o){
	var _parent=$(window.parent.document);
	if($(_o).data("random")){
		for(var i=0;i<=_parent.find('.rightTabBox>.pull-left').length;i++){
			if($(_o).data("random")==_parent.find('.rightTabBox>.pull-left').eq(i).data("random")){
				_parent.find('.rightTabBox> .pull-left').eq(i).addClass('cur').siblings('.pull-left').removeClass('cur');
				_parent.find('#iframeContainer iframe').eq(i).show().siblings('iframe').hide();
				return;
			}
		}
	}

	var name=_o.name?_o.name:$(_o).html();
	var _random=new Date().getTime();
	_parent.find('.rightTabBox>.pull-left').removeClass('cur');
	$(_o).data("random",_random);
	_parent.find('.rightTabBox').width('auto').append('<li data-random="'+_random+'" class="pull-left cur">'+name+'<a href="javascript:;" class="closeWin"  title="点击关闭">X</a></li>');
	var tabWidth=0;
	var size=_parent.find('.rightTabBox li').size();
	for(var i=0;i<size;i++){
		tabWidth=tabWidth+_parent.find('.rightTabBox li').eq(i).outerWidth()+1;
	}
	_parent.find('.rightTabBox').width(tabWidth);
	//下拉框
	if(tabWidth>$('.rightTabBoxW').outerWidth()){
		_parent.find('.rightTabBoxW').height(46);
	}
	$(window).resize(function(){
		if(tabWidth>$('.rightTabBoxW').outerWidth()){
			_parent.find('.rightTabBoxW').height(46);
		}else{
			_parent.find('.rightTabBoxW').height('auto');
		}
	});
	_parent.find('#iframeContainer').find('iframe').hide();
	_parent.find('#iframeContainer').append('<iframe scrolling="auto" frameborder="0" src="'+_o.rel+'" data-random="'+_random+'" style="display:block"></iframe>');

}
//右侧tabs
$('.rightTabBox').on('click','.pull-left',function(){
	var _target=$(this);
	$('.rightTabBox .pull-left').each(function(index){
		if(_target.html()==$(this).html()){
			_target.addClass('cur').siblings('li').removeClass('cur');
			$('#iframeContainer iframe').eq(index).show().siblings('iframe').hide();
			return;
		}
	});
});


