package com.develhope.demo_interceptor2.interceptors;

import com.develhope.demo_interceptor2.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    private final List<Month> months = Arrays.asList(
            new Month(1, "January", "Gennaio", "Januar"),
            new Month(2, "February", "Febbraio", "Februar"),
            new Month(3, "March", "Marzo", "Marz"),
            new Month(4, "April", "Aprile", "April"),
            new Month(5, "May", "Maggio", "Mai"),
            new Month(6, "June", "Giugno", "Juni")
    );

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberString = request.getHeader("monthNumber");

        if (monthNumberString == null || monthNumberString.isEmpty()){
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing header");
            return false;
        }

        int monthNumber;
        try{
            monthNumber = Integer.parseInt(monthNumberString);
        } catch (NumberFormatException e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid format");
            return false;
        }

        Month month = months.stream()
                .filter(m -> m.getMonthNumber() == monthNumber)
                .findFirst()
                .orElse(new Month(monthNumber, "nope", "nope", "nope"));

        request.setAttribute("month", month);
        response.setStatus(HttpStatus.OK.value());
        return true;
    }

}
