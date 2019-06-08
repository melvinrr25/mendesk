<%@ include file="../shared/header.jsp"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="list-group">
				<div class="list-group-item app-bg">
					<h4 class="list-group-item-heading">Request
						#${appRequest.getId()}</h4>
				</div>
				<div class="list-group-item">
					<div class="row">
						<div class="col-md-4">
							<h4 class="list-group-item-heading">Subject</h4>
							<p class="list-group-item-text">${appRequest.getSubject()}</p>
						</div>
						<div class="col-md-4">
							<h4 class="list-group-item-heading">Priority</h4>
							<p class="list-group-item-text">${appRequest.getPriority()}</p>
						</div>
						<div class="col-md-4">
							<h4 class="list-group-item-heading">Status</h4>
							<p class="list-group-item-text">${appRequest.getState()}</p>
						</div>
					</div>

				</div>

				<div class="list-group-item">
					<h4 class="list-group-item-heading">Description</h4>
					<p class="list-group-item-text">${appRequest.getBody()}</p>
				</div>
				<div class="list-group-item text-right">
					<b>${appRequest.getUser().getUsername()}</b> - <i>${appRequest.getCreatedAt()}</i>
				</div>

			</div>
		</div>
	</div>
	<div>
	<%@include file="thread-comments.jsp"%>
	</div>
</div>

<%@include file="../shared/footer.jsp"%>

<script>
	$(document).ready(function(){
		$('.add-comment').click(function(){
			var url = '${request.getContextPath()}/requests/${appRequest.getId()}/comments';
			var $input = $('.comment-input');
			if(!$input.val().length){
				return alert("Please enter a message.")
			}
			
			$.ajax({
				method: 'POST',
				url: url,
				data: { content: $input.val() },
				success: function(res){
					$input.val("").focus()
					location.reload();
				}
			})
		})
	})
</script>