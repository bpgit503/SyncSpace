-- Fitness Management System Database Schema
-- PostgreSQL DDL

-- Users table (handles clients and trainers)
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,
                       phone VARCHAR(20),
                       date_of_birth DATE,
                       address TEXT,
                       user_type VARCHAR(20) NOT NULL CHECK (user_type IN ('CLIENT', 'TRAINER')),
                       status VARCHAR(20) DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE')),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Trainer specific details
CREATE TABLE trainers (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          specializations TEXT[],
                          certifications TEXT[],
                          contract_details TEXT,
                          earnings_percentage DECIMAL(5,2) NOT NULL DEFAULT 50.00 CHECK (earnings_percentage >= 0 AND earnings_percentage <= 100),
                          hourly_rate DECIMAL(10,2),
                          bio TEXT,
                          experience_years INTEGER DEFAULT 0,
                          is_available BOOLEAN DEFAULT true,
                          UNIQUE(user_id)
);

-- Class types (customizable by admin)
CREATE TABLE class_types (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(100) NOT NULL UNIQUE,
                             description TEXT,
                             duration_minutes INTEGER NOT NULL DEFAULT 60,
                             is_group_class BOOLEAN NOT NULL DEFAULT true,
                             max_capacity INTEGER DEFAULT 15,
                             base_price DECIMAL(10,2) NOT NULL,
                             is_active BOOLEAN DEFAULT true,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Scheduled classes/sessions
CREATE TABLE classes (
                         id BIGSERIAL PRIMARY KEY,
                         class_type_id BIGINT NOT NULL REFERENCES class_types(id),
                         trainer_id BIGINT NOT NULL REFERENCES trainers(id),
                         scheduled_date DATE NOT NULL,
                         start_time TIME NOT NULL,
                         end_time TIME NOT NULL,
                         max_capacity INTEGER NOT NULL,
                         current_capacity INTEGER DEFAULT 0,
                         status VARCHAR(20) DEFAULT 'SCHEDULED' CHECK (status IN ('SCHEDULED', 'COMPLETED', 'CANCELLED')),
                         notes TEXT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP on update
);

-- Client bookings
CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          client_id BIGINT NOT NULL REFERENCES users(id),
                          class_id BIGINT NOT NULL REFERENCES classes(id),
                          booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          status VARCHAR(20) DEFAULT 'CONFIRMED' CHECK (status IN ('CONFIRMED', 'CANCELLED', 'COMPLETED', 'NO_SHOW')),
                          price_paid DECIMAL(10,2) NOT NULL,
                          payment_status VARCHAR(20) DEFAULT 'PENDING' CHECK (payment_status IN ('PENDING', 'PAID', 'REFUNDED')),
                          notes TEXT,
                          UNIQUE(client_id, class_id)
);

-- Invoices/Receipts
CREATE TABLE invoices (
                          id BIGSERIAL PRIMARY KEY,
                          client_id BIGINT NOT NULL REFERENCES users(id),
                          invoice_number VARCHAR(50) UNIQUE NOT NULL,
                          invoice_date DATE NOT NULL DEFAULT CURRENT_DATE,
                          due_date DATE,
                          total_amount DECIMAL(10,2) NOT NULL,
                          tax_amount DECIMAL(10,2) DEFAULT 0,
                          status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'PAID', 'OVERDUE', 'CANCELLED')),
                          payment_date TIMESTAMP,
                          payment_method VARCHAR(50),
                          notes TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Invoice line items (links bookings to invoices)
CREATE TABLE invoice_items (
                               id BIGSERIAL PRIMARY KEY,
                               invoice_id BIGINT NOT NULL REFERENCES invoices(id) ON DELETE CASCADE,
                               booking_id BIGINT NOT NULL REFERENCES bookings(id),
                               description TEXT NOT NULL,
                               quantity INTEGER DEFAULT 1,
                               unit_price DECIMAL(10,2) NOT NULL,
                               total_price DECIMAL(10,2) NOT NULL
);

-- Trainer earnings tracking
CREATE TABLE trainer_earnings (
                                  id BIGSERIAL PRIMARY KEY,
                                  trainer_id BIGINT NOT NULL REFERENCES trainers(id),
                                  class_id BIGINT NOT NULL REFERENCES classes(id),
                                  base_amount DECIMAL(10,2) NOT NULL,
                                  earnings_percentage DECIMAL(5,2) NOT NULL,
                                  earnings_amount DECIMAL(10,2) NOT NULL,
                                  payment_status VARCHAR(20) DEFAULT 'PENDING' CHECK (payment_status IN ('PENDING', 'PAID')),
                                  payment_date TIMESTAMP,
                                  calculated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  UNIQUE(trainer_id, class_id)
);

-- Indexes for performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_type ON users(user_type);
CREATE INDEX idx_classes_date_time ON classes(scheduled_date, start_time);
CREATE INDEX idx_classes_trainer ON classes(trainer_id);
CREATE INDEX idx_bookings_client ON bookings(client_id);
CREATE INDEX idx_bookings_class ON bookings(class_id);
CREATE INDEX idx_invoices_client ON invoices(client_id);
CREATE INDEX idx_trainer_earnings_trainer ON trainer_earnings(trainer_id);

-- Triggers for updated_at timestamps
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_users_updated_at BEFORE UPDATE ON users FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_classes_updated_at BEFORE UPDATE ON classes FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();


                                                                                                            ('Personal Training', '1-on-1 training session', 60, false, 1, 80.00),
                                                                                                            ('Group Yoga', 'Group yoga class for all levels', 75, true, 15, 25.00),
                                                                                                            ('CrossFit', 'High-intensity group workout', 60, true, 12, 30.00),
                                                                                                            ('Swimming Lessons', 'Private swimming instruction', 45, false, 1, 60.00);