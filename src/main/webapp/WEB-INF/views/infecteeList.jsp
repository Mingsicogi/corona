<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<jsp:include page="common/default.jsp"/>
<body>

<br>
<br>
<div class="row">
    <div class="col-lg-12">
        <div class="ibox animated fadeInDown">
            <div class="ibox-title"><h1>등록한 감염자 조회 페이지</h1>
                <div class="ibox-tools"><h3>등록된 데이터 수 : ${totalCnt}</h3></div>
            </div>
            <br>
            <div class="ibox-content">
                <div class="table-responsive">
                    <table class="table table-bordered" style="text-align: center">
                        <col width="5%">
                        <col width="5%">
                        <col width="5%">
                        <col width="5%">
                        <col width="25%">
                        <col width="7%">
                        <col width="*">
                        <col width="8%">
                        <col width="8%">
                        <col width="8%">
                        <col width="8%">
                        <thead>
                        <tr>
                            <th style="text-align: center; background-color: green">ID</th>
                            <th style="text-align: center; background-color: green">나이</th>
                            <th style="text-align: center; background-color: green">국적</th>
                            <th style="text-align: center; background-color: green">접촉자 수</th>
                            <th style="text-align: center; background-color: green">감염자 이동 경로</th>
                            <th style="text-align: center; background-color: green">우한시 방문 여부</th>
                            <th style="text-align: center; background-color: green">추가 정보</th>
                            <th style="text-align: center; background-color: green">확진자 발생일</th>
                            <th style="text-align: center; background-color: green">데이터 등록일</th>
                            <th style="text-align: center; background-color: green">데이터 수정일</th>
                            <th style="text-align: center; background-color: green">기능</th>
                        </tr>
                        </thead>
                        <tbody style="text-align: center">
                            <c:choose>
                                <c:when test="${not empty infecteeInfoList}">
                                    <c:forEach var="result" items="${infecteeInfoList}" varStatus="status">
                                        <tr>
                                            <td>${result.id}</td>
                                            <td>${result.age}</td>
                                            <td>${result.country}</td>
                                            <td>${result.howManyPeopleMeet}</td>
                                            <td>
                                                <c:forEach var="locationResult" items="${result.location}" varStatus="status">
                                                    위도 : ${locationResult.x} | 경도 : ${locationResult.y}
                                                    | 도착일:
                                                    <fmt:parseDate var="parseDate" value="${locationResult.arriveYmdt}" pattern="yyyy-MM-dd HH:mm"/>
                                                    <fmt:formatDate value="${parseDate}" pattern="yyyy-MM-dd HH:mm"/>
                                                    <br>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${result.virusSourceAreaVisitYn eq 'Y'}">
                                                        <span style="color: red">Y</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        N
                                                    </c:otherwise>
                                                </c:choose>
                                            <td>${result.detailInfo}</td>
                                            <td><fmt:parseDate var="parseDate" value="${result.issueOpenDate}" pattern="yyyy-MM-dd HH:mm"/>
                                                <fmt:formatDate value="${parseDate}" pattern="yyyy-MM-dd HH:mm"/>
                                            </td>
                                            <td>${result.regYmdt}</td>
                                            <td>${result.modYmdt}</td>
                                            <td>
                                                <div>
                                                    <button class="btn btn-sm btn-primary" onclick="openAddLocationModal('addModal','${result.id}')">이동경로 추가</button>
                                                </div>
                                                <div>
                                                    <a class="btn btn-sm btn-primary" href='/corona/infectee/input?action=modify&id=${result.id}'>수정하기</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="11" align="center">등록된 데이터가 없습니다.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
                <div style="text-align: center">
                    <a type="button" class="btn btn-success" href="/corona/infectee/input?action=add" >데이터 추가 페이지로 이동</a>
                    <a type="button" class="btn btn-danger" href="/corona/main" >메인 페이지로 이동</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="addModal" class="modal" tabindex="-1" role="dialog" aria-hidden="true" >
    <div class="modal-dialog" >
        <div class="modal-content animated fadeIn" style="width: 730px; background-color: #0f0f0f">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">감염자 위치 정보 추가하기</h4>
            </div>
            <div class="modal-body" style="background-color: #0f0f0f">
                <div>
                    위경도 검색 : <input class="form-group" id="addr" name="query" placeholder="위경도를 검색할 주소를 입력해주세요." style="width: 80%; color: #0f0f0f"/>
                    <button class="btn btn-sm btn-success" onclick="selectXY()">조회</button>
                </div>
                <form id="addFrm" name="addFrm">
                    <input id="id" name="id" type="hidden">
                    <table class="table">
                        <col width="25%">
                        <col width="*">
                        <tr>
                            <th>위도</th>
                            <td>
                                <input class="form-control input-sm" type="text" id="x" name="x">
                            </td>
                        </tr>
                        <tr>
                            <th>위도</th>
                            <td>
                                <input class="form-control input-sm" type="text" id="y" name="y">
                            </td>
                        </tr>
                        <tr>
                            <th>위치에 도착한 일짜</th>
                            <td>
                                <input class="form-control input-sm date" type="text" id="arriveYmdt" name="arriveYmdt" placeholder="위치에 도착한 시간" autocomplete="off">
                            </td>
                        </tr>
                    </table>
                </form>
                <div id="addAuthorityEmpList"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal" >Close</button>
                <button type="button" class="btn btn-primary" onclick="addLocationInfo();">
                    위치정보 추가
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        callBootstrapCalendar();  	// 요약내용을 쓰는 필드는 기본 컨텐츠일때만 사용
    }); //ready

    function openAddLocationModal(modalId, infecteeId) {
        $("#id").val(infecteeId);
        $("#" + modalId).modal("show");
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
                $("#x").val(data.x);
                $("#y").val(data.y);
            }
        });
    }

    function addLocationInfo() {
        $.ajax({
            url: '/corona/infectee/location/add',
            data: $("#addFrm").serialize(),
            type: 'POST',
            async: false,
            error: function (error) {
                alert('Error!');
            },
            success: function (data) {
                alert("데이터가 추가 완료!")
                window.location.reload();
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
</script>

</body>
</html>