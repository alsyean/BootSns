<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Post - Start Bootstrap Template</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
      body {
        padding-top: 54px;
      }

      @media (min-width: 992px) {
        body {
          padding-top: 56px;
        }
      }
      
  
      #views { 
      	text-align: right; 
      }
      
    </style>
  </head>

  <body>
    <#include "header.ftl">	

    <div class="container">

      <div class="row">

        <div class="col-lg-2"></div>
        <div class="col-lg-8">

          <h1 class="mt-4" id="detail_title"></h1>

       

          <hr>

          <p>Posted on <span id="detail_date"></span></p>
  			<p class="lead">
            	by <span id="detail_user"></span>
          
   					<label id ="views"> view : ${viewCount }</label>
          	</p>
          <hr>

          <div id="detail_content" class="my-5">
          </div>
          <input type="hidden" value = ${write?then('Y', 'N')}>
          
          <#if write>
          <hr>
          <div class="btn btnBD">
	          <button class="btn btn-primary" data-toggle="modal"
					data-target="#modify_post_modal">Modify</button>
	          <button class="btn btn-danger" id="detail_delete_btn">Delete</button>
          </div>
          <hr>
          </#if>
          
          <div class="card my-4">
            <h5 class="card-header">Leave a Comment:</h5>
            <div class="card-body">
              <form>
              	<div class="form-group">
					<input type="text" class="form-control" id="comment_user_text" placeholder="Username">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="comment_password_text" placeholder="password">
				</div>
                <div class="form-group">
                  <textarea class="form-control" rows="3" id="comment_text"></textarea>
                </div>
                <button type="submit" class="btn btn-primary" id="create_comment_btn">Write Comment</button>
              </form>
            </div>
          </div>
          
          <div id="comments">
	          
          </div>
          
        </div>

      </div>

    </div>

    <#include "footer.ftl">
    
    <div class="modal fade" id="modify_post_modal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Modify Post</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="modify_title_text">Title</label>
							<input type="text" class="form-control" id="modify_title_text" placeholder="Title">
						</div>
						<div class="form-group">
							<label for="modify_content_text">Content</label>
							<textarea class="form-control" rows="3" id="modify_content_text"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="modify_post_btn">Modify Post</button>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" id="detail_post_id" value="${id}">
	
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="/js/detail.js"></script>
  </body>

</html>
