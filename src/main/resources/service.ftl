<#escape x as (x!)?html>

		<#macro stubRow stub indence inheritanceInvolved>			
			<tr>
				<td>
					<#list 0..indence as i>
					  	&nbsp;&nbsp;&nbsp;&nbsp;
					</#list>  
					${elementName(stub.stubName)}
				</td>						
				 		
				<td>
					<#noescape>														
						<#assign stubTypeName>
							${elementType(stub.type.name)}
						</#assign>
						${stubTypeName}
					</#noescape>	
				</td>
				 		
				<td>${stub.required?string("Y","")}</td>				  		
				<td>${stub.multiOccurs?string("Y","")}</td>																								
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


	<!DOCTYPE html>
	<html lang="en">
		<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		
	
		<!-- a bootstrap css is mandatory -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">	
		
		<style type="text/css">

		
			.summary-table tr td:nth-child(1){
    			width:5%;
			}
			.summary-table tr td:nth-child(2){
    			width:5%;
			}		
			.summary-table tr td:nth-child(3){
    			width:30%;
			}					
			.info-table tr td:nth-child(1){
    			width:15%;
			}						

			.operation-intro-table tr td:nth-child(1){
    			width:20%;
			}			
			
			.param-table tr td:nth-child(1){
				width:10%;
			}
			.param-table tr td:nth-child(2){				
				width:10%;
			}
			.param-table tr td:nth-child(3){
				width:60%;
			}
			.param-table tr td:nth-child(4){
				width:10%;
			}													
						
			.response-table tr td:nth-child(1){
				width:10%;
			}			
			
			.response-table tr td:nth-child(2){				
				width:20%;
			}		
			
			.model-rows-table tr td:nth-child(1){
				width:40%;
			} 
			.model-rows-table tr td:nth-child(2){
				width:5%;
			}		
			.model-rows-table tr td:nth-child(3){
				width:40%;
			}		
			.model-rows-table tr td:nth-child(4){
				width:5%;
			}		
			.model-rows-table tr td:nth-child(5){
				width:5%;
			}																	
			.model-rows-table tr td:nth-child(6){
				width:5%;
			}					
 						
			
		</style>
		
			
		<title>
			${className(service.webServiceClass.name)}
		</title>
	
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		</head>
		<body>
		
		<div class="container">
			<h1>${className(service.webServiceClass.name)}</h1>		
			 			
			<h2>Operations</h2>		
			<#include "include/methodsSummary.ftl"/>
 
 			<#if service.methodStubs??>
 				<#list service.methodStubs as method>
 					<#include "include/methodDetail.ftl"/>
 				</#list>
 			</#if>	
		</div>			
		</body>
	</html>

 
</#escape>


