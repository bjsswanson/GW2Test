var GWTools = {} || window.GWTools;

GWTools.session = "";

GWTools.events = {
	init: function(){
		this.sessionFormEvent();
		this.hideSessionSaved();
        this.buttonEvents();
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
    buttonEvents: function(){
        $('#buyForm').submit(function(event){
            event.preventDefault();
            window.open("/buy?" + $('#buyForm').serialize());
        });

        $('#totalBuyPriceForm').submit(function(event){
            event.preventDefault();
            window.open("/totalBuyPrice?session=" + GWTools.session);
        });

        $('#cancelBuyForm').submit(function(event){
            event.preventDefault();
            window.open("/cancelBuy?session=" + GWTools.session);
        });

        $('#cancelSellForm').submit(function(event){
            event.preventDefault();
            window.open("/cancelSell?session=" + GWTools.session);
        });

        $('#repostBuyForm').submit(function(event){
            event.preventDefault();
            window.open("/repostBuy?session=" + GWTools.session + "&add_price=" + $("#repost_add_price").val());
        });

        $('#totalSellPriceForm').submit(function(event){
            event.preventDefault();
            window.open("/totalSellPrice?session=" + GWTools.session);
        });

        $('#buyMasterworkArmorForm').submit(function(event){
            event.preventDefault();
            window.open("/buy?session=" + GWTools.session + "&coins=" + $("#saved_coins").val() + "&type=0&rarity=3&min_level=15&max_level=60&min_price=1000&max_price=20000&add_price=20&count=5&profit_margin=2");
        });

        $('#buyMasterworkWeaponForm').submit(function(event){
            event.preventDefault();
            window.open("/buy?session=" + GWTools.session +"&coins=" + $("#saved_coins").val() + "&type=18&rarity=3&min_level=15&max_level=60&min_price=1000&max_price=20000&add_price=20&count=5&profit_margin=2");
        });

        $('#buyRareArmorForm').submit(function(event){
            event.preventDefault();
            window.open("/buy?session=" + GWTools.session + "&coins=" + $("#saved_coins").val() + "&type=0&rarity=4&min_level=50&max_level=60&min_price=1000&max_price=20000&add_price=20&count=1&profit_margin=2");
        });

        $('#buyRareWeaponForm').submit(function(event){
            event.preventDefault();
            window.open("/buy?session=" + GWTools.session +"&coins=" + $("#saved_coins").val() + "&type=18&rarity=4&min_level=50&max_level=60&min_price=1000&max_price=20000&add_price=20&count=1&profit_margin=2");
        });
    }
};

$(document).ready(function(){
	GWTools.events.init();
});


