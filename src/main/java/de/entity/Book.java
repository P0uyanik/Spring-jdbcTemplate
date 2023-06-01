package de.entity;

import java.time.LocalDate;

public record Book( int authorId , String title , LocalDate yearOfPublication ) { }
