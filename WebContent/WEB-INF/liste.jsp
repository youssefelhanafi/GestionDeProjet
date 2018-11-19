<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 

	private Long idListe;
	private String nomListe;
	private String descriptionListe;
	private List<Tache> tachesDeLaListe;
	private Long tableauID;

	private Long idTache;
	private String nomTache;
	private String descriptionTache;
	private Date dateDeCreationDeTache;
	private Date DateLimiteDeTache;
	private Integer priorite;
	private List<Marque> marquesDeTache;
	private Long listeID;

 -->

<h2>
	Liste: ${ liste.nomListe }
</h2>
<h3>
	${ liste.descriptionListe }
</h3>

<h4>
	Les taches de cette liste: 
</h4>

<c:forEach items="${ taches }" var="tache">
	<p>
		<a href="tache?id=${ tache.idTache }">${ tache.nomTache }</a>
 -- ${ tache.descriptionTache } -- date limite: ${ tache.dateLimiteDeTache } -- priorité: ${ tache.priorite }
	</p>
</c:forEach>

<form action="liste" method="post">
<!-- `nom_tache`, `description_tache`, `date_creation`, `date_limite`, `priorite`, `liste_id` -->
	<p>
		<label for="nom">Nom de la tache : </label>
		<input type="text" name="nom" id="nom">
	</p>
	<p>
		<label for="desc">Description de la tache : </label>
		<input type="text" name="desc" id="desc">
	</p>
	<p>
		<label for="date">Date limite de la tache:  </label>
		<input type="date" name="date" id="date">
	</p>
	<p>
		<label for="priorite">Date limite de la tache:  </label>
		<input type="text" name="priorite" id="priorite">
	</p>
	<p>
		<input type="hidden" name="id" value="${ liste.idListe }">
		<input type="submit">
	</p>
</form>

</body>
</html>