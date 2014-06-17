function RoleCtrl($scope, $http, $modal, $log, $window, $dialogs, RoleService) {

	// errors
	$scope.successMessages = '';
	$scope.errorMessages = '';
	$scope.errors = {};

	// filter
	$scope.filter = new Object();
	$scope.filter.role = '';
	$scope.filter.count = 0;
	$scope.filter.startRow = 1;
	$scope.filter.pageSize = 5;

	$scope.numPages = function() {
		return Math.ceil($scope.filter.count / $scope.filter.pageSize);
	};

	$scope.$watch('filter.startRow + filter.pageSize', function() {
		$scope.findByFilter();
	});

	$scope.findByFilter = function() {
		RoleService.findByFilter($scope.filter, function(response) {
			$scope.roles = response.data;
			$scope.filter.count = response.count;
		});
	};

	$scope.confirmDelete = function(role) {
		dlg = $dialogs.confirm('Please Confirm',
				'Você realmente deseja excluir a regra ' + role.name + '?');
		dlg.result.then(function(btn) {
			// OK
			RoleService.remove({
				id : role.id
			}, // success
			function(data, headers) {
				// send local message
				$scope.findByFilter();

				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the
					// notification
					title : 'success',
					// (string | mandatory) the text inside the
					// notification
					text : 'Regra removida com sucesso.',
					// (bool | optional) if you want it to fade out
					// on its
					// own or just sit there
					sticky : false,
					// (int | optional) the time you want it to be
					// alive for
					// before fading out
					time : '',
					// (string | optional) the class name you want
					// to apply
					// to that specific message
					class_name : 'gritter-custom'
				});
			},
			// error
			function(response) {

				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the
					// notification
					title : 'error',
					// (string | mandatory) the text inside the
					// notification
					text : response.data.error,
					// (bool | optional) if you want it to fade out
					// on its
					// own or just sit there
					sticky : false,
					// (int | optional) the time you want it to be
					// alive for
					// before fading out
					time : '',
					// (string | optional) the class name you want
					// to apply
					// to that specific message
					class_name : 'gritter-custom'
				});
			});

		}, function(btn) {
			// NOT OK
		});
	};

	$scope.add = function() {
		$scope.reset();
		$scope.isEditing = false;

		// abrir modal
		var modalInstance = $modal.open({
			templateUrl : 'modalRole',
			controller : RoleModalController,
			resolve : {
				role : function() {
					return {};
				},
				isEditing : function() {
					return $scope.isEditing;
				}
			}
		});

		modalInstance.result.then(function(role) {
			// refresh current data
			$scope.newRole = role;
			$scope.findByFilter();
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	}

	$scope.edit = function(role) {
		$scope.reset();

		$scope.isEditing = true;

		RoleService.findById({
			id : role.id
		}, function(response) {
			$scope.newRole = response;

			// abrir modal
			var modalInstance = $modal.open({
				templateUrl : 'modalRole',
				controller : RoleModalController,
				resolve : {
					role : function() {
						return $scope.newRole;
					},
					isEditing : function() {
						return $scope.isEditing;
					}
				}
			});

			modalInstance.result.then(function(role) {
				// refresh current data
				$scope.newRole = role;
				$scope.findByFilter();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});

		});
	};

	// Define a reset function, that clears the prototype newRole object, and
	// consequently, the form
	$scope.reset = function() {
		// clear input fields
		$scope.newRole = {};
		$scope.isEditing = false;
		// clear errors fields
		$scope.successMessages = '';
		$scope.errorMessages = '';
		$scope.errors = {};
	};

	// Initialize newRole here to prevent Angular from sending a request
	// without a proper Content-Type.
	$scope.reset();

	$scope.orderBy = "role";

};

// MODAL
var RoleModalController = function($scope, $modalInstance, $log, role,
		isEditing, RoleService) {

	$scope.role = role;
	$scope.isEditing = isEditing;

	$scope.ok = function() {
		$modalInstance.close($scope.role);
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};

	$scope.save = function() {

		if ($scope.isEditing == false) {
			RoleService.save($scope.role, function(data, headers) {
				$scope.ok();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the
					// notification
					title : 'success',
					// (string | mandatory) the text inside the
					// notification
					text : 'Regra ' + $scope.role.role
							+ ' salva com sucesso.',
					// (bool | optional) if you want it to fade out
					// on its
					// own or just sit there
					sticky : false,
					// (int | optional) the time you want it to be
					// alive for
					// before fading out
					time : '',
					// (string | optional) the class name you want
					// to apply
					// to that specific message
					class_name : 'gritter-custom'
				});

			}, function(response) {
				if ((response.status == 409) || (response.status == 400)) {
					$scope.errors = response.data;
					if (response.data.error) {
						var unique_id = $.gritter.add({
							// (string | mandatory) the heading of the
							// notification
							title : 'error',
							// (string | mandatory) the text inside the
							// notification
							text : response.data.error,
							// (bool | optional) if you want it to fade
							// out on its own or just sit there
							sticky : false,
							// (int | optional) the time you want it to
							// be alive for before fading out
							time : '',
							// (string | optional) the class name you
							// want to apply to that specific message
							class_name : 'gritter-custom'
						});
					}
				} else {
					$scope.errorMessages = [ 'Unknown  server error' ];
				}
			});
		} else {
			RoleService.update($scope.role, function(data, headers) {
				$scope.ok();

				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the
					// notification
					title : 'success',
					// (string | mandatory) the text inside the
					// notification
					text : 'Regra ' + $scope.role.role
							+ ' alterada com sucesso.',
					// (bool | optional) if you want it to fade out
					// on its
					// own or just sit there
					sticky : true,
					// (int | optional) the time you want it to be
					// alive for
					// before fading out
					time : '',
					// (string | optional) the class name you want
					// to apply
					// to that specific message
					class_name : 'gritter-custom'
				});

			}, function(response) {
				if ((response.status == 409) || (response.status == 400)) {
					$scope.errors = response.data;
					if (response.data.error) {
						var unique_id = $.gritter.add({
							// (string | mandatory) the heading of the
							// notification
							title : 'error',
							// (string | mandatory) the text inside the
							// notification
							text : response.data.error,
							// (bool | optional) if you want it to fade
							// out on its own or just sit there
							sticky : false,
							// (int | optional) the time you want it to
							// be alive for before fading out
							time : '',
							// (string | optional) the class name you
							// want to apply to that specific message
							class_name : 'gritter-custom'
						});
					}
				} else {
					$scope.errorMessages = [ 'Unknown  server error' ];
				}
			});
		}
	};

};