		<#macro stubChildrenAsTable stub inheritanceInvolved>	
			<#if (stub.childStubs??  && (stub.childStubs?size > 0)) >
				<table class="table table-condensed table-bordered model-rows-table">																								
					<tr>
						<th>Property</th>
						<th>Type</th>						
						<th>Required</th>
						<th>Multiple</th>
					</tr>
					<#list stub.childStubs as childStub>									
						<@stubRow stub=childStub indence=0 inheritanceInvolved=inheritanceInvolved/>
					</#list>											 	
				</table>
			</#if>											
		</#macro>					
		
		<#macro stubRow stub indence inheritanceInvolved>			 	
					<tr>
						<td>
							<#list 0..indence as i>
							  	&nbsp;&nbsp;&nbsp;&nbsp;
							</#list>  
							${elementName(stub.stubName)}
						</td>						
						 		
						<td>
							${elementType(stub.type.name)}
						</td>
						 		
						<td>${stub.required?string("Y","N")}</td>				  		
						<td>${stub.multiOccurs?string("Y","N")}</td>																								
					</tr>								 
					<#list stub.childStubs as childStub>									
						<@stubRow stub=childStub indence=indence+1 inheritanceInvolved=inheritanceInvolved/>
					</#list>			 										
		</#macro>  		


 							
		<#macro showModelRows rows>	 
			<#if (rows?size > 0) >
				<table class="table table-condensed table-bordered model-rows-table">																								
					<tr>
						<th>Property</th>
						<th>Type</th>
						<th>Description</th>
						<th>Format</th>
						<th>Required</th>
						<th>ReadOnly</th>
					</tr>
					<#list rows as row>
						<tr>
							<td>${row.getOgnlPath()}</td>
							<td>${row.getTypeStr()}</td>
							<td>${row.getProperty().getDescription()}</td>
							<td>${row.getProperty().getFormat()}</td>
							<td>${row.getProperty().getRequired()?string('Y', 'N')}</td>
							<td>
								<#if row.getProperty().getReadOnly()??>
									${row.getProperty().getReadOnly()?string('Y', 'N')}
								</#if>
							</td>
						</tr>										
					</#list>
				</table>									
			</#if>
		</#macro>  		