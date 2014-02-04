var GWTools = {} || window.GWTools;

GWTools.session = "";

GWTools.events = {
	init: function(){
		this.sessionFormEvent();
		this.hideSessionSaved();
        this.buyFormEvent();
	},
	sessionFormEvent: function(){
        $('#sessionForm').submit(function(event){
            event.preventDefault();

            var session = $('#sessionInput').val();
            if(session != undefined && session.length > 0);{
                $('#sessionText').html(session);
                $('#session').prop("value", session);
                GWTools.session = session;
                GWTools.events.swapSessionForm();
            }
        });
    },
	swapSessionForm: function(){
		var sessionForm = $('#sessionForm');
		var sessionSaved = $('#sessionSaved');
		sessionForm.toggle();
		sessionSaved.toggle();
	},
	hideSessionSaved: function(){
		$('#sessionSaved').hide();
	},
    buyFormEvent: function(){
        $('#buyForm').submit(function(event){
            event.preventDefault();
            window.open("/buy?" + $('#buyForm').serialize());
        });
    }
};

$(document).ready(function(){
	GWTools.events.init();
});


