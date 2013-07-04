/**
 * 功能:	返回数据主体
 *
 * @param d
 * @returns
 */
function filterData(d){
	if(d && d.succeed && d.data && d.data.list.length > 0){
		return d.data.list;
	}else{
		return null;
	}
	
}
function filterHtml(data){
	data = data.replace("<", "&lt;");
	data = data.replace(">", "&gt;");
	data = data.replace("&", "&amp;");
	data = data.replace('"', "&quot;");
	return data;
}

/**
 * 功能 JSON对象转成字符串
 * @param o
 * @returns
 */
function json2str(o){
	var r = [];
    if(typeof o =="string") return "\""+o.replace(/([\'\"\\])/g,"\\$1").replace(/(\n)/g,"\\n").replace(/(\r)/g,"\\r").replace(/(\t)/g,"\\t")+"\"";
    if(typeof o =="undefined") return "undefined";
    if(typeof o == "object"){
        if(o===null) return "null";
        else if(!o.sort){
            for(var i in o)
                r.push('"'+i+'"'+":"+json2str(o[i]));
            r="{"+r.join()+"}";
        }else{
            for(var i =0;i<o.length;i++)
                r.push(this.objToJson(o[i]));
            r="["+r.join()+"]";
        }
        return r;
    }
    return o.toString();
}
