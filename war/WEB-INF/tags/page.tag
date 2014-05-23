<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag isELIgnored="false" %> 
<html>
  <head>
  	<link href="stylesheets/bootstrap.css" rel="stylesheet" type="text/css" media="screen" />
    <script  type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="javascript/bootstrap.js"></script>
  </head>
  <body>
    <div class="navbar navbar-fixed-top">
	    <div class="navbar-inner" style="padding-left:20px;">
		    <a class="brand" href="#">Make a wish (CQRS/Event Sourcing example)</a>
		    <ul class="nav">
			    <li class="active"><a href="#">Home</a></li>
			    <li class="divider-vertical"></li>
			    <li>
			        <form action="/wishlist" method="post" class="navbar-form pull-left">
			        	<input id='_method' type='hidden' name='_method' value='post'/>
					    <input type="submit" value="New wish list" class="btn" />
				    </form>
			    </li>
		    </ul>
	    </div>
    </div>
    <div class="container-fluid"  style="margin-top:60px;">
    	<div class="alert alert-info">This is a very simple demo application for the "Command and Query Responsibility Segregation" (CQRS) and Event Sourcing patterns. It allows to create wish lists/shopping lists. At the top you can create a new wish list. Then you may add items from the given list to the wish list, add your own items, remove items, rename the wish list, clear it or delete it. Since there is no authentication process (it's not really necessary to show how CQRS/Event Sourcing works), one can see and edit the wish lists of others. On the right hand side you find the current state of the event store and some log outputs. If you want to learn more about CQRS/Event Sourcing I'd recommend <a href="http://cqrs.wordpress.com/">http://cqrs.wordpress.com</a>, <a href="https://groups.google.com/forum/?fromgroups#!forum/dddcqrs">https://groups.google.com</a> and <a href="http://msdn.microsoft.com/en-us/library/jj554200.aspx">http://msdn.microsoft.com</a>.<a class="close" data-dismiss="alert" href="#">&times;</a></div>
	    <div class="row-fluid">
	       	<div class="span6">
	       		<h4>Application</h4>
	     		<jsp:doBody/>
	     	</div>
		    <div class="span3">
		    	<h4>Event Store</h4>
		    	<c:forEach items="${eventStoreLog}" var="aggregate">
		    		<div style="margin-top: 5px; margin-bottom: 5px; border-bottom: 1px dotted black;">
		    			<div style="font-weight:bold; margin-bottom:3px;">Aggregate: ${aggregate.key}</div>
		    			<c:forEach items="${aggregate.value}" var="event">
		    				<div>${event.class.simpleName}</div>
		    			</c:forEach>
		    		</div>
		    	</c:forEach>
		    </div>
		    <div class="span3">
		    	<h4>Information</h4>
		    	<c:forEach items="${webLog}" var="logRecord">
		    		<div  style="margin-top: 5px; margin-bottom: 5px; border-bottom: 1px dotted black;">${logRecord.message}</div>
		    	</c:forEach>
		    </div>
	    </div>
    </div>
  </body>
</html>