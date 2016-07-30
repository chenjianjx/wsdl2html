						<#if (method.responseStub??) >
							<#assign stub = method.responseStub/>			 																	
							<div>				
								<table class="table table-bordered stub-table">
									<tbody>
											<tr>
												<th>Name</th>																					 
												<th>Type</th>																					
												<th>Required</th>		
												<th>Multiple</th>																														
											</tr>	
								 
												<tr>																	
													<td>${elementName(stub.stubName)}</td>												 
													<td>														 
														<div class="panel panel-default">
																<div class="panel-heading">${className(stub.type.name)}</div>																																					 		
																<@stubChildrenAsTable stub=stub inheritanceInvolved=method.inheritanceInvolved/>											
														</div>														  
													</td>											
													<td>${stub.required?string("Y","N")}</td>
													<td>${stub.multiOccurs?string("Y","N")}</td>
												</tr>								 						
																					
									</tbody>						
														
								</table>		
							</div>	
											
						<#else>
							No Response
						</#if>						

