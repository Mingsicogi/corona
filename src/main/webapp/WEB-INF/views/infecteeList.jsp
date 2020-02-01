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
                    <table class="table table-bordered table-hover">
                        <col width="5%">
                        <col width="5%">
                        <col width="15%">
                        <col width="*">
                        <col width="8%">
                        <col width="8%">
                        <col width="8%">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>접촉자 수</th>
                            <th>감염자 이동 경로</th>
                            <th>추가 보정</th>
                            <th>확진자 발생일</th>
                            <th>데이터 등록일</th>
                            <th>기능</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty infecteeInfoList}">
                                    <c:forEach var="result" items="${infecteeInfoList}" varStatus="status">
                                        <tr>
                                            <td style="text-align: center">${result.id}</td>
                                            <td>${result.howManyPeopleMeet}</td>
                                            <td>
                                                <c:forEach var="locationResult" items="${result.location}" varStatus="status">
                                                    위도 : ${locationResult.x} | 경도 : ${locationResult.y}<br>
                                                </c:forEach>
                                            </td>
                                            <td>${result.detailInfo}</td>
                                            <td><fmt:parseDate var="parseDate" value="${result.issueOpenDate}" pattern="yyyy-MM-dd HH:mm"/>
                                                <fmt:formatDate value="${parseDate}" pattern="yyyy-MM-dd HH:mm"/>
                                            </td>
                                            <td><fmt:parseDate var="parseDate" value="${result.regYmdt}" pattern="yyyy-MM-dd HH:mm"/>
                                                <fmt:formatDate value="${parseDate}" pattern="yyyy-MM-dd HH:mm"/>
                                            </td>
                                            <td>
                                                <button class="btn btn-sm btn-primary" onclick="">이동경로 추가</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="7" align="center">등록된 데이터가 없습니다.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
                <div style="text-align: center">
                    <a type="button" class="btn btn-success" href="javascript:history.back();" >데이터 추가 페이지로 이동</a>
                    <a type="button" class="btn btn-danger" href="/main" >메인 페이지로 이동</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>