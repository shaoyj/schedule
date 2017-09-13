function open_dialog(rel, title, href, op){
	var options = $.extend({
			width:650,height:400,
			title: title || 'My Dialog',
			href: href,
			collapsible: true,
			minimizable: false,
			maximizable: false, 
			modal: true,
			onClose : function() {
				$("#"+rel).dialog('destroy');
	        },
			buttons: [{ text: '取消', handler: function() { $("#"+rel).dialog("close"); } }]
		  },op);
	$('<div id=\"'+rel+'\"></div>').dialog(options);
}

function open_window(rel, title, href, op){
	var options = $.extend({
			width:650,height:400,
			title: title || ' ',
			href: href,
			collapsible: true,
			minimizable: false,
			maximizable: false,
			modal: true,
			onClose : function() {
				$(this).dialog('destroy');
			}
		  },op);
	$('<div id=\"'+rel+'\"></div>').window(options);
}