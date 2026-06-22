package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;


public enum ErrorCode {
  INTERNAL_ERROR(100, "An internal error ocurred", "INTERNAL_ERROR"),
  BAD_REQUEST_ERROR(101, "Bad request", "BAD_REQUEST_ERROR"),
  DATABASE_ERROR(102, "Database Error", "DATABASE_ERROR"),
  REST_CLIENT_ERROR(103, "Unexpected rest client error", "REST_CLIENT_ERROR"),
  NOT_FOUND_ERROR(104, "Not Found Error", "NOT_FOUND_ERROR"),
  EXTERNAL_SERVICE_ERROR(105, "External service error", "EXTERNAL_SERVICE_ERROR"),
  ROLE_NOT_FOUND(107, "Role Not Found Error", "ROLE_NOT_FOUND_ERROR"),
  AUDIENCE_BAD_REQUEST(110, "Audience Bad Request Error", "AUDIENCE_BAD_REQUEST_ERROR"),
  AUDIT_REQUIRED_FIELD_ERROR(112, "Audit required field Error", "AUDIT_REQUIRED_FIELD_ERROR"),
  INVALID_RESULT_ERROR(116, "Invalid Result Error", "INVALID_RESULT_ERROR"),
  USER_NOT_FOUND(120, "User Not Found Error", "USER_NOT_FOUND_ERROR"),
  USER_BAD_REQUEST(121, "User Bad Request Error", "USER_BAD_REQUEST_ERROR"),
  INVALID_DATE_RANGE(122, "Invalid Date Range", "INVALID_DATE_RANGE"),
  INVALID_USER_ERROR(128,"Invalid User Error","INVALID_USER_ERROR"),
  INVALID_FILTER_TYPE_ERROR(133, "Filter type not valid", "INVALID_FILTER_TYPE_ERROR"),
  ROLE_NOT_FOUND_ERROR(134,"Role Not Found Error","ROLE_NOT_FOUND_ERROR"),
  AUDIENCE_FORBIDDEN(136, "Audience Forbidden", "AUDIENCE_FORBIDDEN"),
  ACCUSED_LAWYER_BUSY(138, "Accused Lawyer Busy", "ACCUSED_LAWYER_BUSY"),
  CASE_FILE_CRIME_SCENE_PRIMARY_CONFLICT(150, "A primary crime scene already exists for this case file", "CASE_FILE_CRIME_SCENE_PRIMARY_CONFLICT"),
  CASE_FILE_CRIME_SCENE_PRIMARY_DELETE_CONFLICT(151, "A primary record cannot be deleted", "CASE_FILE_CRIME_SCENE_PRIMARY_DELETE_CONFLICT");


  private final int value;
  private final String reason;
  private final String code;

  ErrorCode(int value, String reason, String code) {
    this.value = value;
    this.reason = reason;
    this.code = code;
  }

  public int value() {
    return this.value;
  }

  public String getReason() {
    return this.reason;
  }

  public String getCode() {
    return this.code;
  }
}
