		<#macro stubChildrenAsTable stub>	
			<#if (stub.childStubs??  && (stub.childStubs?size > 0)) >
				<table class="table table-condensed table-bordered model-rows-table">																								
					<tr>
						<th>Property</th>
						<th>Type</th>						
						<th>Description</th>						 
					</tr>
					<#list stub.childStubs as childStub>									
						<@stubRow stub=childStub parentPath=""/>
					</#list>											 	
				</table>
			</#if>											
		</#macro>					
		
		<#macro stubRow stub parentPath>			 	
					<tr>
						<td>
							${stubOgnl(stub, parentPath)}
						</td>						
						 		
						<td>
							${stubType(stub)}
						</td>
						 		
						<td>${stub.description!}</td>

					</tr>
					<#list stub.childStubs as childStub>									
						<@stubRow stub=childStub parentPath=stubOgnl(stub, parentPath)/>
					</#list>			 										
		</#macro>
