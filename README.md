## Sync Space - A Fitness Management App
# Summary

Sync Space a client \- trainer fitness management app that is designed for an admin to use to schedule classes for clients and trainers, keep track of client payments, and trainer earnings. 

In future versions the app will be transformed to a web based portal where clients will be able to log in and check their payment history and book classes themselves. Where trainers can see their schedules and see what classes they will be doing with who and how many people will be attending and where admins will have master access to see business sensitive data.

# Definitions

## Users (Clients, Trainers, Admins)

A client is a user/s who will book and pay for a class with a trainer.
A trainer is a user who teaches classes that clients have booked and will be paid for their services  
An admin is a user who books clients to classes, trainers to classes, and provides invoices to clients and salary payslips to trainers.

## Classes

Clients and trainers will be assigned to classes by an admin. The class type and capacity will be determined by the Admin which will be according to the trainer capabilities and available facilities

## Class Type (Yoga, Pilates, etc.)

## Bookings (links clients to classes)

Bookings will show which classes clients have booked

## Invoice

Invoices will show clients which classes they have paid for.

# Admin Story

As an Admin  
I want to be able to add user (Clients and Trainers) to the system.
I want to be able to read user (Clients and Trainers) entries
I want to update user (Clients and Trainers) entries
I want to be able to delete user (Clients and Trainers) entries
I want to create classes and class their types  
I want to create bookings for clients and assign them to a class of their choice with the designated trainer.  
I want to create invoices for clients who have booked classes with trainers.  
I want to be able to see client payment history and the classes that they have previously booked

Acceptance Criteria:

1. Admins can create one class at a time with one class type and must indicate class capacity, start time and end time.
    1. Admins can read, edit and delete classes
    2. If a class is cancelled its status must be updated not deleted
2. Admins assign a trainer to teach a specified class
    1. Admins cannot overbook a trainer with multiple classes where the start time and end time overlap
3. Admins can book clients to classes
    1. Admins cannot add clients to a class that is already full.
4. Admins can issue invoice to clients
5. Admins can issue a trainers earnings to trainers
~~~~