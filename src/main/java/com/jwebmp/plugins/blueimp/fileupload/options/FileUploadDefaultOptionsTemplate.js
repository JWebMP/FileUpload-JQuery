JW_APP_NAME.config(
    function ($httpProvider, fileUploadProvider) {
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        fileUploadProvider.defaults.redirect = window.location.href.replace(/\/[^\/]*$/, '');
        angular.extend(fileUploadProvider.defaults,
            FileUploadDefaultOptionsTemplateOptions
        );
    }
);
