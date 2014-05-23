<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %> 

<t:page>
    <jsp:body>
    	<c:if test="${not empty status}">
    		<div class="alert alert-error">${status}<a class="close" data-dismiss="alert" href="#">&times;</a></div>
	    </c:if>
	    
        <table class="table table-striped">
	        <colgroup>
		       <col span="1" style="width: 40%;">
		       <col span="1" style="width: 45%;">
		       <col span="1" style="width: 15%;">
		    </colgroup>
        	<tr>
        		<td>Notebook</td>
        		<td>600$</td>
        		<td>
        			<form id="product-form" action="/wishlist" method="post" style="height:6px;">
        				<input id='wishlist-id' type='hidden' name='wishlist-id' value=''/>
        				<input id='item-name' type='hidden' name='item-name' value='Notebook'/>
        				<input id='item-price' type='hidden' name='item-price' value='600'/>
        				<input id='_method' type='hidden' name='_method' value='put'/>
        				<input id='change-action' type='hidden' name='change-action' value='2'/>
	        			<div class="btn-group">
						    <a class="btn btn-small dropdown-toggle btn-primary" data-toggle="dropdown" href="#">
							    Add to wish list
							    <span class="caret"></span>
						    </a>
						    <ul class="dropdown-menu">
						    	<c:forEach items="${wishLists}" var="wishListDto">
							    	<li>
							    		<a href="#" onclick="$(this).parents('form:first').find('#wishlist-id').val('${wishListDto.id}'); $(this).parents('form:first').submit();">${wishListDto.name}</a>
							    	</li>
						    	</c:forEach>
						    </ul>
					    </div>					    
				    </form>
			    </td>
        	</tr>
        	<tr>
        		<td>PC</td>
        		<td>1000$</td>
        		<td>    
        			<form id="product-form" action="/wishlist" method="post" style="height:6px;">
        				<input id='wishlist-id' type='hidden' name='wishlist-id' value=''/>
        				<input id='item-name' type='hidden' name='item-name' value='PC'/>
        				<input id='item-price' type='hidden' name='item-price' value='1000'/>
        				<input id='_method' type='hidden' name='_method' value='put'/>
        				<input id='change-action' type='hidden' name='change-action' value='2'/>
	        			<div class="btn-group">
						    <a class="btn btn-small dropdown-toggle btn-primary" data-toggle="dropdown" href="#">
							    Add to wish list
							    <span class="caret"></span>
						    </a>
						    <ul class="dropdown-menu">
						    	<c:forEach items="${wishLists}" var="wishListDto">
							    	<li>
							    		<a href="#" onclick="$(this).parents('form:first').find('#wishlist-id').val('${wishListDto.id}'); $(this).parents('form:first').submit();">${wishListDto.name}</a>
							    	</li>
						    	</c:forEach>
						    </ul>
					    </div>					    
				    </form>
			    </td>
        	</tr>
        	<tr>
        		<td>Micro controller</td>
        		<td>200$</td>
        		<td>    
        			<form id="product-form" action="/wishlist" method="post" style="height:6px;">
        				<input id='wishlist-id' type='hidden' name='wishlist-id' value=''/>
        				<input id='item-name' type='hidden' name='item-name' value='Micro controller'/>
        				<input id='item-price' type='hidden' name='item-price' value='200'/>
        				<input id='_method' type='hidden' name='_method' value='put'/>
        				<input id='change-action' type='hidden' name='change-action' value='2'/>
	        			<div class="btn-group">
						    <a class="btn btn-small dropdown-toggle btn-primary" data-toggle="dropdown" href="#">
							    Add to wish list
							    <span class="caret"></span>
						    </a>
						    <ul class="dropdown-menu">
						    	<c:forEach items="${wishLists}" var="wishListDto">
							    	<li>
							    		<a href="#" onclick="$(this).parents('form:first').find('#wishlist-id').val('${wishListDto.id}'); $(this).parents('form:first').submit();">${wishListDto.name}</a>
							    	</li>
						    	</c:forEach>
						    </ul>
					    </div>					    
				    </form>
			    </td>
        	</tr>
        	<tr>
        		<td>Java book</td>
        		<td>50$</td>
        		<td>    
        			<form id="product-form" action="/wishlist" method="post" style="height:6px;">
        				<input id='wishlist-id' type='hidden' name='wishlist-id' value=''/>
        				<input id='item-name' type='hidden' name='item-name' value='Java book'/>
        				<input id='item-price' type='hidden' name='item-price' value='50'/>
        				<input id='_method' type='hidden' name='_method' value='put'/>
        				<input id='change-action' type='hidden' name='change-action' value='2'/>
	        			<div class="btn-group">
						    <a class="btn btn-small dropdown-toggle btn-primary" data-toggle="dropdown" href="#">
							    Add to wish list
							    <span class="caret"></span>
						    </a>
						    <ul class="dropdown-menu">
						    	<c:forEach items="${wishLists}" var="wishListDto">
							    	<li>
							    		<a href="#" onclick="$(this).parents('form:first').find('#wishlist-id').val('${wishListDto.id}'); $(this).parents('form:first').submit();">${wishListDto.name}</a>
							    	</li>
						    	</c:forEach>
						    </ul>
					    </div>					    
				    </form>
			    </td>
        	</tr>
        	<tr>
        		<td>High performance cluster</td>
        		<td>10.000$</td>
        		<td>    
        			<form id="product-form" action="/wishlist" method="post" style="height:6px;">
        				<input id='wishlist-id' type='hidden' name='wishlist-id' value=''/>
        				<input id='item-name' type='hidden' name='item-name' value='High performance cluster'/>
        				<input id='item-price' type='hidden' name='item-price' value='10000'/>
        				<input id='_method' type='hidden' name='_method' value='put'/>
        				<input id='change-action' type='hidden' name='change-action' value='2'/>
	        			<div class="btn-group">
						    <a class="btn btn-small dropdown-toggle btn-primary" data-toggle="dropdown" href="#">
							    Add to wish list
							    <span class="caret"></span>
						    </a>
						    <ul class="dropdown-menu">
						    	<c:forEach items="${wishLists}" var="wishListDto">
							    	<li>
							    		<a href="#" onclick="$(this).parents('form:first').find('#wishlist-id').val('${wishListDto.id}'); $(this).parents('form:first').submit();">${wishListDto.name}</a>
							    	</li>
						    	</c:forEach>
						    </ul>
					    </div>					    
				    </form>
			    </td>
        	</tr>
        	<tr>
        		<td>Tablet</td>
        		<td>300$</td>
        		<td>    
        			<form id="product-form" action="/wishlist" method="post" style="height:6px;">
        				<input id='wishlist-id' type='hidden' name='wishlist-id' value=''/>
        				<input id='item-name' type='hidden' name='item-name' value='Tablet'/>
        				<input id='item-price' type='hidden' name='item-price' value='300'/>
        				<input id='_method' type='hidden' name='_method' value='put'/>
        				<input id='change-action' type='hidden' name='change-action' value='2'/>
	        			<div class="btn-group">
						    <a class="btn btn-small dropdown-toggle btn-primary" data-toggle="dropdown" href="#">
							    Add to wish list
							    <span class="caret"></span>
						    </a>
						    <ul class="dropdown-menu">
						    	<c:forEach items="${wishLists}" var="wishListDto">
							    	<li>
							    		<a href="#" onclick="$(this).parents('form:first').find('#wishlist-id').val('${wishListDto.id}'); $(this).parents('form:first').submit();">${wishListDto.name}</a>
							    	</li>
						    	</c:forEach>
						    </ul>
					    </div>					    
				    </form>
			    </td>
        	</tr>
        </table>
        
        <div style="height:40px;"></div>
        
        <c:forEach items="${wishLists}" var="wishListDto">
	        <h5><span style="text-decoration: underline;">${fn:escapeXml(wishListDto.name)}</span></h5>
	        <button class="btn btn-small btn-primary" type="button"  data-toggle="modal" data-target="#modal-change-name-${wishListDto.id}"  style="float:left;">Change name</button>
	        <button class="btn btn-small btn-primary" type="button"  data-toggle="modal" data-target="#modal-add-item-${wishListDto.id}"  style="float:left; margin-left: 10px;">Add own item</button>
	        <form action="/wishlist" method="post"  style="float:left; margin-left: 10px;">
	        	<input id='wishlist-id' type='hidden' name='wishlist-id' value='${wishListDto.id}'/>
	        	<input id='_method' type='hidden' name='_method' value='put'/>
	        	<input id='change-action' type='hidden' name='change-action' value='1' />
			    <button type="submit" class="btn btn-small btn-warning">Clear wish list</button>
		    </form>
		    <form action="/wishlist" method="post" style="float:left; margin-left: 10px;">
		    	<input id='wishlist-id' type='hidden' name='wishlist-id' value='${wishListDto.id}'/>
		    	<input id='_method' type='hidden' name='_method' value='delete'/>
			    <button type="submit" class="btn btn-small btn-danger" >Delete wish list</button>
		    </form>
	        <table class="table table-striped" style="margin-top:10px;">
		        <colgroup>
			       <col span="1" style="width: 40%;">
			       <col span="1" style="width: 45%;">
			       <col span="1" style="width: 15%;">
			    </colgroup>
			    <c:if test="${empty wishListDto.items}">
			    	<div style="clear:left;">There are currently no items in this wish list. </div>
			    </c:if>
	        	<c:forEach items="${wishListDto.items}" var="item">
		        	<tr>
		        		<td>${fn:escapeXml(item.element2)}</td>
		        		<td>${fn:escapeXml(item.element3)}$</td>
		        		<td>
		        			<form action="/wishlist" method="post" class="navbar-form pull-left">
		        				<input id='wishlist-id' type='hidden' name='wishlist-id' value='${wishListDto.id}'/>
		        				<input id='item-id' type='hidden' name='item-id' value='${item.element1}'/>
		        				<input id='_method' type='hidden' name='_method' value='put'/>
		        				<input id='change-action' type='hidden' name='change-action' value='3' />
							    <button type="submit" class="btn btn-small btn-danger" >Remove item</button>
						    </form>
					    </td>
		        	</tr>
	        	</c:forEach>
	        </table>
	        
		    <!-- Modal (Change Name) -->
		    <div id="modal-change-name-${wishListDto.id}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="changeName" aria-hidden="true">
			    <div class="modal-header">
				    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				    <h3 id="modal-label">Change name of wish list "${wishListDto.name}"</h3>
				</div>
				<div class="modal-body">
					<form id="change-wish-list-name-form-${wishListDto.id}" action="/wishlist" method="post">
						<fieldset>
					    	 <label>Enter new name</label>
					    	 <input id="new-name" name="new-name" type="text" style="height:30px;" />
					    	 <input id='wishlist-id' type='hidden' name='wishlist-id' value='${wishListDto.id}'/>
					    	 <input id='_method' type='hidden' name='_method' value='put'/>
					    	 <input id='change-action' type='hidden' name='change-action' value='0' />
				    	 </fieldset>
				    </form>
				</div>
				<div class="modal-footer">
				    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
					<button type="submit" class="btn btn-primary" onclick="$('#change-wish-list-name-form-${wishListDto.id}').submit();">Change wish list name</button>
			    </div>
		    </div>
		    
		    <!-- Modal (Add item) -->
		    <div id="modal-add-item-${wishListDto.id}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="addItem" aria-hidden="true">
			    <div class="modal-header">
				    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				    <h3 id="modal-label">Add own item to wishlist "${wishListDto.name}"</h3>
				</div>
				<div class="modal-body">
					<form id="add-item-form-${wishListDto.id}" action="/wishlist" method="post">
						<fieldset>
					    	 <label>Name:</label>
					    	 <input id='item-name' type="text" name='item-name' style="height:30px;"/>
					    	 <label>Price:</label>
		       				 <input id='item-price' type="text" name='item-price' style="height:30px;"/>
		       				 
					    	 <input id='wishlist-id' type='hidden' name='wishlist-id' value='${wishListDto.id}'/>
		       				 <input id='_method' type='hidden' name='_method' value='put'/>
		       				 <input id='change-action' type='hidden' name='change-action' value='2'/>
				    	 </fieldset>
				    </form>
				</div>
				<div class="modal-footer">
				    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
					<button type="submit" class="btn btn-primary" onclick="$('#add-item-form-${wishListDto.id}').submit();">Add item to wish list</button>
			    </div>
		    </div>
	    </c:forEach>
        
    </jsp:body>
</t:page>