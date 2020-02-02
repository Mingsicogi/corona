<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="common/default.jsp"/>
<body style="color: white">
<br>
<br>
<div>
    위경도 검색 : <input class="form-group" id="addr" name="query" placeholder="위경도를 검색할 주소를 입력해주세요." style="width: 500px"/>
    <button onclick="selectXY()">조회</button>
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
                <h5>감염자 데이터 추가</h5>
            </div>
            <div class="ibox-content">
                <div class="table-responsive">
                    <form id="addFrm" name="addFrm">
                        <table class="table table-bordered table-hover">
                            <col width="20%">
                            <col width="*">
                            <tbody>
                            <tr>
                                <td>
                                    발생 순서
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" name="infectOrder">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    우한시 방문 여부
                                </td>
                                <td>
                                    <select class="form-control input-sm" type="text" name="virusSourceAreaVisitYn">
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
                                    <input class="form-control input-sm" type="text" name="whichHospital">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    감염자 표시 색
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" name="markingColor" placeholder="영어로 입력해주세요">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    접촉자 수
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" name="howManyPeopleMeet">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    확진자가 발생한 위치
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" name="x" placeholder="위도">
                                    <input class="form-control input-sm" type="text" name="y" placeholder="경도">
                                    <input class="form-control input-sm date" type="text" id="arriveYmdt" name="arriveYmdt" placeholder="위치에 도착한 시간" autocomplete="off">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    감염자에 대한 추가 정보
                                </td>
                                <td>
                                    <textarea class="form-control input-sm" name="detailInfo" ></textarea>
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

</body>
</html>
<script>
    $(document).ready(function () {
        callBootstrapCalendar('issueOpenDate');  	// 요약내용을 쓰는 필드는 기본 컨텐츠일때만 사용
        callBootstrapCalendar('arriveYmdt');  	// 요약내용을 쓰는 필드는 기본 컨텐츠일때만 사용
    }); //ready

    function saveInfo() {
        $.ajax({
            url: '/corona/infectee/add',
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

    function callBootstrapCalendar(targetId) {
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

</script>