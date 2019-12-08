var app = angular.module("studentModule", ["ngRoute"])
        .config(function ($routeProvider) {
            $routeProvider
                    .when("/student", {
                        templateUrl: "student.html",
                        controller: "studentController"
                    })
                    .when("/attendance", {
                        templateUrl: "attendance.html",
                        controller: "attendanceController"
                    })
                    .when("/studentAttendanceDetails", {
                        templateUrl: "studentAttendanceDetails.html",
                        controller: "attendanceController"
                    });
        });


