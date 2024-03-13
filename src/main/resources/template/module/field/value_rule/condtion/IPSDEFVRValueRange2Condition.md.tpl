`<%if(item.getCurPSDEField()){%>${item.getCurPSDEField().getName()}(${item.getCurPSDEField().getLogicName()})<%}else { out  << " "}%>` 值在区间 `
<%
if(item.getMinValue()){
    if(item.isIncludeMinValue()){
        out << '['
    }else{
        out << '('
    }
    out << item.getMinValue()
}else{
    out << '(-∞'
}
out << ' , '
if(item.getMaxValue()){
    out << item.getMaxValue()
    if(item.isIncludeMaxValue()){
        out << ']'
    }else{
        out << ')'
    }
}else{
    out << '+∞)'
}
%>` 内