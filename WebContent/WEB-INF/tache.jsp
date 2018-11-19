<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste</title>
</head>
<body>

<!-- 
	private Long idTache;
	private String nomTache;
	private String descriptionTache;
	private Date dateDeCreationDeTache;
	private Date DateLimiteDeTache;
	private Integer priorite;
	private List<Marque> marquesDeTache;
	private Long listeID;
	
		
	private Long idMarque;
	private String nomMarque;
 -->
 
${ tache.nomTache } -- ${ tache.descriptionTache } -- ${ tache.dateDeCreationDeTache }
<h2>Listes des marques :</h2>
<c:forEach items="${ marques }" var="marque">
	<p>
		${ marque.nomMarque }
	</p>
</c:forEach>

<form action="tache" method="post">
	<p>
		<label for="nom">Nom de la marque: </label>
		<input type="text" name="nom" id="nom">
	</p>
	<p>
		<input type="hidden" name="id" value="${ tache.idTache }" >
		<input type="submit">
	</p>
</form>

</body>
</html>