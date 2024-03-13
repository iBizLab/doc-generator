<%if(!(parent) || ((parent) && item.getPSWorkflow().getUserTag() == "EMB")){%>\
<% if (item.getPSWFProcesses()) {
  item.getPSWFProcesses().each{ process -> %>\
<% if (process.getWFProcessType()=='START'){ %>\
        <startEvent id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>sid-${process.getDeployId2()}" name="${process.getName()}">
<% if (process.getFormCodeName() || process.getMobFormCodeName()) {%>\
          <extensionElements>
            <flowable:form <%if (process.getFormCodeName()) {%>process-form="${process.getFormCodeName()}"<% } %><%if (process.getMobFormCodeName()) {%> process-mobform="${process.getMobFormCodeName()}" <% } %> wfversion="${item.getWFVersion()}"/>
          </extensionElements>
<% } %>\
        </startEvent>

<% } else if (process.getWFProcessType()=='END'){ %>\
        <endEvent id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>sid-${process.getDeployId2()}" name="${process.getName()}">
<% if (process.getExitStateValue() || process.getUserData() || process.getUserData2()) {%>\
            <extensionElements>
                <flowable:form<%if (process.getExitStateValue()){ %> exitstatevalue="${process.getExitStateValue()}"<% } %><%if (process.getUserData()){ %> userdata="${process.getUserData()}"<% } %><%if (process.getUserData2()){ %> userdata2="${process.getUserData2()}"<% } %> />
            </extensionElements>
<% } %>\
        </endEvent>

<% } else if (process.getWFProcessType()=='PARALLELGATEWAY'){ %>\
        <parallelGateway id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>sid-${process.getDeployId2()}"></parallelGateway>

<% } else if (process.getWFProcessType()=='INCLUSIVEGATEWAY'){ %>\
        <inclusiveGateway id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>sid-${process.getDeployId2()}"></inclusiveGateway>

<% } else if (process.getWFProcessType()=='EXCLUSIVEGATEWAY'){ %>\
        <exclusiveGateway id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>sid-${process.getDeployId2()}"></exclusiveGateway>

<% } else if (process.getWFProcessType()=='PROCESS'){ %>\
        <serviceTask id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>sid-${process.getDeployId2()}" name="${process.getName()}" flowable:expression="\${wfCoreService.execute(execution, activedata)}" >
<% if (process.getPSDataEntity()) {%>\
            <extensionElements>
                <flowable:field name="service-entity"><flowable:string>${process.getPSDataEntity().getName().toLowerCase()}</flowable:string></flowable:field>
                <flowable:field name="service-deaction"><flowable:string>${process.getPSDEAction().getCodeName().toLowerCase()}</flowable:string></flowable:field>
<% if (process.getPSWFProcessParams()) {
  process.getPSWFProcessParams().each{ param ->
    if(param.getDstField() && param.getDstField())  {
      if(param.getSrcValueType() && param.getSrcValueType()) {%>\
<% if (param.getSrcValueType()=='CURTIME'){ %>\
                <flowable:field name="params-${(param.getDstField().toLowerCase())}"><flowable:expression><![CDATA[\${wfCoreService.getnow()}]]></flowable:expression></flowable:field>
<% } else if (param.getSrcValueType()=='OPERATOR'){ %>\
                <flowable:field name="params-${(param.getDstField().toLowerCase())}"><flowable:expression><![CDATA[\${curuser.userid}]]></flowable:expression></flowable:field>
<% } else if (param.getSrcValueType()=='OPERATORNAME'){ %>\
                <flowable:field name="params-${(param.getDstField().toLowerCase())}"><flowable:expression><![CDATA[\${curuser.personname}]]></flowable:expression></flowable:field>
<% } else if (param.getSrcValueType()=='CONTEXT'){ %>\
                <flowable:field name="params-${(param.getDstField().toLowerCase())}"><flowable:expression><![CDATA[\${activedata.${param.getSrcValue().toLowerCase()}}]]></flowable:expression></flowable:field>
<% } else if (param.getSrcValueType()=='SESSION'){ %>\
                <flowable:field name="params-${(param.getDstField().toLowerCase())}"><flowable:expression><![CDATA[\${curuser.sessionParams.${param.getSrcValue().toLowerCase()}}]]></flowable:expression></flowable:field>
<% } %>\
<% } else {  %>\
                <flowable:field name="params-${(param.getDstField().toLowerCase())}"><flowable:string>${param.getSrcValue()}</flowable:string></flowable:field>
<% }}}} %>\
            </extensionElements>
<% } %>\
<% if (!process.getPSDataEntity()) {%>\
            <extensionElements>
<% if (process.getPSWFProcessParams()) {
  process.getPSWFProcessParams().each{ param ->
    if(param.getSrcValue() && param.getDstField() && param.getDstField())  {%>\
                <flowable:field name="params-${(param.getDstField().toLowerCase())}"><flowable:string>${param.getSrcValue()}</flowable:string></flowable:field>
<% }}} %>\
            </extensionElements>
<% } %>\
        </serviceTask>

<% } else { %>\
<% if (process.getWFProcessType()=='EMBED'){ %>\
        <subProcess id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>sid-${process.getDeployId2()}"<%if(!process.hasTimeOutLink() && process.getCloudDueDate()){%> flowable:dueDate="${process.getCloudDueDate()}"<%}%> flowable:category="\${businessKey}" flowable:candidateUsers="<%if(process.isMultiInstance()){%>\${candidateUsers}<% } else { %>${process.getCloudPorcessUser()}<%=(process.getCloudPorcessUser())? ",\${activedata.srfwfpredefinedusers}":"\${activedata.srfwfpredefinedusers}"%><%}%>" flowable:exclusive="true" name="${process.getName()}"  flowable:formKey="${process.getWFProcessType()}">
<% if (process.getPSWFProcessSubWFs()) {
  process.getPSWFProcessSubWFs().each{ subwf -> %>\
<%= ctx.outputWithParent(subwf.getPSWFVersion(),'/workflow/bpmn.temp.tpl',subwf) %>
<% }} %>
<% } else if (process.getWFProcessType()=='CALLORGACTIVITY'){ %>\
        <callActivity id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>sid-${process.getDeployId2()}" calledElement="\${wfCoreService.getDefinitionKey('${process.getTargetPSWF().getCodeName()}', execution)}" flowable:calledElementType="key" flowable:inheritVariables="true" name="${process.getName()}">
<% } else { %>\
        <userTask id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>tid-${process.getWFStepValue()}-${process.getDeployId2()}"<%if(!process.hasTimeOutLink() && process.getCloudDueDate()){%> flowable:dueDate="${process.getCloudDueDate()}"<%}%> flowable:category="\${businessKey}" flowable:candidateUsers="<%if(process.isMultiInstance()){%>\${candidateUsers}<% } else { %>${process.getCloudPorcessUser()}<%=(process.getCloudPorcessUser())? ",\${activedata.srfwfpredefinedusers}":"\${activedata.srfwfpredefinedusers}"%><%}%>" flowable:exclusive="true" name="${process.getName()}">
<% } %>\
            <documentation>\${majortext}</documentation>
<% if ((process instanceof net.ibizsys.model.wf.IPSWFInteractiveProcess)  && (
    (process.getPredefinedActions() && process.getPredefinedActions().size > 0)
        || (process.getEditFields() && process.getEditFields().size > 0)
        || process.getFormCodeName() || process.getMobFormCodeName()
        || process.getUtilFormCodeName() || process.getUtil2FormCodeName() || process.getUtil3FormCodeName()
        || process.getUtilFormName() || process.getUtilFormName() || process.getUtilFormName()
        || process.getMobUtilFormCodeName() || process.getMobUtil2FormCodeName() || process.getMobUtil3FormCodeName()
        || process.getMobUtilFormName() || process.getMobUtil2FormName() || process.getMobUtil3FormName()
        || process.isEditable() || process.getEditMode() || process.getMemoField() || process.isMultiInstance()
)){ %>\
            <extensionElements>
                <flowable:form
<%if(process.getPredefinedActions() && process.getPredefinedActions().size > 0){%>\
                    procfunc="<%process.getPredefinedActions().eachWithIndex{ action,index -> %><%=(index>0?";":"")%>${action.toLowerCase()}<%}%>"
<% } %>\
<%if(process.getFormCodeName()){%>\
                    process-form="${process.getFormCodeName()}"
<% } %>\
<%if(process.getMobFormCodeName()){%>\
                    process-mobform="${process.getMobFormCodeName()}"
<% } %>\
<%if(process.getUtilFormCodeName()){%>\
                    process-utilform="${process.getUtilFormCodeName()}"
<% } %>\
<%if(process.getUtil2FormCodeName()){%>\
                    process-util2form="${process.getUtil2FormCodeName()}"
<% } %>\
<%if(process.getUtil3FormCodeName()){%>\
                    process-util3form="${process.getUtil3FormCodeName()}"
<% } %>\
<%if(process.getUtilFormName()){%>\
                    process-utilformname="${process.getUtilFormName()}"
<% } %>\
<%if(process.getUtil2FormName()){%>\
                    process-util2formname="${process.getUtil2FormName()}"
<% } %>\
<%if(process.getUtil3FormName()){%>\
                    process-util3formname="${process.getUtil3FormName()}"
<% } %>\
<%if(process.getMobUtilFormCodeName()){%>\
                    process-mobutilform="${process.getMobUtilFormCodeName()}"
<% } %>\
<%if(process.getMobUtil2FormCodeName()){%>\
                    process-mobutil2form="${process.getMobUtil2FormCodeName()}"
<% } %>\
<%if(process.getMobUtil3FormCodeName()){%>\
                    process-mobutil3form="${process.getMobUtil3FormCodeName()}"
<% } %>\
<%if(process.getMobUtilFormName()){%>\
                    process-mobutilformname="${process.getMobUtilFormName()}"
<% } %>\
<%if(process.getMobUtil2FormName()){%>\
                    process-mobutil2formname="${process.getMobUtil2FormName()}"
<% } %>\
<%if(process.getMobUtil3FormName()){%>\
                    process-mobutil3formname="${process.getMobUtil3FormName()}"
<% } %>\
<%if(process.isEditable()){%>\
                    process-isEditable="${process.isEditable()}"
<% } %>\
<%if(process.getEditMode()){%>\
                    process-editMode="${process.getEditMode()}"
<% } %>\
<%if(process.getEditFields() && process.getEditFields().size > 0){%>\
                    editFields="<%process.getEditFields().eachWithIndex{ field,index -> %><%=(index>0?";":"")%>${field.toLowerCase()}<%}%>"
<% } %>\
<%if(process.getMemoField()){%>\
                    process-memofield="${process.getMemoField()}"
<% } %>\
                />
            </extensionElements>
<% } %>\
<% if ((process instanceof net.ibizsys.model.wf.IPSWFCallActivityProcess)  && ( process.isMultiInstance()
)){ %>\
            <extensionElements>
                <flowable:form
<%if(process.isMultiInstance()){%>\
                    candidateUsersList="${process.getCloudPorcessUser2()}<%=(process.getCloudPorcessUser2())? "||#{activedata.srfwfpredefinedusers}":"#{activedata.srfwfpredefinedusers}"%>"
<% } %>\
                />
            </extensionElements>
<% } %>\
<% if (process.isMultiInstance()){ %>\
            <multiInstanceLoopCharacteristics flowable:collection="candidateUsersList" flowable:elementVariable="candidateUsers" isSequential="${process.isSequential()}">
                <completionCondition><![CDATA[\${wfCoreService.accessCondition(execution)}]]></completionCondition>
            </multiInstanceLoopCharacteristics>
<% } %>\
<% if (process.getWFProcessType()=='EMBED'){ %>\
        </subProcess>
<% } else if (process.getWFProcessType()=='CALLORGACTIVITY'){ %>\
        </callActivity>
<% } else { %>\
        </userTask>

<% } %>\
<% if (process.hasTimeOutLink()){ %>\
        <boundaryEvent id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%>bid-${process.getWFStepValue()}-${process.getDeployId2()}" name="timeout-${process.getName()}" attachedToRef="tid-${process.getWFStepValue()}-${process.getDeployId2()}" cancelActivity="true">
            <timerEventDefinition>
                <timeDate>${process.getCloudDueDate()}</timeDate>
            </timerEventDefinition>
        </boundaryEvent>
<% } %>\
<% } %>\
<% }} %>\



<% if (item.getPSWFLinks()) {
  item.getPSWFLinks().each{ link -> %>\
        <sequenceFlow id="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%><%=(link.getWFLinkType()!='ROUTE')?"lid-":"rid-"%>${link.getDeployId2()}"  name="${ctx.ignoreNullString(link.getLogicName())}"
            sourceRef="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%><%if(link.getWFLinkType() == 'TIMEOUT'){%>bid-${link.getFromPSWFProcess().getWFStepValue()}-${link.getFromPSWFProcess().getDeployId2()}<%}else if(link.getFromPSWFProcess().getWFProcessType()=='INTERACTIVE'){%>tid-${link.getFromPSWFProcess().getWFStepValue()}-${link.getFromPSWFProcess().getDeployId2()}<%} else {%>sid-${link.getFromPSWFProcess().getDeployId2()}<%}%>"
            targetRef="<%= (parent)?"sub-"+parent.getCodeName()+"-":""%><%if(link.getWFLinkType() == 'TIMEOUT'){%>bid-${link.getToPSWFProcess().getWFStepValue()}-${link.getToPSWFProcess().getDeployId2()}<%}else if(link.getToPSWFProcess().getWFProcessType()=='INTERACTIVE'){%>tid-${link.getToPSWFProcess().getWFStepValue()}-${link.getToPSWFProcess().getDeployId2()}<%} else {%>sid-${link.getToPSWFProcess().getDeployId2()}<%}%>">
<%if(link.getWFLinkType() != 'WFRETURN'){
        if(link.getWFLinkType() == 'ROUTE'){%>\
<%if(link.getCustomCond()){%>\
            <conditionExpression  xsi:type="tFormalExpression">${link.getCustomCond()}</conditionExpression>
<% } else if(link.getCloudCondInfo()){ %>\
            <conditionExpression  xsi:type="tFormalExpression"><![CDATA[<%out.print("\$")%>{${link.getCloudCondInfo()}}]]></conditionExpression>
<% } %>\
<% } else { %>\
            <conditionExpression  xsi:type="tFormalExpression"><![CDATA[<%out.print("\$")%>{sequenceFlowId=="<%=(link.getWFLinkType()!='ROUTE')?"lid-":"rid-"%>${link.getDeployId2()}"}]]></conditionExpression>
<% }} %>\
<% if ( (link instanceof net.ibizsys.model.wf.IPSWFInteractiveLink) &&
 ( link.getFormCodeName() || link.getMobFormCodeName() || link.getViewCodeName() || link.getMobViewCodeName() || link.getNextCondition() || link.getCustomCond() )
){ %>\
            <extensionElements>
                <flowable:form
<%if(link.getFormCodeName()){%>\
                    sequenceFlowForm="${link.getFormCodeName()}"
<% } %>\
<%if(link.getMobFormCodeName()){%>\
                    sequenceFlowMobForm="${link.getMobFormCodeName()}"
<% } %>\
<%if(link.getViewCodeName()){%>\
                    sequenceFlowView="${link.getViewCodeName()}"
<% } %>\
<%if(link.getMobViewCodeName()){%>\
                    sequenceFlowMobView="${link.getMobViewCodeName()}"
<% } %>\
<%if(link.getNextCondition()){%>\
                    nextCondition="${link.getNextCondition()}"
<% } %>\
<%if(link.getCustomCond()){%>\
                    customCond="${link.getCustomCond()}"
<% } %>\
                />
            </extensionElements>
<% } %>\
        </sequenceFlow>

<% }} %>\
<% } %>