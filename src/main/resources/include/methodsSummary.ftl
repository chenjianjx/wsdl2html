			<#if service.methodStubs??>			
				<div>						
					<table class="table table-bordered table-condensed summary-table">
						<tbody>
								<tr>
									<th>Index</th>
									<th>Method</th>																 									 																										
								</tr>																					 												
								<#list service.methodStubs as method> 
									<tr>
										<td><a href="#method${method_index + 1}">${method_index + 1}</a></td>								
										<td><a href="#method${method_index + 1}">${method.methodName}</a></td>																	 										 										 					
									</tr>								
								</#list>										
						</tbody>
					</table>	
				</div>	
			</#if>