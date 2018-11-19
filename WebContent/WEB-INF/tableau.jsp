<!DOCTYPE html>
<html>
    
    <head>
        <title>${ tableau.nomTableau }</title>
        <!-- Bootstrap -->
         <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="assets/styles.css" rel="stylesheet" media="screen">
        <link href="assets/DT_bootstrap.css" rel="stylesheet" media="screen">
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <style type="text/css">
        #containerIntro h2,
        #containerIntro p {
            margin-left: 5px;
            display: inline;
            vertical-align: bottom;
            font-family: 'Open Sans', sans-serif;
        }
        .inline div{
            margin-left: 5px;
            display: inline;
            vertical-align: bottom;
            font-family: 'Open Sans', sans-serif;
        }.boody{
            width: 100%;
            height: 129px;
            background-color: #e1e1e1;
            /* margin-top: 30px; */
            text-align: center;
        }.top{
            width: 100%;
            height: 40px;
            background-color: #999999;
            margin-top: 10px;
            text-align: center;
        }
    </style>
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">TimeManager</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i>
                                	<%= session.getAttribute("userFirstName") %> <%= session.getAttribute("userLastName") %>
                                <i class="caret"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="profil">Profil</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1" href="logout">Deconnexion</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav">
                            <li>
                                <a href="#">Mes tâches</a>
                            </li>
                            <li>
                                <a href="#">Mes marques</a>
                            </li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span1"></div>
                <!--/span-->
                <div class="span10" id="content" style="margin-left: 10px">

                    <div class="row-fluid">
                        
                        <div class="navbar">
                            <div class="navbar-inner">
                                <ul class="breadcrumb">
                                    <li>
                                        <a href="#">Profil</a> <span class="divider">/</span>    
                                    </li>
                                    <li>
                                        <a href="tableaux?id=${ tableau.idTableau }">Projets</a> <span class="divider">/</span>    
                                    </li>
                                    <li class="active">${ tableau.nomTableau }</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                       <div class="span12" style="margin-left: 0px;margin-right: 10px">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">${ tableau.nomTableau }</div>
                                    <div class="pull-right"><span class="badge badge-info">${ tableau.nbrTaches }</span>
                                    <div class="pull-right">
                                    </div>
                                </div>
                            </div>
                            <div class="block-content collapse in" >
                                <div class="span12">
                                    <div id="containerIntro">
                                        <h2>${ tableau.nomTableau }</h2>
                                        <p style="margin-bottom: 5px"><i class="fa fa-calendar"></i>&nbsp;&nbsp;De&nbsp;&nbsp; 
                                        <i class="icon-time"></i> ${ dateDebut } &nbsp;à&nbsp;&nbsp; <i class="icon-time"></i> ${ dateLimite } </p>
                                    </div>
                                </div>
                                <div class="span12">
                                    <div class="span6">
                                       <br>
                                       <h5><i class="fa fa-align-justify" aria-hidden="true" style="color:#999" ></i> Description</h5>
                                       <p id="textBlock" style="width: 100%; height: 160px" onclick="document.getElementById('textArea').style.display='block';document.getElementById('butt').style.display='block';this.style.display='none';">
                                       		${ tableau.descriptionTableau }
                                       </p>
                                        <textarea id="textArea" class="" placeholder="Enter text ..." style="width: 100%; height: 160px;display: none;" >
                                        	${ tableau.descriptionTableau }
                                        </textarea>
                                    </div>
                                    <div class="span2">
                                       <br>
                                       <h5><i class="icon-time" aria-hidden="true" style="color:#999" ></i> Date creation</h5>
                                       <p style="width: 100%;" >${ tableau.dateCreation }</p>
                                       <h5><i class="icon-time" aria-hidden="true" style="color:#999" ></i> Date debut</h5>
                                       <p style="width: 100%;">${ dateDebut }</p>
                                       <h5><i class="icon-time" aria-hidden="true" style="color:#999" ></i> Date fin</h5>
                                       <p style="width: 100%;" >${ dateLimite }</p>
                                    </div>
                                    <div class="span3" style="">
                                        <div style="margin-top: 30px;background-color: #999999">
                                          <div class="top">
                                            <h3 id="Day">Vendredi</h3></div>
                                          <div class="boody">
                                              <h3 id="Month">MAR</h3>
                                              <h1 id="DayNum">15</h1>
                                              <h6 id="Year">2016</h6>
                                          </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="span12">
                                    <span class="span4">
                                        <h5>Tâches</h5>
                                        <div class="span4">
                                            <i class="fa fa-check-circle fa-3x" style="color:#999">${ nbrTaches + tableau.tachesSupprimes }</i>                                              
                                        </div>
                                        <div class="span4">
                                            <i class="fa fa-minus-circle fa-3x" style="color:#999">${ tableau.tachesSupprimes }</i>                                              
                                        </div>
                                        <div class="span3">
                                            <i class="fa fa-refresh fa-3x" style="color:#999">${ nbrTaches }</i>                                            
                                        </div>
                                    </span>
                                    <span class="span7" style="background-color: ">
                                        <h5>Priorité des tâches</h5>
                                        <span class="span4">
                                        	<i class="fa fa-cog fa-2x" style="color:#999"></i>
                                        	<i class="fa fa-cog fa-2x" style="color:#999"></i>
                                            <i class="fa fa-cog fa-2x" style="color:#999">${ hautes } Haute(s)</i>                                              
                                        </span>
                                        <span class="span4">
                                        	<i class="fa fa-cog fa-2x" style="color:#999"></i>
                                            <i class="fa fa-cog fa-2x" style="color:#999">${ moyennes } Normale(s)</i>                                             
                                        </span>
                                        <span class="span3">
                                            <i class="fa fa-cog fa-2x" style="color:#999">${ basses } Basse(s)</i>                                          
                                        </span>
                                    </span>
                                </div>
                                <span class="span5">
                                    <h5>Marques</h5>
                                    <p>
                                    	<c:forEach items="${ marques }" var="marque" varStatus="status">
                                    		<a href="${ marque.idMarque }">${ marque.nomMarque }</a> <c:if test="${ !status.last }"> / </c:if>
                                    	</c:forEach>
                                    </p>
                                </span>
                                <span class="span2">
                                     <div class="block">
                                        <div class="navbar navbar-inner block-header">
                                            <div class="muted pull-left">Commits</div>
                                        </div>
                                        <div class="block-content collapse in" >
                                            
                                            <div class="span12">
                                                <i class="fa fa-share-alt fa-2x" style="color:#999">${ tableau.nbrCommits }</i>                                              
                                            </div>
                                        </div>
                                    </div>
                                </span>
                                <span class="span4">
                                     <div class="block">
                                        <div class="navbar navbar-inner block-header">
                                            <div class="muted pull-left">checklists</div>
                                        </div>
                                        <div class="block-content collapse in" >
                                            
                                            <div class="span5">
                                                <i class="fa fa-check-square fa-2x" style="color:#999">6 Fait</i>                                              
                                            </div>
                                            <div class="span6">
                                                <i class="fa fa-th-list fa-2x" style="color:#999">15 Totale</i>                                              
                                            </div>
                                        </div>
                                    </div>
                                </span>
                                <span class="span3" id="butt" style="float: right;display: none;">
                                    <input type="submit" class="btn btn-large btn-block" value='Enregistrer les modifications'/>
                                </span>
                            </div>
                            <!-- /block -->
                        </div>
                        <div class="span12" style="margin-left: 0px;margin-right: 10px">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">Taches du  ${ tableau.nomTableau }</div>
                                    <div class="pull-right"><span class="badge badge-info">${ tableau.nbrTaches }</span>
                                    </div>
                                </div>
                                <div class="block-content collapse in" >
                                    <div class="span12">
                                       <div class="table-toolbar">
                                          <div class="btn-group">
                                             <a href="#ajouterTache" data-toggle="modal">
                                             	<button class="btn btn-success">Ajouter nouvelle tache<i class="icon-plus icon-white"></i></button>
                                             </a>
                                          </div>
                                          
                                       </div>
                                        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example2">
                                            <thead>
                                                <tr>
                                                    <th width="20%">Titre</th>
                                                    <th width="40%">Temps restant</th>
                                                    <th width="14%">Date Debut </th>
                                                    <th width="14">Date fin</th>
                                                    <th width="12%"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${ taches }" var="tache" varStatus="status">
                                                <tr class="odd gradeX">
                                                    <td width="">
                                                        <a onclick="tggelDropDown('1');"><h5><i id="iconAngle1" class="fa fa-angle-double-right" aria-hidden="true"></i> ${ tache.nomTache }</h5></a>
                                                        <div id="uniform1" style="display: none;">
                                                            <label id="" class="uniform" style="padding-left: 15px;">
                                                                <input class="uniform_on" type="checkbox" id="optionsCheckbox" value="option1" disabled>
                                                                    Option one is this and that&mdash;be sure to include why it's great
                                                            </label>
                                                            <label id="" class="uniform" style="padding-left: 15px;">
                                                                <input class="uniform_on" type="checkbox" id="optionsCheckbox" value="option1" checked disabled>
                                                                    Option one is this and that&mdash;be sure to include why it's great
                                                            </label>
                                                        </div>
                                                        <div id="racc">
                                                        <c:if test="${ !empty tache.descriptionTache }">
                                                            <div class="span2" style="">
                                                                <i class="fa fa-align-justify" aria-hidden="true" style="color:#999" ></i>
                                                            </div>
                                                        </c:if>
                                                            <div class="span2" >
                                                                <i class="fa fa-list" aria-hidden="true" style="color:#999"></i>
                                                            </div>
                                                            <div class="span3" >
                                                                <span ><i class="fa fa-tags" aria-hidden="true" style="color:#999"></i>${ tache.nbrMarques }</span>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td class="center"> 
                                                        <div class="progress progress-striped active" style="height: 35px;margin-top: 10px;color: #999999">
                                                            <div style="width: ${tache.pourcentage}%;" class="bar"><h4>${tache.pourcentage}%</h4></div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <h4>${ tache.dateDeCreationDeTache }</h4>
                                                    </td>
                                                    <td><h4>${ tache.dateLimiteDeTache }</h4></td>
                                                    
                                                    <td class="center">
                                                        <a href="#myModal" data-toggle="modal" ><button class="btn" style="width: 100%"><i class="icon-eye-open"></i> Voir</button></a>
                                                        <a href="tacheSuppr?id=${ tache.idTache }&tableauID=${tableau.idTableau}" class="btn btn-danger" style="width: 84%"><i class="icon-remove icon-white"></i> Supprimer</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- /block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">Listes des commits</div>
                                    <div class="pull-right"><span class="badge badge-info">${ tableau.nbrCommits }</span></div>
                                </div>
                                <div class="block-content collapse in">
                                    <div class="span12">
                                        
                                        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                            <thead>
                                                <tr>
                                                    <th>Commit</th>
                                                    <th>Date</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach items="${ commits }" var="commit">
                                            	<tr class="odd gradeX">
                                                    <td>${ commit.textCommit }</td>
                                                    <td>${ commit.dateCommit }</td>
                                                </tr>
                                            	</c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="span12">
                            <div id="myModal" class="modal hide block" style="width: 60%;margin-left: unset;left: 20%;">
                            	<form action="tache" method="post">
	                                <div class="modal-header">
	                                    <button data-dismiss="modal" class="close" type="button">&times;</button>
	                                    <h3>Ajouter une nouvelle tache</h3>
	                                </div>
	                                <div class="modal-body block-content" style="max-height: 580px;">
	                                    <div class="span12" >
	                                        <span class="span8" >
	                                            <div class="span7">
	                                                <h5><i class="fa fa-tasks" aria-hidden="true" style="color:#999" ></i> Titre</h5>
	                                                <input name="titre" style="width: 100%;" type="text" class="input-xlarge "  placeholder="Titre de la tache">
	                                            </div>
	                                            <div class="span5">
	                                                <h5><i class="fa fa-thermometer-full" aria-hidden="true" style="color:#999" ></i> Priorité</h5>
	                                                <select name="priorite" id="priorite" style="width: 100%;"  class="input-xlarge "  title="priorite">
	                                                    <option value="3"> Haute</option>
	                                                    <option value="2"> Normale</option>
	                                                    <option value="1"> Basse</option>
	                                                </select>
	                                            </div>
	                                            <h5><i class="fa fa-align-justify" aria-hidden="true" style="color:#999" ></i> Description</h5>
	                                            
	                                            </p>
	                                            <textarea name="desc" placeholder="Description de la tache" style="width: 100%; height: 120px;" ></textarea>
	                                        </span>
	                                        <span class="span4" style="float: right;">
	                                            <div class="span6">
	                                                <h5><i class="icon-time" aria-hidden="true" style="color:#999" ></i> Date Debut</h5>
	                                                <input name="dateDebut" style="width: 78%;" type="date" class="input-xlarge datepicker" id="date01">
	                                            </div>
	                                            <div class="span6">
	                                                <h5><i class="icon-time" aria-hidden="true" style="color:#999" ></i> Date Fin</h5>
	                                                <input name="dateFin" style="width: 78%;" type="date" class="input-xlarge datepicker" id="date01">
	                                            </div>
	                                        </span>
	                                    </div>
	                                    
	                                    
	                                    <div style="margin-left:0" class="span12">
	                                        <div class="span6">
	                                            <h5><i class="fa fa-list" aria-hidden="true" style="color:#999" ></i> Ckecklists</h5>
	                                            <div id="ajouterCheckListes">
	                                            </div>
	                                            <br>
	                                            <div  id="checklistInput">
	                                                <input type="text" id="textCheckliste"  class="input-xlarge"  placeholder="Entrer votre checklist">
	                                                <button type="button" class="btn btn-primary" onclick="ajouterCheckList();">Ajouter checkliste</button>   
	                                            </div>
	                                        </div>
	                                        <div class="span6">
	                                            <h5><i class="fa fa-tags" aria-hidden="true" style="color:#999" ></i> Marques</h5>
	                                            <div id="tags">
	                                                <label id="" class="uniform" style="padding-left: 15px;">
	                                                    <input class="uniform_on" type="text" id="optionsCheckbox" name="marques"
	                                                    placeholder="entrez vos marques séparées par des virgules" style="width: 95%">
	                                                </label>
	                                            </div>
	                                            <br>
	                                        </div>
	                                    </div>
	                                    
	                                    
	                                    <span class="span4" id="but" style="float: right;">
	                                    	<input type="hidden" name="tableauID" value="${ tableau.idTableau }">
	                                        <input type="submit" class="btn btn-large btn-block" value='Enregister les modifications'/>                      
	                                    </span>
			                            <br>
			                            <div style="margin-left:0" class="span12">
			                            
			                            <h5><i class="fa fa-list" aria-hidden="true" style="color:#999" ></i> Commits</h5>
		                                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
		                                    <thead>
		                                        <tr>
		                                            <th>Commit</th>
		                                            <th>Date</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <tr class="odd gradeX">
		                                            <td>Creation du projet ${ tableau.nomTableau }</td>
		                                            <td>18/01/2018</td>
		                                        </tr>
		                                         <tr class="odd gradeX">
		                                            <td>Creation du projet Projet S5</td>
		                                            <td>18/01/2018</td>
		                                        </tr>
		                                    </tbody>
		                                </table>
			                            
			                            </div>
	                                </div>
                                </form>
                            </div>
                            
                            <!-- Debut d'ajout de tache -->
                            <div id="ajouterTache" class="modal hide block" style="width: 60%;margin-left: unset;left: 20%;">
                            	<form action="tache" method="post">
	                                <div class="modal-header">
	                                    <button data-dismiss="modal" class="close" type="button">&times;</button>
	                                    <h3>Ajouter une nouvelle tache</h3>
	                                </div>
	                                <div class="modal-body block-content" style="max-height: 580px;">
	                                    <div class="span12" >
	                                        <span class="span8" >
	                                            <div class="span7">
	                                                <h5><i class="fa fa-tasks" aria-hidden="true" style="color:#999" ></i> Titre</h5>
	                                                <input name="titre" style="width: 100%;" type="text" class="input-xlarge "  placeholder="Titre de la tache">
	                                            </div>
	                                            <div class="span5">
	                                                <h5><i class="fa fa-thermometer-full" aria-hidden="true" style="color:#999" ></i> Priorité</h5>
	                                                <select name="priorite" id="priorite" style="width: 100%;"  class="input-xlarge "  title="priorite">
	                                                    <option value="3"> Haute</option>
	                                                    <option value="2"> Normale</option>
	                                                    <option value="1"> Basse</option>
	                                                </select>
	                                            </div>
	                                            <h5><i class="fa fa-align-justify" aria-hidden="true" style="color:#999" ></i> Description</h5>
	                                            
	                                            </p>
	                                            <textarea name="desc" placeholder="Description de la tache" style="width: 100%; height: 120px;" ></textarea>
	                                        </span>
	                                        <span class="span4" style="float: right;">
	                                            <div class="span6">
	                                                <h5><i class="icon-time" aria-hidden="true" style="color:#999" ></i> Date Debut</h5>
	                                                <input name="dateDebut" style="width: 78%;" type="date" class="input-xlarge datepicker" id="date01">
	                                            </div>
	                                            <div class="span6">
	                                                <h5><i class="icon-time" aria-hidden="true" style="color:#999" ></i> Date Fin</h5>
	                                                <input name="dateFin" style="width: 78%;" type="date" class="input-xlarge datepicker" id="date01">
	                                            </div>
	                                        </span>
	                                    </div>
	                                    
	                                    
	                                    <div style="margin-left:0" class="span12">
	                                        <div class="span6">
	                                            <h5><i class="fa fa-list" aria-hidden="true" style="color:#999" ></i> Ckecklists</h5>
	                                            <div id="ajouterCheckListes">
	                                            </div>
	                                            <br>
	                                            <div  id="checklistInput">
	                                                <input type="text" id="textCheckliste"  class="input-xlarge"  placeholder="Entrer votre checklist">
	                                                <button type="button" class="btn btn-primary" onclick="ajouterCheckList();">Ajouter checkliste</button>   
	                                            </div>
	                                        </div>
	                                        <div class="span6">
	                                            <h5><i class="fa fa-tags" aria-hidden="true" style="color:#999" ></i> Marques</h5>
	                                            <div id="tags">
	                                                <label id="" class="uniform" style="padding-left: 15px;">
	                                                    <input class="uniform_on" type="text" id="optionsCheckbox" name="marques"
	                                                    placeholder="entrez vos marques séparées par des virgules" style="width: 95%">
	                                                </label>
	                                            </div>
	                                            <br>
	                                        </div>
	                                    </div>
	                                    
	                                    
	                                    <span class="span4" id="but" style="float: right;">
	                                    	<input type="hidden" name="tableauID" value="${ tableau.idTableau }">
	                                        <input type="submit" class="btn btn-large btn-block" value='Ajouter tâche'/>                      
	                                    </span>
	                                </div>
                                </form>
                            </div>
                            <!-- Fin d'ajout de tache -->
                            
                        </div>
                    </div>
			    </div>
			</div>
        </div>
                
        <script src="vendors/jquery-1.9.1.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="vendors/datatables/js/jquery.dataTables.min.js"></script>


        <script src="assets/scripts.js"></script>
        <script src="assets/DT_bootstrap.js"></script>
        <script>
        function reply_click(clicked_id){
        	var index = clicked_id.lastIndexOf("_");
            var listeID = clicked_id.substr(index+1);
            document.getElementsByTagName("XXXtitreXXX").innerHTML = "Test";
            // As pointed out in comments, 
            // it is superfluous to have to manually call the modal.
            // $('#addBookDialog').modal('show');
        }
        
        function ajouterCheckList(){
        	var text = document.getElementById('textCheckliste').value;
        	document.getElementById('ajouterCheckListes').innerHTML += "<input type='checkbox' style='padding-left: 15px;' name='checkListes' checked >" + text + "<br>";
        	document.getElementById('textCheckliste').value = "";
        }
        
        </script>
        