<%if(item.getReturnType()){%>\
<%if(item.getReturnType() == 'NONEVALUE' || item.getReturnType() == 'NULLVALUE' || item.getReturnType() == 'BREAK' ){%>\
返回 `${ctx.text('LogicReturnType',item.getReturnType())}`
<%}%>\
<%if(item.getReturnType() == 'SRCVALUE'){%>\
返回值`'${item.getRawValue()}'`
<%}%>\
<%if(item.getReturnType() == 'LOGICPARAM'){%>\
返回 `${item.getReturnParam().getInfo()}`
<%}%>\
<%if(item.getReturnType() == 'LOGICPARAMFIELD'){%>\
返回 `${item.getReturnParam().getInfo()}.${item.getDstFieldName()}`
<%}%>\
<%}else{%>\
*- N/A*
<%}%>