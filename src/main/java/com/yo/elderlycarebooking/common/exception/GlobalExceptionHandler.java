package com.yo.elderlycarebooking.common.exception;

import com.yo.elderlycarebooking.common.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //    Bắt các Custom Exception như NotFoundException.Trả về 404 NOT FOUND kèm thông điệp chi tiết từ exception
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage()));
    }
    //    Bắt các trường hợp vi phạm logic nghiệp vụ về trùng lặp dữ liệu (ví dụ: đăng ký Email đã tồn tại)
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<Void>> handleConflict(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(ex.getMessage()));
    }
    //    BadRequestException: Lỗi logic do client gửi lên sai format hoặc vi phạm rule nghiệp vụ đơn giản
//    ConstraintViolationException: Lỗi validate dữ liệu ở tầng Controller/Service
//    khi dùng Bean Validation trực tiếp trên tham số (ví dụ @PathVariable @Min(1) Long id)
    @ExceptionHandler({BadRequestException.class, ConstraintViolationException.class})
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(Exception ex) {
        return ResponseEntity.badRequest().body(ApiResponse.error(ex.getMessage()));
    }
    //    Bắt lỗi khi client gửi DTO không hợp lệ qua @Valid hoặc @Validated (ví dụ: field @NotBlank, @Email bị sai)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("success", false);
        payload.put("message", "Validation failed");
        payload.put("errors", errors);
        return ResponseEntity.badRequest().body(payload);
    }
    //    Bắt lỗi đăng nhập sai username hoặc password từ Spring Security
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("Username or password is invalid"));
    }
    //    Bắt lỗi khi user đã đăng nhập nhưng không có đủ quyền (Role/Permission) để truy cập tài nguyên
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.error("Access denied"));
    }
    //    Bắt các lỗi cấp Database từ Spring Data JPA/Hibernate như vi phạm Unique Constraint,
//    Foreign Key Constraint (ví dụ: xóa một Category đang có Product chứa nó)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrity(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body(ApiResponse.error("Data integrity violation. Please check duplicate or foreign key values."));
    }
    //    Bắt tất cả các ngoại lệ chưa được khai báo cụ thể ở trên (NullPointerException, OutOfMemory, lỗi kết nối DB,...)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnknown(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Internal server error: " + ex.getMessage()));
    }
}
