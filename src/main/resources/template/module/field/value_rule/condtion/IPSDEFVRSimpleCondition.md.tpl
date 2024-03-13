`<%if(item.getCurPSDEField()){%>${item.getCurPSDEField().getName()}(${item.getCurPSDEField().getLogicName()})<%}else { out  << " "}%>` ${item.getCondOp()} <%
if(item.getCondOp()!='ISNULL' && item.getCondOp()!='ISNOTNULL'){
    out << ' `'
    if (item.getParamType()) {
        if('CURTIME' == item.getParamType())  {
            out << '当前时间'
        } else {
            out << item.getParamValue()
        }
    } else  {
        out << '直接值 ' << item.getParamValue()
    }
    out << '`'
}
%>