			<#if service.methodStubs??>			
				<div>						
					<table class="table table-bordered table-condensed summary-table">
						<tbody>
								<tr>
									<th>Index</th>
									<th>Method</th>			
									<th>Parameters</th>
									<th>Response Type</th>
									<th>Description</th>													 									 																										
								</tr>																					 												
								<#list service.methodStubs as method> 
									<tr>
										<td><a href="#method${method_index + 1}">${method_index + 1}</a></td>								
										<td><a href="#method${method_index + 1}">${method.methodName}</a></td>
										<td>
											<#if (method.requestStubs??  && (method.requestStubs?size > 0)) >
												<table class="table table-condensed table-bordered">
													<#list method.requestStubs as stub>
														<tr>
															<td>
																${stubName(stub)}
															</td>
														</tr>
													</#list>
												</table>											
											</#if>
										</td>
										<td>
											<#if (method.responseStub??) >
												${stubType(method.responseStub)}
											</#if>
										</td>
										<td>${method.methodDescription}</td>
												
									</tr>								
								</#list>										
						</tbody>
					</table>	
				</div>	
			<#else>
				No Operations				
			</#if>
