app.controller("attendanceController", function ($scope, $http) {
    $scope.test = "my";
    $scope.studentsByClass;
    $scope.cls;
    $scope.student = {};
    $scope.attendances = [];
    $scope.totalStudent;
    $scope.result;

    function createAttendaceList() {
        $scope.attendances = [];
        for (var i = 0; i < $scope.studentsByClass.length; i++) {
            var student = $scope.studentsByClass[i];
            var attendance = {};
            // console.log("Student Id: "+$scope.studentsByClass[i].studentId);
            //attendance.assign(student);
            // attendance.assign(cls);
            // attendance.studentId = $scope.studentsByClass[i].studentId;
            //attendance.studentName = $scope.studentsByClass[i].studentName;
            attendance.student = {};
            attendance.student.studentId = $scope.studentsByClass[i].studentId;
            attendance.student.studentName = $scope.studentsByClass[i].studentName;
            attendance.roll = $scope.studentsByClass[i].roll;
            attendance.studentClass = {};
            attendance.studentClass.classId = $scope.cls.classId;
            //attendance.classId = $scope.cls.classId;
            attendance.attendanceStatus = true;
            // console.log(student.studentName);
            // console.log(attendance);
            $scope.attendances.push(attendance);

        }
        console.log($scope.attendances);

    }
    ;
    $scope.selectedClassChanged = function () {
        console.log("Class Changed...");
        $http({
            method: 'GET',
            url: 'attendance/students/' + $scope.cls.classId
        }).then(function successCallback(response) {
            $scope.studentsByClass = response.data;
            $scope.totalStudent = $scope.studentsByClass.length;
            createAttendaceList();
            console.log($scope.studentsByClass);
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    };

    $scope.saveAttendances = function () {
        console.log("saveAttendances called...");
        console.log(angular.toJson($scope.attendances));
        $http({
            method: "POST",
            url: 'attendance/att',
            data: angular.toJson($scope.attendances),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(success, error);
    };

    $scope.attendanceStatus = function (status) {
        if (status == true) {
            return "Present";
        } else {
            return "Absent";
        }

    };

    function success(response) {
        $scope.message = "Success! " + response.data;
        $("#myMessage").show();
        $("#myMessage").removeClass("alert-danger");
        $("#myMessage").addClass("alert-danger");
        setTimeout(clearMassage, 2500);
        console.log("jQuery...");
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

});