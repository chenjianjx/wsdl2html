						<div>				
							<table class="table table-bordered response-table">
								<tbody>
										<tr>
											<th>HTTP Status Code</th>										
											<th>Reason</th>	
											<th>Response Type</th>																																																													
										</tr>	
										<#if sw.getOperation(operationId).getResponses()??>
			 							<#list sw.getOperation(operationId).getResponses()?keys as httpCode>
			 								
											<tr>																	
												<td>${httpCode}</td>
												<td>${sw.getOperation(operationId).getResponses()[httpCode] .getDescription()}</td>
												<td>
													<#if sw.getOperation(operationId).getResponses()[httpCode].getSchema()??>													
															<div class="panel panel-default">														
																<div class="panel-heading">${propertyTypeStr(sw.getOperation(operationId).getResponses()[httpCode].getSchema(), opeartionId)}</div>																
																<@showModelRows rows=propertyToModelRows(sw.getOperation(operationId).getResponses()[httpCode].getSchema(), opeartionId)/>																																								
															</div>														 
													</#if>																								
												</td>											
											</tr>								 					
										</#list>
										</#if>
									</tr>										
																				
								</tbody>						
													
							</table>	
						</div>
