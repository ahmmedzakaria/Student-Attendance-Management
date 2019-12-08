app.controller("attendanceController", function ($scope, $http) {
    $scope.test = "my";
    $scope.studentsByClass;
    $scope.cls;
    $scope.month={monthId:0};
    $scope.months = [];
    $scope.student = {};
    $scope.attendances = [];
    $scope.totalStudent;
    $scope.totalClass;
    $scope.result;
    $scope.attendanceReport = [];
   // $scope.attendanceByStudent=[{attendanceDate : "05-04-18", attendanceStatus: true}];
    $scope.selectedStudent;
    $scope.attendanceIfExis;
    getMonth();
    
//    $scope.$apply(function(){
//        $scope.attendanceByStudent=[{attendanceDate : "05-04-18", attendanceStatus: true}];
//    });
var studentId;
    function createAttendaceList() {
        console.log("createAttendaceList called");
        $scope.attendances = [];
        for (var i = 0; i < $scope.studentsByClass.length; i++) {
            var student = $scope.studentsByClass[i];
            var attendance = {};

            attendance.student = {};
            attendance.student.studentId = $scope.studentsByClass[i].studentId;
            attendance.student.studentName = $scope.studentsByClass[i].studentName;
            attendance.roll = $scope.studentsByClass[i].roll;
            attendance.studentClass = {};
            attendance.studentClass.classId = $scope.cls.classId;
            attendance.ttlPresents = $scope.attendanceReport[i].countPresents;
            attendance.ttlAbsents = $scope.attendanceReport[i].countAbsents;
            //attendance.classId = $scope.cls.classId;
            attendance.attendanceStatus = true;
            // console.log(student.studentName);
            // console.log(attendance);
            $scope.attendances.push(attendance);

        }
        console.log($scope.attendances);

    }
    ;
    function getStudentsByclass() {
        console.log("getStudentsByclass...");
        $http({
            method: 'GET',
            url: 'attendance/students/' + $scope.cls.classId
        }).then(function successCallback(response) {
            $scope.studentsByClass = response.data;
            $scope.totalStudent=$scope.studentsByClass.length;
            console.log("studentsByClass: "+$scope.studentsByClass.length);
            console.log($scope.studentsByClass);
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.classChanged = function () {
        console.log("Class Changed...");
        getStudentsByclass();
        getAttendanceReport();
        //createAttendaceList();

    };
    $scope.monthChanged = function () {
        console.log("Class Changed...");
        //getAttendanceIfExist();
        getStudentsByclass();
        getAttendanceReport();
        
        //createAttendaceList();

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
    
    $scope.updateeAttendances = function () {
        console.log("updateeAttendances called...");
        console.log(angular.toJson($scope.attendances));
        $http({
            method: "PUT",
            url: 'attendance/att',
            data: angular.toJson($scope.attendances),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(success, error);
    };

    function getAttendanceReport() {
        console.log("getAttendanceReport....");
        $http({
            method: 'GET',
            url: 'attendance/att/' + $scope.cls.classId+'/'+$scope.month.monthId
        }).then(function successCallback(response) {
            $scope.attendanceReport = response.data;
            console.log("getAttendanceReport: "+$scope.attendanceReport.length);
            console.log($scope.attendanceReport);
            $scope.totalClass=$scope.attendanceReport[0].countPresents+$scope.attendanceReport[0].countAbsents;
            createAttendaceList();
            //getAttendanceIfExist() ;
            
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    } ;
    
      $scope.$apply(function(){
        $scope.attendanceByStudent=[{attendanceDate : "05-04-18", attendanceStatus: true}];
    });
    function getAttendanceForStudent(studentId) {
        console.log("getAttendanceForStudent for student....");
        console.log("selectStudent.studentId"+studentId);
        $http({
            method: 'GET',
            url: 'attendance/att/student/' + studentId+'/'+$scope.month.monthId
        }).then(function successCallback(response) {
            $scope.attendanceByStudent = response.data;
            console.log("getAttendanceForStudent: "+$scope.attendanceByStudent.length);
            console.log($scope.attendanceByStudent);
            
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    } ;
    
    function getAttendanceIfExist() {
        console.log("getAttendanceIfExist for student....");
        $http({
            method: 'GET',
            url: 'attendance/att/exist/' + $scope.cls.classId
        }).then(function successCallback(response) {
            $scope.attendanceIfExis = response.data;
            console.log("attendanceIfExis: "+$scope.attendanceIfExis.length);
            
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    } ;
    $scope.viewAttendanceDetails=function(student){
        console.log("viewAttendanceDetails called");
        $scope.selectedStudent=student;
        studentId=student.studentId;
        console.log(student.student.studentId);
        console.log("selectedStudent: "+studentId);
        //setTimeout(load, 1000);
       getAttendanceForStudent(student.student.studentId);
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
        $("#myMessage").addClass("alert-success");
        setTimeout(clearMassage, 2500);
        console.log("jQuery...");
        getStudentsByclass();
        getAttendanceReport();
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

});