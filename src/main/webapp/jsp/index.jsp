<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

    <title>Guild Wars 2 Tools</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Guild Wars 2 Tools</a>
        </div>
        <div class="navbar-collapse collapse">
            <form id="sessionSaved" class="navbar-form navbar-right" role="form">
                <div class="form-group">
                    <p id="sessionText" class="form-control"></p>
                </div>
                <button type="submit" class="btn btn-success">Change</button>
            </form>

            <form id="sessionForm" class="navbar-form navbar-right" role="form">
                <div class="form-group">
                    <input id="sessionInput" type="text" placeholder="Session" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
    </div>
</div>

<div class="container">

    <div class="starter-template">

    </div>

    <div class="row">
        <div class="col-md-6">

            <form id="buyForm" class="form-horizontal" role="form" />

                <input id="session" name="session" type="hidden"/>

                <div class="form-group">
                    <div class="col-md-4">
                        <label for="coins" class="control-label">Spend Amount:</label>
                        <input id="coins" name="coins" type="number"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label for="type" class="control-label">Type:</label>
                        <select id="type" name="type" class="form-control">
                            <option value="0">Armor</option>
                            <option value="17">Upgrade Component</option>
                            <option value="18">Weapon</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label for="rarity" class="control-label">Rarity:</label>
                        <select id="rarity" name="rarity" class="form-control">
                            <option value="3">Masterwork</option>
                            <option value="4">Rare</option>
                            <option value="5">Exotic</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label for="min_level" class="control-label">Min Level:</label>
                        <input id="min_level" name="min_level" type="number" value="15"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label for="max_level" class="control-label">Max Level:</label>
                        <input id="max_level" name="max_level" type="number" value="20"/>
                    </div>
                </div>
                <div class="form-group">
                        <div class="col-md-4">
                            <label for="min_price" class="control-label">Min Price:</label>
                            <input id="min_price" name="min_price" type="number" value="1000"/>
                        </div>
                    </div>
                <div class="form-group">
                        <div class="col-md-4">
                            <label for="max_price" class="control-label">Max Price:</label>
                            <input id="max_price" name="max_price" type="number" value="20000"/>
                        </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label for="add_price" class="control-label">Add Price:</label>
                        <input id="add_price" name="add_price" type="number" value="20" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label for="count" class="control-label">Count:</label>
                        <input id="count" name="count" type="number" value="3"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label for="profit_margin" class="control-label">Profit Margin: </label>
                        <input id="profit_margin" name="profit_margin" type="number" value="2"/>
                    </div>
                </div>
                <button id="buySubmit" type="submit" class="btn btn-success">Buy</button>
            </form>

        </div>
        <div class="col-md-6">.col-md-6</div>
    </div>

</div><!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery-1.10.2.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/custom.js"></script>
</body>
</html>
