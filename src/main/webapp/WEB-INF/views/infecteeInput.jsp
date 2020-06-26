<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="common/default.jsp"/>
<body style="color: white">
<style>
    .datepicker {
        color: #0f0f0f;
    }
</style>
<br>
<br>
<div class="col-lg-6">
    <div>
        위경도 검색 : <input class="form-group" id="addr" name="query" placeholder="위경도를 검색할 주소를 입력해주세요." style="width: 80%; color: #0f0f0f"/>
        <button onclick="selectXY()">조회</button>
    </div>
    <div id="searchResult" style="display: none">
        위도 : <text id="lat"></text> | 경도 : <text id="lon"></text>
    </div>
</div>

<br>
<br>
<br>
<div class="row">
    <div class="col-lg-12">
        <div class="ibox ">
            <div class="ibox-title">
                <h2 style="font-size: xx-large;">감염자 데이터 <c:choose><c:when test="${reqAction eq 'modify'}">수정</c:when><c:otherwise>추가</c:otherwise> </c:choose></h2>
            </div>
            <div class="ibox-content">
                <div class="table-responsive">
                    <form id="addFrm" name="addFrm">
                        <input id="id" name="id" type="hidden">
                        <input id="locationId" name="locationId" type="hidden">
                        <table class="table table-bordered">
                            <col width="20%">
                            <col width="*">
                            <tbody>
                            <tr>
                                <td>
                                    발생 순서
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" id="infectOrder" name="infectOrder">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    나이
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" id="age" name="age">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    국적
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" id="country" name="country">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    우한시 방문 여부
                                </td>
                                <td>
                                    <select class="form-control input-sm" type="text" id="virusSourceAreaVisitYn" name="virusSourceAreaVisitYn">
                                        <option value="Y">Y</option>
                                        <option value="N">N</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    감염자 입원 병원
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" id="whichHospital" name="whichHospital">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    감염자 표시 색
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" id="markingColor" name="markingColor" placeholder="영어로 입력해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    접촉자 수
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" id="howManyPeopleMeet" name="howManyPeopleMeet">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    확진자가 발생한 위치
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" id="x" name="x" placeholder="위도">
                                    <input class="form-control input-sm" type="text" id="y" name="y" placeholder="경도">
                                    <input class="form-control input-sm date" type="text" id="arriveYmdt" name="arriveYmdt" placeholder="위치에 도착한 시간" autocomplete="off">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    감염자에 대한 추가 정보
                                </td>
                                <td>
                                    <textarea class="form-control input-sm" id="detailInfo" name="detailInfo" ></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    확진일
                                </td>
                                <td>
                                    <input class="form-control input-sm date" type="text" id="issueOpenDate" name="issueOpenDate" placeholder="확진자가 발생한 일시" autocomplete="off">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div style="text-align: center">
                <a type="button" class="btn btn-success" onclick="saveInfo()" >데이터 추가</a>
                <a type="button" class="btn btn-warning" href="/corona/infectee/list" >추가된 데이터 목록</a>
                <a type="button" class="btn btn-danger" href="/corona/main" >메인 페이지로 이동</a>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        callBootstrapCalendar();  	// 요약내용을 쓰는 필드는 기본 컨텐츠일때만 사용

        var data = '${reqAction}';
        if(data !== '' && data === "modify") {

            $("#id").val('${id}');

            $("#infectOrder").val('${infectOrder}');
            $("#age").val('${age}');
            $("#country").val('${country}');
            $("#location").val('${location}');
            $("#howManyPeopleMeet").val('${howManyPeopleMeet}');
            $("#markingColor").val('${markingColor}');
            $("#virusSourceAreaVisitYn").val('${virusSourceAreaVisitYn}');
            $("#markingColor").val('${markingColor}');
            $("#whichHospital").val('${whichHospital}');
            $("#detailInfo").val('${detailInfo}');

            // first location
            $("#locationId").val('${location[0].id}');
            $("#x").val('${location[0].x}');
            $("#y").val('${location[0].y}');
            $("#arriveYmdt").val(getFormatDate(new Date(${location[0].arriveYmdt})));

            //date
            $("#issueOpenDate").val(getFormatDate(new Date(${issueOpenDate})));
        }
    }); //ready

    function saveInfo() {
        $.ajax({
            url: '/corona/infectee/add?action=' + '${reqAction}',
            data: $("#addFrm").serialize(),
            type: 'POST',
            async: false,
            error: function (error) {
                alert(error.responseText);
            },
            success: function (data) {
                alert("데이터가 추가 완료!")
                window.location.reload();
            }
        })
    }

    function selectXY() {
        var query = $("#addr").val();

        if (query === null || query === '') {
            alert("위경도를 검색할 주소를 입력해주세요.");
            return;
        }

        var data = {
            query: query
        };

        $.ajax({
            url: '/corona/infectee/search/location',
            data: data,
            type: 'POST',
            async: false,
            error: function (error) {
                alert('Error!');
            },
            success: function (data) {
                $("#searchResult").show();
                $("#lat").html(data.x);
                $("#lon").html(data.y);
            }
        });
    }

    function callBootstrapCalendar() {
        var formatStr = "yyyymmdd";
        var clareCalendar = {
            language: $("html").attr('lang'),
            autoclose: true,
            calendarWeeks: false,
            clearBtn: false,
            daysOfWeekDisabled: [],
            forceParse: true,
            format: formatStr,
            keyboardNavigation: true,
            minViewMode: 0,
            multidate: false,
            multidateSeparator: ',',
            orientation: "auto",
            rtl: false,
            startDate: -Infinity,
            startView: 0,
            endDate: Infinity,
            todayBtn: "linked",
            todayHighlight: true,
            weekStart: 0
        };
        // $("#" + targetId).datepicker(clareCalendar);
        $('.date').datepicker(clareCalendar);
        // var date = moment($("#" + targetId).val(), "YYYYMMDD");
        var date = moment($('.date').val(), "YYYYMMDD");
        if (date.isValid()) {
            // $("#" + targetId).datepicker('update', date.toDate());
            $('.date').datepicker('update', date.toDate());
        }
    }

    /**
     *  yyyyMMdd 포맷으로 반환
     */
    function getFormatDate(date){
        var year = date.getFullYear();              //yyyy
        var month = (1 + date.getMonth());          //M
        month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
        var day = date.getDate();                   //d
        day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
        return  year + '' + month + '' + day;
    }
</script>


</body>
</html>