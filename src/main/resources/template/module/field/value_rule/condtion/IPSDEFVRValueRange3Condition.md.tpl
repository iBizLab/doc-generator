`<%if(item.getCurPSDEField()){%>${item.getCurPSDEField().getName()}(${item.getCurPSDEField().getLogicName()})<%}else { out  << " "}%>` 值属于 `${item.getValueRanges()}`