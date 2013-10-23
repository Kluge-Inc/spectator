<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">

		<!-- <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css"> -->
        <r:require modules="bootstrap"/>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
    <div class="container">

        <div class="masthead">
            <h3 class="muted">Project Spectator</h3>
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container">
                        <ul class="nav">
                            <li class="active"><a href="/gr-spectator/">Главная</a></li>
                            <li class="controller"><a href="/gr-spectator/categoryEntity/index">Категории</a></li>
                            <li class="controller"><a href="/gr-spectator/document/index">Документы</a></li>
                            <li class="controller"><a href="/gr-spectator/version/index">Версии</a></li>
                        </ul>
                    </div>
                </div>
            </div><!-- /.navbar -->
        </div>

        <!-- Jumbotron -->
        <div class="jumbotron">
            <h1></h1>
            <p class="lead"><b>Spectator</b> - это централизованное хранилище документов, с версионностю и разбивкой по категориям.</p>
            <a class="btn btn-large btn-success" href="#">Загрузить новый документ</a>
        </div>

        <hr>

        <div class="container content">
            <g:layoutBody/>
            <div class="footer" role="contentinfo"></div>
            <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

        </div>

        <hr>

        <!-- Example row of columns -->
        <div class="row-fluid">
            <div class="span4">
                <h3>Категории</h3>
                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                <p><a class="btn" href="/gr-spectator/categoryEntity/index">Добавить категорию</a></p>
            </div>
            <div class="span4">
                <h3>Документы</h3>
                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                <p><a class="btn" href="/gr-spectator/document/index">Добавить документ</a></p>
            </div>
            <div class="span4">
                <h3>Версии</h3>
                <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa.</p>
                <p><a class="btn" href="/gr-spectator/version/index">Добавить версию</a></p>
            </div>
        </div>

        <hr>

        <div class="footer">
            <p></p>
        </div>

    </div>

    <g:javascript library="application"/>
    <r:layoutResources />
	</body>
</html>
