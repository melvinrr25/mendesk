<div class="detailBox">
	<div class="titleBox">
		<h4>Request Comments</h4>
	</div>

	<div class="actionBox">
		<ul class="commentList">

			<c:forEach items="${requestComments}" var="comment">
				<li>
					<div class="commenterImage">
						<span class="img glyphicon glyphicon-user"/>
					</div>
					<div class="commentText">
						<p class="">${comment.getContent()}</p>
						<span class="date sub-text">${comment.getUser().getUsername()}</span> - 
						<span class="date sub-text">${comment.getCreatedAt()}</span>
					</div>
				</li>
			</c:forEach>


		</ul>
		<div class="form-inline">
			<div class="">
				<textarea placeholder="Post your comment..."
					class="input-block-level comment-input"></textarea>
			</div>
			<div class="text-right">
				<br />
				<button class="btn btn-success add-comment">Add Comment</button>
			</div>
		</div>
	</div>
</div>
