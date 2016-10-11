<#escape x as (x!)?html>
	
	<#include "include/macros.ftl"/>

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
    			width:20%;
			}			
 			.summary-table tr td:nth-child(3){
    			width:20%;
			}
 			.summary-table tr td:nth-child(4){
    			width:10%;
			}		
 			.summary-table tr td:nth-child(5){
    			width:45%;
			}		
			
			.stub-table tr td:nth-child(1){
				width:10%;
			}
			.stub-table tr td:nth-child(2){				
				width:60%;
			}
	
			.stub-table tr td:nth-child(3){
				width:30%;
			}
 	
			
			.model-rows-table tr td:nth-child(1){
				width:20%;
			} 
			.model-rows-table tr td:nth-child(2){
				width:25%;
			}		
			.model-rows-table tr td:nth-child(3){
				width:45%;
			}		
 						
			
		</style>
		
			
		<title>
			${className(service.webServiceClass)}
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
			<h1>${className(service.webServiceClass)}</h1>		
			 			
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


