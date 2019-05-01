(function($) {
	$(function() {

		$('.sidenav').sidenav();
		$('.parallax').parallax();
		$(".dropdown-trigger").dropdown({
			coverTrigger : false
		});
		$('select').formSelect();
	}); // end of document ready
})(jQuery); // end of jQuery name space
