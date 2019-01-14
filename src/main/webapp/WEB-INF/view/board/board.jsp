<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/common/lib.jsp" %>
<body>
<%@ include file="/common/nav.jsp" %>
<%@ include file="/common/left.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		console.log("document.ready");
		
		$(".userList").on("click", ".userClick", function(){
			console.log("userClick")
			var postCode = $(this).children()[5].innerText;
			var userId = $(this).children()[2].innerText;
			
			location.href='/postDetail?postCode='+postCode;
			
		});
		/* var ev = "click";
		$(".userClick").on("click", function(){
			
		});
		 */
	});
	
	//page/pageSize 인자를 받아서 
	//해당 페이지에 속하는 사용자 리스트 정보를 가져온다 
	function getUserList(pageNumber){
		var pageSize = 10;
		var boardCode = ${boardCode};
		
		console.log("pageNumber : "+pageNumber);	
		
		
		//ajax call
		//사용자 리스트 데이터만 가져오기 
		//html(as-is) --> json(to-be) 데이터를 받는 형태로 변경 
		$.ajax({
			
			type: "GET",
			url:"/postPageListAjax",
			data : "pageNumber="+pageNumber+"&boardCode="+boardCode,
			success : function(data){
				//data(사용자 json 데이터)를 바탕으로 
				//사용자 리스트를 갱신
				//1. 기존 리스트를 삭제 
				//2. data를 이용하여 table 태그(tr) 작성
				//3. 기존 리스트 위치에다가 붙여 넣기
				console.log(data);
				var html = "";
				
				$.each(data.postList, function(idx, post){
					console.log(post);
					html+="<tr class= 'userClick'>";
					html+="<td>"+post.rnum+"</td>";
					html+="<td class='trlength'>"+post.title+"</td>";
					html+="<td>"+post.userId+"</td>";
					html+="<td>"+post.postDate+"</td>";
					html+="<td>"+post.postInquiry+"</td>";
					html+="<td id='displaybye' style='display:none;'><b>"+post.postCode+"</b></td>";
					html+="</tr>";
				})
				
				$(".userList").html("");
				$(".userList").html(html);
			 
			}
		});
		
		
		
	}
</script>
	 <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">
          	<%-- <c:choose>
	          	<c:when test="${boardList!=null}">
					<c:forEach items="${boardList}" var="board">
						<c:if test="${board.boardCode == boardCode}">
							${board.boardName}
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose> --%>
			${boardName}
          </h1>
          
          <h2 class="sub-header">게시판</h2>
          <div class="table-responsive">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>번호</th>
                  <th class="trlength">제목</th>
                  <th>글쓴이</th>
                  <th>날짜</th>
                  <th>조회수</th>
                  <th style="display: none">고유코드</th>
                </tr>
              </thead>
              <tbody class="userList">
	            <c:choose>
		            <c:when test="${fn:length(postList) == 0}">
			            <tr class="userClick">
		                  <td colspan="6">해당 게시판에 게시물이 존재하지 않습니다.</td>
		                </tr>
					</c:when>
		          	<c:when test="${postList!=null}">
						<c:forEach items="${postList}" var="post">
				            <tr class="userClick">
			                  <td>${post.rnum}</td>
			                  <td class="trlength">${post.title}</td>
			                  <td>${post.userId}</td>
			                  <fmt:parseDate var="date" value="${post.postDate}" pattern="yyyy-MM-dd"/>
			                  <td><fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></td>
			                  <td>${post.postInquiry}</td>
			                  <td style="display: none"><b>${post.postCode}</b></td>
			                </tr>
						</c:forEach>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose> 
              </tbody>
            </table>
          </div>
          <div class="text-left">
          	  <form class="form-inline" action="/boardSearch" method="post">
          	  	  
				  <div class="form-group">
				    <select class="form-control" name="boardAvailable">
				      	<option value="1" selected="selected">제목</option>
					</select>
				  </div>
				  <div class="form-group">
				    <input type="text" class="form-control" id="postName" name="postName" placeholder="검색">
				  </div>
				  <button type="submit" class="btn btn-primary">검색</button>
				</form>	
				<div class="text-right">
          		<a href="/postSend?boardCode=${boardCode}&postRefer=${postRefer}" class="btn btn-primary pull-right">글쓰기</a>
          		</div>
          	</div>	
          	
	          <div class="text-center">
				<ul class="pagination">
					<li>
				      
				    </li>
				    <c:choose>
				    	<c:when test="${pageNumber == 1}">
				    		<li class="disabled">
					    		 <a class="disabled" href="#" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							     </a>
							</li>
				    	</c:when>
				    	<c:when test="${pageNumber > 1}">
				    		 <li class>
					    		 <a href="javascript:getUserList(${pageNumber});" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							     </a>
							</li>
				    	</c:when>
				    </c:choose>
				    <c:choose>
				    	<c:when test="${pageNumber == 1}">
				    		 <li class="disabled"><a class="disabled" href="#"><</a></li>
				    	</c:when>
				    	<c:when test="${pageNumber > 1}">
				    		 <li><a href="javascript:getUserList(${pageNumber-1});"><</a></li>
				    	</c:when>
				    </c:choose>
				    <c:forEach begin="1" end="${totalPage}" var="i">
						<c:choose>
							<c:when test="${i == PageNumber}"><li><a class="active" href="javascript:getUserList(${i});"">${i}</a></li></c:when>
							<c:otherwise><li><a href="javascript:getUserList(${i});">${i}</a></li></c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
				    	<c:when test="${pageNumber == totalPage}">
				    		 <li class="disabled"><a class="disabled" href="#">></a></li>
				    	</c:when>
				    	<c:when test="${pageNumber < totalPage}">
				    		 <li><a href="javascript:getUserList(${pageNumber+1});">></a></li>
				    	</c:when>
				    </c:choose>
				    <c:choose>
				    	<c:when test="${pageNumber == totalPage}">
				    		 <li class="disabled">
				    		 	 <a class="disabled" href="#" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							     </a>
				    		 </li>
				    	</c:when>
				    	<c:when test="${pageNumber < totalPage}">
				    		 <li>
				    		 	 <a href="javascript:getUserList(${totalPage});" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							     </a>
				    		 </li>
				    	</c:when>
				    </c:choose>
				</ul>
			</div>
          
        </div>
        
</body>
</html>