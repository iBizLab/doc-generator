`<%if(item.getCurPSDEField()){%>${item.getCurPSDEField().getName()}(${item.getCurPSDEField().getLogicName()})<%}else { out  << " "}%>` 符合正则表达式 `${item.getRegExCode()}`