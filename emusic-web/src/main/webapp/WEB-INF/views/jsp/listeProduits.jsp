<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Liste des produits</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous" />
	</head>

	<body>
		<div class="container">
			<h1>Liste des produits</h1>
			<a href="ajouterProduit">Ajouter un nouveau produit</a>
			
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Libellé</th>
						<th>Prix</th>
						<th></th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${ produits }" var="produit">
						<tr>
							<td>${ produit.libelle }</td>
							<td>${ produit.prix }</td>
							<td>
								<a class="btn btn-warning" href="editerProduit?id=${ produit.id }">Editer</a>
								<a class="btn btn-danger" href="supprimerProduit?id=${ produit.id }">Supprimer</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>