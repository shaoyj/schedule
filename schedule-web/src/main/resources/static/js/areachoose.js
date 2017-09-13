var provinces = {
    "11": "北京市",
    "12": "天津市",
    "13": "河北省",
    "14": "山西省",
    "15": "内蒙古自治区",
    "21": "辽宁省",
    "22": "吉林省",
    "23": "黑龙江省",
    "31": "上海市",
    "32": "江苏省",
    "33": "浙江省",
    "34": "安徽省",
    "35": "福建省",
    "36": "江西省",
    "37": "山东省",
    "41": "河南省",
    "42": "湖北省",
    "43": "湖南省",
    "44": "广东省",
    "45": "广西壮族自治区",
    "46": "海南省",
    "50": "重庆市",
    "51": "四川省",
    "52": "贵州省",
    "53": "云南省",
    "54": "西藏自治区",
    "61": "陕西省",
    "62": "甘肃省",
    "63": "青海省",
    "64": "宁夏回族自治区",
    "65": "新疆维吾尔自治区"
};

var citys = {
    "110100": "市辖区",
    "110200": "县",
    "120100": "市辖区",
    "120200": "县",
    "130100": "石家庄市",
    "130200": "唐山市",
    "130300": "秦皇岛市",
    "130400": "邯郸市",
    "130500": "邢台市",
    "130600": "保定市",
    "130700": "张家口市",
    "130800": "承德市",
    "130900": "沧州市",
    "131000": "廊坊市",
    "131100": "衡水市",
    "140100": "太原市",
    "140200": "大同市",
    "140300": "阳泉市",
    "140400": "长治市",
    "140500": "晋城市",
    "140600": "朔州市",
    "140700": "晋中市",
    "140800": "运城市",
    "140900": "忻州市",
    "141000": "临汾市",
    "141100": "吕梁市",
    "150100": "呼和浩特市",
    "150200": "包头市",
    "150300": "乌海市",
    "150400": "赤峰市",
    "150500": "通辽市",
    "150600": "鄂尔多斯市",
    "150700": "呼伦贝尔市",
    "150800": "巴彦淖尔市",
    "150900": "乌兰察布市",
    "152200": "兴安盟",
    "152500": "锡林郭勒盟",
    "152900": "阿拉善盟",
    "210100": "沈阳市",
    "210200": "大连市",
    "210300": "鞍山市",
    "210400": "抚顺市",
    "210500": "本溪市",
    "210600": "丹东市",
    "210700": "锦州市",
    "210800": "营口市",
    "210900": "阜新市",
    "211000": "辽阳市",
    "211100": "盘锦市",
    "211200": "铁岭市",
    "211300": "朝阳市",
    "211400": "葫芦岛市",
    "220100": "长春市",
    "220200": "吉林市",
    "220300": "四平市",
    "220400": "辽源市",
    "220500": "通化市",
    "220600": "白山市",
    "220700": "松原市",
    "220800": "白城市",
    "222400": "延边朝鲜族自治州",
    "230100": "哈尔滨市",
    "230200": "齐齐哈尔市",
    "230300": "鸡西市",
    "230400": "鹤岗市",
    "230500": "双鸭山市",
    "230600": "大庆市",
    "230700": "伊春市",
    "230800": "佳木斯市",
    "230900": "七台河市",
    "231000": "牡丹江市",
    "231100": "黑河市",
    "231200": "绥化市",
    "232700": "大兴安岭地区",
    "310100": "市辖区",
    "310200": "县",
    "320100": "南京市",
    "320200": "无锡市",
    "320300": "徐州市",
    "320400": "常州市",
    "320500": "苏州市",
    "320600": "南通市",
    "320700": "连云港市",
    "320800": "淮安市",
    "320900": "盐城市",
    "321000": "扬州市",
    "321100": "镇江市",
    "321200": "泰州市",
    "321300": "宿迁市",
    "330100": "杭州市",
    "330200": "宁波市",
    "330300": "温州市",
    "330400": "嘉兴市",
    "330500": "湖州市",
    "330600": "绍兴市",
    "330700": "金华市",
    "330800": "衢州市",
    "330900": "舟山市",
    "331000": "台州市",
    "331100": "丽水市",
    "340100": "合肥市",
    "340200": "芜湖市",
    "340300": "蚌埠市",
    "340400": "淮南市",
    "340500": "马鞍山市",
    "340600": "淮北市",
    "340700": "铜陵市",
    "340800": "安庆市",
    "341000": "黄山市",
    "341100": "滁州市",
    "341200": "阜阳市",
    "341300": "宿州市",
    "341500": "六安市",
    "341600": "亳州市",
    "341700": "池州市",
    "341800": "宣城市",
    "350100": "福州市",
    "350200": "厦门市",
    "350300": "莆田市",
    "350400": "三明市",
    "350500": "泉州市",
    "350600": "漳州市",
    "350700": "南平市",
    "350800": "龙岩市",
    "350900": "宁德市",
    "360100": "南昌市",
    "360200": "景德镇市",
    "360300": "萍乡市",
    "360400": "九江市",
    "360500": "新余市",
    "360600": "鹰潭市",
    "360700": "赣州市",
    "360800": "吉安市",
    "360900": "宜春市",
    "361000": "抚州市",
    "361100": "上饶市",
    "370100": "济南市",
    "370200": "青岛市",
    "370300": "淄博市",
    "370400": "枣庄市",
    "370500": "东营市",
    "370600": "烟台市",
    "370700": "潍坊市",
    "370800": "济宁市",
    "370900": "泰安市",
    "371000": "威海市",
    "371100": "日照市",
    "371200": "莱芜市",
    "371300": "临沂市",
    "371400": "德州市",
    "371500": "聊城市",
    "371600": "滨州市",
    "371700": "菏泽市",
    "410100": "郑州市",
    "410200": "开封市",
    "410300": "洛阳市",
    "410400": "平顶山市",
    "410500": "安阳市",
    "410600": "鹤壁市",
    "410700": "新乡市",
    "410800": "焦作市",
    "410900": "濮阳市",
    "411000": "许昌市",
    "411100": "漯河市",
    "411200": "三门峡市",
    "411300": "南阳市",
    "411400": "商丘市",
    "411500": "信阳市",
    "411600": "周口市",
    "411700": "驻马店市",
    "419000": "省直辖县级行政区划",
    "420100": "武汉市",
    "420200": "黄石市",
    "420300": "十堰市",
    "420500": "宜昌市",
    "420600": "襄阳市",
    "420700": "鄂州市",
    "420800": "荆门市",
    "420900": "孝感市",
    "421000": "荆州市",
    "421100": "黄冈市",
    "421200": "咸宁市",
    "421300": "随州市",
    "422800": "恩施土家族苗族自治州",
    "429000": "省直辖县级行政区划",
    "430100": "长沙市",
    "430200": "株洲市",
    "430300": "湘潭市",
    "430400": "衡阳市",
    "430500": "邵阳市",
    "430600": "岳阳市",
    "430700": "常德市",
    "430800": "张家界市",
    "430900": "益阳市",
    "431000": "郴州市",
    "431100": "永州市",
    "431200": "怀化市",
    "431300": "娄底市",
    "433100": "湘西土家族苗族自治州",
    "440100": "广州市",
    "440200": "韶关市",
    "440300": "深圳市",
    "440400": "珠海市",
    "440500": "汕头市",
    "440600": "佛山市",
    "440700": "江门市",
    "440800": "湛江市",
    "440900": "茂名市",
    "441200": "肇庆市",
    "441300": "惠州市",
    "441400": "梅州市",
    "441500": "汕尾市",
    "441600": "河源市",
    "441700": "阳江市",
    "441800": "清远市",
    "441900": "东莞市",
    "442000": "中山市",
    "445100": "潮州市",
    "445200": "揭阳市",
    "445300": "云浮市",
    "450100": "南宁市",
    "450200": "柳州市",
    "450300": "桂林市",
    "450400": "梧州市",
    "450500": "北海市",
    "450600": "防城港市",
    "450700": "钦州市",
    "450800": "贵港市",
    "450900": "玉林市",
    "451000": "百色市",
    "451100": "贺州市",
    "451200": "河池市",
    "451300": "来宾市",
    "451400": "崇左市",
    "460100": "海口市",
    "460200": "三亚市",
    "460300": "三沙市",
    "469000": "省直辖县级行政区划",
    "500100": "市辖区",
    "500200": "县",
    "510100": "成都市",
    "510300": "自贡市",
    "510400": "攀枝花市",
    "510500": "泸州市",
    "510600": "德阳市",
    "510700": "绵阳市",
    "510800": "广元市",
    "510900": "遂宁市",
    "511000": "内江市",
    "511100": "乐山市",
    "511300": "南充市",
    "511400": "眉山市",
    "511500": "宜宾市",
    "511600": "广安市",
    "511700": "达州市",
    "511800": "雅安市",
    "511900": "巴中市",
    "512000": "资阳市",
    "513200": "阿坝藏族羌族自治州",
    "513300": "甘孜藏族自治州",
    "513400": "凉山彝族自治州",
    "520100": "贵阳市",
    "520200": "六盘水市",
    "520300": "遵义市",
    "520400": "安顺市",
    "520500": "毕节市",
    "520600": "铜仁市",
    "522300": "黔西南布依族苗族自治州",
    "522600": "黔东南苗族侗族自治州",
    "522700": "黔南布依族苗族自治州",
    "530100": "昆明市",
    "530300": "曲靖市",
    "530400": "玉溪市",
    "530500": "保山市",
    "530600": "昭通市",
    "530700": "丽江市",
    "530800": "普洱市",
    "530900": "临沧市",
    "532300": "楚雄彝族自治州",
    "532500": "红河哈尼族彝族自治州",
    "532600": "文山壮族苗族自治州",
    "532800": "西双版纳傣族自治州",
    "532900": "大理白族自治州",
    "533100": "德宏傣族景颇族自治州",
    "533300": "怒江傈僳族自治州",
    "533400": "迪庆藏族自治州",
    "540100": "拉萨市",
    "542100": "昌都地区",
    "542200": "山南地区",
    "542300": "日喀则地区",
    "542400": "那曲地区",
    "542500": "阿里地区",
    "542600": "林芝地区",
    "610100": "西安市",
    "610200": "铜川市",
    "610300": "宝鸡市",
    "610400": "咸阳市",
    "610500": "渭南市",
    "610600": "延安市",
    "610700": "汉中市",
    "610800": "榆林市",
    "610900": "安康市",
    "611000": "商洛市",
    "620100": "兰州市",
    "620200": "嘉峪关市",
    "620300": "金昌市",
    "620400": "白银市",
    "620500": "天水市",
    "620600": "武威市",
    "620700": "张掖市",
    "620800": "平凉市",
    "620900": "酒泉市",
    "621000": "庆阳市",
    "621100": "定西市",
    "621200": "陇南市",
    "622900": "临夏回族自治州",
    "623000": "甘南藏族自治州",
    "630100": "西宁市",
    "630200": "海东市",
    "632200": "海北藏族自治州",
    "632300": "黄南藏族自治州",
    "632500": "海南藏族自治州",
    "632600": "果洛藏族自治州",
    "632700": "玉树藏族自治州",
    "632800": "海西蒙古族藏族自治州",
    "640100": "银川市",
    "640200": "石嘴山市",
    "640300": "吴忠市",
    "640400": "固原市",
    "640500": "中卫市",
    "650100": "乌鲁木齐市",
    "650200": "克拉玛依市",
    "652100": "吐鲁番地区",
    "652200": "哈密地区",
    "652300": "昌吉回族自治州",
    "652700": "博尔塔拉蒙古自治州",
    "652800": "巴音郭楞蒙古自治州",
    "652900": "阿克苏地区",
    "653000": "克孜勒苏柯尔克孜自治州",
    "653100": "喀什地区",
    "653200": "和田地区",
    "654000": "伊犁哈萨克自治州",
    "654200": "塔城地区",
    "654300": "阿勒泰地区",
    "659000": "自治区直辖县级行政区划"
};
var prov_city_key = {
    "11": ["110100", "110200"],
    "12": ["120100", "120200"],
    "13": ["130100", "130200", "130300", "130400", "130500", "130600", "130700", "130800", "130900", "131000", "131100"],
    "14": ["140100", "140200", "140300", "140400", "140500", "140600", "140700", "140800", "140900", "141000", "141100"],
    "15": ["150100", "150200", "150300", "150400", "150500", "150600", "150700", "150800", "150900", "152200", "152500", "152900"],
    "21": ["210100", "210200", "210300", "210400", "210500", "210600", "210700", "210800", "210900", "211000", "211100", "211200", "211300", "211400"],
    "22": ["220100", "220200", "220300", "220400", "220500", "220600", "220700", "220800", "222400"],
    "23": ["230100", "230200", "230300", "230400", "230500", "230600", "230700", "230800", "230900", "231000", "231100", "231200", "232700"],
    "31": ["310100", "310200"],
    "32": ["320100", "320200", "320300", "320400", "320500", "320600", "320700", "320800", "320900", "321000", "321100", "321200", "321300"],
    "33": ["330100", "330200", "330300", "330400", "330500", "330600", "330700", "330800", "330900", "331000", "331100"],
    "34": ["340100", "340200", "340300", "340400", "340500", "340600", "340700", "340800", "341000", "341100", "341200", "341300", "341500", "341600", "341700", "341800"],
    "35": ["350100", "350200", "350300", "350400", "350500", "350600", "350700", "350800", "350900"],
    "36": ["360100", "360200", "360300", "360400", "360500", "360600", "360700", "360800", "360900", "361000", "361100"],
    "37": ["370100", "370200", "370300", "370400", "370500", "370600", "370700", "370800", "370900", "371000", "371100", "371200", "371300", "371400", "371500", "371600", "371700"],
    "41": ["410100", "410200", "410300", "410400", "410500", "410600", "410700", "410800", "410900", "411000", "411100", "411200", "411300", "411400", "411500", "411600", "411700", "419000"],
    "42": ["420100", "420200", "420300", "420500", "420600", "420700", "420800", "420900", "421000", "421100", "421200", "421300", "422800", "429000"],
    "43": ["430100", "430200", "430300", "430400", "430500", "430600", "430700", "430800", "430900", "431000", "431100", "431200", "431300", "433100"],
    "44": ["440100", "440200", "440300", "440400", "440500", "440600", "440700", "440800", "440900", "441200", "441300", "441400", "441500", "441600", "441700", "441800", "441900", "442000", "445100", "445200", "445300"],
    "45": ["450100", "450200", "450300", "450400", "450500", "450600", "450700", "450800", "450900", "451000", "451100", "451200", "451300", "451400"],
    "46": ["460100", "460200", "460300", "469000"],
    "50": ["500100", "500200"],
    "51": ["510100", "510300", "510400", "510500", "510600", "510700", "510800", "510900", "511000", "511100", "511300", "511400", "511500", "511600", "511700", "511800", "511900", "512000", "513200", "513300", "513400"],
    "52": ["520100", "520200", "520300", "520400", "520500", "520600", "522300", "522600", "522700"],
    "53": ["530100", "530300", "530400", "530500", "530600", "530700", "530800", "530900", "532300", "532500", "532600", "532800", "532900", "533100", "533300", "533400"],
    "54": ["540100", "542100", "542200", "542300", "542400", "542500", "542600"],
    "61": ["610100", "610200", "610300", "610400", "610500", "610600", "610700", "610800", "610900", "611000"],
    "62": ["620100", "620200", "620300", "620400", "620500", "620600", "620700", "620800", "620900", "621000", "621100", "621200", "622900", "623000"],
    "63": ["630100", "630200", "632200", "632300", "632500", "632600", "632700", "632800"],
    "64": ["640100", "640200", "640300", "640400", "640500"],
    "65": ["650100", "650200", "652100", "652200", "652300", "652700", "652800", "652900", "653000", "653100", "653200", "654000", "654200", "654300", "659000"]
};
//var _int = [{"planNo":1,"firstNum":2,"firstPrice":3,"renewNum":1,"renewPrice":3,"areaCodes":"[110100,110200,650100,220200,220300]","renewPriceYuan":"0.03","firstPriceYuan":"0.03"},{"planNo":2,"firstNum":2,"firstPrice":3,"renewNum":1,"renewPrice":3,"areaCodes":"[141100,310100,310200,220100]","renewPriceYuan":"0.03","firstPriceYuan":"0.03"}];
var _int = [];
var userCheckedCityArr=new Array();
var userTemp={'add':[],'delete':[]};
//数组转换成对象,键名为provinces key   _array为一维数组
function arrayToObject(_array){
	var _o={};
	for(var i=0;i<_array.length;i++){
		var _a=_array[i].substr(0,2);
		if(_o[_a]){
			_o[_a].push(_array[i]);
		}else{
			_o[_a]=[_array[i]]
		}
	}
	return _o;
}
function areaChoose_Init(){
	var _a=new Object();
	for(var i in _int){
		//var a=_int[i].areaCodes.replace(['['],"").replace([']'],"").replace(/\s/g,'').split(",");
        var a=_int[i].areaCodeArray;
		userCheckedCityArr.push(a);
	}
    //console.log(userCheckedCityArr);
	showCityList(userCheckedCityArr,'int');
}
//code数组合成省（市） 传参一维数组
function codeToText(_arr,num){
	var _o = new Object();
	var showList = '';
	for(var i=0;i<_arr.length;i++){
		if(_o[_arr[i].substr(0,2)]){
			_o[_arr[i].substr(0,2)].push(_arr[i])
		}else{
			_o[_arr[i].substr(0,2)]=[_arr[i]];
		}
	}
	for(var item in _o){
		if(prov_city_key[item].length==_o[item].length){
			//showList+='<span class="checked-depth1" data-provinces="'+item+'">'+provinces[item]+'</span>，';
            showList += provinces[item]+'，';
		}else{
			//showList+='<span class="checked-depth1" data-provinces="'+item+'">'+provinces[item]+'(</span>';
            showList += provinces[item]+'(';
			for(var i=0;i<_o[item].length;i++){
				if(i==_o[item].length-1){
					//showList+='<span class="checked-depth2">'+citys[_o[item][i]]+'</span>';
                    showList += citys[_o[item][i]];
				}else{
					//showList+='<span class="checked-depth2">'+citys[_o[item][i]]+'</span>、';
                    showList += citys[_o[item][i]]+'、';
				}
			}
			showList += ')，';
		}
	}
	showList = showList.substr(0,showList.length-1);
    var html = "<span>"+showList+"</span>";
    html += "<input type='hidden' name='itemList["+num+"].areaNames' value='"+showList+"'>";
	return html;
}
function showCityList(_userCheckedCityArr,step,_index){ //传参二维数组  渲染页面
	var html='';
	if(step=='int'){
        for(var num=0;num<_userCheckedCityArr.length;num++){
			html +=  "<tr><td>"+codeToText(_userCheckedCityArr[num],num)+"<br/><a href='javascript:;' class='editBtn' onclick='editFun(this)'>编辑</a>";
	        html += "<a href='javascript:;' class='deleteBtn' onclick='deleteFun(this)'>删除</a>";
	        html += "<input type='hidden' name='itemList["+num+"].areaCodes' value='"+_userCheckedCityArr[num].join(",")+"'></td>";
	        html += "<td><input type='text' class='form-control' name='itemList["+num+"].firstNum' maxlength='6' onkeyup='value=value.replace(/[^0-9]/g,\"\")' value='"+_int[num].firstNum+"'></td>";
	        html += "<td><input type='text' class='form-control' name='itemList["+num+"].firstPriceYuan' maxlength='8' onkeyup='value=value.replace(/[^0-9.]/g,\"\")' value='"+_int[num].firstPriceYuan+"'></td>";
	        html += "<td><input type='text' class='form-control' name='itemList["+num+"].renewNum' maxlength='6' onkeyup='value=value.replace(/[^0-9]/g,\"\")' value='"+_int[num].renewNum+"'></td>";
	        html += "<td><input type='text' class='form-control' name='itemList["+num+"].renewPriceYuan' maxlength='8' onkeyup='value=value.replace(/[^0-9.]/g,\"\")' value='"+_int[num].renewPriceYuan+"'></td></tr>";
        }
        $('#areaShow').append(html);
	}
	if(step=='add'){
		if(_userCheckedCityArr.length>0){
            var num = $('#areaShow tr').length-1;
            //console.log("num"+num);

			html +=  "<tr><td>"+codeToText(_userCheckedCityArr,num)+"<br/><a href='javascript:;' class='editBtn' onclick='editFun(this)'>编辑</a>";
	        html += "<a href='javascript:;' class='deleteBtn' onclick='deleteFun(this)'>删除</a>";
	        html += "<input type='hidden' name='itemList["+num+"].areaCodes' value='"+_userCheckedCityArr.join(",")+"'></td>";
	        html += "<td><input type='text' class='form-control' name='itemList["+num+"].firstNum' maxlength='6' onkeyup='value=value.replace(/[^0-9]/g,\"\")' ></td>";
	        html += "<td><input type='text' class='form-control' name='itemList["+num+"].firstPriceYuan' maxlength='8' onkeyup='value=value.replace(/[^0-9.]/g,\"\")' ></td>";
	        html += "<td><input type='text' class='form-control' name='itemList["+num+"].renewNum' maxlength='6' onkeyup='value=value.replace(/[^0-9]/g,\"\")' ></td>";
	        html += "<td><input type='text' class='form-control' name='itemList["+num+"].renewPriceYuan' maxlength='8' onkeyup='value=value.replace(/[^0-9.]/g,\"\")' ></td></tr>";
            $('#areaShow').append(html);
	   }
	}
	if(step=='edit'){
        //console.log(_userCheckedCityArr);
		html += codeToText(_userCheckedCityArr,_index)+"<br/><a href='javascript:;' class='editBtn' onclick='editFun(this)'>编辑</a>";
        html += "<a href='javascript:;' class='deleteBtn' onclick='deleteFun(this)'>删除</a>";
        html += "<input type='hidden' name='itemList["+_index+"].areaCodes' value='"+_userCheckedCityArr.join(",")+"'>";
        //$('#areaShow').find('tr').eq(_index).find('td').eq(0).html(html);
        $("input[name='itemList["+_index+"].areaCodes']").parent().html(html);
	}
	if(step=='del'){
		$('#areaShow tr').eq(_index+1).remove();
	}
}

