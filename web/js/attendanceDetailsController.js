app.controller("attendanceDetailsController", function ($scope, $http) {
    $scope.test = "my";
    $scope.student={};
    $scope.month={};
    $scope.att;
    $scope.attendanceByStudent=[{attendanceDate: "05-04-18", attendanceStatus: true}];
//getAttendanceForStudent(14,5);
    function getAttendanceForStudent(studentId, monthId) {
        console.log("getAttendanceForStudent for student....");
        console.log("selectStudent.studentId" + studentId);
        $http({
            method: 'GET',
            url: 'attendance/att/student/' + studentId + '/' + monthId
        }).then(function successCallback(response) {
            $scope.attendanceByStudent = response.data;
            console.log("getAttendanceForStudent: " + $scope.attendanceByStudent.length);
            console.log($scope.attendanceByStudent);
            $scope.student.studentId = studentId;
        $scope.month.monthId=monthId;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
    ;
    
    $scope.show=function(){
        
    };
    $scope.attendanceStatus = function (status) {
        if (status == true) {
            return "Present";
        } else {
            return "Absent";
        }

    };

    $scope.viewAttendanceDetails = function (student, month) {
        console.log("viewAttendanceDetails called");
        $scope.student.studentId = student.studentId;
        $scope.month.monthId=month.monthId;
        //viewAttendanceDetails($scope.student, $scope.month);
        console.log(student.student.studentId);
            getAttendanceForStudent(student.student.studentId, month.monthId);

    };
    $scope.show=function () {
        console.log($scope.student.studentId);
        getAttendanceForStudent($scope.att.student.studentId,5);
    };

    //setTimeout(load, 1000);

});


