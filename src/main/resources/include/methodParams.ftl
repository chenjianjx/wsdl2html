						<#if (method.requestStubs??  && (method.requestStubs?size > 0)) >
				 																
							<div>				
								<table class="table table-bordered stub-table">
									<tbody>
											<tr>
												<th>Name</th>																					 
												<th>Type</th>																					
											 
												<th>Description</th>		
										 																									
											</tr>	
									<#list method.requestStubs as stub>
											<#include "rootStubRow.ftl"/>									 
									</#list>												
																					
									</tbody>						
														
								</table>		
							</div>	
											
						<#else>
							No Parameters
						</#if>						
