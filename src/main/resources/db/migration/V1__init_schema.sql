
-- ==============================================================================
-- V1 — Schema khởi tạo cho ElderlyCareBooking
-- ==============================================================================

-- ------------------------------------------------------------------------------
-- 1. BẢNG NGƯỜI DÙNG (USERS)
-- ------------------------------------------------------------------------------
CREATE TABLE users (
    user_id     BIGSERIAL    PRIMARY KEY,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    full_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(100),
    role        VARCHAR(20)  NOT NULL DEFAULT 'CUSTOMER'
                    CHECK (role IN ('CUSTOMER', 'CAREGIVER', 'ADMIN')),
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_username ON users (username);
CREATE INDEX idx_users_phone    ON users (phone_number);

-- ------------------------------------------------------------------------------
-- 2. BẢNG QUẢN LÝ REFRESH TOKEN (USER_REFRESH_TOKENS)
-- ------------------------------------------------------------------------------
CREATE TABLE user_refresh_tokens (
    token_id    BIGSERIAL    PRIMARY KEY,
    user_id     BIGINT       NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    token_hash  VARCHAR(255) NOT NULL UNIQUE,
    is_revoked  BOOLEAN      NOT NULL DEFAULT FALSE,
    expires_at  TIMESTAMP    NOT NULL,
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_refresh_token_hash ON user_refresh_tokens (token_hash);
CREATE INDEX idx_refresh_token_user ON user_refresh_tokens (user_id);

-- ------------------------------------------------------------------------------
-- 3. BẢNG HỒ SƠ NGƯỜI CAO TUỔI / NGƯỜI BỆNH (CARE_RECIPIENTS)
-- ------------------------------------------------------------------------------
CREATE TABLE care_recipients (
    recipient_id  BIGSERIAL   PRIMARY KEY,
    customer_id   BIGINT      NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    full_name     VARCHAR(100) NOT NULL,
    gender        VARCHAR(10)  NOT NULL CHECK (gender IN ('MALE', 'FEMALE', 'OTHER')),
    year_of_birth INT          NOT NULL,
    mobility_level VARCHAR(20) NOT NULL DEFAULT 'INDEPENDENT'
                      CHECK (mobility_level IN ('INDEPENDENT', 'ASSISTED', 'BEDRIDDEN')),
    medical_notes TEXT,
    special_notes TEXT,
    created_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_recipients_customer ON care_recipients (customer_id);

-- ------------------------------------------------------------------------------
-- 4. BẢNG THÔNG TIN CHĂM SÓC VIÊN (CAREGIVERS)
-- ------------------------------------------------------------------------------
CREATE TABLE caregivers (
    caregiver_id     BIGSERIAL    PRIMARY KEY,
    user_id          BIGINT       NOT NULL UNIQUE REFERENCES users(user_id) ON DELETE CASCADE,
    experience_years INT          NOT NULL DEFAULT 0,
    qualification    VARCHAR(100) NOT NULL,
    bio              TEXT,
    is_available     BOOLEAN      NOT NULL DEFAULT TRUE,
    avg_rating       NUMERIC(3,2) NOT NULL DEFAULT 5.00,
    total_jobs_done  INT          NOT NULL DEFAULT 0,
    created_at       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_caregivers_user ON caregivers (user_id);

-- ------------------------------------------------------------------------------
-- 5. BẢNG ĐƠN ĐẶT LỊCH (BOOKINGS)
-- ------------------------------------------------------------------------------
CREATE TABLE bookings (
    booking_id      BIGSERIAL    PRIMARY KEY,
    booking_code    VARCHAR(20)  NOT NULL UNIQUE,
    customer_id     BIGINT       NOT NULL REFERENCES users(user_id),
    recipient_id    BIGINT       NOT NULL REFERENCES care_recipients(recipient_id),
    caregiver_id    BIGINT       REFERENCES caregivers(caregiver_id),

    service_type    VARCHAR(30)  NOT NULL DEFAULT 'HOURLY'
                        CHECK (service_type IN ('HOURLY', 'STAY_247', 'MONTHLY_RECURRING')),
    location_type   VARCHAR(20)  NOT NULL DEFAULT 'HOME'
                        CHECK (location_type IN ('HOME', 'HOSPITAL')),
    address         TEXT         NOT NULL,
    address_detail  VARCHAR(100),

    duration_hours  INT          NOT NULL DEFAULT 2
                        CHECK (duration_hours IN (2, 4, 6, 8, 12, 24)),
    work_date       DATE         NOT NULL,
    start_time      TIME         NOT NULL,
    end_time        TIME         NOT NULL,
    prefer_favorite_tasker  BOOLEAN DEFAULT FALSE,
    is_weekly_recurring     BOOLEAN DEFAULT FALSE,
    work_notes      TEXT,

    contact_name    VARCHAR(100) NOT NULL,
    contact_phone   VARCHAR(15)  NOT NULL,
    service_price   NUMERIC(12,2) NOT NULL,
    discount_amount NUMERIC(12,2) DEFAULT 0.00,
    total_price     NUMERIC(12,2) NOT NULL,
    payment_status  VARCHAR(20)  NOT NULL DEFAULT 'UNPAID'
                        CHECK (payment_status IN ('UNPAID', 'PAID', 'REFUNDED')),

    status          VARCHAR(20)  NOT NULL DEFAULT 'PENDING'
                        CHECK (status IN ('PENDING', 'ASSIGNED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')),
    check_in_time   TIMESTAMP,
    check_out_time  TIMESTAMP,
    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_bookings_customer  ON bookings (customer_id);
CREATE INDEX idx_bookings_caregiver ON bookings (caregiver_id);
CREATE INDEX idx_bookings_status    ON bookings (status);

-- ------------------------------------------------------------------------------
-- 6. BẢNG LỊCH SỬ GIAO DỊCH THANH TOÁN (PAYMENTS)
-- ------------------------------------------------------------------------------
CREATE TABLE payments (
    payment_id       BIGSERIAL     PRIMARY KEY,
    booking_id       BIGINT        NOT NULL REFERENCES bookings(booking_id) ON DELETE CASCADE,
    transaction_code VARCHAR(100)  UNIQUE,
    amount           NUMERIC(12,2) NOT NULL,
    payment_method   VARCHAR(20)   NOT NULL
                         CHECK (payment_method IN ('CASH', 'BPAY', 'MOMO', 'VNPAY', 'VIETQR', 'CREDIT_CARD')),
    status           VARCHAR(20)   NOT NULL DEFAULT 'PENDING'
                         CHECK (status IN ('PENDING', 'SUCCESS', 'FAILED', 'REFUNDED')),
    paid_at          TIMESTAMP,
    gateway_response TEXT,
    created_at       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_payments_booking ON payments (booking_id);
CREATE INDEX idx_payments_status  ON payments (status);

-- ------------------------------------------------------------------------------
-- 7. BẢNG NHẬT KÝ CA LÀM VIỆC (CARE_LOGS)
-- ------------------------------------------------------------------------------
CREATE TABLE care_logs (
    log_id              BIGSERIAL    PRIMARY KEY,
    booking_id          BIGINT       NOT NULL REFERENCES bookings(booking_id) ON DELETE CASCADE,
    blood_pressure_sys  INT,
    blood_pressure_dia  INT,
    heart_rate          INT,
    temperature         NUMERIC(4,1),
    meals_intake        VARCHAR(50),
    medications_given   TEXT,
    caregiver_notes     TEXT,
    recorded_at         TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_carelogs_booking ON care_logs (booking_id);

-- ------------------------------------------------------------------------------
-- 8. BẢNG ĐÁNH GIÁ SAU CA LÀM (REVIEWS)
-- ------------------------------------------------------------------------------
CREATE TABLE reviews (
    review_id    BIGSERIAL    PRIMARY KEY,
    booking_id   BIGINT       NOT NULL UNIQUE REFERENCES bookings(booking_id) ON DELETE CASCADE,
    customer_id  BIGINT       NOT NULL REFERENCES users(user_id),
    caregiver_id BIGINT       NOT NULL REFERENCES caregivers(caregiver_id),
    rating       INT          NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment      TEXT,
    created_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_reviews_caregiver ON reviews (caregiver_id);

-- ------------------------------------------------------------------------------
-- 9. BẢNG THÔNG BÁO (NOTIFICATIONS)
-- ------------------------------------------------------------------------------
CREATE TABLE notifications (
    notification_id BIGSERIAL    PRIMARY KEY,
    user_id         BIGINT       NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    title           VARCHAR(150) NOT NULL,
    content         TEXT         NOT NULL,
    is_read         BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_notifications_user ON notifications (user_id);
