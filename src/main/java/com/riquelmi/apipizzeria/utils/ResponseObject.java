package com.riquelmi.apipizzeria.utils;

public final class ResponseObject {

    /* CODES */
    public static final String CODE_GENERAL = "GENERAL";
    public static final String CODE_SUCCESS = "SUCCESS";
    public static final String CODE_ERROR = "ERROR";
    public static final String CODE_UNAUTHORIZED = "UNAUTHORIZED";
    public static final String CODE_FORBIDDEN = "FORBIDDEN";
    public static final String CODE_NO_PARAMS = "NO_PARAMS";
    public static final String CODE_NOT_FOUND = "NOT_FOUND";
    public static final String CODE_LOGIC_ERROR = "LOGIC_ERROR";
    public static final String CODE_BAD_REQUEST = "BAD_REQUEST";
    public static final String CODE_INACTIVE_USER = "INACTIVE_USER";
    public static final String CODE_SESSION_EXP = "SESSION_EXPIRED";
    public static final String CODE_METHOD_NOT_ALLOWED = "METHOD_NOT_ALLOWED";

    /* MESSAGES */
    public static final String MSG_GENERAL = "Error general";
    public static final String MSG_SUCCESS = "Solicitud realizada";
    public static final String MSG_ERROR = "Se produjo un error en la solicitud";
    public static final String MSG_UNAUTHORIZED = "Sin autorización";
    public static final String MSG_FORBIDDEN = "Sin autorización";
    public static final String MSG_NO_PARAMS = "No se recibieron los parametros necesarios";
    public static final String MSG_LOGIC_ERROR = "Error logico inesperado";
    public static final String MSG_BAD_REQUEST = "Solicitud incorrecta";
    public static final String MSG_INACTIVE_USER = "Usuario inactivo";
    public static final String MSG_SESSION_EXP = "La sesion ha expirado";
    public static final String MSG_METHOD_NOT_ALLOWED = "Método no soportado";
    public static final String MSG_REGISTER = "Registrado correctamente";
    public static final String MSG_UPDATE = "Editado correctamente";
    public static final String MSG_DELETE = "Eliminado correctamente";
    public static final String MSG_EXISTS = "Ese registro ya existe";
    public static final String MSG_NOT_FOUND = "No encontrado";

    String code;
    String response;
    Object data;

    public ResponseObject() {}

    public ResponseObject(String code, String response, Object data) {
        this.code = code;
        this.response = response;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
