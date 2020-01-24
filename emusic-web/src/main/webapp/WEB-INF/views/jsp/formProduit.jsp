<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>
			<c:if test="${ produit == null }">Ajouter un produit</c:if>
			<c:if test="${ produit != null }">Modifier le produit</c:if>
		</title>
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous" />
	</head>

	<body>
		<div class="container">
			<h1>
				<c:if test="${ produit == null }">Ajouter un produit</c:if>
				<c:if test="${ produit != null }">Modifier le produit</c:if>
			</h1>
			
			<form method="POST">
				<div class="form-group row">
					<label class="col-2 col-form-label">Libellé</label>
					<div class="col-10">
						<input class="form-control" type="text" name="libelle" value="${ produit.libelle }" />
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-2 col-form-label">Prix</label>
					<div class="col-10">
						<input class="form-control" type="number" name="prix" value="${ produit.prix }" />
					</div>
				</div>
				
				<div class="form-group row">
					<div class="col-2"></div>
					<div class="col-10">
						<c:if test="${ produit == null }">
							<input class="btn btn-primary" type="submit" value="Ajouter" />
						</c:if>
						
						<c:if test="${ produit != null }">
							<input class="btn btn-warning" type="submit" value="Modifier" />
						</c:if>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>