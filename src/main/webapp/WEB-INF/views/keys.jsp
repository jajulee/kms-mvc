<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Key Management System - Key List</title>
<jsp:include page="_header.jsp"></jsp:include>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="_navigation.jsp"></jsp:include>
		<div id="page-wrapper">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Managed Assets <small></small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> List of
								managed assets</li>
						</ol>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>