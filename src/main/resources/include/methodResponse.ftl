						<#if (method.responseStub??) >
							<#assign stub = method.responseStub/>			 																	
							<div>				
								<table class="table table-bordered stub-table">
									<tbody>
											<tr>
												<th>Name</th>																					 
												<th>Type</th>		
											 													
											</tr>									 
												<#include "responseRootStubRow.ftl"/>						 																											
									</tbody>						
														
								</table>		
							</div>	
											
						<#else>
							No Response
						</#if>						

