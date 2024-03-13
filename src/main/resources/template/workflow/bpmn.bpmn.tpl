<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:flowable="http://flowable.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema" expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.flowable.org/processdef">
    <process id="${sys.getCodeName().toLowerCase()}-${item.codeName.toLowerCase()}" isClosed="false" isExecutable="true" name="${item.getName()}" processType="None">
        <extensionElements>

            <flowable:eventListener delegateExpression="\${processInstanceListener}"  />

<% if (item.getWFRoles()) { %>\
            <flowable:field name="refgroups">
                <flowable:string><%item.getWFRoles().eachWithIndex{ wfrole,index ->%><%=(index>0)?",":""%>${wfrole.getCodeName()}|${wfrole.getName()}|${wfrole.getWFRoleType()}<% if(wfrole.getWFRoleType()=="DEDATASET"){%>/${wfrole.getPSDataEntity().getName().toLowerCase()}/${wfrole.getPSDEDataSet().getCodeName().toLowerCase()}/${wfrole.getWFUserIdPSDEF().getCodeName().toLowerCase()}<%}%><%}%></flowable:string>
            </flowable:field>
<% } %>\
<% if (item.getPSWorkflow() && item.getPSWorkflow().getPSWFDEs()) { %>\
            <flowable:field name="bookings">
                <flowable:string><%item.getPSWorkflow().getPSWFDEs().eachWithIndex{ wfde,index ->%><%=(index>0)?",":""%>${wfde.getPSDataEntity().getName().toLowerCase()}-${item.getPSWorkflow().getCodeName()}<%}%></flowable:string>
            </flowable:field>
<%item.getPSWorkflow().getPSWFDEs().eachWithIndex{ wfde,index ->%>\
<% if (wfde.getApps()) { %>\
            <flowable:field name="bookingapps_${wfde.getName().toLowerCase()}">
                <flowable:string><%wfde.getApps().eachWithIndex{ app,index2 ->%><%=(index2>0)?",":""%>${app.getCodeName()}<%}%></flowable:string>
            </flowable:field>
<% } %>\
<% if (wfde.getMobApps()) { %>\
            <flowable:field name="bookingmobs_${wfde.getName().toLowerCase()}">
                <flowable:string><%wfde.getMobApps().eachWithIndex{ app,index2 ->%><%=(index2>0)?",":""%>${app.getCodeName()}<%}%></flowable:string>
            </flowable:field>
<% } %>\
<% if (wfde.getWFStepPSDEField()) {%>\
            <flowable:field name="wfstepfield_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${(wfde.getWFStepPSDEField().getCodeName().toLowerCase())}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getWFInstPSDEField()) {%>\
            <flowable:field name="wfinstfield_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${(wfde.getWFInstPSDEField().getCodeName().toLowerCase())}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getUDStatePSDEField()) {%>\
            <flowable:field name="udstatefield_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${(wfde.getUDStatePSDEField().getCodeName().toLowerCase())}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getWFStatePSDEField()) {%>\
            <flowable:field name="wfstatefield_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${(wfde.getWFStatePSDEField().getCodeName().toLowerCase())}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getWFVerPSDEField()) {%>\
            <flowable:field name="wfverfield_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${(wfde.getWFVerPSDEField().getCodeName().toLowerCase())}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getPSDataEntity().getMajorPSDEField()) {%>\
            <flowable:field name="majortext_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${(wfde.getPSDataEntity().getMajorPSDEField().getCodeName().toLowerCase())}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getPSDataEntity().getOrgIdPSDEField()) {%>\
            <flowable:field name="orgfield_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${(wfde.getPSDataEntity().getOrgIdPSDEField().getCodeName().toLowerCase())}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getEntityWFState()) {%>\
            <flowable:field name="udstateingval_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${wfde.getEntityWFState()}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getEntityWFFinishState()) {%>\
            <flowable:field name="wffinishval_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${wfde.getEntityWFFinishState()}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getEntityWFCancelState()) {%>\
            <flowable:field name="wfcancelval_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${wfde.getEntityWFCancelState()}</flowable:string>
            </flowable:field>
<%}%>\
<% if (wfde.getEntityWFErrorState()) {%>\
            <flowable:field name="wferrorval_${wfde.getPSDataEntity().getName().toLowerCase()}">
                <flowable:string>${wfde.getEntityWFErrorState()}</flowable:string>
            </flowable:field>
<%}%>\
<%}%>\
<% } %>\
            <flowable:field name="isvalid">
                <flowable:string><%= item.isValid()?"1":"0"%></flowable:string>
            </flowable:field>
        </extensionElements>

<%= ctx.outputWithParent(item,'/workflow/bpmn.temp.tpl',null) %>

    </process>

    <bpmndi:BPMNDiagram id="BPMNDiagram_${sys.getCodeName().toLowerCase()}-${item.codeName.toLowerCase()}">
        <bpmndi:BPMNPlane id="BPMNPlane_${sys.getCodeName().toLowerCase()}-${item.codeName.toLowerCase()}" bpmnElement="${sys.getCodeName().toLowerCase()}-${item.codeName.toLowerCase()}">
<% if (item.getPSWFProcesses()) {
  item.getPSWFProcesses().each{ process -> %>\
            <bpmndi:BPMNShape id="BPMNShape-${process.getDeployId2()}" bpmnElement="<%= (process.getWFProcessType()=='INTERACTIVE') ? "tid-" + process.getWFStepValue():"sid"%>-${process.getDeployId2()}">
                <omgdi:Bounds x="${process.getLeftPos()}" y="${process.getTopPos()}" width="${process.getWidth()}" height="${process.getHeight()}" />
            </bpmndi:BPMNShape>
<% }} %>\
<% if (item.getPSWFLinks()) {
  item.getPSWFLinks().each{ link -> %>\
            <bpmndi:BPMNEdge id="BPMNEdge-<%= (link.getWFLinkType()!='ROUTE') ?  "lid":"rid"%>-${link.getDeployId2()}" bpmnElement="<%= (link.getWFLinkType()!='ROUTE') ?  "lid":"rid"%>-${link.getDeployId2()}">
                <omgdi:waypoint x="0" y="0" />
                <omgdi:waypoint x="0" y="0" />
            </bpmndi:BPMNEdge>
<% }} %>\
        </bpmndi:BPMNPlane>
    </bpmndi:BPMNDiagram>
</definitions>