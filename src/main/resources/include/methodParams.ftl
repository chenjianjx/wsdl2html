						<#if (method.requestStubs??  && (method.requestStubs?size > 0)) >
				 																
							<div>				
								<table class="table table-bordered param-table">
									<tbody>
											<tr>
												<th>Name</th>										
												<th>Parameter Type</th>	
												<th>Data Type</th>																					
												<th>Required</th>		
												<th>Description</th>																														
											</tr>	
									<#list method.requestStubs as stub>
												<tr>																	
													<td>${elementName(stub.stubName)}</td>
													<td>TBD</td>
													<td>
														<table class="table table-condensed table-bordered model-rows-table">
															<div class="panel panel-default">																																					 		
																<@stubRow stub=stub indence=0 inheritanceInvolved=method.inheritanceInvolved/>											
															</div>
														</table>
													</td>											
													<td>${stub.required?string("Y","N")}</td>
													<td>${stub.multiOccurs?string("Y","N")}</td>
												</tr>								 					
	
										</tr>
									</#list>												
																					
									</tbody>						
														
								</table>		
							</div>	
											
						<#else>
							No Parameters
						</#if>						
