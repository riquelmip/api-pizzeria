package com.riquelmi.apipizzeria;

import com.riquelmi.apipizzeria.utils.ResponseObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorHandler implements ErrorController {

    @RequestMapping("/error")
    public @ResponseBody ResponseObject handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ResponseObject response = new ResponseObject(ResponseObject.CODE_GENERAL, ResponseObject.MSG_GENERAL, null);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            switch (statusCode) {
                case 404:
                    response.setCode(ResponseObject.CODE_NOT_FOUND);
                    response.setResponse(ResponseObject.MSG_NOT_FOUND);
                    break;
                case 403:
                    response.setCode(ResponseObject.CODE_UNAUTHORIZED);
                    response.setResponse(ResponseObject.MSG_UNAUTHORIZED);
                    break;
                case 400:
                    response.setCode(ResponseObject.CODE_BAD_REQUEST);
                    response.setResponse(ResponseObject.MSG_BAD_REQUEST);
                    break;
                case 405:
                    response.setCode(ResponseObject.CODE_METHOD_NOT_ALLOWED);
                    response.setResponse(ResponseObject.MSG_METHOD_NOT_ALLOWED);
                    break;
                case 500:
                    response.setCode(ResponseObject.CODE_LOGIC_ERROR);
                    response.setResponse(ResponseObject.MSG_LOGIC_ERROR + " (500)");
                    break;
                default:
                    break;
            }
        }
        return response;
    }


}
