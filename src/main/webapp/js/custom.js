var GWTools = {} || window.GWTools;

GWTools.session = "";

GWTools.events = {
	init: function(){
		this.sessionFormEvent();
		this.hideSessionSaved();
        this.buyFormEvent();
        this.cancelBuyFormEvent();
        this.cancelSellFormEvent();
        this.repostBuyFormEvent();
        this.totalSellPriceFormEvent();
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
    },
    cancelBuyFormEvent: function(){
        $('#cancelBuyForm').submit(function(event){
            event.preventDefault();
            window.open("/cancelBuy?session=" + GWTools.session);
        });
    },
    cancelSellFormEvent: function(){
        $('#cancelSellForm').submit(function(event){
            event.preventDefault();
            window.open("/cancelSell?session=" + GWTools.session);
        });
    },
    repostBuyFormEvent: function(){
        $('#repostBuyForm').submit(function(event){
            event.preventDefault();
            window.open("/repostBuy?session=" + GWTools.session + "&add_price=" + $("#repost_add_price").val());
        });
    },
    totalSellPriceFormEvent: function(){
        $('#totalSellPriceForm').submit(function(event){
            event.preventDefault();
            window.open("/totalSellPrice?session=" + GWTools.session);
        });
    }
};

$(document).ready(function(){
	GWTools.events.init();
});


