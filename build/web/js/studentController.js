app.controller("studentController", function ($scope, $http) {
    $scope.test = "hello";

    $scope.message = "";
    $scope.error_message = "";
    $scope.students = [];
    $scope.months = [];
    $scope.classes = [{name: 'myclass'}];
    $scope.countries = [];
    $scope.maxRoll;
    $scope.cls;
    $scope.myselect = true;
    $scope.studentInfo = {studentClass: {}};
    $scope.selectedMonth = {monthName: "select one"};
    $scope.countryForm = {
        id: -1,
        countryName: "",
        population: "",
        myVar: true
    };

    getStudents();
    //getMonth();
    getClasses();

    $scope.sortColumn = '';
    $scope.reverseSort = false;

    $scope.sortData = function (colum) {
        $scope.reverseSort = ($scope.sortColumn == colum) ? !$scope.reverseSort : false;
        $scope.sortColumn = colum;
    }

    $scope.getSortClass = function (colum) {
        if ($scope.sortColumn == colum) {
            return $scope.reverseSort ? 'arrow-down' : 'arrow-up';
        }
        return '';
    }



    $scope.saveStudent = function () {
        console.log("save student called...");
        console.log(angular.toJson($scope.studentInfo));
        $http({
            method: "POST",
            url: 'attendance/students',
            data: angular.toJson($scope.studentInfo),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(success, error);
    };

    $scope.getRoll = function () {
        console.log("Test save student called...");
        $http({
            method: 'GET',
            url: 'attendance/students/maxroll/' + $scope.studentInfo.studentClass.classId
        }).then(function successCallback(response) {
            // $scope.maxRoll = response.data;
            $scope.studentInfo.roll = response.data;
            // console.log($scope.maxRoll);
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    };

    //clicked student
    $scope.clickedStudent = {};
    $scope.selectedStudent = function (product) {
        $scope.clickedStudent = product;
    };

    //update student
    $scope.updateStudent = function () {
        console.log("update student");
        $http({
            method: "PUT",
            url: 'attendance/students',
            data: angular.toJson($scope.clickedStudent),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(success, error);

    };

    //delete student
    $scope.deleteStudent = function () {
        console.log("delete student");
        $http({
            method: "DELETE",
            url: 'attendance/students/' + $scope.clickedStudent.studentId,
            data: angular.toJson($scope.clickedStudent),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(success, error);

    };

    function getStudents() {
        console.log("get student called...");
        $http({
            method: 'GET',
            url: 'attendance/students'
        }).then(function successCallback(response) {
            $scope.students = response.data;
            console.log($scope.students);
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    function getMonth() {
        console.log("get months called...");
        $http({
            method: 'GET',
            url: 'attendance/students/month'
        }).then(function successCallback(response) {
            $scope.months = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }


    function getClasses() {
        console.log("get classes called...");
        $http({
            method: 'GET',
            url: 'attendance/students/classes'
        }).then(function successCallback(response) {
            $scope.classes = response.data;
            console.log($scope.classes);
            console.log("Success");
        }, function errorCallback(response) {
            console.log(response.statusText);
            console.log("Failed");
        });
    }

    function success(response) {
        $scope.message = "Success! " + response.data;
        getStudents();
        clearFormData();
       $("#myMessage").show(1000);
        $("#myMessage").removeClass("alert-danger");
        $("#myMessage").addClass("alert-danger");
        setTimeout(clearMassage, 2500);
    }

    function error(response) {
        $("#myMessage").show(1000);
        $scope.message = "Failed! " + response.data;
        console.log(response.statusText);
        $("#myMessage").removeClass("alert-success");
        $("#myMessage").addClass("alert-danger");
        setTimeout(clearMassage, 2500);
    }

  function clearMassage() {
        $("#myMessage").hide(1000);
        $scope.message = "";
    }
    //Clear the form
    function clearFormData() {
        $scope.countryForm.id = -1;
        $scope.countryForm.countryName = "";
        $scope.countryForm.population = "";

    }
    ;

    $scope.messageClose = function () {
        $scope.message = "";
        $scope.error_message = "";
    };
});