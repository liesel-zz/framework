function VideoCtrl($scope, $http, $modal, $log, $window, $dialogs, VideoService) {

	// errors
	$scope.successMessages = '';
	$scope.errorMessages = '';
	$scope.errors = {};

	// filter
	$scope.filter = new Object();
	$scope.filter.nome = '';
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
		VideoService.findByFilter($scope.filter, function(response) {
			$scope.videos = response.data;
			$scope.filter.count = response.count;
		});
	};

	$scope.confirmDelete = function(video) {
		dlg = $dialogs.confirm('Please Confirm',
				'Você realmente deseja excluir o video ' + video.nome + '?');
		dlg.result.then(function(btn) {
			// OK
			VideoService.remove({
				id : video.id
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
					text : 'Video removido com sucesso.',
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
			templateUrl : 'modalVideo',
			controller : VideoModalController,
			resolve : {
				video : function() {
					return {};
				},
				isEditing : function() {
					return $scope.isEditing;
				}
			}
		});

		modalInstance.result.then(function(video) {
			// refresh current data
			$scope.newVideo = video;
			$scope.findByFilter();
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	}

	$scope.edit = function(video) {
		$scope.reset();

		$scope.isEditing = true;

		VideoService.findById({
			id : video.id
		}, function(response) {
			$scope.newVideo = response;

			// abrir modal
			var modalInstance = $modal.open({
				templateUrl : 'modalVideo',
				controller : VideoModalController,
				resolve : {
					video : function() {
						return $scope.newVideo;
					},
					isEditing : function() {
						return $scope.isEditing;
					}
				}
			});

			modalInstance.result.then(function(video) {
				// refresh current data
				$scope.newVideo = video;
				$scope.findByFilter();
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});

		});
	};

	// Define a reset function, that clears the prototype newVideo object, and
	// consequently, the form
	$scope.reset = function() {
		// clear input fields
		$scope.newVideo = {};
		$scope.isEditing = false;
		// clear errors fields
		$scope.successMessages = '';
		$scope.errorMessages = '';
		$scope.errors = {};
	};

	// Initialize newVideo here to prevent Angular from sending a request
	// without a proper Content-Type.
	$scope.reset();

	$scope.orderBy = "id";

};

// MODAL
var VideoModalController = function($scope, $modalInstance, $log, video,
		isEditing, VideoService) {

	$scope.video = video;
	$scope.isEditing = isEditing;

	$scope.ok = function() {
		$modalInstance.close($scope.video);
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};

	$scope.save = function() {

		if ($scope.isEditing == false) {
			VideoService.save($scope.video, function(data, headers) {
				$scope.ok();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the
					// notification
					title : 'success',
					// (string | mandatory) the text inside the
					// notification
					text : 'Regra ' + $scope.video.nome
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
			VideoService.update($scope.video, function(data, headers) {
				$scope.ok();

				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the
					// notification
					title : 'success',
					// (string | mandatory) the text inside the
					// notification
					text : 'Video ' + $scope.video.nome
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

    // ZOOM TAGS
    $scope.tagsFKOpt = {
        placeholder : "Selecione as permissôes do usuário",
        minimumInputLength : 1,
        allowClear : true,
        multiple : true,
        ajax : {
            url : "/Sprimas/webservices/rest/tag/findByFilter",
            dataType : 'json',
            quietMillis : 200,
            data : function(term, page) { // page is the one-based page number
                // tracked by Select2
                return {
                    nome : term, // search term
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
            return item.nome;
        },
        formatSelection : function(item) {
            return item.nome;
        },
        escapeMarkup : function(m) {
            return m;
        }
    };

};