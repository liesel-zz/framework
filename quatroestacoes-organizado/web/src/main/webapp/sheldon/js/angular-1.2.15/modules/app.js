'use strict';

/* App Module */
var app = angular.module('app', [ 'ui.bootstrap', 'dialogs', 'ui.utils',
		'ngRoute', 'UserService', 'PlanService', 'RoleService', 'VideoService', 'TagService'  ,'ng',
		'ui.select2' ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/security/user', {
		templateUrl : 'modules/user.html',
		controller : UserCtrl
		
	}).when('/security/role', {
		templateUrl : 'modules/role.html',
		controller : RoleCtrl

    }).when('/video', {
        templateUrl : 'modules/video.html',
        controller : VideoCtrl

    }).when('/tag', {
        templateUrl : 'modules/tag.html',
        controller : TagCtrl

	}).when('/dashboard', {
		templateUrl : 'modules/dashboard.html',
		controller : DashCtrl

	}).otherwise({
		redirectTo : '/dashboard'
	});
} ]);

app.filter('localtime', function() {
	return function(d) {
		if (d) {
			var mDate = moment(d);
			return mDate.format('DD/MM/YYYY');
		} else {
			return null;
		}

	}
});
