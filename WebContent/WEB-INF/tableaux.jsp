<!DOCTYPE html>
<html>
    
    <head>
        <title>Vos Projets</title>
        <!-- Bootstrap -->
        <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="./bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="./assets/styles.css" rel="stylesheet" media="screen">
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="./vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    
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
                                <a href="#">Mes Taches</a>
                            </li>
                            <li>
                                <a href="#">Mes Marques</a>
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
                        <div class="alert alert-success">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <h4>Success</h4>
                            The operation completed successfully
                        </div>
                        <div class="navbar">
                            <div class="navbar-inner">
                                <ul class="breadcrumb">
                                    <li>
                                        <a href="#">Profil</a> <span class="divider">/</span>    
                                    </li>
                                    <li class="active">Projets</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">

                    <c:forEach items="${ tableaux }" var="tableau">
	                    <!-- block -->
	                       <div class="span3" style="margin-left: 0px;margin-right: 10px">
	                            <div class="block">
	                                <div class="navbar navbar-inner block-header">
	                                    <div class="muted pull-left">${ tableau.nomTableau }</div>
	                                    <div class="pull-right"><span class="badge badge-info">${ tableau.nbrTaches }</span>
	
	                                    </div>
	                                </div>
	                                <div class="block-content collapse in" >
	                                <c:if test="${ !empty tableau.descriptionTableau }">
	                                    <div class="span2" style="">
	                                        <i class="fa fa-align-justify" aria-hidden="true" style="color:#999" ></i>
	                                    </div>
	                                </c:if>
	                                    <div class="span2" >
	                                        <i class="fa fa-list" aria-hidden="true" style="color:#999"></i>
	                                    </div>
	                                    <div class="span3" >
	                                        <span ><i class="fa fa-tags" aria-hidden="true" style="color:#999"></i>&nbsp;${ tableau.nbrTags }</span>
	                                    </div>
	                                    <div class="span3" >
	                                        <span ><i class="fa fa-share-alt" aria-hidden="true" style="color:#999"></i>&nbsp;${ tableau.nbrCommits }</span>
	                                    </div>
	                                    <p style="float: right;margin-top: 5px">
	                                        <a href="tableau?id=${ tableau.idTableau }"><button class="btn" ><i class="icon-eye-open"></i>Voir</button></a>
	                                        <a href="tableauSuppr?id=${ tableau.idTableau }" class="btn btn-danger" ><i class="icon-remove icon-white"></i> Supprimer</a>
	                                    </p>
	                                </div>
	                            </div>
	                        </div>
	                    <!-- /block -->
                    </c:forEach>

                        <!-- Ajouter nouveau projet -->
                        <div class="span3" style="margin-left: 0px;margin-right: 10px">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header" style="height: 3px ;min-height: 5px;">
                                   
                                </div>
                                <div class="block-content collapse in" style="height: 3px ;min-height: 5px;">
                                    
                                    
                                </div>
                                <div class="navbar navbar-inner block-header" style="height: 45px ;min-height: 102px;">
                                    <a  href="#ajoutModal" data-toggle="modal"><button class="btn btn-success " style="margin-top: 30px;font-size: 22px">Ajouter un nouveau projet</button></a>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
                        <div class="span12">
                            <div id="ajoutModal" class="modal hide block" style="width: 60%;margin-left: unset;left: 20%;">
                            <form id="ajouterTableau" action="tableaux" method="post">
                                <div class="modal-header">
                                    <button data-dismiss="modal" class="close" type="button">&times;</button>
                                    <h3>Ajouter un nouoveau projet</h3>
                                </div>
                                <div class="modal-body block-content" style="max-height: 580px;">
                                    <div class="span12" >
                                        <span class="span8" >
                                            <h5><i class="fa fa-tasks" aria-hidden="true" style="color:#999" ></i> Titre</h5>
                                            
                                            <input id="titre" style="width: auto;" type="text" class="input-xlarge"
                                            placeholder="Entrer le nom du projet"  name="nom">
                                            <h5><i class="fa fa-align-justify" aria-hidden="true" style="color:#999" ></i> Description</h5>
                                           
                                            <textarea id="textAreaTask" class="textarea"  name="desc" placeholder="Entrer la description du projet" style="width: 100%; height: 160px;" ></textarea>
                                        </span>
                                        
                                    </div>
                                    <span class="span4" id="but" style="float: right;">
                                        <input id="submitForm" type="submit" class="btn btn-large btn-block"  value='Enregistrer'/>
                                    </span>
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
			    </div>
			</div>
            
        </div>
        <!--/.fluid-container-->
        <link href="./vendors/datepicker.css" rel="stylesheet" media="screen">
        <link href="./vendors/uniform.default.css" rel="stylesheet" media="screen">
        <link href="./vendors/chosen.min.css" rel="stylesheet" media="screen">

        <link href="./vendors/wysiwyg/bootstrap-wysihtml5.css" rel="stylesheet" media="screen">

        <script src="./vendors/jquery-1.9.1.js"></script>
        <script src="./bootstrap/js/bootstrap.min.js"></script>
        <script src="./vendors/jquery.uniform.min.js"></script>
        <script src="./vendors/chosen.jquery.min.js"></script>
        <script src="./vendors/bootstrap-datepicker.js"></script>

        <script src="./vendors/wysiwyg/wysihtml5-0.3.0.js"></script>
        <script src="./vendors/wysiwyg/bootstrap-wysihtml5.js"></script>

        <script src="./vendors/wizard/jquery.bootstrap.wizard.min.js"></script>

	<script type="text/javascript" src="v./endors/jquery-validation/dist/jquery.validate.min.js"></script>
	<script src="./assets/form-validation.js"></script>
        
	<script src="assets/scripts.js"></script>
        <script>

	

        $(function() {
            $(".datepicker").datepicker();
            $(".uniform_on").uniform();
            $(".chzn-select").chosen();
            $('.textarea').wysihtml5();

            $('#rootwizard').bootstrapWizard({onTabShow: function(tab, navigation, index) {
                var $total = navigation.find('li').length;
                var $current = index+1;
                var $percent = ($current/$total) * 100;
                $('#rootwizard').find('.bar').css({width:$percent+'%'});
                // If it's the last tab then hide the last button and show the finish instead
                if($current >= $total) {
                    $('#rootwizard').find('.pager .next').hide();
                    $('#rootwizard').find('.pager .finish').show();
                    $('#rootwizard').find('.pager .finish').removeClass('disabled');
                } else {
                    $('#rootwizard').find('.pager .next').show();
                    $('#rootwizard').find('.pager .finish').hide();
                }
            }});
            $('#rootwizard .finish').click(function() {
                alert('Finished!, Starting over!');
                $('#rootwizard').find("a[href*='tab1']").trigger('click');
            });
        });
        </script>
    </body>

</html>