function getProvinces(userAction,_index){
	userTemp={'add':[],'delete':[]}
	$('.areaCheck').html('');
	for(var item in provinces){
		$('.areaCheck').html($('.areaCheck').html()+'<div class="fl depth1" data-provinces="'+item+'"><input type="checkbox" class="depth1-checkbox" onclick="parentChecked(this)" data-index="'+_index+'" data-useraction="'+userAction+'">'+provinces[item]+'<span class="irrow" onclick="irrowClick(this)" data-index="'+_index+'" data-useraction="'+userAction+'"/></span></div>');	
	}	
	var newArr=new Array();
	for(var i=0;i<userCheckedCityArr.length;i++){
		for(var j=0;j<userCheckedCityArr[i].length;j++){
			newArr.push(userCheckedCityArr[i][j]);
		}
	}	
	var _o=arrayToObject(newArr);
	for(var subItem in _o){
		var _t=$('.depth1[data-provinces='+subItem+']');
		if(userAction=="add"){
			_t.find('.irrow').before('<span class="cityCheckedLength">'+_o[subItem].length+'/'+prov_city_key[subItem].length+'</span>');
		}
		if(_o[subItem].length==prov_city_key[subItem].length){
			_t.find('input').attr('checked','checked');
			_t.find('input').attr('disabled','disabled');
		}
	}	
	 if(userAction=='edit'){
		var _o=arrayToObject(userCheckedCityArr[_index]);
		for(var subItem in _o){
			var _t=$('.depth1[data-provinces='+subItem+']');
			_t.find('.irrow').before('<span class="cityCheckedLength">'+_o[subItem].length+'/'+prov_city_key[subItem].length+'</span>');
			if(_o[subItem].length==prov_city_key[subItem].length){
				_t.find('input').attr('disabled',false);
			}
		}
	}
}
function getDepth2(_target,_parentListIndex,userAction){  //_clicklistIndex  被编辑列表的索引
	var p_key=$(_target).parents('.depth1').data("provinces");
	if($(_target).parents('.depth1').find('.depth2').length<1){
		var subCity='';
		for(var key in citys){
			var t=key.substr(0,2).indexOf(String(p_key));
			if(t>-1){
				subCity+='<label><input type="checkbox" data-cityCode="'+key+'" onclick="cityCheck(this)"/>'+citys[key]+'</label>';
			}  
		}
		$(_target).parent('.depth1').append(
			'<div class="depth2 hide">'+subCity+'<div class="depth2Close">关闭</div></div>'
		);
		for(var i=0;i<userCheckedCityArr.length;i++){
			var t=arrayToObject(userCheckedCityArr[i]);
			for(j in t){
				if(j==p_key){
					for (var m=0;m<t[j].length;m++){
						$(_target).siblings('.depth2').find('input[data-citycode='+t[j][m]+']').attr('checked','checked');
						$(_target).siblings('.depth2').find('input[data-citycode='+t[j][m]+']').attr('disabled','disabled');	
					}
				}
			}
		}
		if(userAction=="edit"){
			var t=arrayToObject(userCheckedCityArr[_parentListIndex]);
			for(j in t){
				if(j==p_key){
					for (var m=0;m<t[j].length;m++){
						$(_target).siblings('.depth2').find('input[data-citycode='+t[j][m]+']').attr('disabled',false);	
					}
				}
			}
		}
	}
}
function irrowClick(target){
	$('.depth2').addClass('hide');
	getDepth2(target,$(target).data('index'),$(target).data('useraction'));
	$(target).siblings('.depth2').removeClass('hide');
}
//点击省input[type="checkbox"]
function parentChecked(target){
	getDepth2(target,$(target).data('index'),$(target).data('useraction'));
	var _o=$(target).siblings('.depth2').find('input[type="checkbox"]');
	for(var i=0;i<_o.length;i++){
		if(_o.eq(i).attr('disabled')!="disabled"){
			if(target.checked){
				if(userTemp['delete'].indexOf(String(_o.eq(i).data('citycode')))>-1){
					userTemp['delete'].splice(userTemp['delete'].indexOf(String(_o.eq(i).data('citycode'))),1);
				}else{
					userTemp['add'].push(String(_o.eq(i).data('citycode')));
				}
				 _o.eq(i).prop('checked','checked');
			}else{
				if(userTemp['add'].indexOf(String(_o.eq(i).data('citycode')))>-1){
					userTemp['add'].splice(userTemp['add'].indexOf(String(_o.eq(i).data('citycode'))),1);
					if($(target).data('useraction')=='edit'){
						if(userCheckedCityArr[$(target).data('index')].indexOf(String(_o.eq(i).data('citycode')))>-1){  //之前已选中
							userTemp['delete'].push(String(_o.eq(i).data('citycode')));					
						}
					}
				}else{
					userTemp['delete'].push(String(_o.eq(i).data('citycode')));
				}
				_o.eq(i).prop('checked',false);
			}
		}
	}
	var _choosedLen=0;
	for(var m=0;m<_o.length;m++){
		if(_o.eq(m).attr('disabled')!="disabled"){
			if(_o.eq(m).prop('checked')){
				_choosedLen++;
			}
		}
	}
	if($(target).siblings('.cityCheckedLength').html()!=undefined){
		$(target).siblings('.cityCheckedLength').html('<span class="cityCheckedLength">'+_choosedLen+'/'+_o.length+'</span>');
	}else{
		$(target).siblings('.irrow').before('<span class="cityCheckedLength">'+_choosedLen+'/'+_o.length+'</span>');
	}
	//console.log(userTemp)
}
$(document).on('click','.depth2Close',function(e){
	$(this).parent('.depth2').addClass('hide');
	return false;
});
function cityCheck(target){
	var _p=$(target).parents('.depth1');
	if(!target.checked){
		_p.find('.depth1-checkbox').prop('checked',false);		
		if(userTemp['add'].indexOf(String($(target).data('citycode')))>-1){
			userTemp['add'].splice(userTemp['add'].indexOf(String($(target).data('citycode'))),1);
		}else{
			userTemp['delete'].push(String($(target).data('citycode')));
		}
	}else{
		var a=_p.find('label');
		var b=0;
		for(var i=0;i<a.length;i++){
			if(a.eq(i).find('input[type="checkbox"]').prop('checked')){
				if(a.eq(i).find('input[type="checkbox"]').attr('disabled')!="disabled"){
					b++;
				}
			}
		}
		var c=a.find('input[type="checkbox"]').length;
		if(c==b){
			_p.find('.depth1-checkbox').prop('checked',true);	 
		}
		if(userTemp['delete'].indexOf(String($(target).data('citycode')))>-1){
			userTemp['delete'].splice(userTemp['delete'].indexOf(String($(target).data('citycode'))),1);
		}else{
			userTemp['add'].push(String($(target).data('citycode')));
		}
		
	}
	
	var _choosedLen=0;
	for(var m=0;m<$(target).parents('.depth2').find('input').length;m++){
		if($(target).parents('.depth2').find('input').eq(m).prop('checked')){
			if($(target).parents('.depth2').find('input').eq(m).attr('disabled')!="disabled"){
				_choosedLen++;
			}else{
			}
		}
	}	
	if(_p.find('.cityCheckedLength').html()!=undefined){
		_p.find('.cityCheckedLength').html('<span class="cityCheckedLength">'+_choosedLen+'/'+_p.find('label').length+'</span>');
	}else{
		_p.find('.irrow').before('<span class="cityCheckedLength">'+_choosedLen+'/'+_p.find('label').length+'</span>');
	}
	//console.log(userTemp)
}
/*调用地址弹出层*/
function mainFun(userAction,_index){
	getProvinces(userAction,_index);
	var areaParam={
		title:"选择地区",
		content:$('#areachoosePop'),
		sureBtnFun:function(){
			if(userAction=='add'){
				userCheckedCityArr.push(userTemp['add']);
				showCityList(userTemp['add'],userAction);
			}else{
				for(var i=0;i<userTemp['add'].length;i++){
					if(userCheckedCityArr[_index].indexOf(userTemp['add'][i])<0){
						userCheckedCityArr[_index].push(userTemp['add'][i]);
					}
				}
				for(var i=0;i<userTemp['delete'].length;i++){
					var spliceIndex=userCheckedCityArr[_index].indexOf(userTemp['delete'][i]);
					if(_index>-1){
						userCheckedCityArr[_index].splice(spliceIndex,1);
					}
				}
				showCityList(userCheckedCityArr[_index],userAction,_index);
                //console.log(userCheckedCityArr[_index]);
			}		
			return true;
		},
		cancleBtnFun:function(){
			return true;                      
		}
	}
	popupModel(areaParam);
    $("#popupModel .modal-content").css("margin","2% auto 0");
} 
function editFun(target){
	var _index=$('.editBtn').index($(target));
	mainFun('edit',_index);
}
$('.addArea').click(function(){
	mainFun("add");
});
function deleteFun(target){
	var _index=$('.deleteBtn').index($(target));
	if(confirm("确定删除？")){
		userCheckedCityArr.splice(_index,1);
		showCityList('',"del",_index);
	}
}

//是否全选
function judgeSelectAll(){
	var _userCheckedCityLen=0;
	var _totalcityLen=0;
	for(var a in userCheckedCityArr){
		for(var i=0;i<userCheckedCityArr[a].length;i++){
			_userCheckedCityLen++;
		}
  	}	
	for(var a in citys){
        _totalcityLen++;
 	}
	if(_userCheckedCityLen==_totalcityLen){
		//return 'selectAll';
        return true;
	}else{
        return false;
    }
}
//areaChoose_Init();

