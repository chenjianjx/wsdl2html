						<#if (method.responseStub??) >
							<#assign stub = method.responseStub/>			 																	
							<div>				
								<table class="table table-bordered stub-table">
									<tbody>
											<tr>
												<th>Name</th>																					 
												<th>Type</th>		
											 													
												<th>Required</th>	
												<th>Description</th>	
											 																										
											</tr>									 
												<#include "rootStubRow.ftl"/>						 																											
									</tbody>						
														
								</table>		
							</div>	
											
						<#else>
							No Response
						</#if>						

