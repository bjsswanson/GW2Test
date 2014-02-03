var GWTools = {} || window.GWTools;

GWTools.session = "";

GWTools.trading = {
	init: function(){

	},
	getListing: function(){
		$.ajax("/listing", {

		})
	}
}

GWTools.events = {
	init: function(){
		this.sessionFormEvent();
		this.hideSessionSaved();
	},
	sessionFormEvent: function(){
		$('#sessionForm').submit(function(event){
			event.preventDefault();

			var session = $('#sessionInput').val();
			if(session != undefined && session.length > 0);{
				$('#sessionText').html(session);
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
	}
};

$(document).ready(function(){
	GWTools.events.init();
});


