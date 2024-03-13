`<%if(item.getCurPSDEField()){%>${item.getCurPSDEField().getName()}(${item.getCurPSDEField().getLogicName()})<%}else { out  << " "}%>` 属性长度在区间 `<%
if(item.getMinValue()){
    if(item.isIncludeMinValue()){
        out << '['
    }else{
        out << '('
    }
    out << item.getMinValue()
}else{
    out << '(0'
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