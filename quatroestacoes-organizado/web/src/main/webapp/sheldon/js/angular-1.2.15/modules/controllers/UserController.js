function UserCtrl($scope, $http, $modal, $log, $window, $dialogs, UserService) {

	// errors
	$scope.successMessages = '';
	$scope.errorMessages = '';
	$scope.errors = {};

	// filter
	$scope.filter = new Object();
	$scope.filter.name = '';
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
		UserService.findByFilter($scope.filter, function(response) {
			$scope.users = response.data;
			$scope.filter.count = response.count;
		});
	};

	$scope.confirmDelete = function(user) {
		dlg = $dialogs.confirm('Please Confirm',
				'Você realmente deseja excluir o usuario ' + user.name + '?');
		dlg.result.then(function(btn) {
			// OK
			UserService.remove({
				id : user.id
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
					text : 'Usuário removido com sucesso.',
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
			templateUrl : 'modalUser',
			controller : UserModalController,
			resolve : {
				user : function() {
					return {};
				},
				isEditing : function() {
					return $scope.isEditing;
				}
			}
		});

		modalInstance.result.then(function(user) {
			// refresh current data
			$scope.newUser = user;
			$scope.findByFilter();
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	}

	$scope.edit = function(user) {
		$scope.reset();

		$scope.isEditing = true;

		UserService.findById({
			id : user.id
		}, function(response) {
			$scope.newUser = response;

			// abrir modal
			var modalInstance = $modal.open({
				templateUrl : 'modalUser',
				controller : UserModalController,
				resolve : {
					user : function() {
						return $scope.newUser;
					},
					isEditing : function() {
						return $scope.isEditing;
					}
				}
			});

			modalInstance.result.then(function(user) {
				// refresh current data
				$scope.newUser = user;
				$scope.findByFilter();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});

		});
	};

	// Define a reset function, that clears the prototype newUser object, and
	// consequently, the form
	$scope.reset = function() {
		// clear input fields
		$scope.newUser = {};
		$scope.isEditing = false;
		// clear errors fields
		$scope.successMessages = '';
		$scope.errorMessages = '';
		$scope.errors = {};
	};

	// Initialize newUser here to prevent Angular from sending a request
	// without a proper Content-Type.
	$scope.reset();

	$scope.orderBy = "id";

};

// MODAL
var UserModalController = function($scope, $modalInstance, $log, user,
		isEditing, UserService) {

	$scope.user = user;
	$scope.isEditing = isEditing;

	$scope.ok = function() {
		$modalInstance.close($scope.user);
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};

	$scope.save = function() {

		if ($scope.isEditing == false) {
			UserService.save($scope.user, function(data, headers) {
				$scope.ok();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the
					// notification
					title : 'success',
					// (string | mandatory) the text inside the
					// notification
					text : 'Usuário ' + $scope.user.name
							+ ' salvo com sucesso.',
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
			UserService.update($scope.user, function(data, headers) {
				$scope.ok();

				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the
					// notification
					title : 'success',
					// (string | mandatory) the text inside the
					// notification
					text : 'Usuário ' + $scope.user.name
							+ ' alterado com sucesso.',
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

	// ZOOM ROLES
	$scope.rolesFKOpt = {
		placeholder : "Selecione as permissôes do usuário",
		minimumInputLength : 1,
		allowClear : true,
		multiple : true,
		ajax : {
			url : "/Sprimas/webservices/rest/role/findByFilter",
			dataType : 'json',
			quietMillis : 200,
			data : function(term, page) { // page is the one-based page number
				// tracked by Select2
				return {
					role : term, // search term
					count : 0, // page size
					startRow : page, // page number
					pageSize : 10
				};
			},
			results : function(data, page) {
				var more = (page * 10) < data.count; // whether or not there
				// are more results
				// available
				// notice we return the value of more so Select2 knows if more
				// results can be loaded
				return {
					results : data.data,
					more : more
				};
			}
		},
		formatResult : function(item) {
			return item.role;
		},
		formatSelection : function(item) {
			return item.role;
		},
		escapeMarkup : function(m) {
			return m;
		}
	};

	// ZOOM PLANOS
	$scope.plansFKOpt = {
		placeholder : "Selecione o plano do usuário",
		minimumInputLength : 1,
		allowClear : true,
		multiple : false,
		ajax : {
			url : "/Sprimas/webservices/rest/plan/findByFilter",
			dataType : 'json',
			quietMillis : 200,
			data : function(term, page) { // page is the one-based page number
				// tracked by Select2
				return {
					name : term, // search term
					count : 0, // page size
					startRow : page, // page number
					pageSize : 10
				};
			},
			results : function(data, page) {
				var more = (page * 10) < data.count; // whether or not there
				// are more results
				// available
				// notice we return the value of more so Select2 knows if more
				// results can be loaded
				return {
					results : data.data,
					more : more
				};
			}
		},
		formatResult : function(item) {
			return item.name;
		},
		formatSelection : function(item) {
			return item.name;
		},
		escapeMarkup : function(m) {
			return m;
		}
	};

};