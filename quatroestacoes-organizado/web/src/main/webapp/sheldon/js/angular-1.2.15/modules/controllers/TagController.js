function TagCtrl($scope, $http, $modal, $log, $window, $dialogs, TagService) {

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
        TagService.findByFilter($scope.filter, function(response) {
            $scope.tags = response.data;
            $scope.filter.count = response.count;
        });
    };

    $scope.confirmDelete = function(tag) {
        dlg = $dialogs.confirm('Please Confirm',
                'Você realmente deseja excluir o tag ' + tag.nome + '?');
        dlg.result.then(function(btn) {
            // OK
            TagService.remove({
                    id : tag.id
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
            templateUrl : 'modalTag',
            controller : TagModalController,
            resolve : {
                tag : function() {
                    return {};
                },
                isEditing : function() {
                    return $scope.isEditing;
                }
            }
        });

        modalInstance.result.then(function(tag) {
            // refresh current data
            $scope.newTag = tag;
            $scope.findByFilter();
        }, function() {
            $log.info('Modal dismissed at: ' + new Date());
        });
    }

    $scope.edit = function(tag) {
        $scope.reset();

        $scope.isEditing = true;

        TagService.findById({
            id : tag.id
        }, function(response) {
            $scope.newTag = response;

            // abrir modal
            var modalInstance = $modal.open({
                templateUrl : 'modalTag',
                controller : TagModalController,
                resolve : {
                    tag : function() {
                        return $scope.newTag;
                    },
                    isEditing : function() {
                        return $scope.isEditing;
                    }
                }
            });

            modalInstance.result.then(function(tag) {
                // refresh current data
                $scope.newTag = tag;
                $scope.findByFilter();
            }, function() {
                $log.info('Modal dismissed at: ' + new Date());
            });

        });
    };

    // Define a reset function, that clears the prototype newTag object, and
    // consequently, the form
    $scope.reset = function() {
        // clear input fields
        $scope.newTag = {};
        $scope.isEditing = false;
        // clear errors fields
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};
    };

    // Initialize newTag here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.reset();

    $scope.orderBy = "id";

};

// MODAL
var TagModalController = function($scope, $modalInstance, $log, tag,
                                    isEditing, TagService) {

    $scope.tag = tag;
    $scope.isEditing = isEditing;

    $scope.ok = function() {
        $modalInstance.close($scope.tag);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };

    $scope.save = function() {

        if ($scope.isEditing == false) {
            TagService.save($scope.tag, function(data, headers) {
                $scope.ok();
                var unique_id = $.gritter.add({
                    // (string | mandatory) the heading of the
                    // notification
                    title : 'success',
                    // (string | mandatory) the text inside the
                    // notification
                    text : 'Regra ' + $scope.tag.nome
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
            TagService.update($scope.tag, function(data, headers) {
                $scope.ok();

                var unique_id = $.gritter.add({
                    // (string | mandatory) the heading of the
                    // notification
                    title : 'success',
                    // (string | mandatory) the text inside the
                    // notification
                    text : 'Video ' + $scope.tag.nome
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
};