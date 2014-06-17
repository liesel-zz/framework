'use strict';

// SECURITY
angular.module('UserService', [ 'ngResource' ]).factory('UserService',
    [ '$resource', function ($resource) {
        return $resource('/Sprimas/webservices/rest/user/:action/:id', {
            id: '@id'
        }, {
            register: {
                method: 'POST',
                params: {
                    action: 'register'
                }
            },
            save: {
                method: 'POST',
                params: {
                    action: 'save'
                }
            },
            lostPassword: {
                method: 'POST',
                params: {
                    action: 'lostPassword'
                }
            },
            resetPassword: {
                method: 'POST',
                params: {
                    action: 'resetPassword'
                }
            },
            update: {
                method: 'PUT',
                params: {
                    action: 'update'
                }
            },
            remove: {
                method: 'DELETE',
                params: {
                    action: 'remove'
                }
            },
            findAll: {
                method: 'GET',
                isArray: true,
                params: {
                    action: 'findAll'
                }
            },
            findLoggedUser: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findLoggedUser'
                }
            },
            findById: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findById'
                }
            },
            findByFilter: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findByFilter'
                }
            }
        });
    } ]);

angular.module('PlanService', [ 'ngResource' ]).factory('PlanService',
    [ '$resource', function ($resource) {
        return $resource('/Sprimas/webservices/rest/plan/:action/:id', {
            id: '@id'
        }, {
            save: {
                method: 'POST',
                params: {
                    action: 'save'
                }
            },
            update: {
                method: 'PUT',
                params: {
                    action: 'update'
                }
            },
            remove: {
                method: 'DELETE',
                params: {
                    action: 'remove'
                }
            },
            findAll: {
                method: 'GET',
                isArray: true,
                params: {
                    action: 'findAll'
                }
            },
            findById: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findById'
                }
            },
            findByFilter: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findByFilter'
                }
            }
        });
    } ]);

angular.module('RoleService', [ 'ngResource' ]).factory('RoleService',
    [ '$resource', function ($resource) {
        return $resource('/Sprimas/webservices/rest/role/:action/:id', {
            id: '@id'
        }, {
            save: {
                method: 'POST',
                params: {
                    action: 'save'
                }
            },
            update: {
                method: 'PUT',
                params: {
                    action: 'update'
                }
            },
            remove: {
                method: 'DELETE',
                params: {
                    action: 'remove'
                }
            },
            findAll: {
                method: 'GET',
                isArray: true,
                params: {
                    action: 'findAll'
                }
            },
            findById: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findById'
                }
            },
            findByFilter: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findByFilter'
                }
            }
        });
    } ]);

angular.module('VideoService', [ 'ngResource' ]).factory('VideoService',
    [ '$resource', function ($resource) {
        return $resource('/Sprimas/webservices/rest/video/:action/:id', {
            id: '@id'
        }, {
            save: {
                method: 'POST',
                params: {
                    action: 'save'
                }
            },
            update: {
                method: 'PUT',
                params: {
                    action: 'update'
                }
            },
            remove: {
                method: 'DELETE',
                params: {
                    action: 'remove'
                }
            },
            findAll: {
                method: 'GET',
                isArray: true,
                params: {
                    action: 'findAll'
                }
            },
            findById: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findById'
                }
            },
            findByFilter: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findByFilter'
                }
            }
        });
    } ]);

angular.module('TagService', [ 'ngResource' ]).factory('TagService',
    [ '$resource', function ($resource) {
        return $resource('/Sprimas/webservices/rest/tag/:action/:id', {
            id: '@id'
        }, {
            save: {
                method: 'POST',
                params: {
                    action: 'save'
                }
            },
            update: {
                method: 'PUT',
                params: {
                    action: 'update'
                }
            },
            remove: {
                method: 'DELETE',
                params: {
                    action: 'remove'
                }
            },
            findAll: {
                method: 'GET',
                isArray: true,
                params: {
                    action: 'findAll'
                }
            },
            findById: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findById'
                }
            },
            findByFilter: {
                method: 'GET',
                isArray: false,
                params: {
                    action: 'findByFilter'
                }
            }
        });
    } ]);

angular.module('CEPService', [ 'ngResource' ]).factory('CEPService',
    [ '$resource', function ($resource) {
        return $resource('http://cep.paicon.com.br/jsonp/:cep', {
            cep: '@cep'
        }, {
            getAddressByCEP: {
                method: 'JSON',
                params: {
                    callback: 'JSON_CALLBACK'
                }
            }
        });
    } ]);
